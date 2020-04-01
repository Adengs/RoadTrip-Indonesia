package app.codelabs.forum.activities.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import app.codelabs.forum.R;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsLogin;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView ivBack, ivPhoto;
    private EditText etName, etEmail, etDob, etCity;
    private Button btnSaveEdit;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        context = getApplicationContext();
        setView();
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
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setView() {
        ivBack = findViewById(R.id.iv_back);
        btnSaveEdit = findViewById(R.id.btn_save);
        ivPhoto = findViewById(R.id.iv_photo);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etDob = findViewById(R.id.et_dob);
        etCity = findViewById(R.id.et_city);
    }

}
