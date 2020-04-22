package app.codelabs.forum.activities.forgot_password;

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
import app.codelabs.forum.activities.custom.ProgressDialogFragment;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.helpers.Validator;
import app.codelabs.forum.models.ResponseForgotPassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    private ImageView ivBack;
    private Button btnSend;
    private EditText etEmail;
    private Session session;
    private Context context;
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        context = getApplicationContext();
        session = Session.init(context);

        setView();
        setEvent();
    }


    private void setView() {
        ivBack = findViewById(R.id.btn_back);
        btnSend = findViewById(R.id.btn_send);
        etEmail = findViewById(R.id.et_email);

    }

    private void setEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isValidForm()){
                    return;
                }

                final Map<String, String> data = new HashMap<>();
                final String email = etEmail.getText().toString();
                data.put("email", email);
                progressDialogFragment.show(getSupportFragmentManager(), "progress");
                ConnectionApi.apiService(context).requestResetPassword(data).enqueue(new Callback<ResponseForgotPassword>() {
                    @Override
                    public void onResponse(Call<ResponseForgotPassword> call, Response<ResponseForgotPassword> response) {
                        progressDialogFragment.dismiss();
                        if (response.isSuccessful() && response.body().getSuccess()) {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPasswordActivity.this, SubmitPasswordActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseForgotPassword> call, Throwable t) {
                        progressDialogFragment.dismiss();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    private boolean isValidForm() {
        if (etEmail.getText().toString().isEmpty()) {
            Toast.makeText(context, "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Validator.isEmailValid(etEmail.getText().toString())) {
            Toast.makeText(context, "Email is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
