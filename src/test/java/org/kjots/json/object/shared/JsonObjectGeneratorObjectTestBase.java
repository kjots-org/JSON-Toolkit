/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.shared;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import org.kjots.json.object.shared.JsonProperty.OperationType;

/**
 * JSON Object Generator Object Test Base.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
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
     * Convert to a JSON object property value.
     *
     * @param testJsonObjectProperty The value.
     * @return The JSON object property value.
     * @see #fromJsonProperty(JsonObject)
     */
    @Override
    public TestPropertyJsonObject toJsonProperty(TestJsonObjectProperty testJsonObjectProperty) {
      TestPropertyJsonObject testPropertyJsonObject = JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class);
      
      testPropertyJsonObject.setProperty1(testJsonObjectProperty.getProperty1());
      testPropertyJsonObject.setProperty2(testJsonObjectProperty.getProperty2());
      
      return testPropertyJsonObject;
    }
    
    /**
     * Convert from a JSON object property value.
     *
     * @param propertyValue The JSON object property value.
     * @return The value.
     * @see #toJsonProperty(Object)
     */
    @Override
    public TestJsonObjectProperty fromJsonProperty(TestPropertyJsonObject propertyValue) {
      return new TestJsonObjectProperty(propertyValue.getProperty1(), propertyValue.getProperty2());
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
    JsonObjectArray<JsonObject> testArrayProperty = this.createJsonObjectArray(JsonObject.class);
    
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
    JsonObjectArray<JsonObject> testArrayProperty = this.createJsonObjectArray(JsonObject.class);
    
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
    JsonObjectArray<JsonObject> testObjectArrayProperty = this.createJsonObjectArray(JsonObject.class);
    
    testJsonObject.setObjectProperty("testObjectArrayProperty", testObjectArrayProperty);
    
    assertEquals(testObjectArrayProperty, testJsonObject.getTestObjectArrayProperty());
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
    JsonObjectArray<JsonObject> testObjectArrayProperty = this.createJsonObjectArray(JsonObject.class);
    
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
    JsonObjectArray<TestPropertyJsonObject> testPropertyObjectArrayProperty = this.createJsonObjectArray(TestPropertyJsonObject.class);
    
    testPropertyObjectArrayProperty.set(0, JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class));
    
    testJsonObject.setObjectProperty("testPropertyObjectArrayProperty", testPropertyObjectArrayProperty);
    
    assertEquals(testPropertyObjectArrayProperty, testJsonObject.getTestPropertyObjectArrayProperty());
    assertEquals(testPropertyObjectArrayProperty.get(0).getClass(), testJsonObject.getTestPropertyObjectArrayProperty().get(0).getClass());
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
    JsonObjectMap<JsonObject> testObjectMapProperty = this.createJsonObjectMap(JsonObject.class);
    
    testJsonObject.setObjectProperty("testObjectMapProperty", testObjectMapProperty);
    
    assertEquals(testObjectMapProperty, testJsonObject.getTestObjectMapProperty());
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
    JsonObjectMap<JsonObject> testObjectMapProperty = this.createJsonObjectMap(JsonObject.class);
    
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
    JsonObjectMap<TestPropertyJsonObject> testPropertyObjectMapProperty = this.createJsonObjectMap(TestPropertyJsonObject.class);
    
    testPropertyObjectMapProperty.set("key", JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class));
    
    testJsonObject.setObjectProperty("testPropertyObjectMapProperty", testPropertyObjectMapProperty);
    
    assertEquals(testPropertyObjectMapProperty, testJsonObject.getTestPropertyObjectMapProperty());
    assertEquals(testPropertyObjectMapProperty.get("key").getClass(), testJsonObject.getTestPropertyObjectMapProperty().get("key").getClass());
  }

  /**
   * Create a JSON object array with the given element class.
   *
   * @param <E> The type of the element of the JSOB object array.
   * @param jsonObjectClass The type of the element of the JSOB object array.
   * @return The JSON object array.
   */
  private <E extends JsonObject> JsonObjectArray<E> createJsonObjectArray(Class<E> jsonObjectClass) {
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
  private <E extends JsonObject> JsonObjectMap<E> createJsonObjectMap(Class<E> jsonObjectClass) {
    JsonObjectMap<?> jsonObjectMap = JsonObjectFactory.get().createJsonObject(JsonObjectMap.class);
    
    return jsonObjectMap.castElement(jsonObjectClass);
  }
}
