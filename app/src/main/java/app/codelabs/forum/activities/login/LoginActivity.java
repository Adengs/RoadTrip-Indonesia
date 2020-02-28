package app.codelabs.forum.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.forgotpassword.ForgotPasswordActivity;
import app.codelabs.forum.activities.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView txtForgotPassword, txtRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setView();
        setEvent();

    }

    private void setView() {
        txtForgotPassword = findViewById(R.id.txtforgotpasword);
        txtRegister = findViewById(R.id.txtpindahregister);
        btnLogin = findViewById(R.id.buttonlogin);
    }

    private void setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
