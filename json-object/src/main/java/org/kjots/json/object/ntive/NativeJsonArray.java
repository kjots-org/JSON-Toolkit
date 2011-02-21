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
import java.util.List;

import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonObject;

/**
 * Native JSON Array.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class NativeJsonArray extends NativeJsonObject implements JsonArray {
  /** The list. */
  protected final LinkedList<Object> list;
  
  /**
   * Construct a new Native JSON Array.
   */
  public NativeJsonArray() {
    this(new LinkedList<Object>());
  }
  
  /**
   * Set the element at the given index of the given list to the given value.
   *
   * @param list The list.
   * @param index The index.
   * @param value The value.
   */
  private static void set(List<Object> list, int index, Object value) {
    while (list.size() <= index) {
      list.add(null);
    }
    
    list.set(index, value);
  }

  /**
   * Determine if the JSON object is an array.
   *
   * @return <code>true</code> if the JSON object is an array.
   */
  @Override
  public boolean isArray() {
    return true;
  }
  
  /**
   * Retrieve the length of the array.
   *
   * @return The length of the array.
   * @see #setLength(int)
   */
  @Override
  public int getLength() {
    return this.list.size();
  }
  
  /**
   * Set the length of the array.
   * 
   * @param length The length of the array.
   * @see #getLength()
   */
  @Override
  public void setLength(int length) {
    if (this.list.size() < length) {
      while (this.list.size() < length) {
        this.list.addLast(null);
      }
    }
    else if (this.list.size() > length) {
      while (this.list.size() > length) {
        this.list.removeLast();
      }
    }
  }

  /**
   * Determine if the element at the given index is <code>null</code>.
   *
   * @param index The index.
   * @return <code>true</code> if the element is <code>null</code>.
   */
  @Override
  public boolean isNullElement(int index) {
    return this.list.get(index) == null;
  }

  /**
   * Determine if the element at the given index has a boolean value.
   *
   * @param index The index.
   * @return <code>true</code> if element has a boolean value.
   */
  @Override
  public boolean isBooleanElement(int index) {
    return this.list.get(index) instanceof Boolean;
  }

  /**
   * Retrieve the boolean value of the element at the given index.
   *
   * @param index The index.
   * @return The boolean value.
   * @see #setBooleanElement(int, Boolean)
   */
  @Override
  public Boolean getBooleanElement(int index) {
    return (Boolean)this.list.get(index);
  }

  /**
   * Set the element at the given index to the given boolean value.
   *
   * @param index The index.
   * @param value The boolean value.
   * @see #getBooleanElement(int)
   */
  @Override
  public void setBooleanElement(int index, Boolean value) {
    set(this.list, index, value);
  }

  /**
   * Insert the given boolean value at the given index.
   *
   * @param index The index.
   * @param value The boolean value.
   */
  @Override
  public void insertBooleanElement(int index, Boolean value) {
    this.list.add(index, value);
  }

  /**
   * Determine if the element at the given index has a numeric value.
   *
   * @param index The index.
   * @return <code>true</code> if element has a numeric value.
   */
  @Override
  public boolean isNumberElement(int index) {
    return this.list.get(index) instanceof Number;
  }

  /**
   * Retrieve the numeric value of the element at the given index.
   *
   * @param index The index.
   * @return The numeric value.
   * @see #setNumberElement(int, Number)
   */
  @Override
  public Number getNumberElement(int index) {
    return (Number)this.list.get(index);
  }

  /**
   * Set the element at the given index to the given numeric value.
   *
   * @param index The index.
   * @param value The numeric value.
   * @see #getNumberElement(int)
   */
  @Override
  public void setNumberElement(int index, Number value) {
    set(this.list, index, value);
  }

  /**
   * Insert the given numeric value at the given index.
   *
   * @param index The index.
   * @param value The numeric value.
   */
  @Override
  public void insertNumberElement(int index, Number value) {
    this.list.add(index, value);
  }

  /**
   * Determine if the element at the given index has a string value.
   *
   * @param index The index.
   * @return <code>true</code> if element has a string value.
   */
  @Override
  public boolean isStringElement(int index) {
    return this.list.get(index) instanceof String;
  }

  /**
   * Retrieve the string value of the element at the given index.
   *
   * @param index The index.
   * @return The string value.
   * @see #setStringElement(int, String)
   */
  @Override
  public String getStringElement(int index) {
    return (String)this.list.get(index);
  }

  /**
   * Set the element at the given index to the given string value.
   *
   * @param index The index.
   * @param value The string value.
   * @see #getStringElement(int)
   */
  @Override
  public void setStringElement(int index, String value) {
    set(this.list, index, value);
  }

  /**
   * Insert the given string value at the given index.
   *
   * @param index The index.
   * @param value The string value.
   */
  @Override
  public void insertStringElement(int index, String value) {
    this.list.add(index, value);
  }

  /**
   * Determine if the element at the given index has an object value.
   *
   * @param index The index.
   * @return <code>true</code> if element has an object value.
   */
  @Override
  public boolean isObjectElement(int index) {
    return this.list.get(index) instanceof JsonObject;
  }

  /**
   * Retrieve the object value of the element at the given index.
   *
   * @param index The index.
   * @return The object value.
   * @see #setObjectElement(int, JsonObject)
   */
  @Override
  public JsonObject getObjectElement(int index) {
    return (JsonObject)this.list.get(index);
  }

  /**
   * Retrieve the object value of the element at the given index.
   *
   * @param <T> The type of the object value.
   * @param index The index.
   * @param jsonObjectClass The class of the object value.
   * @return The object value.
   * @see #setObjectElement(int, JsonObject)
   */
  @Override
  public <T extends JsonObject> T getObjectElement(int index, Class<T> jsonObjectClass) {
    JsonObject jsonObject = (JsonObject)this.list.get(index);
    
    return jsonObject != null ? jsonObject.cast(jsonObjectClass) : null;
  }

  /**
   * Set the element at the given index to the given object value.
   *
   * @param index The index.
   * @param value The object value.
   * @see #getObjectElement(int)
   */
  @Override
  public void setObjectElement(int index, JsonObject value) {
    set(this.list, index, value);
  }

  /**
   * Insert the given object value at the given index.
   *
   * @param index The index.
   * @param value The object value.
   */
  @Override
  public void insertObjectElement(int index, JsonObject value) {
    this.list.add(index, value);
  }

  /**
   * Remove the given number of elements at the given index.
   *
   * @param index The index.
   * @param count The number of elements.
   */
  @Override
  public void removeElements(int index, int count) {
    this.list.subList(index, index + count).clear();
  }

  /**
   * Construct a new Native JSON Array.
   * 
   * @param list The list.
   */
  protected NativeJsonArray(LinkedList<Object> list) {
    this.list = list;
  }
}
