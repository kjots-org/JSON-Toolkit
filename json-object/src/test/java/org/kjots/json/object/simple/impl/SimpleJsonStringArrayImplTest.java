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

import com.google.inject.Guice;

import org.junit.Before;

import org.kjots.json.object.shared.JsonStringArray;
import org.kjots.json.object.shared.impl.JsonStringArrayImplTestBase;
import org.kjots.json.object.simple.SimpleJsonObjectModule;
import org.kjots.json.object.simple.SimpleJsonValue;

/**
 * Simple JSON String Array Implementation Test.
 * <p>
 * Created: 9th March 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class SimpleJsonStringArrayImplTest extends JsonStringArrayImplTestBase<SimpleJsonValue> {
  /**
   * Set up the JSON object implementation test.
   */
  @Before
  public void setUp() {
    Guice.createInjector(new SimpleJsonObjectModule());
  }
  
  /**
   * Create a JSON string array with the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The JSON string array.
   */
  @Override
  protected JsonStringArray createJsonStringArray(SimpleJsonValue array) {
    return new SimpleJsonStringArrayImpl(array);
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
    SimpleJsonArrayImplTest.ensureSize(array.getList(), elementIndex + 1).set(elementIndex, elementValue);
  }
}
