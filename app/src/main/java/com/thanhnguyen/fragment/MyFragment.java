package com.thanhnguyen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thanhnguyen.lottery.R;
import com.thanhnguyen.model.Date;

/**
 * Created by ThanhNguyen on 4/28/2017.
 */

public class MyFragment extends Fragment {

    public MyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);

        Bundle bundle = this.getArguments();
        Date date = (Date) bundle.getSerializable("DATE");

        ((TextView) view.findViewById(R.id.rate0)).setText(date.specialRate);
        ((TextView) view.findViewById(R.id.Rate1)).setText(date.firstRate);
        ((TextView) view.findViewById(R.id.Rate2)).setText(date.twoRate);
        ((TextView) view.findViewById(R.id.Rate3)).setText(date.threeRate);
        ((TextView) view.findViewById(R.id.Rate4)).setText(date.fourRate);
        ((TextView) view.findViewById(R.id.Rate5)).setText(date.fiveRate);
        ((TextView) view.findViewById(R.id.Rate6)).setText(date.sixRate);
        ((TextView) view.findViewById(R.id.Rate7)).setText(date.sevenRate);
        ((TextView) view.findViewById(R.id.Rate8)).setText(date.eightRate);



        return view;
    }
}
