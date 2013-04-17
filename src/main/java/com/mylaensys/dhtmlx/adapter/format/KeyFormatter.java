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

package com.mylaensys.dhtmlx.adapter.format;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Key Formatter
 *
 * @author Madytyoo
 */
public class KeyFormatter implements Formatter<Key> {

    public String print(Key key, Locale locale) {
        return "";
    }

    public Key parse(String source, Locale locale) throws ParseException {
        return KeyFactory.createKey("Object", Long.parseLong( source ) );
    }

}
