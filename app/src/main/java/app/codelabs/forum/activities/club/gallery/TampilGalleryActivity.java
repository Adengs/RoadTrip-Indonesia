package app.codelabs.forum.activities.club.gallery;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.menu_gallery.MenuGalleryActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TampilGalleryActivity extends AppCompatActivity {
    ImageView img_panah_back_gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_gallery);

        setView();
        setEvent();
    }

    private void setEvent() {
        img_panah_back_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TampilGalleryActivity.this, MenuGalleryActivity.class));
            }
        });
    }

    private void setView() {
        img_panah_back_gallery = findViewById(R.id.btn_panah_back_galley);
    }
}
