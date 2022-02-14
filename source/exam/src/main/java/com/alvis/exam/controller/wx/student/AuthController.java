package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.configuration.property.SystemConfig;
import com.alvis.exam.controller.wx.BaseWXApiController;
import com.alvis.exam.domain.UserToken;
import com.alvis.exam.domain.enums.UserStatusEnum;
import com.alvis.exam.service.AuthenticationService;
import com.alvis.exam.service.UserService;
import com.alvis.exam.service.UserTokenService;
import com.alvis.exam.utility.WxResponse;
import com.alvis.exam.utility.WxUtil;
import com.alvis.exam.viewmodel.wx.student.user.BindInfo;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;


@Controller("WXStudentAuthController")
@RequestMapping(value = "/api/wx/student/auth")
@AllArgsConstructor
@ResponseBody
public class AuthController extends BaseWXApiController {

    private final SystemConfig systemConfig;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserTokenService userTokenService;

    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public RestResponse bind(@Valid BindInfo model) {
        com.alvis.exam.domain.User user = userService.getUserByUserName(model.getUserName());
        if (user == null) {
            return RestResponse.fail(2, "用户名或密码错误");
        }
        boolean result = authenticationService.authUser(user, model.getUserName(), model.getPassword());
        if (!result) {
            return RestResponse.fail(2, "用户名或密码错误");
        }
        UserStatusEnum userStatusEnum = UserStatusEnum.fromCode(user.getStatus());
        if (UserStatusEnum.Disable == userStatusEnum) {
            return RestResponse.fail(3, "用户被禁用");
        }
        String code = model.getCode();
        String openid = WxUtil.getOpenId(systemConfig.getWx().getAppid(), systemConfig.getWx().getSecret(), code);
        if (null == openid) {
            return RestResponse.fail(4, "获取微信OpenId失败");
        }
        user.setWxOpenId(openid);
        UserToken userToken = userTokenService.bind(user);
        return RestResponse.ok(userToken.getToken());
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RestResponse login(String code) {

        WxResponse wxResponse = WxUtil.getWxResponse(systemConfig.getWx().getAppid(), systemConfig.getWx().getSecret(), code);
        if (null == wxResponse.getOpenid()) {
            return RestResponse.fail(4, "获取微信OpenId失败");
        }
        if (null == wxResponse.getSession_key()) {
            return RestResponse.fail(4, "获取微信sessionkey失败");
        }
        Map map= new HashMap();
        map.put("openId",wxResponse.getOpenid());
        map.put("sessionkey",wxResponse.getSession_key());
        return RestResponse.ok(map);
    }
    @RequestMapping(value = "/mobile", method = RequestMethod.POST)
    public RestResponse mobile(@RequestParam Map<String, Object> params) {
        String encryptedData = (String) params.get("encryptedData");
        String iv = (String) params.get("iv");
        String sessionkey = (String) params.get("sessionkey");
        String string = WxUtil.decrypt(sessionkey,encryptedData,iv);
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(string, map.getClass());
        map.put("mobile",map.get("phoneNumber"));
        return RestResponse.ok(map);
    }
    @RequestMapping(value = "/checkBind", method = RequestMethod.POST)
    public RestResponse checkBind(@Valid @NotBlank String code) {
        String openid = WxUtil.getOpenId(systemConfig.getWx().getAppid(), systemConfig.getWx().getSecret(), code);
        if (null == openid) {
            return RestResponse.fail(3, "获取微信OpenId失败");
        }
        UserToken userToken = userTokenService.checkBind(openid);
        if (null != userToken) {
            return RestResponse.ok(userToken.getToken());
        }
        return RestResponse.fail(2, "用户未绑定");
    }


    @RequestMapping(value = "/unBind", method = RequestMethod.POST)
    public RestResponse unBind() {
        UserToken userToken = getUserToken();
        userTokenService.unBind(userToken);
        return RestResponse.ok();
    }
}
