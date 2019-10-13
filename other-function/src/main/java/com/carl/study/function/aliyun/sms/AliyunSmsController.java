package com.carl.study.function.aliyun.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changez
 * @desc 阿里云短信发送功能
 * @datetime 2019/10/13 22:50
 */
@Slf4j
@RequestMapping("/util/aliyun/sms")
@RestController
public class AliyunSmsController {

    @Autowired
    private AliyunConfig aliyunConfig;

    /**
     *
     * @param phone 手机号
     * @param verfiyCode 验证码
     * @return
     */
    @GetMapping("/send/verify-code")
    public Object sendVerifyCodeSms(String phone, String verfiyCode) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyunConfig.getAccessKeyId(), aliyunConfig.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        Map<String, Object> retMap = new HashMap<>();
        retMap.put("success", 1); // 1:成功, 0:失败
        retMap.put("phone", phone);
        retMap.put("msg", "调用成功");
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        try {
            // 短信接收手机号
            request.putQueryParameter("PhoneNumbers", phone);

            // 短信签名
            request.putQueryParameter("SignName", aliyunConfig.getSmsSignName());

            // 短信模板code
            request.putQueryParameter("TemplateCode", aliyunConfig.getSmsContentTemplateCode());

            // 短信模板变量值 {"code":"1111"}, 验证码暂仅支持code变量
            request.putQueryParameter("TemplateParam", "{\"code\":\"codeValue\"}".replace("codeValue", verfiyCode));
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            log.error("短信发送失败, phone={}, verifyCode={}, errCode={}, errMsg={}, requestId={}, errorType={}",
                    phone, verfiyCode, e.getErrCode(), e.getErrMsg(), e.getRequestId(), e.getErrorType());
            retMap.put("success", 0);
            retMap.put("msg", e.getErrMsg());
        }
        return retMap;
    }
}
