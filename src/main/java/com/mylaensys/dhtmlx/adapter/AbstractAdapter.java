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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Base class for Adapter classes
 *
 * @author Madytyoo
 * @since 1.0
 */
public abstract class AbstractAdapter {
    protected DataFormatter formatter = new DefaultDataFormatter();
    protected List<String> fieldList = new ArrayList<String>();
    protected List<ErrorMessage> errorList = new ArrayList<ErrorMessage>();
    protected static final Logger log = Logger.getLogger(AbstractAdapter.class.getName());

    /**
     * Returns a java.util.List of the attribute names for the given class
     * @param clazz class of objects
     * @return a java.util.List of String
     */
    protected List<String> getObjectFields(Class clazz) {
        List<String> fieldList = new ArrayList<String>();
        Field fields[] =  clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().startsWith("jdo")) {
                fieldList.add(field.getName());
            }
        }
        return fieldList;
    }

    /**
     * Returns the Object primary key
     * @param object  instance of the object
     * @return the object primary key field
     */
    protected Object getPrimaryKey(Object object) {
        try {
            Method method = object.getClass().getMethod("getId");
            return  method.invoke(object);

        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        return null;
    }

    /**
     * Returns the value of the given attribute
     * @param object   instance of the object
     * @param attribute name of the attribute
     * @return the value of the attribute
     */
    public Object getObjectValue(Object object, String attribute)  {
        try {
            Method method = object.getClass().getMethod("get" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1));
            return method.invoke(object);
        } catch (Exception e) {
            log.severe( "Error getting '" + attribute + "' on " + object.getClass().getSimpleName() );
        }
        return "" ;
    }

    /**
     * Format the value for the given Locale
     * @param locale locale
     * @param value  value to format
     * @return a String with the formatted value
     */
    public String formatData(Locale locale, Object value) {
        try {
            Method method = formatter.getClass().getMethod("format", new Class[]{ locale.getClass(), value.getClass()});
            if (method != null) {
                return (String) method.invoke(formatter, new Object[]{locale, value});
            }
        }
        catch (Exception e) {
            log.severe("Could not format an instance of : " + value.getClass());
        }
        return value.toString();
    }

    public List<ErrorMessage> getErrorList() {
        return errorList;
    }
}
