package com.example.clickjogos;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import java.util.HashMap;
import java.util.Map;

public class SonsAnimaisActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SonsAnimaisActivicty";

    private Map<Integer, String> animaisMap;

    private String[] animals = {"ape", "cat", "cow", "dog", "duck", "elephant",
            "horse", "lion", "moose", "owl", "pig", "rooster", "sheep", "tiger", "turkey", "zebra"};
    private ImageView[] animalsImages = new ImageView[animals.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sons_animais);

        animaisMap = new HashMap<>();
        for (int y = 0; y < animals.length; y++) {
            animalsImages[y] = findViewById(getIdByName(animals[y], "id"));
            animalsImages[y].setOnClickListener(this);
            animaisMap.put(getIdByName(
                    animals[y], "id"), animals[y]);
        }
        final MediaPlayer mp = MediaPlayer.create(SonsAnimaisActivity.this, getIdByName("cow", "raw"));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            NavUtils.navigateUpTo(this, intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        MediaPlayer mp = MediaPlayer.create(SonsAnimaisActivity.this,
                getIdByName(animaisMap.get(v.getId()),
                        "raw"));
        if (mp != null) {
            mp.start();
        }
    }


    public int getIdByName(String name, String resource) {
        return getResources().getIdentifier(name, resource, getPackageName());
    }


}
