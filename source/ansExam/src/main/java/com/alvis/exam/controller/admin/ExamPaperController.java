package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.*;
import com.alvis.exam.domain.enums.ExamPaperAnswerStatusEnum;
import com.alvis.exam.event.UserEvent;
import com.alvis.exam.service.*;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.utility.ExamUtil;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperEditAllRequestVM;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.alvis.exam.viewmodel.admin.exam.ExamResponseVM;
import com.alvis.exam.viewmodel.student.exam.ExamPaperReadVM;
import com.alvis.exam.viewmodel.student.exam.ExamPaperSubmitVM;
import com.alvis.exam.viewmodel.student.exampaper.ExamPaperAnswerPageResponseVM;
import com.alvis.exam.viewmodel.student.exampaper.ExamPaperAnswerPageVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController("AdminExamPaperController")
@RequestMapping(value = "/api/admin/exam/paper")
@AllArgsConstructor
public class ExamPaperController extends BaseApiController {

    private final ExamPaperService examPaperService;
    private final ExamPaperAnswerService examPaperAnswerService;
    private final SubjectService subjectService;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private final MessageService messageService;


    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamResponseVM>> pageList(@RequestBody ExamPaperPageRequestVM model) {
        PageInfo<ExamPaper> pageInfo = examPaperService.page(model);
        PageInfo<ExamResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamResponseVM vm = modelMapper.map(e, ExamResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }
    @RequestMapping(value = "/selectYu/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> selectYu(@PathVariable Integer id) {
        ExamPaperEditRequestVM vm = examPaperService.examPaperToVM(id);
        return RestResponse.ok(vm);
    }
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamPaperAnswerPageResponseVM>> pageList(@RequestBody @Valid ExamPaperAnswerPageVM model) {
        if (StringUtils.isNotBlank(model.getUserName())){
            List<Integer> userId  = userService.LikeUserByUserName(model.getUserName()).stream().map(User::getId).collect(Collectors.toList());
            model.setUserId(userId);
        }
        PageInfo<ExamPaperAnswer> pageInfo = examPaperAnswerService.studentPage2(model);
        PageInfo<ExamPaperAnswerPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamPaperAnswerPageResponseVM vm = modelMapper.map(e, ExamPaperAnswerPageResponseVM.class);
            Subject subject = subjectService.selectById(vm.getSubjectId());
            vm.setDoTime(ExamUtil.secondToVM(e.getDoTime()));
            vm.setSystemScore(ExamUtil.scoreToVM(e.getSystemScore()));
            vm.setUserScore(ExamUtil.scoreToVM(e.getUserScore()));
            vm.setPaperScore(ExamUtil.scoreToVM(e.getPaperScore()));
            vm.setSubjectName(subject.getName());
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            vm.setUser(userService.getUserById(e.getCreateUser()));
            return vm;
        });
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public RestResponse<List<ExamPaperAnswerPageResponseVM>> listExcel(@RequestBody @Valid ExamPaperAnswerPageVM model) {
        if (StringUtils.isNotBlank(model.getUserName())){
            List<Integer> userId  = userService.LikeUserByUserName(model.getUserName()).stream().map(User::getId).collect(Collectors.toList());
            model.setUserId(userId);
        }
        List<ExamPaperAnswer> page = examPaperAnswerService.studentExcel(model);
        List<ExamPaperAnswerPageResponseVM> pageInfo = page.stream().map( o -> {
            ExamPaperAnswerPageResponseVM vm = modelMapper.map(o, ExamPaperAnswerPageResponseVM.class);
            Subject subject = subjectService.selectById(vm.getSubjectId());
            vm.setDoTime(ExamUtil.secondToVM(o.getDoTime()));
            vm.setSystemScore(ExamUtil.scoreToVM(o.getSystemScore()));
            vm.setUserScore(ExamUtil.scoreToVM(o.getUserScore()));
            vm.setPaperScore(ExamUtil.scoreToVM(o.getPaperScore()));
            vm.setSubjectName(subject.getName());
            vm.setPaperTypeName(o.getPaperType() == 4 ? "时段试卷" : "固定试卷");
            vm.setCreateTime(DateTimeUtil.dateFormat(o.getCreateTime()));
            vm.setUserName(userService.getUserById(o.getCreateUser()).getUserName());
            return vm;
        }).collect(Collectors.toList());
        return RestResponse.ok(pageInfo);
    }
    @RequestMapping(value = "/taskExamPage", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamResponseVM>> taskExamPageList(@RequestBody ExamPaperPageRequestVM model) {
        PageInfo<ExamPaper> pageInfo = examPaperService.taskExamPage(model);
        PageInfo<ExamResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamResponseVM vm = modelMapper.map(e, ExamResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }



    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> edit(@RequestBody @Valid ExamPaperEditRequestVM model) {
        ExamPaper examPaper = examPaperService.savePaperFromVM(model, getCurrentUser());
        ExamPaperEditRequestVM newVM = examPaperService.examPaperToVM(examPaper.getId());
        return RestResponse.ok(newVM);
    }
    @RequestMapping(value = "/editAll", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> editAll(@RequestBody @Valid ExamPaperEditAllRequestVM model) {
        examPaperService.editAllPaperFromVM(model, getCurrentUser());
        return RestResponse.ok();
    }
    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> select(@PathVariable Integer id) {
        ExamPaperEditRequestVM vm = examPaperService.examPaperToVM(id);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        ExamPaper examPaper = examPaperService.selectById(id);
        examPaper.setDeleted(true);
        examPaperService.updateByIdFilter(examPaper);
        return RestResponse.ok();
    }
    @RequestMapping(value = "/read/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperReadVM> read(@PathVariable Integer id) {
        ExamPaperAnswer examPaperAnswer = examPaperAnswerService.selectById(id);
        ExamPaperReadVM vm = new ExamPaperReadVM();
        ExamPaperEditRequestVM paper = examPaperService.examPaperToVM(examPaperAnswer.getExamPaperId());
        ExamPaperSubmitVM answer = examPaperAnswerService.examPaperAnswerToVM(examPaperAnswer.getId());
        vm.setPaper(paper);
        vm.setAnswer(answer);
        return RestResponse.ok(vm);
    }
    @RequestMapping(value = "/editPaper", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid ExamPaperSubmitVM examPaperSubmitVM) {
        boolean notJudge = examPaperSubmitVM.getAnswerItems().stream().anyMatch(i -> i.getDoRight() == null && i.getScore() == null);
        if (notJudge) {
            return RestResponse.fail(2, "有未批改题目");
        }

        ExamPaperAnswer examPaperAnswer = examPaperAnswerService.selectById(examPaperSubmitVM.getId());
        ExamPaperAnswerStatusEnum examPaperAnswerStatusEnum = ExamPaperAnswerStatusEnum.fromCode(examPaperAnswer.getStatus());
        if (examPaperAnswerStatusEnum == ExamPaperAnswerStatusEnum.Complete) {
            return RestResponse.fail(3, "试卷已完成");
        }
        String score = examPaperAnswerService.judge(examPaperSubmitVM);
        User user = getCurrentUser();
        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
        String content = user.getUserName() + " 批改试卷：" + examPaperAnswer.getPaperName() + " 得分：" + score;

        messageService.sendMessageForUser(examPaperAnswer);

        userEventLog.setContent(content);
        eventPublisher.publishEvent(new UserEvent(userEventLog));
        return RestResponse.ok(score);
    }
}
