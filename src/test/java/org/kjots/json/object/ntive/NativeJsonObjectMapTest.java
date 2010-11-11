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
 * Native JSON Object Map Test.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonObjectMapTest {
  /**
   * Cast JSON Object.
   */
  public static interface CastJsonObject extends JsonObject {
  }
  
  /** The test native JSON object map. */
  private NativeJsonObjectMap<JsonObject> testNativeJsonObjectMap;
  
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
    this.testNativeJsonObjectMap = new NativeJsonObjectMap<JsonObject>(JsonObject.class);
  }
  
  /**
   * Test the casting of a JSON object map.
   * <p>
   * This test asserts that the native JSON object map correctly casts the
   * element of the map to the class provided to the cast element method.
   */
  @Test
  public void testCastElement() {
    NativeJsonObjectMap<CastJsonObject> testCastJsonObjectMap = testNativeJsonObjectMap.castElement(CastJsonObject.class);
    
    testNativeJsonObjectMap.set("key", JsonObjectFactory.get().createJsonObject());
    
    assertSame("testNativeJsonObjectMap.map !== testCastJsonObjectMap.map", testNativeJsonObjectMap.map, testCastJsonObjectMap.map);
    assertTrue("testCastJsonObjectMap[0] !instanceof " + CastJsonObject.class.getName(), testCastJsonObjectMap.get("key") instanceof CastJsonObject);
  }
  
  /**
   * Test the retrieval of an element of the map.
   * <p>
   * This test asserts that the native JSON object map correctly retrieves
   * elements of the map.
   */
  @Test
  public void testGet() {
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    for (int i = 0; i < 5; i++) {
      testNativeJsonObjectMap.map.put(Integer.toString(i), jsonObjects[i]);
    }
    
    for (int i = 0; i < 5; i++) {
      assertEquals("testJsonObjectMap[\"" + Integer.toString(i) + "\"]", jsonObjects[i], testNativeJsonObjectMap.get(Integer.toString(i)));
    }
  }
  
  /**
   * Test the setting of an element of the map.
   * <p>
   * This test asserts that the native JSON object map correctly sets
   * elements of the map.
   */
  @Test
  public void testSet() {
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    for (int i = 0; i < 5; i++) {
      testNativeJsonObjectMap.set(Integer.toString(i), jsonObjects[i]);
    }
    
    for (int i = 0; i < 5; i++) {
      assertEquals("object[\"" + Integer.toString(i) + "\"]", jsonObjects[i], ((JsonObject)testNativeJsonObjectMap.map.get(Integer.toString(i))));
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
