package com.layoutfermeri.fermeri;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegjistroGjedhe extends AppCompatActivity {

    EditText etShifra, etEmri, etGjinia, etNgjyra, etNgarko;

    Button btnRuaj;
    Button btnShfaq;

    Databaza myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regjistro_gjedhe);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        myDb = new Databaza(this);

        etShifra = (EditText) findViewById(R.id.etShifra);
        etEmri = (EditText) findViewById(R.id.etEmri);
        etGjinia = (EditText) findViewById(R.id.etGjinia);
        etNgjyra = (EditText) findViewById(R.id.etNgjyra);

        btnRuaj = (Button) findViewById(R.id.btnRuaj);
        btnShfaq = (Button) findViewById(R.id.btnShfaq);

        ShtoTeDhena();
        viewAll();

    }
    public void ShtoTeDhena ()
    {
        btnRuaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    boolean isInserted = myDb.insertData(etShifra.getText().toString(), etEmri.getText().toString(),
                            etGjinia.getText().toString(), etNgjyra.getText().toString());
                    if (isInserted == true) {
                        Toast.makeText(RegjistroGjedhe.this, "Regjistrimi i suksesshem", Toast.LENGTH_LONG).show();
                        etShifra.setText("");
                        etEmri.setText("");
                        etGjinia.setText("");
                        etNgjyra.setText("");
                    } else
                        Toast.makeText(RegjistroGjedhe.this, "Regjistrimi i pasuksesshem", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void viewAll()
    {
        btnShfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    //show msg
                    showMessage("Gabim", "Ska te dhena");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID: "+res.getString(0)+"\n");
                    buffer.append("Shifra: "+res.getString(1)+"\n");
                    buffer.append("Emri: "+res.getString(2)+"\n");
                    buffer.append("Gjinia: "+res.getString(3)+"\n");
                    buffer.append("Ngjyra: "+res.getString(4)+"\n\n");

                }
                // show all data
                showMessage("Te dhenat", buffer.toString());

            }
        });
    }

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}

