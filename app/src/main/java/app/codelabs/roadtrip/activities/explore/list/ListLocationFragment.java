package app.codelabs.roadtrip.activities.explore.list;

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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.CategoryExploreSelected;
import app.codelabs.roadtrip.models.EventBusClass;
import app.codelabs.roadtrip.models.ResponseListLocation;
import app.codelabs.roadtrip.models.ResponseListShopByCategories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListLocationFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterListLocation adapter;
    private Context context;
    private String search;
    public final static int CATEGORIES = 0;
    int id = 1;

    public ListLocationFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        setView(view);
        setRecyclerView();
//        loadData();
        getListByCategory();
    }

    private void loadData() {
        if (getArguments() != null) {
            int type = getArguments().getInt("type", 0);
            if (type == CATEGORIES) {
                getListByCategory();
            }
        }

    }

    private void getListByCategory() {
//        if (getArguments() != null) {
//            int referenceId = getArguments().getInt("reference_id", 0);
            ConnectionApi.apiService(context).getListByCategories(id, "").enqueue(new Callback<ResponseListLocation>() {
                @Override
                public void onResponse(Call<ResponseListLocation> call, Response<ResponseListLocation> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful() & response.body().getSuccess()) {
                            setListLocation(response.body().getData());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseListLocation> call, Throwable t) {
                    if (t.getMessage() != null) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
//        }
    }

    private void setListLocation(List<ResponseListLocation.DataEntity> data) {
        adapter.setItems(data);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview_list);
        adapter = new AdapterListLocation(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectCategory(CategoryExploreSelected categoryExploreSelected){
        id = categoryExploreSelected.id;
        getListByCategory();
    }
}
