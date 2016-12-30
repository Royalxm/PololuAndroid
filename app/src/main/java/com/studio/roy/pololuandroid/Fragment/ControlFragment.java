package com.studio.roy.pololuandroid.Fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.studio.roy.pololuandroid.R;
import com.studio.roy.pololuandroid.SurfaceView.DrawCanvas;
import com.studio.roy.pololuandroid.SurfaceView.value;


public class ControlFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    Button avancer;
    Button droite;
    Button gauche;
    Button stop;
    Button control;
    Button reculer;
    DrawCanvas mycanvas;
    Button photo;
    boolean controlRobot = false;
    View view;
    FirebaseDatabase database;
    public ControlFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_control, container, false);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Control");
        avancer = (Button)view.findViewById(R.id.buttonAvancer);
        reculer = (Button)view.findViewById(R.id.buttonReculer);
        photo = (Button)view.findViewById(R.id.buttonPhoto);
        droite = (Button)view.findViewById(R.id.buttonDroite);
        gauche = (Button)view.findViewById(R.id.buttonGauche);
        stop = (Button)view.findViewById(R.id.buttonStop);
        control = (Button)view.findViewById(R.id.buttonControl);

        database = FirebaseDatabase.getInstance();
        DrawCanvas mycanvas = (DrawCanvas) view.findViewById(R.id.videoView);
        mycanvas.setOnClickListener(clickListener);
        ((value) getActivity().getApplication()).setSomeVariable("0");
        if(controlRobot){
            avancer.setEnabled(true);
            reculer.setEnabled(true);
            droite.setEnabled(true);
            gauche.setEnabled(true);
            stop.setEnabled(true);
            photo.setEnabled(true);
        }else{
            avancer.setEnabled(false);
            reculer.setEnabled(false);
            droite.setEnabled(false);
            gauche.setEnabled(false);
            stop.setEnabled(false);
            photo.setEnabled(false);

        }

        control.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DatabaseReference myRef = database.getReference("RobotControl");
                if(controlRobot){

                    myRef.child("NewPath").setValue(1);
                    myRef.child("Manuel").setValue(0);
                    controlRobot = false;
                    control.setText("Passer en manuel");
                }else {
                    myRef.child("Manuel").setValue(1);
                    myRef.child("NewPath").setValue(0);
                    controlRobot = true;
                    control.setText("Passer en automatique");
                }
                if(controlRobot){
                    avancer.setEnabled(true);
                    reculer.setEnabled(true);
                    droite.setEnabled(true);
                    gauche.setEnabled(true);
                    stop.setEnabled(true);
                    photo.setEnabled(true);
                }else{
                    avancer.setEnabled(false);
                    reculer.setEnabled(false);
                    droite.setEnabled(false);
                    gauche.setEnabled(false);
                    stop.setEnabled(false);
                    photo.setEnabled(false);

                }
            }

        });




        avancer.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DatabaseReference myRef = database.getReference("RobotControl");
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        ((value) getActivity().getApplication()).setValue(1);
                        myRef.child("Avance").setValue(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        ((value) getActivity().getApplication()).setValue(0);
                        myRef.child("Avance").setValue(0);
                        break;
                }
                return true;

            }
        });

        reculer.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DatabaseReference myRef = database.getReference("RobotControl");
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        myRef.child("Recule").setValue(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        myRef.child("Recule").setValue(0);
                        break;
                }
                return true;

            }
        });
        droite.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DatabaseReference myRef = database.getReference("RobotControl");
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        myRef.child("Droite").setValue(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        myRef.child("Droite").setValue(0);
                        break;
                }
                return true;

            }
        });
        gauche.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DatabaseReference myRef = database.getReference("RobotControl");
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        myRef.child("Gauche").setValue(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        myRef.child("Gauche").setValue(0);
                        break;
                }
                return true;

            }
        });
        stop.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DatabaseReference myRef = database.getReference("RobotControl");
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        myRef.child("Stop").setValue(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        myRef.child("Stop").setValue(0);
                        break;
                }
                return true;

            }
        });

        photo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DatabaseReference myRef = database.getReference("RobotControl");
                myRef.child("Photo").setValue(1);


            }
        });
        return view;
    }



    private View.OnClickListener clickListener = new View.OnClickListener() {
        private boolean isDrawn = false;
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (!isDrawn) {
                mycanvas = (DrawCanvas) view.findViewById(R.id.videoView);
                mycanvas.startDrawImage(getActivity());

                isDrawn = true;
            }
        }
    };


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class MyView extends View {



        class Pt{

            float x, y;



            Pt(float _x, float _y){

                x = _x;

                y = _y;

            }

        }



        Pt[] myPath = { new Pt(100, 100),

                new Pt(200, 200),

                new Pt(200, 500),

                new Pt(400, 500),

                new Pt(400, 200)

        };



        public MyView(Context context) {

            super(context);

            // TODO Auto-generated constructor stub

        }



        @Override

        protected void onDraw(Canvas canvas) {

            // TODO Auto-generated method stub

            super.onDraw(canvas);





            Paint paint = new Paint();

            paint.setColor(Color.WHITE);

            paint.setStrokeWidth(3);

            paint.setStyle(Paint.Style.STROKE);

            Path path = new Path();



            path.moveTo(myPath[0].x, myPath[0].y);

            for (int i = 1; i < myPath.length; i++){

                path.lineTo(myPath[i].x, myPath[i].y);

            }

            canvas.drawPath(path, paint);



        }



    }
}
