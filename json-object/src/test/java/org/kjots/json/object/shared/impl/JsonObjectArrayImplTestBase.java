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
import org.kjots.json.object.shared.JsonObjectArray;

/**
 * JSON Object Array Implementation Test Base.
 * <p>
 * Created: 11th December 2009.
 *
 * @param <J> The type of the underlying JSON object.
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public abstract class JsonObjectArrayImplTestBase<J> {
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
    JsonObjectArray<JsonObject> testJsonObjectArray = this.createJsonObjectArray(this.createUnderlyingJsonArray());
    JsonObjectArray<CastJsonObject> testCastJsonObjectArray = testJsonObjectArray.castElement(CastJsonObject.class);
    
    assertEquals(testJsonObjectArray, testCastJsonObjectArray);
    
    testJsonObjectArray.set(0, this.createJsonObject(this.createUnderlyingJsonObject()));
    
    assertTrue("testCastJsonObjectArray[0] !instanceof " + CastJsonObject.class.getName(), testCastJsonObjectArray.get(0) instanceof CastJsonObject);
  }
  
  /**
   * Test the retrieval of an element of the array.
   * <p>
   * This test asserts that the retrieved elements are the same as the elements
   * at the corresponding index of the underlying array.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testGet() {
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    J array = this.createUnderlyingJsonArray();
    
    for (int i = 0; i < 5; i++) {
      this.setObjectElement(array, i, (J)jsonObjects[i].getObject());
    }
    
    JsonObjectArray<JsonObject> testJsonObjectArray = this.createJsonObjectArray(array);
    
    assertEquals("testJsonBooleanArray.length", 5, testJsonObjectArray.getLength());
    for (int i = 0; i < 5; i++) {
      assertEquals("testJsonBooleanArray[" + i + "]", jsonObjects[i], testJsonObjectArray.get(i));
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
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    J array = this.createUnderlyingJsonArray();
    
    JsonObjectArray<JsonObject> testJsonObjectArray = this.createJsonObjectArray(array);
    
    for (int i = 0; i < 5; i++) {
      testJsonObjectArray.set(i, jsonObjects[i]);
    }
    
    assertEquals("array.length", 5, this.getArrayLength(array));
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", jsonObjects[i].getObject(), this.getObjectElement(array, i));
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
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    J array = this.createUnderlyingJsonArray();
    
    JsonObjectArray<JsonObject> testJsonObjectArray = this.createJsonObjectArray(array);
    
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 1) {
        testJsonObjectArray.set(j++, jsonObjects[i]);
      }
    }
    
    testJsonObjectArray.insert(1, jsonObjects[1]);
    
    assertEquals("array.length", 5, this.getArrayLength(array));
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", jsonObjects[i].getObject(), this.getObjectElement(array, i));
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
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    J array = this.createUnderlyingJsonArray();
    
    JsonObjectArray<JsonObject> testJsonObjectArray = this.createJsonObjectArray(array);
    
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 0) {
        testJsonObjectArray.set(j++, jsonObjects[i]);
      }
    }
    
    testJsonObjectArray.prepend(jsonObjects[0]);
    
    assertEquals("array.length", 5, this.getArrayLength(array));
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", jsonObjects[i].getObject(), this.getObjectElement(array, i));
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
    JsonObject[] jsonObjects = this.createJsonObjects(5);
    
    J array = this.createUnderlyingJsonArray();
    
    JsonObjectArray<JsonObject> testJsonObjectArray = this.createJsonObjectArray(array);
    
    for (int i = 0, j = 0; i < 5; i++) {
      if (i != 4) {
        testJsonObjectArray.set(j++, jsonObjects[i]);
      }
    }
    
    testJsonObjectArray.append(jsonObjects[4]);
    
    assertEquals("array.length", 5, this.getArrayLength(array));
    for (int i = 0; i < 5; i++) {
      assertEquals("array[" + i + "]", jsonObjects[i].getObject(), this.getObjectElement(array, i));
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
   * Create a JSON object array with the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The JSON object array.
   */
  protected abstract JsonObjectArray<JsonObject> createJsonObjectArray(J array);

  /**
   * Create an empty underlying JSON object.
   *
   * @return The empty underlying JSON object.
   */
  protected abstract J createUnderlyingJsonObject();
  
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
   * Retrieve the object value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The object value of the element.
   * @see #setObjectElement(Object, int, Object)
   */
  protected abstract J getObjectElement(J array, int elementIndex);
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given object value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The object value of the element.
   * @see #getObjectElement(Object, int)
   */
  protected abstract void setObjectElement(J array, int elementIndex, J elementValue);
  
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
