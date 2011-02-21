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

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.shared.JsonObjectPropertyAdapter;
import org.kjots.json.object.shared.JsonProperty;
import org.kjots.json.object.shared.JsonProperty.OperationType;
import org.kjots.json.object.simple.SimpleJsonObjectModule;

/**
 * Native JSON Object Object Test.
 * <p>
 * Created: 12th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class NativeJsonObjectObjectTest {
  /**
   * Test Property JSON Object.
   */
  public static interface TestPropertyJsonObject extends JsonObject {
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
   * Test Native JSON Object.
   */
  public static class TestNativeJsonObject extends NativeJsonObject {
    /** The test object property.*/
    @NativeJsonProperty
    private JsonObject testObjectProperty;
    
    /** The test adapted object property.*/
    @NativeJsonProperty(adapter = TestJsonObjectPropertyAdapter.class)
    private TestJsonObjectProperty testAdaptedObjectProperty;
  }
  
  /**
   * Test JSON Object Property Adapter
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
   * Test JSON Object Property.
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
 
  /** The test native JSON object. */
  private TestNativeJsonObject testNativeJsonObject;
  
  /**
   * Setup the test class.
   */
  @BeforeClass
  public static void setupClass() {
    Guice.createInjector(new SimpleJsonObjectModule());
  }
  
  /**
   * Setup the test environment.
   */
  @Before
  public void setup() {
    this.testNativeJsonObject = new TestNativeJsonObject();
  }
  
  /**
   * Test the determination of a object value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has a object value.
   */
  @Test
  public void testIsObjectProperty() {
    assertFalse("testNativeJsonObject.isObjectProperty(\"testObjectProperty\") != false", testNativeJsonObject.isObjectProperty("testObjectProperty"));
    
    testNativeJsonObject.testObjectProperty = null;
    testNativeJsonObject.setHasProperty("testObjectProperty");
    
    assertFalse("testNativeJsonObject.isObjectProperty(\"testObjectProperty\") != false", testNativeJsonObject.isObjectProperty("testObjectProperty"));
    
    testNativeJsonObject.testObjectProperty = JsonObjectFactory.get().createJsonObject();
    
    assertTrue("testNativeJsonObject.isObjectProperty(\"testObjectProperty\") != true", testNativeJsonObject.isObjectProperty("testObjectProperty"));
  }
  
  /**
   * Test the retrieval of the object value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a object property.
   */
  @Test
  public void testGetObjectProperty() {
    JsonObject jsonObject = JsonObjectFactory.get().createJsonObject();
    
    testNativeJsonObject.testObjectProperty = jsonObject;
    testNativeJsonObject.setHasProperty("testObjectProperty");
    
    assertEquals(jsonObject, testNativeJsonObject.getObjectProperty("testObjectProperty"));
  }
  
  /**
   * Test the retrieval of the object value of a property with a class.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a object property, and that the class of the retrieved object
   * value matches the native JSON object class provided to the retrieval
   * method.
   */
  @Test
  public void testGetObjectPropertyWithNativeClass() {
    JsonObject jsonObject = new TestNativeJsonObject();
    
    testNativeJsonObject.testObjectProperty = jsonObject;
    testNativeJsonObject.setHasProperty("testObjectProperty");
    
    TestNativeJsonObject jsonObjectPropertyValue = testNativeJsonObject.getObjectProperty("testObjectProperty", TestNativeJsonObject.class);
    
    assertEquals(jsonObject, jsonObjectPropertyValue);
  }
  
  /**
   * Test the retrieval of the object value of a property with a class.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a object property, and that the class of the retrieved object
   * value matches the non-native JSON object class provided to the retrieval
   * method.
   */
  @Test
  public void testGetObjectPropertyWithNonNativeClass() {
    JsonObject jsonObject = JsonObjectFactory.get().createJsonObject();
    
    testNativeJsonObject.testObjectProperty = jsonObject;
    testNativeJsonObject.setHasProperty("testObjectProperty");
    
    TestPropertyJsonObject jsonObjectPropertyValue = testNativeJsonObject.getObjectProperty("testObjectProperty", TestPropertyJsonObject.class);
    
    assertEquals(jsonObject, jsonObjectPropertyValue);
  }
  
  /**
   * Test the setting of the object value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a object property.
   */
  @Test
  public void testSetObjectProperty() {
    JsonObject jsonObject = JsonObjectFactory.get().createJsonObject();
    
    testNativeJsonObject.setObjectProperty("testObjectProperty", jsonObject);
    
    assertEquals(jsonObject, testNativeJsonObject.testObjectProperty);
    assertTrue("testNativeJsonObject.hasProperty(\"testObjectProperty\") != true", testNativeJsonObject.hasProperty("testObjectProperty"));
  }
  
  /**
   * Test the determination of an adapted object value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has an adapted object value.
   */
  @Test
  public void testIsAdaptedObjectProperty() {
    assertFalse("testNativeJsonObject.isObjectProperty(\"testAdaptedObjectProperty\") != false", testNativeJsonObject.isObjectProperty("testAdaptedObjectProperty"));
    
    testNativeJsonObject.testAdaptedObjectProperty = null;
    testNativeJsonObject.setHasProperty("testAdaptedObjectProperty");
    
    assertFalse("testNativeJsonObject.isObjectProperty(\"testAdaptedObjectProperty\") != false", testNativeJsonObject.isObjectProperty("testAdaptedObjectProperty"));
    
    testNativeJsonObject.testAdaptedObjectProperty = new TestJsonObjectProperty("value1", "value2");
    
    assertTrue("testNativeJsonObject.isObjectProperty(\"testAdaptedObjectProperty\") != true", testNativeJsonObject.isObjectProperty("testAdaptedObjectProperty"));
  }
  
  /**
   * Test the retrieval of the value of an adapted object property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of adapted object property.
   */
  @Test
  public void testGetAdaptedObjectProperty() {
    testNativeJsonObject.testAdaptedObjectProperty = new TestJsonObjectProperty("value1", "value2");
    testNativeJsonObject.setHasProperty("testAdaptedObjectProperty");
    
    TestPropertyJsonObject testAdaptedObjectProperty = testNativeJsonObject.getObjectProperty("testAdaptedObjectProperty", TestPropertyJsonObject.class);
    
    assertEquals("value1", testAdaptedObjectProperty.getProperty1());
    assertEquals("value2", testAdaptedObjectProperty.getProperty2());
  }

  /**
   * Test the setting of the value of an adapted object property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * an adapted object property.
   */
  @Test
  public void testSetAdaptedObjectProperty() {
    TestPropertyJsonObject testAdaptedObjectProperty = JsonObjectFactory.get().createJsonObject(TestPropertyJsonObject.class);
    
    testAdaptedObjectProperty.setProperty1("value1");
    testAdaptedObjectProperty.setProperty2("value2");
    
    testNativeJsonObject.setObjectProperty("testAdaptedObjectProperty", testAdaptedObjectProperty);
    
    assertEquals(new TestJsonObjectProperty("value1", "value2"), testNativeJsonObject.testAdaptedObjectProperty);
    assertTrue("testNativeJsonObject.hasProperty(\"testAdaptedObjectProperty\") != true", testNativeJsonObject.hasProperty("testAdaptedObjectProperty"));
  }
}
