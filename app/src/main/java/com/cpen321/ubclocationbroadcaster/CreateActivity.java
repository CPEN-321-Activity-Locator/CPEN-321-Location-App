package com.cpen321.ubclocationbroadcaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditText name;
        Button done_btn;
        done_btn = findViewById(R.id.activity_done);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent createActivityIntent = new Intent(CreateActivity.this, MenuActivity.class);
                startActivity(createActivityIntent);
                Log.d("sign in button", "sign in button has been clicked");

                Intent intent = new Intent(CreateActivity.this, MenuActivity.class);
                startActivity(intent);
                Log.d("done button", "done button has been clicked");


                String URL = "http://40.122.147.73:5000/addactivity";

                //format request
                JSONObject jsnRequest = new JSONObject();

                EditText name = findViewById(R.id.activity_name);
                EditText aid  = findViewById(R.id.activity_id);
                EditText info = findViewById(R.id.activity_desc);
                EditText course = findViewById(R.id.activity_course);
                EditText lat = findViewById(R.id.activity_lat);
                EditText lon = findViewById(R.id.activity_long);

                String inputaid = aid.getText().toString();
                String inputcourse = course.getText().toString();
                String inputInfo = info.getText().toString();
                String inputLat = lat.getText().toString();
                String inputLong = lon.getText().toString();
                String inputName = name.getText().toString();

               // Intent intent = getIntent();
                String username = getIntent().getStringExtra("USERNAME");
                String inputSchool = getIntent().getStringExtra("SCHOOL");
                String inputMajor = getIntent().getStringExtra("MAJOR");
                String course1 = getIntent().getStringExtra("COURSE1");
                String course2 = getIntent().getStringExtra("COURSE2");
                String course3 = getIntent().getStringExtra("COURSE3");
                String course4 = getIntent().getStringExtra("COURSE4");
                String course5 = getIntent().getStringExtra("COURSE5");

                try {
                    jsnRequest.put("name", inputName);
                    jsnRequest.put("aid", inputaid);
                    jsnRequest.put("users", username);
                    jsnRequest.put("course", inputcourse);
                    jsnRequest.put("school", inputSchool);
                    jsnRequest.put("major", inputMajor);
                    jsnRequest.put("info", inputInfo);

                    jsnRequest.put("lat", inputLat);
                    jsnRequest.put("long", inputLong);
                    jsnRequest.put("lat", inputLat);
                    jsnRequest.put("long", inputLong);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
               // if(inputcourse == course1 || inputcourse == course2 || inputcourse == course3 || inputcourse == course4 || inputcourse == course5){
                    JsonObjectRequest json_obj = new JsonObjectRequest(Request.Method.POST, URL, jsnRequest,
                            new Response.Listener<JSONObject> (){
                                @Override
                                public void onResponse(JSONObject response){
                                    try {
                                        boolean successVal = (boolean) response.get("success"); // check if user signed in successfully
                                        String stat = response.get("status").toString(); // get status
                                        if(successVal) {
                                            Intent sign_in_Intent = new Intent(CreateActivity.this, MenuActivity.class);
                                            Toast.makeText(CreateActivity.this, "Activity added", Toast.LENGTH_SHORT).show();
                                            startActivity(sign_in_Intent);
                                        } else {
                                            Toast.makeText(CreateActivity.this, "ERROR: " + stat, Toast.LENGTH_SHORT).show();
                                        }
                                        Log.d("SignInActivity", stat);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error){
                            Toast.makeText(CreateActivity.this, "Unable to send the sign in data to the server!", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    });

                    MySingleton.getInstance(CreateActivity.this).addToRequestQueue(json_obj);
                }
            //}
        });
    }
}