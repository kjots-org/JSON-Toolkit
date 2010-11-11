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

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kjots.json.object.shared.JsonStringPropertyAdapter;

/**
 * Native JSON Object String Test.
 * <p>
 * Created: 12th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonObjectStringTest {
  /**
   * Test Native JSON Object.
   */
  public static class TestNativeJsonObject extends NativeJsonObject {
    /** The test string property.*/
    @NativeJsonProperty
    private String testStringProperty;
    
    /** The test adapted string property.*/
    @NativeJsonProperty(adapter = TestJsonStringPropertyAdapter.class)
    private Date testAdaptedStringProperty;
  }
  
  /**
   * Test JSON String Property Adapter
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
   * Test the determination of a string value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has a string value.
   */
  @Test
  public void testIsStringProperty() {
    assertFalse("testNativeJsonObject.isStringProperty(\"testStringProperty\") != false", testNativeJsonObject.isStringProperty("testStringProperty"));
    
    testNativeJsonObject.testStringProperty = null;
    testNativeJsonObject.setHasProperty("testStringProperty");
    
    assertFalse("testNativeJsonObject.isStringProperty(\"testStringProperty\") != false", testNativeJsonObject.isStringProperty("testStringProperty"));
    
    testNativeJsonObject.testStringProperty = "Test String Property Value";
    
    assertTrue("testNativeJsonObject.isStringProperty(\"testStringProperty\") != true", testNativeJsonObject.isStringProperty("testStringProperty"));
  }
  
  /**
   * Test the retrieval of the string value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a string property.
   */
  @Test
  public void testGetStringProperty() {
    testNativeJsonObject.testStringProperty = "Test String Property Value";
    testNativeJsonObject.setHasProperty("testStringProperty");
    
    assertEquals("Test String Property Value", testNativeJsonObject.getStringProperty("testStringProperty"));
  }
  
  /**
   * Test the setting of the string value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a string property.
   */
  @Test
  public void testSetStringProperty() {
    testNativeJsonObject.setStringProperty("testStringProperty", "Test String Property Value");
    
    assertEquals("Test String Property Value", testNativeJsonObject.testStringProperty);
    assertTrue("testNativeJsonObject.hasProperty(\"testStringProperty\") != true", testNativeJsonObject.hasProperty("testStringProperty"));
  }
  
  /**
   * Test the determination of an adapted string value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has an adapted string value.
   */
  @Test
  public void testIsAdaptedStringProperty() {
    assertFalse("testNativeJsonObject.isStringProperty(\"testAdaptedStringProperty\") != false", testNativeJsonObject.isStringProperty("testAdaptedStringProperty"));
    
    testNativeJsonObject.testAdaptedStringProperty = null;
    testNativeJsonObject.setHasProperty("testAdaptedStringProperty");
    
    assertFalse("testNativeJsonObject.isStringProperty(\"testAdaptedStringProperty\") != false", testNativeJsonObject.isStringProperty("testAdaptedStringProperty"));
    
    testNativeJsonObject.testAdaptedStringProperty = new TestJsonStringPropertyAdapter().fromJsonProperty("13 Dec 2009 02:00:00 GMT");
    
    assertTrue("testNativeJsonObject.isStringProperty(\"testAdaptedStringProperty\") != true", testNativeJsonObject.isStringProperty("testAdaptedStringProperty"));
  }
  
  /**
   * Test the retrieval of the value of an adapted string property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of adapted string property.
   */
  @Test
  public void testGetAdaptedStringProperty() {
    testNativeJsonObject.testAdaptedStringProperty = new TestJsonStringPropertyAdapter().fromJsonProperty("13 Dec 2009 02:00:00 GMT");
    testNativeJsonObject.setHasProperty("testAdaptedStringProperty");
    
    assertEquals("13 Dec 2009 02:00:00 GMT", testNativeJsonObject.getStringProperty("testAdaptedStringProperty"));
  }

  /**
   * Test the setting of the value of an adapted string property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * an adapted string property.
   */
  @Test
  public void testSetAdaptedStringProperty() {
    testNativeJsonObject.setStringProperty("testAdaptedStringProperty", "13 Dec 2009 02:00:00 GMT");
    
    assertEquals(new TestJsonStringPropertyAdapter().fromJsonProperty("13 Dec 2009 02:00:00 GMT"), testNativeJsonObject.testAdaptedStringProperty);
    assertTrue("testNativeJsonObject.hasProperty(\"testAdaptedStringProperty\") != true", testNativeJsonObject.hasProperty("testAdaptedStringProperty"));
  }
}
