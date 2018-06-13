package com.layoutfermeri.fermeri;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Llogarit_Daten_Shtimit extends AppCompatActivity {

    Button btnZgjedhDate;
   // Calendar calendar;
    TextView  tvVendos;
   int year, month, day;
   Calendar calendar;
   ListView lista;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llogarit__daten__shtimit);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        lista=(ListView) findViewById(R.id.idListaEDatave);
        btnZgjedhDate=(Button) findViewById(R.id.idZgjedhDaten);
        tvVendos=(TextView) findViewById(R.id.idVendosDaten);
        calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        tvVendos.setText("Data sot: " +day+"/"+(month+1)+"/"+year);

       // ditet=Integer.parseInt((String) tvTest.getText());
        //calendar.add(Calendar.DATE, ditet );



        btnZgjedhDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog=  new
                        DatePickerDialog(Llogarit_Daten_Shtimit.this, R.style.Theme_AppCompat_DayNight_Dialog ,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        //day= day+280;


                        int ditet = 280;
                        //c.set(year, month, day);
                        //c.add(Calendar.DATE,280);
                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        calendar.set(year, month, day);
                        calendar.add(Calendar.DATE, ditet);

                       // String[] lista = {};
                       // for (int i = 1; i <= 30; i++) {
                            tvVendos.setText("Eventi ne daten: " + df.format(calendar.getTime()));
                       //     lista[i] = df.format(calendar.getTime()) + ", ";
                       // }
//
                       // for (int i = 1; i <= 30; i++) {
                       //     tvVendos.setText("Lista e dhene "+lista[i]+" ");
                       // }

                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });
        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar= Calendar.getInstance();
        Intent intent = new Intent("android.action.DISPLAY_NOTIFICATION");
        PendingIntent broadcast= PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), broadcast );


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
