package app.codelabs.forum.activities.article_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.article_home.adapter.TabLayoutArticleAdapter;
import app.codelabs.forum.activities.article_home.fragment.ArticleForyouFragment;
import app.codelabs.forum.activities.article_home.fragment.ArticleReviewFragment;
import app.codelabs.forum.activities.article_home.fragment.ArticleTipsFragment;
import app.codelabs.forum.activities.home.fragment.adapter.TabLayoutAdapter;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class ArticleHome extends AppCompatActivity {
TextView titlebackArticle;
TabLayout tabLayout;
TabLayoutArticleAdapter tabLayoutArticleAdapter;
ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_home);

        setView();
        setViewPager();
    }

    private void setView() {
        titlebackArticle = findViewById(R.id.toolbar_titleBackArticle);
        tabLayout = findViewById(R.id.tab_layoutarticle);
        viewPager = findViewById(R.id.viewpagerarticle);
    }

    private void setViewPager() {
        tabLayoutArticleAdapter = new TabLayoutArticleAdapter(getSupportFragmentManager());

        tabLayoutArticleAdapter.addFragment(new ArticleTipsFragment(),"Tips & tricks");
        tabLayoutArticleAdapter.addFragment(new ArticleReviewFragment(),"Review");
        tabLayoutArticleAdapter.addFragment(new ArticleForyouFragment(),"For you");
        viewPager.setAdapter(tabLayoutArticleAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
