package com.mylaensys.dhtmlx.adapter;

import java.util.List;
import java.util.Locale;

public interface DataViewInterceptor {
    public void onStartData(DefaultDataViewAdapter adapter, List list, StringBuffer buffer);
    public void onEndData(StringBuffer buffer);

    public void onStartItem(DefaultDataViewAdapter adapter, Object object, StringBuffer buffer);
    public void onEndItem(StringBuffer buffer);

    public void onRenderItem(DefaultDataViewAdapter adapter,Locale locale, Object object, String data, StringBuffer buffer);
}
