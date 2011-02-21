/* 
 * Copyright © 2010 Karl J. Ots <kjots@kjots.org>
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
package org.kjots.json.object.simple.impl;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectMap;
import org.kjots.json.object.simple.SimpleJsonValue;

/**
 * Simple JSON Object Map Implementation.
 * <p>
 * Created: 9th March 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class SimpleJsonObjectMapImpl<T extends JsonObject> extends SimpleJsonObjectImpl implements JsonObjectMap<T> {
  /** The element class. */
  private final Class<T> elementClass;

  /**
   * Construct a new Simple JSON Object Map Implementation.
   *
   * @param simpleJsonValue The simple JSON value.
   */
  @SuppressWarnings("unchecked")
  public SimpleJsonObjectMapImpl(SimpleJsonValue simpleJsonValue) {
    this(simpleJsonValue, (Class<T>)JsonObject.class);
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
    
    return new SimpleJsonObjectMapImpl<E>(this.simpleJsonValue, elementClass);
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
   * Construct a new Simple JSON Object Map Implementation.
   *
   * @param simpleJsonValue The simple JSON value.
   * @param elementClass The element class.
   */
  private SimpleJsonObjectMapImpl(SimpleJsonValue simpleJsonValue, Class<T> elementClass) {
    super(JsonObjectMap.class, simpleJsonValue);
    
    this.elementClass = elementClass;
  }
}
