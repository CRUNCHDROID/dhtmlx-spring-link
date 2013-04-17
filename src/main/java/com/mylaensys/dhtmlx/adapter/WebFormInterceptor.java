package com.mylaensys.dhtmlx.adapter;


import com.mylaensys.dhtmlx.adapter.form.FormItem;

import java.util.Locale;
import java.util.logging.Logger;

public class WebFormInterceptor implements FormInterceptor {
    protected static final Logger log = Logger.getLogger(WebFormInterceptor.class.getName());

    @Override
    public void onData(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer) {
        buffer.append("<?xml version='1.0' encoding='UTF-8'?>");
        buffer.append("<data>");
        try {
            if (adapter.getData() != null) {
                for (String field : adapter.fieldList) {
                    Object value = adapter.getObjectValue(adapter.getData(), field);
                    buffer.append("<").append(field).append("><![CDATA[").append(adapter.formatData(locale, value)).append("]]></").append(field).append(">");
                }
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        buffer.append("</data>");
    }

    @Override
    public void onError(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer) {
        buffer.append("<data>");
        for (ErrorMessage e : adapter.getErrorList() ) {
            Object data = adapter.getData();
            buffer.append("<action sid='").append(adapter.getPrimaryKey(data) == null ? "" : adapter.getPrimaryKey(data).toString()).append("' ");
            buffer.append("type='invalid' ");
            buffer.append("field='").append(e.getField()).append("' ");
            buffer.append("message='").append(adapter.formatData(locale, e.getMessage())).append("'/>");
        }
        buffer.append("</data>");
    }

    @Override
    public void onStore(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer) {
        buffer.append("<data>");
        buffer.append("<action type='").append(adapter.getOperation()).append("' ");
        if (adapter.getData()  != null) {
            Object key = adapter.getPrimaryKey(adapter.getData());
            String id = key == null ? "" : key.toString();
            buffer.append("sid='").append(id).append("' ");
            buffer.append("tid='").append(id).append("'/>");
        } else {
            buffer.append("field='id' ");
            buffer.append("sid='").append("0").append("' ");
            buffer.append("tid='").append("0").append("' ");
            buffer.append("message='").append(adapter.formatData(locale, "invalid data")).append("'/>");
        }
        buffer.append("</data>");

    }

    @Override
    public void onRender(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer) {
        buffer.append("<?xml version='1.0' encoding='UTF-8'?>");
        buffer.append("<items>");
        for (FormItem item : adapter.getItems() ) {
            buffer.append(item.renderWeb(adapter, locale));
        }
        buffer.append("</items>");
    }
}
