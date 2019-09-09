package com.example.clickjogos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //elementos da DOM
        ImageView animais = findViewById(R.id.animaisId);
        ImageView adivinha = findViewById(R.id.jogoAdivinhaID);

        definirBarraLateral(); //definir a barra de tarefas lateral

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().add
                    (R.id.fragmento1_id, new FirstFragment()).commit();
        }


        animais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().
                        add(R.id.fragmento_id, new FirstFragment()).commit();
            }
        });

        adivinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().
                        add(R.id.fragmento_id, new SecondFragment()).commit();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        if (id == R.id.AnimaisId) {
            Log.d("Navigation", "onNavigationItemSelected: deu certo");
            startActivity(new Intent(MainActivity.this, SonsAnimaisActivity.class));
            return true;
        } else if (id == R.id.AdivinhaId) {
            startActivity(new Intent(MainActivity.this, JogoAdvinha.class));
            return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void definirBarraLateral()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
