package com.example.conversortemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity
{
    //Referenciando objetos visuais
    private EditText edtNumero;
    private TextView txtResultado;

    private boolean checkBoxCelsius;
    private boolean checkBoxKelvin;
    private boolean checkBoxFahrenheit;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean validaCampo(String campo)
    {
        return (campo == null || campo.equals(""));
    }

    //Procedimento para imprimir mensagem na tela
    public void print(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    public void onClickButton(View view)
    {
        //Realizando referências aos obj visuais
        this.edtNumero = findViewById(R.id.edtNumeroid);
        this.txtResultado = findViewById(R.id.txtResultadoid);

        String numero = edtNumero.getText().toString();

        if(validaCampo(numero))
        {
            onClickButton(numero);
        }
        else { print("Obrigatório informar os campos!"); }
    }


    private void onClickButton(String numero)
    {
        RadioGroup temperaturas = (RadioGroup) findViewById(R.id.idTemperaturas);

        switch (temperaturas.getCheckedRadioButtonId())
        {
            case R.id.idCelsius:
                txtResultado.setText(String.valueOf(numero));
                break;
            case R.id.idKelvin:
                //pegar string e converter valor para kelvin
                double temp = Double.parseDouble(numero);
                temp = (temp + 273.15);
                txtResultado.setText(String.valueOf(temp) + "K");
                break;
            case R.id.idFahrenheit:
                //pegar string e converter valor para fahrenheit
                double tmp = Double.parseDouble(numero);
                tmp = (tmp * 1.8) + 32;
                txtResultado.setText("°F" + String.valueOf(tmp));
                break;
            default:
                txtResultado.setText("°C: " + numero);
                break;
        }
    }

}
