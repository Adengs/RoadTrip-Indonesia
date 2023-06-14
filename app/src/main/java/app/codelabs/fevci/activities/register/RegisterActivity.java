package app.codelabs.fevci.activities.register;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

import app.codelabs.fevci.models.ResponseCheckNra;
import app.codelabs.fevci.models.ResponseDefault;
import app.codelabs.fevci.models.ResponseListMemberCompany;
import app.codelabs.forum.R;
import app.codelabs.fevci.activities.custom.ProgressDialogFragment;
import app.codelabs.fevci.helpers.ConnectionApi;
import app.codelabs.fevci.helpers.Session;
import app.codelabs.fevci.helpers.Validator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText etNra, etUsername, etName, etEmail, etPassword, etConPass;
    private TextView tvLogin;
    private ImageView ivBack;
    private Button btnRegister;
    private Session session;
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private Context context;

    private boolean isLoading;
    private ProgressBar progressBar;
    String keyword = "";
    String nameAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = getApplicationContext();
        session = Session.init(context);

        setView();
        setEvent();
        setLoading(true, true);
//        loadData();
        checkNra();
        removeNra();
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
                data.put("nra", etNra.getText().toString());
                data.put("name", etName.getText().toString());
                data.put("email", etEmail.getText().toString());
                data.put("password", etPassword.getText().toString());
//                data.put("alamat", etPassword.getText().toString());

                progressDialogFragment.show(getSupportFragmentManager(), "progress");
                ConnectionApi.apiService(context).register(data).enqueue(new Callback<ResponseDefault>() {
                    @Override
                    public void onResponse(Call<ResponseDefault> call, Response<ResponseDefault> response) {
                        progressDialogFragment.dismiss();
                        if (response.isSuccessful() && response.body().getSuccess()) {
                            Toast.makeText(context, "Success Register", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDefault> call, Throwable t) {
                        progressDialogFragment.dismiss();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }


    private void setView() {
        btnRegister = findViewById(R.id.btn_register);
        etNra = findViewById(R.id.et_nra);
        etUsername = findViewById(R.id.et_username);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConPass = findViewById(R.id.con_password);
        tvLogin = findViewById(R.id.tv_login);
        ivBack = findViewById(R.id.iv_back);
        progressBar = findViewById(R.id.progress);
    }


    private boolean isValidForm() {

        if (etNra.getText().toString().isEmpty()) {
            Toast.makeText(context, "NRA is required", Toast.LENGTH_SHORT).show();
            return false;
        }

//        if (etUsername.getText().toString().isEmpty()) {
//            Toast.makeText(context, "Username is required", Toast.LENGTH_SHORT).show();
//            return false;
//        }

        if (etName.getText().toString().isEmpty()) {
            Toast.makeText(context, "Name is required", Toast.LENGTH_SHORT).show();
            btnRegister.setEnabled(false);
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

        if (etConPass.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please confirm your password", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!etConPass.getText().toString().trim().equals(etPassword.getText().toString().trim())){
            Toast.makeText(context, "Password not matching", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Validator.isEmailValid(etEmail.getText().toString())) {
            Toast.makeText(context, "Email is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setLoading(boolean isLoading, boolean isRefresh) {
        this.isLoading = isLoading;
        if (isLoading == true && isRefresh == false) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    private void loadData() {
        btnRegister.setEnabled(true);
//        Map<String, String> keyNra = new HashMap<>();
//        keyNra.put("nra", keyword);
        ConnectionApi.apiService(context).checkNra(keyword).enqueue(new Callback<ResponseCheckNra>() {

            @Override
            public void onResponse(Call<ResponseCheckNra> call, Response<ResponseCheckNra> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().isSuccess()) {
//                        adapter.setItems(response.body().getData());
                        etName.setText(response.body().getData().getName());
                        etEmail.setText(response.body().getData().getEmail());
                        if (response.body().getData().getName().equals("")){
                            btnRegister.setEnabled(false);
                        }
                    }
                    else {
                        etName.setText("");
                        etEmail.setText("");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCheckNra> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkNra(){
        etNra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyword = s.toString();
                setLoading(true, true);
                loadData();
            }

            @Override
            public void afterTextChanged(Editable s) {

//                if (nameAddress != "") {
//                    rvAddress.setVisibility(View.GONE);
//                } else {
//                    getAddress();
//                }
            }
        });

//        etNra.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
////                    etNote.setVisibility(View.VISIBLE);
//                    getAddress();
//                    hideKeyboard();
//                    return true;
//                }
//                return false;
//            }
//        });
    }

    private void removeNra(){
        etNra.removeTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyword = s.toString();
                setLoading(true, true);
                loadData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
