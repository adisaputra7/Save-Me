package com.example.nurhadis.SaveMe.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nurhadis.SaveMe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class FragmentSave extends Fragment {

    Fragment fragment;

    private FirebaseAuth firebaseAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference mDatabaseRef;


    Button finish;
    EditText textSaveName, textValueSave, textValueStart;
    EditText textNote;
    TextView textEndingDate;
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

        textSaveName = (EditText) rootView.findViewById(R.id.fullName);
        textValueSave = (EditText) rootView.findViewById(R.id.setStartingSave);
        textValueStart = (EditText) rootView.findViewById(R.id.setStartingAmount);
        textEndingDate = (TextView) rootView.findViewById(R.id.endingDate);
        textNote = (EditText) rootView.findViewById(R.id.setNote);

        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();

        String key = mCurrentUser.getUid();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(key);

        textEndingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                textEndingDate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



        Button finish = (Button) rootView.findViewById(R.id.submitSave);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSave();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragment = new Summary();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentku, fragment).commit();
            }
        });

        return rootView;
    }

    private void setSave() {

        String nameSave, valueStart, valueSave, endDate, note;

        nameSave = textSaveName.getText().toString().trim();
        endDate = textEndingDate.getText().toString().trim();
        valueSave = textValueSave.getText().toString().trim();
        valueStart = textValueStart.getText().toString().trim();
        note = textNote.getText().toString().trim();

        if (TextUtils.isEmpty(nameSave) &&   (TextUtils.isEmpty(endDate) &&  (TextUtils.isEmpty(valueSave)&& (TextUtils.isEmpty(note))))){
            //email is empty
            Toast.makeText(getActivity(), "Please complete form",Toast.LENGTH_SHORT).show();
            //stopping the function execution further;
            return ;
        }

        DatabaseReference mCurrent_db = mDatabaseRef.child("save").push();

        mCurrent_db.child("name").setValue(nameSave);
        mCurrent_db.child("value").setValue(valueSave);
        mCurrent_db.child("start").setValue(valueStart);
        mCurrent_db.child("date").setValue(endDate);
        mCurrent_db.child("note").setValue(note);

        Toast.makeText(getActivity(), "Transaction Added", Toast.LENGTH_SHORT).show();

    }

}
