package com.example.clickjogos;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SonsAnimaisActivity extends AppCompatActivity implements View.OnClickListener
{
    private String animais[] = {"ape", "cat", "cow", "dog", "duck", "elephant",
            "horse", "lion", "moose", "owl", "pig", "rooster"};
    private Button btnAnimais[] = new Button[animais.length];
    private Map<Integer,String> mapButtonId = new HashMap<Integer,String>();


    public void onClick(View v)
    {
        Toast.makeText(getApplicationContext(), mapButtonId.get(v.getId()), Toast.LENGTH_LONG).show();
        MediaPlayer mp = MediaPlayer.create(SonsAnimaisActivity.this, getIdByName(mapButtonId.get(v.getId()), "raw"));
        if(mp !=null) { mp.start(); }
    }

    public int getIdByName(String name, String recource) 
    {
        return getResources().getIdentifier(name, recource, getPackageName());
    }


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sons_animais);

        for(int i=0; i<animais.length;i++)
        {
            btnAnimais[i] = (Button) findViewById(getIdByName(animais[i] + "Id","id"));
            btnAnimais[i].setOnClickListener((View.OnClickListener) this);
            mapButtonId.put(getIdByName(animais[i] + "Id","id"), animais[i] );
        }
    }    

}
