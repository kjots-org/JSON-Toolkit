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
import static junit.framework.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kjots.json.object.shared.JsonProperty.OperationType;

/**
 * JSON Object Generator Object Test Base.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public abstract class JsonObjectGeneratorObjectTestBase {
  /**
   * Test Property JSON Object.
   * <p>
   * Created: 7th December 2009.
   */
  public interface TestPropertyJsonObject extends JsonObject {
    /**
     * Retrieve the first property.
     *
     * @return The first property.
     * @see #setProperty1(String)
     */
    @JsonProperty(name = "property1", operation = OperationType.GET)
    public String getProperty1();
    
    /**
     * Set the first property.
     *
     * @param property2 The first property.
     * @see #getProperty1()
     */
    @JsonProperty(name = "property1", operation = OperationType.SET)
    public void setProperty1(String property1);
    
    /**
     * Retrieve the second property.
     *
     * @return The second property.
     * @see #setProperty2(String)
     */
    @JsonProperty(name = "property2", operation = OperationType.GET)
    public String getProperty2();
    
    /**
     * Set the second property.
     *
     * @param property2 The second property.
     * @see #getProperty2()
     */
    @JsonProperty(name = "property2", operation = OperationType.SET)
    public void setProperty2(String property2);
  }
  
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject {
    /**
     * Retrieve the test object property.
     *
     * @return The test object property.
     * @see #setTestObjectProperty(TestPropertyJsonObject)
     */
    @JsonProperty(name = "testObjectProperty", operation = OperationType.GET)
    public TestPropertyJsonObject getTestObjectProperty();
    
    /**
     * Set the test object property.
     *
     * @param testObjectProperty The test object property.
     * @see #getTestStringProperty()
     */
    @JsonProperty(name = "testObjectProperty", operation = OperationType.SET)
    public void setTestObjectProperty(TestPropertyJsonObject testObjectProperty);
    
    /**
     * Retrieve the test adapted object property.
     * 
     * @return The test adapted object property.
     * @see #setTestAdaptedObjectProperty(TestJsonObjectProperty)
     */
    @JsonProperty(name = "testAdaptedObjectProperty", operation = OperationType.GET, adapter = TestJsonObjectPropertyAdapter.class)
    public TestJsonObjectProperty getTestAdaptedObjectProperty();
    
    /**
     * Set the test adapted object property.
     * 
     * @param testAdaptedObjectProperty The test adapted object property.
     * @see #getTestAdaptedObjectProperty()
     */
    @JsonProperty(name = "testAdaptedObjectProperty", operation = OperationType.SET, adapter = TestJsonObjectPropertyAdapter.class)
    public void setTestAdaptedObjectProperty(TestJsonObjectProperty testAdaptedObjectProperty);
    
    /**
     * Retrieve the test array property.
     *
     * @return The test array property.
     * @see #setTestArrayProperty(JsonArray)
     */
    @JsonProperty(name = "testArrayProperty", operation = OperationType.GET)
    public JsonArray getTestArrayProperty();
    
    /**
     * Set the test array property.
     *
     * @param testArrayProperty The test array property.
     * @see #getTestArrayProperty()
     */
    @JsonProperty(name = "testArrayProperty", operation = OperationType.SET)
    public void setTestArrayProperty(JsonArray testArrayProperty);
    
    /**
     * Retrieve the test object array property.
     *
     * @return The test object array property.
     * @see #setTestObjectArrayProperty(JsonObjectArray)
     */
    @JsonProperty(name = "testObjectArrayProperty", operation = OperationType.GET)
    public JsonObjectArray<JsonObject> getTestObjectArrayProperty();
    
    /**
     * Set the test object array property.
     *
     * @param testStringArrayProperty The test object array property.
     * @see #getTestObjectArrayProperty()
     */
    @JsonProperty(name = "testObjectArrayProperty", operation = OperationType.SET)
    public void setTestObjectArrayProperty(JsonObjectArray<JsonObject> testObjectArrayProperty);
    
    /**
     * Retrieve the test property object array property.
     *
     * @return The test property object array property.
     */
    @JsonProperty(name = "testPropertyObjectArrayProperty", operation = OperationType.GET)
    public JsonObjectArray<TestPropertyJsonObject> getTestPropertyObjectArrayProperty();
    
    /**
     * Retrieve the test object map property.
     *
     * @return The test object map property.
     * @see #setTestObjectMapProperty(JsonObjectMap)
     */
    @JsonProperty(name = "testObjectMapProperty", operation = OperationType.GET)
    public JsonObjectMap<JsonObject> getTestObjectMapProperty();
    
    /**
     * Retrieve the test adapted object array property.
     *
     * @return The test adapted object array property.
     * @see #setTestAdaptedObjectArrayProperty(List)
     */
    @JsonProperty(name = "testAdaptedObjectArrayProperty", operation = OperationType.GET, adapter = TestJsonObjectArrayPropertyAdapter.class)
    public List<TestJsonObjectProperty> getTestAdaptedObjectArrayProperty();
    
    /**
     * Set the test adapted object array property.
     *
     * @param testAdaptedObjectArrayProperty The test adapted object array property.
     * @see #getTestAdaptedObjectArrayProperty()
     */
    @JsonProperty(name = "testAdaptedObjectArrayProperty", operation = OperationType.SET, adapter = TestJsonObjectArrayPropertyAdapter.class)
    public void setTestAdaptedObjectArrayProperty(List<TestJsonObjectProperty> testAdaptedObjectArrayProperty);
    
    /**
     * Set the test object map property.
     *
     * @param testStringMapProperty The test object map property.
     * @see #getTestObjectMapProperty()
     */
    @JsonProperty(name = "testObjectMapProperty", operation = OperationType.SET)
    public void setTestObjectMapProperty(JsonObjectMap<JsonObject> testObjectMapProperty);
    
    /**
     * Retrieve the test property object map property.
     *
     * @return The test property object map property.
     */
    @JsonProperty(name = "testPropertyObjectMapProperty", operation = OperationType.GET)
    public JsonObjectMap<TestPropertyJsonObject> getTestPropertyObjectMapProperty();
    
    /**
     * Retrieve the test adapted object map property.
     *
     * @return The test adapted object map property.
     * @see #setTestAdaptedObjectMapProperty(Map)
     */
    @JsonProperty(name = "testAdaptedObjectMapProperty", operation = OperationType.GET, adapter = TestJsonObjectMapPropertyAdapter.class)
    public Map<String, TestJsonObjectProperty> getTestAdaptedObjectMapProperty();
    
    /**
     * Set the test adapted object map property.
     *
     * @param testAdaptedObjectMapProperty The test adapted object map property.
     * @see #getTestAdaptedObjectMapProperty()
     */
    @JsonProperty(name = "testAdaptedObjectMapProperty", operation = OperationType.SET, adapter = TestJsonObjectMapPropertyAdapter.class)
    public void setTestAdaptedObjectMapProperty(Map<String, TestJsonObjectProperty> testAdaptedObjectMapProperty);
  }
  
  /**
   * Test JSON Object Property Adapter
   * <p>
   * Created: 13th December 2009.
   *
   * @since json-object-0.2
   */
  public static class TestJsonObjectPropertyAdapter implements JsonObjectPropertyAdapter<TestJsonObjectProperty, TestPropertyJsonObject> {
    /**
     * Convert to a JSON property value.
     *
     * @param value The value.
     * @return The JSON property value.
     */
    @Override
    public TestPropertyJsonObject toJsonProperty(TestJsonObjectProperty value) {
      TestPropertyJsonObject testPropertyJsonObject = JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class);
      
      testPropertyJsonObject.setProperty1(value.getProperty1());
      testPropertyJsonObject.setProperty2(value.getProperty2());
      
      return testPropertyJsonObject;
    }
    
    /**
     * Convert from a JSON property value.
     *
     * @param propertyValue The JSON property value.
     * @return The value.
     */
    @Override
    public TestJsonObjectProperty fromJsonProperty(TestPropertyJsonObject propertyValue) {
      return new TestJsonObjectProperty(propertyValue.getProperty1(), propertyValue.getProperty2());
    }
  }
  
  /**
   * Test JSON Object Array Property Adapter.
   * <p>
   * Created: 7th July 2010.
   */
  public static class TestJsonObjectArrayPropertyAdapter implements JsonObjectPropertyAdapter<List<TestJsonObjectProperty>, JsonObjectArray<?>> {
    /** The test JSON object property adapter. */
    private final TestJsonObjectPropertyAdapter testJsonObjectPropertyAdapter = new TestJsonObjectPropertyAdapter();
    
    /**
     * Convert to a JSON property value.
     *
     * @param value The value.
     * @return The JSON property value.
     */
    @Override
    public JsonObjectArray<?> toJsonProperty(List<TestJsonObjectProperty> value) {
      JsonObjectArray<TestPropertyJsonObject> jsonObjectArray = createJsonObjectArray(TestPropertyJsonObject.class);
      
      for (int i = 0; i < value.size(); i++) {
        jsonObjectArray.set(i, this.testJsonObjectPropertyAdapter.toJsonProperty(value.get(i)));
      }
      
      return jsonObjectArray;
    }

    /**
     * Convert from a JSON property value.
     *
     * @param propertyValue The JSON property value.
     * @return The value.
     */
    @Override
    public List<TestJsonObjectProperty> fromJsonProperty(JsonObjectArray<?> propertyValue) {
      List<TestJsonObjectProperty> list = new ArrayList<TestJsonObjectProperty>(propertyValue.getLength());
      
      for (JsonObject value : propertyValue) {
        list.add(this.testJsonObjectPropertyAdapter.fromJsonProperty(value.cast(TestPropertyJsonObject.class)));
      }
      
      return list;
    }
  }
  
  /**
   * Test JSON Object Map Property Adapter.
   * <p>
   * Created: 7th July 2010.
   */
  public static class TestJsonObjectMapPropertyAdapter implements JsonObjectPropertyAdapter<Map<String, TestJsonObjectProperty>, JsonObjectMap<?>> {
    /** The test JSON object property adapter. */
    private final TestJsonObjectPropertyAdapter testJsonObjectPropertyAdapter = new TestJsonObjectPropertyAdapter();
    
    /**
     * Convert to a JSON property value.
     *
     * @param value The value.
     * @return The JSON property value.
     */
    @Override
    public JsonObjectMap<?> toJsonProperty(Map<String, TestJsonObjectProperty> value) {
      JsonObjectMap<TestPropertyJsonObject> jsonObjectMap = createJsonObjectMap(TestPropertyJsonObject.class);
      
      for (Map.Entry<String, TestJsonObjectProperty> entry : value.entrySet()) {
        jsonObjectMap.set(entry.getKey(), this.testJsonObjectPropertyAdapter.toJsonProperty(entry.getValue()));
      }
      
      return jsonObjectMap;
    }

    /**
     * Convert from a JSON property value.
     *
     * @param propertyValue The JSON property value.
     * @return The value.
     */
    @Override
    public Map<String, TestJsonObjectProperty> fromJsonProperty(JsonObjectMap<?> propertyValue) {
      Map<String, TestJsonObjectProperty> map = new HashMap<String, TestJsonObjectProperty>();
      
      for (String key : propertyValue.getPropertyNames()) {
        map.put(key, this.testJsonObjectPropertyAdapter.fromJsonProperty(propertyValue.get(key).cast(TestPropertyJsonObject.class)));
      }
      
      return map;
    }
  }
  
  /**
   * Test JSON Object Property.
   * <p>
   * Created: 13th December 2009.
   *
   * @since json-object-0.2
   */
  public static class TestJsonObjectProperty {
    /** The first property. */
    private final String property1;
    
    /** The second property. */
    private final String property2;

    /**
     * Construct a new TestJsonObjectProperty.
     *
     * @param property1 The first property.
     * @param property2 The second property.
     */
    public TestJsonObjectProperty(String property1, String property2) {
      this.property1 = property1;
      this.property2 = property2;
    }

    /**
     * Retrieve the first property.
     *
     * @return The first property.
     */
    public String getProperty1() {
      return this.property1;
    }

    /**
     * Retrieve the second property.
     *
     * @return The second property.
     */
    public String getProperty2() {
      return this.property2;
    }
    
    /**
     * Determine if this object is equal to the given object.
     *
     * @param object The object.
     * @return TRUE if this object is equal to the given object.
     */
    @Override
    public final boolean equals(Object object) {
      if (object == this) {
        return true;
      }
      else if (object instanceof TestJsonObjectProperty) {
        TestJsonObjectProperty that = (TestJsonObjectProperty)object;
        
        return this.property1.equals(that.property1) &&
               this.property2.equals(that.property2);
      }
      else {
        return false;
      }
    }
    
    /**
     * Calculate the hash code for this object.
     *
     * @return The hash code for this object.
     */
    @Override
    public final int hashCode() {
      int hashCode = 17;
      
      hashCode = hashCode * 37 + this.property1.hashCode();
      hashCode = hashCode * 37 + this.property2.hashCode();
      
      return hashCode;
    }
    
    /**
     * Create a string representation of this object.
     *
     * @return The string representation of this object.
     */
    @Override
    public String toString() {
      return "{property1=" + this.property1 + ",property2=" + this.property2 + "}";
    }
  }
  
  /**
   * Create a JSON object array with the given element class.
   *
   * @param <E> The type of the element of the JSOB object array.
   * @param jsonObjectClass The type of the element of the JSOB object array.
   * @return The JSON object array.
   */
  private static <E extends JsonObject> JsonObjectArray<E> createJsonObjectArray(Class<E> jsonObjectClass) {
    JsonObjectArray<?> jsonObjectArray = JsonObjectFactory.get().createJsonArray(JsonObjectArray.class);
    
    return jsonObjectArray.castElement(jsonObjectClass);
  }

  /**
   * Create a JSON object map with the given element class.
   *
   * @param <E> The type of the element of the JSOB object map
   * @param jsonObjectClass The type of the element of the JSOB object map.
   * @return The JSON object Map.
   */
  private static <E extends JsonObject> JsonObjectMap<E> createJsonObjectMap(Class<E> jsonObjectClass) {
    JsonObjectMap<?> jsonObjectMap = JsonObjectFactory.get().createJsonObject(JsonObjectMap.class);
    
    return jsonObjectMap.castElement(jsonObjectClass);
  }

  /**
   * Test the retrieval of the object value of a property.
   * <p>
   * This test asserts that the retrieved object value of a property matches 
   * the value of the corresponding property of the underlying JSON object.
   */
  @Test
  public void testGetObjectProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    TestPropertyJsonObject testPropertyJsonObject = JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class);
    
    testJsonObject.setObjectProperty("testObjectProperty", testPropertyJsonObject);
    
    assertEquals(testPropertyJsonObject, testJsonObject.getTestObjectProperty());
  }
  
  /**
   * Test the setting of the object value of a property.
   * <p>
   * This test asserts that the setting of the object value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetObjectProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    TestPropertyJsonObject testPropertyJsonObject = JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class);
    
    testJsonObject.setTestObjectProperty(testPropertyJsonObject);
    
    assertEquals(testPropertyJsonObject, testJsonObject.getObjectProperty("testObjectProperty"));
  }
  
  /**
   * Test the retrieval of the value of an adapted object property.
   * <p>
   * This test asserts that the retrieved value of an adapted object property
   * matches the adapted value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testGetAdaptedObjectProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    TestPropertyJsonObject testAdaptedObjectProperty = JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class);
    
    testAdaptedObjectProperty.setProperty1("value1");
    testAdaptedObjectProperty.setProperty2("value2");
    
    testJsonObject.setObjectProperty("testAdaptedObjectProperty", testAdaptedObjectProperty);
    
    assertEquals(new TestJsonObjectProperty("value1", "value2"), testJsonObject.getTestAdaptedObjectProperty());
  }
  
  /**
   * Test the setting of the value of an adapted object property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted object value.
   */
  @Test
  public void testSetAdaptedObjectProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestAdaptedObjectProperty(new TestJsonObjectProperty("value1", "value2"));
    
    TestPropertyJsonObject testAdaptedObjectProperty = testJsonObject.getObjectProperty("testAdaptedObjectProperty", TestPropertyJsonObject.class);
    
    assertEquals("value1", testAdaptedObjectProperty.getProperty1());
    assertEquals("value2", testAdaptedObjectProperty.getProperty2());
  }
  
  /**
   * Test the retrieval of the array value of a property.
   * <p>
   * This test asserts that the retrieved array value of a property matches the
   * value of the corresponding property of the underlying JSON object.
   */
  @Test
  public void testGetArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonObjectArray<JsonObject> testArrayProperty = createJsonObjectArray(JsonObject.class);
    
    testJsonObject.setObjectProperty("testArrayProperty", testArrayProperty);
    
    assertEquals(testArrayProperty, testJsonObject.getTestArrayProperty());
  }
  
  /**
   * Test the setting of the array value of a property.
   * <p>
   * This test asserts that the setting of the array value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonObjectArray<JsonObject> testArrayProperty = createJsonObjectArray(JsonObject.class);
    
    testJsonObject.setTestArrayProperty(testArrayProperty);
    
    assertEquals(testArrayProperty, testJsonObject.getObjectProperty("testArrayProperty"));
  }
  
  /**
   * Test the retrieval of the object array value of a property.
   * <p>
   * This test asserts that the retrieved object array value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetObjectArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonObjectArray<JsonObject> testObjectArrayProperty = createJsonObjectArray(JsonObject.class);
    
    testJsonObject.setObjectProperty("testObjectArrayProperty", testObjectArrayProperty);
    
    assertEquals(testObjectArrayProperty, testJsonObject.getTestObjectArrayProperty());
  }
  
  /**
   * Test the retrieval of the object array value of a property.
   * <p>
   * This test asserts that the retrieved object array value of a non-existent
   * property is <code>null</code>.
   */
  @Test
  public void testGetNullObjectArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertNull(testJsonObject.getTestObjectArrayProperty());
  }

  /**
   * Test the setting of the object array value of a property.
   * <p>
   * This test asserts that the setting of the object array value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetObjectArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonObjectArray<JsonObject> testObjectArrayProperty = createJsonObjectArray(JsonObject.class);
    
    testJsonObject.setTestObjectArrayProperty(testObjectArrayProperty);
    
    assertEquals(testObjectArrayProperty, testJsonObject.getObjectProperty("testObjectArrayProperty"));
  }
  
  /**
   * Test the retrieval of the object array value of a property.
   * <p>
   * This test asserts that the retrieved object array value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetPropertyObjectArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonObjectArray<TestPropertyJsonObject> testPropertyObjectArrayProperty = createJsonObjectArray(TestPropertyJsonObject.class);
    
    testPropertyObjectArrayProperty.set(0, JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class));
    
    testJsonObject.setObjectProperty("testPropertyObjectArrayProperty", testPropertyObjectArrayProperty);
    
    assertEquals(testPropertyObjectArrayProperty, testJsonObject.getTestPropertyObjectArrayProperty());
    assertEquals(testPropertyObjectArrayProperty.get(0).getClass(), testJsonObject.getTestPropertyObjectArrayProperty().get(0).getClass());
  }
  
  /**
   * Test the retrieval of the value of an adapted object array property.
   * <p>
   * This test asserts that the retrieved value of an adapted object array
   * property matches the adapted value of the corresponding property of the
   * underlying JSON object.
   */
  @Test
  public void testGetAdaptedObjectArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    JsonObjectArray<TestPropertyJsonObject> testJsonObjectArray = createJsonObjectArray(TestPropertyJsonObject.class);
    
    for (int i = 0; i < 10; i++) {
      TestPropertyJsonObject testAdaptedObjectProperty = JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class);
      
      testAdaptedObjectProperty.setProperty1("value1." + i);
      testAdaptedObjectProperty.setProperty2("value2." + i);
      
      testJsonObjectArray.set(i, testAdaptedObjectProperty);
    }
    
    testJsonObject.setObjectProperty("testAdaptedObjectArrayProperty", testJsonObjectArray);
    
    List<TestJsonObjectProperty> testAdaptedObjectArrayProperty = testJsonObject.getTestAdaptedObjectArrayProperty();
    
    assertNotNull(testAdaptedObjectArrayProperty);
    assertEquals(testJsonObjectArray.getLength(), testAdaptedObjectArrayProperty.size());
    for (int i = 0; i < testJsonObjectArray.getLength(); i++) {
      assertEquals(new TestJsonObjectPropertyAdapter().fromJsonProperty(testJsonObjectArray.get(i)), testAdaptedObjectArrayProperty.get(i));
    }
  }

  /**
   * Test the setting of the value of an adapted object array property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted object array value.
   */
  @Test
  public void testSetAdaptedObjectArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    List<TestJsonObjectProperty> testAdaptedObjectArrayProperty = new ArrayList<TestJsonObjectProperty>();
    
    for (int i = 0; i < 10; i++) {
      testAdaptedObjectArrayProperty.add(new TestJsonObjectProperty("value1." + i, "value2." + i));
    }
    
    testJsonObject.setTestAdaptedObjectArrayProperty(testAdaptedObjectArrayProperty);
    
    JsonObjectArray<?> jsonObjectArray = testJsonObject.getObjectProperty("testAdaptedObjectArrayProperty", JsonObjectArray.class);
    JsonObjectArray<TestPropertyJsonObject> testJsonObjectArray = jsonObjectArray.castElement(TestPropertyJsonObject.class);
    
    assertNotNull(testJsonObjectArray);
    assertEquals(testAdaptedObjectArrayProperty.size(), testJsonObjectArray.getLength());
    for (int i = 0; i < testAdaptedObjectArrayProperty.size(); i++) {
      TestPropertyJsonObject testPropertyJsonObject = testJsonObjectArray.get(i);
      
      assertEquals(testAdaptedObjectArrayProperty.get(i).getProperty1(), testPropertyJsonObject.getProperty1());
      assertEquals(testAdaptedObjectArrayProperty.get(i).getProperty2(), testPropertyJsonObject.getProperty2());
    }
  }

  /**
   * Test the retrieval of the object map value of a property.
   * <p>
   * This test asserts that the retrieved object map value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetObjectMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonObjectMap<JsonObject> testObjectMapProperty = createJsonObjectMap(JsonObject.class);
    
    testJsonObject.setObjectProperty("testObjectMapProperty", testObjectMapProperty);
    
    assertEquals(testObjectMapProperty, testJsonObject.getTestObjectMapProperty());
  }
  
  /**
   * Test the retrieval of the object map value of a property.
   * <p>
   * This test asserts that the retrieved object map value of a non-existent
   * property is <code>null</code>
   */
  @Test
  public void testGetNullObjectMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertNull(testJsonObject.getTestObjectMapProperty());
  }
  
  /**
   * Test the setting of the object map value of a property.
   * <p>
   * This test asserts that the setting of the object map value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetObjectMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonObjectMap<JsonObject> testObjectMapProperty = createJsonObjectMap(JsonObject.class);
    
    testJsonObject.setTestObjectMapProperty(testObjectMapProperty);
    
    assertEquals(testObjectMapProperty, testJsonObject.getObjectProperty("testObjectMapProperty"));
  }
  
  /**
   * Test the retrieval of the object map value of a property.
   * <p>
   * This test asserts that the retrieved object map value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetPropertyObjectMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonObjectMap<TestPropertyJsonObject> testPropertyObjectMapProperty = createJsonObjectMap(TestPropertyJsonObject.class);
    
    testPropertyObjectMapProperty.set("key", JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class));
    
    testJsonObject.setObjectProperty("testPropertyObjectMapProperty", testPropertyObjectMapProperty);
    
    assertEquals(testPropertyObjectMapProperty, testJsonObject.getTestPropertyObjectMapProperty());
    assertEquals(testPropertyObjectMapProperty.get("key").getClass(), testJsonObject.getTestPropertyObjectMapProperty().get("key").getClass());
  }
  
  /**
   * Test the retrieval of the value of an adapted object map property.
   * <p>
   * This test asserts that the retrieved value of an adapted object map
   * property matches the adapted value of the corresponding property of the
   * underlying JSON object.
   */
  @Test
  public void testGetAdaptedObjectMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    JsonObjectMap<TestPropertyJsonObject> testJsonObjectMap = createJsonObjectMap(TestPropertyJsonObject.class);
    
    for (int i = 0; i < 10; i++) {
      TestPropertyJsonObject testAdaptedObjectProperty = JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class);
      
      testAdaptedObjectProperty.setProperty1("value1." + i);
      testAdaptedObjectProperty.setProperty2("value2." + i);
      
      testJsonObjectMap.set(Integer.toString(i), testAdaptedObjectProperty);
    }
    
    testJsonObject.setObjectProperty("testAdaptedObjectMapProperty", testJsonObjectMap);
    
    Map<String, TestJsonObjectProperty> testAdaptedObjectMapProperty = testJsonObject.getTestAdaptedObjectMapProperty();
    
    assertNotNull(testAdaptedObjectMapProperty);
    assertEquals(testJsonObjectMap.getPropertyNames().size(), testAdaptedObjectMapProperty.size());
    for (String key : testJsonObjectMap.getPropertyNames()) {
      assertEquals(new TestJsonObjectPropertyAdapter().fromJsonProperty(testJsonObjectMap.get(key)), testAdaptedObjectMapProperty.get(key));
    }
  }

  /**
   * Test the setting of the value of an adapted object map property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted object map value.
   */
  @Test
  public void testSetAdaptedObjectMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    Map<String, TestJsonObjectProperty> testAdaptedObjectMapProperty = new HashMap<String, TestJsonObjectProperty>();
    
    for (int i = 0; i < 10; i++) {
      testAdaptedObjectMapProperty.put(Integer.toString(i), new TestJsonObjectProperty("value1." + i, "value2." + i));
    }
    
    testJsonObject.setTestAdaptedObjectMapProperty(testAdaptedObjectMapProperty);
    
    JsonObjectMap<?> jsonObjectMap = testJsonObject.getObjectProperty("testAdaptedObjectMapProperty", JsonObjectMap.class);
    JsonObjectMap<TestPropertyJsonObject> testJsonObjectMap = jsonObjectMap.castElement(TestPropertyJsonObject.class);
    
    assertNotNull(testJsonObjectMap);
    assertEquals(testAdaptedObjectMapProperty.size(), testJsonObjectMap.getPropertyNames().size());
    for (Map.Entry<String, TestJsonObjectProperty> entry : testAdaptedObjectMapProperty.entrySet()) {
      TestPropertyJsonObject testPropertyJsonObject = testJsonObjectMap.get(entry.getKey());
      
      assertEquals(testAdaptedObjectMapProperty.get(entry.getKey()).getProperty1(), testPropertyJsonObject.getProperty1());
      assertEquals(testAdaptedObjectMapProperty.get(entry.getKey()).getProperty2(), testPropertyJsonObject.getProperty2());
    }
  }
}
