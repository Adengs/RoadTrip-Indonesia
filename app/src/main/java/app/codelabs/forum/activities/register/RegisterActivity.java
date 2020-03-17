package app.codelabs.forum.activities.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.login.LoginActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponseApi;
import app.codelabs.forum.models.ResponseRegister;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameRegis,etNamaRegis , etEmailRegis,etPassword , etPassword2;
    private TextView txtlogin;
    ImageView btnEditRegister;
    private Session session;
    String apptoken;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = getApplicationContext();
        session = Session.init(context);
        apptoken = session.getAppToken();
        setView();
        setEvent();



    }

    private void setEvent() {
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        btnEditRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
                //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void doRegister() {
        Map<String, String> data = new HashMap<>();
        data.put("username" ,usernameRegis.getText().toString());
        data.put("password" ,etPassword.getText().toString());
        data.put("name" ,etNamaRegis.getText().toString());
        data.put("email",etEmailRegis.getText().toString());

        ConnectionApi.apiService().register(data,apptoken).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response.isSuccessful() && response.body() .getSuccess()){
                    Toast.makeText(context,"Succes Register",Toast.LENGTH_SHORT).show();
                }else {
                    ResponseRegister catchErrorResponse = new Gson().fromJson(response.errorBody().charStream(),ResponseRegister.class);
                    Toast.makeText(context ,catchErrorResponse.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(context , t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
            }

    private void setView() {
        usernameRegis=findViewById(R.id.et_usernameregis);
        etNamaRegis=findViewById(R.id.et_nameregis);
        etEmailRegis = findViewById(R.id.et_emailregis);
        etPassword = findViewById(R.id.et_passwordregis);
        txtlogin = findViewById(R.id.txtloginregis);
        btnEditRegister = findViewById(R.id.imgEditRegis);
    }
}
