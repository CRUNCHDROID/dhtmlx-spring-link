package com.mylaensys.dhtmlx.adapter;

import java.util.List;
import java.util.Locale;

public class DataViewInterceptorImpl implements DataViewInterceptor {

    public void onStartData(DefaultDataViewAdapter adapter, List list, StringBuffer buffer) {
        buffer.append("<data>");
    }

    public void onEndData(StringBuffer buffer) {
        buffer.append("</data>");
    }

    public void onStartItem(DefaultDataViewAdapter adapter, Object object, StringBuffer buffer) {
        buffer.append("<item id='").append( adapter.getPrimaryKey(object) ).append("'>");
        
    }

    public void onEndItem(StringBuffer buffer) {
        buffer.append("</item>");
    }

    public void onRenderItem(DefaultDataViewAdapter adapter, Locale locale, Object object, String item, StringBuffer buffer) {
        Object value = adapter.getObjectValue(object, item);
        buffer.append("<" ).append( item ).append("><![CDATA[").append( adapter.formatData(locale, value) ).append("]]></").append(item).append(">");
    }
}
