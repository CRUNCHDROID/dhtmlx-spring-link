/*
 * Copyright 2011-2012 Mylaensys LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mylaensys.dhtmlx.adapter;


import com.mylaensys.dhtmlx.adapter.form.FormItem;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Default implementation of Form
 *
 * @author Madytyoo
 * @since 1.0
 */
public class DefaultFormAdapter extends AbstractAdapter implements Adapter {
    public static final String Insert = "inserted";
    public static final String Update = "updated";
    public static final String Delete = "deleted";
    public static final String Render = "render";
    protected String operation;
    protected Object data;
    protected FormInterceptor interceptor = new WebFormInterceptor();
    protected List<FormItem> items = new ArrayList<FormItem>();


    public DefaultFormAdapter(Object data) {
        initialize(data);
        this.operation = "";
    }

    public DefaultFormAdapter(Object data,FormInterceptor interceptor) {
        initialize(data);
        this.operation = "";
        this.interceptor = interceptor;
    }

    public DefaultFormAdapter(Object data, String operation) {
        initialize(data);
        this.operation = operation;
    }
    public DefaultFormAdapter(Object data, String operation,FormInterceptor interceptor) {
        initialize(data);
        this.operation = operation;
        this.interceptor = interceptor;
    }

    /*public DefaultFormAdapter(Object data, BindingResult binding) {
        initialize(data);
        if (binding.hasErrors()) {
            for (FieldError e : binding.getFieldErrors()) {
                this.errorList.add(new ErrorMessage(e.getField(), e.getDefaultMessage()));
            }
        }
    } */

    private void initialize(Object data) {
        this.fieldList = getObjectFields(data.getClass());
        Object id = getPrimaryKey(data);
        if (id == null) {
            this.operation = Insert;
        } else {
            this.operation = Update;
        }
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private boolean isWriting() {
        return Insert.equalsIgnoreCase(this.operation) || Update.equalsIgnoreCase(this.operation) || Delete.equalsIgnoreCase(this.operation);
    }

    private boolean isRendering() {
        return Render.equalsIgnoreCase(this.operation);
    }

    public boolean hasValidData() {
        return errorList.size() == 0;
    }


    @Override
    public String serialize(Locale locale) {
        StringBuffer buffer = new StringBuffer();

        if (isWriting()) {
            if (this.errorList.size() == 0) {
                interceptor.onStore( this , locale , buffer );
            } else {
                interceptor.onError( this , locale , buffer );
            }
        } else if( isRendering() ) {
            interceptor.onRender( this , locale , buffer );
        } else {
            interceptor.onData(this, locale, buffer);
        }
        return buffer.toString();
    }

    public String getOperation() {
        return operation;
    }

    public List<FormItem> getItems() {
        return items;
    }

    public FormItem add(FormItem item) {
        this.items.add(item);
        return item;
    }
}
