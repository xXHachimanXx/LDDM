package com.example.clickjogos;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment
{
    private static final String TAG = "SonsAnimaisActivicty";
    private Map<Integer, String> animaisMap;
    private String[] animals = {"ape", "cat", "cow", "dog", "duck", "elephant",
            "horse", "lion", "moose", "owl", "pig", "rooster", "sheep", "tiger", "turkey", "zebra"};
    private ImageView[] animalsImages = new ImageView[animals.length];


    public FirstFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        animaisMap = new HashMap<>();
        for (int y = 0; y < animals.length; y++)
        {
            animalsImages[y] = view.findViewById(getIdByName(animals[y],"id"));
            animalsImages[y].setOnClickListener((View.OnClickListener) this);
            animaisMap.put(getIdByName(
                    animals[y], "id"), animals[y]);
        }
        final MediaPlayer mp = (MediaPlayer) MediaPlayer.create(view.getContext(), getIdByName("cow", "raw"));

        return view;
    }

    public void onClick(View view)
    {
        //Toast.makeText(getApplicationContext(), mapButtonId.get(v.getId()), Toast.LENGTH_LONG).show();
        MediaPlayer mp = MediaPlayer.create(view.getContext(), getIdByName(animaisMap.get(view.getId()), "raw"));
        if (mp != null) {
            mp.start();
        }
    }


    public int getIdByName(String name, String resource) {

        return getResources().getIdentifier(name, resource, getContext().getPackageName());
    }
}
