package com.bawei.pointdome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.security.spec.ECPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author:程金柱
 * Date:2019/7/23 12:00
 * Description：
 */

public class QiuView extends View {

    private Paint paintRed;
    private Paint paintGreen;
    private Paint paintGreenHead;
    private Bitmap bitmap;
    private int height;
    private int width;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public QiuView(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public QiuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public QiuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPrint(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        canvas.drawBitmap(bitmap, 150, height - 150, paintRed);
        canvas.drawBitmap(bitmap, width - 150 - bitmap.getWidth(), height - 150, paintRed);
        RectF rectF = new RectF();
        rectF.bottom = buttom;
        rectF.left = left;
        rectF.right = right;
        rectF.top = top;
        canvas.drawRect(rectF, paintGreen);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).x > left && list.get(i).x < right && list.get(i).y > top && list.get(i).y < buttom) {
                    System.out.println(qingkog);
                    if (qingkog) {
                        list.remove(i);
                        invalidate();
                    } else {
                        canvas.drawCircle(list.get(i).x, list.get(i).y, 10, paintGreenHead);
                    }
                } else {
                    canvas.drawCircle(list.get(i).x, list.get(i).y, 10, paintRed);
                }
            }

        }
        qingkog = false;
    }


    private int left = 0, right = 0, top = 0, buttom = 0;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                left = (int) event.getX();
                top = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                right = (int) event.getX();
                buttom = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (event.getX() > 150 && event.getX() < 150 + bitmap.getWidth()
                        && event.getY() > height - 150 && event.getY() < height - (150 - bitmap.getHeight())) {
                    initDot();
                    System.out.println("x:");
                }
                if (event.getX() > width - 150 - bitmap.getWidth() && event.getX() < width - 150 && event.getY() > height - 150 && event.getY() < height - (150 - bitmap.getHeight())) {
                    qingkog = true;
                    System.out.println(qingkog + "up---------------");
                    invalidate();
                }
                break;
        }
        return true;
    }

    private boolean qingkog;
    private List<PointF> list = new ArrayList<>();

    private void initDot() {
        Random random = new Random();
        float pointX = random.nextInt(width);
        Random random2 = new Random();
        float pointY = random2.nextInt(height);
        System.out.println("x:" + pointX + "   y:" + pointY);
        list.add(new PointF(pointX, pointY));
        invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initPrint(Context context, AttributeSet attrs) {
        paintRed = new Paint();
        paintRed.setAntiAlias(true);
        paintRed.setDither(true);
        paintRed.setStyle(Paint.Style.FILL);
        paintRed.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

        paintGreen = new Paint();
        paintGreen.setStyle(Paint.Style.STROKE);
        paintGreen.setDither(true);
        paintGreen.setStrokeWidth(5);
        paintGreen.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        paintGreen.setAntiAlias(true);
        paintGreenHead = new Paint();
        paintGreenHead.setColor(ContextCompat.getColor(getContext(), R.color.colorGreenHead));
        paintGreenHead.setStyle(Paint.Style.FILL);
        paintGreenHead.setDither(true);
        paintGreenHead.setAntiAlias(true);
    }
}
