package com.example.agenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_atualizar, container, false);
        buttonBuscar = rootView.findViewById(R.id.button_buscar_id);
        editTextNome = rootView.findViewById(R.id.nome_edit_text3);
        editTextEmail = rootView.findViewById(R.id.email_edit_text3);
        buttonAtualizar = rootView.findViewById(R.id.button_atualizar3);
        buttonDeletar = rootView.findViewById((R.id.button_delete_id));
        editTextUserId = rootView.findViewById(R.id.id_edit_text3);
        editTextEnd = rootView.findViewById(R.id.editText_end_id);
        editTextCEP = rootView.findViewById(R.id.editText_cep_id);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email_id = editTextUserId.getText().toString();

                contatoDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Contato contato = new Contato();
                        String idContato = Base64.encodeToString(email_id.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
                        boolean contatoCadastrado = dataSnapshot.hasChild(idContato);

                        if (contatoCadastrado) {
                            contato.setNome(dataSnapshot.child(idContato).child("nome").getValue().toString());

                            contato.setEmail(dataSnapshot.child(idContato).child("email").getValue().toString());

                            contato.setId(Integer.parseInt(dataSnapshot.child(idContato).child("id").getValue().toString()));

                            contato.setCep(dataSnapshot.child(idContato).child("cep").getValue().toString());

                            contato.setEndereco(dataSnapshot.child(idContato).child("endereco").getValue().toString());

                        } else {
                            contato = null;
                        }

                        if (contato != null) {
                            editTextNome.setText(contato.getNome());
                            editTextEmail.setText(contato.getEmail());
                            editTextCEP.setText(contato.getCep());
                            editTextEnd.setText(contato.getEndereco());
                        } else
                            Toast.makeText(getContext().getApplicationContext(), "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
                    }//end onDataChange()

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }//end onClick()
        });


        buttonAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email_id = editTextUserId.getText().toString();
                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();
                String cep = editTextCEP.getText().toString();
                String endereco = editTextEnd.getText().toString();

                String idContato = Base64.encodeToString(email.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");

                if (!nome.equals("") && !email.equals("")) {
                    int id = Integer.parseInt(editTextUserId.getText().toString());
                    Contato contato = new Contato(nome, email);
                    contato.setCep(cep);
                    contato.setEndereco(endereco);
                    contato.setId(Integer.parseInt(idContato));
                    contato.setId(id);

                    atualizaUsuarios(contato, email_id);
                    Toast.makeText(getContext().getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    editTextNome.setText("");
                    editTextEmail.setText("");
                    editTextCEP.setText("");
                    editTextEnd.setText("");
                    editTextUserId.setText("");
                } else
                    Toast.makeText(getContext().getApplicationContext(), "Favor preencher os campos", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextUserId.getText().toString().equals("")) {
                    String email = editTextUserId.getText().toString();

                    deleteUsuarios(email);

                    Toast.makeText(getContext().getApplicationContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                    editTextNome.setText("");
                    editTextEmail.setText("");
                    editTextCEP.setText("");
                    editTextEnd.setText("");
                    editTextUserId.setText("");

                } else
                    Toast.makeText(getContext().getApplicationContext(), "Digite o email", Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }

    private void atualizaUsuarios(final Contato contato, final String email_id) {
        contatoDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String idContato = "" + contato.getId(); //passando id como string
                boolean contatoJaCadastrado = dataSnapshot.hasChild(idContato);

                if (!contatoJaCadastrado) {
                    String idContatoAntigo = Base64.encodeToString(email_id.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");

                    contatoDatabaseReference.child(idContatoAntigo).removeValue();

                    contatoDatabaseReference.child(idContato).setValue(contato);

                    Toast.makeText(getContext().getApplicationContext(), "Contato atualizado com sucesso", Toast.LENGTH_SHORT).show();

                } else {
                    contatoDatabaseReference.child(idContato).setValue(contato);
                    Toast.makeText(getContext().getApplicationContext(), "Contato atualizado com sucesso", Toast.LENGTH_SHORT).show();
                }//end if

            }//end onDataChange()

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    /*
    private Contato recuperarUsuarios(int id) {
        Contato contato = null;
        try {
            SQLiteDatabase bancoDeDados = getContext().getApplicationContext().openOrCreateDatabase("bancoContatos", MODE_PRIVATE, null);

            Cursor cursor = bancoDeDados.rawQuery("SELECT id, nome, email FROM Contato WHERE id = " + String.valueOf(id), null);
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceEmail = cursor.getColumnIndex("email");

            cursor.moveToFirst();
            while (cursor != null) {
                contato = new Contato(cursor.getString(indiceNome), cursor.getString(indiceEmail));
                contato.setId(Integer.parseInt(cursor.getString(indiceId)));
                cursor.moveToNext(); //move para o próximo registro
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contato;
    }
*/

    private void deleteUsuarios(final String email)
    {
        contatoDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String idContato = Base64.encodeToString(email.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
                boolean contatoExiste = dataSnapshot.hasChild(idContato);

                if (!contatoExiste)
                    Toast.makeText(getContext().getApplicationContext(), "Conato não existe.", Toast.LENGTH_SHORT).show();
                else {
                    contatoDatabaseReference.child(idContato).removeValue();
                    Toast.makeText(getContext().getApplicationContext(), "Contato removido com sucesso.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }//end deleteUsuarios()

}
