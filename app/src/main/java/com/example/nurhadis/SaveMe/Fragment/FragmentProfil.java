package com.example.nurhadis.SaveMe.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.nurhadis.SaveMe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentProfil extends Fragment {

    View rootView;
    EditText editName, editEmail, editPhone;
    Button btnSave;

    FirebaseUser mCurrentUser;
    FirebaseAuth firebaseAuth;
    DatabaseReference mDataRef;

    String keyUser;


    public FragmentProfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profil, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();

        keyUser = mCurrentUser.getUid();

        mDataRef = FirebaseDatabase.getInstance().getReference().child("Users");

        editName = (EditText) rootView.findViewById(R.id.setName);
        editEmail = (EditText) rootView.findViewById(R.id.setEmail);
        btnSave = (Button) rootView.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editName.getText().toString().trim();
                String email = editEmail.getText().toString().trim();

                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email)) {
                        mDataRef.child(keyUser).child("fullname").setValue(name);
                        mDataRef.child(keyUser).child("emailUser").setValue(email);
                    }
            }
        });

        return rootView;
    }

}
