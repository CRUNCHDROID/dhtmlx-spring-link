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

public class Input extends AbstractItem {
    private Integer width;
    private String  label;
    private String  position;
    private Integer labelWidth;
    private String  labelAlign;

    public Input(String name,String label) {
        this.name = name;
        this.label = label;
    }

    public Input(String name,String label,Integer width) {
        this.name = name;
        this.label = label;
        this.width = width;
    }


    public Input(String name,String label,Integer width,Integer labelWidth) {
        this.name = name;
        this.label = label;
        this.width = width;
        this.labelWidth = labelWidth;
    }

    public Input(String name,String label,Integer width,Integer labelWidth,String labelAlign) {
        this.name = name;
        this.label = label;
        this.width = width;
        this.labelWidth = labelWidth;
        this.labelAlign = labelAlign;
    }
    public Input(String name,String label,Integer width,Integer labelWidth,String labelAlign,String position) {
        this.name = name;
        this.label = label;
        this.width = width;
        this.labelWidth = labelWidth;
        this.labelAlign = labelAlign;
        this.position = position;

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
                String value = adapter.formatData( locale , data);
                buffer.append( formatAttribute("value", value ));
            } else {
                buffer.append( formatAttribute("value", "" ));
            }
        }
        buffer.append( formatAttribute("validate",computeValidators(this.name,adapter.getData()) ) );

        buffer.append( endTag(adapter, locale) );
        return buffer.toString();
    }

    @Override
    public String renderTouch(DefaultFormAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( startTag(adapter) );
        buffer.append( formatAttribute("view","text" ) );
        buffer.append( formatAttribute("id", this.name) );
        buffer.append( formatAttribute("width", this.width) );
        buffer.append( formatAttribute("label",this.label) );
        buffer.append( formatAttribute("value",adapter.getObjectValue( adapter.getData(), this.name )) );

        buffer.append( endTag(adapter, locale) );
        return buffer.toString();
    }

}
