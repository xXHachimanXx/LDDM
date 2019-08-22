package com.example.conversortemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
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

    public boolean validaCampo(double number)
    {
        return (number > 0);
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

        double numero = Double.valueOf(edtNumero.getText().toString());

        if(validaCampo(numero))
        {
            onClickButton(numero, view);
        }
        else { print("Obrigatório informar os campos!"); }
    }


    private void onClickButton(double numero, View view)
    {
        RadioGroup temperaturas = (RadioGroup) findViewById(R.id.idTemperaturas);
        boolean checked = ((RadioButton) view).isChecked();

        switch (temperaturas.getCheckedRadioButtonId())
        {
            case R.id.idCelsius:
                if(checked)
                    txtResultado.setText("" + numero);
                break;
            case R.id.idKelvin:
                //pegar string e converter valor para kelvin
                double temp = numero;
                temp = (temp + 273.15);
                txtResultado.setText(temp + "K");
                break;
            case R.id.idFahrenheit:
                //pegar string e converter valor para fahrenheit
                double tmp = numero;
                tmp = (tmp * 1.8) + 32;
                txtResultado.setText("°F" + tmp);
                break;
            default:
                txtResultado.setText("°C: " + numero);
                break;
        }
    }

}
