/* 
 * Copyright © 2011 Karl J. Ots <kjots@kjots.org>
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
import org.kjots.json.object.shared.JsonPropertyAdapter.AutoAdaptedType;

/**
 * JSON Object Generator Auto-Adapted Property Test Base.
 * <p>
 * Created: 23rd February 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.1
 */
public class JsonObjectGeneratorAutoAdaptedPropertyTestBase {
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject {
    /**
     * Retrieve the test auto-adapted property.
     *
     * @return The test auto-adapted property.
     * @see #setTestAutoAdaptedProperty(TestAutoAdaptedPropertyType)
     */
    @JsonProperty(name = "testAutoAdaptedProperty", operation = OperationType.GET)
    public TestAutoAdaptedPropertyType getTestAutoAdaptedProperty();
    
    /**
     * Set the test auto-adapted property.
     *
     * @param testAutoAdaptedProperty The test auto-adapted property.
     * @see #getTestAutoAdaptedProperty()
     */
    @JsonProperty(name = "testAutoAdaptedProperty", operation = OperationType.SET)
    public void setTestAutoAdaptedProperty(TestAutoAdaptedPropertyType testAutoAdaptedProperty);
  }
  
  /**
   * Test Auto-Adapted Property Type.
   */
  @AutoAdaptedType(TestAutoAdaptedPropertyType.JsonPropertyAdapter.class)
  public static class TestAutoAdaptedPropertyType {
    /**
     * Test Auto-Adapted Property Type JSON Property Adapter.
     */
    public static class JsonPropertyAdapter implements JsonStringPropertyAdapter<TestAutoAdaptedPropertyType> {
      /**
       * Convert to a JSON property value.
       *
       * @param value The value.
       * @return The JSON property value.
       * @see #fromJsonProperty(String)
       */
      @Override
      public String toJsonProperty(TestAutoAdaptedPropertyType value) {
        return value != null ? value.getStringValue() : null;
      }
      
      /**
       * Convert from a JSON property value.
       *
       * @param propertyValue The JSON property value.
       * @return The value.
       * @see #toJsonProperty(TestAutoAdaptedPropertyType)
       */
      @Override
      public TestAutoAdaptedPropertyType fromJsonProperty(String propertyValue) {
        return propertyValue != null ? new TestAutoAdaptedPropertyType(propertyValue) : null;
      }
    }
    
    /** The string value. */
    private final String stringValue;

    /**
     * Construct a new Test Auto-Adapted Property Type.
     *
     * @param stringValue The string value.
     */
    public TestAutoAdaptedPropertyType(String stringValue) {
      this.stringValue = stringValue;
    }
    
    /**
     * Retrieve the string value.
     *
     * @return The string value.
     */
    public String getStringValue() {
      return this.stringValue;
    }
  }
  
  /**
   * Test the retrieval of the value of an auto-adapted property.
   * <p>
   * This test asserts that the retrieved value of a auto-adapted property
   * matches the adapted value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testGetAutoAdaptedProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setStringProperty("testAutoAdaptedProperty", "Test Auto-Adapted Property Value");
    
    assertEquals("Test Auto-Adapted Property Value", testJsonObject.getTestAutoAdaptedProperty().getStringValue());
  }
  
  /**
   * Test the setting of the value of an auto-adapted property.
   * <p>
   * This test asserts that the setting of the value of an auto-adapted
   * property changes the value of the corresponding property of the underlying
   * JSON object to the adapted value.
   */
  @Test
  public void testSetAutoAdaptedProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestAutoAdaptedProperty(new TestAutoAdaptedPropertyType("Test Auto-Adapted Property Value"));
    
    assertEquals("Test Auto-Adapted Property Value", testJsonObject.getStringProperty("testAutoAdaptedProperty"));
  }
}
