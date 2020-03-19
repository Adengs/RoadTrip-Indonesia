package app.codelabs.forum.activities.forgotpassword;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.login.LoginActivity;
import app.codelabs.forum.helpers.Session;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class FinishPasswordActivity extends AppCompatActivity {

    ImageView btnBackFinish;
    Button btnFinishForgot;

    EditText pass,passd;

    Session session;
    private String apptoken;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_password);
        context = getApplicationContext();
        session = Session.init(context);
        apptoken = session.getAppToken();

        setView();
        setEvent();
    }

    private void setView() {
        btnBackFinish = findViewById(R.id.btn_BackFinish);
        btnFinishForgot = findViewById(R.id.btn_FinishForgot);
        pass = findViewById(R.id.et_passwordsatu);
        passd = findViewById(R.id.et_passworddua);
    }

    private void setEvent() {
        btnBackFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinishPasswordActivity.this,SubmitPasswordActivity.class));
            }
        });

        btnFinishForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> data = new HashMap<>();
                data.put("password",pass.getText().toString());
                data.put("password-confirm",passd.getText().toString());

                startActivity(new Intent(FinishPasswordActivity.this, LoginActivity.class));

            }
        });
    }

}
