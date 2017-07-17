package com.example.nurhadis.SaveMe.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nurhadis.SaveMe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FragmentPassword extends Fragment {

    View rootView;
    EditText editName, editEmail, editPhone;
    Button btnSave;

    FirebaseUser mCurrentUser;
    FirebaseAuth firebaseAuth;
    DatabaseReference mDataRef;

    String keyUser;


    public FragmentPassword() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_password, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();

        keyUser = mCurrentUser.getUid();

        mDataRef = FirebaseDatabase.getInstance().getReference().child("Users").child(keyUser);

        editName = (EditText) rootView.findViewById(R.id.setName);
        editEmail = (EditText) rootView.findViewById(R.id.setEmail);
        btnSave = (Button) rootView.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDataRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String name = editName.getText().toString().trim();
                        String email = editEmail.getText().toString().trim();
                        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email)) {
                            mDataRef.child("fullname").setValue(name);
                            mDataRef.child("emailUser").setValue(email);

                            Toast.makeText(getActivity(), "Profil changed", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        mDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String profile_name = (String) dataSnapshot.child("fullname").getValue();
                String profile_email = (String) dataSnapshot.child("emailUser").getValue();


                editEmail.setText(profile_email);
                editName.setText(profile_name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }

}