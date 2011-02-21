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

import java.util.LinkedList;
import java.util.List;

import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.simple.SimpleJsonValue;

/**
 * Simple JSON Array Implementation.
 * <p>
 * Created: 4th March 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class SimpleJsonArrayImpl extends SimpleJsonObjectImpl implements JsonArray {
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
   * Construct a new Simple JSON Array Implementation.
   *
   * @param jsonArrayClass The JSON array class.
   * @param simpleJsonValue The simple JSON value.
   */
  public SimpleJsonArrayImpl(Class<? extends JsonArray> jsonArrayClass, SimpleJsonValue simpleJsonValue) {
    super(jsonArrayClass, simpleJsonValue);
  }
  
  /**
   * Retrieve the length of the array.
   *
   * @return The length of the array.
   * @see #setLength(int)
   */
  @Override
  public final int getLength() {
    return this.simpleJsonValue.getList().size();
  }
  
  /**
   * Set the length of the array.
   * 
   * @param length The length of the array.
   * @see #getLength()
   */
  @Override
  public final void setLength(int length) {
    LinkedList<Object> list = this.simpleJsonValue.getList();
    
    if (list.size() < length) {
      while (list.size() < length) {
        list.addLast(null);
      }
    }
    else if (list.size() > length) {
      while (list.size() > length) {
        list.removeLast();
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
  public final boolean isNullElement(int index) {
    return this.simpleJsonValue.getList().get(index) == null;
  }
  
  /**
   * Determine if the element at the given index has a boolean value.
   *
   * @param index The index.
   * @return <code>true</code> if element has a boolean value.
   */
  @Override
  public final boolean isBooleanElement(int index) {
    return this.simpleJsonValue.getList().get(index) instanceof Boolean;
  }

  /**
   * Retrieve the boolean value of the element at the given index.
   *
   * @param index The index.
   * @return The boolean value.
   * @see #setBooleanElement(int, Boolean)
   */
  @Override
  public final Boolean getBooleanElement(int index) {
    return (Boolean)this.simpleJsonValue.getList().get(index);
  }
  
  /**
   * Set the element at the given index to the given boolean value.
   *
   * @param index The index.
   * @param value The boolean value.
   * @see #getBooleanElement(int)
   */
  @Override
  public final void setBooleanElement(int index, Boolean value) {
    set(this.simpleJsonValue.getList(), index, value);
  }
  
  /**
   * Insert the given boolean value at the given index.
   *
   * @param index The index.
   * @param value The boolean value.
   */
  @Override
  public final void insertBooleanElement(int index, Boolean value) {
    this.simpleJsonValue.getList().add(index, value);
  }
  
  /**
   * Determine if the element at the given index has a numeric value.
   *
   * @param index The index.
   * @return <code>true</code> if element has a numeric value.
   */
  @Override
  public final boolean isNumberElement(int index) {
    return this.simpleJsonValue.getList().get(index) instanceof Number;
  }

  /**
   * Retrieve the numeric value of the element at the given index.
   *
   * @param index The index.
   * @return The numeric value.
   * @see #setNumberElement(int, Number)
   */
  @Override
  public final Number getNumberElement(int index) {
    return (Number)this.simpleJsonValue.getList().get(index);
  }
  
  /**
   * Set the element at the given index to the given numeric value.
   *
   * @param index The index.
   * @param value The numeric value.
   * @see #getNumberElement(int)
   */
  @Override
  public final void setNumberElement(int index, Number value) {
    set(this.simpleJsonValue.getList(), index, value);
  }
  
  /**
   * Insert the given numeric value at the given index.
   *
   * @param index The index.
   * @param value The numeric value.
   */
  @Override
  public final void insertNumberElement(int index, Number value) {
    this.simpleJsonValue.getList().add(index, value);
  }

  /**
   * Determine if the element at the given index has a string value.
   *
   * @param index The index.
   * @return <code>true</code> if element has a string value.
   */
  @Override
  public final boolean isStringElement(int index) {
    return this.simpleJsonValue.getList().get(index) instanceof String;
  }

  /**
   * Retrieve the string value of the element at the given index.
   *
   * @param index The index.
   * @return The string value.
   * @see #setStringElement(int, String)
   */
  @Override
  public final String getStringElement(int index) {
    return (String)this.simpleJsonValue.getList().get(index);
  }
  
  /**
   * Set the element at the given index to the given string value.
   *
   * @param index The index.
   * @param value The string value.
   * @see #getStringElement(int)
   */
  @Override
  public final void setStringElement(int index, String value) {
    set(this.simpleJsonValue.getList(), index, value);
  }
  
  /**
   * Insert the given string value at the given index.
   *
   * @param index The index.
   * @param value The string value.
   */
  @Override
  public final void insertStringElement(int index, String value) {
    this.simpleJsonValue.getList().add(index, value);
  }

  /**
   * Determine if the element at the given index has an object value.
   *
   * @param index The index.
   * @return <code>true</code> if element has an object value.
   */
  @Override
  public final boolean isObjectElement(int index) {
    return this.simpleJsonValue.getList().get(index) instanceof SimpleJsonValue;
  }

  /**
   * Retrieve the object value of the element at the given index.
   *
   * @param index The index.
   * @return The object value.
   * @see #setObjectElement(int, JsonObject)
   */
  @Override
  public final JsonObject getObjectElement(int index) {
    return this.getObjectElement(index, JsonObject.class);
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
  public final <T extends JsonObject> T getObjectElement(int index, Class<T> jsonObjectClass) {
    SimpleJsonValue propertyValue = (SimpleJsonValue)this.simpleJsonValue.getList().get(index);
    
    return propertyValue != null ? JsonObjectFactory.get().createJsonObject(jsonObjectClass, propertyValue) : null;
  }
  
  /**
   * Set the element at the given index to the given object value.
   *
   * @param index The index.
   * @param value The object value.
   * @see #getObjectElement(int)
   */
  @Override
  public final void setObjectElement(int index, JsonObject value) {
    SimpleJsonValue simpleValue = value != null ? (SimpleJsonValue)value.getObject() : null;
    
    set(this.simpleJsonValue.getList(), index, simpleValue);
  }
  
  /**
   * Insert the given object value at the given index.
   *
   * @param index The index.
   * @param value The object value.
   */
  @Override
  public final void insertObjectElement(int index, JsonObject value) {
    SimpleJsonValue simpleValue = value != null ? (SimpleJsonValue)value.getObject() : null;
    
    this.simpleJsonValue.getList().add(index, simpleValue);
  }
  
  /**
   * Remove the given number of elements at the given index.
   *
   * @param index The index.
   * @param count The number of elements.
   */
  @Override
  public final void removeElements(int index, int count) {
    this.simpleJsonValue.getList().subList(index, index + count).clear();
  }
}
