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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.simple.SimpleJsonObjectModule;

import com.google.inject.Guice;

/**
 * Native JSON Object Array Test.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonObjectArrayTest {
  /**
   * Cast JSON Object.
   */
  public static interface CastJsonObject extends JsonObject {
  }
  
  /** The test native JSON object array. */
  private NativeJsonObjectArray<JsonObject> testNativeJsonObjectArray;
  
  /**
   * Setup the test class.
   */
  @BeforeClass
  public static void setupClass() {
    Guice.createInjector(new SimpleJsonObjectModule());
  }
  
  /**
   * Setup the test environment.
   */
  @Before
  public void setup() {
    this.testNativeJsonObjectArray = new NativeJsonObjectArray<JsonObject>(JsonObject.class);
  }
  
  /**
   * Test the casting of a JSON object array.
   * <p>
   * This test asserts that the native JSON object array correctly casts the
   * element of the array to the class provided to the cast element method.
   */
  @Test
  public void testCastElement() {
    NativeJsonObjectArray<CastJsonObject> testCastJsonObjectArray = testNativeJsonObjectArray.castElement(CastJsonObject.class);
    
    testNativeJsonObjectArray.set(0, JsonObjectFactory.get().createJsonObject());
    
    assertSame("testNativeJsonObjectArray.list !=== testCastJsonObjectArray.list", testNativeJsonObjectArray.list, testCastJsonObjectArray.list);
    assertTrue("testCastJsonObjectArray[0] !instanceof " + CastJsonObject.class.getName(), testCastJsonObjectArray.get(0) instanceof CastJsonObject);
  }
  
  /**
   * Test the retrieval of an element of the array.
   * <p>
   * This test asserts that the native JSON object array correctly retrieves
   * elements of the array.
   */
  @Test
  public void testGet() {
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    for (int i = 0; i < 5; i++) {
      testNativeJsonObjectArray.list.add(jsonObjects[i]);
    }
    
    assertEquals("testJsonObjectArray.length", 5, testNativeJsonObjectArray.getLength());
    for (int i = 0; i < 5; i++) {
      assertEquals("testJsonObjectArray[" + i + "]", jsonObjects[i], testNativeJsonObjectArray.get(i));
    }
  }
  
  /**
   * Test the setting of an element of the array.
   * <p>
   * This test asserts that the native JSON object array correctly sets
   * elements of the array.
   */
  @Test
  public void testSet() {
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    for (int i = 0; i < 5; i++) {
      testNativeJsonObjectArray.set(i, jsonObjects[i]);
    }
    
    assertEquals("array.length", 5, testNativeJsonObjectArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", jsonObjects[i], (JsonObject)testNativeJsonObjectArray.list.get(i));
    }
  }
  
  /**
   * Test the insertion of an element of the array.
   * <p>
   * This test asserts that the native JSON object array correctly inserts
   * elements into the array.
   */
  @Test
  public void testInsert() {
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 1) {
        testNativeJsonObjectArray.set(j++, jsonObjects[i]);
      }
    }
    
    testNativeJsonObjectArray.insert(1, jsonObjects[1]);
    
    assertEquals("array.length", 5, testNativeJsonObjectArray.list.size());
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", jsonObjects[i], (JsonObject)testNativeJsonObjectArray.list.get(i));
    }
  }
  
  /**
   * Create an array of JSON objects.
   *
   * @param n The number of JSON objects.
   * @return The JSON objects.
   */
  private JsonObject[] createJsonObjects(int n) {
    JsonObject[] jsonObjects = new JsonObject[n];
    
    for (int i = 0; i < n; i++) {
      jsonObjects[i] = JsonObjectFactory.get().createJsonObject();
    }
    
    return jsonObjects;
  }
}
