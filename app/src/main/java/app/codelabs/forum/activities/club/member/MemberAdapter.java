package app.codelabs.forum.activities.club.member;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.models.ResponsListMemberCompany;
import de.hdodenhof.circleimageview.CircleImageView;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MyHolder> {
    private Context context;
    private List<ResponsListMemberCompany.Data>items;

    public MemberAdapter(){
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
        ResponsListMemberCompany.Data data = items.get(position);

        holder.txtNama.setText(data.getName());
        holder.txtfollowers.setText(data.getFollowers());
        Picasso.with(context).load(data.getPhoto()).centerCrop().fit().into(holder.imgMember);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setItems(List<ResponsListMemberCompany.Data> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView txtNama,txtfollowers;
        CircleImageView imgMember;

        public MyHolder(@NonNull View view) {
            super(view);

            setView(view);
            setEvent();

        }

        private void setEvent() {

        }

        private void setView(View view) {
            txtNama = view.findViewById(R.id.txtMember);
            txtfollowers = view.findViewById(R.id.txt_follow_member);
            imgMember = view.findViewById(R.id.img_member);
        }
    }


}
