package app.codelabs.forum.activities.forgotpassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.login.LoginActivity;
import app.codelabs.forum.activities.login.ProgresDialogFragment;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponseForgotPassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    ImageView btnBackForgot;
    Button btnSendForgot;
    EditText et_EmailForgot;
    Session session;
    private String apptoken;
    Context context;
    private ProgresDialogFragment progresDialogFragment = new ProgresDialogFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        context = getApplicationContext();
        session = Session.init(context);
        apptoken = session.getAppToken();

        setView();
        setEvent();


    }




    private void setView() {
        btnBackForgot = findViewById(R.id.btn_BackForgot);
        btnSendForgot = findViewById(R.id.btn_sendforgotpass);
        et_EmailForgot = findViewById(R.id.et_emailforgotpass);

    }

    private void setEvent() {
        btnSendForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> data = new HashMap<>();
                data.put("email",et_EmailForgot.getText().toString());
                progresDialogFragment.show(getSupportFragmentManager(),"proggress");
                ConnectionApi.apiService().forgotpassword(data, apptoken).enqueue(new Callback<ResponseForgotPassword>() {
                    @Override
                    public void onResponse(Call<ResponseForgotPassword> call, Response<ResponseForgotPassword> response) {
                        progresDialogFragment.dismiss();
                        if (response.isSuccessful()&& response.body().getSuccess()){
                            Toast.makeText(context,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPasswordActivity.this, SubmitPasswordActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseForgotPassword> call, Throwable t) {
                        progresDialogFragment.dismiss();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        btnBackForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
            }
        });

    }
}
