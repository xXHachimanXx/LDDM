package com.example.agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tab1Cadastrar extends Fragment
{
    private Button buttonSalvar;
    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextcep;
    private EditText editTextendereco;
    private Button buttonAtualizar;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference contatoDatabaseReference = databaseReference.child("Contatos");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.tab1_cadastrar, container, false);

        buttonSalvar = rootView.findViewById(R.id.button_salvar_id);
        editTextNome = rootView.findViewById(R.id.nome_edit_text1);
        editTextEmail = rootView.findViewById(R.id.email_edit_text1); //editText_cep_id
        editTextcep = rootView.findViewById(R.id.editText_cep_id);
        editTextendereco = rootView.findViewById(R.id.editText_end_id);
        buttonAtualizar = rootView.findViewById(R.id.button_atualizar3);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();
                String endereco = editTextendereco.getText().toString();
                String cep = editTextcep.getText().toString();

                if ( !(nome.equals("")) && !(email.equals("")) )
                {
                    Contato pessoa = new Contato(nome, email, endereco, cep);
                    cadastrarUsuarios(pessoa);
                    Toast.makeText(getContext().getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getContext().getApplicationContext(), "Campos vazios", Toast.LENGTH_SHORT).show();

                editTextNome.setText("");
                editTextEmail.setText("");
                editTextendereco.setText("");
                editTextcep.setText("");
            }
        });
        
        return rootView;
    }


    private void cadastrarUsuarios (Contato pessoa)
    {
        databaseReference.child("contatos").push().setValue(pessoa);
    }

}
