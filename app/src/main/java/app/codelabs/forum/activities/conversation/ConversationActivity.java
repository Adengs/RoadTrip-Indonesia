package app.codelabs.forum.activities.conversation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.conversation.adapter.ConversationAdapter;
import app.codelabs.forum.activities.conversation.fragment.CoversationFragment;
import app.codelabs.forum.activities.conversation.recyclerview_multipleview.RecyclerViewMultipleItemActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConversationActivity extends AppCompatActivity {

    CardView cardviewe;


    Context context;
    ConversationAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        context = getApplicationContext();






        setView();
        setEvent();







    }



    private void setView() {
       cardviewe  = findViewById (R.id.cardvieweventname);



    }

    private void setEvent() {
        cardviewe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConversationActivity.this, RecyclerViewMultipleItemActivity.class));
            }
        });





    }


}
