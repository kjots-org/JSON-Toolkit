/* 
 * Copyright © 2009 Karl J. Ots <kjots@kjots.org>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kjots.json.object.gwt.client.impl;

import com.google.gwt.core.client.JsArrayString;

import org.kjots.json.object.shared.JsonStringArray;

/**
 * GWT JSON String Array Implementation.
 * <p>
 * Created: 7th November 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonStringArrayImpl extends GwtJsonArrayImpl implements JsonStringArray {
  /**
   * Construct a new GWT JSON String Array Implementation.
   *
   * @param jsStringArray The JavaScript string array.
   */
  public GwtJsonStringArrayImpl(JsArrayString jsStringArray) {
    super(JsonStringArray.class, jsStringArray);
  }

  /**
   * Retrieve the string value of the element at the given index.
   *
   * @param index The index.
   * @return The string value.
   * @see #set(int, String)
   */
  @Override
  public final String get(int index) {
    return this.getStringElement(index);
  }
  
  /**
   * Set the element at the given index to the given string value.
   *
   * @param index The index.
   * @param value The string value.
   * @see #get(int)
   */
  @Override
  public final void set(int index, String value) {
    this.setStringElement(index, value);
  }
  
  /**
   * Insert the given string value at the given index.
   *
   * @param index The index.
   * @param value The string value.
   */
  @Override
  public final void insert(int index, String value) {
    this.insertStringElement(index, value);
  }

  /**
   * Retrieve an iterator for the array.
   *
   * @return The iterator.
   */
  @Override
  public java.util.Iterator<String> iterator() {
    return new Iterator(this);
  }
}
