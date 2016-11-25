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
    Button reculer;
    Button photo;

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

        database = FirebaseDatabase.getInstance();

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
