package app.codelabs.fevci.activities.home.fragment.profile.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.codelabs.fevci.models.ResponseLogin;
import app.codelabs.forum.R;
import app.codelabs.fevci.helpers.Session;
import app.codelabs.fevci.models.EventBusClass;

public class ProfileUserFragment extends Fragment {
    private Session session;
    private Context context;

    private TextView tvName, tvEmail, tvDob, tvCity;
    public ProfileUserFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        session = Session.init(context);
        setView(view);
        setProfileToView();
    }

    private void setProfileToView() {
        ResponseLogin.Data user = session.getUser();

        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
        tvCity.setText(user.getCity());
        tvDob.setText(user.getDate_birth());

    }

    private void setView(View view) {
        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);
        tvDob = view.findViewById(R.id.tv_dob);
        tvCity = view.findViewById(R.id.tv_city);
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
    public void onUpdateProfile(EventBusClass.Profile profile){
        setProfileToView();
    }
}
