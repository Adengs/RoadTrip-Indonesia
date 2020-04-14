package app.codelabs.forum.activities.community.post;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseListArticelbyCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private Context context;
    private Integer id ;
    private ResponseListArticelbyCategory.DataEntity data = new ResponseListArticelbyCategory.DataEntity();

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        context = getContext();
        id = data.getCategory_id();

        setView(view);
        setRecycleView();
        loadData();
    }

    private void loadData() {
        ConnectionApi.apiService(context).listArticle(id).enqueue(new Callback<ResponseListArticelbyCategory>() {
            @Override
            public void onResponse(Call<ResponseListArticelbyCategory> call, Response<ResponseListArticelbyCategory> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        adapter.setItems(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListArticelbyCategory> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView(View view) {
        adapter = new PostAdapter();
        recyclerView = view.findViewById(R.id.rv_post);
    }
}
