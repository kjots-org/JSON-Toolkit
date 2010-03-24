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
package org.kjots.json.object.js.impl;

import javax.script.Invocable;

import org.kjots.json.object.shared.JsonNumberArray;

/**
 * JavaScript JSON Number Array Implementation.
 * <p>
 * Created: 12th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonNumberArrayImpl extends JsJsonArrayImpl implements JsonNumberArray {
  /**
   * Construct a new JavaScript JSON Number Array Implementation.
   *
   * @param jsEngine The JavaScript engine.
   * @param jsObject The JavaScript object.
   */
  public JsJsonNumberArrayImpl(Invocable jsEngine, Object jsObject) {
    super(jsEngine, jsObject);
  }
  
  /**
   * Retrieve the numeric value of the element at the given index.
   *
   * @param index The index.
   * @return The numeric value.
   * @see #set(int, Number)
   */
  @Override
  public final Number get(int index) {
    return this.getNumberElement(index);
  }
  
  /**
   * Set the element at the given index to the given numeric value.
   *
   * @param index The index.
   * @param value The numeric value.
   * @see #get(int)
   */
  @Override
  public final void set(int index, Number value) {
    this.setNumberElement(index, value);
  }
  
  /**
   * Insert the given numeric value at the given index.
   *
   * @param index The index.
   * @param value The numeric value.
   */
  @Override
  public final void insert(int index, Number value) {
    this.insertNumberElement(index, value);
  }
}
