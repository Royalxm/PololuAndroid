package com.studio.roy.pololuandroid.Class.ListeTour;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio.roy.pololuandroid.R;

import java.util.ArrayList;

/**
 * Created by Royal on 16/12/2016.
 */

public class TourArrayAdapter extends ArrayAdapter<Tour> {
    private final Context context;
    private final ArrayList<Tour> values;

    public TourArrayAdapter(Context context,ArrayList<Tour> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_tour, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textView4);
        TextView textViewid = (TextView) rowView.findViewById(R.id.textView3);

        textView.setText(values.get(position).getNameTour());
        textViewid.setText(values.get(position).getId());

        if(Integer.parseInt(values.get(position).getEtatTour() ) == 0)
             rowView.setBackgroundColor(Color.GREEN);
        else
            rowView.setBackgroundColor(Color.RED);
        // change the icon for Windows and iPhone
      //  String s = values[position];


        return rowView;
    }
}
