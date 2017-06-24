package com.example.nurhadis.SaveMe.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.nurhadis.SaveMe.Model.User;
import com.example.nurhadis.SaveMe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentProfil extends Fragment {

    View rootView;
    EditText editName, editEmail, editPhone;
    Button btnSave;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase mRef;


    public FragmentProfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profil, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        btnSave = (Button) rootView.findViewById(R.id.btnSave);
        editName = (EditText) rootView.findViewById(R.id.setName);
        editEmail = (EditText) rootView.findViewById(R.id.setEmail);
        editPhone = (EditText) rootView.findViewById(R.id.setNoHp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("https://saku-ria-98cb3.firebaseio.com/Users");

        User user = new User();

        editName.setText(""+user.getName());
        editEmail.setText(""+user.getEmail());
        editPhone.setText(""+user.getPhone());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editName.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();

                User user = new User();

                user.setName(name);
                user.setEmail(email);
                user.setPhone(phone);

                mRef.getReference().child("User").setValue(user);
            }
        });

        return rootView;
    }

}
