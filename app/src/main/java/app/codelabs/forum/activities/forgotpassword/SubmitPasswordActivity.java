package app.codelabs.forum.activities.forgotpassword;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SubmitPasswordActivity extends AppCompatActivity {
    ImageView btnBackSubmit;
    Button btnSubmitForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_password);

        setView();
        setEvent();
    }



    private void setView() {
        btnBackSubmit = findViewById(R.id.btn_BackSubmit);
        btnSubmitForgot = findViewById(R.id.btn_SubmitForgot);
    }
    private void setEvent() {
        btnBackSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubmitPasswordActivity.this,ForgotPasswordActivity.class));
            }
        });

        btnSubmitForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubmitPasswordActivity.this,FinishPasswordActivity.class));
            }
        });
    }
}
