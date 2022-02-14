package com.alvis.exam.utility;


import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;

public class WxUtil {
    private static final Logger logger = LoggerFactory.getLogger(WxUtil.class);
    private static final String openIdUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    public static String getOpenId(String appId, String secret, String code) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            String requestUrl = String.format(openIdUrl, appId, secret, code);
            HttpGet httpGet = new HttpGet(requestUrl);
            HttpEntity responseEntity = httpClient.execute(httpGet).getEntity();
            if (responseEntity != null) {
                String responseStr = EntityUtils.toString(responseEntity);
                if (responseStr.contains("openid")) {
                    WxResponse wxResponse = JsonUtil.toJsonObject(responseStr, WxResponse.class);
                    return wxResponse.getOpenid();
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static WxResponse getWxResponse(String appId, String secret, String code) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            String requestUrl = String.format(openIdUrl, appId, secret, code);
            HttpGet httpGet = new HttpGet(requestUrl);
            HttpEntity responseEntity = httpClient.execute(httpGet).getEntity();
            if (responseEntity != null) {
                String responseStr = EntityUtils.toString(responseEntity);
                WxResponse wxResponse = JsonUtil.toJsonObject(responseStr, WxResponse.class);
                return wxResponse;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    /**
     * AES解密
     *
     * @param encryptedData 消息密文
     * @param ivStr         iv字符串
     */
    public static String decrypt(String sessionKey, String encryptedData, String ivStr) {
        try {

            AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
            params.init(new IvParameterSpec(Base64.decodeBase64(ivStr)));

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(sessionKey), "AES"), params);

            return new String(PKCS7Encoder.decode(cipher.doFinal(Base64.decodeBase64(encryptedData))), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("AES解密失败", e);
        }
    }
}
