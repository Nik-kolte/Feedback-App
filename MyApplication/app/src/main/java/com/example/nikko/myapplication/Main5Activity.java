package com.example.nikko.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main5Activity extends AppCompatActivity {

    TextView TV_teachname;
    Spinner sp_year,sp_branch;
    String roll_no_like;
    ListView lv;
    ArrayAdapter<String> barray,yarray;
    String[] b_array,y_array;
    Button search,comments;
    private String s_year,s_branch;
    private TextView TVques1,TVques2,TVques3,TVques4,TVques5,TVques6,TVques7,TVques8,TVques9,TVques10,
    TV_ans1,TV_ans2,TV_ans3,TV_ans4,TV_ans5,TV_ans6,TV_ans7,TV_ans8,TV_ans9,no_comments;
    static int b_position=0,y_position=0;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        TV_teachname = (TextView)findViewById(R.id.teach_name);
        TV_teachname.setText(GlobalClass.t_name);
        search = (Button)findViewById(R.id.Go);
        sp_branch= (Spinner) findViewById(R.id.sp_branch);
        sp_year= (Spinner) findViewById(R.id.sp_year);
        TVques1 =(TextView)findViewById(R.id.ques_1);
        TVques2 =(TextView)findViewById(R.id.ques_2);
        TVques3 =(TextView)findViewById(R.id.ques_3);
        TVques4 =(TextView)findViewById(R.id.ques_4);
        TVques5 =(TextView)findViewById(R.id.ques_5);
        TVques6 =(TextView)findViewById(R.id.ques_6);
        TVques7 =(TextView)findViewById(R.id.ques_7);
        TVques8 =(TextView)findViewById(R.id.ques_8);
        TVques9 =(TextView)findViewById(R.id.ques_9);
        TV_ans1 = (TextView)findViewById(R.id.ans1);
        TV_ans2 = (TextView)findViewById(R.id.ans2);
        TV_ans3 = (TextView)findViewById(R.id.ans3);
        TV_ans4 = (TextView)findViewById(R.id.ans4);
        TV_ans5 = (TextView)findViewById(R.id.ans5);
        TV_ans6 = (TextView)findViewById(R.id.ans6);
        TV_ans7 = (TextView)findViewById(R.id.ans7);
        TV_ans8 = (TextView)findViewById(R.id.ans8);
        TV_ans9 = (TextView)findViewById(R.id.ans9);
        TVques10 =(TextView)findViewById(R.id.ques_10);
        no_comments = (TextView)findViewById(R.id.no_comments);
        comments = (Button)findViewById(R.id.comments);

        display();
        GlobalClass.t_branch_count=1;
        if (GlobalClass.t_branch2!="null") {
            GlobalClass.t_branch_count++;
        }
        if (GlobalClass.t_branch3!="null") {
            GlobalClass.t_branch_count++;
        }

        if(GlobalClass.t_FE ==1){
            GlobalClass.t_year_count++;
        }
        if(GlobalClass.t_SE ==1){
            GlobalClass.t_year_count++;
        }
        if(GlobalClass.t_TE ==1){
            GlobalClass.t_year_count++;
        }
        if(GlobalClass.t_BE ==1){
            GlobalClass.t_year_count++;
        }
        b_array = new String[GlobalClass.t_branch_count+1];
        y_array = new String[GlobalClass.t_year_count+1];
        if(GlobalClass.t_branch_count==1)
        {
            b_array[0]="Branch";
            b_array[1]=GlobalClass.t_branch1;
        }
        if(GlobalClass.t_branch_count==2)
        {
            b_array[0]="Branch";
            b_array[1]=GlobalClass.t_branch1;
            b_array[2]=GlobalClass.t_branch2;
        }
        if(GlobalClass.t_branch_count==3)
        {
            b_array[0]="Branch";
            b_array[1]=GlobalClass.t_branch1;
            b_array[2]=GlobalClass.t_branch2;
            b_array[3]=GlobalClass.t_branch3;
        }

        y_array[0]="Year";
        if(GlobalClass.t_FE ==1){
            y_array[i] = "FE";
            i++;
        }
        if(GlobalClass.t_SE ==1){
            y_array[i] = "SE";
            i++;
        }
        if(GlobalClass.t_TE ==1){
            y_array[i] = "TE";
            i++;
        }
        if(GlobalClass.t_BE ==1){
            y_array[i] = "BE";
        }
        yarray = new ArrayAdapter<String>(Main5Activity.this, android.R.layout.simple_list_item_1, y_array); //created adapter and received yname array from res file
        yarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  //Set type of Spinner Adapter
        sp_year.setAdapter(yarray);  //Called Spinner using the adapter
        sp_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                y_position=adapterView.getSelectedItemPosition();
                if(y_position==0){
                    search.setVisibility(View.GONE);
                }
                else{
                    if(b_position!=0){
                        search.setVisibility(View.VISIBLE);
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        barray = new ArrayAdapter<String>(Main5Activity.this, android.R.layout.simple_list_item_1, b_array); //created adapter and received yname array from res file
        barray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  //Set type of Spinner Adapter
        sp_branch.setAdapter(barray);  //Called Spinner using the adapter
        sp_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                b_position=adapterView.getSelectedItemPosition();
                if(b_position==0){
                    search.setVisibility(View.GONE);
                }
                else{
                    if(y_position!=0){
                        search.setVisibility(View.VISIBLE);
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void fsearch(View view)
    {
        //save button
        s_year = sp_year.getSelectedItem().toString();
        s_branch = sp_branch.getSelectedItem().toString();


        if(s_year.equals("FE")){
            if(s_branch.equals("CS")){
                roll_no_like = "FC";
            }
            if(s_branch.equals("IT")){
                roll_no_like = "FI";
            }
            if(s_branch.equals("ETC")){
                roll_no_like = "FE";
            }
        }
        if(s_year.equals("SE")){
            if(s_branch.equals("CS")){
                roll_no_like = "SC";
            }
            if(s_branch.equals("IT")){
                roll_no_like = "SI";
            }
            if(s_branch.equals("ETC")){
                roll_no_like = "SE";
            }
        }
        if(s_year.equals("TE")){
            if(s_branch.equals("CS")){
                roll_no_like = "TC";
            }
            if(s_branch.equals("IT")){
                roll_no_like = "TI";
            }
            if(s_branch.equals("ETC")){
                roll_no_like = "TE";
            }
        }
        if(s_year.equals("BE")){
            if(s_branch.equals("CS")){
                roll_no_like = "BC";
            }
            if(s_branch.equals("IT")){
                roll_no_like = "BI";
            }
            if(s_branch.equals("ETC")){
                roll_no_like = "BE";
            }
        }

        String URL_ROOT = "http://"+GlobalClass.URL+"/fed_app/teacher_feedback.php";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest =  new StringRequest(Request.Method.POST,URL_ROOT,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null)
                {
                    System.out.println(response);
                }
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    GlobalClass.question10 = new String[jsonArray.length()-1];
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    if (code.equals("successful")) {
                        TV_ans1.setText(jsonObject.getString("ans1"));
                        if(jsonObject.getString("ans1").equals("null")){
                            TV_ans1.setText("-");
                        }

                        TV_ans2.setText(jsonObject.getString("ans2"));
                        if(jsonObject.getString("ans2").equals("null")){
                            TV_ans2.setText("-");
                        }
                        TV_ans3.setText(jsonObject.getString("ans3"));
                        if(jsonObject.getString("ans3").equals("null")){
                            TV_ans3.setText("-");
                        }
                        TV_ans4.setText(jsonObject.getString("ans4"));
                        if(jsonObject.getString("ans4").equals("null")){
                            TV_ans4.setText("-");
                        }
                        TV_ans5.setText(jsonObject.getString("ans5"));
                        if(jsonObject.getString("ans5").equals("null")){
                            TV_ans5.setText("-");
                        }
                        TV_ans6.setText(jsonObject.getString("ans6"));
                        if(jsonObject.getString("ans6").equals("null")){
                            TV_ans6.setText("-");
                        }
                        TV_ans7.setText(jsonObject.getString("ans7"));
                        if(jsonObject.getString("ans7").equals("null")){
                            TV_ans7.setText("-");
                        }
                        TV_ans8.setText(jsonObject.getString("ans8"));
                        if(jsonObject.getString("ans8").equals("null")){
                            TV_ans8.setText("-");
                        }
                        TV_ans9.setText(jsonObject.getString("ans9"));
                        if(jsonObject.getString("ans9").equals("null")){
                            TV_ans9.setText("-");
                        }

                        if(jsonArray.length()>1){
                            comments.setVisibility(View.VISIBLE);
                            no_comments.setVisibility(View.GONE);

                        for(int i=1;i<jsonArray.length();i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);
                            GlobalClass.question10[i - 1] = jsonObject.getString("ques10");
                        }
                        }
                        else{
                            no_comments.setVisibility(View.VISIBLE);
                            comments.setVisibility(View.GONE);
                        }
                    } else {
                        Toast.makeText(Main5Activity.this, code, Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError){
                        System.out.println(String.valueOf(volleyError));

                    }})
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // return super.getParams();
                Map<String, String> params = new HashMap<String, String>();
                params.put("rollno_like", roll_no_like.toUpperCase());
                params.put("teacher_id", GlobalClass.t_teacher_id);
                return params;
            }
        };
        queue.add(stringRequest);

    }

    public void display(){
        String URL_ROOT = "http://"+GlobalClass.URL+"/fed_app/questions.php";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest =  new StringRequest(Request.Method.POST,URL_ROOT,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null)
                {
                    System.out.println(response);
                }
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")) {
                        TVques1.setText("1. "+jsonObject.getString("ques1"));
                        TVques2.setText("2. "+jsonObject.getString("ques2"));
                        TVques3.setText("3. "+jsonObject.getString("ques3"));
                        TVques4.setText("4. "+jsonObject.getString("ques4"));
                        TVques5.setText("5. "+jsonObject.getString("ques5"));
                        TVques6.setText("6. "+jsonObject.getString("ques6"));
                        TVques7.setText("7. "+jsonObject.getString("ques7"));
                        TVques8.setText("8. "+jsonObject.getString("ques8"));
                        TVques9.setText("9. "+jsonObject.getString("ques9"));
                        TVques10.setText("10. "+jsonObject.getString("ques10"));
                    } else {
                        Toast.makeText(Main5Activity.this, code, Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError){
                        System.out.println(String.valueOf(volleyError));


                    }})
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // return super.getParams();
                Map<String, String> params = new HashMap<String, String>();
                return super.getParams();
            }
        };
        queue.add(stringRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu manu){
        getMenuInflater().inflate(R.menu.teach_fb , manu); //creating menu in action bar
        return true;
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Main5Activity.this);
        builder.setMessage("Do you want to Logout?")
                .setPositiveButton("Ok",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent stud = new Intent(Main5Activity.this,Main3Activity.class);   //back button=login screen
                        startActivity(stud);
                        finish();
                    }
                })
                .setNegativeButton("Cancel",null);
        AlertDialog alert= builder.create();
        alert.show();
    }

    public void comments(View view){
        Intent i = new Intent(this,view_comments.class);
        startActivity(i);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){    //programming the items inside menu
        int id=item.getItemId();

        if(id==R.id.id_changepass)
        {
            Toast.makeText( getApplicationContext(),"In development",Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id==R.id.id_logout)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(Main5Activity.this);
            builder.setMessage("Do want to Logout?")
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent stud = new Intent(Main5Activity.this,Main3Activity.class);   //back button=login screen
                            startActivity(stud);
                            finish();
                        }
                    })
                    .setNegativeButton("Cancel",null);
            AlertDialog alert= builder.create();
            alert.show();
        }
        return true;
    }


}
