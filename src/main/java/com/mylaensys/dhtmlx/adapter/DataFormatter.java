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


import com.google.appengine.api.datastore.Key;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

public interface DataFormatter {
    String format(Locale locale, Long value);
    String format(Locale locale, Integer integer);
    String format(Locale locale, BigDecimal bigDecimal);
    String format(Locale locale, Date date);
    String format(Locale locale, Boolean bool);
    String format(Locale locale, String string);
    String format(Locale locale, Key key);
}
