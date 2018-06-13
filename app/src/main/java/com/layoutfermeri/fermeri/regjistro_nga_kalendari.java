package com.layoutfermeri.fermeri;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

public class regjistro_nga_kalendari extends AppCompatActivity {

    CalendarView calendar;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    int year, month, dayofMonth;
    DatabaseReference databazeEvente;
    ListView listViewEventet;
    List<Databaza_Eventet> evetetList;
    Calendar c;
    Spinner eventetSpiner;
    EditText txtEditText;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regjistro_nga_kalendari);
        txtEditText=(EditText) findViewById(R.id.txtEditText);
        eventetSpiner=(Spinner) findViewById(R.id.idSpiner);

        evetetList=new ArrayList<>();
        databazeEvente= FirebaseDatabase.getInstance().getReference("tblEventet");
        listViewEventet=(ListView) findViewById(R.id.idDatatELindjes);
        calendar=(CalendarView) findViewById(R.id.idZgjedhNgaKalendari);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayofMonth) {
                //Toast.makeText(getBaseContext(),"Select date "+dayofMonth+"/"+month+"/"+ year,Toast.LENGTH_SHORT).show();
                //String newItem= "Select date "+dayofMonth+"/"+month+"/"+ year;
                //arrayList.add(newItem);
                //adapter.notifyDataSetChanged();
                int muaji = month + 1;
                shtoEvente(dayofMonth, muaji, year);
                int ditet = 280;
                // //c.set(year, month, day);
                // //c.add(Calendar.DATE,280);
                // //DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                // c.set(year, month, dayofMonth);
                // c.add(Calendar.DATE, ditet);

            }

        });

        // AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        // Calendar c= Calendar.getInstance();
        // Intent intent = new Intent("android.action.DISPLAY_NOTIFICATION");
        // PendingIntent broadcast= PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), broadcast );

      listViewEventet.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
             Databaza_Eventet events=evetetList.get(i);
             showUpdateDialog(events.getIdData(),events.getData(), events.getKafshet(), events.getIdKafshes());

              return false;
          }
      });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databazeEvente.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                evetetList.clear();

                for(DataSnapshot eventetSnapshot:dataSnapshot.getChildren())
                {
                    Databaza_Eventet eventet=eventetSnapshot.getValue(Databaza_Eventet.class);
                    evetetList.add(eventet);
                }
                Event_List adapter=new Event_List(regjistro_nga_kalendari.this,evetetList);
                listViewEventet.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void scrollMyListViewToBottom() {
        listViewEventet.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                listViewEventet.setSelection(adapter.getCount() - 1);
            }
        });
    }
    private void showUpdateDialog(final String idData,final String data,final String kafsha, final String idKafsha)
    {
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
        LayoutInflater inflater=getLayoutInflater();
       final View dialogView=inflater.inflate(R.layout.update_dialog_eventet,null);
       dialogBuilder.setView(dialogView);

       final EditText editTextdate=(EditText) dialogView.findViewById(R.id.editTextDate);
        final EditText editTextname=(EditText) dialogView.findViewById(R.id.editTextName);
        final Spinner idSpinnereventet=(Spinner) dialogView.findViewById(R.id.idSpiner);
        final Button btnUpdate=(Button) dialogView.findViewById(R.id.idUpdateButton);
        final Button btnDelete=(Button) dialogView.findViewById(R.id.idDeleteButton);


        dialogBuilder.setTitle("Ndrysho eventin per kafshen me ID: "+ idKafsha);

        final AlertDialog alertDialog=dialogBuilder.create();
        alertDialog.show();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteEvent(idData);
                alertDialog.dismiss();
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                String newData= "Eventi ne daten:"+editTextdate.getText().toString().trim();
                String newName= editTextname.getText().toString().trim();
                String kafsha=idSpinnereventet.getSelectedItem().toString();
                if(TextUtils.isEmpty(newData)&&TextUtils.isEmpty(newName))
                {
                    editTextdate.setError("Vendoseni daten!");
                    editTextname.setError("Vendoseni emrin!");
                    return;
                }


                updateEvent( idData, newData, kafsha,  newName);

                alertDialog.dismiss();

            }
        });

    }
    private void deleteEvent(String idData)
    {
        DatabaseReference drEventet=FirebaseDatabase.getInstance().getReference("tblEventet").child(idData);

        drEventet.removeValue();
        Toast.makeText(this, "Eventi u fshi me sukses!", Toast.LENGTH_SHORT).show();

    }


    private  void updateEvent(String idData,String data,String kafsha, String idKafsha)
    {
     DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("tblEventet").child(idData);
     Databaza_Eventet eventat =new Databaza_Eventet(idData, data, kafsha,  idKafsha);

     databaseReference.setValue(eventat);
     Toast.makeText(this,"Eventi u ndryshua me sukses",Toast.LENGTH_LONG).show();
    }

    private void shtoEvente(int a, int b, int c)
    {
        String eventi="Eventi ne daten: "+a+"/"+b+"/"+ c;
        String kafshet=eventetSpiner.getSelectedItem().toString();
        String idkafshet=txtEditText.getText().toString();


        String id= databazeEvente.push().getKey();
        Databaza_Eventet events=new Databaza_Eventet(id, eventi, kafshet,idkafshet);
        databazeEvente.child(id).setValue(events);
        Toast.makeText((this), "Eventi u shtua!", Toast.LENGTH_SHORT).show();
    }


}