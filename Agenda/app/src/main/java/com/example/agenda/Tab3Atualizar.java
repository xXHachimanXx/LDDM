package com.example.agenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import static android.content.Context.MODE_PRIVATE;

public class Tab3Atualizar extends Fragment
{
    private Button buttonBuscar;
    private TextInputEditText editTextNome;
    private TextInputEditText editTextEmail;
    private Button buttonAtualizar;
    private Button buttonDeletar;
    private TextInputEditText editTextUserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.tab3_atualizar, container, false);
        buttonBuscar = rootView.findViewById(R.id.button_buscar_id);
        editTextNome = rootView.findViewById(R.id.nome_text_input_edit_text3);
        editTextEmail = rootView.findViewById(R.id.email_text_input_edit_text2);
        buttonAtualizar = rootView.findViewById(R.id.button_atualizar3);
        buttonDeletar = rootView.findViewById((R.id.button_delete_id));
        editTextUserId = rootView.findViewById(R.id.id_text_input_edit_text);

        buttonBuscar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!editTextUserId.getText().toString().equals("")){
                    int id = Integer.parseInt(editTextUserId.getText().toString());
                    Contato contato = recuperarUsuarios (id);

                    if (contato != null)
                    {
                        editTextNome.setText(contato.getNome());
                        editTextEmail.setText(contato.getEmail());
                    }
                    else
                        Toast.makeText(getContext().getApplicationContext(), "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getContext().getApplicationContext(), "Favor digitar um id.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonAtualizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();
                if(!nome.equals("") && !email.equals("")){
                    //int id = Integer.parseInt(editTextUserId.getText().toString());
                    Contato contato = new Contato(nome, email);
                    //contato.setId(id);
                    atualizaUsuarios (contato);
                    Toast.makeText(getContext().getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    editTextNome.setText("");
                    editTextEmail.setText("");
                }
                else
                    Toast.makeText(getContext().getApplicationContext(), "Favor preencher os campos", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDeletar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!editTextUserId.getText().toString().equals("")){
                    int id = Integer.parseInt(editTextUserId.getText().toString());
                    deleteUsuarios (id);
                    Toast.makeText(getContext().getApplicationContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                    editTextNome.setText("");
                    editTextEmail.setText("");
                }
                else
                    Toast.makeText(getContext().getApplicationContext(), "Digite o id", Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }

    private Contato recuperarUsuarios (int id)
    {
        Contato contato = null;
        try
        {
            SQLiteDatabase bancoDeDados = getContext().getApplicationContext().openOrCreateDatabase("bancoContatos" , MODE_PRIVATE, null);

            Cursor cursor = bancoDeDados.rawQuery("SELECT id, nome, email FROM Contato WHERE id = " + String.valueOf(id), null);
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceEmail = cursor.getColumnIndex("email");

            cursor.moveToFirst();
            while (cursor != null)
            {
                contato = new Contato (cursor.getString(indiceNome), cursor.getString(indiceEmail));
                contato.setId(Integer.parseInt(cursor.getString(indiceId)));
                cursor.moveToNext(); //move para o próximo registro
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return contato;
    }

    private void atualizaUsuarios (Contato contato)
    {
        try
        {
            SQLiteDatabase bancoDeDados = getContext().getApplicationContext().openOrCreateDatabase("bancoContatos" , MODE_PRIVATE, null);
            String update = "UPDATE Contato " + "SET nome = '" + contato.getNome() + "', " + "email = '" + contato.getEmail() + "' " + "WHERE id = " + contato.getId();
            bancoDeDados.execSQL(update);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//end atualizaUsuarios()

    private void deleteUsuarios (int id)
    {
        try
        {
            SQLiteDatabase bancoDeDados = getContext().getApplicationContext().openOrCreateDatabase("bancoContatos" , MODE_PRIVATE, null);
            String delete = "DELETE FROM Contato " + "WHERE id = " + id;
            bancoDeDados.execSQL(delete);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//end deleteUsuarios()
}
