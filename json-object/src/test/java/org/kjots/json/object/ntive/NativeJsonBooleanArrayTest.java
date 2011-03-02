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
 * Native JSON Boolean Array Test.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class NativeJsonBooleanArrayTest {
  /** The test native JSON boolean array. */
  private NativeJsonBooleanArray testNativeJsonBooleanArray;
  
  /**
   * Setup the test environment.
   */
  @Before
  public void setup() {
    this.testNativeJsonBooleanArray = new NativeJsonBooleanArray();
  }
  
  /**
   * Test the retrieval of an element of the array.
   * <p>
   * This test asserts that the native JSON boolean array correctly retrieves
   * elements of the array.
   */
  @Test
  public void testGet() {
    for (int i = 0; i < 5; i++) {
      testNativeJsonBooleanArray.list.add(Boolean.valueOf(i % 2 == 0));
    }
    
    assertEquals("testJsonBooleanArray.length", 5, testNativeJsonBooleanArray.getLength());
    for (int i = 0; i < 5; i++) {
      assertEquals("testJsonBooleanArray[" + i + "]", i % 2 == 0, testNativeJsonBooleanArray.get(i).booleanValue());
    }
  }
  
  /**
   * Test the setting of an element of the array.
   * <p>
   * This test asserts that the native JSON boolean array correctly sets
   * elements of the array.
   */
  @Test
  public void testSet() {
    for (int i = 0; i < 5; i++) {
      testNativeJsonBooleanArray.set(i, i % 2 == 0);
    }
    
    assertEquals("array.length", 5, testNativeJsonBooleanArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i % 2 == 0, ((Boolean)testNativeJsonBooleanArray.list.get(i)).booleanValue());
    }
  }
  
  /**
   * Test the insertion of an element of the array.
   * <p>
   * This test asserts that the native JSON boolean array correctly inserts
   * elements into the array.
   */
  @Test
  public void testInsert() {
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 1) {
        testNativeJsonBooleanArray.set(j++, i % 2 == 0);
      }
    }
    
    testNativeJsonBooleanArray.insert(1, false);
    
    assertEquals("array.length", 5, testNativeJsonBooleanArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i % 2 == 0, ((Boolean)testNativeJsonBooleanArray.list.get(i)).booleanValue());
    }
  }
  
  /**
   * Test the prepending of an element of the array.
   * <p>
   * This test asserts that the native JSON boolean array correctly prepends
   * elements to the array.
   */
  @Test
  public void testPrepend() {
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 0) {
        testNativeJsonBooleanArray.set(j++, i % 2 == 0);
      }
    }
    
    testNativeJsonBooleanArray.prepend(true);
    
    assertEquals("array.length", 5, testNativeJsonBooleanArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i % 2 == 0, ((Boolean)testNativeJsonBooleanArray.list.get(i)).booleanValue());
    }
  }
  
  /**
   * Test the appending of an element of the array.
   * <p>
   * This test asserts that the native JSON boolean array correctly appends
   * elements to the array.
   */
  @Test
  public void testAppend() {
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 4) {
        testNativeJsonBooleanArray.set(j++, i % 2 == 0);
      }
    }
    
    testNativeJsonBooleanArray.append(true);
    
    assertEquals("array.length", 5, testNativeJsonBooleanArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", i % 2 == 0, ((Boolean)testNativeJsonBooleanArray.list.get(i)).booleanValue());
    }
  }
}
