package com.example.nurhadis.SaveMe.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nurhadis.SaveMe.R;


public class FragmentSave extends Fragment {

    Fragment fragment;

    TextView editTextDate, btnInput, btnStart;
    DatePickerDialog datePickerDialog;
    View rootView;

    public FragmentSave() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_save, container, false);

        editTextDate = (EditText) rootView.findViewById(R.id.endingDate);

        btnInput = (TextView) rootView.findViewById(R.id.setStartingSave);
        btnStart = (TextView) rootView.findViewById(R.id.setStartingAmount);


        Button finish = (Button) rootView.findViewById(R.id.submitSave);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragment = new Summary();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentku, fragment).commit();
            }
        });

        return rootView;
    }

}
