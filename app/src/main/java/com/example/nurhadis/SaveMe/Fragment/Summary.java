package com.example.nurhadis.SaveMe.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nurhadis.SaveMe.Adapter.Adapter;
import com.example.nurhadis.SaveMe.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class Summary extends Fragment {

    View rootView;

    private RecyclerView mTransactionList;
    private DatabaseReference mDatabase;
    private FirebaseUser mCurrentUser;
    private FirebaseAuth mAuth;

    public Summary() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_summary, container, false);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mTransactionList = (RecyclerView) rootView.findViewById(R.id.transactionList);
        mTransactionList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Adapter, TransactionViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Adapter, TransactionViewHolder>(
                Adapter.class,
                R.layout.transaction_list,
                TransactionViewHolder.class,
                mDatabase


        ) {
            @Override
            protected void populateViewHolder(TransactionViewHolder viewHolder, Adapter model, int position) {

                viewHolder.setCategory(model.getCategory());
                viewHolder.setNotes(model.getNotes());
                viewHolder.setValue(model.getValue());
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

        public void setCategory(String category){

            TextView post_category = (TextView) mView.findViewById(R.id.category);
            post_category.setText(category);
        }

        public void setNotes(String notes){

            TextView post_category = (TextView) mView.findViewById(R.id.notes);
            post_category.setText(notes);
        }

        public void setValue(String value){

            TextView post_category = (TextView) mView.findViewById(R.id.value);
            post_category.setText(value);
        }
    }

}
