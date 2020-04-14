package app.codelabs.forum.activities.about_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import app.codelabs.forum.R;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponseAbout;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class AboutHome extends AppCompatActivity {
    private CircleImageView ivLogo;
    private TextView tvBackAbout,tvCompany_name,tvhistory,tvSecretariat;
    private Session session;
    private Toolbar toolbar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_home);

        context = getApplicationContext();
        session = Session.init(context);

        setView();
        setEvent();
        setToolbar();
        loadData();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        setTitle("About");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void loadData() {

        ConnectionApi.apiService(context).getAboutCompany().enqueue(new Callback<ResponseAbout>() {
            @Override
            public void onResponse(Call<ResponseAbout> call, Response<ResponseAbout> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()){
                        setAbout(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAbout> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAbout(ResponseAbout.DataEntity data) {
        Picasso.with(context).load(data.getLogo()).fit().centerCrop().into(ivLogo);
        tvCompany_name.setText(data.getCompany_name());
        tvhistory.setText(data.getHistory());
        tvSecretariat.setText(data.getSecretariat());

    }

    private void setEvent() {
    }

    private void setView() {
        ivLogo = findViewById(R.id.iv_logo);
        tvCompany_name = findViewById(R.id.tv_company_name);
        tvhistory = findViewById(R.id.tv_History);
        tvSecretariat = findViewById(R.id.tv_Secretariat);
        toolbar = findViewById(R.id.toolbar);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
