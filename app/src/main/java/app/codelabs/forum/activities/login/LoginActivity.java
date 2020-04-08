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
import app.codelabs.forum.activities.forgot_password.ForgotPasswordActivity;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.register.RegisterActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.helpers.Validator;
import app.codelabs.forum.models.ResponsLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView tvForgotPassword, tvGotoRegister;
    private EditText etEmail;
    private EditText etPassword;
    private ProgresDialogFragment progresDialogFragment = new ProgresDialogFragment();
    private Context context;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = getApplicationContext();
        session = Session.init(context);

        setView();
        setEvent();

    }

    private void setView() {
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        tvGotoRegister = findViewById(R.id.tv_goto_register);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    private void setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidForm()) {
                    return;
                }
                Map<String, String> dataLogin = new HashMap<>();
                dataLogin.put("email", etEmail.getText().toString());
                dataLogin.put("password", etPassword.getText().toString());
                progresDialogFragment.show(getSupportFragmentManager(), "proggress");
                ConnectionApi.apiService(context).login(dataLogin).enqueue(new Callback<ResponsLogin>() {
                    @Override
                    public void onResponse(Call<ResponsLogin> call, Response<ResponsLogin> response) {
                        progresDialogFragment.dismiss();
                        if (response.isSuccessful() && response.body().getSuccess()) {

                            session.setLogin(response.body().getData(), response.body().getToken());

                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsLogin> call, Throwable t) {
                        progresDialogFragment.dismiss();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
        tvGotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private boolean isValidForm() {
        if (etEmail.getText().toString().isEmpty()) {
            Toast.makeText(context, "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etPassword.getText().toString().isEmpty()) {
            Toast.makeText(context, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Validator.isEmailValid(etEmail.getText().toString())) {
            Toast.makeText(context, "Email is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
