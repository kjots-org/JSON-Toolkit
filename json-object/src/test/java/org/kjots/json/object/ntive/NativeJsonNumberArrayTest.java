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

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Native JSON Number Array Test.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class NativeJsonNumberArrayTest {
  /** The test native JSON number array. */
  private NativeJsonNumberArray testNativeJsonNumberArray;
  
  /**
   * Setup the test environment.
   */
  @Before
  public void setup() {
    this.testNativeJsonNumberArray = new NativeJsonNumberArray();
  }
  
  /**
   * Test the retrieval of an element of the array.
   * <p>
   * This test asserts that the native JSON number array correctly retrieves
   * elements of the array.
   */
  @Test
  public void testGet() {
    for (int i = 0; i < 5; i++) {
      testNativeJsonNumberArray.list.add(Double.valueOf(i));
    }
    
    assertEquals("testJsonNumberArray.length", 5, testNativeJsonNumberArray.getLength());
    for (int i = 0; i < 5; i++) {
      assertEquals("testJsonNumberArray[" + i + "]", i, testNativeJsonNumberArray.get(i).doubleValue(), 0.1);
    }
  }
  
  /**
   * Test the setting of an element of the array.
   * <p>
   * This test asserts that the native JSON number array correctly sets
   * elements of the array.
   */
  @Test
  public void testSet() {
    for (int i = 0; i < 5; i++) {
      testNativeJsonNumberArray.set(i, Double.valueOf(i));
    }
    
    assertEquals("array.length", 5, testNativeJsonNumberArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i, ((Number)testNativeJsonNumberArray.list.get(i)).doubleValue(), 0.1);
    }
  }
  
  /**
   * Test the insertion of an element of the array.
   * <p>
   * This test asserts that the native JSON number array correctly inserts
   * elements into the array.
   */
  @Test
  public void testInsert() {
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 1) {
        testNativeJsonNumberArray.set(j++, Double.valueOf(i));
      }
    }
    
    testNativeJsonNumberArray.insert(1, 1.0);
    
    assertEquals("array.length", 5, testNativeJsonNumberArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i, ((Number)testNativeJsonNumberArray.list.get(i)).doubleValue(), 0.1);
    }
  }
  
  /**
   * Test the prepending of an element of the array.
   * <p>
   * This test asserts that the native JSON number array correctly prepends
   * elements to the array.
   */
  @Test
  public void testPrepend() {
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 0) {
        testNativeJsonNumberArray.set(j++, Double.valueOf(i));
      }
    }
    
    testNativeJsonNumberArray.prepend(0.0);
    
    assertEquals("array.length", 5, testNativeJsonNumberArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i, ((Number)testNativeJsonNumberArray.list.get(i)).doubleValue(), 0.1);
    }
  }
  
  /**
   * Test the appending of an element of the array.
   * <p>
   * This test asserts that the native JSON number array correctly appends
   * elements to the array.
   */
  @Test
  public void testAppend() {
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 4) {
        testNativeJsonNumberArray.set(j++, Double.valueOf(i));
      }
    }
    
    testNativeJsonNumberArray.append(4.0);
    
    assertEquals("array.length", 5, testNativeJsonNumberArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i, ((Number)testNativeJsonNumberArray.list.get(i)).doubleValue(), 0.1);
    }
  }
}
