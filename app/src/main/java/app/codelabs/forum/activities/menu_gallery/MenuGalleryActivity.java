package app.codelabs.forum.activities.menu_gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.club.gallery.GalleryFragment;
import app.codelabs.forum.activities.home.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuGalleryActivity extends AppCompatActivity {
    ImageView img_panah_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gallery);

        setView();
        setEvent();
        setFragment(new GalleryFragment());
    }

    private void setEvent() {
        img_panah_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuGalleryActivity.this, HomeActivity.class));
            }
        });
    }

    private void setView() {
        img_panah_back = findViewById(R.id.btn_panah_back_menu_galley);
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_gallery, fragment);
        fragmentTransaction.commit();
    }
}
