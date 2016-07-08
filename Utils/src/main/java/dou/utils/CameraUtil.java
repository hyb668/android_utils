package dou.utils;

import android.hardware.Camera;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mac on 16/7/7.
 */
public class CameraUtil {


    /**
     * path :String.format("/sdcard/img/%d.jpg", System.currentTimeMillis())
     *
     * @param yuv_data
     * @param camera
     * @param save_path
     */
    public static void saveFromPreview(byte[] yuv_data, Camera camera, String save_path) {
        FileOutputStream outStream = null;
        try {
            YuvImage yuvimage = new YuvImage(yuv_data, ImageFormat.NV21,
                    camera.getParameters().getPreviewSize().width, camera.getParameters().getPreviewSize().height, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            yuvimage.compressToJpeg(new Rect(0, 0, camera.getParameters().getPreviewSize().width, camera.getParameters().getPreviewSize().height), 80, baos);

            outStream = new FileOutputStream(save_path);
            outStream.write(baos.toByteArray());
            outStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
