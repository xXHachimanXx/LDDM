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
    private TextInputEditText edtTexto;
    private EditText edtNumero;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Realizando referências aos obj visuais
        edtTexto = findViewById(R.id.edtTextoId);
        edtNumero = findViewById(R.id.edtNumeroid);
        txtResultado = findViewById(R.id.txtResultadoid);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean validaCampo(String campo)
    {
        if(campo == null || campo.equals(""))
            return false;
        else
            return true;
    }

    //Procedimento para imprimir mensagem na tela
    public void print(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    public void onClickButton(View view)
    {
        String texto = edtTexto.getText().toString();
        String numero = edtNumero.getText().toString();

        if(validaCampo(texto) && validaCampo(numero))
        {
            txtResultado.setText("Texto: " + texto + " Número: " + numero);
        }
        else { print("Obrigatório informar os campos!"); }
    }


    public void onClickCheckBox(View view)
    {
        Boolean checked = ((CheckBox)view).isChecked();

        if(checked)
            print("CheckBox ON");
        else
            print("CheckBox OFF");
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
            print("" + edtTexto.getText().toString());
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
            float temp = Float.parseFloat(edtTexto.getText().toString());
            temp = (float) (temp * 1.8) + 32;

            print("" + temp);
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
            float temp = Float.parseFloat(edtTexto.getText().toString());
            temp = (float)(temp + 273.15);

            print("" + temp);
        }
    }//end kelvinCheckBox()

}
