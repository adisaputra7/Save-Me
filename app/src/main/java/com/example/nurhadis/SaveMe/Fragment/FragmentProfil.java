package com.example.nurhadis.SaveMe.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.nurhadis.SaveMe.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentProfil extends Fragment {

    View rootView;
    EditText name, email, phone;
    Button btnSubmit;

    DatabaseReference mDataRef;

    String keyUser, nameString, emailString, phoneString;

    public FragmentProfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_profil, container, false);

        mDataRef = FirebaseDatabase.getInstance().getReference().child("Users").child(keyUser);

        name = (EditText) rootView.findViewById(R.id.setName);
        email = (EditText) rootView.findViewById(R.id.setEmail);
        phone = (EditText) rootView.findViewById(R.id.setNoHp);
        btnSubmit = (Button) rootView.findViewById(R.id.btnProfil);

        nameString = name.getText().toString().trim();
        emailString = email.getText().toString().trim();
        phoneString = phone.getText().toString().trim();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return rootView;
    }

}
