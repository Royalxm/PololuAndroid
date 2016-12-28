package com.studio.roy.pololuandroid.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.studio.roy.pololuandroid.Activity.PhotoLisActivity;
import com.studio.roy.pololuandroid.Class.ListeTour.Tour;
import com.studio.roy.pololuandroid.Class.ListeTour.TourArrayAdapter;
import com.studio.roy.pololuandroid.Class.ListeTour.TourDatabase;
import com.studio.roy.pololuandroid.R;

import java.util.ArrayList;


public class listViewInfoFragment extends Fragment {

    public listViewInfoFragment() {
        // Required empty public constructor
    }

ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view_info, container, false);
        listView = (ListView)view.findViewById(R.id.ListViewData);


        final ArrayList<Tour> list;

        final TourDatabase tourDatabase = new TourDatabase(getActivity());

        list = tourDatabase.getAllTour();

        TourArrayAdapter adapter = new TourArrayAdapter(getContext(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(),
                        "Click ListItem Number " + list.get(position).getNameTour(), Toast.LENGTH_LONG)
                        .show();

                Intent intent = new Intent(getActivity(), PhotoLisActivity.class);
                intent.putExtra("id",list.get(position).getNameTour());
                startActivity(intent);
            }
        });
        return view;
    }



}
