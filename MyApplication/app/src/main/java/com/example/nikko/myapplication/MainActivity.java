package com.example.nikko.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    public Button but1;
    public Button but2;

    public void Init()
    {
        but1= (Button) findViewById(R.id.Students); //Student button widget's object created
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stud = new Intent(MainActivity.this,Main2Activity.class);   //Linking welcome screen to Student Screen
                startActivity(stud);
            }
        });

        but2= (Button) findViewById(R.id.Teachers); //Teacher button widget's object created
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stud = new Intent(MainActivity.this,Main3Activity.class);   //Linking welcome screen to Teacher Login Screen
                startActivity(stud);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        Init();
    }
}
