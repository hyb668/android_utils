package dou.utils;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mac on 16/7/7.
 */
public class CameraUtil {


    public static void saveFromPreview(byte[] yuv_data, String save_path, int iw, int ih) {
        FileOutputStream outStream = null;
        try {
            YuvImage yuvimage = new YuvImage(yuv_data, ImageFormat.NV21, iw, ih, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            yuvimage.compressToJpeg(new Rect(0, 0, iw, ih), 100, baos);

            outStream = new FileOutputStream(save_path);
            outStream.write(baos.toByteArray());
            outStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                    outStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
