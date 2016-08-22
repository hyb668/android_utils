package dou.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by mac on 16/8/15.
 */
public class TextUtil {
    public static int getFontSize(Context mContext, int textSize, int Swidth) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int rate = (int) ((int) (textSize * (float) Swidth / 640) / dm.density);
        return rate;
    }
}
