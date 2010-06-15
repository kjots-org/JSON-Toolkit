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

import org.kjots.json.object.shared.JsonBooleanArray;

import com.google.gwt.core.client.JsArrayBoolean;

/**
 * GWT JSON Boolean Array Implementation.
 * <p>
 * Created: 7th November 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonBooleanArrayImpl extends GwtJsonArrayImpl implements JsonBooleanArray {
  /**
   * Construct a new GWT JSON Boolean Array Implementation.
   *
   * @param jsBooleanArray The JavaScript boolean array.
   */
  public GwtJsonBooleanArrayImpl(JsArrayBoolean jsBooleanArray) {
    super(jsBooleanArray);
  }

  /**
   * Retrieve the boolean value of the element at the given index.
   *
   * @param index The index.
   * @return The boolean value.
   * @see #set(int, Boolean)
   */
  @Override
  public final Boolean get(int index) {
    return this.getBooleanElement(index);
  }
  
  /**
   * Set the element at the given index to the given boolean value.
   *
   * @param index The index.
   * @param value The boolean value.
   * @see #get(int)
   */
  @Override
  public final void set(int index, Boolean value) {
    this.setBooleanElement(index, value);
  }
  
  /**
   * Insert the given boolean value at the given index.
   *
   * @param index The index.
   * @param value The boolean value.
   */
  @Override
  public final void insert(int index, Boolean value) {
    this.insertBooleanElement(index, value);
  }

  /**
   * Retrieve an iterator for the array.
   *
   * @return The iterator.
   */
  @Override
  public java.util.Iterator<Boolean> iterator() {
    return new Iterator(this);
  }
}
