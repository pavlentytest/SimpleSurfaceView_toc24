package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Random;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {


    private MyThread myThread;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
       // Random random = new Random();
     //   int red = random.nextInt(255);
      //  int green = random.nextInt(255);
      //  int blue = random.
        //Color.RED
       // int[] colors = {Color.RED, Color.BLUE, Color.GREEN};

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        myThread = new MyThread(getHolder());
        myThread.setFlag(true);
        myThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        // завершение потока
        boolean retry = true;
        myThread.setFlag(false);
        while(retry) {
            try {
                myThread.join();
                retry = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
