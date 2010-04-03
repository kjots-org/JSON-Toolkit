/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.shared;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import org.kjots.json.object.shared.JsonProperty.OperationType;

/**
 * JSON Object Generator Number Test Base.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
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
     * Convert to a JSON number property value.
     *
     * @param string The value.
     * @return The JSON number property value.
     * @see #fromJsonProperty(Number)
     */
    @Override
    public Number toJsonProperty(String string) {
      return Double.valueOf(Double.parseDouble(string));
    }
    
    /**
     * Convert from a JSON number property value.
     *
     * @param propertyValue The JSON number property value.
     * @return The value.
     * @see #toJsonProperty(String)
     */
    @Override
    public String fromJsonProperty(Number propertyValue) {
      return propertyValue.toString();
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
}
