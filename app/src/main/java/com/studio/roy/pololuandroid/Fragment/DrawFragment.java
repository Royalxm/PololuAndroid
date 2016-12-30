package com.studio.roy.pololuandroid.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.studio.roy.pololuandroid.R;
import com.studio.roy.pololuandroid.SurfaceView.DrawCanvas;


public class DrawFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    DrawCanvas mycanvas;
    public DrawFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    SurfaceView surfaceView;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_draw, container, false);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Draw");
        DrawCanvas mycanvas = (DrawCanvas) view.findViewById(R.id.DrawingImageView);
        mycanvas.setOnClickListener(clickListener);
        String value = "111111123";
    //    canvas.drawPoint();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        private boolean isDrawn = false;
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (!isDrawn) {
                 mycanvas = (DrawCanvas) view.findViewById(R.id.DrawingImageView);
                mycanvas.startDrawImage(getActivity());

                isDrawn = true;
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
       // mycanvas.surfaceDestroyed();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
