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

import org.kjots.json.object.shared.JsonBooleanMap;

/**
 * JavaScript JSON Boolean Map Implementation.
 * <p>
 * Created: 12th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonBooleanMapImpl extends JsJsonObjectImpl implements JsonBooleanMap {
  /**
   * Construct a new JavaScript JSON Boolean Map Implementation.
   *
   * @param jsEngine The JavaScript engine.
   * @param jsObject The JavaScript object.
   */
  public JsJsonBooleanMapImpl(Invocable jsEngine, Object jsObject) {
    super(JsonBooleanMap.class, jsEngine, jsObject);
  }

  /**
   * Retrieve the boolean value of the element with the given key.
   *
   * @param key The key.
   * @return The boolean value.
   * @see #set(String, Boolean)
   */
  @Override
  public final Boolean get(String key) {
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
  public final void set(String key, Boolean value) {
    this.setBooleanProperty(key, value);
  }
}
