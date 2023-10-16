package app.codelabs.roadtrip.activities.article;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.custom.ProgressDialogFragment;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.DateTimeHelper;
import app.codelabs.roadtrip.models.ResponseArticleDetail;
import app.codelabs.roadtrip.models.ResponseBookmarkArticle;
import app.codelabs.roadtrip.models.ResponseDoBookmark;
import app.codelabs.roadtrip.models.ResponseListArticle;
import app.codelabs.roadtrip.models.ResponseShareArticle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.codelabs.roadtrip.helpers.DateTimeHelper.PATTERN_DATETIME_SPACE;

public class DetailArticleActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView ivCompany, ivImage;
//    private WebView wvYoutube;
    private YouTubePlayerView yp;
    private TextView tvCompanyName, tvTitle, tvContent, tvTime, tvDate, tvCategoryName;
    private Context context;
    private ResponseListArticle.Data article;
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private Menu menu;
    String link = "";

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
    }

    private void getYouTubeId () {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(link);
        if(matcher.find()){
            Log.e("TAG", "getYouTubeId: " + matcher.group() );
            link = matcher.group();
//            return matcher.group();
        } else {
//            return "error";
            Toast.makeText(context, "Url error", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        tvTitle.setText(article.getTitle());
        tvCategoryName.setText(article.getCategory().getCategory());
        tvContent.setText(Html.fromHtml(article.getContent()));

        tvTime.setText(DateTimeHelper.instance(article.getCreatedAt()).get(PATTERN_DATETIME_SPACE));

        if (article.getMediaType() == 1){
            ivImage.setVisibility(View.VISIBLE);
            yp.setVisibility(View.GONE);
            Picasso.with(context).load(article.getImage())
                    .placeholder(R.drawable.default_image)
                    .error(R.drawable.default_no_image)
                    .fit().centerCrop().into(ivImage);
        }else if (article.getMediaType() == 2){
            ivImage.setVisibility(View.GONE);
            yp.setVisibility(View.VISIBLE);
        }

//        link = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/se5S1r2lpWY\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
//        wvYoutube.loadData(link,"text/html","utf-8");
//        wvYoutube.getSettings().setJavaScriptEnabled(true);
//        wvYoutube.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wvYoutube.setWebChromeClient(new WebChromeClient());
        getLifecycle().addObserver(yp);
//        YouTubePlayer youTubePlayer = null;
//        link = "se5S1r2lpWY";
//        if (youTubePlayer != null) {
//            youTubePlayer.loadVideo(link, 0);
//        }
//        Log.e("TAG", "link before: " + link );

        yp.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                super.onReady(youTubePlayer);
//                link = "se5S1r2lpWY";
                Log.e("TAG", "link after: " + link );
                youTubePlayer.cueVideo(link, 0);
                youTubePlayer.pause();
//                youTubePlayer.loadVideo(link, 0);
            }
        });
    }

    private void getData() {
        if (getIntent().getStringExtra("data") != null) {
            String strData = getIntent().getStringExtra("data");
            article = new Gson().fromJson(strData, ResponseListArticle.Data.class);
            if (article.getMediaLink() != null) {
                link = article.getMediaLink();
                getYouTubeId();
            } else {
                link = "";
            }
        } else {
            progressDialogFragment.show(getSupportFragmentManager(), "detail-article");

            int articleId = getIntent().getIntExtra("article_id", 0);
            ConnectionApi.apiService(context).getArticle(articleId).enqueue(new Callback<ResponseArticleDetail>() {
                @Override
                public void onResponse(Call<ResponseArticleDetail> call, Response<ResponseArticleDetail> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful() && response.body().isSuccess()) {
                            article = response.body().getData();
                            if (article.getMediaLink() != null) {
                                link = article.getMediaLink();
                                getYouTubeId();
                            } else {
                                link = "";
                            }

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
//        wvYoutube = findViewById(R.id.wv_youtube);
        yp = findViewById(R.id.youtube_player);

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

    private void checkBookmark(List<ResponseListArticle.Data> items) {
        article.setBookmark(false);
        for (ResponseListArticle.Data item : items) {
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
