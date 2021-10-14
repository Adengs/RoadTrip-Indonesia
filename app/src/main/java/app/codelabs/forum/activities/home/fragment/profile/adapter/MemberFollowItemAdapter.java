package app.codelabs.forum.activities.home.fragment.profile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.adapter.MemberAdapter;
import app.codelabs.forum.databinding.ItemMemberClubBinding;
import app.codelabs.forum.models.ResponseFollowsModel;
import app.codelabs.forum.models.ResponseListMemberCompany;

public class MemberFollowItemAdapter extends RecyclerView.Adapter<MemberFollowItemAdapter.ViewHolder> {
    private Context context;
    private List<ResponseFollowsModel.Data> items;
    private OnItemSelected listener;


    public MemberFollowItemAdapter(){
        items = new ArrayList<>();
    }

    public void setItems(List<ResponseFollowsModel.Data> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setListener(OnItemSelected listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(ItemMemberClubBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResponseFollowsModel.Data data = items.get(position);
        if (data.getUsername()!=null){
            holder.itemMemberClubBinding.tvMember.setText(data.getUsername());
        }else {
            holder.itemMemberClubBinding.tvMember.setText(data.getName());
        }
        Picasso.with(context).load(data.getPhoto())
                .placeholder(R.drawable.default_photo)
                .centerCrop().fit().into(holder.itemMemberClubBinding.ivMember);
        holder.itemMemberClubBinding.tvFollow.setVisibility(View.VISIBLE);
        holder.itemMemberClubBinding.tvFollowers.setText(data.getFollowerCount()+"");

        if (data.isIsFollowing()){
            holder.itemMemberClubBinding.tvFollow.setText("Following");
            holder.itemMemberClubBinding.tvFollow.setTextColor(Color.parseColor("#FFFFFF"));
            holder.itemMemberClubBinding.tvFollow.setBackgroundResource(R.drawable.shape_button_follow);
        }else {
            holder.itemMemberClubBinding.tvFollow.setText("+ Follow");
            holder.itemMemberClubBinding.tvFollow.setTextColor(Color.parseColor("#F62C4C"));
            holder.itemMemberClubBinding.tvFollow.setBackgroundResource(R.drawable.shape_car_club);
        }



    }

    @Override
    public int getItemCount() {
        if (items!=null) {
            return items.size();
        }else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemMemberClubBinding itemMemberClubBinding;
        public ViewHolder(@NonNull ItemMemberClubBinding itemMemberClubBinding) {
            super(itemMemberClubBinding.getRoot());
            this.itemMemberClubBinding = itemMemberClubBinding;
            itemMemberClubBinding.tvFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onFollow(items.get(getAdapterPosition()));
                }
            });
        }
    }
    public interface OnItemSelected {
        void onFollow(ResponseFollowsModel.Data data);
    }
}
