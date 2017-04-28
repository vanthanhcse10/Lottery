package com.thanhnguyen.lottery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.thanhnguyen.model.Date;
import com.thanhnguyen.model.Province;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.Object;

public class MainActivity extends AppCompatActivity {

    private static final String LINK_LOTTERY = "http://thanhhungqb.tk:8080/kqxsmn";

    ArrayList<Province> arrProvinces = new ArrayList<>();

    ArrayList<String> arrProvinceName;
    ArrayAdapter<String> adapterProvinces;
    ListView lvProvinces;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();

        new LotteryTask().execute();

    }

    private void addControls() {
        lvProvinces = (ListView) findViewById(R.id.lvProvinces);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thông báo!");
        progressDialog.setMessage("Loading ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        arrProvinceName = new ArrayList<>();
        adapterProvinces = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrProvinceName);
        lvProvinces.setAdapter(adapterProvinces);

    }

    private void addEvents() {
        lvProvinces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("PROVINCE", arrProvinces.get(i));
                startActivity(intent);
            }
        });
    }

    class LotteryTask extends AsyncTask<Void, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String provinces) {
            progressDialog.dismiss();
            ArrayList<String> arrProvince_Name = new ArrayList<>();
            try {
                JSONObject jsonProvinces = new JSONObject(provinces);
                arrProvince_Name = getListFromJsonObject(jsonProvinces);
                for (int i = 0; i< arrProvince_Name.size(); i++)
                {
                    JSONObject jsonProvince = jsonProvinces.getJSONObject(arrProvince_Name.get(i));
                    ArrayList<String> arrDateName = getListFromJsonObject(jsonProvince);
                    ArrayList<Date> arrDate = new ArrayList<>();
                    for(int j = 0; j<arrDateName.size(); j++)
                    {
                        JSONObject jsonDate = jsonProvince.getJSONObject(arrDateName.get(j));
                        arrDate.add(new Date(
                                arrDateName.get(j), jsonDate.getString("1"), jsonDate.getString("2"),
                                jsonDate.getString("3"), jsonDate.getString("4"), jsonDate.getString("5"),
                                jsonDate.getString("6"), jsonDate.getString("7"), jsonDate.getString("8"),
                                jsonDate.getString("DB")
                        ));
                    }
                    arrProvinces.add(new Province(arrProvince_Name.get(i), arrDate));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapterProvinces.addAll(arrProvince_Name);
        }

        @Override
        protected String doInBackground(Void... voids) {
            StringBuffer buffer = new StringBuffer();
            try{
                URL url = new URL(LINK_LOTTERY);
                InputStream inputStream = url.openConnection().getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
            }catch (Exception e)
            {
                Log.e("ERROR", e.toString());
            }
            return buffer.toString();
        }
    }

    public static ArrayList<String> getListFromJsonObject(JSONObject jObject) throws JSONException {
        Iterator<String> keys = jObject.keys();

        ArrayList<String> keysList = new ArrayList<String>();
        while (keys.hasNext()) {
            keysList.add(keys.next());
        }
        return keysList;
    }
}

