package com.example.clickjogos;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class JogoAdvinha extends AppCompatActivity
{
    private Button buttonSalvar;
    private EditText chuteNumero;
    private Button btnChutar;
    public static final String Deveras = "Frustrante"; // ;-; DESISTO ;-;

    private static final String PREFERENCE = "filePreference";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_advinha);
        inicializarVariaveis();
        final EditText input = findViewById(R.id.et_numero);

        buttonSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //0 - só pode ser lido por esta app
                SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //verifica se o texto é vazio
                if (chuteNumero.getText().toString().equals(""))
                {
                    Toast.makeText(JogoAdvinha.this, "Digite o nome", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    editor.putString("nome", chuteNumero.getText().toString());
                    editor.commit();

                    Toast.makeText(JogoAdvinha.this, "Seu chute " + chuteNumero.getText().toString() + " foi guardado.", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

        //recuperar os dados
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE, 0);
        if ( sharedPreferences.contains("nome") )
        {
            String userName = sharedPreferences.getString("nome", "Usuário não encontrado");
            String numero = sharedPreferences.getString("Número", "Usuário não encontrado");
            textViewNome.setText(textViewNome.getText() + userName);
            textViewNumero.setText(textViewNumero.getText() + numero);
        } else
            Toast.makeText(getApplicationContext(), "Fudeu", Toast.LENGTH_LONG).show();
    }



    private void inicializarVariaveis()
    {
        this.btnChutar = findViewById(R.id.btn_chutar);
        this.buttonSalvar = findViewById(R.id.idbuttonSalvar);
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
