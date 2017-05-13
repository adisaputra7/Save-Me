package com.example.nurhadis.sakuria1.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nurhadis.sakuria1.Fragment.FragmentIncomes;
import com.example.nurhadis.sakuria1.R;

public class MainActivity extends AppCompatActivity {

    FragmentIncomes incomesFragment = new FragmentIncomes();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_summary:
                    return true;
                case R.id.navigation_incomes:
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.contentku,
                            incomesFragment,
                            incomesFragment.getTag()).commit();
                    return true;
                case R.id.navigation_saving:
                    return true;
                case R.id.navigation_expenses:
                    return true;
                case R.id.navigation_account:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
