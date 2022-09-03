package com.emmanuel.emmanuelkorircv.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.emmanuel.emmanuelkorircv.Adapters.ViewPagerAdapter;
import com.emmanuel.emmanuelkorircv.Fragments.AdminMyDataFragment;
import com.emmanuel.emmanuelkorircv.Fragments.AdminMyMessegesFragment;
import com.emmanuel.emmanuelkorircv.R;
import com.emmanuel.emmanuelkorircv.Utility.navigationtabstrip.NavigationTabStrip;

public class AdminFunctions extends AppCompatActivity {

    private ImageButton Back;
    private ViewPager vpPager;
    private ViewPagerAdapter viewPagerAdapter;
    private final int currentPosition = 0;
    private NavigationTabStrip viewPagerTab;
    private LinearLayout Main_Layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_functions);

        Back=findViewById(R.id.imBack_admin_functions);

        vpPager = findViewById(R.id.viewpager_profile);
        viewPagerTab = findViewById(R.id.viewpagertab_profile);
        Main_Layout = findViewById(R.id.linear_layout_admin);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());


        viewPagerAdapter.add("My Data", new AdminMyDataFragment());
        viewPagerAdapter.add("My Messeges", new AdminMyMessegesFragment());
        // viewPagerAdapter.add("My Group", new ViewPagerMyProfilelMyGroupFragment());
        //viewPagerAdapter.add("My Groups", new ViewPagerMyProfileMyGroupsFragment());

        vpPager.setAdapter(viewPagerAdapter);

        viewPagerTab.setTitles("My Messeges","My Data");

        //assign tabs to view pager

        viewPagerTab.setViewPager(vpPager);

        // -----------------------------------               margin top status------------------------------------------------------------///
        //get status bar height
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, result, 0, 0);
        Main_Layout.setLayoutParams(params);


// -----------------------------------               margin top status end ------------------------------------------------------------///

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminFunctions.this.onBackPressed();
                finish();
            }
        });
        View_Pager_Scroll_Listener();
    }

    private void View_Pager_Scroll_Listener()
    {
        // Attach the page change listener inside the activity
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int newPosition) {
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
    }
}
