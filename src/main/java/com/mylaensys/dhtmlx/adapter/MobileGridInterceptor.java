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


import com.mylaensys.dhtmlx.adapter.grid.Column;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * GridIntercepter implementation for mobile
 *
 * @author Madytyoo
 * @since 1.0
 */
public class MobileGridInterceptor implements GridInterceptor {
    protected static final Logger log = Logger.getLogger(MobileGridInterceptor.class.getName());

    @Override
    public void onHeader(DefaultGridAdapter adapter , Locale locale, List list, StringBuffer buffer) {
        for(Column column : adapter.getItems() ) {
            buffer.append( column.renderTouch(adapter, locale));
        }
    }

    @Override
    public void onStartRows(DefaultGridAdapter adapter, List list, StringBuffer buffer) {
        buffer.append("<data>");
    }

    @Override
    public void onEndRows(DefaultGridAdapter adatper, Locale locale, StringBuffer buffer) {
        buffer.append("</data>");
    }

    @Override
    public void onStartRow(DefaultGridAdapter adapter, Object object, StringBuffer buffer) {
        buffer.append("<item id='").append( adapter.getPrimaryKey(object).toString() ).append("'>");
    }

    @Override
    public void onEndRow(DefaultGridAdapter adapter, Object object, StringBuffer buffer) {
        buffer.append("</item>");
    }

    @Override
    public void onRenderCell(DefaultGridAdapter adapter, Locale locale, Object object, String column, StringBuffer buffer) {
        Object value = adapter.getObjectValue(object, column);
        buffer.append("<" ).append( column ).append("><![CDATA[").append(value.toString()).append("]]></").append(column).append(">");
    }

    @Override
    public void onOutput(StringBuffer buffer) {
        // custom code.
    }
}
