package com.studio.roy.pololuandroid.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.studio.roy.pololuandroid.R;


public class ControlFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    Button avancer;
    Button droite;
    Button gauche;
    Button stop;
    Button control;
    Button reculer;
    Button photo;
    boolean controlRobot = false;

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
        View view = inflater.inflate(R.layout.fragment_control, container, false);

        avancer = (Button)view.findViewById(R.id.buttonAvancer);
        reculer = (Button)view.findViewById(R.id.buttonReculer);
        photo = (Button)view.findViewById(R.id.buttonPhoto);
        droite = (Button)view.findViewById(R.id.buttonDroite);
        gauche = (Button)view.findViewById(R.id.buttonGauche);
        stop = (Button)view.findViewById(R.id.buttonStop);
        control = (Button)view.findViewById(R.id.buttonControl);

        database = FirebaseDatabase.getInstance();

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
                        myRef.child("Avance").setValue(1);
                        break;
                    case MotionEvent.ACTION_UP:
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
