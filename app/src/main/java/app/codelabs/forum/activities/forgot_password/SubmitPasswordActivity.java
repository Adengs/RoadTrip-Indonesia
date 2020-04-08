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
import app.codelabs.forum.activities.login.ProgresDialogFragment;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponseSubmitPassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitPasswordActivity extends AppCompatActivity {
    private ImageView ivBack;
    private Button btnSubmit;
    private EditText etCode1, etCode2, etCode3, etCode4;
    private Session session;
    private Context context;
    private ProgresDialogFragment progresDialogFragment = new ProgresDialogFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_password);
        context = getApplicationContext();
        session = Session.init(context);

        setView();
        setEvent();
    }

    private void setView() {

        ivBack = findViewById(R.id.btn_back);
        btnSubmit = findViewById(R.id.btn_submit);
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

                progresDialogFragment.show(getSupportFragmentManager(), "progress");
                ConnectionApi.apiService(context).verifyCodeResetPassword(data).enqueue(new Callback<ResponseSubmitPassword>() {
                    @Override
                    public void onResponse(Call<ResponseSubmitPassword> call, Response<ResponseSubmitPassword> response) {
                        progresDialogFragment.dismiss();
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
                        progresDialogFragment.dismiss();
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
        if (etCode1.getText().toString().isEmpty() || etCode2.getText().toString().isEmpty() || etCode3.getText().toString().isEmpty() || etCode4.getText().toString().isEmpty()) {
            Toast.makeText(context, "Fill all code", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }
}
