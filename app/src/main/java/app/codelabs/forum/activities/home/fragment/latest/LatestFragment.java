package app.codelabs.forum.activities.home.fragment.latest;


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
import app.codelabs.forum.activities.home.fragment.latest.adapter.LatestAdapter;
import app.codelabs.forum.helpers.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestFragment extends Fragment {
    RecyclerView recyclerView;
    LatestAdapter adapter;
    Context context;
    private Session session;
    private String token;
    private String apptoken;

    public LatestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new LatestAdapter();

        setView(view);
        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.viewtablayout);

    }
}
