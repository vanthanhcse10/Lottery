package com.thanhnguyen.lottery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.thanhnguyen.fragment.MyFragment;
import com.thanhnguyen.model.Date;
import com.thanhnguyen.model.Province;

import java.util.ArrayList;

public class Detail extends AppCompatActivity {

    ArrayList<Date> arrDate;
    TextView txtProvinceName;

    Spinner spDate;
    ArrayList<String> arrDateName;
    ArrayAdapter<String> adapterDate;

    Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        addControls();
        addEvents();
    }

    private void addControls() {
        txtProvinceName = (TextView) findViewById(R.id.txtProvinceName);
        spDate = (Spinner) findViewById(R.id.spDate);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        Province province = (Province) getIntent().getSerializableExtra("PROVINCE");
        arrDate = province.getArrDate();
        txtProvinceName.setText("Kết quả xổ số tỉnh: " + province.getProvinceName());
        arrDateName = new ArrayList<>();
        for(int i = 0; i< arrDate.size(); i++)
            arrDateName.add(arrDate.get(i).dateName);

        adapterDate = new ArrayAdapter<String>(Detail.this, android.R.layout.simple_spinner_item, arrDateName);
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDate.setAdapter(adapterDate);
    }

    private void addEvents() {
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDate();
            }
        });
    }

    private void sendDate() {
        String dateName = spDate.getSelectedItem().toString();
        Date date = new Date();
        for(int i = 0; i< arrDate.size(); i++)
        {
            if(arrDate.get(i).dateName.equals(dateName))
                date = arrDate.get(i);
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("DATE", date);

        MyFragment myFragment = new MyFragment();
        myFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.my_fagment,myFragment).commit();
    }
}
