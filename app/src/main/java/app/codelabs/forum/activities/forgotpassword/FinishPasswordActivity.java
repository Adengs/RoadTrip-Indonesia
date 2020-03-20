package app.codelabs.forum.activities.forgotpassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.login.LoginActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponseFinishPassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinishPasswordActivity extends AppCompatActivity {

    ImageView btnBackFinish;
    Button btnFinishForgot;

    EditText pass, passd;

    Session session;
    private String apptoken;

    //private String xresett = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODQ1MTU1OTYsImV4cCI6MTU4NDUyNjM5NiwiZGF0YSI6eyJ1c2VyX2lkIjo2LCJjb2RlIjoiNTQxNSJ9fQ.UW6w4kTrVPaz75pFFwet4--1yJDXYeD8IKX2y4KykGI";

    Context context;

    private String xresett;


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
                startActivity(new Intent(FinishPasswordActivity.this, SubmitPasswordActivity.class));
            }
        });

        btnFinishForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> data = new HashMap<>();
                data.put("password", pass.getText().toString());
                data.put("password-confirm", passd.getText().toString());

                ConnectionApi.apiService().finishpassword(data, apptoken, xresett).enqueue(new Callback<ResponseFinishPassword>() {
                    @Override
                    public void onResponse(Call<ResponseFinishPassword> call, Response<ResponseFinishPassword> response) {
                        if (response.isSuccessful() && response.body().getSuccess()) {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(FinishPasswordActivity.this, LoginActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseFinishPassword> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }

}
