package com.layoutfermeri.fermeri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Columns_ListAdapterGjedhe extends ArrayAdapter<Gjedhi> {

    private LayoutInflater mInflater;
    private ArrayList<Gjedhi> gjedhet;
    private int mViewResourceId;

    public Columns_ListAdapterGjedhe(Context context, int textViewResourceId, ArrayList<Gjedhi> gjedhet)
    {
        super(context,textViewResourceId,gjedhet);
        this.gjedhet = gjedhet;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }


    public View getView (int position, View convertView, ViewGroup parents)
    {
        convertView = mInflater.inflate(mViewResourceId,null);
        Gjedhi gjedhi = gjedhet.get(position);


            if (gjedhi != null)
            {
                TextView tvShifra = (TextView) convertView.findViewById(R.id.tvShifra);
                TextView tvEmri   = (TextView) convertView.findViewById(R.id.tvEmri);
                TextView tvGjinia = (TextView) convertView.findViewById(R.id.tvGjinia);
                TextView tvNgjyra = (TextView) convertView.findViewById(R.id.tvNgjyra);
            if (tvShifra != null)
            {
                tvShifra.setText(gjedhi.getShifra());
            }
            if (tvShifra != null)
            {
                tvEmri.setText(gjedhi.getEmri());
            }
            if (tvShifra != null)
            {
                tvGjinia.setText(gjedhi.getGjinia());
            }
            if (tvShifra != null)
            {
                tvNgjyra.setText(gjedhi.getNgjyra());
            }
        }
        return convertView;
    }
}