package app.codelabs.roadtrip.activities.home.fragment.profile.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.codelabs.roadtrip.models.ResponseLogin;
import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.helpers.Session;
import app.codelabs.roadtrip.models.EventBusClass;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileUserFragment extends Fragment {
    private Session session;
    private Context context;

    private TextView tvName, tvEmail, tvDob, tvChapter, tvPhone, tvNameKta, tvNraKta;
    private CircleImageView imageKta;
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
//        tvChapter.setText(user.getChapter());
        tvDob.setText(user.getDateBirth());
        tvPhone.setText(user.getPhone());

        Picasso.with(context).load(user.getPhoto())
                .placeholder(R.drawable.default_photo)
                .fit().centerCrop().into(imageKta);

        tvNameKta.setText(user.getName());
        if (user.getNra().isEmpty()){
            tvNraKta.setText(" xxx xx xxxxx");
        }else {
            tvNraKta.setText(user.getNra());
        }

    }

    private void setView(View view) {
        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);
        tvDob = view.findViewById(R.id.tv_dob);
        tvChapter = view.findViewById(R.id.tv_chapter);
        tvPhone = view.findViewById(R.id.tv_phone);
        tvNameKta = view.findViewById(R.id.textView_name);
        tvNraKta = view.findViewById(R.id.textView_noId);
        imageKta = view.findViewById(R.id.image_profile);
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
