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


import java.util.List;
import java.util.Locale;

public interface GridInterceptor {
    public void onHeader(DefaultGridAdapter adapter, Locale locale, List list, StringBuffer buffer);

    public void onStartRows(DefaultGridAdapter adapter, List list, StringBuffer buffer);
    public void onEndRows(DefaultGridAdapter adatper, Locale locale, StringBuffer buffer);

    public void onStartRow(DefaultGridAdapter adapter, Object object, StringBuffer buffer);
    public void onEndRow(DefaultGridAdapter adapter, Object object, StringBuffer buffer);

    public void onRenderCell(DefaultGridAdapter adapter,Locale locale, Object object, String column, StringBuffer buffer);
    public void onOutput(StringBuffer buffer);
}
