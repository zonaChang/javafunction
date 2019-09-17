package com.carl.study.function.qrcode;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author changez
 * @desc
 * @datetime 2019/9/11 10:55
 */
@Slf4j
public class QRCodeUtils {

    /**
     * 二维码默认图片宽
     */
    public static final int QRCODE_WIDTH = 350;

    /**
     * 二维码默认图片高
     */
    public static final int QRCODE_HEIGHT = 350;

    public static final String SUCCESS_KEY = "success";
    public static final String BASE64_KEY = "base64";
    public static final String BASE64_IMAGE_KEY = "base64Image";

    public static void generateQRCodeImage(String text, String filePath) {
        generateQRCodeImage(text, QRCODE_WIDTH, QRCODE_HEIGHT, filePath);
    }

    /**
     * 生成二维码图片, 存到指定目录
     * @param text 二维码内容
     * @param width 宽
     * @param height 高
     * @param filePath 文件存放地址
     */
    public static void generateQRCodeImage(String text, int width, int height, String filePath) {

        width = width <= 0 ? QRCODE_WIDTH : width;
        height = height <= 0 ? QRCODE_HEIGHT : height;
        log.info("生成二维码内容={}, width={}, height={},filePath={}", text, width, height, filePath);
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (Exception e) {
            log.error(String.format("二维码生成失败, errMsg=%s", e.getMessage()), e);
        }
    }


    public static Map<String, Object> generateQRCodeImageBase64(String text) {

        return generateQRCodeImageBase64(text, QRCODE_WIDTH, QRCODE_HEIGHT);
    }

    /**
     * 获取二维码base64字符
     * @param text 二维码内容
     * @param width 二维码宽
     * @param height 二维码高
     * @return
     * success: 1: 调用成功; 0:调用失败
     * base64: 图片的base64编码字符串
     * base64Image: 前端可直接显示的图片base64编码字符串
     *
     */
    public static Map<String, Object> generateQRCodeImageBase64(String text, int width, int height) {

        width = width <= 0 ? QRCODE_WIDTH : width;
        height = height <= 0 ? QRCODE_HEIGHT : height;

        log.info("获取图片base64编码, 图片内容={}, width={}, height={}", text, width, height);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put(SUCCESS_KEY, "1");
        retMap.put(BASE64_KEY, null);
        retMap.put(BASE64_IMAGE_KEY, null);
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();
            String base64 = Base64.encodeBase64String(pngData);
            retMap.put(BASE64_KEY, base64);
            retMap.put(BASE64_IMAGE_KEY, "data:image/png;base64,"+base64);
        } catch (Exception e) {
            log.error(String.format("二维码生成失败, errMsg=%s", e.getMessage()), e);
            retMap.put(BASE64_KEY, "0");
        }
        return retMap;
    }
}
