package app.codelabs.forum.activities.article_home.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.article_home.adapter.ArticleTipsAdapter;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsListArticelbyCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleTipsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArticleTipsAdapter adapter;
    private Context context;
    private Session session;
    private String token;
    private String appToken;
    private Integer category_id ;
    private ResponsListArticelbyCategory.DataEntity data = new ResponsListArticelbyCategory.DataEntity();
    //private List<ResponsListArticelbyCategory.DataEntity> data = new ArrayList<>();



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
        context = getContext();
        session = Session.init(context);
        appToken = session.getAppToken();
        token = session.getToken();


        setView(view);
        setRecyclerView();
        loadData();
    }

    private void loadData() {
        ConnectionApi.apiService().listarticel(category_id,token,appToken).enqueue(new Callback<ResponsListArticelbyCategory>() {
            @Override
            public void onResponse(Call<ResponsListArticelbyCategory> call, Response<ResponsListArticelbyCategory> response) {
                if (response.isSuccessful() && response.body().getSuccess()){
                    
                        adapter.setItems(response.body().getData());
                }
                else {

                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsListArticelbyCategory> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.viewarticletablayout);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }
}
