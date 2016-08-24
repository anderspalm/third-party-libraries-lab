package com.example.ander.third_party_libraries_lab;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.andremion.floatingnavigationview.FloatingNavigationView;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mArray;
    private  FloatingNavigationView mFNV;
    Button mAddMenuItem;
    static int mNum;
    static int mCount;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNum = 0;
        mCount = 0;

        mFNV = (FloatingNavigationView) findViewById(R.id.floating_navigation_view);
        mAddMenuItem = (Button) findViewById(R.id.add_menu_item);

//      WHEN CLICKED OPEN THE NAV
        mFNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFNV.open();
            }
        });

//      NOW TO TELL THE APP WHAT TO DO ON MENU ITEM CLICKED
        mFNV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                Toast.makeText(MainActivity.this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                View view = new View(MainActivity.this);
                return true;
            }
        });

        mAddMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu menu = mFNV.getMenu();
                menu.add(0, Menu.FIRST + mNum, Menu.CATEGORY_CONTAINER, "menu item " + mNum).setIcon(android.R.drawable.ic_dialog_alert);
                Log.i(TAG, "onClick: " + menu.getItem(mNum));
                mNum ++;
            }
        });

        for(int i = 0; i < mFNV.getMenu().size(); i ++) {
           View view = new View(this);
            mCount = i;
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mFNV.getMenu().removeItem(mCount);
                    return false;
                }
            });
        }

    }
}
