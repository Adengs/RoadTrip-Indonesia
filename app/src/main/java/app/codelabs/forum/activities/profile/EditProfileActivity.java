package app.codelabs.forum.activities.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import app.codelabs.forum.R;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsLogin;

public class EditProfileActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView ivPhoto;
    private EditText etName, etEmail, etDob, etCity;
    private Button btnSaveEdit;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        context = getApplicationContext();
        setView();
        setToolbar();
        setEvent();
        setData();
    }

    private void setData() {
        ResponsLogin.Data user = Session.init(context).getUser();
        etName.setText(user.getName());
        etEmail.setText(user.getEmail());
        etDob.setText(user.getDate_birth());
        etCity.setText(user.getCity());
        Picasso.with(context).load(user.getPhoto()).fit().centerCrop().into(ivPhoto);
    }

    private void setEvent() {
        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Edit Profile");
    }

    private void setView() {
        toolbar = findViewById(R.id.toolbar);
        btnSaveEdit = findViewById(R.id.btn_save);
        ivPhoto = findViewById(R.id.iv_photo);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etDob = findViewById(R.id.et_dob);
        etCity = findViewById(R.id.et_city);
    }

}
