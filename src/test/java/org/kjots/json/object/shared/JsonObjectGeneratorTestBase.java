/* 
 * Copyright © 2009 Karl J. Ots <kjots@kjots.org>
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
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonBooleanArray;
import org.kjots.json.object.shared.JsonBooleanMap;
import org.kjots.json.object.shared.JsonBooleanPropertyAdapter;
import org.kjots.json.object.shared.JsonNumberArray;
import org.kjots.json.object.shared.JsonNumberMap;
import org.kjots.json.object.shared.JsonNumberPropertyAdapter;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectArray;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.shared.JsonObjectMap;
import org.kjots.json.object.shared.JsonObjectPropertyAdapter;
import org.kjots.json.object.shared.JsonProperty;
import org.kjots.json.object.shared.JsonStringArray;
import org.kjots.json.object.shared.JsonStringMap;
import org.kjots.json.object.shared.JsonStringPropertyAdapter;
import org.kjots.json.object.shared.JsonProperty.OperationType;

/**
 * JSON Object Generator Test Base.
 * <p>
 * Created: 11th December 2009.
 *
 * @param <J> The type of the underlying JSON object.
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public abstract class JsonObjectGeneratorTestBase {
  /**
   * Test Base JSON Object.
   */
  public static interface TestBaseJsonObject extends JsonObject {
  }
  
  /**
   * Test Property JSON Object.
   * <p>
   * Created: 7th December 2009.
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
   * Test JSON Object.
   */
  public static interface TestJsonObject extends TestBaseJsonObject {
    /**
     * Determine if the test property exists.
     *
     * @return <code>true</code> if the test property exists.
     */
    @JsonProperty(name = "testProperty", operation = OperationType.HAS)
    public boolean hasTestProperty();
    
    /**
     * Determine if the test property is <code>null</code>.
     *
     * @return <code>true</code> if the test property is <code>null</code>.
     */
    @JsonProperty(name = "testProperty", operation = OperationType.IS_NULL)
    public boolean isTestPropertyNull();
    
    /**
     * Delete the test property.
     *
     * @return <code>true</code> if the test property existed.
     */
    @JsonProperty(name = "testProperty", operation = OperationType.DELETE)
    public boolean deleteTestProperty();
    
    /**
     * Retrieve the test boolean property.
     *
     * @return The test boolean property.
     * @see #setTestBooleanProperty(Boolean)
     */
    @JsonProperty(name = "testBooleanProperty", operation = OperationType.GET)
    public Boolean getTestBooleanProperty();
    
    /**
     * Set the test boolean property.
     *
     * @param testBooleanProperty The test boolean property.
     * @see #getTestBooleanProperty()
     */
    @JsonProperty(name = "testBooleanProperty", operation = OperationType.SET)
    public void setTestBooleanProperty(Boolean testBooleanProperty);
    
    /**
     * Retrieve the test adapted boolean property.
     * 
     * @return The test adapted boolean property.
     * @see #setTestAdaptedBooleanProperty(String)
     */
    @JsonProperty(name = "testAdaptedBooleanProperty", operation = OperationType.GET, adapter = TestJsonBooleanPropertyAdapter.class)
    public String getTestAdaptedBooleanProperty();
    
    /**
     * Set the test adapted boolean property.
     * 
     * @param testAdaptedBooleanProperty The test adapted boolean property.
     * @see #getTestAdaptedBooleanProperty()
     */
    @JsonProperty(name = "testAdaptedBooleanProperty", operation = OperationType.SET, adapter = TestJsonBooleanPropertyAdapter.class)
    public void setTestAdaptedBooleanProperty(String testAdaptedBooleanProperty);
    
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
     * Retrieve the test boolean primitive property.
     *
     * @return The test boolean primitive property.
     * @see #setTestBooleanPrimitiveProperty(boolean)
     */
    @JsonProperty(name = "testBooleanPrimitiveProperty", operation = OperationType.GET)
    public boolean getTestBooleanPrimitiveProperty();
    
    /**
     * Set the test boolean primitive property.
     *
     * @param testBooleanPrimitiveProperty The test boolean primitive property.
     * @see #getTestBooleanPrimitiveProperty()
     */
    @JsonProperty(name = "testBooleanPrimitiveProperty", operation = OperationType.SET)
    public void setTestBooleanPrimitiveProperty(boolean testBooleanPrimitiveProperty);
    
    /**
     * Retrieve the test integer primitive property.
     *
     * @return The test integer primitive property.
     * @see #setTestIntegerPrimitiveProperty(int)
     */
    @JsonProperty(name = "testIntegerPrimitiveProperty", operation = OperationType.GET)
    public int getTestIntegerPrimitiveProperty();
    
    /**
     * Set the test integer primitive property.
     *
     * @param testIntegerPrimitiveProperty The test integer primitive property.
     * @see #getTestIntegerPrimitiveProperty()
     */
    @JsonProperty(name = "testIntegerPrimitiveProperty", operation = OperationType.SET)
    public void setTestIntegerPrimitiveProperty(int testIntegerPrimitiveProperty);
    
    /**
     * Retrieve the test double primitive property.
     *
     * @return The test double primitive property.
     * @see #setTestDoublePrimitiveProperty(double)
     */
    @JsonProperty(name = "testDoublePrimitiveProperty", operation = OperationType.GET)
    public double getTestDoublePrimitiveProperty();
    
    /**
     * Set the test double primitive property.
     *
     * @param testDoublePrimitiveProperty The test double primitive property.
     * @see #getTestDoublePrimitiveProperty()
     */
    @JsonProperty(name = "testDoublePrimitiveProperty", operation = OperationType.SET)
    public void setTestDoublePrimitiveProperty(double testDoublePrimitiveProperty);
    
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
     * Retrieve the test boolean array property.
     *
     * @return The test boolean array property.
     * @see #setTestBooleanArrayProperty(JsonBooleanArray)
     */
    @JsonProperty(name = "testBooleanArrayProperty", operation = OperationType.GET)
    public JsonBooleanArray getTestBooleanArrayProperty();
    
    /**
     * Set the test boolean array property.
     *
     * @param testBooleanArrayProperty The test boolean array property.
     * @see #getTestBooleanArrayProperty()
     */
    @JsonProperty(name = "testBooleanArrayProperty", operation = OperationType.SET)
    public void setTestBooleanArrayProperty(JsonBooleanArray testBooleanArrayProperty);
    
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
     * Retrieve the test boolean map property.
     *
     * @return The test boolean map property.
     * @see #setTestBooleanMapProperty(JsonBooleanMap)
     */
    @JsonProperty(name = "testBooleanMapProperty", operation = OperationType.GET)
    public JsonBooleanMap getTestBooleanMapProperty();
    
    /**
     * Set the test boolean map property.
     *
     * @param testBooleanMapProperty The test boolean map property.
     * @see #getTestBooleanMapProperty()
     */
    @JsonProperty(name = "testBooleanMapProperty", operation = OperationType.SET)
    public void setTestBooleanMapProperty(JsonBooleanMap testBooleanMapProperty);
    
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
   * Test JSON Boolean Property Adapter
   * <p>
   * Created: 13th December 2009.
   *
   * @since json-object-0.2
   */
  public static class TestJsonBooleanPropertyAdapter implements JsonBooleanPropertyAdapter<String> {
    /**
     * Convert to a JSON boolean property value.
     *
     * @param string The value.
     * @return The JSON boolean property value.
     * @see #fromJsonProperty(Boolean)
     */
    @Override
    public Boolean toJsonProperty(String string) {
      return Boolean.valueOf(Boolean.parseBoolean(string));
    }
    
    /**
     * Convert from a JSON boolean property value.
     *
     * @param propertyValue The JSON boolean property value.
     * @return The value.
     * @see #toJsonProperty(String)
     */
    @Override
    public String fromJsonProperty(Boolean propertyValue) {
      return propertyValue.toString();
    }
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
   * TestJsonObjectProperty.
   * <p>
   * Created: 13/12/2009
   *
   * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
   * @since
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
   * Test the creation of a JSON object.
   * <p>
   * This test asserts that a JSON object was created successfully.
   */
  @Test
  public void testCreateJsonObject() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertNotNull(testJsonObject);
  }
  
  /**
   * Test the determination of the existence of a property.
   * <p>
   * This test asserts that the JSON object correctly reports the existence or
   * non-existence of a property.
   */
  @Test
  public void testHasProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertFalse("testJsonObject.hasTestProperty() != false", testJsonObject.hasTestProperty());
    
    testJsonObject.setStringProperty("testProperty", null);
    
    assertTrue("testJsonObject.hasTestProperty() != true", testJsonObject.hasTestProperty());
    
    testJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertTrue("testJsonObject.hasTestProperty() != true", testJsonObject.hasTestProperty());
  }
  
  /**
   * Test the determination of the <code>null</code> value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that a property
   * exists but has a <code>null</code> value.
   */
  @Test
  public void testIsPropertyNull() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertFalse("testJsonObject.isTestPropertyNull() != false", testJsonObject.isTestPropertyNull());
    
    testJsonObject.setStringProperty("testProperty", null);
    
    assertTrue("testJsonObject.isTestPropertyNull() != true", testJsonObject.isTestPropertyNull());
    
    testJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertFalse("testJsonObject.isTestPropertyNull() != false", testJsonObject.isTestPropertyNull());
  }
  
  /**
   * Test the retrieval of the boolean value of a property.
   * <p>
   * This test asserts that the retrieved boolean value of a property matches 
   * the value of the corresponding property of the underlying JSON object.
   */
  @Test
  public void testGetBooleanProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setBooleanProperty("testBooleanProperty", true);
    
    assertEquals(true, testJsonObject.getTestBooleanProperty().booleanValue());
  }
  
  /**
   * Test the setting of the boolean value of a property.
   * <p>
   * This test asserts that the setting of the boolean value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetBooleanProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestBooleanProperty(true);
    
    assertEquals(true, testJsonObject.getBooleanProperty("testBooleanProperty").booleanValue());
  }
  
  /**
   * Test the retrieval of the value of an adapted boolean property.
   * <p>
   * This test asserts that the retrieved value of an adapted boolean property
   * matches the adapted value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testGetAdaptedBooleanProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setBooleanProperty("testAdaptedBooleanProperty", true);
    
    assertEquals(new TestJsonBooleanPropertyAdapter().fromJsonProperty(true), testJsonObject.getTestAdaptedBooleanProperty());
  }
  
  /**
   * Test the setting of the value of an adapted boolean property.
   * <p>
   * This test asserts that the setting of the value of an adapted property
   * changes the value of the corresponding property of the underlying JSON
   * object to the adapted boolean value.
   */
  @Test
  public void testSetAdaptedBooleanProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestAdaptedBooleanProperty(new TestJsonBooleanPropertyAdapter().fromJsonProperty(true));
    
    assertEquals(true, testJsonObject.getBooleanProperty("testAdaptedBooleanProperty").booleanValue());
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
   * Test the retrieval of the primitive boolean value of a property.
   * <p>
   * This test asserts that the retrieved primitive boolean value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetBooleanPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setBooleanProperty("testBooleanPrimitiveProperty", true);
    
    assertEquals(true, testJsonObject.getTestBooleanPrimitiveProperty());
  }
  
  /**
   * Test the setting of the primitive boolean value of a property.
   * <p>
   * This test asserts that the setting of the primitive boolean value of a
   * property changes the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testSetBooleanPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestBooleanPrimitiveProperty(true);
    
    assertEquals(true, testJsonObject.getBooleanProperty("testBooleanPrimitiveProperty").booleanValue());
  }
  
  /**
   * Test the retrieval of the default primitive boolean value of a property.
   * <p>
   * This test asserts that the retrieved primitive boolean value of a property
   * that does not exist in the underlying JSON object is the default boolean
   * value.
   */
  @Test
  public void testGetDefaultBooleanPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(false, testJsonObject.getTestBooleanPrimitiveProperty());
  }
  
  /**
   * Test the retrieval of the primitive integer value of a property.
   * <p>
   * This test asserts that the retrieved primitive integer value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetIntegerPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setNumberProperty("testIntegerPrimitiveProperty", 42);
    
    assertEquals(42, testJsonObject.getTestIntegerPrimitiveProperty());
  }
  
  /**
   * Test the setting of the primitive integer value of a property.
   * <p>
   * This test asserts that the setting of the primitive integer value of a
   * property changes the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testSetIntegerPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestIntegerPrimitiveProperty(42);
    
    assertEquals(42, testJsonObject.getNumberProperty("testIntegerPrimitiveProperty").intValue());
  }
  
  /**
   * Test the retrieval of the default primitive integer value of a property.
   * <p>
   * This test asserts that the retrieved primitive integer value of a property
   * that does not exist in the underlying JSON object is the default integer
   * value.
   */
  @Test
  public void testGetDefaultIntegerPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(0, testJsonObject.getTestIntegerPrimitiveProperty());
  }
  
  /**
   * Test the retrieval of the primitive double value of a property.
   * <p>
   * This test asserts that the retrieved primitive double value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetDoublePrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setNumberProperty("testDoublePrimitiveProperty", 3.14);
    
    assertEquals(3.14, testJsonObject.getTestDoublePrimitiveProperty(), 0.001);
  }
  
  /**
   * Test the setting of the primitive double value of a property.
   * <p>
   * This test asserts that the setting of the primitive double value of a
   * property changes the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testSetDoublePrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestDoublePrimitiveProperty(3.14);
    
    assertEquals(3.14, testJsonObject.getNumberProperty("testDoublePrimitiveProperty").doubleValue(), 0.001);
  }
  
  /**
   * Test the retrieval of the default primitive double value of a property.
   * <p>
   * This test asserts that the retrieved primitive double value of a property
   * that does not exist in the underlying JSON object is the default double
   * value.
   */
  @Test
  public void testGetDefaultDoublePrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(0.0, testJsonObject.getTestDoublePrimitiveProperty(), 0.0);
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
   * Test the retrieval of the boolean array value of a property.
   * <p>
   * This test asserts that the retrieved boolean array value of a property
   * matches the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testGetBooleanArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonBooleanArray testBooleanArrayProperty = JsonObjectFactory.get().createJsonArray(JsonBooleanArray.class);
    
    testJsonObject.setObjectProperty("testBooleanArrayProperty", testBooleanArrayProperty);
    
    assertEquals(testBooleanArrayProperty, testJsonObject.getTestBooleanArrayProperty());
  }
  
  /**
   * Test the setting of the boolean array value of a property.
   * <p>
   * This test asserts that the setting of the boolean array value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetBooleanArrayProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonBooleanArray testBooleanArrayProperty = JsonObjectFactory.get().createJsonArray(JsonBooleanArray.class);
    
    testJsonObject.setTestBooleanArrayProperty(testBooleanArrayProperty);
    
    assertEquals(testBooleanArrayProperty, testJsonObject.getObjectProperty("testBooleanArrayProperty"));
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
    
    testJsonObject.setObjectProperty("testObjectArrayProperty", testObjectArrayProperty);
    
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
   * Test the retrieval of the boolean map value of a property.
   * <p>
   * This test asserts that the retrieved boolean map value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetBooleanMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonBooleanMap testBooleanMapProperty = JsonObjectFactory.get().createJsonObject(JsonBooleanMap.class);
    
    testJsonObject.setObjectProperty("testBooleanMapProperty", testBooleanMapProperty);
    
    assertEquals(testBooleanMapProperty, testJsonObject.getTestBooleanMapProperty());
  }
  
  /**
   * Test the setting of the boolean map value of a property.
   * <p>
   * This test asserts that the setting of the boolean map value of a property
   * changes the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testSetBooleanMapProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    JsonBooleanMap testBooleanMapProperty = JsonObjectFactory.get().createJsonObject(JsonBooleanMap.class);
    
    testJsonObject.setTestBooleanMapProperty(testBooleanMapProperty);
    
    assertEquals(testBooleanMapProperty, testJsonObject.getObjectProperty("testBooleanMapProperty"));
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
   * Test the deletion of a property.
   * <p>
   * This test asserts that a property is correctly deleted from the JSON
   * object.
   */
  @Test
  public void testDeleteProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertTrue("testJsonObject.deleteTestProperty() != true", testJsonObject.deleteTestProperty());
    assertFalse("testJsonObject.hasTestProperty() != false", testJsonObject.hasTestProperty());
    
    assertFalse("testJsonObject.deleteTestProperty() != false", testJsonObject.deleteTestProperty());
    assertFalse("testJsonObject.hasTestProperty() != false", testJsonObject.hasTestProperty());
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
