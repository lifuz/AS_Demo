package com.lifuz.testdraw.utils;


import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class MyValueFormatter implements ValueFormatter {

    private DecimalFormat mFormat;
    
    public MyValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }
    
//    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value) + "次";
    }

    @Override
    public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return null;
    }
}
