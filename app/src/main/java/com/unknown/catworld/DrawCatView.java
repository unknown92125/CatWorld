package com.unknown.catworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Random;

import static com.unknown.catworld.HomeActivity.pageAt;
import static com.unknown.catworld.MainActivity.arrListCats;

public class DrawCatView extends SurfaceView implements SurfaceHolder.Callback {

    private Context context;

    private int width, height, catSize, catHalf, rndX, rndY;
    private int repeat = 1;
    private DrawCatThread drawCatThread;
    private SurfaceHolder surfaceHolder;
    private ArrayList<Cats> arrListRoomCats = new ArrayList<>();
    private ArrayList<Cats> arrListForestCats = new ArrayList<>();
    private ArrayList<int[]> arrListRnd = new ArrayList<>();
    private boolean isRunning = false, isWaiting = false;

    public DrawCatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        setZOrderOnTop(true);

        drawCatThread = new DrawCatThread();
    }

    public void resume() {
        Log.e("DrawCatView", "resume");

    }

    public void pause() {
        Log.e("DrawCatView", "pause");

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e("DrawCatView", "surfaceCreated");
        getSize();

        arrListRoomCats.clear();
        for (int i = 0; i < arrListCats.size(); i++) {
            Cats cats = arrListCats.get(i);
            if (cats.isUnlocked && cats.isAt.equals("room")) {
                arrListRoomCats.add(cats);
            }
        }
        arrListForestCats.clear();
        for (int i = 0; i < arrListCats.size(); i++) {
            Cats cats = arrListCats.get(i);
            if (cats.isUnlocked && cats.isAt.equals("forest")) {
                arrListForestCats.add(cats);
            }
        }

        Random random = new Random();
        for (int i = 0; i < arrListCats.size(); i++) {

            rndX = random.nextInt(width - catSize);
            rndY = random.nextInt(height - catSize);
            arrListRnd.add(new int[]{rndX, rndY});

            Log.e("DrawCatView", arrListRnd.get(i)[0] + "    " + arrListRnd.get(i)[1]);

        }


        Log.e("DrawCatView", drawCatThread.getState() + "");
        if (drawCatThread.getState() == Thread.State.NEW) {
            drawCatThread.setRunning(true);
            drawCatThread.start();
        } else {
            drawCatThread = new DrawCatThread();
            drawCatThread.setRunning(true);
            drawCatThread.start();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("DrawCatView", "surfaceDestroyed");

        drawCatThread.setRunning(false);
        boolean retry = true;
        while (retry) {
            try {
                drawCatThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void getSize() {
        Log.e("DrawCatView", "getSize()");
        width = getWidth();
        height = getHeight();
        catSize = width / 8;
        catHalf = catSize / 2;

        Log.e("DrawCatView", width + "");
    }


    class DrawCatThread extends Thread {

        boolean isRunning;

        public void setRunning(boolean running) {
            isRunning = running;
        }

        @Override
        public void run() {
            super.run();

            Log.e("DrawCatView", "DrawCatThread");
            Log.e("DrawCatView", isRunning + "");
            while (isRunning) {

                if (repeat == 1) {
                    repeat = 0;
                } else {
                    repeat = 1;
                }

                Canvas canvas = surfaceHolder.lockCanvas();
                if (canvas != null) {
                    drawCats(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    Log.e("DrawCatView", "unlockCanvasAndPost");
                }

                //sleep 500
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void drawCats(Canvas canvas) {

        if (pageAt.equals("room")) {
            for (int i = 0; i < arrListRoomCats.size(); i++) {
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), arrListRoomCats.get(i).idle[repeat]), catSize, catSize, true);
                canvas.drawBitmap(bitmap, arrListRnd.get(i)[0], arrListRnd.get(i)[1], null);
            }
        }else if (pageAt.equals("forest")){
            for (int i = 0; i < arrListForestCats.size(); i++) {
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), arrListForestCats.get(i).dig[repeat]), catSize, catSize, true);
                canvas.drawBitmap(bitmap, arrListRnd.get(i)[0], arrListRnd.get(i)[1], null);
            }
        }
    }

}
