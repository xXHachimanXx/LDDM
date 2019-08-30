package com.example.clickjogos;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Tela2 extends AppCompatActivity {
    private MediaPlayer mp;
    private ImageView imgCao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        imgCao = findViewById(R.id.ape);
        mp = MediaPlayer.create(Tela2.this, R.raw.ape);

        imgCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });
    }
}
