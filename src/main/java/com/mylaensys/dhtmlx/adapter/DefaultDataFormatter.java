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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Default implementation of DataFormatter
 *
 * @author Madytyoo
 * @since 1.0
 */
public class DefaultDataFormatter implements DataFormatter {
    /**
     * Format a java.lang.Long for the given Locale
     * @param locale locale
     * @param value value to format
     * @return a String with the formatted value,emtpy if null
     */
    @Override
    public String format(Locale locale, Long value) {
        return value == null ? "" : value.toString();
    }
    /**
     * Format a java.lang.Integer for the given Locale
     * @param locale  locale
     * @param integer value to format
     * @return a String with the formatted value,emtpy if null
     */
    @Override
    public String format(Locale locale, Integer integer) {
        return integer == null ? "" : integer.toString();
    }
    /**
     * Format a java.lang.BigDecimal for the given Locale
     * @param locale locale
     * @param decimal value to format
     * @return a String with the formatted value,emtpy if null
     */
    @Override
    public String format(Locale locale, BigDecimal decimal) {
        return decimal == null ? "" : decimal.toString();
    }
    /**
     * Format a java.util.Date for the given Locale
     * @param locale locale
     * @param date value to format
     * @return a String with the formatted value
     */
    @Override
    public String format(Locale locale, Date date) {
            String format = System.getProperty("com.mylaensys.dhtmlx.adapter.DefaultDataFormatter.DateFormat");
        if( format == null ) {
            DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT,locale);
            return dateFormatter.format(date);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }
    /**
     * Format a java.lang.Boolean (for the given Locale)
     * @param locale locale
     * @param bool value to format
     * @return a String with the formatted value
     */
    @Override
    public String format(Locale locale, Boolean bool) {
        return bool ? "1" : "0";
    }

    /**
     * Format a java.lang.String for the given Locale
     * @param locale locale
     * @param string value to format
     * @return a String with the formatted value
     */
    @Override
    public String format(Locale locale, String string) {
        return string;
    }
    /**
     * Format a com.google.appengine.api.datastore.Key
     * @param locale locale
     * @param key value to format
     * @return a String with the formatted value
     */
    @Override
    public String format(Locale locale, Key key) {
        return new Long(key.getId()).toString();
    }
}
