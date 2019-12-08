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

import java.util.ArrayList;
import java.util.Random;

import static com.unknown.catworld.MainActivity.arrListCats;

public class RoomView extends SurfaceView implements SurfaceHolder.Callback {

    private Context context;

    private int width, height, catSize, catHalf, rndX, rndY;
    private int repeat = 1;
    private DrawCatThread drawCatThread;
    private SurfaceHolder surfaceHolder;
    private ArrayList<Cats> arrListRoomCats = new ArrayList<>();
    private ArrayList<int[]> arrListRnd = new ArrayList<>();
    private boolean isRunning = false, isWaiting = false;

    public RoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        setZOrderOnTop(true);

        drawCatThread = new DrawCatThread();
    }

    public void resume() {
        Log.e("RoomView", "resume");

    }

    public void pause() {
        Log.e("RoomView", "pause");

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e("RoomView", "surfaceCreated");
        getSize();

        arrListRoomCats.clear();
        for (int i = 0; i < arrListCats.size(); i++) {
            Cats cats = arrListCats.get(i);
            if (cats.isUnlocked && cats.isAt.equals("room")) {
                arrListRoomCats.add(cats);
            }
        }

        Random random = new Random();
        for (int i = 0; i < arrListRoomCats.size(); i++) {

            rndX = random.nextInt(width - catSize);
            rndY = random.nextInt(height - catSize);
            arrListRnd.add(new int[]{rndX, rndY});

            Log.e("RoomView", arrListRnd.get(i)[0] + "    " + arrListRnd.get(i)[1]);

        }


        Log.e("RoomView", drawCatThread.getState() + "");
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
        Log.e("RoomView", "surfaceDestroyed");

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
        Log.e("RoomView", "getSize()");
        width = getWidth();
        height = getHeight();
        catSize = width / 8;
        catHalf = catSize / 2;

        Log.e("RoomView", width + "");
    }


    class DrawCatThread extends Thread {

        boolean isRunning;

        public void setRunning(boolean running) {
            isRunning = running;
        }

        @Override
        public void run() {
            super.run();

            Log.e("RoomView", "DrawCatThread");
            Log.e("RoomView", isRunning + "");
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
                    Log.e("RoomView", "unlockCanvasAndPost");
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

        for (int i = 0; i < arrListRoomCats.size(); i++) {

            Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), arrListRoomCats.get(i).idle[repeat]), catSize, catSize, true);
            canvas.drawBitmap(bitmap, arrListRnd.get(i)[0], arrListRnd.get(i)[1], null);
        }
    }

}
