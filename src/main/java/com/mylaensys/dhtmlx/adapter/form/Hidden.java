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

public class Hidden extends AbstractItem {
    private Object value;

    public Hidden(String name) {
        this.name = name;
    }

    public Hidden(String name,Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String renderWeb(DefaultFormAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( startTag(adapter) );
        buffer.append( formatAttribute("type",this.getClass().getSimpleName().toLowerCase()) );
        buffer.append( formatAttribute("name",this.name) );
        if( adapter.getData() != null ) {
            /* Check if the value has been specified in the constructor, if not apply the value of the bean */
            if( this.value == null ) {
                Object data = adapter.getObjectValue( adapter.getData(), this.name );
                if( data != null ) {
                    String value = adapter.formatData( locale , data);
                    buffer.append( formatAttribute("value", value ));
                }
            } else {
                buffer.append( formatAttribute("value", value ));
            }
        }
        buffer.append( endTag( adapter ,locale) );
        return buffer.toString();
    }

    @Override
    public String renderTouch(DefaultFormAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( startTag(adapter) );
        buffer.append( formatAttribute("view","hidden" ) );
        buffer.append( formatAttribute("id", this.name) );
        buffer.append( formatAttribute("label", "" ) );
        buffer.append( formatAttribute("value",adapter.getObjectValue( adapter.getData(), this.name )) );
        buffer.append( endTag(adapter, locale) );
        return buffer.toString();
    }
}
