package com.example.aula03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
    private Button btnClica;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClica = findViewById(R.id.btnId);

        btnClica.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

    }//end onCreate()

    public void print(String str)
    {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        print("On Start");
        super.onStart();
    }

    @Override
    protected void onPause() {
        print("On Pause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        print("On Stop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        print("On restart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        print("On destroy");
        super.onDestroy();
    }
}
