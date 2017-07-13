package com.example.nikko.myapplication;

import java.lang.String;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    Button b;
    EditText eId,ePassword;
    //TextView tv;
    private String usern,pass;
    //Need to create object for button and then link it to next page
/*    private boolean checkInDatabase(String username,String password)
    {
        //will update after the query knowledge of SQLite
    }

*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        b = (Button) findViewById(R.id.button);      //CLICK CHECK
        //tv= (TextView) findViewById(R.id.display);   //DISPLAY CHECK*

        b.setOnClickListener(new View.OnClickListener(){
            @Override
     public void onClick(View v){
        eId = (EditText) findViewById(R.id.teacherid); //complete the identifier error--
        ePassword = (EditText) findViewById(R.id.teacherpass); //complete the identifier
        usern = eId.getText().toString();
        pass = ePassword.getText().toString();
     /*   if(checkInDatabase(usern,pass)) {  //onCLICK to next activity page
        }
        else {
            //same activity page with notification
        }*/
    }});

}}
