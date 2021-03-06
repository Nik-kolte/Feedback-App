package com.example.nikko.myapplication;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    protected Button proc_btn ;               //PROCEED BTN
    private EditText r_num;
    protected String rollno;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
if(get_registerbit()==1)
{
    Toast.makeText(getBaseContext(),"Please finish your previous feedback", Toast.LENGTH_LONG).show();
    Intent stud4;
    stud4 = new Intent(Main2Activity.this,Main4Activity.class);
    startActivity(stud4);
}
else {
    store_feed_count(0);
    r_num = (EditText) findViewById(R.id.r_no);
    proc_btn = (Button) findViewById(R.id.proceed);
    proc_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(final View view) {
                                        //if user pressed on button register
                                        //here we will register the user to server
                                        //   registerUser();
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
                                                    Toast.makeText(Main2Activity.this, "Invalid Roll Number", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            else {
                                                Toast.makeText(Main2Activity.this, "Invalid Roll Number", Toast.LENGTH_LONG).show();
                                            }

                                        } else {
                                            Toast.makeText(Main2Activity.this, "Invalid Roll Number", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }

    );
                                    }
    }

    public void signup(){

        String URL = "http://"+GlobalClass.URL+"/fed_app/register.php";
        RequestQueue queue = Volley.newRequestQueue(Main2Activity.this);
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
                        store_branch(jsonObject.getString("branch"));
                        store_year(jsonObject.getString("year"));
                        store_rollno(jsonObject.getString("rollno"));
                        set_registerbit(1);
                        Toast.makeText(getBaseContext(),code, Toast.LENGTH_LONG).show();
                        Intent stud4;
                        stud4 = new Intent(Main2Activity.this,Main4Activity.class);
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
        Intent stud = new Intent(Main2Activity.this, MainActivity.class);   //back button
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

    private void set_registerbit(int bit) {
        SharedPreferences mSharedPreferences = getSharedPreferences("main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("bit",bit);
        mEditor.apply();
    }
    private int get_registerbit() {
        SharedPreferences mSharedPreferences = getSharedPreferences("main", MODE_PRIVATE);
        int checker = mSharedPreferences.getInt("bit", 0);
        return checker;
    }
    private void store_rollno(String rollno) {
        SharedPreferences mSharedPreferences = getSharedPreferences("main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("rollno", rollno);
        mEditor.apply();
    }
    private void store_branch(String branch) {
        SharedPreferences mSharedPreferences = getSharedPreferences("main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("branch", branch);
        mEditor.apply();
    }
    private void store_year(String year) {
        SharedPreferences mSharedPreferences = getSharedPreferences("main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("year",year );
        mEditor.apply();
    }

    private void store_feed_count(int count) {
        SharedPreferences mSharedPreferences = getSharedPreferences("main",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("count",count);
        mEditor.apply();
    }

}



