package com.gmail.vitaliklancer.mytestapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class ClockView extends View {
    private Paint circlePaint;
    private Paint rectPaint;
    private Paint arrowPaint;
    private Paint secArrowPaint;
    private Paint textPaint;
    private RectF rectF;


    public ClockView(Context context) {
        super(context);
        init();
        movePlayer0Runnable.run();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        movePlayer0Runnable.run();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        movePlayer0Runnable.run();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        movePlayer0Runnable.run();
    }

    public void init() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.WHITE);
        circlePaint.setAntiAlias(true);
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(10);
        arrowPaint = new Paint();
        arrowPaint.setColor(Color.BLUE);
        arrowPaint.setAntiAlias(true);
        arrowPaint.setStrokeWidth(10);
        secArrowPaint = new Paint();
        secArrowPaint.setColor(Color.BLUE);
        secArrowPaint.setAntiAlias(true);
        secArrowPaint.setStrokeWidth(5);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(70);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = Math.min(getWidth(), getHeight()) / 2;
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, circlePaint);

        float yEdge = getHeight() - radius;
        float xLine = getWidth() / 2;
        float yStartLine = yEdge * 0.85f + radius;
        float yEndLine = yEdge + radius;
        float step = 360 / 12;
        float k;
        canvas.save();
        for (int i = 0; i < 12; i++) {
            if (i == 0 || i == 3 || i == 6 || i == 9) {
                k = 1.05f;
            } else {
                k = 1f;
            }
            canvas.drawLine(xLine, yStartLine * k, xLine, yEndLine, rectPaint);
            canvas.rotate(step, getWidth() / 2, getHeight() / 2);
        }
        canvas.restore();
        int time = 3;
        int angle = 0;
        for (int i = 0; i < 4; i++) {
            canvas.drawText(String.valueOf(time), (float) (getWidth() / 2 * 0.95f + (getWidth() / 2) * 0.8f * Math.cos(Math.toRadians(angle))),
                    (float) (getWidth() / 2 * 1.05f + (getWidth() / 2) * 0.8f * Math.sin(Math.toRadians(angle))), textPaint);
            time += 3;
            angle += 90;
        }


        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        canvas.save();
        canvas.rotate(step * hour + step * minute / 60, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(xLine, getHeight() / 2, xLine, getHeight() / 2 - radius / 2, arrowPaint);
        canvas.restore();
        canvas.save();
        canvas.rotate((step * 12 / 60) * minute, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(xLine, getHeight() / 2, xLine, getHeight() / 2 - radius / 2 * 1.4f, arrowPaint);
        canvas.restore();
        canvas.save();
        canvas.rotate((step * 12 / 60) * second, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(xLine, getHeight() / 2, xLine, getHeight() / 2 - radius / 2 * 1.4f, secArrowPaint);
        canvas.restore();

    }

    Handler handler = new Handler(Looper.getMainLooper());
    Runnable movePlayer0Runnable = new Runnable() {
        public void run() {
            invalidate();
            handler.postDelayed(this, 1000);
        }
    };

}
