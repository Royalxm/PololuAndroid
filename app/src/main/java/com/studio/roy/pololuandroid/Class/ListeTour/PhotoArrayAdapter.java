package com.studio.roy.pololuandroid.Class.ListeTour;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio.roy.pololuandroid.Class.Utils.DbBitmapUtility;
import com.studio.roy.pololuandroid.R;

import java.util.ArrayList;

/**
 * Created by Royal on 16/12/2016.
 */

public class PhotoArrayAdapter extends ArrayAdapter<Photo> {
    private final Context context;
    private final ArrayList<Photo> values;

    public PhotoArrayAdapter(Context context, ArrayList<Photo> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_photo, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewPicture);
      Bitmap bitmap = BitmapFactory.decodeByteArray((values.get(position).getDataPhoto()), 0, (values.get(position).getDataPhoto()).length);
        imageView.setImageBitmap(bitmap);

        // change the icon for Windows and iPhone
      //  String s = values[position];


        return rowView;
    }
}
