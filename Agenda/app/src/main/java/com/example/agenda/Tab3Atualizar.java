package com.example.agenda;

import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class Tab3Atualizar extends Fragment {
    private Button buttonBuscar;
    private EditText editTextNome;
    private EditText editTextEmail;
    private Button buttonAtualizar;
    private Button buttonDeletar;
    private EditText editTextUserId;
    private EditText editTextEnd;
    private EditText editTextCEP;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference contatoDatabaseReference = databaseReference.child("Contatos");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inicializarVars(inflater, container);

        buttonBuscar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id = editTextNome.getText().toString();
                recuperarUsuario(id);
            }
        });

        buttonAtualizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String key = editTextUserId.getText().toString();

                if(key != null && !key.equals("")){
                    atualizaUsuario(key);
                }
            }
        });

        buttonDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletaUsuario();
            }
        });

        return rootView;
    }

    private void recuperarUsuario(String id)
    {
        if (id != null)
        {
            buscarUsuario(id);
        }
        else
            Toast.makeText(getContext().getApplicationContext(), "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
    }

    private void atualizaUsuario(String id)
    {
        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String cep = editTextCEP.getText().toString();
        String endereco = editTextEnd.getText().toString();

        Contato contato = new Contato(nome, email, cep, endereco);

        atualizaUsuario(id, contato);
    }


    private void atualizaUsuario(String id, Contato contato) {
        //contatosReference.child(key).push().setValue(contato);

        Map<String, Object> update = new HashMap<>();

        update.put(id, contato);

        contatoDatabaseReference.updateChildren(update);

        Toast.makeText(getContext().getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();

        clearView();
    }//end atualizaUsuarios()


    private void deletaUsuario(){
        String id = editTextUserId.getText().toString();

        if(id != null && !id.equals(""))
            deletaUsuario(id);
    }

    private void deletaUsuario(String key){
        contatoDatabaseReference.child(key).removeValue();
    }

    private View inicializarVars(LayoutInflater inflater, ViewGroup container)
    {
        View rootView = inflater.inflate(R.layout.tab3_atualizar, container, false);

        editTextNome = rootView.findViewById(R.id.nome_edit_text3);
        editTextEmail = rootView.findViewById(R.id.email_edit_text3);
        editTextUserId = rootView.findViewById(R.id.id_edit_text3);
        editTextCEP = rootView.findViewById(R.id.editText_cep_id);
        editTextEnd = rootView.findViewById(R.id.editText_end_id);
        buttonBuscar = rootView.findViewById(R.id.button_buscar_id);
        buttonAtualizar = rootView.findViewById(R.id.button_atualizar3);
        buttonDeletar = rootView.findViewById((R.id.button_delete_id));

        return rootView;
    }

    private void buscarUsuario(final String id){

        contatoDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    Contato contato = data.getValue(Contato.class);

                    if ((contato != null) && contato.getId() == Integer.parseInt(id))
                    {
                        editTextNome.setText(contato.getNome());
                        editTextEmail.setText(contato.getEmail());
                        editTextCEP.setText(contato.getCep());
                        editTextEnd.setText(contato.getEndereco());
                    } else
                        Toast.makeText(getContext().getApplicationContext(), "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext().getApplicationContext(), "Erro na busca no banco de dados", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearView(){
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextCEP.setText("");
        editTextEnd.setText("");
        editTextUserId.setText("");
    }

}