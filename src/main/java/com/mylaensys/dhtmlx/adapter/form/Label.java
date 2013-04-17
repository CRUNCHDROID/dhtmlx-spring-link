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

public class Label extends AbstractItem {
    private String  label;
    private String  className;
    private Integer labelWidth;
    private Integer offsetLeft;


    public Label(String name,String label) {
        this.name = name;
        this.label = label;
    }
    public Label(String name,String label,String className) {
        this.name = name;
        this.label = label;
        this.className = className;

    }

    public Label(String name,String label,Integer labelWidth) {
        this.name = name;
        this.label = label;
        this.labelWidth = labelWidth;
    }

    public Label(String name,String label,Integer labelWidth,Integer offsetLeft) {
        this.name = name;
        this.label = label;
        this.labelWidth = labelWidth;
        this.offsetLeft = offsetLeft;
    }

    @Override
    public String renderWeb(DefaultFormAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( startTag(adapter) );
        buffer.append( formatAttribute("type", this.getClass().getSimpleName().toLowerCase()) );
        buffer.append( formatAttribute("name", this.name) );
        buffer.append( formatAttribute("label",this.label) );
        buffer.append( formatAttribute("labelWidth",this.labelWidth) );
        buffer.append( formatAttribute("offsetLeft",this.offsetLeft) );
        buffer.append( formatAttribute("className",this.className) );
        buffer.append( endTag(adapter, locale) );
        return buffer.toString();
    }

    @Override
    public String renderTouch(DefaultFormAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();

        for(FormItem item : this.items ) {
            buffer.append( item.renderTouch(adapter, locale) );
        }
        return buffer.toString();

    }
}
