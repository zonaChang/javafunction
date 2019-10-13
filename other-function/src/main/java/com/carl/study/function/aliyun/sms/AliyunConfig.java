package com.carl.study.function.aliyun.sms;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author changez
 * @desc
 * @datetime 2019/10/13 22:49
 */
@Data
@Component
public class AliyunConfig {

    // 短信签名
    private String smsSignName="carlSign";

    // 短信内容模板code
    private String smsContentTemplateCode="SMS_xxx475049";

    // RAM accessKeyId
    private String accessKeyId="LTFhTuEKhLM4v7L4";

    // RAM accessSecret
    private String accessSecret="fVHGSv19xRe38H0CWHnrg91RMA";


}
