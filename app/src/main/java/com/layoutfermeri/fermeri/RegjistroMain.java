package com.layoutfermeri.fermeri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class RegjistroMain extends AppCompatActivity {

    Button btnLope;
    Button btnDele;
    Button btnKuaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regjistro_main);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        btnLope=(Button) findViewById(R.id.idLope);

        btnLope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objLope=new Intent(RegjistroMain.this, RegjistroGjedhe.class);
                startActivity(objLope);
            }
        });

        btnDele=(Button) findViewById(R.id.idDele);

        btnDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objDele=new Intent(RegjistroMain.this, RegjistroDele.class);
                startActivity(objDele);
            }
        });
        btnKuaj=(Button) findViewById(R.id.idKuaj);

        btnKuaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objKuaj=new Intent(RegjistroMain.this, RegjistroKuaj.class);
                startActivity(objKuaj);
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
