package app.codelabs.fevci.activities.about;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.fevci.helpers.Session;
import app.codelabs.fevci.models.ResponseAbout;
import app.codelabs.forum.R;
import app.codelabs.fevci.activities.about.adapter.SecretariatAdapter;
import app.codelabs.fevci.helpers.ConnectionApi;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutActivity extends AppCompatActivity {
    private CircleImageView ivLogo;
    private ImageView ivBackGround;
    private TextView tvBackAbout,tvCompany_name,tvhistory,tvSecretariat, tvMaps;
    private RecyclerView rvSecretariat;
    private Session session;
    private SecretariatAdapter adapter = new SecretariatAdapter();
    private Toolbar toolbar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        context = getApplicationContext();
        session = Session.init(context);

        setView();
        setEvent();
        setToolbar();
        setRecyclerView();
        loadData();
    }

    private void setRecyclerView() {
        rvSecretariat.setHasFixedSize(true);
        rvSecretariat.setLayoutManager(new LinearLayoutManager(context));
        rvSecretariat.setAdapter(adapter);
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
                        adapter.setItems(response.body().getData().getSecretariat());
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

    private void setAbout(final ResponseAbout.DataEntity data) {
        Picasso.with(context).load(data.getLogo()).fit().centerCrop().into(ivLogo);
        tvCompany_name.setText(data.getFull_name());
        tvhistory.setText(Html.fromHtml(data.getHistory()));
        Picasso.with(context).load(data.getBackground()).fit().centerCrop().into(ivBackGround);
    }

    private void setEvent() {

    }

    private void setView() {
        ivLogo = findViewById(R.id.iv_logo);
        tvCompany_name = findViewById(R.id.tv_company_name);
        tvhistory = findViewById(R.id.tv_History);
        toolbar = findViewById(R.id.toolbar);
        ivBackGround = findViewById(R.id.iv_background);
        rvSecretariat = findViewById(R.id.rv_secretariat);

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
