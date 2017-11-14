package com.example.nikko.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class guest_feedback extends AppCompatActivity {
    //Student feedback page
    TextView TV_ques1,TV_ques2,TV_ques3,TV_ques4,TV_ques5,TV_ques6,TV_ques7,TV_ques8,TV_ques9,TV_ques10;
    EditText ET_ans1,ET_ans2,ET_ans3,ET_ans4,ET_ans5,ET_ans6,ET_ans7,ET_ans8,ET_ans9,ET_ans10;
    String st_ans10;
    Spinner sp_teacher;

    ScrollView scroll;
    String[] teacher_name_array,teacher_id_array,teacher_n_array,teacher_i_array;
    static int teacher_index,teacher_count;
    ArrayAdapter<String> tarray;

    String ans1,ans2,ans3,ans4,ans5,ans6,ans7,ans8,ans9,ans10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_feedback);
        TV_ques1 =(TextView)findViewById(R.id.ques1);
        TV_ques2 =(TextView)findViewById(R.id.ques2);
        TV_ques3 =(TextView)findViewById(R.id.ques3);
        TV_ques4 =(TextView)findViewById(R.id.ques4);
        TV_ques5 =(TextView)findViewById(R.id.ques5);
        TV_ques6 =(TextView)findViewById(R.id.ques6);
        TV_ques7 =(TextView)findViewById(R.id.ques7);
        TV_ques8 =(TextView)findViewById(R.id.ques8);
        TV_ques9 =(TextView)findViewById(R.id.ques9);
        TV_ques10 =(TextView)findViewById(R.id.ques10);
        ET_ans1 = (EditText)findViewById(R.id.ans1);
        ET_ans2 = (EditText)findViewById(R.id.ans2);
        ET_ans3 = (EditText)findViewById(R.id.ans3);
        ET_ans4 = (EditText)findViewById(R.id.ans4);
        ET_ans5 = (EditText)findViewById(R.id.ans5);
        ET_ans6 = (EditText)findViewById(R.id.ans6);
        ET_ans7 = (EditText)findViewById(R.id.ans7);
        ET_ans8 = (EditText)findViewById(R.id.ans8);
        ET_ans9 = (EditText)findViewById(R.id.ans9);
        ET_ans10 = (EditText)findViewById(R.id.ans10);
        sp_teacher = (Spinner)findViewById(R.id.spinner1);
        scroll= (ScrollView)findViewById(R.id.scroll);

        display();
        teacher_array();

    }
    public void feedSave(View view)
    {
        ans1 = ET_ans1.getText().toString();
        if (TextUtils.isEmpty(ans1)) {
            ET_ans1.setError("Please enter value between 1-10");
            ET_ans1.requestFocus();
            Toast.makeText(guest_feedback.this, "Check Answer 1", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            int i = Integer.parseInt(ans1);
            if(i>10||i==0){
                ET_ans1.setError("Please enter value between 1-10");
                ET_ans9.requestFocus();
                Toast.makeText(guest_feedback.this, "Check Answer 1", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ans2 = ET_ans2.getText().toString();
        if (TextUtils.isEmpty(ans2)) {
            ET_ans2.setError("Please enter value between 1-10");
            ET_ans2.requestFocus();
            Toast.makeText(guest_feedback.this, "Check Answer 2", Toast.LENGTH_SHORT).show();
            return;}
        else{
            int i = Integer.parseInt(ans2);
            if(i>10||i==0){
                ET_ans2.setError("Please enter value between 1-10");
                ET_ans9.requestFocus();
                Toast.makeText(guest_feedback.this, "Check Answer 2", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ans3 = ET_ans3.getText().toString();
        if (TextUtils.isEmpty(ans3)) {
            ET_ans3.setError("Please enter value between 1-10");
            ET_ans3.requestFocus();
            Toast.makeText(guest_feedback.this, "Check Answer 3", Toast.LENGTH_SHORT).show();
            return;}
        else{
            int i = Integer.parseInt(ans3);
            if(i>10||i==0){
                ET_ans3.setError("Please enter value between 1-10");
                ET_ans9.requestFocus();
                Toast.makeText(guest_feedback.this, "Check Answer 3", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ans4 = ET_ans4.getText().toString();
        if (TextUtils.isEmpty(ans4)) {
            ET_ans4.setError("Please enter value between 1-10");
            ET_ans4.requestFocus();
            Toast.makeText(guest_feedback.this, "Check Answer 4", Toast.LENGTH_SHORT).show();
            return;}
        else{
            int i = Integer.parseInt(ans4);
            if(i>10||i==0){
                ET_ans4.setError("Please enter value between 1-10");
                ET_ans9.requestFocus();
                Toast.makeText(guest_feedback.this, "Check Answer 4", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ans5 = ET_ans5.getText().toString();
        if (TextUtils.isEmpty(ans5)) {
            ET_ans5.setError("Please enter value between 1-10");
            ET_ans5.requestFocus();
            Toast.makeText(guest_feedback.this, "Check Answer 5", Toast.LENGTH_SHORT).show();
            return;}
        else{
            int i = Integer.parseInt(ans5);
            if(i>10||i==0){
                ET_ans5.setError("Please enter value between 1-10");
                ET_ans9.requestFocus();
                Toast.makeText(guest_feedback.this, "Check Answer 5", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ans6 = ET_ans6.getText().toString();
        if (TextUtils.isEmpty(ans6)) {
            ET_ans6.setError("Please enter value between 1-10");
            ET_ans6.requestFocus();
            Toast.makeText(guest_feedback.this, "Check Answer 6", Toast.LENGTH_SHORT).show();
            return;}
        else{
            int i = Integer.parseInt(ans6);
            if(i>10||i==0){
                ET_ans6.setError("Please enter value between 1-10");
                ET_ans9.requestFocus();
                Toast.makeText(guest_feedback.this, "Check Answer 6", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ans7 = ET_ans7.getText().toString();
        if (TextUtils.isEmpty(ans7)) {
            ET_ans7.setError("Please enter value between 1-10");
            ET_ans7.requestFocus();
            Toast.makeText(guest_feedback.this, "Check Answer 7", Toast.LENGTH_SHORT).show();
            return;}
        else{
            int i = Integer.parseInt(ans7);
            if(i>10||i==0){
                ET_ans7.setError("Please enter value between 1-10");
                ET_ans9.requestFocus();
                Toast.makeText(guest_feedback.this, "Check Answer 7", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ans8 = ET_ans8.getText().toString();
        if (TextUtils.isEmpty(ans8)) {
            ET_ans8.setError("Please enter value between 1-10");
            ET_ans8.requestFocus();
            Toast.makeText(guest_feedback.this, "Check Answer 8", Toast.LENGTH_SHORT).show();

            return;}
        else{
            int i = Integer.parseInt(ans8);
            if(i>10||i==0){
                ET_ans8.setError("Please enter value between 1-10");
                ET_ans9.requestFocus();
                Toast.makeText(guest_feedback.this, "Check Answer 8", Toast.LENGTH_SHORT).show();
                return;
            }

        }
        ans9 = ET_ans9.getText().toString();
        if (TextUtils.isEmpty(ans9)) {
            ET_ans9.setError("Please enter value between 1-10");
            ET_ans9.requestFocus();
            Toast.makeText(guest_feedback.this, "Check Answer 9", Toast.LENGTH_SHORT).show();
            return;}
        else{
            int i = Integer.parseInt(ans9);
            if(i>10||i==0){
                ET_ans9.setError("Please enter value between 1-10");
                ET_ans9.requestFocus();
                Toast.makeText(guest_feedback.this, "Check Answer 9", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ans10 = ET_ans10.getText().toString();
        if (TextUtils.isEmpty(ans9)) {

            return;}
        else{
            if(ans10.length()>150){
                ET_ans9.setError("Max 150 characters");
                ET_ans9.requestFocus();
                Toast.makeText(guest_feedback.this, "Check Answer 10", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        feedback_send();
        //save button
    }

    public void feedback_send() {
        //if successful send to Mainactivity
        String URL_ROOT = "http://" + GlobalClass.URL + "/fed_app/feedback_send.php";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ROOT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    System.out.println(response);
                }
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    if (code.equals("Feedback Successful")) {
                        store_feed_count_guest(get_feed_count_guest() + 1);
                        set_registerbit_guest(0);
                        Toast.makeText(guest_feedback.this, "Feedback for " + teacher_name_array[teacher_index] + " Submitted", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(guest_feedback.this, code, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println(String.valueOf(volleyError));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // return super.getParams();
                Map<String, String> params = new HashMap<String, String>();
                params.put("teacher_id", teacher_id_array[teacher_index]);
                params.put("roll_no", get_rollno_guest().toUpperCase());
                params.put("question1", ans1);
                params.put("question2", ans2);
                params.put("question3", ans3);
                params.put("question4", ans4);
                params.put("question5", ans5);
                params.put("question6", ans6);
                params.put("question7", ans7);
                params.put("question8", ans8);
                params.put("question9", ans9);
                return params;
            }
        };
        queue.add(stringRequest);

    }
    public void teacher_array()
    {
        String URL_ROOT = "http://"+ GlobalClass.URL+"/fed_app/extract_teachers.php";
        //Toast.makeText(guest_feedback.this, get, Toast.LENGTH_SHORT).show();

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

                    JSONObject jsonObject;

                    teacher_n_array = new String[jsonArray.length()+1];
                    teacher_i_array = new String[jsonArray.length()+1];
                    teacher_count=jsonArray.length();
                    for(int i=0;i<jsonArray.length();i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        String code = jsonObject.getString("code");
                        if (code.equals("success")) {

                            if(jsonObject.getString("id").charAt(0)=='G'&&jsonObject.getString("id").charAt(1)=='S')
                            {
                                teacher_n_array[i] = jsonObject.getString("name");
                                teacher_i_array[i] = jsonObject.getString("id");
                            }
                            else {
                                teacher_n_array[i]= "why";
                                teacher_i_array[i]="why";
                                teacher_count--;
                            }

                        } else {
                            Toast.makeText(guest_feedback.this, code, Toast.LENGTH_SHORT).show();

                        }
                    }
                    int j=1;
                    teacher_name_array = new String[teacher_count+1];
                    teacher_id_array = new String[teacher_count+1];
                    teacher_name_array[0]="Select Teacher ";
                    teacher_id_array[0]="00000";
                    for(int i=0;i<jsonArray.length();i++) {
                        if(teacher_n_array[i].equals("why")){

                        }
                        else{
                            teacher_name_array[j++]= teacher_n_array[i];

                        }
                    }

                    j=1;
                    for(int i=0;i<jsonArray.length();i++) {
                        if(teacher_i_array[i].equals("why")){

                        }
                        else{
                            teacher_id_array[j++]= teacher_i_array[i];
                        }
                    }
                    spinner();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            public void spinner() {
                tarray = new ArrayAdapter<String>(guest_feedback.this, android.R.layout.simple_list_item_1, teacher_name_array); //created adapter and received yname array from res file
                tarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  //Set type of Spinner Adapter
                sp_teacher.setAdapter(tarray);  //Called Spinner using the adapter
                sp_teacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        teacher_index = adapterView.getSelectedItemPosition();
                        if (teacher_index == 0) {
                            scroll.setVisibility(View.GONE);
                            ET_ans1.setText("");
                            ET_ans2.setText("");
                            ET_ans3.setText("");
                            ET_ans4.setText("");
                            ET_ans5.setText("");
                            ET_ans6.setText("");
                            ET_ans7.setText("");
                            ET_ans8.setText("");
                            ET_ans9.setText("");
                            ET_ans10.setText("");
                        }
                        if (teacher_index > 0) {
                            scroll.setVisibility(View.VISIBLE);
                            ET_ans1.setText("");
                            ET_ans2.setText("");
                            ET_ans3.setText("");
                            ET_ans4.setText("");
                            ET_ans5.setText("");
                            ET_ans6.setText("");
                            ET_ans7.setText("");
                            ET_ans8.setText("");
                            ET_ans9.setText("");
                            ET_ans10.setText("");
                            scroll.smoothScrollTo(0,0);

                        }

                    }


                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
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
                params.put("branch",get_branch_guest());
                params.put("year",get_year_guest());
                return params;
            }
        };
        queue.add(stringRequest);


    }

    public void display() {

        String URL_ROOT = "http://"+GlobalClass.URL+"/fed_app/questions_guest.php";

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

                        TV_ques1.setText("1. "+jsonObject.getString("ques1"));
                        TV_ques2.setText("2. "+jsonObject.getString("ques2"));
                        TV_ques3.setText("3. "+jsonObject.getString("ques3"));
                        TV_ques4.setText("4. "+jsonObject.getString("ques4"));
                        TV_ques5.setText("5. "+jsonObject.getString("ques5"));
                        TV_ques6.setText("6. "+jsonObject.getString("ques6"));
                        TV_ques7.setText("7. "+jsonObject.getString("ques7"));
                        TV_ques8.setText("8. "+jsonObject.getString("ques8"));
                        TV_ques9.setText("9. "+jsonObject.getString("ques9"));
                        TV_ques10.setText("10. "+jsonObject.getString("ques10"));
                    } else {
                        Toast.makeText(guest_feedback.this, code, Toast.LENGTH_SHORT).show();
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
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(guest_feedback.this);
        builder.setMessage("Do want to exit?")
                .setPositiveButton("Ok",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent stud = new Intent(guest_feedback.this,MainActivity.class);   //back button=login screen
                        startActivity(stud);
                        finish();
                    }
                })
                .setNegativeButton("Cancel",null);
        AlertDialog alert= builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu manu){
        getMenuInflater().inflate(R.menu.feedback , manu); //creating menu in action bar
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){    //programming the items inside menu
        int id=item.getItemId();

        if(id==R.id.submit_all) {
            if(get_feed_count_guest()==teacher_count){
                set_registerbit_guest(0);

                String URL = "http://"+GlobalClass.URL+"/fed_app/bitset.php";
                RequestQueue queue = Volley.newRequestQueue(guest_feedback.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response!=null)
                        {
                            System.out.println(response);
                        }
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            System.out.println(response);
                            String code = jsonObject.getString("code");
                            if(code.equals("Successful"))
                            {
                                Toast.makeText(guest_feedback.this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(guest_feedback.this,MainActivity.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(guest_feedback.this, "Some error has occurred", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("Damn it");
                        }

                    }
                }, new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),"Check your internet", Toast.LENGTH_LONG).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("rollno", get_rollno_guest().toUpperCase());
                        params.put("bit", "guest_bit");
                        params.put("value", "1");
                        return params;
                    }
                };
                queue.add(stringRequest);


            }
            else{
                Toast.makeText(guest_feedback.this, ""+(teacher_count-get_feed_count_guest())+ " feedbacks not submitted", Toast.LENGTH_SHORT).show();
            }


        }

        if(id==R.id.id_aboutapp)
        {
            Intent i = new Intent(this,About_activity.class);
            startActivity(i);
            return true;
        }
        if(id==R.id.id_exit)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(guest_feedback.this);
            builder.setMessage("Do you want to exit?")
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent stud = new Intent(guest_feedback.this,MainActivity.class);   //back button=login screen
                            startActivity(stud);
                            finish();
                        }
                    })
                    .setNegativeButton("Cancel",null);
            AlertDialog alert= builder.create();
            alert.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void set_registerbit_guest(int guest_bit) {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("guest_bit",guest_bit);
        mEditor.apply();
    }
    private void store_feed_count_guest(int guest_count) {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("guest_count",guest_count);
        mEditor.apply();
    }
    private String get_rollno_guest() {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main", MODE_PRIVATE);
        String checker = mSharedPreferences.getString("guest_rollno", "00000");
        return checker;
    }
    private String get_branch_guest() {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main", MODE_PRIVATE);
        String checker = mSharedPreferences.getString("guest_branch", "");
        return checker;
    }
    private String get_year_guest() {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main", MODE_PRIVATE);
        String checker = mSharedPreferences.getString("guest_year", "");
        return checker;
    }
    private int get_feed_count_guest() {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main", MODE_PRIVATE);
        int checker = mSharedPreferences.getInt("guest_count",0);
        return checker;
    }
}
