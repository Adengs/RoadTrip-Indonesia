package app.codelabs.fevci.activities.article.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.fevci.activities.home.adapter.ArticleAdapter;
import app.codelabs.fevci.helpers.ConnectionApi;
import app.codelabs.fevci.models.ResponseListArticle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
    private Context context;

    public final static int LATEST = 0;
    public final static int POPULAR = 1;
    public final static int CATEGORY = 2;
    public final static int POST = 3;

    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        setView(view);
        setRecyclerView();
        loadData();
    }

    private void loadData() {
        if (getArguments() != null) {
            int type = getArguments().getInt("type", 0);
            if (type == LATEST) {
                getLatestArticle();
            } else if (type == POPULAR) {
                getPopularArticle();
            } else if (type == CATEGORY) {
                getArticleByCategory();
            } else if (type == POST ){
                getArticleByCategory();
            }
        }
    }

    private void getArticleByCategory() {
        if (getArguments() != null) {
            int referenceId = getArguments().getInt("reference_id", 0);
            ConnectionApi.apiService(context).getArticleByCategory(referenceId).enqueue(new Callback<ResponseListArticle>() {
                @Override
                public void onResponse(Call<ResponseListArticle> call, Response<ResponseListArticle> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful() & response.body().getSuccess()) {
                            setArticles(response.body().getData());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseListArticle> call, Throwable t) {
                    if (t.getMessage() != null) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void getPopularArticle() {
        ConnectionApi.apiService(context).getPopularArticle().enqueue(new Callback<ResponseListArticle>() {
            @Override
            public void onResponse(Call<ResponseListArticle> call, Response<ResponseListArticle> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() & response.body().getSuccess()) {
                        setArticles(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListArticle> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getLatestArticle() {
        ConnectionApi.apiService(context).getLatestArticle().enqueue(new Callback<ResponseListArticle>() {
            @Override
            public void onResponse(Call<ResponseListArticle> call, Response<ResponseListArticle> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() & response.body().getSuccess()) {
                        setArticles(response.body().getData());
                    }else {
                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListArticle> call, Throwable t) {
                if(t.getMessage() != null){
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setArticles(List<ResponseListArticle.Article> data) {
        adapter.setItems(data);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.viewtablayout);
        adapter = new ArticleAdapter(this);

    }

    public ArticleFragment setType(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        setArguments(args);
        return this;
    }

    public ArticleFragment setTypeAndReferenceId(int type, int referenceId) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putInt("reference_id", referenceId);
        setArguments(args);
        return this;
    }
}
