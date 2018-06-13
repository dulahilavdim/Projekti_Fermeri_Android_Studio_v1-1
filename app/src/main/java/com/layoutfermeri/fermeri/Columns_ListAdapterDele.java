package com.layoutfermeri.fermeri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Columns_ListAdapterDele extends ArrayAdapter<Delja> {

    private LayoutInflater mInflater;
    private ArrayList<Delja> delet;
    private int mViewResourceId;

    public Columns_ListAdapterDele(Context context, int textViewResourceId, ArrayList<Delja> delet)
    {
        super(context,textViewResourceId,delet);
        this.delet = delet;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }


    public View getView (int position, View convertView, ViewGroup parents)
    {
        convertView = mInflater.inflate(mViewResourceId,null);
        Delja delja = delet.get(position);


        if (delja != null)
        {
            TextView tvShifra = (TextView) convertView.findViewById(R.id.tvShifra);
            TextView tvEmri   = (TextView) convertView.findViewById(R.id.tvEmri);
            TextView tvGjinia = (TextView) convertView.findViewById(R.id.tvGjinia);
            TextView tvNgjyra = (TextView) convertView.findViewById(R.id.tvNgjyra);
            if (tvShifra != null)
            {
                tvShifra.setText(delja.getShifra());
            }
            if (tvShifra != null)
            {
                tvEmri.setText(delja.getEmri());
            }
            if (tvShifra != null)
            {
                tvGjinia.setText(delja.getGjinia());
            }
            if (tvShifra != null)
            {
                tvNgjyra.setText(delja.getNgjyra());
            }
        }
        return convertView;
    }
}