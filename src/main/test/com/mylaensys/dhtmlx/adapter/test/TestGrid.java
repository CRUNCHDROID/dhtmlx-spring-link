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

package com.mylaensys.dhtmlx.adapter.test;

import com.mylaensys.dhtmlx.adapter.DefaultGridAdapter;
import com.mylaensys.dhtmlx.adapter.MobileGridInterceptor;
import com.mylaensys.dhtmlx.adapter.grid.Column;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Test Grid
 *
 * @author Madytyoo
 * @since 1.0
 */

public class TestGrid {
    private static List<TestObject> objectList;

    @BeforeClass
    public static void initialize() throws ParseException {
		objectList = new ArrayList<TestObject>();
        objectList.add( new TestObject(1L,"10/09/2011","First String of Text",100,new BigDecimal(100.0),true) );
        objectList.add( new TestObject(2L,"11/09/2011","Second String of Text",200,new BigDecimal(100.2),false) );
	}

    /**
     * Check xml for conformance to schemas
     * @param xml xml to validate
     * @param filename name of the xsd file
     * @return true valid, false not valid
     */
    private boolean validateXSD(String xml,String filename) throws Exception {
        Boolean valid = false;

        try {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            File schemaLocation = new File( System.getProperty("dhtmlx.schema.path","/home/maurizio/IdeaProjects/dhtmlx-spring-link/src/main/resources/xsd/") + filename );
            Schema schema = factory.newSchema(schemaLocation);

            Validator validator = schema.newValidator();
            Source source = new StreamSource( new StringReader( xml ));

            validator.validate(source);
            valid = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return valid;
    }

    /**
     * Test the Default From XML Schema
     * @throws Exception
     */
    @Test public void defaultGridAdapterSchema() throws Exception {
        DefaultGridAdapter adapter = new DefaultGridAdapter(TestObject.class);
        adapter.setData( objectList );
        adapter.serialize(Locale.getDefault());
        //assertTrue(validateXSD(adapter.serialize(Locale.getDefault()), "dhtmlxGrid.xsd"));
        DefaultGridAdapter adapterWithColumns = new DefaultGridAdapter("date,string",TestObject.class);
        adapterWithColumns.setData(objectList);
        adapterWithColumns.serialize(Locale.getDefault());
        //assertTrue(validateXSD(adapter.serialize(Locale.getDefault()), "dhtmlxGrid.xsd"));

    }

    @Test public void serverGridAdapterSchema() throws Exception {
        DefaultGridAdapter adapter = new DefaultGridAdapter(TestObject.class);
        addGridColumns(adapter);

        adapter.setData(objectList);
        adapter.serialize(Locale.getDefault());

        //assertTrue(validateXSD(adapter.serialize(Locale.getDefault()), "dhtmlxGrid.xsd"));
    }

    @Test public void serverGridAdapterMobileSchema() throws Exception {
           DefaultGridAdapter adapter = new DefaultGridAdapter(TestObject.class,new MobileGridInterceptor());
           addGridColumns(adapter);

           adapter.setData( objectList );
           adapter.serialize(Locale.getDefault());
           //assertTrue(validateXSD(adapter.serialize(Locale.getDefault()), "dhtmlxGrid.xsd"));

           DefaultGridAdapter adapterWithColumns = new DefaultGridAdapter("date,string",TestObject.class,new MobileGridInterceptor());
           addGridColumns(adapterWithColumns);
           adapterWithColumns.setData(objectList);
           adapterWithColumns.serialize(Locale.getDefault());

       }

    private void addGridColumns(DefaultGridAdapter adapter) {
        adapter.add( new Column("id","ID","ro","right", "str", 100));
        adapter.add( new Column("date","Date","ro","right", "str", 100));
        adapter.add( new Column("string","String","ro","right", "str", 100));
        adapter.add( new Column("integer","Integer","ro","right", "str", 100));
        adapter.add( new Column("decimal","BigDecimal","ro","right", "str", 100));
        adapter.add( new Column("bool","Boolean","ro","right", "str", 100));
    }


}
