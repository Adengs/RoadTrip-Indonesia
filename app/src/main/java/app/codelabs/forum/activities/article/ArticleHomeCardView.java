package app.codelabs.forum.activities.article;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsDetailList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ArticleHomeCardView extends AppCompatActivity {
    TextView txtcarhome, timehome,datehome,txtmobilhome,deschome;
    ImageView img_panahkirihome, img_savedhome,img_sharehome,img_logohome,img_carhome,circlehome;
    View vwline, linehome;
    private Context context;
    private Session session;
    private Integer id;
    ResponsDetailList.DataEntity data = new ResponsDetailList.DataEntity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_home_card_view);

        context = getApplicationContext();
        session = Session.init(context);
        id = data.getId();


        setView();
        setEvent();
        loadData();
    }

    private void loadData() {
        ConnectionApi.apiService(context).detailArticel(id).enqueue(new Callback<ResponsDetailList>() {
            @Override
            public void onResponse(Call<ResponsDetailList> call, Response<ResponsDetailList> response) {
                if (response.isSuccessful() && response.body().getSuccess()){
                    txtcarhome.setText(response.body().getData().getTitle());
                    deschome.setText(response.body().getData().getContent());
                    Picasso.with(context).load(response.body().getData().getImage()).centerCrop().fit().into(img_carhome);
                }

            }

            @Override
            public void onFailure(Call<ResponsDetailList> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setEvent() {
        img_panahkirihome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticleHomeCardView.this, ArticleActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setView() {
        txtcarhome = findViewById(R.id.txtcarhome);
        timehome = findViewById(R.id.timehome);
        circlehome = findViewById(R.id.circlehome);
        datehome = findViewById(R.id.datehome);
        txtmobilhome = findViewById(R.id.txtmobilhome);
        deschome = findViewById(R.id.deschome);
        img_panahkirihome = findViewById(R.id.img_panahkirihome);
        img_savedhome = findViewById(R.id.img_savedhome);
        img_sharehome = findViewById(R.id.img_sharehome);
        img_logohome = findViewById(R.id.img_logohome);
        img_carhome = findViewById(R.id.img_carhome);
        vwline = findViewById(R.id.vwline);
        linehome = findViewById(R.id.linehome);
    }
}
