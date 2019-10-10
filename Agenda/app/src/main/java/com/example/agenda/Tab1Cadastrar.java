package com.example.agenda;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import static android.content.Context.MODE_PRIVATE;

public class Tab1Cadastrar extends Fragment
{
    private Button buttonSalvar;
    private TextInputEditText editTextNome;
    private TextInputEditText editTextEmail;
    private Button buttonAtualizar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inicializarVars(inflater, container);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();
                if ((nome != "") && (email != ""))
                {
                    Contato pessoa = new Contato(nome, email);
                    cadastrarUsuarios(pessoa);
                    Toast.makeText(getContext().getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                }

                editTextNome.setText("");
                editTextEmail.setText("");
            }
        });
        
        return rootView;
    }

    private View inicializarVars(LayoutInflater inflater, ViewGroup container)
    {
        View rootView = inflater.inflate(R.layout.tab1_cadastrar, container, false);

        buttonSalvar = rootView.findViewById(R.id.button_salvar_id);
        editTextNome = rootView.findViewById(R.id.nome_edit_text1);
        editTextEmail = rootView.findViewById(R.id.email_edit_text1);
        buttonAtualizar = rootView.findViewById(R.id.button_atualizar3);

        return rootView;
    }

    private void cadastrarUsuarios (Contato pessoa)
    {
        try
        {
            //Apaga o banco de dadados
            //getContext().getApplicationContext().deleteDatabase("bancoContatos");
            //definimos o nome e o modo do banco de dados.
            SQLiteDatabase bancoDeDados = getContext().getApplicationContext().openOrCreateDatabase("bancoContatos", MODE_PRIVATE, null);
            //criar tabela no banco de dados
            bancoDeDados.execSQL(" CREATE TABLE IF NOT EXISTS Contato(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, email VARCHAR) ");
            String insert = "INSERT INTO Contato " + "(nome, email) VALUES " + "('" + pessoa.getNome() + "','" + pessoa.getEmail() + "')";
            //inserir dados na tabela
            bancoDeDados.execSQL(insert);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
