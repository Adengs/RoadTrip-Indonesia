package app.codelabs.forum.activities.menu_event;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.activities.event.description.DescriptionFragment;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsFollow;
import app.codelabs.forum.models.ResponsJoinEvent;
import app.codelabs.forum.models.ResponsListEventCommunity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinPickFragment extends BottomSheetDialogFragment {
    private Button btnJoin, btnCancel;
    private Boolean isJoin;
    private Context context;
    private Integer id;


    public JoinPickFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join_pick, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        Bundle bundle = this.getArguments();
        id = bundle.getInt("event_id",0);
        setView(view);
        setEvent();

    }

    private void setEvent() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MenuEventActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> dataJoin = new HashMap<>();
                dataJoin.put("event_id", String.valueOf(id));
               ConnectionApi.apiService(context).joinEvent(dataJoin).enqueue(new Callback<ResponsJoinEvent>() {
                    @Override
                   public void onResponse(Call<ResponsJoinEvent> call, Response<ResponsJoinEvent> response) {
                        if (response.isSuccessful() && response.body().getSuccess()) {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, EventActivity.class);
                            intent.putExtra("is_join",true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsJoinEvent> call, Throwable t) {
                        Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
        private void setView (View view){
            btnCancel = view.findViewById(R.id.btn_cancel);
            btnJoin = view.findViewById(R.id.btn_join);

        }
    }
