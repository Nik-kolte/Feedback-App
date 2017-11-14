package com.example.nikko.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public Button but1;
    public Button but2;

    public void Init()
    {
        but1= (Button) findViewById(R.id.Students); //Student button widget's object created
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // Toast.makeText(MainActivity.this,"This Option is disabled for now",Toast.LENGTH_SHORT).show();
                Intent stud = new Intent(MainActivity.this,Main2Activity.class);   //Linking welcome screen to Student Screen
                startActivity(stud);
                finish();
            }
        });

        but2= (Button) findViewById(R.id.Teachers); //Teacher button widget's object created
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stud = new Intent(MainActivity.this,Main3Activity.class);   //Linking welcome screen to Teacher Login Screen
                startActivity(stud);
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        /*Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/");
        tv.setTypeface(myCustomFont);*/
        }

    public void guestButton(View view){
        Intent i = new Intent(this,guest_login.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu manu){
        getMenuInflater().inflate(R.menu.main_screen , manu); //creating menu in action bar
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){    //programming the items inside menu
        int id=item.getItemId();

        if(id==R.id.id_aboutapp)
        {
            Intent i = new Intent(this,About_activity.class);
            startActivity(i);
            return true;
        }
        return true;
    }


}
