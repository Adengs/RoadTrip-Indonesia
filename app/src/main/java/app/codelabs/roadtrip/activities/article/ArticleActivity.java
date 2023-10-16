package app.codelabs.roadtrip.activities.article;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import app.codelabs.roadtrip.activities.article.fragment.ArticleFragment;
import app.codelabs.roadtrip.activities.home.adapter.TabLayoutAdapter;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.ResponseArticleCategory;
import app.codelabs.roadtrip.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private TabLayout tabLayout;
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
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        setTabLayout(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseArticleCategory> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
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
        setTitle("Articles");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
