package app.codelabs.forum.activities.home.fragment.foryou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.fragment.latest.adapter.LatestAdapter;

public class ForYouAdapter extends RecyclerView.Adapter<ForYouAdapter.ForYouViewHolder> {
    List<Fragment> items = new ArrayList<>();
    Context context;
    @NonNull
    @Override
    public ForYouViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new ForYouViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForYouViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ForYouViewHolder extends RecyclerView.ViewHolder {
        TextView TxtNamaMobil1,TxtNamaMobil2,TxtCars1,TxtCars2,TxTDescMobil1,TxtDescMobil2;
        TextView txtday,txtdaymo;
        ImageView imgLogo1,imgLogo2,circleGray1,circleGray2,imgMobil1,imgMobil2;
        public ForYouViewHolder(@NonNull View view) {
            super(view);
            setView(view);
        }

        private void setView(View view) {
            TxtNamaMobil1 = view.findViewById(R.id.txtnamamobil1);
            TxtNamaMobil2 = view.findViewById(R.id.txtnamamobil2);
            TxTDescMobil1 = view.findViewById(R.id.txt_desc_mobil);
            TxtDescMobil2 = view.findViewById(R.id.txt_desc_mobil1);
            TxtCars1 = view.findViewById(R.id.cars);
            TxtCars2 = view.findViewById(R.id.cars2);
            txtday = view.findViewById(R.id.titik);
            txtdaymo = view.findViewById(R.id.tikmobil);
            imgLogo1 = view.findViewById(R.id.img_logo1);
            imgLogo2 = view.findViewById(R.id.img_logo2);
            imgMobil1 = view.findViewById(R.id.img_mobill1);
            imgMobil2 = view.findViewById(R.id.img_mobil2);
            circleGray1 = view.findViewById(R.id.circlegray);
            circleGray2 = view.findViewById(R.id.circlegrayss);
        }
    }
}
