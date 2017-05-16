package com.example.nurhadis.SaveMe.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.nurhadis.SaveMe.Fragment.FragmentAccount;
import com.example.nurhadis.SaveMe.Fragment.FragmentExpenses;
import com.example.nurhadis.SaveMe.Fragment.FragmentIncomes;
import com.example.nurhadis.SaveMe.Fragment.FragmentSave;
import com.example.nurhadis.SaveMe.Fragment.Summary;
import com.example.nurhadis.SaveMe.R;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager manager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_summary:
                        fragment = new Summary();
                        break;
                    case R.id.navigation_incomes:
                        fragment = new FragmentIncomes();
                        break;
                    case R.id.navigation_saving:
                        fragment = new FragmentSave();
                        break;
                    case R.id.navigation_expenses:
                        fragment = new FragmentExpenses();
                        break;
                    case R.id.navigation_account:
                        fragment = new FragmentAccount();
                        break;
                }
            final FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.contentku, fragment).commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        fragment = new Summary();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.contentku, fragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
