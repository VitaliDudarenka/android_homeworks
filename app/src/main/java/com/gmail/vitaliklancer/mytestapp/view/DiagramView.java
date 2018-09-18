package com.gmail.vitaliklancer.mytestapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DiagramView extends View {
    private Paint paint;
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private List<Paint> paintList = new ArrayList<>();
    private RectF rectF;
    private int arr[] = {12, 48, 15, 67};
    private Map<Integer, Float> grads = new HashMap<>();
    private Random random = new Random();

    public DiagramView(Context context) {
        super(context);
        init();
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setTextSize(60);
        paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.GREEN);
        paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(Color.BLUE);
        paint3 = new Paint();
        paint3.setColor(Color.YELLOW);
        paintList.add(paint1);
        paintList.add(paint2);
        paintList.add(paint3);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF = new RectF();
        rectF.left = getWidth() * 0.05f;
        rectF.top = getHeight() * 0.05f;
        rectF.right = getWidth() - rectF.left;
        rectF.bottom = getHeight() - rectF.top;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        gradCalc();
        float arc = 0;
        Iterator<Map.Entry<Integer, Float>> entries = grads.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Float> entry = entries.next();
            canvas.drawArc(rectF, arc, entry.getValue(), true, paintList.get(random.nextInt(3)));
            canvas.drawText(String.valueOf(entry.getKey()), (float) (getWidth() / 2 + (getWidth() / 2) * 0.8f * Math.cos(Math.toRadians(arc + 10))),
                    (float) (getWidth() / 2 + (getWidth() / 2) * 0.8f * Math.sin(Math.toRadians(arc + 10))), paint);
            arc += entry.getValue();
            /*Log.d("value", String.valueOf(entry.getValue()));
            Log.d("key", String.valueOf(entry.getKey()));*/
        }

    }

    public void gradCalc() {
        float summ = 0;
        for (int i = 0; i < arr.length; i++) {
            summ += arr[i];
        }
        float rate = 360 / summ;
        for (int i = 0; i < arr.length; i++) {
            grads.put(arr[i], (float) arr[i] * rate);
        }
    }
}
