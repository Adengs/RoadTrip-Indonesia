package app.codelabs.forum.activities.club.post;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsListArticelbyCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    RecyclerView recyclerView;
    private PostAdapter adapter;
    Context context;
    private Session session;//definisi variabel session dengan tipe data session
    private String token;
    private String apptoken;
    private Integer id ;
    ResponsListArticelbyCategory.DataEntity data = new ResponsListArticelbyCategory.DataEntity();

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
        session = Session.init(context);
        apptoken = session.getAppToken();
        token = session.getToken();
        id = data.getCategory_id();

        setView(view);
        setRecycleView();
        loadData();
    }

    private void loadData() {
        ConnectionApi.apiService().listarticel(id,token,apptoken).enqueue(new Callback<ResponsListArticelbyCategory>() {
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
