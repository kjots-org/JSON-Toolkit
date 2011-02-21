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
package org.kjots.json.object.ntive;

import java.util.HashMap;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectMap;

/**
 * Native JSON Object Map.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class NativeJsonObjectMap<T extends JsonObject> extends NativeJsonMap implements JsonObjectMap<T> {
  /** The element class. */
  private final Class<T> elementClass;
  
  /**
   * Construct a new Native JSON Object Map.
   *
   * @param elementClass The element class.
   */
  public NativeJsonObjectMap(Class<T> elementClass) {
    this.elementClass = elementClass;
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
  public <E extends JsonObject> NativeJsonObjectMap<E> castElement(Class<E> elementClass) {
    if (elementClass.equals(this.elementClass)) {
      return (NativeJsonObjectMap<E>)this;
    }
    
    return new NativeJsonObjectMap<E>(this.map, elementClass);
  }
  
  /**
   * Retrieve the numeric value of the element with the given key.
   *
   * @param key The key.
   * @return The numeric value.
   * @see #set(String, JsonObject)
   */
  @Override
  public T get(String key) {
    return this.getObjectProperty(key, this.elementClass);
  }

  /**
   * Set the element with the given key to the given numeric value.
   *
   * @param key The key.
   * @param value The numeric value.
   * @see #get(String)
   */
  @Override
  public void set(String key, T value) {
    this.setObjectProperty(key, value);
  }

  /**
   * Construct a new Native JSON Object Map.
   *
   * @param map The map.
   * @param elementClass The element class.
   */
  private NativeJsonObjectMap(HashMap<String, Object> map, Class<T> elementClass) {
    super(map);
    
    this.elementClass = elementClass;
  }
}
