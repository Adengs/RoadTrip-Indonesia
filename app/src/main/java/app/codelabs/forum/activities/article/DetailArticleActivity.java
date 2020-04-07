package app.codelabs.forum.activities.article;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import app.codelabs.forum.R;
import app.codelabs.forum.models.ResponseListArticle;

public class DetailArticleActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView ivCompany, ivImage;
    private TextView tvCompanyName, tvTitle, tvContent, tvTime, tvDate, tvCategoryName;
    private Context context;
    private ResponseListArticle.Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);

        context = getApplicationContext();

        getData();
        setView();
        setEvent();
        loadData();
        setToolbar();
        setData();
    }

    private void setData() {
        tvTitle.setText(article.getTitle());
        tvCategoryName.setText(article.getCategory().getCategory());
        tvContent.setText(Html.fromHtml(article.getContent()));
        Picasso.with(context).load(article.getImage()).fit().centerCrop().into(ivImage);

    }

    private void getData() {
        if (getIntent().getStringExtra("data") != null) {
            String strData = getIntent().getStringExtra("data");
            article = new Gson().fromJson(strData, ResponseListArticle.Article.class);
        }
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("");
    }

    private void setEvent() {

    }

    private void loadData() {

    }

    private void setView() {
        toolbar = findViewById(R.id.toolbar);
        ivCompany = findViewById(R.id.iv_company);
        ivImage = findViewById(R.id.iv_image);
        tvCompanyName = findViewById(R.id.tv_company_name);
        tvTitle = findViewById(R.id.tv_title);
        tvTime = findViewById(R.id.tv_time);
        tvDate = findViewById(R.id.tv_date);
        tvCategoryName = findViewById(R.id.tv_category_name);
        tvContent = findViewById(R.id.tv_content);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_article_detail_bookmark:
                bookmarkArticle();
                break;
            case R.id.menu_article_detail_share:
                shareArticle();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareArticle() {

    }

    private void bookmarkArticle() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_article_detail, menu);
        return true;

    }
}
