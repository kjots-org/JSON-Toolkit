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
 * Native JSON String Array Test.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonStringArrayTest {
  /** The test native JSON string array. */
  private NativeJsonStringArray testNativeJsonStringArray;
  
  /**
   * Setup the test environment.
   */
  @Before
  public void setup() {
    this.testNativeJsonStringArray = new NativeJsonStringArray();
  }
  
  /**
   * Test the retrieval of an element of the array.
   * <p>
   * This test asserts that the native JSON string array correctly retrieves
   * elements of the array.
   */
  @Test
  public void testGet() {
    for (int i = 0; i < 5; i++) {
      testNativeJsonStringArray.list.add(Integer.toString(i));
    }
    
    assertEquals("testJsonStringArray.length", 5, testNativeJsonStringArray.getLength());
    for (int i = 0; i < 5; i++) {
      assertEquals("testJsonStringArray[" + i + "]", Integer.toString(i), testNativeJsonStringArray.get(i));
    }
  }
  
  /**
   * Test the setting of an element of the array.
   * <p>
   * This test asserts that the native JSON string array correctly sets
   * elements of the array.
   */
  @Test
  public void testSet() {
    for (int i = 0; i < 5; i++) {
      testNativeJsonStringArray.set(i, Integer.toString(i));
    }
    
    assertEquals("array.length", 5, testNativeJsonStringArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", Integer.toString(i), (String)testNativeJsonStringArray.list.get(i));
    }
  }
  
  /**
   * Test the insertion of an element of the array.
   * <p>
   * This test asserts that the native JSON string array correctly inserts
   * elements into the array.
   */
  @Test
  public void testInsert() {
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 1) {
        testNativeJsonStringArray.set(j++, Integer.toString(i));
      }
    }
    
    testNativeJsonStringArray.insert(1, "1");
    
    assertEquals("array.length", 5, testNativeJsonStringArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", Integer.toString(i), (String)testNativeJsonStringArray.list.get(i));
    }
  }
}
