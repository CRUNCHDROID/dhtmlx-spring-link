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
package com.mylaensys.dhtmlx.adapter.form;


import com.mylaensys.dhtmlx.adapter.AbstractAdapter;
import com.mylaensys.dhtmlx.adapter.DefaultFormAdapter;
import org.hibernate.validator.constraints.NotEmpty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class AbstractItem implements FormItem {
    protected String name = "";
    protected List<FormItem> items = new ArrayList<FormItem>();

    public abstract String renderWeb(DefaultFormAdapter adapter, Locale locale);
    public abstract String renderTouch(DefaultFormAdapter adapter, Locale locale);

    public String getName() {
        return name;
    }

    public FormItem add(FormItem item ) {
        this.items.add( item );
        return item;
    }
    protected StringBuffer formatAttribute(String name,Object value) {
        StringBuffer buffer = new StringBuffer();
        if( value != null ) {
            buffer.append(" ").append(name).append("='").append( value ).append("'");
        }
        return buffer;
    }

    protected StringBuffer endTag(DefaultFormAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();
        if(this.items.size() == 0) {
            buffer.append("/>");
        } else {
            buffer.append(">");
            for(FormItem item : this.items ) {
                buffer.append( item.renderWeb(adapter, locale) );
            }
            buffer.append("</item>");
        }
        return buffer;
    }

    protected StringBuffer startTag(AbstractAdapter adapter) {
        return new StringBuffer("<item ");
    }

    protected String computeValidators(String name, Object data) {
        List<String> constraints = new ArrayList<String>();
        Field fields[] =  data.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase(name)) {
                if (field.getType() == Integer.class) {
                    constraints.add("ValidInteger");
                }
                if (field.getType() == Date.class) {
                    constraints.add("ValidDateFormat");
                }
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType() == NotEmpty.class) {
                        constraints.add("NotEmpty");
                    }
                }
            }
        }
        StringBuffer buffer = new StringBuffer();
        for(int i = 0;i < constraints.size();i++) {
            buffer.append(constraints.get(i)).append( i == (constraints.size()-1) ? "" : ",");
        }
        return buffer.toString();
    }
}
