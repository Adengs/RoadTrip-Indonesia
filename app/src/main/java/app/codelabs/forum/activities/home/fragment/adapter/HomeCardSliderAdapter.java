package app.codelabs.forum.activities.home.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.islamkhsh.CardSliderAdapter;
import com.github.islamkhsh.CardSliderViewPager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;

public class HomeCardSliderAdapter extends CardSliderAdapter<HomeCardSliderAdapter.HomeViewHolder> {
    ArrayList<Fragment> items = new ArrayList<>();

    public HomeCardSliderAdapter(ArrayList<Fragment> items){
        this.items = items;
    }
    @Override
    public void bindVH(HomeViewHolder homeViewHolder, int i) {

    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_slider, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMobil, imgLogo,pager,pagergray,pagergrayes,circlenew;
        TextView namehome;
        public HomeViewHolder(@NonNull View view) {
            super(view);

            setView(view);

        }

        private void setView(View view) {
            imgMobil = view.findViewById(R.id.img_mobil);
            imgLogo = view.findViewById(R.id.img_logo);
            pager = view.findViewById(R.id.pager);
            pagergray = view.findViewById(R.id.pagergray);
            pagergrayes = view.findViewById(R.id.pagergrayes);
            namehome = view.findViewById(R.id.namehome);
        }
    }
}
