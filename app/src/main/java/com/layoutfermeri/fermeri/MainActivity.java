package com.layoutfermeri.fermeri;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button btnKafshet;
    Button btnRegjistro;
    Button btnKalkulatori;
    Button btnEventet;
    Button btnNdihma;
    private DrawerLayout mainSlideDrawer;
    private ActionBarDrawerToggle mainToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNdihma=(Button) findViewById(R.id.idAboutUs);

        btnNdihma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objNdihma=new Intent(MainActivity.this, Ndihma.class);
                startActivity(objNdihma);
            }
        });

        btnEventet=(Button) findViewById(R.id.idEventet);
        btnEventet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objEventet=new Intent(MainActivity.this, regjistro_nga_kalendari.class);
                startActivity(objEventet);
            }
        });


        btnKafshet=(Button) findViewById(R.id.idKafshet);
        btnRegjistro=(Button) findViewById(R.id.idRegjistro);

        btnKafshet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objI=new Intent(MainActivity.this, Kafshet.class);
                startActivity(objI);
            }
        });

        btnRegjistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objRegjistro=new Intent(MainActivity.this,RegjistroMain.class);
                startActivity(objRegjistro);
            }
        });

        btnKalkulatori=(Button) findViewById(R.id.idKalkulatori);
        btnKalkulatori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objKalkulatori=new Intent(MainActivity.this,Kalkulatori_Lindjes.class);
                startActivity(objKalkulatori);
            }
        });

        mainSlideDrawer=(DrawerLayout) findViewById(R.id.id_nav);
        mainToggle=new ActionBarDrawerToggle(MainActivity.this, mainSlideDrawer, R.string.open,R.string.close);
        mainSlideDrawer.addDrawerListener(mainToggle);
        mainToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView) findViewById(R.id.id_navigationview);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mainToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.home)
        {
            Intent objHome=new Intent(MainActivity.this, MainActivity.class);
            startActivity(objHome);
        }
        if(id==R.id.kafshet)
        {
            Intent objKafshet=new Intent(MainActivity.this,Kafshet.class);
            startActivity(objKafshet);
        }
        if(id==R.id.regjistro)
        {
            Intent objRegjistro=new Intent(MainActivity.this, RegjistroMain.class);
            startActivity(objRegjistro);
        }
        if(id==R.id.llogarit)
        {
            Intent objKalkulatori=new Intent(MainActivity.this, Kalkulatori_Lindjes.class );
            startActivity(objKalkulatori);
        }
        if(id==R.id.ndihma)
        {
            Intent objNdihma=new Intent(MainActivity.this, Ndihma.class );
            startActivity(objNdihma);
        }
        return false;
    }
}
