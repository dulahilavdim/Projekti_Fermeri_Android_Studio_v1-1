package com.layoutfermeri.fermeri;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Kalkulatori_Lindjes extends AppCompatActivity {

    Button btnData;
    Calendar c;
    TextView tv;
    TextView TV;

    int year, month, day;

    RadioButton rbtGjedhe, rbtKuaj, rbtQen, rbtDele, rbtDhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulatori__lindjes);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btnData = (Button) findViewById(R.id.btnZgjedhDaten);
        tv = (TextView) findViewById(R.id.tvData);
        c = Calendar.getInstance();
        TV=(TextView) findViewById(R.id.idtextview);

        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        //month = month+1;
        tv.setText("Data sot: " +day+"/"+(month+1)+"/"+year);

        rbtGjedhe = (RadioButton) findViewById(R.id.rbGjedhe);
        rbtKuaj = (RadioButton) findViewById(R.id.rbKuaj);
        rbtQen = (RadioButton) findViewById(R.id.rbQen);
        rbtDele = (RadioButton) findViewById(R.id.rbDele);
        rbtDhi = (RadioButton) findViewById(R.id.rbDhi);



        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog=  new
                        DatePickerDialog(Kalkulatori_Lindjes.this, R.style.Theme_AppCompat_DayNight_Dialog ,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        //day= day+280;


                        //c.set(year, month, day);
                        //c.add(Calendar.DATE,280);
                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        //tv.setText("Dita e shtimit: "+df.format(c.getTime())+" \n(Dita mund te ndryshoj per +-10 dite)");




                        if (rbtGjedhe.isChecked())
                        {
                            c.set(year, month, day);
                            c.add(Calendar.DATE,280);
                            tv.setText("Data e shtimit për gjedhin: "+df.format(c.getTime()));
                            TV.setText("Data mund të ndryshojë për +-10 ditë");
                        }
                        else if (rbtKuaj.isChecked())
                        {
                            c.set(year, month, day);
                            c.add(Calendar.DATE,336);
                            tv.setText("Data e shtimit për kalin: "+df.format(c.getTime()));
                            TV.setText("Data mund të ndryshojë për +-10 ditë");
                    }
                        else if (rbtQen.isChecked())
                        {
                            c.set(year, month, day);
                            c.add(Calendar.DATE,62);
                            tv.setText("Data e shtimit për qen: "+df.format(c.getTime()));
                            TV.setText("Data mund të ndryshojë për +-10 ditë");

                        }
                        else if (rbtDele.isChecked())
                        {
                            c.set(year, month, day);
                            c.add(Calendar.DATE,150);
                            tv.setText("Data e shtimit për dele: "+df.format(c.getTime()));
                            TV.setText("Data mund të ndryshojë për +-5 ditë");
                        }
                        else if (rbtDhi.isChecked())
                        {
                            c.set(year, month, day);
                            c.add(Calendar.DATE,154);
                            tv.setText("Data e shtimit për dhi: "+df.format(c.getTime()));
                            TV.setText("Data mund të ndryshojë për +-8 ditë");
                        }
                        else
                        {
                            Toast.makeText(Kalkulatori_Lindjes.this, "Zgjedh llojin e kafshes fillimisht", Toast.LENGTH_LONG).show();
                        }







                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
