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
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.HashSet;
import java.util.Set;

/**
 * Key Annotation for Google datastore JPA
 *
 * @author Madytyoo
 */
public class KeyAnnotationFormatterFactory implements AnnotationFormatterFactory<KeyFormat> {

  public Set<Class<?>> getFieldTypes() {
    Set<Class<?>> set = new HashSet<Class<?>>();
    set.add(Key.class);
    return set;
  }

  public Parser<Key> getParser(KeyFormat arg0, Class<?> arg1) {
    return new KeyFormatter();
  }

  public Printer<Key> getPrinter(KeyFormat annotation, Class<?> fieldType) {
    return new KeyFormatter();
  }
}

