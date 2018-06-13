package com.layoutfermeri.fermeri;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListView_Gjedhe extends AppCompatActivity {

    public static Databaza myDb;
    ArrayList<Gjedhi> gjedhiList;
    Gjedhi gjedhi;
    Columns_ListAdapterGjedhe Adapter = null;
    Button btnSort;
    private ArrayAdapter<String> adapterii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view__gjedhe);



        ListView listView = (ListView) findViewById(R.id.idListView);
        myDb = new Databaza(this);

        gjedhiList = new ArrayList<>();
        Cursor data = myDb.getAllData();

        if (data.getCount() == 0)
        {
            Toast.makeText(ListView_Gjedhe.this, "Lista eshte e zbrazet", Toast.LENGTH_LONG).show();
        }
        else
        {
            while (data.moveToNext())
            {
                gjedhi = new Gjedhi (data.getString(1), data.getString(2), data.getString(3),data.getString(4));
                gjedhiList.add(gjedhi);

            }
            Columns_ListAdapterGjedhe adapter = new Columns_ListAdapterGjedhe(this, R.layout.list_adapter_view_gjedhe, gjedhiList);
            listView = (ListView) findViewById(R.id.idListView);
            listView.setAdapter(adapter);
        }

        //Update dhe delete ne LongClick

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //
                CharSequence[] items = {"Ndrysho", "Fshij"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListView_Gjedhe.this);
                dialog.setTitle("Zgjedh veprimin:");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i==0)
                        {
                            //update
                            Cursor c = ListView_Gjedhe.myDb.getAllData();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext())
                            {
                                arrID.add(c.getInt(0));
                            }
                            //show update Dialog
                            showDialogUpdate(ListView_Gjedhe.this,arrID.get(position));
                        }
                        if (i==1)
                        {
                            //delete
                            Cursor c = ListView_Gjedhe.myDb.getAllData();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext())
                            {
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

        //pjese per fshirje nese nuk punohet
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                Intent objI=new Intent(ListView_Gjedhe.this, xxxxxxx.class);

                objI.putExtra("Pos",position);


                startActivity(objI);
            }
        });*/



    }


    private void showDialogDelete(final int idRecord)
    {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ListView_Gjedhe.this);
        dialogDelete.setTitle("Kujdes!");
        dialogDelete.setMessage("A jeni te sigurt qe doni te fshini");
        dialogDelete.setPositiveButton("Po", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try
                {
                    ListView_Gjedhe.myDb.deleteData(idRecord);
                    Toast.makeText(ListView_Gjedhe.this, "Fshirja e suksesshme", Toast.LENGTH_LONG).show();

                }
                catch (Exception e)
                {
                    Log.e("error",e.getMessage());
                }
                //Refresh i listes pas Delete
                updateRecordList();


            }
        });
        dialogDelete.setNegativeButton("Anulo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void showDialogUpdate(Activity activity, final int position)
    {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_dialog_gjedhe);
        dialog.setTitle("Ndrysho");

        final TextView tvTitulli = (TextView) dialog.findViewById(R.id.idTitulli);

        final EditText edtShifra = (EditText) dialog.findViewById(R.id.edtShifra);
        final EditText edtEmri = (EditText) dialog.findViewById(R.id.edtEmri);
        final EditText edtGjinia = (EditText) dialog.findViewById(R.id.edtGjinia);
        final EditText edtNgjyra = (EditText) dialog.findViewById(R.id.edtNgjyra);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btUpdate);

        //Caktimi i gjeresise dhe lartesise se dialogut
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels*0.95);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();


        //Per shfaqje te te dhenave ne textview ne rastin kur behet update
        Cursor c = ListView_Gjedhe.myDb.getId(position);
        ArrayList<String> arrID = new ArrayList<String>();
        while (c.moveToNext())
        {
            arrID.add(c.getString(0));
            arrID.add(c.getString(1));
            arrID.add(c.getString(2));
            arrID.add(c.getString(3));
            arrID.add(c.getString(4));
        }
        tvTitulli.setText("Ndrysho te dhenat per: "+arrID.get(2));
        edtShifra.setText(arrID.get(1));
        edtEmri.setText(arrID.get(2));
        edtGjinia.setText(arrID.get(3));
        edtNgjyra.setText(arrID.get(4));



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ListView_Gjedhe.myDb.updateDataGjedhe(
                            position,
                            edtShifra.getText().toString().trim(),
                            edtEmri.getText().toString().trim(),
                            edtGjinia.getText().toString().trim(),
                            edtNgjyra.getText().toString().trim()
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"U ndryshua", Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    Log.e("Error, nuk u ndryshua", e.getMessage());

                }
                updateRecordList();

            }
        });

    }

    private void updateRecordList() {
        //merr te dhenat pas delete nga sqlite
        gjedhiList = new ArrayList<>();
        Cursor data = myDb.getAllData();


        while (data.moveToNext())
        {
            gjedhi = new Gjedhi (data.getString(1), data.getString(2), data.getString(3),data.getString(4));
            gjedhiList.add(gjedhi);

        }
        Columns_ListAdapterGjedhe adapter = new Columns_ListAdapterGjedhe(this, R.layout.list_adapter_view_gjedhe, gjedhiList);
        ListView listView = (ListView) findViewById(R.id.idListView);
        listView.setAdapter(adapter);

    }

    private void sortArrayListAZ()
    {
        try {
            Collections.sort(gjedhiList, new Comparator<Gjedhi>() {
                @Override
                public int compare(Gjedhi gjedhi, Gjedhi t1) {
                    return gjedhi.getEmri().compareTo(t1.getEmri());
                }
            });

            Columns_ListAdapterGjedhe adapter = new Columns_ListAdapterGjedhe(this, R.layout.list_adapter_view_gjedhe, gjedhiList);
            ListView listView = (ListView) findViewById(R.id.idListView);
            listView.setAdapter(adapter);

        }
        catch(Exception e)
        {
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_gjedhe,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.idShtoGjedh:
                Intent objI = new Intent(ListView_Gjedhe.this, RegjistroGjedhe.class);
                startActivity(objI);
            case R.id.idNjoftimeGjedhe:
                objI = new Intent(ListView_Gjedhe.this, regjistro_nga_kalendari.class);
                startActivity(objI);
            case R.id.idSortAZ:
                sortArrayListAZ();
        }
        return true;
    }




}
