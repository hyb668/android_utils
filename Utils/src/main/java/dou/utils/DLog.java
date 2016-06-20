package dou.utils;

import android.util.Log;

/**
 * 日志
 * Created by mac on 16/6/14.
 */
public class DLog {

    public static boolean mSwitch = true;
    private static String DEFAULT_TAG = "DLog";
    private static String DEFAULT_MESSAGE = "DLog message";

    public static void v() {
        v(DEFAULT_MESSAGE);
    }

    public static void d() {
        d(DEFAULT_MESSAGE);
    }

    public static void i() {
        i(DEFAULT_MESSAGE);
    }

    public static void w() {
        w(DEFAULT_MESSAGE);
    }

    public static void e() {
        e(DEFAULT_MESSAGE);
    }

    public static void v(Object message) {
        v(DEFAULT_TAG, String.valueOf(message));
    }

    public static void d(Object message) {
        d(DEFAULT_TAG, String.valueOf(message));
    }

    public static void i(Object message) {
        i(DEFAULT_TAG, String.valueOf(message));
    }

    public static void w(Object message) {
        w(DEFAULT_TAG, String.valueOf(message));
    }

    public static void e(Object message) {
        e(DEFAULT_TAG, String.valueOf(message));
    }

    public static void v(String tag, Object message) {
        if (mSwitch) Log.v(tag, String.valueOf(message));
    }

    public static void d(String tag, Object message) {
        if (mSwitch) Log.d(tag, String.valueOf(message));
    }

    public static void i(String tag, Object message) {
        if (mSwitch) Log.d(tag, String.valueOf(message));
    }

    public static void w(String tag, Object message) {
        if (mSwitch) Log.w(tag, String.valueOf(message));
    }

    public static void e(String tag, Object message) {
        if (mSwitch) Log.e(tag, String.valueOf(message));
    }


}
