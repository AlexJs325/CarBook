package com.Doric.CarBook.member;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.Doric.CarBook.R;

public class PersonalCenter extends Activity implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //����Actionbar
        getActionBar().setTitle("��������");
        android.app.ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void onClick(View view) {

    }
}
