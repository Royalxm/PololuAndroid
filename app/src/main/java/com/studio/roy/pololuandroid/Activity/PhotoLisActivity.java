package com.studio.roy.pololuandroid.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.studio.roy.pololuandroid.Class.ListeTour.Photo;
import com.studio.roy.pololuandroid.Class.ListeTour.PhotoArrayAdapter;
import com.studio.roy.pololuandroid.Class.ListeTour.PhotoDatabase;
import com.studio.roy.pololuandroid.R;

import java.util.ArrayList;

public class PhotoLisActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_lis);
        Intent intent = getIntent();
        String StrData= intent.getStringExtra("id");

        listView = (ListView)findViewById(R.id.ListViewDataPhotoActi);


        final ArrayList<Photo> list;

        final PhotoDatabase tourDatabase = new PhotoDatabase(PhotoLisActivity.this);
        Log.d("test:",StrData);
        list = tourDatabase.getAllPhotoiD(StrData);
        Log.d("test:",Integer.toString(list.size()));

        PhotoArrayAdapter adapter = new PhotoArrayAdapter(PhotoLisActivity.this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(PhotoLisActivity.this,
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
