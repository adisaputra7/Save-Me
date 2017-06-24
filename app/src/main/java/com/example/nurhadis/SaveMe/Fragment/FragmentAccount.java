package com.example.nurhadis.SaveMe.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nurhadis.SaveMe.Activities.SignIn;
import com.example.nurhadis.SaveMe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class FragmentAccount extends Fragment implements View.OnClickListener{

    View rootView;
    private LinearLayoutCompat btnLogOut, btnPwd, btnProfil, btnHelp;

    Fragment fragment;
    FragmentManager fragmentManager;


    public FirebaseAuth firebaseAuth;

    public FragmentAccount() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_account, container, false);

        btnProfil = (LinearLayoutCompat)  rootView.findViewById(R.id.btnProfil);
        btnPwd = (LinearLayoutCompat) rootView.findViewById(R.id.btnPassword);
        btnHelp = (LinearLayoutCompat)  rootView.findViewById(R.id.btnHelp);
        btnLogOut = (LinearLayoutCompat)  rootView.findViewById(R.id.logOut);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();




        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getActivity().getSupportFragmentManager();
                fragment = new FragmentProfil();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentku, fragment).commit();


            }
        });


        btnPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager = getActivity().getSupportFragmentManager();
                fragment = new FragmentPassword();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentku, fragment).commit();

            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getActivity().getSupportFragmentManager();
                fragment = new FragmentHelp();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentku, fragment).commit();

            }
        });

        btnLogOut.setOnClickListener(this);

        return rootView;

    }


    @Override
    public void onClick(View view) {
        if (view == btnLogOut) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getContext(), SignIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }
}
