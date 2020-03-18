package app.codelabs.forum.activities.forgotpassword;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.login.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FinishPasswordActivity extends AppCompatActivity {

    ImageView btnBackFinish;
    Button btnFinishForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_password);

        setView();
        setEvent();
    }

    private void setView() {
        btnBackFinish = findViewById(R.id.btn_BackFinish);
        btnFinishForgot = findViewById(R.id.btn_FinishForgot);
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
                startActivity(new Intent(FinishPasswordActivity.this, LoginActivity.class));

            }
        });
    }

}
