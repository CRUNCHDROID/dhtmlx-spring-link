package com.mylaensys.dhtmlx.adapter;


import java.util.Locale;

public interface FormInterceptor {
    public void onData(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer);
    public void onError(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer);
    public void onStore(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer);
    public void onRender(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer);

}
