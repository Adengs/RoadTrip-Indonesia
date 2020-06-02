package app.codelabs.forum.activities.about.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.models.ResponseAbout;

public class SecretariatAdapter extends RecyclerView.Adapter<SecretariatAdapter.SecretariatHolder> {
    private Context context;
    private List<ResponseAbout.SecretariatEntity> item;

    public SecretariatAdapter(){
        item = new ArrayList<>();
    }

    @NonNull
    @Override
    public SecretariatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.item_secretariat,parent,false);
        return new SecretariatAdapter.SecretariatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecretariatHolder holder, int position) {
        final ResponseAbout.SecretariatEntity items = item.get(position);
        holder.tvSecretariat.setText(Html.fromHtml(items.getAddress()));
        holder.tvMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMaps(items);
            }
        });
    }

    private void setMaps(ResponseAbout.SecretariatEntity items) {
        float zoomLevel = 16.0f;
        String url_map = "https://www.google.com/maps/search/?api=1&query=";
        Uri gmmIntentUri = Uri.parse( url_map +items.getLatitude()+","+items.getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }
    public void setItems(List<ResponseAbout.SecretariatEntity> items) {
        this.item = items;
        notifyDataSetChanged();
    }

    public class SecretariatHolder extends RecyclerView.ViewHolder {
        private TextView tvSecretariat, tvMaps;
        public SecretariatHolder(@NonNull View view) {
            super(view);
            setView(view);
            setEvent();
        }

        private void setEvent() {
        }

        private void setView(View view) {
            tvSecretariat = view.findViewById(R.id.tv_Secretariat);
            tvMaps = view.findViewById(R.id.tv_map);
        }
    }
}
