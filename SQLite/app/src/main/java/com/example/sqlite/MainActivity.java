package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            //Criar banco de dados
            SQLiteDatabase bancoDeDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Criar tabela
            bancoDeDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR, idade INT(3))");

            //Inserir dados
            bancoDeDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Roniuson', 30)");
            bancoDeDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Uelerson', 21)");
            bancoDeDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Cleiton', 78)");

            //Recuperar dados da tabela
            Cursor cursor = bancoDeDados.rawQuery("SELECT nome, idade FROM pessoas", null);

            cursor.moveToFirst(); //garantir que o cursor está no início

            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            while(cursor != null)
            {
                Log.i("RESULTADO - nome: ", cursor.getString(indiceNome));
                Log.i("RESULTADO - idade: ", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }//end while

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }//end try-catch

    }//end OnCreate()

}
