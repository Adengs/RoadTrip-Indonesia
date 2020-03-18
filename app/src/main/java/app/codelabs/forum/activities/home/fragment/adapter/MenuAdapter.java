package app.codelabs.forum.activities.home.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.about_home.AboutHome;
import app.codelabs.forum.activities.article_home.ArticleHome;
import app.codelabs.forum.activities.menu_event.MenuEventActivity;
import app.codelabs.forum.activities.menu_gallery.MenuGalleryActivity;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    Context context;
    ArrayList<Fragment> items = new ArrayList<>();

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.home_item_grid, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView imgArtikel,imgEvent,imgShop,imgGalery,imgAbout,imgVote;
        TextView txtArtikel,txtEvent,txtShop,txtGalery,txtAbout,txtVote;
        public MenuViewHolder(@NonNull View view) {
            super(view);
            setView(view);
            setEvent();
        }

        private void setView(View view) {
            imgArtikel = view.findViewById(R.id.img_artikel);
            txtArtikel = view.findViewById(R.id.txtArtikel);
            imgEvent = view.findViewById(R.id.img_event);
            txtEvent = view.findViewById(R.id.txtEvent);
            imgShop = view.findViewById(R.id.img_shop);
            txtShop = view.findViewById(R.id.txtShop);
            imgGalery = view.findViewById(R.id.img_galeri);
            txtGalery = view.findViewById(R.id.txtGalery);
            imgAbout = view.findViewById(R.id.img_about);
            txtAbout = view.findViewById(R.id.txtAbout);
            imgVote = view.findViewById(R.id.img_vote);
            txtVote = view.findViewById(R.id.txtVote);
        }

        private void setEvent() {
            imgArtikel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),ArticleHome.class);
                    v.getContext().startActivity(intent);
                }
            });
            imgAbout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AboutHome.class);
                    v.getContext().startActivity(intent);
                }
            });
            imgEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), MenuEventActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
            imgGalery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), MenuGalleryActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }
}
