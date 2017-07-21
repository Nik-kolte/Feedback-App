package com.example.nikko.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu manu){
        getMenuInflater().inflate(R.menu.teach_fb , manu); //creating menu in action bar
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){    //programming the items inside menu
        int id=item.getItemId();

        if(id==R.id.id_aboutapp)
        {
            Toast.makeText( getApplicationContext(),"About App Selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id==R.id.id_changepass)
        {
            Toast.makeText( getApplicationContext(),"Change Password Selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id==R.id.id_logout)
        {
            Toast.makeText( getApplicationContext(),"Logout Selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        return true;
    }

}
