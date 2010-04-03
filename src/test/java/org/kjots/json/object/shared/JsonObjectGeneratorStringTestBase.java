/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.shared;

import static junit.framework.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import org.kjots.json.object.shared.JsonProperty.OperationType;

/**
 * JSON Object Generator String Test Base.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
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
     * Convert to a JSON string property value.
     *
     * @param date The value.
     * @return The JSON string property value.
     * @see #fromJsonProperty(String)
     */
    @Override
    @SuppressWarnings("deprecation")
    public String toJsonProperty(Date date) {
      return date.toGMTString();
    }
    
    /**
     * Convert from a JSON string property value.
     *
     * @param propertyValue The JSON string property value.
     * @return The value.
     * @see #toJsonProperty(Date)
     */
    @Override
    @SuppressWarnings("deprecation")
    public Date fromJsonProperty(String propertyValue) {
      return new Date(Date.parse(propertyValue));
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
}
