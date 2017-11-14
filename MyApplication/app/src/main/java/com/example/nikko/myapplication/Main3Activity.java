package com.example.nikko.myapplication;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.*;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.*;

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


public class Main3Activity extends AppCompatActivity {
    Button b;
    EditText eId,ePassword;
    //TextView tv;
    private String username,password;




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

                if (TextUtils.isEmpty(username)) {
                    eId.setError("Please enter your ID");
                    eId.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    ePassword.setError("Please enter your password");
                    ePassword.requestFocus();
                    return;
                }

                else
                {
                    signIn();
                }

    }});


}

    public void signIn() {

        String URL_ROOT = "http://"+GlobalClass.URL+"/fed_app/teacher_login.php";

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
                    if (code.equals("login_success")) {
                        GlobalClass.t_name = jsonObject.getString("name");
                        GlobalClass.t_branch1 = jsonObject.getString("branch1");
                        GlobalClass.t_branch2 = jsonObject.getString("branch2");
                        GlobalClass.t_branch3 = jsonObject.getString("branch3");
                        GlobalClass.t_FE = jsonObject.getInt("FE");
                        GlobalClass.t_SE = jsonObject.getInt("SE");
                        GlobalClass.t_TE = jsonObject.getInt("TE");
                        GlobalClass.t_BE = jsonObject.getInt("BE");
                        GlobalClass.t_teacher_id = jsonObject.getString("teacher_id");

                        Toast.makeText(Main3Activity.this, "Welcome "+GlobalClass.t_name, Toast.LENGTH_SHORT).show();
                        Intent stud = new Intent(Main3Activity.this,Main5Activity.class);   //Linking teacher login to logged in
                        startActivity(stud);

                    } else {
                        Toast.makeText(Main3Activity.this, "Invalid ID or Password", Toast.LENGTH_SHORT).show();
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
                params.put("userid", username);
                params.put("userpass", password);
                return params;
            }
        };
        queue.add(stringRequest);
    }
    @Override
    public void onBackPressed(){
        Intent stud = new Intent(Main3Activity.this,MainActivity.class);   //back button = main screen
        startActivity(stud);
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
