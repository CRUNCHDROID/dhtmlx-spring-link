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

import com.mylaensys.dhtmlx.adapter.DefaultFormAdapter;

import java.util.Locale;

/**
 * Select control
 * @author Madytyoo
 */

public class Select extends AbstractItem {
    private String  label;
    private Integer width;
    private String  position;
    private Integer labelWidth;
    private String  labelAlign;
    private String  selected;


    public Select(String name,String label, Integer width, Integer labelWidth, String labelAlign, String position) {
        this.name = name;
        this.label = label;
        this.width = width;
        this.position = position;
        this.labelWidth = labelWidth;
        this.labelAlign = labelAlign;
    }

    public String getSelected() {
        return selected;
    }

    @Override
    public String renderWeb(DefaultFormAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( startTag(adapter) );
        buffer.append( formatAttribute("type",this.getClass().getSimpleName().toLowerCase()) );
        buffer.append( formatAttribute("name", this.name) );
        buffer.append( formatAttribute("bind", this.name) );
        buffer.append( formatAttribute("width", this.width) );
        buffer.append( formatAttribute("label",this.label) );
        buffer.append( formatAttribute("labelWidth",this.labelWidth) );
        buffer.append( formatAttribute("labelAlign",this.labelAlign) );
        buffer.append( formatAttribute("position",this.position) );

        if( adapter.getData() != null ) {
            Object data = adapter.getObjectValue( adapter.getData(), this.name );
            if( data != null ) {
                this.selected = adapter.formatData( locale , data);
            }
        }
        buffer.append( endTag(adapter, locale) );
        return buffer.toString();

    }

    @Override
    public String renderTouch(DefaultFormAdapter defaultFormAdapter, Locale locale) {
        return null;
    }
}
