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

import org.kjots.json.object.shared.JsonBooleanMap;

/**
 * GWT JSON Boolean Map Implementation.
 * <p>
 * Created: 9th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonBooleanMapImpl extends GwtJsonObjectImpl implements JsonBooleanMap {
  /**
   * Construct a new GWT JSON Boolean Map Implementation.
   *
   * @param jsObject The JavaScript object.
   */
  public GwtJsonBooleanMapImpl(JavaScriptObject jsObject) {
    super(jsObject);
  }
  
  /**
   * Retrieve the boolean value of the element with the given key.
   *
   * @param key The key.
   * @return The boolean value.
   * @see #set(String, boolean)
   */
  @Override
  public final boolean get(String key) {
    return this.getBooleanProperty(key);
  }
  
  /**
   * Set the element with the given key to the given boolean value.
   *
   * @param key The key.
   * @param value The boolean value.
   * @see #get(String)
   */
  @Override
  public final void set(String key, boolean value) {
    this.setBooleanProperty(key, value);
  }
}
