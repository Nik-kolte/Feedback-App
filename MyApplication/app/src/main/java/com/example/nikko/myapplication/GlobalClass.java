package com.example.nikko.myapplication;

import android.content.Intent;
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

/**
 * Created by nikko on 22-Sep-17.
 */

public class GlobalClass {
    public static String t_name,t_teacher_id,t_branch1,t_branch2,t_branch3,s_name,s_lname,g_s_name,g_s_lname;
            public static String URL ="192.168.43.243";
    public static int t_FE,t_SE,t_TE,t_BE,t_branch_count,t_year_count;
    public static String[] question10;

}
