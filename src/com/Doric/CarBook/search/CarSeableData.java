package com.Doric.CarBook.search;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.Doric.CarBook.Static;
import com.Doric.CarBook.utility.JSONParser;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

//����Ʒ�����ݾ�̬�ࡣ�Ա�֤���κ�Act...�ж����Ի�ó�����Ϣ
public class CarSeableData {
    //Ʒ��list
    public static ArrayList<CarSeable> mCarSeable;

    public static MyFragment context;
    public static JSONObject brandObj;
    public static List<NameValuePair> brandParams = new ArrayList<NameValuePair>();
    public static String url = Static.BASE_URL + "/search.php";
    public static boolean isload = false;
    //�ӷ������˻�ȡ����
    static {
        mCarSeable = new ArrayList<CarSeable>();
    }
    public static void getData(MyFragment c) {
        context=c;
        if(!isload) {
            brandParams.add(new BasicNameValuePair("tag", "brand"));

            new GetBrand().execute();
        }
        else
        context.initPage();

    }

    public static CarSeable find(String cName) {
        for (CarSeable cs : mCarSeable) {
            if (cs.getCarSeableName().equals(cName))
                return cs;
        }
        return null;
    }

    private static class  GetBrand extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
            //����"���ڵ�¼"��
            context.loading();
        }

        protected Void doInBackground(Void... params) {
            //���������������
            JSONParser jsonParser = new JSONParser();
            brandObj = jsonParser.getJSONFromUrl( url,  brandParams);
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            context.stopLoading();
            if ( brandObj!=null) {
                try {
                    int success = brandObj.getInt("success");
                    if(success==1){
                        int num = brandObj.getInt("brand_number");
                        for(int i=1;i<=num;i++){
                            CarSeable cs = new CarSeable();
                            cs.setCarSeableName( brandObj.getString("brand_"+i));
                            cs.setPicPath(Static.BASE_URL+"/"+  brandObj.getString("brand_"+i+"_url"));

                             mCarSeable.add(cs);
                        }
                        isload= true;
                        if(mCarSeable.size()>0)
                        Collections.sort( mCarSeable, new ComparatorCarSeable());
                        context.initPage();
                    }
                }
                catch(JSONException e)
                {
                    Context c= context.getActivity().getApplicationContext();
                    if(c==null) return;
                    Toast.makeText( c,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
            else {
                Context c= context.getActivity().getApplicationContext();
                if(c==null) return;
                Toast.makeText( c, "�޷��������磬���������ֻ���������", Toast.LENGTH_LONG).show();
            }
        }
    }



}

