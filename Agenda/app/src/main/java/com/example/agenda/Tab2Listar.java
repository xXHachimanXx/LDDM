package com.example.agenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Tab2Listar extends Fragment
{
    private ListView listView;
    private Button botaoAtualizar;
    private ArrayAdapter<Contato> adapter;
    private List<Contato> contatos;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference contatoDatabaseReference = databaseReference.child("Contatos");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.tab2_listar, container, false);
        listView = rootView.findViewById(R.id.listView_id);

        botaoAtualizar = rootView.findViewById(R.id.button_atualizar2);
        contatos = recuperarUsuarios ();
        adapter = new ArrayAdapter<Contato>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1, contatos);

        //O listView não pode adicnar um tipo List, mas podemos adicionar um tipo adapter (que contem nossa lista de contatos)
        listView.setAdapter(adapter);

        botaoAtualizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listView.setAdapter(null);

                contatoDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener()
                {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot)
                   {
                       List<Contato> contatos = new ArrayList<Contato>();
                       int num = 0;
                       for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                       {
                           Contato contato = new Contato();

                           contato.setNome(postSnapshot.child("nome").getValue().toString());

                           contato.setEmail(postSnapshot.child("email").getValue().toString());

                           contato.setCep(postSnapshot.child("cep").getValue().toString());

                           contato.setEndereco(postSnapshot.child("endereco").getValue().toString());
                           contatos.add(contato);
                           contato = null;
                       }//end for

                       adapter = new ArrayAdapter<Contato>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1, contatos);
                       listView.setAdapter(adapter);
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {
                   }
               });
            }
        });

        return rootView;
    }


    private List<Contato> recuperarUsuarios ()
    {
        Contato contato;
        List<Contato> contatos = new ArrayList<Contato>();
        try
        {
            SQLiteDatabase bancoDeDados = getContext().getApplicationContext().openOrCreateDatabase("bancoContatos" , MODE_PRIVATE, null);
            //recuperar dados da tabela
            Cursor cursor = bancoDeDados.rawQuery("SELECT id, nome, email FROM Contato", null);

            //recuperando o índice das colunas (o primeiro campo é 0, o segundo é 1)
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceEmail = cursor.getColumnIndex("email");

            //o cursos se move do primeiro item ao último a medida em que a leitura ocorre
            cursor.moveToFirst();
            while (cursor != null)
            {
                contato = new Contato (cursor.getString(indiceNome), cursor.getString(indiceEmail));
                contato.setId(Integer.parseInt(cursor.getString(indiceId)));
                contatos.add(contato);
                contato = null;
                cursor.moveToNext(); //move para o próximo registro
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return contatos;

    }//end recuperarUsuarios()


}//end class Tab2Listar
