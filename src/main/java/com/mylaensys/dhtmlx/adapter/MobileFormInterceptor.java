package com.mylaensys.dhtmlx.adapter;


import com.mylaensys.dhtmlx.adapter.form.FormItem;

import java.util.Locale;
import java.util.logging.Logger;

public class MobileFormInterceptor implements  FormInterceptor {
    protected static final Logger log = Logger.getLogger(MobileFormInterceptor.class.getName());

    @Override
    public void onData(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer) {

    }

    @Override
    public void onError(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer) {

    }

    @Override
    public void onStore(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer) {

    }

    @Override
    public void onRender(DefaultFormAdapter adapter, Locale locale, StringBuffer buffer) {
         buffer.append("<?xml version='1.0' encoding='UTF-8'?>");
        buffer.append("<items>");
        for (FormItem item : adapter.getItems() ) {
            buffer.append(item.renderTouch(adapter, locale));
        }
        buffer.append("</items>");
    }
}
