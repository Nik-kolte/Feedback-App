package com.example.nikko.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Spinner spinner1= (Spinner) findViewById(R.id.sp2); //Object for Branch drop down /Spinner created
        ArrayAdapter<String> barray = new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.bname)); //created adapter and received bname array from res file
        barray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  //Set type of Spinner Adapter
        spinner1.setAdapter(barray); //Called Spinner using the adapter

        Spinner spinner2= (Spinner) findViewById(R.id.sp1);  //Object for Branch drop down /Spinner created
        ArrayAdapter<String> yarray = new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.yname)); //created adapter and received yname array from res file
        barray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  //Set type of Spinner Adapter
        spinner2.setAdapter(yarray);  //Called Spinner using the adapter
    }
}
