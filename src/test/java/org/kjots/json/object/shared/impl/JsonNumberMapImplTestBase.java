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

import org.kjots.json.object.shared.JsonNumberMap;

/**
 * JSON Number Map Implementation Test Base
 * <p>
 * Created: 11th December 2009.
 *
 * @param <J> The type of the underlying JSON object.
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public abstract class JsonNumberMapImplTestBase<J> {
  /**
   * Test the retrieval of an element of the map.
   * <p>
   * This test asserts that the retrieved elements are the same as the
   * properties with the corresponding key of the underlying object.
   */
  @Test
  public void testGet() {
    J object = this.createUnderlyingJsonObject();
    
    for (int i = 0; i < 5; i++) {
      this.setNumberProperty(object, Integer.toString(i), i);
    }
    
    JsonNumberMap testJsonNumberMap = this.createJsonNumberMap(object);
    
    for (int i = 0; i < 5; i++) {
      assertEquals("testJsonNumberMap[\"" + Integer.toString(i) + "\"]", i, testJsonNumberMap.get(Integer.toString(i)), 0.1);
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
    J object = this.createUnderlyingJsonObject();
    
    JsonNumberMap testJsonNumberMap = this.createJsonNumberMap(object);
    
    for (int i = 0; i < 5; i++) {
      testJsonNumberMap.set(Integer.toString(i), i);
    }
    
    for (int i = 0; i < 5; i++) {
      assertEquals("object[\"" + Integer.toString(i) + "\"]", i, this.getNumberProperty(object, Integer.toString(i)), 0.1);
    }
  }
  
  /**
   * Create a JSON number map with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON number map.
   */
  protected abstract JsonNumberMap createJsonNumberMap(J object);

  /**
   * Create an empty underlying JSON object.
   *
   * @return The empty underlying JSON object.
   */
  protected abstract J createUnderlyingJsonObject();
  
  /**
   * Retrieve the number value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The number value of the property.
   * @see #setNumberProperty(Object, String, double)
   */
  protected abstract double getNumberProperty(J object, String propertyName);
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given number value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The number value of the property.
   * @see #getNumberProperty(Object, String)
   */
  protected abstract void setNumberProperty(J object, String propertyName, double propertyValue);
  
}
