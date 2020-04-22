package app.codelabs.forum.activities.home.fragment.profile.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.adapter.ArticleAdapter;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseBookmarkArticle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class BookmarkArticleFragment extends Fragment {
    private Context context;
    private ProgressBar progressBar;
    private TextView tvNoData;

    private RecyclerView recyclerView;
    private ArticleAdapter adapter;

    public BookmarkArticleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        setView(view);
        setRecyclerview();
        getData();
    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        tvNoData.setVisibility(View.GONE);
        ConnectionApi.apiService(context).getBookmarkArticle().enqueue(new Callback<ResponseBookmarkArticle>() {
            @Override
            public void onResponse(Call<ResponseBookmarkArticle> call, Response<ResponseBookmarkArticle> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        adapter.setItems(response.body().getData());
                    }
                }
                if(adapter.getItemCount() == 0){
                    tvNoData.setVisibility(View.VISIBLE);
                }else{
                    tvNoData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBookmarkArticle> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setRecyclerview() {
        adapter = new ArticleAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView(View view) {
        progressBar = view.findViewById(R.id.progressbar);
        recyclerView = view.findViewById(R.id.recyclerview);
        tvNoData = view.findViewById(R.id.tv_no_data);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == ArticleAdapter.REQ_REFRESH_ARTICLE_LIST && resultCode == RESULT_OK){
            adapter.setItems(new ArrayList());
            getData();
        }
    }
}
