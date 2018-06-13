package com.layoutfermeri.fermeri;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LED-COM on 5/27/2018.
 */

public class Event_List extends ArrayAdapter<Databaza_Eventet> {
    private Activity context;
    private List<Databaza_Eventet> eventsList;
    public Event_List(Activity context,List<Databaza_Eventet> eventsList){
        super(context,R.layout.list_item,eventsList);
        this.context=context;
        this.eventsList=eventsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem=inflater.inflate(R.layout.list_item,null,true);

        TextView textView=(TextView) listViewItem.findViewById(R.id.txtItem);
        TextView textViewKafshet=(TextView) listViewItem.findViewById(R.id.txtSmall);
        Databaza_Eventet eventet=eventsList.get(position);
        textView.setText(eventet.getData());
        textViewKafshet.setText(eventet.getKafshet()+" ID: "+eventet.getIdKafshes());
        return listViewItem;
    }
}