package app.codelabs.forum.activities.forgotpassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.login.LoginActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    ImageView btnbackfor;
    EditText etEmailF;
    Button btnSendF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setView();
        setEvent();


    }



    private void setView() {
        etEmailF = findViewById(R.id.et_emailforgotpass);
        btnSendF = findViewById(R.id.btnSendForgotPass);
        btnbackfor = findViewById(R.id.btnBackForgot);
    }

    private void setEvent() {
        btnbackfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
            }
        });
    }
}
