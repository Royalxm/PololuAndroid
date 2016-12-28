package com.studio.roy.pololuandroid.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.studio.roy.pololuandroid.Class.ListeTour.Photo;
import com.studio.roy.pololuandroid.Class.ListeTour.PhotoArrayAdapter;
import com.studio.roy.pololuandroid.Class.ListeTour.PhotoDatabase;
import com.studio.roy.pololuandroid.R;

import java.util.ArrayList;


public class PhotoLisFragment extends Fragment {
    ListView listView;

    public PhotoLisFragment() {
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
        View view = inflater.inflate(R.layout.fragment_photo_lis, container, false);
        listView = (ListView)view.findViewById(R.id.ListViewData);


        final ArrayList<Photo> list;

        final PhotoDatabase tourDatabase = new PhotoDatabase(getActivity());

        list = tourDatabase.getAllPhoto();

        PhotoArrayAdapter adapter = new PhotoArrayAdapter(getContext(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });
        return view;
    }



}
