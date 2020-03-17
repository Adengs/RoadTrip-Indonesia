package app.codelabs.forum.activities.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameRegis,etNamaRegis , etEmailRegis,etPassword , etPassword2;
    private TextView txtlogin;
    ImageView btnEditRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

    }

    private void setView() {
        usernameRegis=findViewById(R.id.et_usernameregis);
        etNamaRegis=findViewById(R.id.et_nameregis);
        etEmailRegis = findViewById(R.id.et_emailregis);
        etPassword = findViewById(R.id.et_passwordregis);
        etPassword2 = findViewById(R.id.et_password2regis);
        txtlogin = findViewById(R.id.txtloginregis);
        btnEditRegister = findViewById(R.id.imgEditRegis);
    }
}
