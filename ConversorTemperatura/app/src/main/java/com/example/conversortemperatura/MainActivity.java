package com.example.conversortemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity
{
    //Referenciando objetos visuais
    private EditText edtNumero;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Realizando referências aos obj visuais
        edtNumero = findViewById(R.id.edtNumeroid);
        txtResultado = findViewById(R.id.txtResultadoid);

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
        String numero = edtNumero.getText().toString();

        if(validaCampo(numero))
        {
            txtResultado.setText("Temperatura: " + numero);
        }
        else { print("Obrigatório informar os campos!"); }
    }

    /**
     * Método para mostrar celsius
     *
     * @param view
     */
    public void celsiusCheckBox(View view)
    {
        Boolean checked = ((CheckBox) view).isChecked();
        if (checked)
        {
            txtResultado.setText(String.valueOf(edtNumero.getText().toString()));
        }
    }

    /**
     * Método de conversão celsius -> fahrenheit
     *
     * @param view
     */
    public void fahrenheitCheckBox(View view)
    {
        Boolean checked = ((CheckBox) view).isChecked();
        if (checked)
        {
            //pegar string e converter valor para fahrenheit
            double temp = Double.parseDouble(edtNumero.getText().toString());
            temp = (temp * 1.8) + 32;

            txtResultado.setText(String.valueOf(temp));
        }

    }

    /**
     * Método de conversão celsius -> kelvin
     *
     * @param view
     */
    public void kelvinCheckBox(View view)
    {
        Boolean checked = ((CheckBox) view).isChecked();

        if (checked)
        {
            //pegar string e converter valor para fahrenheit
            double temp = Double.parseDouble(edtNumero.getText().toString());
            temp = (temp + 273.15);

            txtResultado.setText(String.valueOf(temp));
            print("" + temp);
        }
    }//end kelvinCheckBox()

}
