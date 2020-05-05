package app.codelabs.forum.activities.article;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.custom.ProgressDialogFragment;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.DateTimeHelper;
import app.codelabs.forum.models.ResponseArticleDetail;
import app.codelabs.forum.models.ResponseBookmarkArticle;
import app.codelabs.forum.models.ResponseDoBookmark;
import app.codelabs.forum.models.ResponseListArticle;
import app.codelabs.forum.models.ResponseShareArticle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.codelabs.forum.helpers.DateTimeHelper.PATTERN_DATETIME_SPACE;

public class DetailArticleActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView ivCompany, ivImage;
    private TextView tvCompanyName, tvTitle, tvContent, tvTime, tvDate, tvCategoryName;
    private Context context;
    private ResponseListArticle.Article article;
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);

        context = getApplicationContext();
        setView();
        getData();
        setToolbar();

        if (getIntent().getStringExtra("data") != null) {
            progressDialogFragment.show(getSupportFragmentManager(), "detail-article");
            setData();
            getBookmark();
        }
        callArticle();
    }

    private void callArticle() {
        ConnectionApi.apiService(context).getArticle(article.getId()).enqueue(new Callback<ResponseArticleDetail>() {
            @Override
            public void onResponse(Call<ResponseArticleDetail> call, Response<ResponseArticleDetail> response) {

            }

            @Override
            public void onFailure(Call<ResponseArticleDetail> call, Throwable t) {

            }
        });

    }

    private void setData() {
        tvTitle.setText(article.getTitle());
        tvCategoryName.setText(article.getCategory().getCategory());
        tvContent.setText(Html.fromHtml(article.getContent()));
        Picasso.with(context).load(article.getImage())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .fit().centerCrop().into(ivImage);

        tvTime.setText(DateTimeHelper.instance(article.getCreated_at()).get(PATTERN_DATETIME_SPACE));
    }

    private void getData() {
        if (getIntent().getStringExtra("data") != null) {
            String strData = getIntent().getStringExtra("data");
            article = new Gson().fromJson(strData, ResponseListArticle.Article.class);
        } else {
            progressDialogFragment.show(getSupportFragmentManager(), "detail-article");

            int articleId = getIntent().getIntExtra("article_id", 0);
            ConnectionApi.apiService(context).getArticle(articleId).enqueue(new Callback<ResponseArticleDetail>() {
                @Override
                public void onResponse(Call<ResponseArticleDetail> call, Response<ResponseArticleDetail> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful() && response.body().isSuccess()) {
                            article = response.body().getData();
                            setData();
                            getBookmark();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseArticleDetail> call, Throwable t) {
                    progressDialogFragment.dismiss();
                    if (t.getMessage() != null) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("");
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
            case R.id.menu_bookmark:
                bookmarkArticle();
                break;
            case R.id.menu_article_detail_share:
                shareArticle();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareArticle() {
        progressDialogFragment.show(getSupportFragmentManager(), "detail-article");

        ConnectionApi.apiService(context).doShareArticle(article.getId()).enqueue(new Callback<ResponseShareArticle>() {
            @Override
            public void onResponse(Call<ResponseShareArticle> call, Response<ResponseShareArticle> response) {
                progressDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                        intent.putExtra(Intent.EXTRA_TEXT, response.body().getData());
                        startActivity(Intent.createChooser(intent, "Share URL"));
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseShareArticle> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bookmarkArticle() {
        progressDialogFragment.show(getSupportFragmentManager(), "detail-article");
        ConnectionApi.apiService(context).doBookmark(article.getId(), "article").enqueue(new Callback<ResponseDoBookmark>() {
            @Override
            public void onResponse(Call<ResponseDoBookmark> call, Response<ResponseDoBookmark> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        setResult(RESULT_OK);
                        getBookmark();
                    }
                } else {
                    progressDialogFragment.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ResponseDoBookmark> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getBookmark() {
        ConnectionApi.apiService(context).getBookmarkArticle().enqueue(new Callback<ResponseBookmarkArticle>() {
            @Override
            public void onResponse(Call<ResponseBookmarkArticle> call, Response<ResponseBookmarkArticle> response) {
                progressDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess()) {
                            checkBookmark(response.body().getData());
                        } else {
                            checkBookmark(new ArrayList());
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBookmarkArticle> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkBookmark(List<ResponseListArticle.Article> items) {
        article.setBookmark(false);
        for (ResponseListArticle.Article item : items) {
            if (item.getId() == article.getId()) {
                article.setBookmark(true);
                break;
            }
        }

        refreshToolbar();
    }

    private void refreshToolbar() {
        if (menu != null) {
            if (article.isBookmark()) {
                menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_fill));
            } else {
                menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_article_detail, menu);
        this.menu = menu;
        return true;

    }
}
