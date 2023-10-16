package app.codelabs.roadtrip.activities.forgot_password;

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

import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.Session;
import app.codelabs.roadtrip.models.ResponseFinishPassword;
import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.login.LoginActivity;
import app.codelabs.roadtrip.activities.custom.ProgressDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinishPasswordActivity extends AppCompatActivity {

    private ImageView ivBack;
    private Button btnFinish;
    private EditText etPassword, etConfirmPassword;
    private Session session;
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private Context context;
    private String xResetToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_password);
        context = getApplicationContext();
        session = Session.init(context);

        getData();
        setView();
        setEvent();
    }

    private void getData() {
        xResetToken = getIntent().getStringExtra("x-token");
    }

    private void setView() {
        ivBack = findViewById(R.id.btn_back);
        btnFinish = findViewById(R.id.btn_finish);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
    }

    private void setEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValidForm()) {
                    return;
                }

                Map<String, String> data = new HashMap<>();
                data.put("password", etPassword.getText().toString());
                data.put("password-confirm", etConfirmPassword.getText().toString());
                progressDialogFragment.show(getSupportFragmentManager(), "progress");
                ConnectionApi.apiService(context).resetPassword(data, xResetToken).enqueue(new Callback<ResponseFinishPassword>() {
                    @Override
                    public void onResponse(Call<ResponseFinishPassword> call, Response<ResponseFinishPassword> response) {
                        progressDialogFragment.dismiss();
                        if (response.isSuccessful() && response.body().getSuccess()) {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FinishPasswordActivity.this, LoginActivity.class).setFlags(
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK
                            ));

                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseFinishPassword> call, Throwable t) {
                        progressDialogFragment.dismiss();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }


    private boolean isValidForm() {
        if (etPassword.getText().toString().isEmpty()) {
            Toast.makeText(context, "New Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (etConfirmPassword.getText().toString().isEmpty()) {
            Toast.makeText(context, "Confirmation Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
