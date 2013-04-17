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

import com.mylaensys.dhtmlx.adapter.DefaultFormAdapter;
import com.mylaensys.dhtmlx.adapter.MobileFormInterceptor;
import com.mylaensys.dhtmlx.adapter.form.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Locale;

/**
 * Test Form
 *
 * @author Madytyoo
 * @since 1.0
 */

public class TestForm {
    private static TestObject object;
    @BeforeClass
    public static void initialize() throws ParseException {
        object = new TestObject(1L,"10/09/2011","First String of Text",100,new BigDecimal(100.0),true);
    }


    @Test public void defaultFormAdapter() throws Exception {
        DefaultFormAdapter adapter = new DefaultFormAdapter( object );
        String xml1 = adapter.serialize(Locale.getDefault() );
        System.out.print( xml1 );
        System.out.println( "" );

        DefaultFormAdapter mobileAdapter = new DefaultFormAdapter( object , new MobileFormInterceptor());
        String xml2 = mobileAdapter.serialize(Locale.getDefault() );
        System.out.print( xml2 );
        System.out.println( "" );

    }

    @Test public void serverFormAdapter() throws Exception {
        DefaultFormAdapter adapter = new DefaultFormAdapter( object , DefaultFormAdapter.Render);
        Label buttonBlock = addFormItems(adapter);

        adapter.add(buttonBlock);
        String xml = adapter.serialize(Locale.getDefault() );
        System.out.println( xml );
        System.out.println("");

        DefaultFormAdapter mobileAdapter = new DefaultFormAdapter( object , DefaultFormAdapter.Render,new MobileFormInterceptor());
        mobileAdapter.add(buttonBlock);
        String xml2 = mobileAdapter.serialize(Locale.getDefault() );

        System.out.print( xml2 );
        System.out.println( "" );

    }

    @Test public void errorFormAdapter() throws Exception {

        BindingResult binding = new BeanPropertyBindingResult(object,"object");

        DefaultFormAdapter adapter = new DefaultFormAdapter(object,binding);
        adapter.serialize(Locale.getDefault());

        /* Adds an error */
        binding.addError(new FieldError("object","date","error"));
        DefaultFormAdapter adapterWithErrors = new DefaultFormAdapter(object,binding);
        adapterWithErrors.serialize(Locale.getDefault());


    }


    private Label addFormItems(DefaultFormAdapter adapter) {
        adapter.add(new Hidden("id"));
        adapter.add(new Label("message", "&#160;"));
        adapter.add(new Input("date", "Date", 250, 120, "right"));
        adapter.add(new Input("string", "String", 250, 120, "right"));
        adapter.add(new Input("integer", "Integer", 250, 120, "right"));
        adapter.add(new Input("sales", "Sales", 250, 120, "right"));

        Label buttonBlock = new Label("dummy1", "&#160;");
        buttonBlock.add( new Label("dummy2", "&#160;",120) );
        buttonBlock.add( new NewColumn() );
        buttonBlock.add( new Button("button_upd", "update") );
        buttonBlock.add( new NewColumn() );
        buttonBlock.add( new Button("button_close", "close") );
        return buttonBlock;
    }

}
