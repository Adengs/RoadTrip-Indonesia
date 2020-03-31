package app.codelabs.forum.activities.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.forgotpassword.ForgotPasswordActivity;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.register.RegisterActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView txtForgotPassword, txtRegister;
    private EditText et_email;
    private EditText et_password;
    private String Apptoken;
    private ProgresDialogFragment progresDialogFragment = new ProgresDialogFragment();
    Context context;
    Session session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = getApplicationContext();
        session = Session.init(context);
        Apptoken = session.getAppToken();

        setView();
        setEvent();

    }

    private void setView() {
        txtForgotPassword = findViewById(R.id.txtforgotpasword);
        txtRegister = findViewById(R.id.txtpindahregister);
        et_email = findViewById(R.id.etemaillogin);
        et_password = findViewById(R.id.etpasswordlogin);
        btnLogin = findViewById(R.id.buttonlogin);
    }

    private void setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> dataLogin = new HashMap<>();
                dataLogin.put("email",et_email.getText().toString());
                dataLogin.put("password",et_password.getText().toString());
                progresDialogFragment.show(getSupportFragmentManager(),"proggress");
                ConnectionApi.apiService().login(dataLogin,Apptoken).enqueue(new Callback<ResponsLogin>() {
                    @Override
                    public void onResponse(Call<ResponsLogin> call, Response<ResponsLogin> response) {
                    progresDialogFragment.dismiss();
                        if(response.isSuccessful() && response.body().getSuccess()){

                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            session.setLogin(response.body().getData(),response.body().getToken());
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(context,"can not login",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsLogin> call, Throwable t) {
                        progresDialogFragment.dismiss();
                        Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

}
