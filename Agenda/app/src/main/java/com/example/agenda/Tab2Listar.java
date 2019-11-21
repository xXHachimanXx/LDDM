package com.example.agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Tab2Listar extends Fragment
{
    private ListView listView;
    private Button botaoAtualizar;
    private ArrayAdapter<Contato> adapter;

    private DatabaseReference contatoDatabaseReference = FirebaseDatabase.getInstance().getReference().child("contatos");

    private List<Contato> contatos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.tab2_listar, container, false);

        //INICIALIZAR COMPONENTES
        listView = rootView.findViewById(R.id.listView_id);
        contatos = new ArrayList<>();
        botaoAtualizar = rootView.findViewById(R.id.button_atualizar2);

        recuperarUsuarios();

        adapter = new ArrayAdapter<Contato>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1, contatos);

        //O listView não pode adicionar um tipo List, mas podemos adicionar um tipo adapter (que contem nossa lista de contatos)
        listView.setAdapter(adapter);

        botaoAtualizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                recuperarUsuarios();
            }
        });

        return rootView;
    }

    private void recuperarUsuarios(){
        contatoDatabaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                contatos = new ArrayList<>();
                adapter = new ArrayAdapter<Contato>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1, contatos);

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Contato contato = postSnapshot.getValue(Contato.class);
                    contatos.add(contato);

                    adapter = new ArrayAdapter<Contato>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1, contatos);
                    listView.setAdapter(adapter);
                }//end for

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(getContext().getApplicationContext(), "Erro: falha na busca", Toast.LENGTH_SHORT).show();
            }
        });

    }

}//end class Tab2Listar
