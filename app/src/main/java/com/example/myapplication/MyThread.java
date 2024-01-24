package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class MyThread extends Thread {

    private Paint paint;
    private SurfaceHolder holder;
    private boolean flag;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public long getTime() {
        return System.nanoTime()/1000;
    }

    MyThread(SurfaceHolder holder) {
        this.holder = holder;
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL); // тип заливки
        paint.setAntiAlias(true); // сглаживание
    }

    private long redrawTime = 0;
    @Override
    public void run() {
        Canvas canvas;

        while(flag) {
            long currentTime = getTime();
            long elapsedTime = currentTime - redrawTime;
            if(elapsedTime < 1000000) {
                continue;
            }
            // блокировка Canvas для отрисовки
            canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK); // Фон
            canvas.drawCircle(canvas.getWidth()/2,
                                canvas.getHeight()/2,
                    (float)(300*Math.random()),paint);
            // разблокируем и показываем
            holder.unlockCanvasAndPost(canvas);
            redrawTime = getTime();
        }
    }
}
