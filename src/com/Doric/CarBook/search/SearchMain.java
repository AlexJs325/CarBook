package com.Doric.CarBook.search;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.Doric.CarBook.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014/5/12.
 */
public class SearchMain extends Activity {
    public static SearchMain searchmain;
    public DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchmain = this;
        setContentView(R.layout.sea_main);
        //��ȡ����

        //��ȡ�ؼ�

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerList = (ListView) findViewById(R.id.drawerListView);
        InInitialize();


    }


    //�����ϵ
    private ArrayList<Map<String, Object>> getUniformDataSeries(ArrayList<CarSeries> al_cs) {
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (CarSeries cs : al_cs) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", cs.getName());
            map.put("img", R.drawable.ic_launcher);
            //map.put("price", cs.getLowPrice() + " -- " + cs.getHighPrice());
            list.add(map);

        }

        return list;

    }

    public void setListData(ArrayList<CarSeries> serieslist) {
        SimpleAdapter adapter = new SimpleAdapter(this, getUniformDataSeries(serieslist), R.layout.sea_row,
                new String[]{"title", "img"/*,"price"*/},
                new int[]{R.id.title, R.id.img/*,R.id.price*/});
        mDrawerList.setDivider(getResources().getDrawable(R.drawable.list_divider));
        mDrawerList.setDividerHeight(1);
        mDrawerList.setAdapter(adapter);
        //�����ϵ
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListView lv = (ListView) parent;
                HashMap<String, Object> Info = (HashMap<String, Object>) lv.getItemAtPosition(position);//SimpleAdapter����Map
                mDrawerLayout.closeDrawer(mDrawerList);
                FragmentManager fragmentManager = getFragmentManager();
                MyFragment fragment = new CarListShow();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.drawerFrame, fragment, "CarListShow");
                transaction.addToBackStack("CarListShow");
                Bundle bundle = new Bundle();
                bundle.putString("carBrand", AlphaShow.carseable.getCarSeableName());
                bundle.putString("carSerie", (String) Info.get("title"));
                fragment.setArguments(bundle);
                transaction.commit();


            }

        });

    }

    private void InInitialize() {
        MyFragment mfFragment = new AlphaShow();

        //����һ��FragmentManager������������getFragmentManager�ķ���ֵ�����˽⵽���˴��������������Ǵ�����PlanetFragment����ġ�
        FragmentManager fragmentManager = getFragmentManager();
        //����android api��˵���õ�ǰ��PlanetFragment���滻֮ǰ���ڵ�Fragment��
        fragmentManager.beginTransaction()
                .replace(R.id.drawerFrame, mfFragment).commit();

    }

    public void OpenSliding() {
        mDrawerLayout.openDrawer(mDrawerList);

    }

    public void SearchToResult(Double lowprice, Double higprice, Grade grade) {
        FragmentManager fragmentManager = getFragmentManager();
        MyFragment fragment = new Result();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.drawerFrame, fragment, "Result");
        transaction.addToBackStack("Result");
        Bundle bundle = new Bundle();
        bundle.putDouble("lowprice", lowprice);
        bundle.putDouble("higprice", higprice);
        bundle.putSerializable("grade", grade);
        fragment.setArguments(bundle);
        transaction.commit();
    }


}
