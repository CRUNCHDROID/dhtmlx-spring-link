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
 * Select option
 * @author Madytyoo
 */

public class Option extends AbstractItem {
    private Select parent;
    private String value;
    private String text;

    public Option(Select parent,String value, String text) {
        this.parent = parent;
        this.value = value;
        this.text = text;
        this.parent = parent;
    }


    @Override
    public String renderWeb(DefaultFormAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "<" ).append(this.getClass().getSimpleName().toLowerCase());
        buffer.append( formatAttribute("text", this.text) );
        buffer.append( formatAttribute("value", this.value) );
        if(this.value != null && this.value.equalsIgnoreCase( parent.getSelected() ) ) {
            buffer.append( formatAttribute("selected", "true" ) );
        }
        buffer.append("/>");
        return buffer.toString();
    }

    @Override
    public String renderTouch(DefaultFormAdapter adapter, Locale locale) {
        return null;
    }
}
