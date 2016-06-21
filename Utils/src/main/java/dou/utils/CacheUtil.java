package dou.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.File;

/**
 * Created by mac on 16/6/21.
 */
public class CacheUtil {

    @TargetApi(Build.VERSION_CODES.FROYO)
    public static void setCache(Context context, String key, String strCache) {
        String encodeName = EncodeUtil.encode(key);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            FileUtil.writeFile(context.getExternalCacheDir() + "/" + encodeName, strCache);
        }
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public static String getCache(Context context, String key) {
        String encodeName = EncodeUtil.encode(key);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String filename = context.getExternalCacheDir() + "/" + encodeName;
            File file = new File(filename);
            return file.exists() ? FileUtil.readFile(filename) : "";
        } else {
            return "";
        }
    }
}
