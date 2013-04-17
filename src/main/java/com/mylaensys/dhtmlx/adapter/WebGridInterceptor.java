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
 * Default implementation of GridInterceptor
 *
 * @author Madytyoo
 * @since 1.0
 */
public class WebGridInterceptor implements GridInterceptor {
    protected static final Logger log = Logger.getLogger(WebGridInterceptor.class.getName());

    public void onHeader(DefaultGridAdapter adapter, Locale locale,List list, StringBuffer buffer) {
        if( adapter.getItems().size() > 0 ) {
            buffer.append("<head>");
            for(Column column : adapter.getItems() ) {
                buffer.append( column.renderWeb(adapter, locale));
            }
            buffer.append("</head>");
        }
    }

    public void onStartRows(DefaultGridAdapter adapter, List list, StringBuffer buffer) {
        buffer.append("<rows>");
    }

    public void onEndRows(DefaultGridAdapter adatper, Locale locale, StringBuffer buffer) {
        buffer.append("</rows>");
    }

    public void onStartRow(DefaultGridAdapter adapter, Object object, StringBuffer buffer) {
        buffer.append("<row id='").append( adapter.getPrimaryKey(object).toString() ).append("'>");
    }

    public void onEndRow(DefaultGridAdapter adapter, Object object, StringBuffer buffer) {
        buffer.append("</row>");
    }

    public void onRenderCell(DefaultGridAdapter adapter, Locale locale,Object object, String column, StringBuffer buffer) {
        Object value = adapter.getObjectValue(object, column);
        if( adapter.getItems().size() > 0 ) {

        } else {

        }
        buffer.append("<cell><![CDATA[").append( adapter.formatData( locale , value ) ).append("]]></cell>");
    }

    public void onOutput(StringBuffer buffer) {

    }
}
