package app.codelabs.forum.activities.club.event_club;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.menu_event.JoinPickFragment;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsFollow;
import app.codelabs.forum.models.ResponsJoinEvent;
import app.codelabs.forum.models.ResponsListEventCommunity;
import app.codelabs.forum.models.ResponsListMemberCompany;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Event_Club_Fragment extends Fragment {
    RecyclerView recyclerView;
    private EventClubAdapter adapter;
    JoinPickFragment joinPickFragment = new JoinPickFragment();
    private String token;
    private String apptoken;
    Context context;
    private Session session;



    public Event_Club_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_club, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        session = Session.init(context);
        apptoken = session.getAppToken();
        token = session.getToken();

        setView(view);
        setEvent();
        setRecycleView();
        loadData();
    }

    private void setEvent() {
        adapter.setListener(new EventClubAdapter.OnItemSelection() {
            @Override
            public void onBtnJoin(ResponsListEventCommunity.DataEntity datas) {
                Fragment fragment = joinPickFragment;
                Bundle bundle = new Bundle();
                bundle.putInt("event_id",datas.getId());
                fragment.setArguments(bundle);
                joinPickFragment.show(getFragmentManager(),"pick");
            }
        });
    }

    private void loadData() {
        ConnectionApi.apiService().listEvent(token,apptoken).enqueue(new Callback<ResponsListEventCommunity>() {
            @Override
            public void onResponse(Call<ResponsListEventCommunity> call, Response<ResponsListEventCommunity> response) {
                if (response.isSuccessful() && response.body().getSuccess()){
                    adapter.setItems(response.body().getData());
                }
                else {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponsListEventCommunity> call, Throwable t) {
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView(View view) {
        adapter = new EventClubAdapter();
        recyclerView = view.findViewById(R.id.rv_event);
    }
}
