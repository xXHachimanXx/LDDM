package com.example.webservices;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{
    private TextView edtCep;
    private TextView edtLogradouro;
    private TextView edtComplemento;
    private TextView edtBairro;
    private TextView edtCidade;
    private TextView edtUF;
    private Button botaoPesquisar;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference contatoDatabaseReference = databaseReference.child("Contatos");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtCep = findViewById(R.id.edtCepId);
        edtLogradouro =  findViewById(R.id.edtLogradouroId);
        edtComplemento =  findViewById(R.id.edtComplementoId);
        edtBairro = findViewById(R.id.edtBairroId);
        edtCidade = findViewById(R.id.edtCidadeId);
        edtUF = findViewById(R.id.edtUFId);
        botaoPesquisar = findViewById(R.id.idBotaoPesquisar);

        botaoPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onClickPesquisar();
            }
        });

    }

    //Método para exibir mensagem Toast
    public void print(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    // PROCEDIMENTO PARA EXECUTAR O ONCLICK DO BOTÃO
    public void onClickPesquisar()
    {
        String cep = edtCep.getText().toString();
        if(cep == null || cep.equals(""))
            print("Obrigatório informar o CEP!");
        else
        {
            WebServiceEndereco webServiceEndereco = new WebServiceEndereco();
            webServiceEndereco.execute(cep);
        }
    }

    public class WebServiceEndereco extends AsyncTask<String, Void, String>
    {
        // MÉTODO QUE FAZ A REQUISIÇÃO HTTP
        @Override
        protected String doInBackground(String... strings)
        {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("https://viacep.com.br/ws/" + strings[0] + "/json/");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String linha;
                StringBuffer buffer = new StringBuffer();

                while ((linha = reader.readLine()) != null) {
                    buffer.append(linha);
                    buffer.append("\n");
                }
                return buffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            return null;
        }

        // MÉTODO QUE CONFIGURA A RESPOSTA DO MÉTODO HTTP
        @Override
        protected void onPostExecute(String s)
        {
            if(s == null) {
                print("Não foi possível recuperar os dados...");
            }
            else {
                try
                {
                    JSONObject json = new JSONObject(s);
                    edtLogradouro.setText(json.getString("logradouro"));
                    edtComplemento.setText(json.getString("complemento"));
                    edtBairro.setText(json.getString("bairro"));
                    edtCidade.setText(json.getString("localidade"));
                    edtUF.setText(json.getString("uf"));
                    salvarEndereco();
                    print("Endereço recuperado com sucesso!");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }//end onPostExecute()

        private void salvarEndereco()
        {
            String logradouro = edtLogradouro.getText().toString();
            String complemento = edtComplemento.getText().toString();
            String bairro = edtBairro.getText().toString();
            String cidade = edtCidade.getText().toString();
            String uf = edtUF.getText().toString();
            String cep = edtCep.getText().toString();

            if (cep != "")
            {
                //Criar objeto Endereco
                Endereco endereco = new Endereco();
                endereco.setLogradouro(logradouro);
                endereco.setComplemento(complemento);
                endereco.setBairro(bairro);
                endereco.setCidade(cidade);
                endereco.setUF(uf);
                endereco.setCep(cep);

                salvarEndereco(endereco);
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            }
            else
                {
                    Toast.makeText(getApplicationContext(), "Campos vazios", Toast.LENGTH_SHORT).show();
                }
        }
        private void salvarEndereco (Endereco endereco)
        {
            databaseReference.child("endereços").push().setValue(endereco);
        }

    }//end class

}//end class