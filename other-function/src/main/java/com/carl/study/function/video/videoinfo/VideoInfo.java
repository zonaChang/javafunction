package com.carl.study.function.video.videoinfo;


import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;

import java.io.File;

/**
 * @author changez
 * @desc
 * @datetime 2019/10/9 14:54
 */
public class VideoInfo {

    // http://jiaohuitong-test.oss-cn-shanghai.aliyuncs.com/2019/10/08/10db5a31-20cd-41f1-ab82-aea87cb02415.mp4
    // D:\images\video\wjzn.mp4
    private static String videoFileName = "D:\\images\\video\\wjzn.mp4";
    public static void main(String[] args) throws FrameGrabber.Exception {
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoFileName);
        fFmpegFrameGrabber.start();
        int ftp = fFmpegFrameGrabber.getLengthInFrames();

        System.out.println("ftp="+ftp);

        // 视频长度, 单位:s getFrameRate内部调用了getVideoFrameRate
        System.out.println("时长(s)=" + ftp / fFmpegFrameGrabber.getVideoFrameRate());

        // 帧速率, 单位:帧/秒
        System.out.println("帧速率(帧/秒)="+fFmpegFrameGrabber.getVideoFrameRate());

        // 音频采样频率, 单位:Hz
        System.out.println("音频采样频率(Hz)="+fFmpegFrameGrabber.getSampleRate());


    }
}
