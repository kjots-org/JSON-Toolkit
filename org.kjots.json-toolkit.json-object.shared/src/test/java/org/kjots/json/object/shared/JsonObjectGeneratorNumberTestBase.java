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
 * JSON Object Generator Number Test Base.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public abstract class JsonObjectGeneratorNumberTestBase {
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject {
    /**
     * Retrieve the test number property.
     *
     * @return The test number property.
     * @see #setTestNumberProperty(Number)
     */
    @JsonProperty(name = "testNumberProperty", operation = OperationType.GET)
    public Number getTestNumberProperty();
    
    /**
     * Set the test number property.
     *
     * @param testNumberProperty The test number property.
     * @see #getTestNumberProperty()
     */
    @JsonProperty(name = "testNumberProperty", operation = OperationType.SET)
    public void setTestNumberProperty(Number testNumberProperty);
    
    /**
     * Retrieve the test adapted number property.
     * 
     * @return The test adapted number property.
     * @see #setTestAdaptedNumberProperty(String)
     */
    @JsonProperty(name = "testAdaptedNumberProperty", operation = OperationType.GET, adapter = TestJsonNumberPropertyAdapter.class)
    public String getTestAdaptedNumberProperty();
    
    /**
     * Set the test adapted number property.
     * 
     * @param testAdaptedNumberProperty The test adapted number property.
     * @see #getTestAdaptedNumberProperty()
     */
    @JsonProperty(name = "testAdaptedNumberProperty", operation = OperationType.SET, adapter = TestJsonNumberPropertyAdapter.class)
    public void setTestAdaptedNumberProperty(String testAdaptedNumberProperty);
    
    /**
     * Retrieve the test number array property.
     *
     * @return The test number array property.
     * @see #setTestNumberArrayProperty(JsonNumberArray)
     */
    @JsonProperty(name = "testNumberArrayProperty", operation = OperationType.GET)
    public JsonNumberArray getTestNumberArrayProperty();
    
    /**
     * Set the test number array property.
     *
     * @param testNumberArrayProperty The test number array property.
     * @see #getTestNumberArrayProperty()
     */
    @JsonProperty(name = "testNumberArrayProperty", operation = OperationType.SET)
    public void setTestNumberArrayProperty(JsonNumberArray testNumberArrayProperty);
    
    /**
     * Retrieve the test adapted number array property.
     *
     * @return The test adapted number array property.
     * @see #setTestAdaptedNumberArrayProperty(List)
     */
    @JsonProperty(name = "testAdaptedNumberArrayProperty", operation = OperationType.GET, adapter = TestJsonNumberArrayPropertyAdapter.class)
    public List<Number> getTestAdaptedNumberArrayProperty();

    /**
     * Set the test adapted number array property.
     *
     * @param testAdaptedNumberArrayProperty The test adapted number array property.
     * @see #getTestAdaptedNumberArrayProperty()
     */
    @JsonProperty(name = "testAdaptedNumberArrayProperty", operation = OperationType.SET, adapter = TestJsonNumberArrayPropertyAdapter.class)
    public void setTestAdaptedNumberArrayProperty(List<Number> testAdaptedNumberArrayProperty);

    /**
     * Retrieve the test number map property.
     *
     * @return The test number map property.
     * @see #setTestNumberMapProperty(JsonNumberMap)
     */
    @JsonProperty(name = "testNumberMapProperty", operation = OperationType.GET)
    public JsonNumberMap getTestNumberMapProperty();
    
    /**
     * Set the test number map property.
     *
     * @param testNumberMapProperty The test number map property.
     * @see #getTestNumberMapProperty()
     */
    @JsonProperty(name = "testNumberMapProperty", operation = OperationType.SET)
    public void setTestNumberMapProperty(JsonNumberMap testNumberMapProperty);
    
    /**
     * Retrieve the test adapted number map property.
     *
     * @return The test adapted number map property.
     * @see #setTestAdaptedNumberMapProperty(Map)
     */
    @JsonProperty(name = "testAdaptedNumberMapProperty", operation = OperationType.GET, adapter = TestJsonNumberMapPropertyAdapter.class)
    public Map<String, Number> getTestAdaptedNumberMapProperty();
    
    /**
     * Set the test adapted number map property.
     *
     * @param testAdaptedNumberMapProperty The test adapted number map property.
     * @see #getTestAdaptedNumberMapProperty()
     */
    @JsonProperty(name = "testAdaptedNumberMapProperty", operation = OperationType.SET, adapter = TestJsonNumberMapPropertyAdapter.class)
    public void setTestAdaptedNumberMapProperty(Map<String, Number> testAdaptedNumberMapProperty);
  }
  
  /**
   * Test JSON Number Property Adapter
   * <p>
   * Created: 13th December 2009.
   *
   * @since json-object-0.2
   */
  public static class TestJsonNumberPropertyAdapter implements JsonNumberPropertyAdapter<String> {
    /**
     * Convert to a JSON property value.
     *
     * @param value The value.
     * @return The JSON property value.
     */
    @Override
    public Number toJsonProperty(String value) {
      return Double.valueOf(Double.parseDouble(value));
    }
    
    /**
     * Convert from a JSON property value.
     *
     * @param propertyValue The JSON property value.
     * @return The value.
     */
    @Override
    public String fromJsonProperty(Number propertyValue) {
      return propertyValue.toString();
    }
  }
  
  /**
   * Test JSON Number Array Property Adapter.
   * <p>
   * Created: 7th July 2010.
   */
  public static class TestJsonNumberArrayPropertyAdapter implements JsonObjectPropertyAdapter<List<Number>, JsonNumberArray> {
    /**
     * Convert to a JSON object property value.
     *
     * @param testJsonNumberProperties The value.
     * @return The JSON object property value.
     * @see #fromJsonProperty(JsonNumberArray)
     */
    @Override
    public JsonNumberArray toJsonProperty(List<Number> testJsonNumberProperties) {
      JsonNumberArray jsonNumberArray = JsonObjectFactory.get().createJsonArray(JsonNumberArray.class);
      
      for (int i = 0; i < testJsonNumberProperties.size(); i++) {
        jsonNumberArray.set(i, testJsonNumberProperties.get(i));
      }
      
      return jsonNumberArray;
    }

    /**
     * Convert from a JSON object property value.
     *
     * @param propertyValue The JSON object property value.
     * @return The value.
     * @see #toJsonProperty(List)
     */
    @Override
    public List<Number> fromJsonProperty(JsonNumberArray propertyValue) {
      List<Number> list = new ArrayList<Number>(propertyValue.getLength());
      
      for (Number value : propertyValue) {
        list.add(value);
      }
      
      return list;
    }
  }
  
  /**
   * Test JSON Number Map Property Adapter.
   * <p>
   * Created: 7th July 2010.
   */
  public static class TestJsonNumberMapPropertyAdapter implements JsonObjectPropertyAdapter<Map<String, Number>, JsonNumberMap> {
    /**
     * Convert to a JSON object property value.
     *
     * @param testJsonNumberProperties The value.
     * @return The JSON object property value.
     * @see #fromJsonProperty(JsonNumberMap)
     */
    @Override
    public JsonNumberMap toJsonProperty(Map<String, Number> testJsonNumberProperties) {
      JsonNumberMap jsonNumberMap = JsonObjectFactory.get().createJsonObject(JsonNumberMap.class);
      
      for (Map.Entry<String, Number> entry : testJsonNumberProperties.entrySet()) {
        jsonNumberMap.set(entry.getKey(), entry.getValue());
      }
      
      return jsonNumberMap;
    }

    /**
     * Convert from a JSON object property value.
     *
     * @param propertyValue The JSON object property value.
     * @return The value.
     * @see #toJsonProperty(Map)
     */
    @Override
    public Map<String, Number> fromJsonProperty(JsonNumberMap propertyValue) {
      Map<String, Number> map = new HashMap<String, Number>();
      
      for (String key : propertyValue.getPropertyNames()) {
        map.put(key, propertyValue.get(key));
      }
      
      return map;
    }
  }
  
  /**
   * Test the retrieval of the numeric value of a property.
   * <p>
   * This test asserts that the retrieved numeric value of a property matches 
   * the value of the corresponding property of the underlying JSON object.
   */
  @Test
  public void testGetNumberProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setNumberProperty("testNumberProperty", 3.14);
    
    assertEquals(3.14, testJsonObject.getTestNumberProperty().doubleValue(), 0.001);
  }
  
  /**
   * Test the setting of the numeric value of a property.
   * <p>
   * This test asserts that the setting of the numeric value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetNumberProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestNumberProperty(3.14);
    
    assertEquals(3.14, testJsonObject.getNumberProperty("testNumberProperty").doubleValue(), 0.001);
  }
  
  /**
   * Test the retrieval of the value of an adapted number property.
   * <p>
   * This test asserts that the retrieved value of an adapted number property
   * matches the adapted value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testGetAdaptedNumberProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setNumberProperty("testAdaptedNumberProperty", 3.14);
    
    assertEquals(new TestJsonNumberPropertyAdapter().fromJsonProperty(3.14), testJsonObject.getTestAdaptedNumberProperty());
  }
  
  /**
   * Test the setting of the value of an adapted number property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted number value.
   */
  @Test
  public void testSetAdaptedNumberProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestAdaptedNumberProperty(new TestJsonNumberPropertyAdapter().fromJsonProperty(3.14));
    
    assertEquals(3.14, testJsonObject.getNumberProperty("testAdaptedNumberProperty").doubleValue(), 0.001);
  }

  /**
   * Test the retrieval of the number array value of a property.
   * <p>
   * This test asserts that the retrieved number array value of a property
   * matches the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testGetNumberArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonNumberArray testNumberArrayProperty = JsonObjectFactory.get().createJsonArray(JsonNumberArray.class);
    
    testJsonObject.setObjectProperty("testNumberArrayProperty", testNumberArrayProperty);
    
    assertEquals(testNumberArrayProperty, testJsonObject.getTestNumberArrayProperty());
  }
  
  /**
   * Test the setting of the number array value of a property.
   * <p>
   * This test asserts that the setting of the number array value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetNumberArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonNumberArray testNumberArrayProperty = JsonObjectFactory.get().createJsonArray(JsonNumberArray.class);
    
    testJsonObject.setTestNumberArrayProperty(testNumberArrayProperty);
    
    assertEquals(testNumberArrayProperty, testJsonObject.getObjectProperty("testNumberArrayProperty"));
  }
  
  /**
   * Test the retrieval of the value of an adapted number array property.
   * <p>
   * This test asserts that the retrieved value of an adapted number array
   * property matches the adapted value of the corresponding property of the
   * underlying JSON object.
   */
  @Test
  public void testGetAdaptedNumberArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    JsonNumberArray testJsonNumberArray = JsonObjectFactory.get().createJsonArray(JsonNumberArray.class);
    
    for (int i = 0; i < 10; i++) {
      testJsonNumberArray.set(i, Double.valueOf(i));
    }
    
    testJsonObject.setObjectProperty("testAdaptedNumberArrayProperty", testJsonNumberArray);
    
    List<Number> testAdaptedNumberArrayProperty = testJsonObject.getTestAdaptedNumberArrayProperty();
    
    assertNotNull(testAdaptedNumberArrayProperty);
    assertEquals(testJsonNumberArray.getLength(), testAdaptedNumberArrayProperty.size());
    for (int i = 0; i < testJsonNumberArray.getLength(); i++) {
      assertEquals(testJsonNumberArray.get(i).doubleValue(), testAdaptedNumberArrayProperty.get(i).doubleValue(), 0.1);
    }
  }

  /**
   * Test the setting of the value of an adapted number array property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted number array value.
   */
  @Test
  public void testSetAdaptedNumberArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    List<Number> testAdaptedNumberArrayProperty = new ArrayList<Number>();
    
    for (int i = 0; i < 10; i++) {
      testAdaptedNumberArrayProperty.add(Double.valueOf(i));
    }
    
    testJsonObject.setTestAdaptedNumberArrayProperty(testAdaptedNumberArrayProperty);
    
    JsonNumberArray testJsonNumberArray = testJsonObject.getObjectProperty("testAdaptedNumberArrayProperty", JsonNumberArray.class);
    
    assertNotNull(testJsonNumberArray);
    assertEquals(testAdaptedNumberArrayProperty.size(), testJsonNumberArray.getLength());
    for (int i = 0; i < testAdaptedNumberArrayProperty.size(); i++) {
      assertEquals(testAdaptedNumberArrayProperty.get(i).doubleValue(), testJsonNumberArray.get(i).doubleValue());
    }
  }

  /**
   * Test the retrieval of the number map value of a property.
   * <p>
   * This test asserts that the retrieved number map value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetNumberMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonNumberMap testNumberMapProperty = JsonObjectFactory.get().createJsonObject(JsonNumberMap.class);
    
    testJsonObject.setObjectProperty("testNumberMapProperty", testNumberMapProperty);
    
    assertEquals(testNumberMapProperty, testJsonObject.getTestNumberMapProperty());
  }
  
  /**
   * Test the setting of the number map value of a property.
   * <p>
   * This test asserts that the setting of the number map value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetNumberMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonNumberMap testNumberMapProperty = JsonObjectFactory.get().createJsonObject(JsonNumberMap.class);
    
    testJsonObject.setTestNumberMapProperty(testNumberMapProperty);
    
    assertEquals(testNumberMapProperty, testJsonObject.getObjectProperty("testNumberMapProperty"));
  }
  
  /**
   * Test the retrieval of the value of an adapted number map property.
   * <p>
   * This test asserts that the retrieved value of an adapted number map
   * property matches the adapted value of the corresponding property of the
   * underlying JSON object.
   */
  @Test
  public void testGetAdaptedNumberMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    JsonNumberMap testJsonNumberMap = JsonObjectFactory.get().createJsonObject(JsonNumberMap.class);
    
    for (int i = 0; i < 10; i++) {
      testJsonNumberMap.set(Integer.toString(i), Double.valueOf(i));
    }
    
    testJsonObject.setObjectProperty("testAdaptedNumberMapProperty", testJsonNumberMap);
    
    Map<String, Number> testAdaptedNumberMapProperty = testJsonObject.getTestAdaptedNumberMapProperty();
    
    assertNotNull(testAdaptedNumberMapProperty);
    assertEquals(testJsonNumberMap.getPropertyNames().size(), testAdaptedNumberMapProperty.size());
    for (String key : testJsonNumberMap.getPropertyNames()) {
      assertEquals(testJsonNumberMap.get(key).doubleValue(), testAdaptedNumberMapProperty.get(key).doubleValue(), 0.1);
    }
  }
  
  /**
   * Test the setting of the value of an adapted number map property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted number map value.
   */
  @Test
  public void testSetAdaptedNumberMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    Map<String, Number> testAdaptedNumberMapProperty = new HashMap<String, Number>();
    
    for (int i = 0; i < 10; i++) {
      testAdaptedNumberMapProperty.put(Integer.toString(i), Double.valueOf(i));
    }
    
    testJsonObject.setTestAdaptedNumberMapProperty(testAdaptedNumberMapProperty);
    
    JsonNumberMap testJsonNumberMap = testJsonObject.getObjectProperty("testAdaptedNumberMapProperty", JsonNumberMap.class);
    
    assertNotNull(testJsonNumberMap);
    assertEquals(testAdaptedNumberMapProperty.size(), testJsonNumberMap.getPropertyNames().size());
    for (Map.Entry<String, Number> entry : testAdaptedNumberMapProperty.entrySet()) {
      assertEquals(testAdaptedNumberMapProperty.get(entry.getKey()).doubleValue(), testJsonNumberMap.get(entry.getKey()).doubleValue(), 0.1);
    }
  }
}
