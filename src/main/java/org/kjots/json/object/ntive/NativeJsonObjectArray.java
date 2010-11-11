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

import java.util.LinkedList;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectArray;

/**
 * Native JSON Object Array.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonObjectArray<T extends JsonObject> extends NativeJsonArray implements JsonObjectArray<T> {
  /** The element class. */
  private final Class<T> elementClass;
  
  /**
   * Construct a new Native JSON Object Array.
   *
   * @param elementClass The element class.
   */
  public NativeJsonObjectArray(Class<T> elementClass) {
    this.elementClass = elementClass;
  }
  
  /**
   * Cast the JSON object array to a JSON object array with the given element
   * type.
   * 
   * @param <E> The type of the element.
   * @param elementClass The class of the element.
   */
  @Override
  @SuppressWarnings("unchecked")
  public <E extends JsonObject> NativeJsonObjectArray<E> castElement(Class<E> elementClass) {
    if (elementClass.equals(this.elementClass)) {
      return (NativeJsonObjectArray<E>)this;
    }
    
    return new NativeJsonObjectArray<E>(this.list, elementClass);
  }
  
  /**
   * Retrieve the object value of the element at the given index.
   *
   * @param index The index.
   * @return The object value.
   * @see #set(int, JsonObject)
   */
  @Override
  public final T get(int index) {
    return this.getObjectElement(index, this.elementClass);
  }

  /**
   * Set the element at the given index to the given object value.
   *
   * @param index The index.
   * @param value The object value.
   * @see #get(int)
   */
  @Override
  public final void set(int index, T value) {
    this.setObjectElement(index, value);
  }

  /**
   * Insert the given object value at the given index.
   *
   * @param index The index.
   * @param value The object value.
   */
  @Override
  public final void insert(int index, T value) {
    this.insertObjectElement(index, value);
  }

  /**
   * Retrieve an iterator for the array.
   *
   * @return The iterator.
   */
  @Override
  public final java.util.Iterator<T> iterator() {
    return new Iterator<T>(this);
  }
  
  /**
   * Construct a new Native JSON Object Array.
   *
   * @param list The list.
   * @param elementClass The element class.
   */
  private NativeJsonObjectArray(LinkedList<Object> list, Class<T> elementClass) {
    super(list);
    
    this.elementClass = elementClass;
  }
}
