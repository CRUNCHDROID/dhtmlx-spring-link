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
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Default implementation of a Grid
 *
 * @author Madytyoo
 * @since 1.0
 */
public class DefaultGridAdapter extends AbstractAdapter implements Adapter {
    private List data;
    protected final List<String> columnList = new ArrayList<String>();
    private GridInterceptor interceptor = new WebGridInterceptor();
    protected List<Column> items = new ArrayList<Column>();


    /**
     * Default constructor for grid
     */
    public DefaultGridAdapter() {

    }

    /**
     * Default constructor for grid
     */
    public DefaultGridAdapter(GridInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    /**
     * Constructor for grid
     * @param clazz class of objects in the grid
     */
    public DefaultGridAdapter(Class clazz) {
        this.fieldList = getObjectFields(clazz);
        for(String field : fieldList ) {
            this.columnList.add( field );
        }
    }

    /**
     * Constructor for a mobile grid
     * @param clazz class of objects in the grid
     * @param interceptor an GridInterceptor implementation
     */
    public DefaultGridAdapter(Class clazz,GridInterceptor interceptor) {
        this.fieldList = getObjectFields(clazz);
        for(String field : fieldList ) {
            this.columnList.add( field );
        }
        this.interceptor = interceptor;
    }

    public DefaultGridAdapter(String columnList,Class clazz) {
        this.fieldList = getObjectFields(clazz);
        computeColumnList(columnList);
    }


    public DefaultGridAdapter(String columnList,Class clazz,GridInterceptor interceptor) {
        this.fieldList = getObjectFields(clazz);
        computeColumnList(columnList);
        this.interceptor = interceptor;
    }

    public void setData(List data) {
        this.data = data;
    }

    /**
     * Serialize the grid data to XML format
     * @param locale locale
     * @return an String containing the XML representation of the Grid Data
     */
    @Override
    public String serialize(Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version='1.0' encoding='UTF-8'?>");
        interceptor.onStartRows(this, data, buffer);
        interceptor.onHeader(this,locale,data,buffer);
        if( this.data != null ) {
            for(Object object : this.data) {
                interceptor.onStartRow(this, object, buffer);
                for(String column : columnList ) {
                    interceptor.onRenderCell(this, locale ,object, column, buffer);
                }
                interceptor.onEndRow(this, object, buffer);
            }
        }
        interceptor.onEndRows(this, locale, buffer);

        interceptor.onOutput(buffer);
        return buffer.toString();
    }

    private void computeColumnList(String columnList) {
        StringTokenizer st = new StringTokenizer(columnList,",");
        while(st.hasMoreTokens()) {
              this.columnList.add(st.nextToken());
        }
    }

    public List<Column> getItems() {
        return items;
    }

    public Column add(Column item) {
        if( !columnList.contains( item.getName()) ) {
            this.items.add( item );
            this.columnList.add( item.getName() );
        }
        return item;
    }
}
