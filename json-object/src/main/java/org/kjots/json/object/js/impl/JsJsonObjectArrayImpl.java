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

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectArray;

/**
 * JavaScript JSON Object Array Implementation.
 * <p>
 * Created: 12th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonObjectArrayImpl<T extends JsonObject> extends JsJsonArrayImpl implements JsonObjectArray<T> {
  /** The element class. */
  private final Class<T> elementClass;
  
  /**
   * Construct a new JavaScript JSON Object Array Implementation.
   *
   * @param jsEngine The JavaScript engine.
   * @param jsObject The JavaScript object.
   */
  @SuppressWarnings("unchecked")
  public JsJsonObjectArrayImpl(Invocable jsEngine, Object jsObject) {
    this(jsEngine, jsObject, (Class<T>)JsonObject.class);
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
  public final <E extends JsonObject> JsonObjectArray<E> castElement(Class<E> elementClass) {
    if (elementClass.equals(this.elementClass)) {
      return (JsonObjectArray<E>)this;
    }
    
    return new JsJsonObjectArrayImpl<E>(this.jsEngine, this.jsObject, elementClass);
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
   * Construct a new JavaScript JSON Object Array Implementation.
   *
   * @param jsScriptEngine The JavaScript engine.
   * @param jsObject The JavaScript object.
   * @param elementClass The element class.
   */
  private JsJsonObjectArrayImpl(Invocable jsEngine, Object jsObject, Class<T> elementClass) {
    super(JsonObjectArray.class, jsEngine, jsObject);
    
    this.elementClass = elementClass;
  }
}
