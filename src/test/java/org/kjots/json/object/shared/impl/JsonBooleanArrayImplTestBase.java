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
package org.kjots.json.object.shared.impl;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import org.kjots.json.object.shared.JsonBooleanArray;

/**
 * JSON Boolean Array Implementation Test Base.
 * <p>
 * Created: 11th December 2009.
 *
 * @param <J> The type of the underlying JSON object.
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public abstract class JsonBooleanArrayImplTestBase<J> {
  /**
   * Test the retrieval of an element of the array.
   * <p>
   * This test asserts that the retrieved elements are the same as the elements
   * at the corresponding index of the underlying array.
   */
  @Test
  public void testGet() {
    J array = this.createUnderlyingJsonArray();
   
    for (int i = 0; i < 5; i++) {
      this.setBooleanElement(array, i, i % 2 == 0);
    }
    
    JsonBooleanArray testJsonBooleanArray = this.createJsonBooleanArray(array);
    
    assertEquals("testJsonBooleanArray.length", 5, testJsonBooleanArray.getLength());
    for (int i = 0; i < 5; i++) {
      assertEquals("testJsonBooleanArray[" + i + "]", i % 2 == 0, testJsonBooleanArray.get(i));
    }
  }
  
  /**
   * Test the setting of an element of the array.
   * <p>
   * This test asserts that setting the elements changes the elements at the
   * corresponding index of the underlying array.
   */
  @Test
  public void testSet() {
    J array = this.createUnderlyingJsonArray();
    
    JsonBooleanArray testJsonBooleanArray = this.createJsonBooleanArray(array);
    
    for (int i = 0; i < 5; i++) {
      testJsonBooleanArray.set(i, i % 2 == 0);
    }
    
    assertEquals("array.length", 5, this.getArrayLength(array));
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i % 2 == 0, this.getBooleanElement(array, i));
    }
  }
  
  /**
   * Test the insertion of an element of the array.
   * <p>
   * This test asserts that inserting an element inserts an element at the
   * corresponding index of the underlying array.
   */
  @Test
  public void testInsert() {
    J array = this.createUnderlyingJsonArray();
    
    JsonBooleanArray testJsonBooleanArray = this.createJsonBooleanArray(array);
    
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 1) {
        testJsonBooleanArray.set(j++, i % 2 == 0);
      }
    }
    
    testJsonBooleanArray.insert(1, false);
    
    assertEquals("array.length", 5, this.getArrayLength(array));
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i % 2 == 0, this.getBooleanElement(array, i));
    }
  }
  
  /**
   * Create a JSON boolean array with the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The JSON boolean array.
   */
  protected abstract JsonBooleanArray createJsonBooleanArray(J array);

  /**
   * Create an empty underlying JSON array.
   *
   * @return The empty underlying JSON array.
   */
  protected abstract J createUnderlyingJsonArray();
  
  /**
   * Retrieve the length of the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The length of the underlying JSON array.
   */
  protected abstract int getArrayLength(J array);
  
  /**
   * Retrieve the boolean value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The boolean value of the element.
   * @see #setBooleanElement(Object, int, boolean)
   */
  protected abstract boolean getBooleanElement(J array, int elementIndex);
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given boolean value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The boolean value of the element.
   * @see #getBooleanElement(Object, int)
   */
  protected abstract void setBooleanElement(J array, int elementIndex, boolean elementValue);
}
