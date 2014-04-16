package com.Doric.CarBook.member;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.Doric.CarBook.R;
import com.Doric.CarBook.utility.JSONParser;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Register extends Activity implements View.OnClickListener {

    EditText edt_username, edt_password, edt_ensurePsd, edt_email;
    Button btn_register;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //���ÿؼ�
        edt_username = (EditText) findViewById(R.id.username);
        edt_password = (EditText) findViewById(R.id.password);
        edt_ensurePsd = (EditText) findViewById(R.id.ensure_password);
        edt_email = (EditText) findViewById(R.id.email);
        btn_register = (Button) findViewById(R.id.register);

        //��Ӽ�����
        btn_register.setOnClickListener(this);

        //����Actionbar
        getActionBar().setTitle("ע���˺�");

        android.app.ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClick(View v) {
        int id = v.getId();

        //��ע�ᡱ��ť
        if (id == R.id.register) {
            //��ȡ�û��������Ϣ
            String name = edt_username.getText().toString();
            String psd = edt_password.getText().toString();
            String enPsd = edt_ensurePsd.getText().toString();
            String emailAddress = edt_email.getText().toString();

            //�ж��û����Ƿ�Ϊ��
            if (name.equals("")) {
                Toast.makeText(Register.this, "�������û���", Toast.LENGTH_LONG).show();
            }
            //�ж��û����Ƿ����
            else if (name.length() > 16) {
                Toast.makeText(Register.this, "�û�������������������", Toast.LENGTH_LONG).show();
            }
            //�ж��û����Ƿ����
            else if (name.length() < 6) {
                Toast.makeText(Register.this, "�û������̣�����������", Toast.LENGTH_LONG).show();
            }
            //�ж������Ƿ�Ϊ��
            else if (psd.equals("")) {
                Toast.makeText(Register.this, "����������", Toast.LENGTH_LONG).show();
            }
            //�ж������Ƿ����
            else if (psd.length() > 16) {
                Toast.makeText(Register.this, "�������������������", Toast.LENGTH_LONG).show();
            }
            //�ж������Ƿ����
            else if (psd.length() < 6) {
                Toast.makeText(Register.this, "������̣�����������", Toast.LENGTH_LONG).show();
            }
            //�ж���֤�����Ƿ�Ϊ��
            else if (enPsd.equals("")) {
                Toast.makeText(Register.this, "���ٴ�������������", Toast.LENGTH_LONG).show();
            }
            //�ж���֤�����Ƿ���ȷ
            else if (psd.equals(enPsd)) {
                Toast.makeText(Register.this, "������������벻ͬ������������?", Toast.LENGTH_LONG).show();
            }
            //�ж������Ƿ�Ϊ��
            else if (emailAddress.equals("")) {
                Toast.makeText(Register.this, "���������������ַ?", Toast.LENGTH_LONG).show();
            }
            //�ж������ʽ�Ƿ�Ϲ淶
            else if (!check_email(emailAddress)) {
                Toast.makeText(Register.this, "���������ַ����ȷ������������", Toast.LENGTH_LONG).show();
            } else {
                //�����û���Ϣ��������
                String url = "";
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", name));
                params.add(new BasicNameValuePair("password", psd));
                params.add(new BasicNameValuePair("email", emailAddress));
                JSONParser jsonParser = new JSONParser();
                JSONObject loginInfo = jsonParser.getJSONFromUrl(url, params);
            }
        }
    }

    public boolean check_email(String ead) {
        int len = ead.length();
        int i;
        for (i = 0; i < len; i++) {
            if (ead.charAt(i) == '@') {
                return true;
            }
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
