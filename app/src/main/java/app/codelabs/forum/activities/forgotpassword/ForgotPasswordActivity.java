package app.codelabs.forum.activities.forgotpassword;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.forgotpassword.fragment.ForgotPasswordFragment;

public class ForgotPasswordActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setView();
        setEvent();
        setFragment(new ForgotPasswordFragment());


    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_forgot, fragment);
        fragmentTransaction.commit();
    }


    private void setView() {

    }

    private void setEvent() {

    }
}
