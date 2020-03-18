package app.codelabs.forum.activities.club.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;

public class GalleryNameAdapter extends RecyclerView.Adapter<GalleryNameAdapter.MyHolder> {

    private Context context;
    private LinearLayoutManager linearLayoutManager;
    private GalleryAdapter galleryAdapter;
    @NonNull
    @Override
    public GalleryNameAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_name_gallery, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryNameAdapter.MyHolder holder, int position) {
        linearLayoutManager = new LinearLayoutManager(context);
        galleryAdapter = new GalleryAdapter();
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        holder.recyclerView.setAdapter(galleryAdapter);

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public RecyclerView recyclerView;

        public MyHolder(@NonNull View view) {

            super(view);

            setView(view);
        }

        private void setView(View view) {
            recyclerView = view.findViewById(R.id.rv_galery);

        }
    }
}
