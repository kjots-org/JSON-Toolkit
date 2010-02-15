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
package org.kjots.json.object.server.js.impl;

import javax.script.Invocable;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectMap;

/**
 * JavaScript JSON Object Map Implementation.
 * <p>
 * Created: 12th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonObjectMapImpl<T extends JsonObject> extends JsJsonObjectImpl implements JsonObjectMap<T> {
  /** The element class. */
  private Class<T> elementClass;
  
  /**
   * Construct a new JavaScript JSON Object Map Implementation.
   *
   * @param jsEngine The JavaScript engine.
   * @param jsObject The JavaScript object.
   */
  @SuppressWarnings("unchecked")
  public JsJsonObjectMapImpl(Invocable jsEngine, Object jsObject) {
    this(jsEngine, jsObject, (Class<T>)JsonObject.class);
  }
  
  /**
   * Cast the JSON object map to a JSON object map with the given element
   * type.
   * 
   * @param <E> The type of the element.
   * @param elementClass The class of the element.
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <E extends JsonObject> JsonObjectMap<E> castElement(Class<E> elementClass) {
    if (elementClass.equals(this.elementClass)) {
      return (JsonObjectMap<E>)this;
    }
    
    return new JsJsonObjectMapImpl<E>(this.jsEngine, this.jsObject, elementClass);
  }
  
  /**
   * Retrieve the object value of the element with the given key.
   *
   * @param key The key.
   * @return The object value.
   * @see #set(String, JsonObject)
   */
  @Override
  public final T get(String key) {
    return this.getObjectProperty(key, this.elementClass);
  }
  
  /**
   * Set the element with the given key to the given object value.
   *
   * @param key The key.
   * @param value The object value.
   * @see #get(String)
   */
  @Override
  public final void set(String key, T value) {
    this.setObjectProperty(key, value);
  }
  
  /**
   * Construct a new JavaScript JSON Object Map Implementation.
   *
   * @param jsScriptEngine The JavaScript engine.
   * @param jsObject The JavaScript object.
   * @param elementClass The element class.
   */
  private JsJsonObjectMapImpl(Invocable jsEngine, Object jsObject, Class<T> elementClass) {
    super(jsEngine, jsObject);
    
    this.elementClass = elementClass;
  }
}
