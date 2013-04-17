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

package com.mylaensys.dhtmlx.adapter.grid;

import com.mylaensys.dhtmlx.adapter.DefaultGridAdapter;

import java.util.Locale;

public class Column implements GridItem {
    protected String name;
    protected String header;
    protected String type;
    protected String align;
    protected String sort;
    protected Integer width;

    public Column(String name,String header, String type, String align, String sort, Integer width) {
        this.name = name;
        this.header = header;
        this.type = type;
        this.align = align;
        this.sort = sort;
        this.width = width;
    }

    public String getName() {
        return name;
    }

    @Override
    public String renderWeb(DefaultGridAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "<column " );
        buffer.append( formatAttribute("type", this.type) );
        buffer.append( formatAttribute("align", this.align) );
        buffer.append( formatAttribute("sort", this.sort ) );
        buffer.append( formatAttribute("width", this.width == -1 ? "*" : this.width ) );
        buffer.append( ">" );
        buffer.append(  adapter.formatData( locale , this.header ));
        buffer.append( "</column>" );
        return buffer.toString();
    }

    @Override
    public String renderTouch(DefaultGridAdapter adapter, Locale locale) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "<header " );
        buffer.append( formatAttribute("id", this.name) );
        buffer.append( formatAttribute("label", adapter.formatData( locale , this.header ) ) );
        buffer.append( formatAttribute("align", this.align) );
        if( this.width != -1 ) {
            buffer.append( formatAttribute("width", this.width ) );
        }
        buffer.append( "/>" );
        return buffer.toString();

    }

    protected StringBuffer formatAttribute(String name,Object value) {
        StringBuffer buffer = new StringBuffer();
        if( value != null ) {
            buffer.append(" ").append(name).append("='").append( value ).append("'");
        }
        return buffer;
    }
}
