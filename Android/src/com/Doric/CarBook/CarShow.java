package com.Doric.CarBook;


import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.CarBook_master.R;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class CarShow extends FragmentActivity implements android.app.ActionBar.TabListener{
    /*
    ʵ��һ���������һ����ģ���������������ͼƬ���������������ۡ������ۡ��ĳ�����Ϣչʾҳ��
     */
    private ViewPager mViewPager;
    public static final int MAX_TAB_SIZE = 5;
    private TabFragmentPagerAdapter mAdapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_show);
        /*
            ʹ��carId����Carʵ��
        */
        Intent intent = getIntent();
        intent.getIntExtra("carId", 0);
        CarInfor car = new CarInfor();
        car.setCarId(89757);
        car.setBrandId("BMW");
        car.setCarName("Z4");
        car.setCarSize("Sports Car");
        car.setHighSpeed(200.0);
        car.setEngineType("������");
        car.setHigPrice(50.0);
        car.setLowPrice(40.0);
        car.setTimeTo100Km(20.0);

        getActionBar().setTitle(car.getCarName());

        findViewById();
        initView();
    }
    private void findViewById(){

        mViewPager = (ViewPager) this.findViewById(R.id.pager);
    }

    private void initView(){
        final android.app.ActionBar mActionBar = getActionBar();

        mActionBar.setDisplayHomeAsUpEnabled(false);

        mActionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);

        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {

                mActionBar.setSelectedNavigationItem(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        //��ʼ�� ActionBar
        for(int i=0;i<MAX_TAB_SIZE;i++){
            android.app.ActionBar.Tab tab = mActionBar.newTab();
            tab.setText(mAdapter.getPageTitle(i)).setTabListener(this);
            mActionBar.addTab(tab);
        }
    }
    public static class TabFragmentPagerAdapter extends FragmentPagerAdapter{

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            Fragment ft = null;
            switch (arg0) {
                case 0:
                    ft = new SummaryFragment();
                    break;
                case 1:
                    ft = new PictureFragment();
                    break;
                case 2:
                    ft = new ParameterFragment();
                    break;
                case 3:
                    ft = new PriceFragment();
                    break;
                case 4:
                    ft = new CommentFragment();
                    break;
                default:
                    break;
            }
            return ft;
        }

        @Override
        public int getCount() {

            return MAX_TAB_SIZE;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "����";
                case 1:
                    return "ͼƬ";
                case 2:
                    return "����";
                case 3:
                    return "����";
                case 4:
                    return "����";
                default:
                    return "";
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }
}