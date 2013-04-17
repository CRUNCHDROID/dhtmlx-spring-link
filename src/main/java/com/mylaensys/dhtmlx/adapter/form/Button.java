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

public class Button extends AbstractItem {
    private Object value;

    public Button(String name) {
        this.name = name;
    }

    public Button(String name,Object value) {
        this.name = name;
        this.value = value;
    }

    public String renderWeb(DefaultFormAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( startTag(adapter) );
        buffer.append( formatAttribute("type",this.getClass().getSimpleName().toLowerCase()) );
        buffer.append( formatAttribute("name", this.name) );
        buffer.append( formatAttribute("value", this.value) );

        buffer.append( endTag( adapter ,locale) );
        return buffer.toString();
    }

    public String renderTouch(DefaultFormAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( startTag(adapter) );
        buffer.append( formatAttribute("view", "button" ) );
        buffer.append( formatAttribute("label", this.value ) );
        buffer.append( formatAttribute("value", this.value ) );
        buffer.append( formatAttribute("id", this.name) );
        buffer.append( endTag( adapter ,locale) );
        return buffer.toString();
    }
}
