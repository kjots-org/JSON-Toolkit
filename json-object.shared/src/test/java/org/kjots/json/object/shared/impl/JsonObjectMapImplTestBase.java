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
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectMap;

/**
 * JSON Object Map Implementation Test Base.
 * <p>
 * Created: 11th December 2009.
 *
 * @param <J> The type of the underlying JSON object.
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public abstract class JsonObjectMapImplTestBase<J> {
  /**
   * Cast JSON Object.
   * <p>
   * Created: 8th December 2009.
   */
  public static interface CastJsonObject extends JsonObject {
  }
  
  /**
   * Test the casting of a JSON object array.
   * <p>
   * This test asserts that a the class of a elements of the cast JSON object
   * matches the class provided to the cast element method.
   */
  @Test
  public void testCastElement() {
    JsonObjectMap<JsonObject> testJsonObjectMap = this.createJsonObjectMap(this.createUnderlyingJsonObject());
    JsonObjectMap<CastJsonObject> testCastJsonObjectMap = testJsonObjectMap.castElement(CastJsonObject.class);
    
    assertEquals(testJsonObjectMap, testCastJsonObjectMap);
    
    testJsonObjectMap.set("key", this.createJsonObject(this.createUnderlyingJsonObject()));
    
    assertTrue("testCastJsonObjectMap[\"key\"] !instanceof " + CastJsonObject.class.getName(), testCastJsonObjectMap.get("key") instanceof CastJsonObject);
  }
  
  /**
   * Test the retrieval of an element of the map.
   * <p>
   * This test asserts that the retrieved elements are the same as the
   * properties with the corresponding key of the underlying object.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testGet() {
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    J object = this.createUnderlyingJsonObject();
    
    for (int i = 0; i < 5; i++) {
      this.setObjectProperty(object, Integer.toString(i), (J)jsonObjects[i].getObject());
    }
    
    JsonObjectMap<JsonObject> testJsonObjectMap = this.createJsonObjectMap(object);
    
    for (int i = 0; i < 5; i++) {
      assertEquals("testJsonObjectMap[\"" + Integer.toString(i) + "\"]", jsonObjects[i], testJsonObjectMap.get(Integer.toString(i)));
    }
  }
  
  /**
   * Test the setting of an element of the map.
   * <p>
   * This test asserts that setting the elements changes the properties with
   * the corresponding key of the underlying object.
   */
  @Test
  public void testSet() {
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    J object = this.createUnderlyingJsonObject();
    
    JsonObjectMap<JsonObject> testJsonObjectMap = this.createJsonObjectMap(object);
    
    for (int i = 0; i < 5; i++) {
      testJsonObjectMap.set(Integer.toString(i), jsonObjects[i]);
    }
    
    for (int i = 0; i < 5; i++) {
      assertEquals("object[\"" + Integer.toString(i) + "\"]", jsonObjects[i].getObject(), this.getObjectProperty(object, Integer.toString(i)));
    }
  }
  
  /**
   * Create a JSON object with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  protected abstract JsonObject createJsonObject(J object);
  
  /**
   * Create a JSON object map with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON object map.
   */
  protected abstract JsonObjectMap<JsonObject> createJsonObjectMap(J object);
  
  /**
   * Create an empty underlying JSON object.
   *
   * @return The empty underlying JSON object.
   */
  protected abstract J createUnderlyingJsonObject();
  
  /**
   * Retrieve the object value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The object value of the property.
   * @see #setObjectProperty(Object, String, Object)
   */
  protected abstract J getObjectProperty(J object, String propertyName);
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given object value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The object value of the property.
   * @see #getObjectProperty(Object, String)
   */
  protected abstract void setObjectProperty(J object, String propertyName, J propertyValue);
  
  /**
   * Create an array of JSON objects.
   *
   * @param n The number of JSON objects.
   * @return The JSON objects.
   */
  private JsonObject[] createJsonObjects(int n) {
    JsonObject[] jsonObjects = new JsonObject[n];
    
    for (int i = 0; i < n; i++) {
      jsonObjects[i] = this.createJsonObject(this.createUnderlyingJsonObject());
    }
    
    return jsonObjects;
  }
}
