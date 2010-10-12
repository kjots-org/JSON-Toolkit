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
package org.kjots.json.object.shared;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;
import org.kjots.json.object.shared.JsonProperty.OperationType;

/**
 * JSON Object Generator Test Base.
 * <p>
 * Created: 11th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public abstract class JsonObjectGeneratorTestBase {
  /**
   * Test Base JSON Object.
   */
  public interface TestBaseJsonObject extends JsonObject {
  }
  
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends TestBaseJsonObject, Cloneable {
    /**
     * Determine if the test property exists.
     *
     * @return <code>true</code> if the test property exists.
     */
    @JsonProperty(name = "testProperty", operation = OperationType.HAS)
    public boolean hasTestProperty();
    
    /**
     * Determine if the test property is <code>null</code>.
     *
     * @return <code>true</code> if the test property is <code>null</code>.
     */
    @JsonProperty(name = "testProperty", operation = OperationType.IS_NULL)
    public boolean isTestPropertyNull();
    
    /**
     * Delete the test property.
     *
     * @return <code>true</code> if the test property existed.
     */
    @JsonProperty(name = "testProperty", operation = OperationType.DELETE)
    public boolean deleteTestProperty();
  }
  
  /**
   * Test the creation of a JSON object.
   * <p>
   * This test asserts that a JSON object was created successfully when created
   * by specifying a JSON object class.
   */
  @Test
  public void testCreateJsonObjectByClass() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertNotNull(testJsonObject);
  }
  
  /**
   * Test the creation of a JSON object.
   * <p>
   * This test asserts that a JSON object was created successfully when created
   * by specifying a JSON object class name.
   */
  @Test
  public void testCreateJsonObjectByClassName() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class.getName());
    
    assertNotNull(testJsonObject);
  }
  
  /**
   * Test the retrieval of the class of a JSON object.
   * <p>
   * This test asserts that the JSON object class returned by a JSON object
   * matches the class which which the JSON object was created.
   */
  @Test
  public void testGetJsonObjectClass() {
    TestJsonObject testTestJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(TestJsonObject.class, testTestJsonObject.getJsonObjectClass());
  }

  
  /**
   * Test the determination of the existence of a property.
   * <p>
   * This test asserts that the JSON object correctly reports the existence or
   * non-existence of a property.
   */
  @Test
  public void testHasProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertFalse("testJsonObject.hasTestProperty() != false", testJsonObject.hasTestProperty());
    
    testJsonObject.setStringProperty("testProperty", null);
    
    assertTrue("testJsonObject.hasTestProperty() != true", testJsonObject.hasTestProperty());
    
    testJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertTrue("testJsonObject.hasTestProperty() != true", testJsonObject.hasTestProperty());
  }
  
  /**
   * Test the determination of the <code>null</code> value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that a property
   * exists but has a <code>null</code> value.
   */
  @Test
  public void testIsPropertyNull() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertFalse("testJsonObject.isTestPropertyNull() != false", testJsonObject.isTestPropertyNull());
    
    testJsonObject.setStringProperty("testProperty", null);
    
    assertTrue("testJsonObject.isTestPropertyNull() != true", testJsonObject.isTestPropertyNull());
    
    testJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertFalse("testJsonObject.isTestPropertyNull() != false", testJsonObject.isTestPropertyNull());
  }

  /**
   * Test the deletion of a property.
   * <p>
   * This test asserts that a property is correctly deleted from the JSON
   * object.
   */
  @Test
  public void testDeleteProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertTrue("testJsonObject.deleteTestProperty() != true", testJsonObject.deleteTestProperty());
    assertFalse("testJsonObject.hasTestProperty() != false", testJsonObject.hasTestProperty());
    
    assertFalse("testJsonObject.deleteTestProperty() != false", testJsonObject.deleteTestProperty());
    assertFalse("testJsonObject.hasTestProperty() != false", testJsonObject.hasTestProperty());
  }
}
