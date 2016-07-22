package dou.util.image;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mac on 16/7/20.
 */
public class DownLoad {

    public static final int TIMEOUT = 5000;

    private static void downLoad(String image_url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(image_url).openConnection();
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > -1) {
                    baos.write(buffer, 0, length);
                }
                baos.flush();
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downLoad1(String image_url, String file_path) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(image_url).openConnection();

            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                File file = new File(file_path);
                if (file.exists()) file.delete();
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                //直接写入文件中，每10kb写入文件一次，比较频繁
//                byte[] buffer = new byte[10240];
//                int length;
//                while ((length = is.read(buffer)) > -1) {
//                    fos.write(buffer, 0, length);
//                }
//                fos.flush();
//                fos.close();
//                is.close();
                //先写入内存，写入内存5M时开始写入文件，避免多次读写文件
                BufferedOutputStream bos = new BufferedOutputStream(fos, 5 * 1024 * 1024);
                byte[] buffer = new byte[10240];
                int length;
                while ((length = is.read(buffer)) > -1) {
                    bos.write(buffer, 0, length);
                }
                bos.flush();
                bos.close();
                fos.close();
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int caclulateScaleSize(int originalWidth, int originalHeight
            , int destWidth, int destHeight) {
        int scaleWidth = (int) Math.ceil((float) originalWidth / destWidth);
        int scaleHeight = (int) Math.ceil((float) originalHeight / destHeight);
        return Math.max(scaleWidth, scaleHeight);
    }


}
