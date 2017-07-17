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
import com.example.nurhadis.SaveMe.Model.Balance;
import com.example.nurhadis.SaveMe.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class Summary extends Fragment {

    View rootView;

    private RecyclerView mTransactionList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    FirebaseUser mCurrentUser;
    int expenses = 0;
    int value = 0;
    int incomesku=0 ;
    int expensesku=0 ;

    private TextView mVBalance;

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

        String key = mCurrentUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(key).child("transaction");

        mVBalance = (TextView) rootView.findViewById(R.id.myBalance);
        mTransactionList = (RecyclerView) rootView.findViewById(R.id.transactionList);
        mTransactionList.setLayoutManager(new LinearLayoutManager(getActivity()));

        Query queryInc = mDatabase.orderByChild("id").equalTo("income");

        queryInc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                Query queryExp = mDatabase.orderByChild("id").equalTo("expenses");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Balance balance = postSnapshot.getValue(Balance.class);

                    //Adding it to a string

                    if(balance.getValue()!= null) {
                        value += Integer.valueOf(balance.getValue());
                    }
                }


                queryExp.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            Balance balance = postSnapshot.getValue(Balance.class);

                            //Adding it to a string
                            if (balance.getValue()!=null) {
                                expenses += Integer.valueOf(balance.getValue());
                            }
                        }
                        expensesku= value - expenses;
                        mVBalance.setText(String.valueOf(expensesku));


                    }



                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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


        ){
            @Override
            protected void populateViewHolder(TransactionViewHolder viewHolder, Adapter model, int position) {

                viewHolder.setCategory(model.getCategory());
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

        public void setCategory(String category){

            TextView post_category = (TextView) mView.findViewById(R.id.category);
            post_category.setText(category);
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
