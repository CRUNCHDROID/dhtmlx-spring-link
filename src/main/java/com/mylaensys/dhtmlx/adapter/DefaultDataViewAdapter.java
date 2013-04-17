package com.mylaensys.dhtmlx.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DefaultDataViewAdapter extends AbstractAdapter  implements Adapter {
    private List data;
    protected List<String> fieldList = new ArrayList<String>();
    private DataViewInterceptor interceptor = new DataViewInterceptorImpl();

    public DefaultDataViewAdapter(Class clazz) {
        this.fieldList = getObjectFields(clazz);
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String serialize(Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version='1.0' encoding='UTF-8'?>");
        interceptor.onStartData(this, data, buffer);
        if( this.data != null ) {
            for(Object object : this.data) {
                interceptor.onStartItem(this, object, buffer);
                for(String item : this.fieldList) {
                    interceptor.onRenderItem(this, locale, object, item, buffer);
                }
                interceptor.onEndItem(buffer);
            }
        }
        interceptor.onEndData(buffer);

        return buffer.toString();
    }
}
