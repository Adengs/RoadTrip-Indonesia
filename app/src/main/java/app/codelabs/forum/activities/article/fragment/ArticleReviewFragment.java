package app.codelabs.forum.activities.article.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.article.adapter.ArticleReviewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleReviewFragment extends Fragment {
RecyclerView recyclerView;
ArticleReviewAdapter adapter;
Context context;

    public ArticleReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ArticleReviewAdapter();

        setView(view);
        setRecyclerView();
        setEvent();
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.viewarticletablayout);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setEvent() {

    }
}
