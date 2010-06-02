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
package org.kjots.json.object.shared;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import org.kjots.json.object.shared.JsonProperty.OperationType;

/**
 * JSON Object Generator Boolean Test Base.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public abstract class JsonObjectGeneratorBooleanTestBase {
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject {
    /**
     * Retrieve the test boolean property.
     *
     * @return The test boolean property.
     * @see #setTestBooleanProperty(Boolean)
     */
    @JsonProperty(name = "testBooleanProperty", operation = OperationType.GET)
    public Boolean getTestBooleanProperty();
    
    /**
     * Set the test boolean property.
     *
     * @param testBooleanProperty The test boolean property.
     * @see #getTestBooleanProperty()
     */
    @JsonProperty(name = "testBooleanProperty", operation = OperationType.SET)
    public void setTestBooleanProperty(Boolean testBooleanProperty);
    
    /**
     * Retrieve the test adapted boolean property.
     * 
     * @return The test adapted boolean property.
     * @see #setTestAdaptedBooleanProperty(String)
     */
    @JsonProperty(name = "testAdaptedBooleanProperty", operation = OperationType.GET, adapter = TestJsonBooleanPropertyAdapter.class)
    public String getTestAdaptedBooleanProperty();
    
    /**
     * Set the test adapted boolean property.
     * 
     * @param testAdaptedBooleanProperty The test adapted boolean property.
     * @see #getTestAdaptedBooleanProperty()
     */
    @JsonProperty(name = "testAdaptedBooleanProperty", operation = OperationType.SET, adapter = TestJsonBooleanPropertyAdapter.class)
    public void setTestAdaptedBooleanProperty(String testAdaptedBooleanProperty);
    
    /**
     * Retrieve the test boolean array property.
     *
     * @return The test boolean array property.
     * @see #setTestBooleanArrayProperty(JsonBooleanArray)
     */
    @JsonProperty(name = "testBooleanArrayProperty", operation = OperationType.GET)
    public JsonBooleanArray getTestBooleanArrayProperty();
    
    /**
     * Set the test boolean array property.
     *
     * @param testBooleanArrayProperty The test boolean array property.
     * @see #getTestBooleanArrayProperty()
     */
    @JsonProperty(name = "testBooleanArrayProperty", operation = OperationType.SET)
    public void setTestBooleanArrayProperty(JsonBooleanArray testBooleanArrayProperty);
    
    /**
     * Retrieve the test boolean map property.
     *
     * @return The test boolean map property.
     * @see #setTestBooleanMapProperty(JsonBooleanMap)
     */
    @JsonProperty(name = "testBooleanMapProperty", operation = OperationType.GET)
    public JsonBooleanMap getTestBooleanMapProperty();
    
    /**
     * Set the test boolean map property.
     *
     * @param testBooleanMapProperty The test boolean map property.
     * @see #getTestBooleanMapProperty()
     */
    @JsonProperty(name = "testBooleanMapProperty", operation = OperationType.SET)
    public void setTestBooleanMapProperty(JsonBooleanMap testBooleanMapProperty);
  }
  
  /**
   * Test JSON Boolean Property Adapter
   * <p>
   * Created: 13th December 2009.
   *
   * @since json-object-0.2
   */
  public static class TestJsonBooleanPropertyAdapter implements JsonBooleanPropertyAdapter<String> {
    /**
     * Convert to a JSON property value.
     *
     * @param value The value.
     * @return The JSON property value.
     */
    @Override
    public Boolean toJsonProperty(String value) {
      return Boolean.valueOf(Boolean.parseBoolean(value));
    }
    
    /**
     * Convert from a JSON property value.
     *
     * @param propertyValue The JSON property value.
     * @return The value.
     */
    @Override
    public String fromJsonProperty(Boolean propertyValue) {
      return propertyValue.toString();
    }
  }
  
  /**
   * Test the retrieval of the boolean value of a property.
   * <p>
   * This test asserts that the retrieved boolean value of a property matches 
   * the value of the corresponding property of the underlying JSON object.
   */
  @Test
  public void testGetBooleanProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setBooleanProperty("testBooleanProperty", true);
    
    assertEquals(true, testJsonObject.getTestBooleanProperty().booleanValue());
  }
  
  /**
   * Test the setting of the boolean value of a property.
   * <p>
   * This test asserts that the setting of the boolean value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetBooleanProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestBooleanProperty(true);
    
    assertEquals(true, testJsonObject.getBooleanProperty("testBooleanProperty").booleanValue());
  }
  
  /**
   * Test the retrieval of the value of an adapted boolean property.
   * <p>
   * This test asserts that the retrieved value of an adapted boolean property
   * matches the adapted value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testGetAdaptedBooleanProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setBooleanProperty("testAdaptedBooleanProperty", true);
    
    assertEquals(new TestJsonBooleanPropertyAdapter().fromJsonProperty(true), testJsonObject.getTestAdaptedBooleanProperty());
  }
  
  /**
   * Test the setting of the value of an adapted boolean property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted boolean value.
   */
  @Test
  public void testSetAdaptedBooleanProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestAdaptedBooleanProperty(new TestJsonBooleanPropertyAdapter().fromJsonProperty(true));
    
    assertEquals(true, testJsonObject.getBooleanProperty("testAdaptedBooleanProperty").booleanValue());
  }

  /**
   * Test the retrieval of the boolean array value of a property.
   * <p>
   * This test asserts that the retrieved boolean array value of a property
   * matches the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testGetBooleanArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonBooleanArray testBooleanArrayProperty = JsonObjectFactory.get().createJsonArray(JsonBooleanArray.class);
    
    testJsonObject.setObjectProperty("testBooleanArrayProperty", testBooleanArrayProperty);
    
    assertEquals(testBooleanArrayProperty, testJsonObject.getTestBooleanArrayProperty());
  }
  
  /**
   * Test the setting of the boolean array value of a property.
   * <p>
   * This test asserts that the setting of the boolean array value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetBooleanArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonBooleanArray testBooleanArrayProperty = JsonObjectFactory.get().createJsonArray(JsonBooleanArray.class);
    
    testJsonObject.setTestBooleanArrayProperty(testBooleanArrayProperty);
    
    assertEquals(testBooleanArrayProperty, testJsonObject.getObjectProperty("testBooleanArrayProperty"));
  }
  
  /**
   * Test the retrieval of the boolean map value of a property.
   * <p>
   * This test asserts that the retrieved boolean map value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetBooleanMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonBooleanMap testBooleanMapProperty = JsonObjectFactory.get().createJsonObject(JsonBooleanMap.class);
    
    testJsonObject.setObjectProperty("testBooleanMapProperty", testBooleanMapProperty);
    
    assertEquals(testBooleanMapProperty, testJsonObject.getTestBooleanMapProperty());
  }
  
  /**
   * Test the setting of the boolean map value of a property.
   * <p>
   * This test asserts that the setting of the boolean map value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetBooleanMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonBooleanMap testBooleanMapProperty = JsonObjectFactory.get().createJsonObject(JsonBooleanMap.class);
    
    testJsonObject.setTestBooleanMapProperty(testBooleanMapProperty);
    
    assertEquals(testBooleanMapProperty, testJsonObject.getObjectProperty("testBooleanMapProperty"));
  }
}
