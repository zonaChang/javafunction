package com.carl.study.function.qrcode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * @author changez
 * @desc
 * @datetime 2019/9/17 21:56
 */
@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @GetMapping("/generate/image")
    public Object generateQrCodeImage(){
        String text = "我是内容信息xxxxafaf";
        Map<String, Object> qrcodeMap = QRCodeUtils.generateQRCodeImageBase64(text);
        QRCodeUtils.generateQRCodeImage(text, String.format("D:\\images\\video2image\\aa-%s.png", UUID.randomUUID().toString()));
        return qrcodeMap;
    }
}
