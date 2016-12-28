package com.studio.roy.pololuandroid.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.studio.roy.pololuandroid.R;


public class StreamingFragment extends Fragment {

    EditText addrField;
    Button btnConnect;
    VideoView streamView;
    MediaController mediaController;
    public StreamingFragment() {
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
        View view = inflater.inflate(R.layout.fragment_streaming, container, false);
        addrField = (EditText)view.findViewById(R.id.addr);
        btnConnect = (Button)view.findViewById(R.id.connect);
        streamView = (VideoView)view.findViewById(R.id.streamview);

        btnConnect.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String s = addrField.getEditableText().toString();
                playStream(s);
            }});


        return view;
    }

    private void playStream(String src){
        Uri UriSrc = Uri.parse(src);
        if(UriSrc == null){
            Toast.makeText(getActivity(),
                    "UriSrc == null", Toast.LENGTH_LONG).show();
        }else{
            streamView.setVideoURI(UriSrc);
            mediaController = new MediaController(getActivity());
            streamView.setMediaController(mediaController);
            streamView.start();

            Toast.makeText(getActivity(),
                    "Connect: " + src,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        streamView.stopPlayback();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
