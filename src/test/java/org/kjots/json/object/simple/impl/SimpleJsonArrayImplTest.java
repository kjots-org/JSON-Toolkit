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

import java.util.List;

import org.junit.Before;
import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.impl.JsonArrayImplTestBase;
import org.kjots.json.object.simple.SimpleJsonObjectModule;
import org.kjots.json.object.simple.SimpleJsonValue;

import com.google.inject.Guice;

/**
 * Simple JSON Array Implementation Test.
 * <p>
 * Created: 4th March 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class SimpleJsonArrayImplTest extends JsonArrayImplTestBase<SimpleJsonValue> {
  /**
   * Ensure the given list has the given size.
   * <p>
   * If the size of the given list is less then the given size, this method
   * will append <code>null</code> values to the list until it is the given
   * size.
   * <p>
   * For convenience, this method will return the given list.
   *
   * @param list The list.
   * @param size The size.
   * @return The list.
   */
  protected static List<Object> ensureSize(List<Object> list, int size) {
    while (list.size() < size) {
      list.add(null);
    }
    
    return list;
  }

  /**
   * Set up the JSON object implementation test.
   */
  @Before
  public void setUp() {
    Guice.createInjector(new SimpleJsonObjectModule());
  }
  
  /**
   * Create a JSON object with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  @Override
  protected JsonObject createJsonObject(SimpleJsonValue object) {
    return new SimpleJsonObjectImpl(object);
  }

  /**
   * Create a JSON array with the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The JSON array.
   */
  @Override
  protected JsonArray createJsonArray(SimpleJsonValue array) {
    return new SimpleJsonArrayImpl(array);
 }

  /**
   * Create an empty underlying JSON object.
   *
   * @return The empty underlying JSON object.
   */
  @Override
  protected SimpleJsonValue createUnderlyingJsonObject() {
    return SimpleJsonValue.createObject();
  }
  
  /**
   * Create an empty underlying JSON array.
   *
   * @return The empty underlying JSON array.
   */
  @Override
  protected SimpleJsonValue createUnderlyingJsonArray() {
    return SimpleJsonValue.createArray();
  }
  
  /**
   * Retrieve the length of the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The length of the underlying JSON array.
   * @see #setArrayLength(SimpleJsonValue, int)
   */
  @Override
  protected int getArrayLength(SimpleJsonValue array) {
    return array.getList().size();
  }
  
  /**
   * Set the length of the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param length The length of the underlying JSON array.
   * @see #getArrayLength(SimpleJsonValue)
   */
  @Override
  protected void setArrayLength(SimpleJsonValue array, int length) {
    ensureSize(array.getList(), length);
  }
  
  /**
   * Retrieve the boolean value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The boolean value of the element.
   * @see #setBooleanElement(SimpleJsonValue, int, boolean)
   */
  @Override
  protected Boolean getBooleanElement(SimpleJsonValue array, int elementIndex) {
    return (Boolean)array.getList().get(elementIndex);
  }
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given boolean value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The boolean value of the element.
   * @see #getBooleanElement(SimpleJsonValue, int)
   */
  @Override
  protected void setBooleanElement(SimpleJsonValue array, int elementIndex, Boolean elementValue) {
    ensureSize(array.getList(), elementIndex + 1).set(elementIndex, elementValue);
  }
  
  /**
   * Retrieve the numeric value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The numeric value of the element.
   * @see #setNumberElement(SimpleJsonValue, int, Number)
   */
  @Override
  protected Number getNumberElement(SimpleJsonValue array, int elementIndex) {
    return (Number)array.getList().get(elementIndex);
  }
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given numeric value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The numeric value of the element.
   * @see #getNumberElement(SimpleJsonValue, int)
   */
  @Override
  protected void setNumberElement(SimpleJsonValue array, int elementIndex, Number elementValue) {
    ensureSize(array.getList(), elementIndex + 1).set(elementIndex, elementValue);
  }
  
  /**
   * Retrieve the string value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The string value of the element.
   * @see #setStringElement(SimpleJsonValue, int, String)
   */
  @Override
  protected String getStringElement(SimpleJsonValue array, int elementIndex) {
    return (String)array.getList().get(elementIndex);
  }
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given string value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The string value of the element.
   * @see #getStringElement(SimpleJsonValue, int)
   */
  @Override
  protected void setStringElement(SimpleJsonValue array, int elementIndex, String elementValue) {
    ensureSize(array.getList(), elementIndex + 1).set(elementIndex, elementValue);
  }
  
  /**
   * Retrieve the object value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The object value of the element.
   * @see #setObjectElement(SimpleJsonValue, int, SimpleJsonValue)
   */
  @Override
  protected SimpleJsonValue getObjectElement(SimpleJsonValue array, int elementIndex) {
    return (SimpleJsonValue)array.getList().get(elementIndex);
  }
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given object value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The object value of the element.
   * @see #getObjectElement(SimpleJsonValue, int)
   */
  @Override
  protected void setObjectElement(SimpleJsonValue array, int elementIndex, SimpleJsonValue elementValue) {
    ensureSize(array.getList(), elementIndex + 1).set(elementIndex, elementValue);
  }
}
