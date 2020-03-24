package app.codelabs.forum.activities.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;

public class EditProfileActivity extends AppCompatActivity {
    ImageView backEdit;
    Button saveEdit;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        context = getApplicationContext();
        setView();
        setEvent();
    }

    private void setEvent() {
        backEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setView() {
        backEdit =findViewById(R.id.imgBack_Edit);
        saveEdit=findViewById(R.id.btnSave_EditProfile);
    }

}
