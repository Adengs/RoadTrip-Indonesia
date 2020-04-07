package app.codelabs.forum.activities.article;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.article.adapter.TabLayoutArticleAdapter;
import app.codelabs.forum.activities.article.fragment.ArticleForyouFragment;
import app.codelabs.forum.activities.article.fragment.ArticleReviewFragment;
import app.codelabs.forum.activities.article.fragment.ArticleTipsFragment;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.home.fragment.ArticleFragment;
import app.codelabs.forum.activities.home.fragment.adapter.TabLayoutAdapter;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseArticleCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class ArticleActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private TabLayoutArticleAdapter tabLayoutArticleAdapter;
    private ViewPager viewPager;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        context = getApplicationContext();
        setView();
        setEvent();
        setToolbar();
        getCategories();
    }

    private void getCategories() {
        ConnectionApi.apiService(context).getArticleCategories().enqueue(new Callback<ResponseArticleCategory>() {
            @Override
            public void onResponse(Call<ResponseArticleCategory> call, Response<ResponseArticleCategory> response) {
                if (response.isSuccessful() && response.body().getSuccess()) {
                    setTabLayout(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ResponseArticleCategory> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTabLayout(List<ResponseArticleCategory.Category> data) {
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getSupportFragmentManager());

        for (ResponseArticleCategory.Category item : data) {
            tabLayoutAdapter.addFragment(new ArticleFragment().setTypeAndReferenceId(ArticleFragment.CATEGORY,
                    item.getId()), item.getCategory());
        }
        viewPager.setAdapter(tabLayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Articles");
    }

    private void setEvent() {

    }

    private void setView() {
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);
        progressBar = findViewById(R.id.progressbar);
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
