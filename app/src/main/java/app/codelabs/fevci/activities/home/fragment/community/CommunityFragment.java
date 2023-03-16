package app.codelabs.fevci.activities.home.fragment.community;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import app.codelabs.fevci.activities.article.fragment.ArticleFragment;
import app.codelabs.forum.R;
import app.codelabs.fevci.activities.home.adapter.PagerAdapter;
import app.codelabs.fevci.activities.home.fragment.community.fragment.AboutFragment;
import app.codelabs.fevci.activities.home.fragment.community.fragment.EventFragment;
import app.codelabs.fevci.activities.home.fragment.community.fragment.GalleryFragment;
import app.codelabs.fevci.activities.home.fragment.community.fragment.MemberFragment;
import app.codelabs.fevci.helpers.ConnectionApi;
import app.codelabs.fevci.models.ResponseAbout;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityFragment extends Fragment {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TabLayout tabLayout;
    private Context context;
    private ImageView ivBackGround;
    private CircleImageView ivLogo;
    private TextView tvCompanyName;



    public CommunityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        setView(view);
        adapter = new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        setViewPager();
        loadData();

    }
    private void loadData() {
        ConnectionApi.apiService(context).getAboutCompany().enqueue(new Callback<ResponseAbout>() {
            @Override
            public void onResponse(Call<ResponseAbout> call, Response<ResponseAbout> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()){
                        setBackGroud((ResponseAbout.DataEntity) response.body().getData());
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseAbout> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setBackGroud(ResponseAbout.DataEntity data) {
        tvCompanyName.setText(data.getFull_name());
        Picasso.with(context).load(data.getLogo()).fit().centerCrop().into(ivLogo);
        Picasso.with(context).load(data.getBackground()).fit().centerCrop().into(ivBackGround);
    }


    private void setViewPager() {
        adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ArticleFragment().setType(ArticleFragment.POST), "Post");
        adapter.addFragment(new MemberFragment(), "Member");
        adapter.addFragment(new EventFragment(), "Event");
        adapter.addFragment(new GalleryFragment(), "Gallery");
        adapter.addFragment(new AboutFragment(), "About");

        this.viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(this.viewPager);
    }

    private void setView(View view) {
        viewPager = view.findViewById(R.id.view_pager_club);
        tabLayout = view.findViewById(R.id.tab_layout);
        tvCompanyName = view.findViewById(R.id.tv_company_name);
        ivBackGround = view.findViewById(R.id.iv_background);
        ivLogo = view.findViewById(R.id.iv_logo);
    }

}

