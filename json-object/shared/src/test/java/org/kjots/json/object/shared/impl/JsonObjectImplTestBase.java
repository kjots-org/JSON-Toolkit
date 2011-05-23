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
package org.kjots.json.object.shared.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import org.kjots.json.object.shared.JsonObject;

/**
 * JSON Object Implementation Test Base.
 * <p>
 * Created: 11th December 2009.
 *
 * @param <J> The type of the underlying JSON object.
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public abstract class JsonObjectImplTestBase<J> {
  /**
   * Cast JSON Object.
   * <p>
   * Created: 8th December 2009.
   */
  public static interface CastJsonObject extends JsonObject {
  }
  
  /**
   * Test Property JSON Object.
   * <p>
   * Created: 8th December 2009.
   */
  public static interface TestPropertyJsonObject extends JsonObject {
  }

  /**
   * Test the casting of a JSON object.
   * <p>
   * This test asserts that a the class of a cast JSON object matches the class
   * provided to the cast method.
   */
  @Test
  public void testCastByClass() {
    JsonObject testJsonObject = this.createJsonObject(this.createUnderlyingJsonObject());
    CastJsonObject castJsonObject = testJsonObject.cast(CastJsonObject.class);
    
    assertEquals(testJsonObject, castJsonObject);
  }
  
  /**
   * Test the casting of a JSON object.
   * <p>
   * This test asserts that a the class of a cast JSON object matches the class
   * with the name provided to the cast method.
   */
  @Test
  public void testCastByClassName() {
    JsonObject testJsonObject = this.createJsonObject(this.createUnderlyingJsonObject());
    CastJsonObject castJsonObject = testJsonObject.cast(CastJsonObject.class.getName());
    
    assertEquals(testJsonObject, castJsonObject);
  }
  
  /**
   * Test the determination of a JSON array.
   * <p>
   * This test asserts that the JSON object correctly reports that it is not a
   * JSON array.
   */
  @Test
  public void testIsArray() {
    assertFalse("object.isArray() != false", this.createJsonObject(this.createUnderlyingJsonObject()).isArray());
    assertTrue("array.isArray() != true", this.createJsonObject(this.createUnderlyingJsonArray()).isArray());
  }
  
  /**
   * Test the retrieval of the names of the properties.
   * <p>
   * This test asserts that the retrieved property names match the names of the
   * properties of the underlying JavaScript object.
   */
  @Test
  public void testGetPropertyNames() {
    JsonObject testJsonObject = this.createJsonObject(this.createUnderlyingJsonObject());
    
    Set<String> testPropertyNames = new HashSet<String>(Arrays.asList("one", "two", "three", "four", "five"));
    for (String testPropertyName : testPropertyNames) {
      testJsonObject.setObjectProperty(testPropertyName, null);
    }
    
    assertEquals(testPropertyNames, testJsonObject.getPropertyNames());
  }
  
  /**
   * Test the determination of the existence of a property.
   * <p>
   * This test asserts that the JSON object correctly reports the existence or
   * non-existence of a property.
   */
  @Test
  public void testHasProperty() {
    J object = this.createUnderlyingJsonObject();
    
    this.setObjectProperty(object, "notNullProperty", this.createUnderlyingJsonObject());
    this.setObjectProperty(object, "nullProperty", null);
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    assertTrue("hasProperty(\"notNullProperty\") != true", testJsonObject.hasProperty("notNullProperty"));
    assertTrue("hasProperty(\"nullProperty\") != true", testJsonObject.hasProperty("nullProperty"));
    assertFalse("hasProperty(\"noProperty\") != false", testJsonObject.hasProperty("noProperty"));
  }
  
  /**
   * Test the determination of the <code>null</code> value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that a property
   * exists but has a <code>null</code> value.
   */
  @Test
  public void testIsNullProperty() {
    J object = this.createUnderlyingJsonObject();
    
    this.setObjectProperty(object, "notNullProperty", this.createUnderlyingJsonObject());
    this.setObjectProperty(object, "nullProperty", null);
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    assertFalse("isNullProperty(\"notNullProperty\") != false", testJsonObject.isNullProperty("notNullProperty"));
    assertTrue("isNullProperty(\"nullProperty\") != true", testJsonObject.isNullProperty("nullProperty"));
    assertFalse("isNullProperty(\"noProperty\") != false", testJsonObject.isNullProperty("noProperty"));
  }
  
  /**
   * Test the determination of a boolean value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that a property
   * exists and has a boolean value.
   */
  @Test
  public void testIsBooleanProperty() {
    J object = this.createUnderlyingJsonObject();
    
    this.setBooleanProperty(object, "booleanProperty", true);
    this.setNumberProperty(object, "numberProperty", 3.14);
    this.setStringProperty(object, "stringProperty", "Test String Property Value");
    this.setObjectProperty(object, "objectProperty", this.createUnderlyingJsonObject());
    this.setObjectProperty(object, "nullProperty", null);
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    assertTrue("isBooleanProperty(\"booleanProperty\") != true", testJsonObject.isBooleanProperty("booleanProperty"));
    assertFalse("isBooleanProperty(\"numberProperty\") != false", testJsonObject.isBooleanProperty("numberProperty"));
    assertFalse("isBooleanProperty(\"stringProperty\") != false", testJsonObject.isBooleanProperty("stringProperty"));
    assertFalse("isBooleanProperty(\"objectProperty\") != false", testJsonObject.isBooleanProperty("objectProperty"));
    assertFalse("isBooleanProperty(\"nullProperty\") != false", testJsonObject.isBooleanProperty("nullProperty"));
    assertFalse("isBooleanProperty(\"noProperty\") != false", testJsonObject.isBooleanProperty("noProperty"));
  }

  /**
   * Test the retrieval of the boolean value of a property.
   * <p>
   * This test asserts that the retrieved boolean value of a property matches 
   * the value of the corresponding property of the underlying JavaScript
   * object.
   */
  @Test
  public void testGetBooleanProperty() {
    J object = this.createUnderlyingJsonObject();
    
    this.setBooleanProperty(object, "booleanProperty", true);
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    assertEquals(true, testJsonObject.getBooleanProperty("booleanProperty").booleanValue());
  }
  
  /**
   * Test the setting of the boolean value of a property.
   * <p>
   * This test asserts that the setting of the boolean value of a property
   * changes the value of the corresponding property of the underlying
   * JavaScript object.
   */
  @Test
  public void testSetBooleanProperty() {
    J object = this.createUnderlyingJsonObject();
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    testJsonObject.setBooleanProperty("booleanProperty", true);
    
    assertEquals(true, this.getBooleanProperty(object, "booleanProperty").booleanValue());
  }
  
  /**
   * Test the determination of a numeric value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that a property
   * exists and has a numeric value.
   */
  @Test
  public void testIsNumberProperty() {
    J object = this.createUnderlyingJsonObject();
    
    this.setBooleanProperty(object, "booleanProperty", true);
    this.setNumberProperty(object, "numberProperty", 3.14);
    this.setStringProperty(object, "stringProperty", "Test String Property Value");
    this.setObjectProperty(object, "objectProperty", this.createUnderlyingJsonObject());
    this.setObjectProperty(object, "nullProperty", null);
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    assertFalse("isNumberProperty(\"booleanProperty\") != false", testJsonObject.isNumberProperty("booleanProperty"));
    assertTrue("isNumberProperty(\"numberProperty\") != true", testJsonObject.isNumberProperty("numberProperty"));
    assertFalse("isNumberProperty(\"stringProperty\") != false", testJsonObject.isNumberProperty("stringProperty"));
    assertFalse("isNumberProperty(\"objectProperty\") != false", testJsonObject.isNumberProperty("objectProperty"));
    assertFalse("isNumberProperty(\"nullProperty\") != false", testJsonObject.isNumberProperty("nullProperty"));
    assertFalse("isNumberProperty(\"noProperty\") != false", testJsonObject.isNumberProperty("noProperty"));
  }

  /**
   * Test the retrieval of the numeric value of a property.
   * <p>
   * This test asserts that the retrieved numeric value of a property matches 
   * the value of the corresponding property of the underlying JavaScript
   * object.
   */
  @Test
  public void testGetNumberProperty() {
    J object = this.createUnderlyingJsonObject();
    
    this.setNumberProperty(object, "numberProperty", 3.14);
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    assertEquals(3.14, testJsonObject.getNumberProperty("numberProperty").doubleValue(), 0.001);
  }
  
  /**
   * Test the setting of the numeric value of a property.
   * <p>
   * This test asserts that the setting of the numeric value of a property
   * changes the value of the corresponding property of the underlying
   * JavaScript object.
   */
  @Test
  public void testSetNumberProperty() {
    J object = this.createUnderlyingJsonObject();
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    testJsonObject.setNumberProperty("numberProperty", 3.14);
    
    assertEquals(3.14, this.getNumberProperty(object, "numberProperty").doubleValue(), 0.001);
  }
  
  /**
   * Test the determination of a string value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that a property
   * exists and has a string value.
   */
  @Test
  public void testIsStringProperty() {
    J object = this.createUnderlyingJsonObject();
    
    this.setBooleanProperty(object, "booleanProperty", true);
    this.setNumberProperty(object, "numberProperty", 3.14);
    this.setStringProperty(object, "stringProperty", "Test String Property Value");
    this.setObjectProperty(object, "objectProperty", this.createUnderlyingJsonObject());
    this.setObjectProperty(object, "nullProperty", null);
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    assertFalse("isStringProperty(\"booleanProperty\") != false", testJsonObject.isStringProperty("booleanProperty"));
    assertFalse("isStringProperty(\"numberProperty\") != false", testJsonObject.isStringProperty("numberProperty"));
    assertTrue("isStringProperty(\"stringProperty\") != true", testJsonObject.isStringProperty("stringProperty"));
    assertFalse("isStringProperty(\"objectProperty\") != false", testJsonObject.isStringProperty("objectProperty"));
    assertFalse("isStringProperty(\"nullProperty\") != false", testJsonObject.isStringProperty("nullProperty"));
    assertFalse("isStringProperty(\"noProperty\") != false", testJsonObject.isStringProperty("noProperty"));
  }
  
  /**
   * Test the retrieval of the string value of a property.
   * <p>
   * This test asserts that the retrieved string value of a property matches 
   * the value of the corresponding property of the underlying JavaScript
   * object.
   */
  @Test
  public void testGetStringProperty() {
    J object = this.createUnderlyingJsonObject();
    
    this.setStringProperty(object, "stringProperty", "Test String Property Value");
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    assertEquals("Test String Property Value", testJsonObject.getStringProperty("stringProperty"));
  }
  
  /**
   * Test the setting of the string value of a property.
   * <p>
   * This test asserts that the setting of the string value of a property
   * changes the value of the corresponding property of the underlying
   * JavaScript object.
   */
  @Test
  public void testSetStringProperty() {
    J object = this.createUnderlyingJsonObject();
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    testJsonObject.setStringProperty("stringProperty", "Test String Property Value");
    
    assertEquals("Test String Property Value", this.getStringProperty(object, "stringProperty"));
  }
  
  /**
   * Test the determination of a object value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that a property
   * exists and has a object value.
   */
  @Test
  public void testIsObjectProperty() {
    J object = this.createUnderlyingJsonObject();
    
    this.setBooleanProperty(object, "booleanProperty", true);
    this.setNumberProperty(object, "numberProperty", 3.14);
    this.setStringProperty(object, "stringProperty", "Test String Property Value");
    this.setObjectProperty(object, "objectProperty", this.createUnderlyingJsonObject());
    this.setObjectProperty(object, "nullProperty", null);
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    assertFalse("isObjectProperty(\"booleanProperty\") != false", testJsonObject.isObjectProperty("booleanProperty"));
    assertFalse("isObjectProperty(\"numberProperty\") != false", testJsonObject.isObjectProperty("numberProperty"));
    assertFalse("isObjectProperty(\"stringProperty\") != false", testJsonObject.isObjectProperty("stringProperty"));
    assertTrue("isObjectProperty(\"objectProperty\") != true", testJsonObject.isObjectProperty("objectProperty"));
    assertFalse("isObjectProperty(\"nullProperty\") != false", testJsonObject.isObjectProperty("nullProperty"));
    assertFalse("isObjectProperty(\"noProperty\") != false", testJsonObject.isObjectProperty("noProperty"));
  }

  /**
   * Test the retrieval of the object value of a property.
   * <p>
   * This test asserts that the retrieved object value of a property matches 
   * the value of the corresponding property of the underlying JavaScript
   * object.
   */
  @Test
  public void testGetObjectProperty() {
    J object = this.createUnderlyingJsonObject();
    J objectPropertyValue = this.createUnderlyingJsonObject();
    
    this.setObjectProperty(object, "objectProperty", objectPropertyValue);
    
    JsonObject testJsonObject = this.createJsonObject(object);
    JsonObject jsonObjectPropertyValue = testJsonObject.getObjectProperty("objectProperty");
    
    assertEquals(objectPropertyValue, jsonObjectPropertyValue.getObject());
  }
  
  /**
   * Test the retrieval of the object value of a property with a class.
   * <p>
   * This test asserts that the retrieved object value of a property matches 
   * the value of the corresponding property of the underlying JavaScript
   * object, and that the class of the retrieved object value matches the class
   * provided to the retrieval method.
   */
  @Test
  public void testGetObjectPropertyWithClass() {
    J object = this.createUnderlyingJsonObject();
    J objectPropertyValue = this.createUnderlyingJsonObject();
    
    this.setObjectProperty(object, "objectProperty", objectPropertyValue);
    
    JsonObject testJsonObject = this.createJsonObject(object);
    TestPropertyJsonObject jsonObjectPropertyValue = testJsonObject.getObjectProperty("objectProperty", TestPropertyJsonObject.class);
    
    assertEquals(objectPropertyValue, jsonObjectPropertyValue.getObject());
  }
  
  /**
   * Test the setting of the object value of a property.
   * <p>
   * This test asserts that the setting of the object value of a property
   * changes the value of the corresponding property of the underlying
   * JavaScript object.
   */
  @Test
  public void testSetObjectProperty() {
    J object = this.createUnderlyingJsonObject();
    J objectPropertyValue = this.createUnderlyingJsonObject();
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    testJsonObject.setObjectProperty("objectProperty", this.createJsonObject(objectPropertyValue));
    
    assertEquals(objectPropertyValue, getObjectProperty(object, "objectProperty"));
  }
  
  /**
   * Test the deletion of a property.
   * <p>
   * This test asserts that a property is correctly deleted from the JSON
   * object.
   */
  @Test
  public void testDeleteProperty() {
    J object = this.createUnderlyingJsonObject();
    
    this.setObjectProperty(object, "property", this.createUnderlyingJsonObject());
    
    JsonObject testJsonObject = this.createJsonObject(object);
    
    assertTrue("testJsonObject.deleteProperty(\"property\") != true", testJsonObject.deleteProperty("property"));
    assertFalse("hasProperty(\"property\") != false", testJsonObject.hasProperty("property"));
    
    assertFalse("testJsonObject.deleteProperty(\"noProperty\" != false)", testJsonObject.deleteProperty("noProperty"));
    assertFalse("hasProperty(\"noProperty\") != false", testJsonObject.hasProperty("noProperty"));
  }

  /**
   * Test the determination of equality of JSON objects.
   * <p>
   * This test asserts that two JSON objects created with the same underlying
   * JSON object indicate that they are equal to each other.
   */
  @Test
  public void testEquals() {
    J object = this.createUnderlyingJsonObject();
    
    JsonObject testJsonObject1 = this.createJsonObject(object);
    JsonObject testJsonObject2 = this.createJsonObject(object);
    
    assertTrue("testJsonObject1.equals(testJsonObject2) != true", testJsonObject1.equals(testJsonObject2));
    assertTrue("testJsonObject2.equals(testJsonObject1) != true", testJsonObject2.equals(testJsonObject1));
  }
  
  /**
   * Test the calculation of the hash code of a JSON object.
   * <p>
   * This test asserts that two JSON objects created with the same underlying 
   * JSON object both provide the same hash code.
   */
  @Test
  public void testHashCode() {
    J object = this.createUnderlyingJsonObject();
    
    JsonObject testJsonObject1 = this.createJsonObject(object);
    JsonObject testJsonObject2 = this.createJsonObject(object);
    
    assertTrue("testJsonObject1.hashCode() != testJsonObject2.hashCode()", testJsonObject1.hashCode() == testJsonObject2.hashCode());
  }
  
  /**
   * Create a JSON object with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  protected abstract JsonObject createJsonObject(J object);

  /**
   * Create an empty underlying JSON object.
   *
   * @return The empty underlying JSON object.
   */
  protected abstract J createUnderlyingJsonObject();
  
  /**
   * Create an empty underlying JSON array.
   *
   * @return The empty underlying JSON array.
   */
  protected abstract J createUnderlyingJsonArray();
  
  /**
   * Retrieve the boolean value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The boolean value of the property.
   * @see #setBooleanProperty(Object, String, Boolean)
   */
  protected abstract Boolean getBooleanProperty(J object, String propertyName);
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given boolean value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The boolean value of the property.
   * @see #getBooleanProperty(Object, String)
   */
  protected abstract void setBooleanProperty(J object, String propertyName, Boolean propertyValue);
  
  /**
   * Retrieve the number value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The number value of the property.
   * @see #setNumberProperty(Object, String, Number)
   */
  protected abstract Number getNumberProperty(J object, String propertyName);
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given number value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The number value of the property.
   * @see #getNumberProperty(Object, String)
   */
  protected abstract void setNumberProperty(J object, String propertyName, Number propertyValue);
  
  /**
   * Retrieve the string value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The string value of the property.
   * @see #setStringProperty(Object, String, String)
   */
  protected abstract String getStringProperty(J object, String propertyName);
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given string value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The string value of the property.
   * @see #getStringProperty(Object, String)
   */
  protected abstract void setStringProperty(J object, String propertyName, String propertyValue);
  
  /**
   * Retrieve the object value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The object value of the property.
   * @see #setObjectProperty(Object, String, Object)
   */
  protected abstract J getObjectProperty(J object, String propertyName);
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given object value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The object value of the property.
   * @see #getObjectProperty(Object, String)
   */
  protected abstract void setObjectProperty(J object, String propertyName, J propertyValue);
}
