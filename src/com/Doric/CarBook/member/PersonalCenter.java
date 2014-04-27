package com.Doric.CarBook.member;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.Doric.CarBook.MainActivity;
import com.Doric.CarBook.R;

public class PersonalCenter extends Activity implements View.OnClickListener {

    //����ؼ�
    private Button btnInformation,btnComment, btnFavourite,btnLogOut,btnBack;
    private TextView tvUsername;

    //�������
    private String name;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center);

        //���ÿؼ�
        btnFavourite = (Button) findViewById(R.id.name);
        btnInformation = (Button) findViewById(R.id.head);
        btnComment = (Button) findViewById(R.id.sex);
        btnLogOut = (Button) findViewById(R.id.log_out);
        btnBack = (Button) findViewById(R.id.back);
        tvUsername = ( TextView ) findViewById(R.id.bar_username);

        //��Ӽ�����
        btnComment.setOnClickListener(this);
        btnFavourite.setOnClickListener(this);
        btnInformation.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        //����Actionbar
        getActionBar().hide();

        //ȡ��������Activity��Intent����
        Intent intent =getIntent();

        //ȡ��Intent�и��ӵ�����
        if ( intent.getStringExtra("name") !=  null ) {
            name = intent.getStringExtra("name");
            tvUsername.setText(name);
        }
    }

    public void onClick(View v) {
        int id = v.getId();

        //"�ҵ�����"��ť
        if (id == R.id.head) {
            Intent intent = new Intent(PersonalCenter.this, MyInformation.class);
            intent.putExtra("name",name);
            startActivity(intent);
        }

        //"�ҵ�����"��ť
        if (id == R.id.sex) {
            /*Intent intent = new Intent(PersonalCenter.this, comment.class);
            intent.putExtra("name",name);
             startActivity(intent);*/
        }

        //"�ҵ��ղ�"��ť
        if (id == R.id.name) {
            Intent intent = new Intent(PersonalCenter.this, UserCollection.class);
            intent.putExtra("name",name);
            startActivity(intent);
        }

        //"�˳���¼"��ť
        if (id == R.id.log_out) {
            logOutDialog();
        }

        //"����"��ť
        if (id == R.id.back) {
            PersonalCenter.this.finish();
        }
    }

    //�˳���¼�Ի���
    public void logOutDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(PersonalCenter.this);
        builder.setMessage("ȷ��Ҫ�˳���¼��");
        builder.setTitle("�˳���¼");
        builder.setPositiveButton("ȡ��", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("ȷ��", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                PersonalCenter.this.finish();
            }
        });
        builder.create().show();
     }

}
