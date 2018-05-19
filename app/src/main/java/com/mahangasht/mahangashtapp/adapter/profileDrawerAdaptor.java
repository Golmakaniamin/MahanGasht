package com.mahangasht.mahangashtapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mahangasht.mahangashtapp.Model.Agencies;
import com.mahangasht.mahangashtapp.R;

import java.util.ArrayList;

/**
 * Created by Amin on 2/21/2017.
 */

public class profileDrawerAdaptor extends BaseAdapter {
    private Context context;
    private Typeface tf;
    private ArrayList<Agencies> LProfileDrawer;

    public profileDrawerAdaptor(Context context, ArrayList<Agencies> LProfileDrawer) {
        this.context = context;
        this.LProfileDrawer = LProfileDrawer;
        tf = Typeface.createFromAsset(context.getAssets(), "fonts/BYEKAN_0.TTF");
    }

    @Override
    public int getCount() {
        return LProfileDrawer.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.activity_main_lvprofile_item, viewGroup, false);

        Agencies iLProfileDrawer = LProfileDrawer.get(i);

//        TextView lvp_AMobile = (TextView) rowView.findViewById(R.id.lvp_AMobile);
//        TextView lvp_AManager = (TextView) rowView.findViewById(R.id.lvp_AManager);
//        lvp_AMobile.setText(iLProfileDrawer.getAMobile());
//        lvp_AManager.setText(iLProfileDrawer.getAManager());

        TextView lvp_APhones = (TextView) rowView.findViewById(R.id.lvp_APhones);
        TextView lvp_AName = (TextView) rowView.findViewById(R.id.lvp_AName);
        TextView lvp_AAddress = (TextView) rowView.findViewById(R.id.lvp_AAddress);

        lvp_APhones.setTypeface(tf);
        lvp_APhones.setText(iLProfileDrawer.getAPhones());

        lvp_AName.setTypeface(tf);
        lvp_AName.setText(iLProfileDrawer.getAName());

        lvp_AAddress.setTypeface(tf);
        lvp_AAddress.setText(iLProfileDrawer.getAAddress());

        return rowView;
    }
}