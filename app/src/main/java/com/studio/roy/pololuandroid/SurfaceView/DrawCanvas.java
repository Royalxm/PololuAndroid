package com.studio.roy.pololuandroid.SurfaceView;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

import com.studio.roy.pololuandroid.Activity.MainActivity;
import com.studio.roy.pololuandroid.Fragment.DrawFragment;
import com.studio.roy.pololuandroid.R;

import java.util.ArrayList;
import java.util.List;

public class DrawCanvas extends SurfaceView implements Callback {
    private CanvasThread canvasThread;
    Context mcontext;

    public Activity activity;
    public DrawCanvas(Context context) {
        super(context);
        mcontext = context;
        // TODO Auto-generated constructor stub
    }
    class Pt{

        float x, y;



        Pt(float _x, float _y){

            x = _x;

            y = _y;

        }

    }



    Pt[] myPath = { new Pt(100, 100)};

    List<Pt> mylistPath =   new ArrayList<Pt>();

    public DrawCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mcontext = context;
        this.getHolder().addCallback(this);
        this.canvasThread = new CanvasThread(getHolder());
        this.setFocusable(true);

    }

    public DrawCanvas(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub

    }

    public void startDrawImage(Activity act) {
        canvasThread.setRunning(true);
        canvasThread.start();
        this.activity = act;

        mylistPath.add(new Pt(100, 100));
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        boolean retry = true;
        canvasThread.setRunning(false);
        while(retry) {
            try {
                canvasThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    String route = "1111111111";
    int x = 1;
    int y = 1;
    int multi = 4;
    int swit = 0;
    int valuechar = 0;
    int i = 0;
    int positionvalue = 0;
    float[] pts = new float[10000];

    String firstLetter;

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub

        super.onDraw(canvas);




        Paint paint = new Paint();

        paint.setColor(Color.WHITE);

        paint.setStrokeWidth(3);

        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();



        path.moveTo(mylistPath.get(0).x, mylistPath.get(0).y);

        if(((value) activity.getApplication()).getValue() == 1)
        {

            mylistPath.add(new Pt(100, 100));
//            myPath[myPath.length +1] = new Pt(myPath[myPath.length].x + 10, myPath[myPath.length].y);

        }
        for (int i = 1; i < mylistPath.size(); i++){

            path.lineTo(myPath[i].x, myPath[i].y);

        }

        canvas.drawPath(path, paint);
    }


    private class CanvasThread extends Thread {
        private SurfaceHolder surfaceHolder;
        private boolean isRun = false;

        public CanvasThread(SurfaceHolder holder) {
            this.surfaceHolder = holder;
        }

        public void setRunning(boolean run) {
            this.isRun = run;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            Canvas c;

            while(isRun) {
                c = null;
                try {
                    c = this.surfaceHolder.lockCanvas(null);
                    if(c != null) {
                        synchronized (this.surfaceHolder) {
                            DrawCanvas.this.onDraw(c);


                        }
                    }
                } finally {
                    if(c != null){
                        surfaceHolder.unlockCanvasAndPost(c);
                    }

                }
            }
        }
    }
}
