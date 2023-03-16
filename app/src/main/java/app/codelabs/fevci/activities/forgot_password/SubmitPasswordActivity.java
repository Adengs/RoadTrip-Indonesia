package app.codelabs.fevci.activities.forgot_password;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import app.codelabs.fevci.activities.custom.ProgressDialogFragment;
import app.codelabs.fevci.helpers.ConnectionApi;
import app.codelabs.fevci.helpers.Session;
import app.codelabs.fevci.models.ResponseForgotPassword;
import app.codelabs.fevci.models.ResponseSubmitPassword;
import app.codelabs.forum.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitPasswordActivity extends AppCompatActivity {
    private ImageView ivBack;
    private Button btnSubmit;
    private EditText etCode1, etCode2, etCode3, etCode4;
    private Session session;
    private Context context;
    private TextView tvResending;
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_password);
        context = getApplicationContext();
        session = Session.init(context);

        setView();
        setEvent();
        setCode();
    }

    private void setCode() {
        etCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 1){
                    etCode2.requestFocus();
                }else if(s.length() == 0){
                    etCode1.requestFocus();
                }

            }
        });
        etCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 1){
                    etCode3.requestFocus();
                }else  if(s.length() == 0){
                    etCode1.requestFocus();
                }
            }
        });
        etCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 1){
                    etCode4.requestFocus();
                }else if(s.length() == 0){
                    etCode2.requestFocus();
                }
            }
        });
        etCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0){
                    etCode3.requestFocus();
                }
            }
        });
    }

    private void setView() {

        ivBack = findViewById(R.id.btn_back);
        btnSubmit = findViewById(R.id.btn_submit);
        tvResending = findViewById(R.id.tv_resend_code);
        etCode1 = findViewById(R.id.et_code_1);
        etCode2 = findViewById(R.id.et_code_2);
        etCode3 = findViewById(R.id.et_code_3);
        etCode4 = findViewById(R.id.et_code_4);


    }

    private void setEvent() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValidForm()) {
                    return;
                }
                String code = etCode1.getText().toString() + etCode2.getText().toString() + etCode3.getText().toString() + etCode4.getText().toString();
                Map<String, String> data = new HashMap<>();
                data.put("code", code);

                progressDialogFragment.show(getSupportFragmentManager(), "progress");
                ConnectionApi.apiService(context).verifyCodeResetPassword(data).enqueue(new Callback<ResponseSubmitPassword>() {
                    @Override
                    public void onResponse(Call<ResponseSubmitPassword> call, Response<ResponseSubmitPassword> response) {
                        progressDialogFragment.dismiss();
                        if (response.isSuccessful() && response.body().getSuccess()) {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SubmitPasswordActivity.this, FinishPasswordActivity.class);
                            intent.putExtra("x-token", response.body().getData().getToken());
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseSubmitPassword> call, Throwable t) {
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
        tvResending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getIntent().getStringExtra("email");
                final Map<String, String> data = new HashMap<>();
                data.put("email", email);
                progressDialogFragment.show(getSupportFragmentManager(), "progress");
                ConnectionApi.apiService(context).requestResetPassword(data).enqueue(new Callback<ResponseForgotPassword>() {
                    @Override
                    public void onResponse(Call<ResponseForgotPassword> call, Response<ResponseForgotPassword> response) {
                        progressDialogFragment.dismiss();
                        if (response.isSuccessful() && response.body().getSuccess()) {

                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

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

    }


    private boolean isValidForm() {
        if (etCode1.getText().toString().isEmpty() || etCode2.getText().toString().isEmpty() || etCode3.getText().toString().isEmpty() || etCode4.getText().toString().isEmpty()) {
            Toast.makeText(context, "Fill all code", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }
}
