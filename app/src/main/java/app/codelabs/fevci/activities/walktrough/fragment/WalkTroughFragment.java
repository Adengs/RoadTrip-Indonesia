package app.codelabs.fevci.activities.walktrough.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.codelabs.fevci.models.ResponseWalkThrough;
import app.codelabs.forum.R;

public class WalkTroughFragment extends Fragment {
    private TextView tvNext, tvTitle, tvDesc;
    private ImageView ivBackground;
    private Context context;

    private ResponseWalkThrough.DataEntity item;

    public WalkTroughFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_walk_trough, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        setView(view);
        setData();
    }

    private void setData() {
        tvTitle.setText(item.getTitle());
        tvDesc.setText(Html.fromHtml(item.getDescription()));
        Picasso.with(context).load(item.getImage()).fit().centerCrop().into(ivBackground);
    }

    private void setView(View view) {
        tvTitle = view.findViewById(R.id.tv_title);
        tvDesc = view.findViewById(R.id.tv_desc);
        ivBackground = view.findViewById(R.id.iv_background);
    }


    public WalkTroughFragment setData(ResponseWalkThrough.DataEntity item) {
        this.item = item;
        return this;
    }
}
