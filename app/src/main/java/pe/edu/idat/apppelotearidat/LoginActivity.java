package pe.edu.idat.apppelotearidat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button btnIngresar;
    private EditText etUsuario, etPassword;
    private ProgressBar progbar;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsuario =  findViewById(R.id.etUsuario);
        etPassword =  findViewById(R.id.etPassword);
        progbar = findViewById(R.id.progbar);
        btnIngresar = findViewById(R.id.btnLogin);
        SharedPreferences preferences = getSharedPreferences(
                "appPelotear", MODE_PRIVATE);
        if(preferences.contains("idpersona")){
            startActivity(new Intent(LoginActivity.this,
                    MainActivity.class));
        }

        mQueue = Volley.newRequestQueue(this);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://luis.wordlatin.com/RestfulService/login.php";
                Map<String, String> params = new HashMap();
                params.put("usuario", etUsuario.getText().toString());
                params.put("password", etPassword.getText().toString());
                JSONObject parameters = new JSONObject(params);
                btnIngresar.setVisibility(View.GONE);
                progbar.setVisibility(View.VISIBLE);
                JsonObjectRequest request = new JsonObjectRequest(
                        Request.Method.POST, url, parameters,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try{
                                    if(response.getBoolean("rpta")){
                                        SharedPreferences.Editor editor =
                                                getSharedPreferences("appPelotear",
                                                        MODE_PRIVATE).edit();
                                        editor.putString("idpersona", String.valueOf(response.getInt("idpersona")));
                                        editor.apply();
                                        progbar.setVisibility(View.GONE);
                                        startActivity(new Intent(LoginActivity.this,
                                                MainActivity.class));

                                    }else{
                                        btnIngresar.setVisibility(View.VISIBLE);
                                        progbar.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(),
                                                response.getString("mensaje"),Toast.LENGTH_LONG).show();
                                    }
                                    //Toast.makeText(getApplicationContext(), response.toString(),Toast.LENGTH_SHORT).show();
                                }catch (Exception ex){
                                    ex.printStackTrace();

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });
                mQueue.add(request);



            }
        });

    }
}
