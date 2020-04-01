package app.codelabs.forum.activities.home.fragment.latest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.latest_popular_foryou.CardViewActivity;
import app.codelabs.forum.models.ResponsArticleLatest;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.HomeAdapterViewHolder> {
    List<ResponsArticleLatest.DataEntity> items;
    Context context;


    public LatestAdapter() {
       this.items = new ArrayList<>();
    }


    @NonNull
    @Override
    public HomeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new HomeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapterViewHolder holder, int position) {
        ResponsArticleLatest.DataEntity item = items.get(position);

        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CardViewActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class HomeAdapterViewHolder extends RecyclerView.ViewHolder {
        CardView cardView ;
        ImageView imglogolatest,imgmobillatest;
        TextView namamobil ,waktulatest,descmobil,carlatest;
        public HomeAdapterViewHolder(@NonNull View view) {
            super(view);
            setView(view);
        }

        private void setView(View view) {
            cardView = view.findViewById(R.id.cardviewtabhome);
            imglogolatest=view.findViewById(R.id.img_logo1_latest);
            imgmobillatest=view.findViewById(R.id.img_mobill_latest);
            namamobil=view.findViewById(R.id.txtnamamobil1_latest);
            waktulatest=view.findViewById(R.id.waktu_latest);
            descmobil= view.findViewById(R.id.txt_desc_mobil_latest);
            carlatest= view.findViewById(R.id.cars_latest);
        }
    }
}
