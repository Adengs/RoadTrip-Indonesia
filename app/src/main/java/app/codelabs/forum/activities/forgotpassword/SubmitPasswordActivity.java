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
import app.codelabs.forum.activities.login.ProgresDialogFragment;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponseSubmitPassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitPasswordActivity extends AppCompatActivity {
    ImageView btnBackSubmit;
    Button btnSubmitForgot;
    String code ="";
    String codesatu,codedua,codetiga,codeempat;
    EditText codes, coded, codet, codee;
    Session session;
    private String apptoken;
    Context context;
    private ProgresDialogFragment progresDialogFragment = new ProgresDialogFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_password);
        context = getApplicationContext();
        session = Session.init(context);
        apptoken = session.getAppToken();

        setView();
        setEvent();
    }

    private void setView() {

        btnBackSubmit = findViewById(R.id.btn_BackSubmit);
        btnSubmitForgot = findViewById(R.id.btn_SubmitForgot);
        codes = findViewById(R.id.et_codesatu);
        coded = findViewById(R.id.et_codedua);
        codet = findViewById(R.id.et_codetiga);
        codee = findViewById(R.id.et_codeempat);


    }

    private void setEvent() {
        btnSubmitForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codesatu = codes.getText().toString();
                codedua = coded.getText().toString();
                codetiga = codet.getText().toString();
                codeempat = codee.getText().toString();
                code = codesatu+codedua+codetiga+codeempat;

                Map<String, String> data = new HashMap<>();
                data.put("code",code);

                progresDialogFragment.show(getSupportFragmentManager(), "proggress");
                ConnectionApi.apiService().submitpassword(data, apptoken).enqueue(new Callback<ResponseSubmitPassword>() {
                    @Override
                    public void onResponse(Call<ResponseSubmitPassword> call, Response<ResponseSubmitPassword> response) {
                        progresDialogFragment.dismiss();
                        if (response.isSuccessful() && response.body().getSuccess()) {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            session.setXresetToken(response.body().getData().getToken());
                            startActivity(new Intent(SubmitPasswordActivity.this, FinishPasswordActivity.class));

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

        btnBackSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubmitPasswordActivity.this, ForgotPasswordActivity.class));

            }
        });
    }
}
