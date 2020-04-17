package app.codelabs.forum.activities.register;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.custom.ProgressDialogFragment;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.helpers.Validator;
import app.codelabs.forum.models.ResponseRegister;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etName, etEmail, etPassword;
    private TextView tvLogin;
    private ImageView ivBack;
    private Button btnRegister;
    private Session session;
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = getApplicationContext();
        session = Session.init(context);

        setView();
        setEvent();
    }

    private void setEvent() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidForm()) {
                    return;
                }
                
                Map<String, String> data = new HashMap<>();
                data.put("username", etUsername.getText().toString());
                data.put("password", etPassword.getText().toString());
                data.put("name", etName.getText().toString());
                data.put("email", etEmail.getText().toString());

                progressDialogFragment.show(getSupportFragmentManager(), "progress");
                ConnectionApi.apiService(context).register(data).enqueue(new Callback<ResponseRegister>() {
                    @Override
                    public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                        progressDialogFragment.dismiss();
                        if (response.isSuccessful() && response.body().getSuccess()) {
                            Toast.makeText(context, "Success Register", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseRegister> call, Throwable t) {
                        progressDialogFragment.dismiss();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }


    private void setView() {
        btnRegister = findViewById(R.id.btn_register);
        etUsername = findViewById(R.id.et_username);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvLogin = findViewById(R.id.tv_login);
        ivBack = findViewById(R.id.iv_back);
    }


    private boolean isValidForm() {

        if (etUsername.getText().toString().isEmpty()) {
            Toast.makeText(context, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etName.getText().toString().isEmpty()) {
            Toast.makeText(context, "Name is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etEmail.getText().toString().isEmpty()) {
            Toast.makeText(context, "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etPassword.getText().toString().isEmpty()) {
            Toast.makeText(context, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Validator.isEmailValid(etEmail.getText().toString())) {
            Toast.makeText(context, "Email is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
