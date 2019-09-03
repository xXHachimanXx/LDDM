package com.example.clickjogos;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import java.util.Random;

public class JogoAdvinha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_advinha);

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            NavUtils.navigateUpTo(this, intent);

        }
        return super.onOptionsItemSelected(item);
    }

}
