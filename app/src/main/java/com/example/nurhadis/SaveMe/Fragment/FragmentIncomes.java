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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nurhadis.SaveMe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class FragmentIncomes extends Fragment{

    Fragment fragment;
    private FirebaseAuth firebaseAuth;
    DatabaseReference mDatabaseRef;
    FirebaseUser mCurrentUser;

    Button finish;
    EditText textValue;
    TextView textDate;
    DatePickerDialog datePickerDialog;
    EditText textNote;
    Spinner textCategory;

    View rootView;


    public FragmentIncomes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_incomes, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        textValue = (EditText) rootView.findViewById(R.id.setIncomes);
        textCategory = (Spinner) rootView.findViewById(R.id.setCategory);
        textDate = (TextView) rootView.findViewById(R.id.setDate);
        textNote =(EditText) rootView.findViewById(R.id.setNote);
        finish = (Button) rootView.findViewById(R.id.submitIncomes);

        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();

        String key = mCurrentUser.getUid();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(key);

        textDate.setOnClickListener(new View.OnClickListener() {
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
                                textDate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setIncomes();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragment = new Summary();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentku, fragment).commit();
            }
        });
        return rootView;
    }

    private void setIncomes() {
        String value, cetegory, date, note;

        value = textValue.getText().toString().trim();
        cetegory = textCategory.getSelectedItem().toString().trim();
        date = textDate.getText().toString().trim();
        note = textNote.getText().toString().trim();

        if (TextUtils.isEmpty(value) &&   (TextUtils.isEmpty(cetegory) &&  (TextUtils.isEmpty(date)&& (TextUtils.isEmpty(note))))){
            //email is empty
            Toast.makeText(getActivity(), "Please complete form",Toast.LENGTH_SHORT).show();
            //stopping the function execution further;
            return;
        }

        DatabaseReference mCurrent_db = mDatabaseRef.child("transaction").push();

        mCurrent_db.child("id").setValue("income");
        mCurrent_db.child("category").setValue(cetegory);
        mCurrent_db.child("date").setValue(date);
        mCurrent_db.child("note").setValue(note);
        mCurrent_db.child("value").setValue(value);


        Toast.makeText(getActivity(), "Transaction Added", Toast.LENGTH_SHORT).show();

    }



}
