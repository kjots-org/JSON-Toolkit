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
import static junit.framework.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Retrieve the test adapted boolean array property.
     *
     * @return The test adapted boolean array property.
     * @see #setTestAdaptedBooleanArrayProperty(List)
     */
    @JsonProperty(name = "testAdaptedBooleanArrayProperty", operation = OperationType.GET, adapter = TestJsonBooleanArrayPropertyAdapter.class)
    public List<Boolean> getTestAdaptedBooleanArrayProperty();

    /**
     * Set the test adapted boolean array property.
     *
     * @param testAdaptedBooleanArrayProperty The test adapted boolean array property.
     * @see #getTestAdaptedBooleanArrayProperty()
     */
    @JsonProperty(name = "testAdaptedBooleanArrayProperty", operation = OperationType.SET, adapter = TestJsonBooleanArrayPropertyAdapter.class)
    public void setTestAdaptedBooleanArrayProperty(List<Boolean> testAdaptedBooleanArrayProperty);

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
    
    /**
     * Retrieve the test adapted boolean map property.
     *
     * @return The test adapted boolean map property.
     * @see #setTestAdaptedBooleanMapProperty(Map)
     */
    @JsonProperty(name = "testAdaptedBooleanMapProperty", operation = OperationType.GET, adapter = TestJsonBooleanMapPropertyAdapter.class)
    public Map<String, Boolean> getTestAdaptedBooleanMapProperty();
    
    /**
     * Set the test adapted boolean map property.
     *
     * @param testAdaptedBooleanMapProperty The test adapted boolean map property.
     * @see #getTestAdaptedBooleanMapProperty()
     */
    @JsonProperty(name = "testAdaptedBooleanMapProperty", operation = OperationType.SET, adapter = TestJsonBooleanMapPropertyAdapter.class)
    public void setTestAdaptedBooleanMapProperty(Map<String, Boolean> testAdaptedBooleanMapProperty);
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
     * Convert to a JSON boolean property value.
     *
     * @param string The value.
     * @return The JSON boolean property value.
     * @see #fromJsonProperty(Boolean)
     */
    @Override
    public Boolean toJsonProperty(String string) {
      return Boolean.valueOf(Boolean.parseBoolean(string));
    }
    
    /**
     * Convert from a JSON boolean property value.
     *
     * @param propertyValue The JSON boolean property value.
     * @return The value.
     * @see #toJsonProperty(String)
     */
    @Override
    public String fromJsonProperty(Boolean propertyValue) {
      return propertyValue.toString();
    }
  }
  
  /**
   * Test JSON Boolean Array Property Adapter.
   * <p>
   * Created: 7th July 2010.
   */
  public static class TestJsonBooleanArrayPropertyAdapter implements JsonObjectPropertyAdapter<List<Boolean>, JsonBooleanArray> {
    /**
     * Convert to a JSON object property value.
     *
     * @param testJsonBooleanProperties The value.
     * @return The JSON object property value.
     * @see #fromJsonProperty(JsonBooleanArray)
     */
    @Override
    public JsonBooleanArray toJsonProperty(List<Boolean> testJsonBooleanProperties) {
      JsonBooleanArray jsonBooleanArray = JsonObjectFactory.get().createJsonArray(JsonBooleanArray.class);
      
      for (int i = 0; i < testJsonBooleanProperties.size(); i++) {
        jsonBooleanArray.set(i, testJsonBooleanProperties.get(i));
      }
      
      return jsonBooleanArray;
    }

    /**
     * Convert from a JSON object property value.
     *
     * @param propertyValue The JSON object property value.
     * @return The value.
     * @see #toJsonProperty(List)
     */
    @Override
    public List<Boolean> fromJsonProperty(JsonBooleanArray propertyValue) {
      List<Boolean> list = new ArrayList<Boolean>(propertyValue.getLength());
      
      for (Boolean value : propertyValue) {
        list.add(value);
      }
      
      return list;
    }
  }
  
  /**
   * Test JSON Boolean Map Property Adapter.
   * <p>
   * Created: 7th July 2010.
   */
  public static class TestJsonBooleanMapPropertyAdapter implements JsonObjectPropertyAdapter<Map<String, Boolean>, JsonBooleanMap> {
    /**
     * Convert to a JSON object property value.
     *
     * @param testJsonBooleanProperties The value.
     * @return The JSON object property value.
     * @see #fromJsonProperty(JsonBooleanMap)
     */
    @Override
    public JsonBooleanMap toJsonProperty(Map<String, Boolean> testJsonBooleanProperties) {
      JsonBooleanMap jsonBooleanMap = JsonObjectFactory.get().createJsonObject(JsonBooleanMap.class);
      
      for (Map.Entry<String, Boolean> entry : testJsonBooleanProperties.entrySet()) {
        jsonBooleanMap.set(entry.getKey(), entry.getValue());
      }
      
      return jsonBooleanMap;
    }

    /**
     * Convert from a JSON object property value.
     *
     * @param propertyValue The JSON object property value.
     * @return The value.
     * @see #toJsonProperty(Map)
     */
    @Override
    public Map<String, Boolean> fromJsonProperty(JsonBooleanMap propertyValue) {
      Map<String, Boolean> map = new HashMap<String, Boolean>();
      
      for (String key : propertyValue.getPropertyNames()) {
        map.put(key, propertyValue.get(key));
      }
      
      return map;
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
   * Test the retrieval of the value of an adapted boolean array property.
   * <p>
   * This test asserts that the retrieved value of an adapted boolean array
   * property matches the adapted value of the corresponding property of the
   * underlying JSON object.
   */
  @Test
  public void testGetAdaptedBooleanArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    JsonBooleanArray testJsonBooleanArray = JsonObjectFactory.get().createJsonArray(JsonBooleanArray.class);
    
    for (int i = 0; i < 10; i++) {
      testJsonBooleanArray.set(i, i % 2 == 0);
    }
    
    testJsonObject.setObjectProperty("testAdaptedBooleanArrayProperty", testJsonBooleanArray);
    
    List<Boolean> testAdaptedBooleanArrayProperty = testJsonObject.getTestAdaptedBooleanArrayProperty();
    
    assertNotNull(testAdaptedBooleanArrayProperty);
    assertEquals(testJsonBooleanArray.getLength(), testAdaptedBooleanArrayProperty.size());
    for (int i = 0; i < testJsonBooleanArray.getLength(); i++) {
      assertEquals(testJsonBooleanArray.get(i), testAdaptedBooleanArrayProperty.get(i));
    }
  }

  /**
   * Test the setting of the value of an adapted boolean array property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted boolean array value.
   */
  @Test
  public void testSetAdaptedBooleanArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    List<Boolean> testAdaptedBooleanArrayProperty = new ArrayList<Boolean>();
    
    for (int i = 0; i < 10; i++) {
      testAdaptedBooleanArrayProperty.add(i % 2 == 0);
    }
    
    testJsonObject.setTestAdaptedBooleanArrayProperty(testAdaptedBooleanArrayProperty);
    
    JsonBooleanArray testJsonBooleanArray = testJsonObject.getObjectProperty("testAdaptedBooleanArrayProperty", JsonBooleanArray.class);
    
    assertNotNull(testJsonBooleanArray);
    assertEquals(testAdaptedBooleanArrayProperty.size(), testJsonBooleanArray.getLength());
    for (int i = 0; i < testAdaptedBooleanArrayProperty.size(); i++) {
      assertEquals(testAdaptedBooleanArrayProperty.get(i), testJsonBooleanArray.get(i));
    }
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
  
  /**
   * Test the retrieval of the value of an adapted boolean map property.
   * <p>
   * This test asserts that the retrieved value of an adapted boolean map
   * property matches the adapted value of the corresponding property of the
   * underlying JSON object.
   */
  @Test
  public void testGetAdaptedBooleanMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    JsonBooleanMap testJsonBooleanMap = JsonObjectFactory.get().createJsonObject(JsonBooleanMap.class);
    
    for (int i = 0; i < 10; i++) {
      testJsonBooleanMap.set(Integer.toString(i), i % 2 == 0);
    }
    
    testJsonObject.setObjectProperty("testAdaptedBooleanMapProperty", testJsonBooleanMap);
    
    Map<String, Boolean> testAdaptedBooleanMapProperty = testJsonObject.getTestAdaptedBooleanMapProperty();
    
    assertNotNull(testAdaptedBooleanMapProperty);
    assertEquals(testJsonBooleanMap.getPropertyNames().size(), testAdaptedBooleanMapProperty.size());
    for (String key : testJsonBooleanMap.getPropertyNames()) {
      assertEquals(testJsonBooleanMap.get(key), testAdaptedBooleanMapProperty.get(key));
    }
  }
  
  /**
   * Test the setting of the value of an adapted boolean map property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted boolean map value.
   */
  @Test
  public void testSetAdaptedBooleanMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    Map<String, Boolean> testAdaptedBooleanMapProperty = new HashMap<String, Boolean>();
    
    for (int i = 0; i < 10; i++) {
      testAdaptedBooleanMapProperty.put(Integer.toString(i), i % 2 == 0);
    }
    
    testJsonObject.setTestAdaptedBooleanMapProperty(testAdaptedBooleanMapProperty);
    
    JsonBooleanMap testJsonBooleanMap = testJsonObject.getObjectProperty("testAdaptedBooleanMapProperty", JsonBooleanMap.class);
    
    assertNotNull(testJsonBooleanMap);
    assertEquals(testAdaptedBooleanMapProperty.size(), testJsonBooleanMap.getPropertyNames().size());
    for (Map.Entry<String, Boolean> entry : testAdaptedBooleanMapProperty.entrySet()) {
      assertEquals(testAdaptedBooleanMapProperty.get(entry.getKey()), testJsonBooleanMap.get(entry.getKey()));
    }
  }
}
