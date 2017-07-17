package com.example.nurhadis.SaveMe.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nurhadis.SaveMe.Adapter.AdapterSave;
import com.example.nurhadis.SaveMe.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSaving extends Fragment {

    View rootView;

    private RecyclerView mTransactionList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private TextView mAddSaving;

    Fragment fragment;
    FragmentManager fragmentManager;


    FirebaseUser mCurrentUser;
    int value = 0;
    int expenses = 0;
    int incomesku ;
    int expensesku ;

    private TextView mVBalance;

    public FragmentSaving() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_fragment__saving, container, false);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        String key = mCurrentUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(key).child("save");

        mVBalance = (TextView) rootView.findViewById(R.id.myBalance);
        mAddSaving = (Button) rootView.findViewById(R.id.addSaving);

        mAddSaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getActivity().getSupportFragmentManager();
                fragment = new FragmentSave();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentku, fragment).commit();

            }
        });

        mTransactionList = (RecyclerView) rootView.findViewById(R.id.transactionLis);
        mTransactionList.setLayoutManager(new LinearLayoutManager(getActivity()));


        return rootView;
    }



    @Override
    public void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<AdapterSave, TransactionViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AdapterSave, TransactionViewHolder>(
                AdapterSave.class,
                R.layout.transaction_save,
                TransactionViewHolder.class,
                mDatabase


        ){
            @Override
            protected void populateViewHolder(TransactionViewHolder viewHolder, AdapterSave model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setNote(model.getNote());
                viewHolder.setValue(model.getValue());
                viewHolder.setDate(model.getDate());
            }


        };
        mTransactionList.setAdapter(firebaseRecyclerAdapter);

    }


    public static class TransactionViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public TransactionViewHolder(View itemView) {
            super(itemView);

            mView = itemView ;
        }

        public void setName(String name){

            TextView post_category = (TextView) mView.findViewById(R.id.nameSave);
            post_category.setText(name);
        }

        public void setNote(String note){

            TextView post_category = (TextView) mView.findViewById(R.id.notes);
            post_category.setText(note);
        }

        public void setValue(String value){

            TextView post_category = (TextView) mView.findViewById(R.id.value);
            post_category.setText(value);
        }

        public void setDate(String date){

            TextView post_category = (TextView) mView.findViewById(R.id.tanggal);
            post_category.setText(date);
        }
    }

}
