package com.example.clickjogos;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    public SecondFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        Button btnChutar = findViewById(R.id.btn_chutar);
        final EditText input = findViewById(R.id.et_numero);

        btnChutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chute = input.getText().toString().trim();

                if (!TextUtils.isEmpty(chute)) {
                    Random random = new Random();
                    int numero = random.nextInt(100);

                    if (Integer.parseInt(chute) == numero)
                        Toast.makeText(getApplicationContext(), "Você acertou mizeravi", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Você errou :( \n Era " + numero, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

        return view;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            NavUtils.navigateUpTo(this, intent);

        }
        return super.onOptionsItemSelected(item);
    }

}
