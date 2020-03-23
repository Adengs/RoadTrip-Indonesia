package app.codelabs.forum.activities.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameRegis, etNamaRegis, etEmailRegis, etPassword;
    private TextView txtlogin;
    private ImageView btnEditRegister;
    private Button btnRegis;
    Session session;
    private String Apptoken;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = getApplicationContext();
        session = Session.init(context);
        Apptoken = session.getAppToken();

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
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> data = new HashMap<>();
                data.put("username", usernameRegis.getText().toString());
                data.put("password", etPassword.getText().toString());
                data.put("name", etNamaRegis.getText().toString());
                data.put("email", etEmailRegis.getText().toString());

                ConnectionApi.apiService().register(data ,Apptoken).enqueue(new Callback<ResponseRegister>() {
                    @Override
                    public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                        if (response.isSuccessful() && response.body().getSuccess()) {

                            Toast.makeText(context,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        } else {

                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseRegister> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }


    private void setView() {
        btnRegis = findViewById(R.id.btn_regster);
        usernameRegis = findViewById(R.id.et_usernameregis);
        etNamaRegis = findViewById(R.id.et_nameregis);
        etEmailRegis = findViewById(R.id.et_emailregis);
        etPassword = findViewById(R.id.et_passwordregis);
        txtlogin = findViewById(R.id.txtloginregis);
        btnEditRegister = findViewById(R.id.imgEditRegis);
    }
}
