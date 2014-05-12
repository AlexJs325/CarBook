package com.Doric.CarBook.search;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.Doric.CarBook.Static;
import com.Doric.CarBook.utility.JSONParser;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CarSeable {

    private ArrayList<CarSeries> carSeriesList;    //��ϵlist

    private String carSeableName;
    private String picPath;

    private MyFragment context;            //���ڵ�������Activity �е�loading �� stopLoading

    private JSONObject seriesObj;

    private List<NameValuePair> seriesParams = new ArrayList<NameValuePair>();

    private String url = Static.BASE_URL + "/search.php";

    private boolean isload = false;

    //init
    public CarSeable() {
        carSeriesList = new ArrayList<CarSeries>();

    }

    //��ȡ��ϵ
    CarSeries findCarSeries(String name) {
        for (CarSeries cs : carSeriesList) {
            if (cs.getName().equals(name))
                return cs;
        }
        return null;
    }

    //�ӷ�������ȡ��ϵ��Ϣ
    public void LoadCarSeries(MyFragment f) {

        //brandParams
        context = f;
        if (!isload) {
            seriesParams.add(new BasicNameValuePair("tag", "brand_series"));
            seriesParams.add(new BasicNameValuePair("brand", carSeableName));

            new GetSeries().execute();
        }

        /*
        if (carSeableName.equals("����")) {

                CarSeries  ci = new CarSeries();
                ci.setCarSeableName(carSeableName);
                ci.setName("D");
                ci.gsetHighPrice(200.0);
                ci.setLowPrice(100.0);
                carSeriesList.add(ci);

                ci = new CarSeries();
                ci.setName("F");
                ci.setCarSeableName(carSeableName);
                ci.gsetHighPrice(200.0);
                ci.setLowPrice(100.0);
                carSeriesList.add(ci);

        } else if (carSeableName.equals("����")) {

                CarSeries  ci = new CarSeries();
            ci.setCarSeableName(carSeableName);
                ci.setName("F");
                ci.gsetHighPrice(200.0);
                ci.setLowPrice(100.0);
                carSeriesList.add(ci);

        } else if (carSeableName.equals("����")) {
            CarSeries  ci = new CarSeries();
            ci.setCarSeableName(carSeableName);
            ci.setName("B");
            ci.gsetHighPrice(200.0);
            ci.setLowPrice(100.0);
            carSeriesList.add(ci);
        } else if (carSeableName.equals("����")) {
            CarSeries  ci = new CarSeries();
            ci.setCarSeableName(carSeableName);
            ci.setName("S");
            ci.gsetHighPrice(200.0);
            ci.setLowPrice(100.0);
            carSeriesList.add(ci);
        }*/


    }


    public ArrayList<CarSeries> getCarSeriesList() {
        return carSeriesList;
    }

    public void setCarSeriesist(ArrayList<CarSeries> carSeriesList) {
        this.carSeriesList = carSeriesList;
    }

    public String getCarSeableName() {
        return carSeableName;
    }

    public void setCarSeableName(String carSeableName) {
        this.carSeableName = carSeableName;
    }


    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }


    private class GetSeries extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
            //����"���ڵ�¼"��
            context.loading();
        }

        protected Void doInBackground(Void... params) {
            //���������������
            JSONParser jsonParser = new JSONParser();
            seriesObj = jsonParser.getJSONFromUrl(url, seriesParams);
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            context.stopLoading();
            if (seriesObj != null) {
                try {
                    int success = seriesObj.getInt("success");
                    if (success == 1) {
                        int num = seriesObj.getInt("brand_series_number");
                        for (int i = 1; i <= num; i++) {
                            CarSeries cs = new CarSeries();
                            cs.setName(seriesObj.getString("brand_series_" + i));
                            cs.setCarSeableName(carSeableName);

                            carSeriesList.add(cs);
                        }
                        isload = true;
                        if (carSeriesList.size() > 0)
                            Collections.sort(carSeriesList, new ComparatorCarSeries());

                    }
                } catch (JSONException e) {
                    Context c = context.getActivity().getApplicationContext();
                    if (c == null) return;
                    Toast.makeText(c, e.toString(), Toast.LENGTH_LONG).show();
                }
            } else {
                Context c = context.getActivity().getApplicationContext();
                if (c == null) return;
                Toast.makeText(c, "�޷��������磬���������ֻ���������", Toast.LENGTH_LONG).show();
            }
        }
    }
}












	

