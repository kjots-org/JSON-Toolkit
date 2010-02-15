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
package org.kjots.json.object.client.impl;

import com.google.gwt.core.client.JavaScriptObject;

import org.kjots.json.object.shared.JsonIntegerMap;

/**
 * GWT JSON Integer Map Implementation.
 * <p>
 * Created: 9th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonIntegerMapImpl extends GwtJsonObjectImpl implements JsonIntegerMap {
  /**
   * Construct a new GWT JSON Integer Map Implementation.
   *
   * @param jsObject The JavaScript object.
   */
  public GwtJsonIntegerMapImpl(JavaScriptObject jsObject) {
    super(jsObject);
  }
  
  /**
   * Retrieve the integer value of the element with the given key.
   *
   * @param key The key.
   * @return The integer value.
   * @see #set(String, int)
   */
  @Override
  public final int get(String key) {
    return this.getIntegerProperty(key);
  }
  
  /**
   * Set the element with the given key to the integer string value.
   *
   * @param key The key.
   * @param value The integer value.
   * @see #get(String)
   */
  @Override
  public final void set(String key, int value) {
    this.setIntegerProperty(key, value);
  }
}
