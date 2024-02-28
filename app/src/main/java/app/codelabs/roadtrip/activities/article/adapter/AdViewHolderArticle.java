package app.codelabs.roadtrip.activities.article.adapter;

import app.codelabs.roadtrip.R;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.nativead.NativeAd;


public class AdViewHolderArticle extends RecyclerView.ViewHolder {
    public TemplateView template;

    public AdViewHolderArticle(@NonNull View itemView){
        super(itemView);

        template = itemView.findViewById(R.id.ads_article);

        NativeTemplateStyle style = new NativeTemplateStyle.Builder().build();
        template.setStyles(style);
    }

    public void setNativeAd(NativeAd nativeAd){
        template.setNativeAd(nativeAd);
    }
}
