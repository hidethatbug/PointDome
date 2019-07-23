package com.bawei.pointdome;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author:程金柱
 * Date:2019/7/23 19:28
 * Description：
 */

public class YuanView extends View {

    private Paint paintRed;

    public YuanView(Context context) {
        super(context);
        initView(context);
    }


    public YuanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public YuanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, 100, paintRed);
    }

    private float x = 0;
    private float y = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x = 0;
                y = 0;
                break;

        }
        invalidate();
        return true;

    }

    private void initView(Context context) {
        paintRed = new Paint();
        paintRed.setAntiAlias(true);
        paintRed.setDither(true);
        paintRed.setStyle(Paint.Style.STROKE);
        paintRed.setStrokeWidth(10);
        paintRed.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
    }
}
