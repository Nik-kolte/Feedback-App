package com.example.nikko.myapplication;

import java.lang.String;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.*;


public class Main3Activity extends AppCompatActivity {
    Button b;
    EditText eId,ePassword;
    //TextView tv;
    private String username,password;
    //Need to create object for button and then link it to next page
   /* private boolean checkInDatabase(String username, String password, Context context)
    {
     //          SQLiteDatabase db =
        //will update after the query knowledge of SQLite
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        b = (Button) findViewById(R.id.button);      //CLICK CHECK

        b.setOnClickListener(new View.OnClickListener(){
            @Override
     public void onClick(View v){
        eId = (EditText) findViewById(R.id.teacherid); //complete the identifier error--
        ePassword = (EditText) findViewById(R.id.teacherpass); //complete the identifier
        username = eId.getText().toString();
        password = ePassword.getText().toString();

                if(username.equals("")||username==null){
                    Toast.makeText( getApplicationContext(),"Invalid ID",Toast.LENGTH_SHORT).show();
                }
                else if(password.equals("") || password == null){
                    Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
                }
                else
                {
                      //  boolean checkLogin = checkInDatabase(username , password, Main3Activity.this);
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent stud = new Intent(Main3Activity.this,Main5Activity.class);   //Linking welcome screen to Teacher Login Screen
                    startActivity(stud);
                }


     /*   if(checkInDatabase(usern,pass)) {  //onCLICK to next activity page
        }
        else {
            //same activity page with notification
        }*/
    }});

}
    @Override
    public boolean onCreateOptionsMenu(Menu manu){
        getMenuInflater().inflate(R.menu.main , manu); //creating menu in action bar
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
        if(id==R.id.id_exit)
        {
            finish();
            System.exit(0);
            return true;
        }
        return true;
    }
}
