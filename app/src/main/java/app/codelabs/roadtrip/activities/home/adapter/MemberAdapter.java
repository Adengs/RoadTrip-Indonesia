package app.codelabs.roadtrip.activities.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.roadtrip.activities.home.fragment.community.DetailMemberActivity;
import app.codelabs.roadtrip.helpers.Session;
import app.codelabs.roadtrip.models.ResponseListMemberCompany;
import app.codelabs.roadtrip.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MyHolder> {
    private Context context;
    private List<ResponseListMemberCompany.Data> items;
    private OnItemSelected listener;

    public MemberAdapter() {
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_member_club, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final ResponseListMemberCompany.Data data = items.get(position);
        holder.tvNama.setText(data.getName());
        holder.tvfollowers.setText(String.valueOf(data.getFollowers()));
//        holder.tvChapter.setText(data.getChapter());
//        holder.nra.setText(String.valueOf(data.getNra()));
        Picasso.with(context).load(data.getPhoto())
                .placeholder(R.drawable.default_photo)
                .centerCrop().fit().into(holder.ivMember);

        if (data.getId() == Session.init(context).getUser().getId()) {
            holder.tvfollow.setVisibility(View.INVISIBLE);
        }else{
            holder.tvfollow.setVisibility(View.VISIBLE);
        }

        if (data.getIs_following() == true) {
            holder.tvfollow.setText("Following");
            holder.tvfollow.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tvfollow.setBackgroundResource(R.drawable.shape_button_follow);
        } else {
            holder.tvfollow.setText("+ Follow");
            holder.tvfollow.setTextColor(Color.parseColor("#F62C4C"));
            holder.tvfollow.setBackgroundResource(R.drawable.shape_car_club);
        }

        holder.containerMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "cek", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailMemberActivity.class);
                intent.putExtra("user_id", data.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ResponseListMemberCompany.Data> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    public void addItems(List<ResponseListMemberCompany.Data> data) {
        this.items.addAll(data);
        notifyDataSetChanged();
    }

    public void setListener(OnItemSelected listener) {
        this.listener = listener;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvfollowers, tvfollow, tvChapter, nra;
        CircleImageView ivMember;
        LinearLayout containerMember;

        public MyHolder(@NonNull View view) {
            super(view);

            setView(view);
            setEvent();

        }

        private void setEvent() {

            tvfollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponseListMemberCompany.Data data = items.get(getAdapterPosition());
                    listener.onFollow(data);
                }
            });

        }

        private void setView(View view) {
            tvNama = view.findViewById(R.id.tv_Member);
            tvfollowers = view.findViewById(R.id.tv_followers);
            ivMember = view.findViewById(R.id.iv_Member);
            tvfollow = view.findViewById(R.id.tv_follow);
            tvfollow = view.findViewById(R.id.tv_follow);
            tvChapter = view.findViewById(R.id.tv_chapter);
            nra = view.findViewById(R.id.tv_Nra);
            containerMember = view.findViewById(R.id.container_member);
        }
    }

    public interface OnItemSelected {
        void onFollow(ResponseListMemberCompany.Data data);
    }

}
