package com.layoutfermeri.fermeri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Kafshet extends AppCompatActivity {

    Button btnGjedhe, btnDele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kafshet);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btnGjedhe = (Button) findViewById(R.id.idGjedhe);
        btnDele = (Button) findViewById(R.id.idDele);

        btnGjedhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objI=new Intent(Kafshet.this, ListView_Gjedhe.class);
                startActivity(objI);
            }
        });
        btnDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objI=new Intent(Kafshet.this, ListView_Dele.class);
                startActivity(objI);
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
