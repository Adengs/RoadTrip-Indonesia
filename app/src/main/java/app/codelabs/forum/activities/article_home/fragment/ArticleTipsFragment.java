package app.codelabs.forum.activities.article_home.fragment;


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
import app.codelabs.forum.activities.article_home.adapter.ArticleTipsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleTipsFragment extends Fragment {
    RecyclerView recyclerView;
    ArticleTipsAdapter adapter;
    Context context;

    public ArticleTipsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_tips, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ArticleTipsAdapter();

        setView(view);
        setEvent();
        setRecyclerView();
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.viewarticletablayout);
    }

    private void setEvent() {

    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }
}
