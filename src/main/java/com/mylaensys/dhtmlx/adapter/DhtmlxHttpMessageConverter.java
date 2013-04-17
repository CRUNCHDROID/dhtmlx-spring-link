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

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;

import java.io.IOException;
import java.nio.charset.Charset;

public class DhtmlxHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    @Override
    protected boolean supports(Class<?> clazz) {
        Class[] theInterfaces = clazz.getInterfaces();
        for (int i = 0; i < theInterfaces.length; i++) {
            if( theInterfaces[i].getName().equalsIgnoreCase( Adapter.class.getName() ) ) {
                return true;
            }
        }
        return true;
    }

    public DhtmlxHttpMessageConverter() {
        super(new MediaType("application", "xml", DEFAULT_CHARSET));
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Adapter adapter = (Adapter)object;
        outputMessage.getBody().write( adapter.serialize(((ServletServerHttpResponse) outputMessage).getServletResponse().getLocale()).getBytes() );
    }
}
