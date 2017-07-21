package com.example.nikko.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class Main2Activity extends AppCompatActivity {

    Button proc_btn ;
    EditText s_fname,s_lname,r_num;
    String s_branch,s_year,st_fname,st_lname,rollno;
    Spinner spinner1,spinner2;
    ArrayAdapter<String> barray,yarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        s_fname = (EditText)findViewById(R.id.f_name) ;
        s_lname = (EditText)findViewById(R.id.l_name) ;
        r_num = (EditText)findViewById(R.id.r_no) ;
        proc_btn =(Button) findViewById(R.id.proceed);

        spinner1= (Spinner) findViewById(R.id.sp2); //Object for Branch drop down /Spinner created
        barray = new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.bname)); //created adapter and received bname array from res file
        barray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  //Set type of Spinner Adapter
        spinner1.setAdapter(barray); //Called Spinner using the adapter
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                s_branch = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinner2= (Spinner) findViewById(R.id.sp1);  //Object for Branch drop down /Spinner created
        yarray = new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.yname)); //created adapter and received yname array from res file
        barray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  //Set type of Spinner Adapter
        spinner2.setAdapter(yarray);  //Called Spinner using the adapter
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s_year = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(getBaseContext(),s_year+" Selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        proc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                st_fname = s_fname.getText().toString();
                st_lname = s_lname.getText().toString();
                rollno = r_num.getText().toString();
                if(st_fname.equals("")||st_fname==null){
                    Toast.makeText( getApplicationContext(),"Invalid First Name",Toast.LENGTH_SHORT).show();
                }
                else if(st_lname.equals("") || st_lname == null){
                    Toast.makeText(getApplicationContext(),"Invalid Last Name",Toast.LENGTH_SHORT).show();
                }
                else if(rollno.equals("") || rollno == null){
                    Toast.makeText(getApplicationContext(),"Invalid Roll Number",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Register Successful",Toast.LENGTH_SHORT).show();
                    Intent stud = new Intent(Main2Activity.this,Main4Activity.class);   //Linking student details to feedback
                    startActivity(stud);
                }
            }
        });
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
