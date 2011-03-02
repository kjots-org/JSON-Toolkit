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

import org.kjots.json.object.shared.JsonNumberArray;

/**
 * JSON Boolean Array Implementation TestBase.
 * <p>
 * Created: 11th December 2009.
 *
 * @param <J> The type of the underlying JSON object.
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public abstract class JsonNumberArrayImplTestBase<J> {
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
      this.setNumberElement(array, i, i);
    }
    
    JsonNumberArray testJsonNumberArray = this.createJsonNumberArray(array);
    
    assertEquals("testJsonNumberArray.length", 5, testJsonNumberArray.getLength());
    for (int i = 0; i < 5; i++) {
      assertEquals("testJsonNumberArray[" + i + "]", i, testJsonNumberArray.get(i).doubleValue(), 0.1);
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
    
    JsonNumberArray testJsonNumberArray = this.createJsonNumberArray(array);
    
    for (int i = 0; i < 5; i++) {
      testJsonNumberArray.set(i, i);
    }
    
    assertEquals("array.length", 5, this.getArrayLength(array));
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i, this.getNumberElement(array, i).doubleValue(), 0.1);
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
    
    JsonNumberArray testJsonNumberArray = this.createJsonNumberArray(array);
    
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 1) {
        testJsonNumberArray.set(j++, i);
      }
    }
    
    testJsonNumberArray.insert(1, 1.0);
    
    assertEquals("array.length", 5, this.getArrayLength(array));
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i, this.getNumberElement(array, i).doubleValue(), 0.1);
    }
  }
  
  /**
   * Test the prepending of an element of the array.
   * <p>
   * This test asserts that prepending an element prepends an element to the
   * underlying array.
   */
  @Test
  public void testPrepend() {
    J array = this.createUnderlyingJsonArray();
    
    JsonNumberArray testJsonNumberArray = this.createJsonNumberArray(array);
    
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 0) {
        testJsonNumberArray.set(j++, i);
      }
    }
    
    testJsonNumberArray.prepend(0.0);
    
    assertEquals("array.length", 5, this.getArrayLength(array));
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i, this.getNumberElement(array, i).doubleValue(), 0.1);
    }
  }
  
  /**
   * Test the appending of an element of the array.
   * <p>
   * This test asserts that appending an element appends an element to the
   * underlying array.
   */
  @Test
  public void testAppend() {
    J array = this.createUnderlyingJsonArray();
    
    JsonNumberArray testJsonNumberArray = this.createJsonNumberArray(array);
    
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 4) {
        testJsonNumberArray.set(j++, i);
      }
    }
    
    testJsonNumberArray.append(4.0);
    
    assertEquals("array.length", 5, this.getArrayLength(array));
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i, this.getNumberElement(array, i).doubleValue(), 0.1);
    }
  }
  
  /**
   * Create a JSON number array with the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The JSON number array.
   */
  protected abstract JsonNumberArray createJsonNumberArray(J array);
  
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
   * Retrieve the numeric value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The numeric value of the element.
   * @see #setNumberElement(Object, int, Number)
   */
  protected abstract Number getNumberElement(J array, int elementIndex);
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given numeric value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The numeric value of the element.
   * @see #getNumberElement(Object, int)
   */
  protected abstract void setNumberElement(J array, int elementIndex, Number elementValue);
}
