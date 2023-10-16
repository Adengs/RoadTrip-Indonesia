package app.codelabs.roadtrip.activities.explore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.explore.DetailExploreActivity;
import app.codelabs.roadtrip.activities.explore.list.AdapterListLocation;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.ResponseDetailExplore;
import app.codelabs.roadtrip.models.ResponseListLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterImageList extends RecyclerView.Adapter<AdapterImageList.MyHolder> {
    private Context context;
    private List<String> item;

    public AdapterImageList(Context context) {
        this.context = context;
        this.item = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_detail_explore, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String items = item.get(position);

        if (items.isEmpty()){

        }else {
            Picasso.with(context).load(items)
                    .placeholder(R.drawable.ic_car)
                    .error(R.drawable.default_no_image)
                    .fit().centerCrop().into(holder.imgStore);
        }

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void setItems(List<String> items) {
        this.item = items;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView imgStore;

        public MyHolder(@NonNull View view) {

            super(view);
            setView(view);
        }

        private void setView(View view) {
            imgStore = view.findViewById(R.id.img_store);
        }
    }
}
