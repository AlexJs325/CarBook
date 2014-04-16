package com.Doric.CarBook.member;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.Doric.CarBook.R;
import com.Doric.CarBook.Static;
import com.Doric.CarBook.utility.JSONParser;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Login extends Activity implements OnClickListener {

    String url = Static.BASE_URL + "/login.php";  //��¼�����url,��ؼ���http://��https://
    List<NameValuePair> loginParams;    //��¼ʱ���͸�������������
    JSONObject loginInfo;       //�����������õ���json����
    private EditText edt_username, edt_password;
    private Button btn_login, btn_register;
    private ProgressDialog progressDialog;   //�첽����ʱ��ʾ�Ľ�����

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //���ÿؼ�
        edt_username = (EditText) findViewById(R.id.username);
        edt_password = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.login);
        btn_register = (Button) findViewById(R.id.register);

        //��Ӽ�����
        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);

        //����Actionbar
        getActionBar().setTitle("��¼");

        android.app.ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void onClick(View v) {

        int id = v.getId();

        //��ע�ᡱ��ť
        if (id == R.id.register) {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        }

        //����½����ť
        if (id == R.id.login) {
            //��ȡ�û�������
            String name = edt_username.getText().toString();
            String psd = edt_password.getText().toString();

            //�ж��û����Ƿ�Ϊ��
            if (name.equals("")) {
                Toast.makeText(Login.this, "�������û���", Toast.LENGTH_LONG).show();
            }
            //�ж������Ƿ�Ϊ��
            else if (psd.equals("")) {
                Toast.makeText(Login.this, "����������", Toast.LENGTH_LONG).show();
            }

            //�����û���Ϣ��������
            loginParams = new ArrayList<NameValuePair>();
            loginParams.add(new BasicNameValuePair("tag", "login"));
            loginParams.add(new BasicNameValuePair("username", name));
            loginParams.add(new BasicNameValuePair("password", psd));

            //�첽�����ж��û��Ƿ��¼�ɹ�
            new CheckUser().execute();

            //��֤�û�������
            if (true) {

            }

            //���û����������
            else {
                Toast.makeText(Login.this, "�û����������������������", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class CheckUser extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            JSONParser jsonParser = new JSONParser();
            loginInfo = jsonParser.getJSONFromUrl(url, loginParams);
            Log.d("Response: ", "" + loginInfo);

            if (loginInfo != null) {
                Log.e("JSON", loginInfo.toString());
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
