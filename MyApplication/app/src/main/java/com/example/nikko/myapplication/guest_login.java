package com.example.nikko.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class guest_login extends AppCompatActivity {

    protected Button proc_btn ;               //PROCEED BTN
    private EditText r_num;
    protected String rollno;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_loginlayout);
if(get_registerbit_guest()==1)
{
    Toast.makeText(getBaseContext(),"Please finish your previous feedback", Toast.LENGTH_LONG).show();
    Intent stud4;
    stud4 = new Intent(guest_login.this,guest_feedback.class);
    startActivity(stud4);
}
else {
    store_feed_count_guest(0);
    r_num = (EditText) findViewById(R.id.r_no);
    proc_btn = (Button) findViewById(R.id.proceed);
    proc_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(final View view) {
                                        rollno = r_num.getText().toString().toUpperCase();

                                        if (TextUtils.isEmpty(rollno)) {
                                            r_num.setError("Please Enter Roll no");
                                            r_num.requestFocus();
                                            r_num.equals("");
                                            return;
                                        }
                                        if (rollno.length()== 5&&rollno.charAt(2)=='0') {

                                            if(rollno.charAt(0)=='F'||rollno.charAt(0)=='S'||rollno.charAt(0)=='T'||rollno.charAt(0)=='B'){
                                                if(rollno.charAt(1)=='C'||rollno.charAt(1)=='I'||rollno.charAt(1)=='E'){
                                                    signup();

                                                }
                                                else {
                                                    Toast.makeText(guest_login.this, "Invalid Roll Number", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            else {
                                                Toast.makeText(guest_login.this, "Invalid Roll Number", Toast.LENGTH_LONG).show();
                                            }

                                        } else {
                                            Toast.makeText(guest_login.this, "Invalid Roll Number", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }

    );
}
    }

    public void signup(){

        String URL = "http://"+GlobalClass.URL+"/fed_app/register_guest.php";
        RequestQueue queue = Volley.newRequestQueue(guest_login.this);
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
                        store_branch_guest(jsonObject.getString("branch"));
                        store_year_guest(jsonObject.getString("year"));
                        store_rollno_guest(jsonObject.getString("rollno"));
                        set_registerbit_guest(1);
                        Toast.makeText(getBaseContext(),code, Toast.LENGTH_LONG).show();
                        Intent stud4;
                        stud4 = new Intent(guest_login.this,guest_feedback.class);
                        startActivity(stud4);
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),code, Toast.LENGTH_LONG).show();

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
                params.put("rollno", rollno.toUpperCase());
                return params;
            }
        };
        queue.add(stringRequest);


    }



    @Override
    public void onBackPressed() {
        Intent stud = new Intent(guest_login.this, MainActivity.class);   //back button
        startActivity(stud);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu manu) {
        getMenuInflater().inflate(R.menu.main_screen, manu); //creating menu in action bar
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    //programming the items inside menu
        int id = item.getItemId();

        if (id == R.id.id_aboutapp) {
            Intent i = new Intent(this,About_activity.class);
            startActivity(i);
            return true;
        }
        return true;
    }

    private void set_registerbit_guest(int guest_bit) {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("guest_bit",guest_bit);
        mEditor.apply();
    }
    private int get_registerbit_guest() {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main", MODE_PRIVATE);
        int checker = mSharedPreferences.getInt("guest_bit", 0);
        return checker;
    }

    private void store_rollno_guest(String guest_rollno) {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("guest_rollno",guest_rollno);
        mEditor.apply();
    }

    private void store_branch_guest(String guest_branch) {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("guest_branch", guest_branch);
        mEditor.apply();
    }
    private void store_year_guest(String guest_year) {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("guest_year",guest_year );
        mEditor.apply();
    }
    private void store_feed_count_guest(int guest_count) {
        SharedPreferences mSharedPreferences = getSharedPreferences("guest_main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("guest_count",guest_count);
        mEditor.apply();
    }

}



