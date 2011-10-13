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
package org.kjots.json.object.ntive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import org.kjots.json.object.shared.JsonBooleanPropertyAdapter;

/**
 * Native JSON Object Boolean Test.
 * <p>
 * Created: 12th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class NativeJsonObjectBooleanTest {
  /**
   * Test Native JSON Object.
   */
  public static class TestNativeJsonObject extends NativeJsonObject {
    /** The test boolean property.*/
    @NativeJsonProperty
    private Boolean testBooleanProperty;
    
    /** The test adapted boolean property.*/
    @NativeJsonProperty(adapter = TestJsonBooleanPropertyAdapter.class)
    private String testAdaptedBooleanProperty;
  }
  
  /**
   * Test JSON Boolean Property Adapter
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
  
  /** The test native JSON object. */
  private TestNativeJsonObject testNativeJsonObject;
  
  /**
   * Setup the test environment.
   */
  @Before
  public void setup() {
    this.testNativeJsonObject = new TestNativeJsonObject();
  }
  
  /**
   * Test the determination of a boolean value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has a boolean value.
   */
  @Test
  public void testIsBooleanProperty() {
    assertFalse("testNativeJsonObject.isBooleanProperty(\"testBooleanProperty\") != false", testNativeJsonObject.isBooleanProperty("testBooleanProperty"));
    
    testNativeJsonObject.testBooleanProperty = null;
    testNativeJsonObject.setHasProperty("testBooleanProperty");
    
    assertFalse("testNativeJsonObject.isBooleanProperty(\"testBooleanProperty\") != false", testNativeJsonObject.isBooleanProperty("testBooleanProperty"));
    
    testNativeJsonObject.testBooleanProperty = Boolean.TRUE;
    
    assertTrue("testNativeJsonObject.isBooleanProperty(\"testBooleanProperty\") != true", testNativeJsonObject.isBooleanProperty("testBooleanProperty"));
  }
  
  /**
   * Test the retrieval of the boolean value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a boolean property.
   */
  @Test
  public void testGetBooleanProperty() {
    testNativeJsonObject.testBooleanProperty = Boolean.TRUE;
    testNativeJsonObject.setHasProperty("testBooleanProperty");
    
    assertEquals(true, testNativeJsonObject.getBooleanProperty("testBooleanProperty").booleanValue());
  }
  
  /**
   * Test the setting of the boolean value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a boolean property.
   */
  @Test
  public void testSetBooleanProperty() {
    testNativeJsonObject.setBooleanProperty("testBooleanProperty", Boolean.TRUE);
    
    assertEquals(true, testNativeJsonObject.testBooleanProperty.booleanValue());
    assertTrue("testNativeJsonObject.hasProperty(\"testBooleanProperty\") != true", testNativeJsonObject.hasProperty("testBooleanProperty"));
  }
  
  /**
   * Test the determination of an adapted boolean value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has an adapted boolean value.
   */
  @Test
  public void testIsAdaptedBooleanProperty() {
    assertFalse("testNativeJsonObject.isBooleanProperty(\"testAdaptedBooleanProperty\") != false", testNativeJsonObject.isBooleanProperty("testAdaptedBooleanProperty"));
    
    testNativeJsonObject.testAdaptedBooleanProperty = null;
    testNativeJsonObject.setHasProperty("testAdaptedBooleanProperty");
    
    assertFalse("testNativeJsonObject.isBooleanProperty(\"testAdaptedBooleanProperty\") != false", testNativeJsonObject.isBooleanProperty("testAdaptedBooleanProperty"));
    
    testNativeJsonObject.testAdaptedBooleanProperty = new TestJsonBooleanPropertyAdapter().fromJsonProperty(true);
    
    assertTrue("testNativeJsonObject.isBooleanProperty(\"testAdaptedBooleanProperty\") != true", testNativeJsonObject.isBooleanProperty("testAdaptedBooleanProperty"));
  }
  
  /**
   * Test the retrieval of the value of an adapted boolean property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of adapted boolean property.
   */
  @Test
  public void testGetAdaptedBooleanProperty() {
    testNativeJsonObject.testAdaptedBooleanProperty = new TestJsonBooleanPropertyAdapter().fromJsonProperty(true);
    testNativeJsonObject.setHasProperty("testAdaptedBooleanProperty");
    
    assertEquals(true, testNativeJsonObject.getBooleanProperty("testAdaptedBooleanProperty").booleanValue());
  }

  /**
   * Test the setting of the value of an adapted boolean property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * an adapted boolean property.
   */
  @Test
  public void testSetAdaptedBooleanProperty() {
    testNativeJsonObject.setBooleanProperty("testAdaptedBooleanProperty", true);
    
    assertEquals(new TestJsonBooleanPropertyAdapter().fromJsonProperty(true), testNativeJsonObject.testAdaptedBooleanProperty);
    assertTrue("testNativeJsonObject.hasProperty(\"testAdaptedBooleanProperty\") != true", testNativeJsonObject.hasProperty("testAdaptedBooleanProperty"));
  }
}
