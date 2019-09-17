package com.carl.study.function.video.video2image;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

/**
 * @author changez
 * @desc 从视频中提取图片
 * @datetime 2019/9/17 21:14
 */

@RestController
@RequestMapping("/video/extract/video2image")
public class ExtractImageFromVideoController {

    @Value("${data.video.path}")
    private String videoPath;

    @Value("${data.video.img.Save.path}")
    private String imgSavePath;
    //存放截取视频某一帧的图片
//    public static String videoFramesPath = "D:/images/video2image/";
//    public static String videoFrames5Path = "D:/images/";

    /**
     * 将视频文件帧处理并以“jpg”格式进行存储。
     * 依赖FrameToBufferedImage方法：将frame转换为bufferedImage对象
     *
     * @param videoFileName
     */
    public String grabberVideoFramer(String videoFileName) {
        //最后获取到的视频的图片的路径
        String videPicture = "";
        //Frame对象
        Frame frame = null;
        //标识
        int flag = 0;
        try {
			 /*
            获取视频文件
            */
            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoFileName);
            fFmpegFrameGrabber.start();

            //获取视频总帧数
            int ftp = fFmpegFrameGrabber.getLengthInFrames();
            System.out.println("时长 " + ftp / fFmpegFrameGrabber.getFrameRate() / 60);

            while (flag <= ftp) {
                frame = fFmpegFrameGrabber.grabImage();
				/*
				对视频的第五帧进行处理
				 */
                if (frame != null && flag == 5) {
                    //文件绝对路径+名字
                    String fileName = imgSavePath + UUID.randomUUID().toString() + "_" + flag + ".jpg";

                    //文件储存对象
                    File outPut = new File(fileName);
                    ImageIO.write(FrameToBufferedImage(frame), "jpg", outPut);
                    videPicture = fileName;

                    //视频第五帧图的路径
//                    String savedUrl = videoFrames5Path + outPut.getName();
//                    videPicture = savedUrl;
                    break;
                }
                flag++;
            }
            fFmpegFrameGrabber.stop();
            fFmpegFrameGrabber.close();
        } catch (Exception E) {
            E.printStackTrace();
        }
        return videPicture;
    }

    public static BufferedImage FrameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }


    /**
     * 测试：
     * 1、在D盘中新建一个test文件夹，test中再分成video和img，在video下存入一个视频，并命名为test
     * D:/test/video     D:/test/img
     */
    @GetMapping("/generate")
    public void main() {
//            String videoFileName = "D:\\images\\57bd88d187055b018f267568ea674dc6.mp4";
        String videoFileName = videoPath; //"D:\\images\\xxabcd.mp4";
        String url = grabberVideoFramer(videoFileName);
        System.out.println("url=" + url);
    }


}
