package readsense.viewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import dou.utils.DLog;

/**
 * Created by mac on 16/9/21.
 */

public class FontView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public FontView(Context context) {
        this(context, null);
    }

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        DLog.d(w + ":" + h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        path.setLastPoint(-300, 300);
        canvas.drawPath(path, mPaint);
    }
}
