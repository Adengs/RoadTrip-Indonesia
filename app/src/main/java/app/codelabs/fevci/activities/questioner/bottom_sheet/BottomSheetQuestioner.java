package app.codelabs.fevci.activities.questioner.bottom_sheet;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import app.codelabs.fevci.models.ResponseAnswerQuestioner;
import app.codelabs.forum.R;
import app.codelabs.fevci.activities.questioner.QuestionResultActivity;

public class BottomSheetQuestioner extends BottomSheetDialogFragment {


    private ResponseAnswerQuestioner data;

    private Button btnDone;

    public BottomSheetQuestioner() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.bottom_sheet_questioner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setView();
        setEvent();
        setCancelable(false);
    }

    private void setEvent() {
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent intent = new Intent(requireContext(), QuestionResultActivity.class);
                intent.putExtra("data", data.toJson());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void setView() {
        btnDone = requireView().findViewById(R.id.btn_done_answer);
    }

    public BottomSheetQuestioner setData(ResponseAnswerQuestioner data) {
        this.data = data;
        return this;
    }


}