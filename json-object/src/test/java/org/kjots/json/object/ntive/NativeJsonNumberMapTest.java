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
 * Native JSON Number Map Test.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class NativeJsonNumberMapTest {
  /** The test native JSON number map. */
  private NativeJsonNumberMap testNativeJsonNumberMap;
  
  /**
   * Setup the test environment.
   */
  @Before
  public void setup() {
    this.testNativeJsonNumberMap = new NativeJsonNumberMap();
  }
  
  /**
   * Test the retrieval of an element of the map.
   * <p>
   * This test asserts that the native JSON number map correctly retrieves
   * elements of the map.
   */
  @Test
  public void testGet() {
    for (int i = 0; i < 5; i++) {
      testNativeJsonNumberMap.map.put(Integer.toString(i), Double.valueOf(i));
    }
    
    for (int i = 0; i < 5; i++) {
      assertEquals("testJsonNumberMap[\"" + Integer.toString(i) + "\"]", i, testNativeJsonNumberMap.get(Integer.toString(i)).doubleValue(), 0.1);
    }
  }
  
  /**
   * Test the setting of an element of the map.
   * <p>
   * This test asserts that the native JSON number map correctly sets
   * elements of the map.
   */
  @Test
  public void testSet() {
    for (int i = 0; i < 5; i++) {
      testNativeJsonNumberMap.set(Integer.toString(i), Double.valueOf(i));
    }
    
    for (int i = 0; i < 5; i++) {
      assertEquals("object[\"" + Integer.toString(i) + "\"]", i, ((Number)testNativeJsonNumberMap.map.get(Integer.toString(i))).doubleValue(), 0.1);
    }
  }
}
