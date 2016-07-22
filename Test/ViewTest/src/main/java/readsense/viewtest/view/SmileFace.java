package readsense.viewtest.view;

import android.graphics.Paint;

/**
 * Created by mac on 16/7/20.
 */
public class SmileFace {
    private int mFaceColor = 0xffffffff;
    private int mEyeAndMouthColor = 0xffcccccc;
    private Paint mPaint;
    private float mFaceRadius;
    private float mCenterX;
    private float mCenterY;

    public SmileFace(float x, float y, float radius) {
        mCenterX = x;
        mCenterY = y;
        mFaceRadius = radius;
        mPaint = new Paint();
    }

    public void setFaceColor(int color) {
        mFaceColor = color;
    }

    public void setEyeAndMouthColor(int color) {
        mEyeAndMouthColor = color;
    }

}
