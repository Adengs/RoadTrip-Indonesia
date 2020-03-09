package app.codelabs.forum;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import app.codelabs.forum.activities.conversation.ConversationActivity;
import app.codelabs.forum.activities.conversation.fragment.CoversationFragment;
import app.codelabs.forum.activities.conversation.recyclerview_multipleview.RecyclerViewMultipleItemActivity;
import app.codelabs.forum.activities.walktrought.fragment.WalkTroughtFirstFragment;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }




}
