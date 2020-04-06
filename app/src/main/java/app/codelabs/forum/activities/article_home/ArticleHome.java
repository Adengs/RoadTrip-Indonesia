package app.codelabs.forum.activities.article_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.article_home.adapter.TabLayoutArticleAdapter;
import app.codelabs.forum.activities.article_home.fragment.ArticleForyouFragment;
import app.codelabs.forum.activities.article_home.fragment.ArticleReviewFragment;
import app.codelabs.forum.activities.article_home.fragment.ArticleTipsFragment;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.home.fragment.adapter.TabLayoutAdapter;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ArticelCategory;
import app.codelabs.forum.models.ResponsListArticelbyCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ArticleHome extends AppCompatActivity {
    private TextView titlebackArticle;
    private TabLayout tabLayout;
    private TabLayoutArticleAdapter tabLayoutArticleAdapter;
    private ViewPager viewPager ;
    private Context context;
    private Session session;
    private String token;
    private String appToken;
    private ArticelCategory.DataEntity data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_home);

        context = getApplicationContext();
        session = Session.init(context);
        appToken = session.getAppToken();
        token = session.getToken();



        setView();
        setViewPager();
        setEvent();
    }

    private void setEvent() {
        titlebackArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticleHome.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setView() {
        titlebackArticle = findViewById(R.id.toolbar_titleBackArticle);
        tabLayout = findViewById(R.id.tab_layoutarticle);
        viewPager = findViewById(R.id.viewpagerarticle);
    }

    private void setViewPager() {
        ConnectionApi.apiService().dataCategory(token, appToken).enqueue(new Callback<ArticelCategory>() {
            @Override
            public void onResponse(Call<ArticelCategory> call, Response<ArticelCategory> response) {
                if (response.isSuccessful() && response.body().getSuccess()) {
                    tabLayoutArticleAdapter = new TabLayoutArticleAdapter(getSupportFragmentManager());
                    Integer category_id;
                    for(category_id = data.getId(); category_id <= data.getId(); category_id++){
                        Bundle bundle = new Bundle();
                        bundle.putInt("category_id",data.getId());
                        ArticleTipsFragment articleTipsFragment = new ArticleTipsFragment();
                        articleTipsFragment.setArguments(bundle);
                        tabLayoutArticleAdapter.addFragment(articleTipsFragment,data.getCategory());
                        ArticleReviewFragment articleReviewFragment = new ArticleReviewFragment();
                        articleReviewFragment.setArguments(bundle);
                        tabLayoutArticleAdapter.addFragment(articleReviewFragment, data.getCategory());
                        ArticleForyouFragment articleForyouFragment = new ArticleForyouFragment();
                        articleForyouFragment.setArguments(bundle);
                        tabLayoutArticleAdapter.addFragment(articleForyouFragment, data.getCategory());
                        viewPager.setAdapter(tabLayoutArticleAdapter);
                        tabLayout.setupWithViewPager(viewPager);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArticelCategory> call, Throwable t) {

            }


        });
    }
}
