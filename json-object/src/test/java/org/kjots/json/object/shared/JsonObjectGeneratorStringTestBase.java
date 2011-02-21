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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kjots.json.object.shared.JsonProperty.OperationType;

/**
 * JSON Object Generator String Test Base.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public abstract class JsonObjectGeneratorStringTestBase {
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject {
    /**
     * Retrieve the test string property.
     *
     * @return The test string property.
     * @see #setTestStringProperty(String)
     */
    @JsonProperty(name = "testStringProperty", operation = OperationType.GET)
    public String getTestStringProperty();
    
    /**
     * Set the test string property.
     *
     * @param testStringProperty The test string property.
     * @see #getTestStringProperty()
     */
    @JsonProperty(name = "testStringProperty", operation = OperationType.SET)
    public void setTestStringProperty(String testStringProperty);
    
    /**
     * Retrieve the test adapted string property.
     * 
     * @return The test adapted string property.
     * @see #setTestAdaptedStringProperty(Date)
     */
    @JsonProperty(name = "testAdaptedStringProperty", operation = OperationType.GET, adapter = TestJsonStringPropertyAdapter.class)
    public Date getTestAdaptedStringProperty();
    
    /**
     * Set the test adapted string property.
     * 
     * @param testAdaptedStringProperty The test adapted string property.
     * @see #getTestAdaptedStringProperty()
     */
    @JsonProperty(name = "testAdaptedStringProperty", operation = OperationType.SET, adapter = TestJsonStringPropertyAdapter.class)
    public void setTestAdaptedStringProperty(Date testAdaptedStringProperty);
    
    /**
     * Retrieve the test string array property.
     *
     * @return The test string array property.
     * @see #setTestStringArrayProperty(JsonStringArray)
     */
    @JsonProperty(name = "testStringArrayProperty", operation = OperationType.GET)
    public JsonStringArray getTestStringArrayProperty();
    
    /**
     * Set the test string array property.
     *
     * @param testStringArrayProperty The test string array property.
     * @see #getTestStringArrayProperty()
     */
    @JsonProperty(name = "testStringArrayProperty", operation = OperationType.SET)
    public void setTestStringArrayProperty(JsonStringArray testStringArrayProperty);
    
    /**
     * Retrieve the test adapted string array property.
     *
     * @return The test adapted string array property.
     * @see #setTestAdaptedStringArrayProperty(List)
     */
    @JsonProperty(name = "testAdaptedStringArrayProperty", operation = OperationType.GET, adapter = TestJsonStringArrayPropertyAdapter.class)
    public List<String> getTestAdaptedStringArrayProperty();

    /**
     * Set the test adapted string array property.
     *
     * @param testAdaptedStringArrayProperty The test adapted string array property.
     * @see #getTestAdaptedStringArrayProperty()
     */
    @JsonProperty(name = "testAdaptedStringArrayProperty", operation = OperationType.SET, adapter = TestJsonStringArrayPropertyAdapter.class)
    public void setTestAdaptedStringArrayProperty(List<String> testAdaptedStringArrayProperty);

    /**
     * Retrieve the test string map property.
     *
     * @return The test string map property.
     * @see #setTestStringMapProperty(JsonStringMap)
     */
    @JsonProperty(name = "testStringMapProperty", operation = OperationType.GET)
    public JsonStringMap getTestStringMapProperty();
    
    /**
     * Set the test string map property.
     *
     * @param testStringMapProperty The test string map property.
     * @see #getTestStringMapProperty()
     */
    @JsonProperty(name = "testStringMapProperty", operation = OperationType.SET)
    public void setTestStringMapProperty(JsonStringMap testStringMapProperty);
    
    /**
     * Retrieve the test adapted string map property.
     *
     * @return The test adapted string map property.
     * @see #setTestAdaptedStringMapProperty(Map)
     */
    @JsonProperty(name = "testAdaptedStringMapProperty", operation = OperationType.GET, adapter = TestJsonStringMapPropertyAdapter.class)
    public Map<String, String> getTestAdaptedStringMapProperty();
    
    /**
     * Set the test adapted string map property.
     *
     * @param testAdaptedStringMapProperty The test adapted string map property.
     * @see #getTestAdaptedStringMapProperty()
     */
    @JsonProperty(name = "testAdaptedStringMapProperty", operation = OperationType.SET, adapter = TestJsonStringMapPropertyAdapter.class)
    public void setTestAdaptedStringMapProperty(Map<String, String> testAdaptedStringMapProperty);
  }
  
  /**
   * Test JSON String Property Adapter
   * <p>
   * Created: 13th December 2009.
   *
   * @since json-object-0.2
   */
  public static class TestJsonStringPropertyAdapter implements JsonStringPropertyAdapter<Date> {
    /**
     * Convert to a JSON property value.
     *
     * @param value The value.
     * @return The JSON property value.
     */
    @Override
    @SuppressWarnings("deprecation")
    public String toJsonProperty(Date value) {
      return value.toGMTString();
    }
    
    /**
     * Convert from a JSON property value.
     *
     * @param propertyValue The JSON property value.
     * @return The value.
     */
    @Override
    @SuppressWarnings("deprecation")
    public Date fromJsonProperty(String propertyValue) {
      return new Date(Date.parse(propertyValue));
    }
  }
  
  /**
   * Test JSON String Array Property Adapter.
   * <p>
   * Created: 7th July 2010.
   */
  public static class TestJsonStringArrayPropertyAdapter implements JsonObjectPropertyAdapter<List<String>, JsonStringArray> {
    /**
     * Convert to a JSON object property value.
     *
     * @param testJsonStringProperties The value.
     * @return The JSON object property value.
     * @see #fromJsonProperty(JsonStringArray)
     */
    @Override
    public JsonStringArray toJsonProperty(List<String> testJsonStringProperties) {
      JsonStringArray jsonStringArray = JsonObjectFactory.get().createJsonArray(JsonStringArray.class);
      
      for (int i = 0; i < testJsonStringProperties.size(); i++) {
        jsonStringArray.set(i, testJsonStringProperties.get(i));
      }
      
      return jsonStringArray;
    }

    /**
     * Convert from a JSON object property value.
     *
     * @param propertyValue The JSON object property value.
     * @return The value.
     * @see #toJsonProperty(List)
     */
    @Override
    public List<String> fromJsonProperty(JsonStringArray propertyValue) {
      List<String> list = new ArrayList<String>(propertyValue.getLength());
      
      for (String value : propertyValue) {
        list.add(value);
      }
      
      return list;
    }
  }
  
  /**
   * Test JSON String Map Property Adapter.
   * <p>
   * Created: 7th July 2010.
   */
  public static class TestJsonStringMapPropertyAdapter implements JsonObjectPropertyAdapter<Map<String, String>, JsonStringMap> {
    /**
     * Convert to a JSON object property value.
     *
     * @param testJsonStringProperties The value.
     * @return The JSON object property value.
     * @see #fromJsonProperty(JsonStringMap)
     */
    @Override
    public JsonStringMap toJsonProperty(Map<String, String> testJsonStringProperties) {
      JsonStringMap jsonStringMap = JsonObjectFactory.get().createJsonObject(JsonStringMap.class);
      
      for (Map.Entry<String, String> entry : testJsonStringProperties.entrySet()) {
        jsonStringMap.set(entry.getKey(), entry.getValue());
      }
      
      return jsonStringMap;
    }

    /**
     * Convert from a JSON object property value.
     *
     * @param propertyValue The JSON object property value.
     * @return The value.
     * @see #toJsonProperty(Map)
     */
    @Override
    public Map<String, String> fromJsonProperty(JsonStringMap propertyValue) {
      Map<String, String> map = new HashMap<String, String>();
      
      for (String key : propertyValue.getPropertyNames()) {
        map.put(key, propertyValue.get(key));
      }
      
      return map;
    }
  }
  
  /**
   * Test the retrieval of the string value of a property.
   * <p>
   * This test asserts that the retrieved string value of a property matches 
   * the value of the corresponding property of the underlying JSON object.
   */
  @Test
  public void testGetStringProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setStringProperty("testStringProperty", "Test String Property Value");
    
    assertEquals("Test String Property Value", testJsonObject.getTestStringProperty());
  }
  
  /**
   * Test the setting of the string value of a property.
   * <p>
   * This test asserts that the setting of the string value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetStringProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestStringProperty("Test String Property Value");
    
    assertEquals("Test String Property Value", testJsonObject.getStringProperty("testStringProperty"));
  }
  
  /**
   * Test the retrieval of the value of an adapted string property.
   * <p>
   * This test asserts that the retrieved value of an adapted string property
   * matches the adapted value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testGetAdaptedStringProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setStringProperty("testAdaptedStringProperty", "13 Dec 2009 02:00:00 GMT");
    
    assertEquals(new TestJsonStringPropertyAdapter().fromJsonProperty("13 Dec 2009 02:00:00 GMT"), testJsonObject.getTestAdaptedStringProperty());
  }
  
  /**
   * Test the setting of the value of an adapted string property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted string value.
   */
  @Test
  public void testSetAdaptedStringProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestAdaptedStringProperty(new TestJsonStringPropertyAdapter().fromJsonProperty("13 Dec 2009 02:00:00 GMT"));
    
    assertEquals("13 Dec 2009 02:00:00 GMT", testJsonObject.getStringProperty("testAdaptedStringProperty"));
  }
  
  /**
   * Test the retrieval of the string array value of a property.
   * <p>
   * This test asserts that the retrieved string array value of a property
   * matches the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testGetStringArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonStringArray testStringArrayProperty = JsonObjectFactory.get().createJsonArray(JsonStringArray.class);
    
    testJsonObject.setObjectProperty("testStringArrayProperty", testStringArrayProperty);
    
    assertEquals(testStringArrayProperty, testJsonObject.getTestStringArrayProperty());
  }
  
  /**
   * Test the setting of the string array value of a property.
   * <p>
   * This test asserts that the setting of the string array value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetStringArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonStringArray testStringArrayProperty = JsonObjectFactory.get().createJsonArray(JsonStringArray.class);
    
    testJsonObject.setTestStringArrayProperty(testStringArrayProperty);
    
    assertEquals(testStringArrayProperty, testJsonObject.getObjectProperty("testStringArrayProperty"));
  }
  
  /**
   * Test the retrieval of the value of an adapted string array property.
   * <p>
   * This test asserts that the retrieved value of an adapted string array
   * property matches the adapted value of the corresponding property of the
   * underlying JSON object.
   */
  @Test
  public void testGetAdaptedStringArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    JsonStringArray testJsonStringArray = JsonObjectFactory.get().createJsonArray(JsonStringArray.class);
    
    for (int i = 0; i < 10; i++) {
      testJsonStringArray.set(i, Integer.toString(i));
    }
    
    testJsonObject.setObjectProperty("testAdaptedStringArrayProperty", testJsonStringArray);
    
    List<String> testAdaptedStringArrayProperty = testJsonObject.getTestAdaptedStringArrayProperty();
    
    assertNotNull(testAdaptedStringArrayProperty);
    assertEquals(testJsonStringArray.getLength(), testAdaptedStringArrayProperty.size());
    for (int i = 0; i < testJsonStringArray.getLength(); i++) {
      assertEquals(testJsonStringArray.get(i), testAdaptedStringArrayProperty.get(i));
    }
  }

  /**
   * Test the setting of the value of an adapted string array property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted string array value.
   */
  @Test
  public void testSetAdaptedStringArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    List<String> testAdaptedStringArrayProperty = new ArrayList<String>();
    
    for (int i = 0; i < 10; i++) {
      testAdaptedStringArrayProperty.add(Integer.toString(i));
    }
    
    testJsonObject.setTestAdaptedStringArrayProperty(testAdaptedStringArrayProperty);
    
    JsonStringArray testJsonStringArray = testJsonObject.getObjectProperty("testAdaptedStringArrayProperty", JsonStringArray.class);
    
    assertNotNull(testJsonStringArray);
    assertEquals(testAdaptedStringArrayProperty.size(), testJsonStringArray.getLength());
    for (int i = 0; i < testAdaptedStringArrayProperty.size(); i++) {
      assertEquals(testAdaptedStringArrayProperty.get(i), testJsonStringArray.get(i));
    }
  }

  /**
   * Test the retrieval of the string map value of a property.
   * <p>
   * This test asserts that the retrieved string map value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetStringMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonStringMap testStringMapProperty = JsonObjectFactory.get().createJsonObject(JsonStringMap.class);
    
    testJsonObject.setObjectProperty("testStringMapProperty", testStringMapProperty);
    
    assertEquals(testStringMapProperty, testJsonObject.getTestStringMapProperty());
  }
  
  /**
   * Test the setting of the string map value of a property.
   * <p>
   * This test asserts that the setting of the string map value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetStringMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonStringMap testStringMapProperty = JsonObjectFactory.get().createJsonObject(JsonStringMap.class);
    
    testJsonObject.setTestStringMapProperty(testStringMapProperty);
    
    assertEquals(testStringMapProperty, testJsonObject.getObjectProperty("testStringMapProperty"));
  }
  
  /**
   * Test the retrieval of the value of an adapted string map property.
   * <p>
   * This test asserts that the retrieved value of an adapted string map
   * property matches the adapted value of the corresponding property of the
   * underlying JSON object.
   */
  @Test
  public void testGetAdaptedStringMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    JsonStringMap testJsonStringMap = JsonObjectFactory.get().createJsonObject(JsonStringMap.class);
    
    for (int i = 0; i < 10; i++) {
      testJsonStringMap.set(Integer.toString(i), Integer.toString(i));
    }
    
    testJsonObject.setObjectProperty("testAdaptedStringMapProperty", testJsonStringMap);
    
    Map<String, String> testAdaptedStringMapProperty = testJsonObject.getTestAdaptedStringMapProperty();
    
    assertNotNull(testAdaptedStringMapProperty);
    assertEquals(testJsonStringMap.getPropertyNames().size(), testAdaptedStringMapProperty.size());
    for (String key : testJsonStringMap.getPropertyNames()) {
      assertEquals(testJsonStringMap.get(key), testAdaptedStringMapProperty.get(key));
    }
  }
  
  /**
   * Test the setting of the value of an adapted string map property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted string map value.
   */
  @Test
  public void testSetAdaptedStringMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    Map<String, String> testAdaptedStringMapProperty = new HashMap<String, String>();
    
    for (int i = 0; i < 10; i++) {
      testAdaptedStringMapProperty.put(Integer.toString(i), Integer.toString(i));
    }
    
    testJsonObject.setTestAdaptedStringMapProperty(testAdaptedStringMapProperty);
    
    JsonStringMap testJsonStringMap = testJsonObject.getObjectProperty("testAdaptedStringMapProperty", JsonStringMap.class);
    
    assertNotNull(testJsonStringMap);
    assertEquals(testAdaptedStringMapProperty.size(), testJsonStringMap.getPropertyNames().size());
    for (Map.Entry<String, String> entry : testAdaptedStringMapProperty.entrySet()) {
      assertEquals(testAdaptedStringMapProperty.get(entry.getKey()), testJsonStringMap.get(entry.getKey()));
    }
  }
}
