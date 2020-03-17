package app.codelabs.forum.activities.forgotpassword.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import app.codelabs.forum.R;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponseForgotPassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends Fragment {
    Button btnSend;
    EditText et_EmailForgot;
    private Session session;
    String apptoken;
    Context context;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        session = Session.init(context);
        apptoken = session.getAppToken();

        setView(view);
        setEvent();

    }



    private void setView(View view) {
        et_EmailForgot = view.findViewById(R.id.et_emailforgotpass);

        btnSend = view.findViewById(R.id.btn_sendforgotpass);
    }
    private void setEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), SubmitFragment.class);
                    view.getContext().startActivity(intent);
                    getActivity().finish();
                //doForgotPassword();
            }
        });
    }

    private void doForgotPassword(){

      //  Map<String,String> data = new HashMap<>();
     //   data.put("email",et_EmailForgot.getText().toString());

     /**   ConnectionApi.apiService().forgotpassword(data,apptoken).enqueue(new Callback<ResponseForgotPassword>() {
            @Override
            public void onResponse(Call<ResponseForgotPassword> call, Response<ResponseForgotPassword> response) {


            }

            @Override
            public void onFailure(Call<ResponseForgotPassword> call, Throwable t) {

            }
        }); */


    }


}
