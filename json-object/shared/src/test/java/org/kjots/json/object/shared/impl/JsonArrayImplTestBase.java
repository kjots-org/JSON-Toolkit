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

import org.junit.Test;

import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonObject;

/**
 * JSON Array Implementation Test Base.
 * <p>
 * Created: 11th December 2009.
 *
 * @param <J> The type of the underlying JSON object.
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public abstract class JsonArrayImplTestBase<J> {
  /**
   * Test Property JSON Object.
   * <p>
   * Created: 10th December 2009.
   */
  public static interface TestPropertyJsonObject extends JsonObject {
  }
  
  /**
   * Test the retrieval of the length of the array.
   * <p>
   * This test asserts that the retrieved length is the same as the length of
   * the underlying array.
   */
  @Test
  public void testGetLength() {
    J array = this.createUnderlyingJsonArray();
    JsonArray testJsonArray = this.createJsonArray(array);
    
    assertEquals(0, testJsonArray.getLength());
    
    this.setArrayLength(array, 5);
    
    assertEquals(5, testJsonArray.getLength());
    
    this.setArrayLength(array, 0);
    
    assertEquals(0, testJsonArray.getLength());
  }
  
  /**
   * Test the settings of the length of the array.
   * <p>
   * This test asserts that setting the length of the array changes the length
   * of the underlying array.
   */
  @Test
  public void testSetLength() {
    J array = this.createUnderlyingJsonArray();
    JsonArray testJsonArray = this.createJsonArray(array);
    
    assertEquals(0, this.getArrayLength(array));
    
    testJsonArray.setLength(5);
    
    assertEquals(5, this.getArrayLength(array));
    
    testJsonArray.setLength(0);
    
    assertEquals(0, this.getArrayLength(array));
  }
  
  /**
   * Test the determination of the <code>null</code> value of an element.
   * <p>
   * This test asserts that the JSON array correctly reports that an element
   * has a <code>null</code> value.
   */
  @Test
  public void testIsNullElement() {
    J array = this.createUnderlyingJsonArray();
    
    this.setObjectElement(array, 0, null);
    this.setObjectElement(array, 1, this.createUnderlyingJsonObject());
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    assertTrue("isNull(0) != true", testJsonArray.isNullElement(0));
    assertFalse("isNull(1) != false", testJsonArray.isNullElement(1));
  }
  
  /**
   * Test the determination of a boolean value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that an element
   * has a boolean value.
   */
  @Test
  public void testIsBooleanElement() {
    J array = this.createUnderlyingJsonArray();
    
    this.setBooleanElement(array, 0, true);
    this.setNumberElement(array, 1, 3.14);
    this.setStringElement(array, 2, "Test String Element Value");
    this.setObjectElement(array, 3, this.createUnderlyingJsonObject());
    this.setObjectElement(array, 4, null);
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    assertTrue("isBooleanElement(0) != true", testJsonArray.isBooleanElement(0));
    assertFalse("isBooleanElement(1) != false", testJsonArray.isBooleanElement(1));
    assertFalse("isBooleanElement(2) != false", testJsonArray.isBooleanElement(2));
    assertFalse("isBooleanElement(3) != false", testJsonArray.isBooleanElement(3));
    assertFalse("isBooleanElement(4) != false", testJsonArray.isBooleanElement(4));
  }

  /**
   * Test the retrieval of the boolean value of an element.
   * <p>
   * This test asserts that the retrieved boolean value of an element matches 
   * the value of the corresponding element of the underlying JSON array.
   */
  @Test
  public void testGetBooleanElement() {
    J array = this.createUnderlyingJsonArray();
    
    this.setBooleanElement(array, 0, true);
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    assertEquals(true, testJsonArray.getBooleanElement(0).booleanValue());
  }
  
  /**
   * Test the setting of the boolean value of an element.
   * <p>
   * This test asserts that the setting of the boolean value of an element
   * changes the value of the corresponding element of the underlying JSON
   * array.
   */
  @Test
  public void testSetBooleanElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setBooleanElement(0, true);
    
    assertEquals(true, this.getBooleanElement(array, 0).booleanValue());
  }
  
  /**
   * Test the insertion of a boolean value element.
   * <p>
   * This test asserts that the insertion of a boolean value element inserts 
   * a corresponding element in the the underlying JSON array.
   */
  @Test
  public void testInsertBooleanElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setBooleanElement(0, true);
    testJsonArray.setBooleanElement(1, true);
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals(true, this.getBooleanElement(array, 0).booleanValue());
    assertEquals(true, this.getBooleanElement(array, 1).booleanValue());
    
    testJsonArray.insertBooleanElement(1, false);
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals(true, this.getBooleanElement(array, 0).booleanValue());
    assertEquals(false, this.getBooleanElement(array, 1).booleanValue());
    assertEquals(true, this.getBooleanElement(array, 2).booleanValue());
  }
  
  /**
   * Test the prepending of a boolean value element.
   * <p>
   * This test asserts that the prepending of a boolean value element prepends 
   * a corresponding element to the the underlying JSON array.
   */
  @Test
  public void testPrependBooleanElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setBooleanElement(0, true);
    testJsonArray.setBooleanElement(1, true);
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals(true, this.getBooleanElement(array, 0).booleanValue());
    assertEquals(true, this.getBooleanElement(array, 1).booleanValue());
    
    testJsonArray.prependBooleanElement(false);
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals(false, this.getBooleanElement(array, 0).booleanValue());
    assertEquals(true, this.getBooleanElement(array, 1).booleanValue());
    assertEquals(true, this.getBooleanElement(array, 2).booleanValue());
  }
  
  /**
   * Test the appending of a boolean value element.
   * <p>
   * This test asserts that the appending of a boolean value element appends 
   * a corresponding element to the the underlying JSON array.
   */
  @Test
  public void testAppendBooleanElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setBooleanElement(0, true);
    testJsonArray.setBooleanElement(1, true);
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals(true, this.getBooleanElement(array, 0).booleanValue());
    assertEquals(true, this.getBooleanElement(array, 1).booleanValue());
    
    testJsonArray.appendBooleanElement(false);
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals(true, this.getBooleanElement(array, 0).booleanValue());
    assertEquals(true, this.getBooleanElement(array, 1).booleanValue());
    assertEquals(false, this.getBooleanElement(array, 2).booleanValue());
  }
  
  /**
   * Test the determination of a numeric value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that an element
   * has a numeric value.
   */
  @Test
  public void testIsNumberElement() {
    J array = this.createUnderlyingJsonArray();
    
    this.setBooleanElement(array, 0, true);
    this.setNumberElement(array, 1, 3.14);
    this.setStringElement(array, 2, "Test String Element Value");
    this.setObjectElement(array, 3, this.createUnderlyingJsonObject());
    this.setObjectElement(array, 4, null);
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    assertFalse("isNumberElement(0) != false", testJsonArray.isNumberElement(0));
    assertTrue("isNumberElement(1) != true", testJsonArray.isNumberElement(1));
    assertFalse("isNumberElement(2) != false", testJsonArray.isNumberElement(2));
    assertFalse("isNumberElement(3) != false", testJsonArray.isNumberElement(3));
    assertFalse("isNumberElement(4) != false", testJsonArray.isNumberElement(4));
  }

  /**
   * Test the retrieval of the numeric value of an element.
   * <p>
   * This test asserts that the retrieved numeric value of an element matches 
   * the value of the corresponding element of the underlying JSON array.
   */
  @Test
  public void testGetNumberElement() {
    J array = this.createUnderlyingJsonArray();
    
    this.setNumberElement(array, 0, 3.14);
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    assertEquals(3.14, testJsonArray.getNumberElement(0).doubleValue(), 0.001);
  }
  
  /**
   * Test the setting of the numeric value of an element.
   * <p>
   * This test asserts that the setting of the numeric value of an element
   * changes the value of the corresponding element of the underlying JSON
   * array.
   */
  @Test
  public void testSetNumberElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setNumberElement(0, 3.14);
    
    assertEquals(3.14, this.getNumberElement(array, 0).doubleValue(), 0.001);
  }
  
  /**
   * Test the insertion of a number value element.
   * <p>
   * This test asserts that the insertion of a number value element inserts 
   * a corresponding element in the the underlying JSON array.
   */
  @Test
  public void testInsertNumberElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setNumberElement(0, 1.1);
    testJsonArray.setNumberElement(1, 3.3);
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals(1.1, this.getNumberElement(array, 0).doubleValue(), 0.01);
    assertEquals(3.3, this.getNumberElement(array, 1).doubleValue(), 0.01);
    
    testJsonArray.insertNumberElement(1, 2.2);
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals(1.1, this.getNumberElement(array, 0).doubleValue(), 0.01);
    assertEquals(2.2, this.getNumberElement(array, 1).doubleValue(), 0.01);
    assertEquals(3.3, this.getNumberElement(array, 2).doubleValue(), 0.01);
  }

  /**
   * Test the prepending of a number value element.
   * <p>
   * This test asserts that the prepending of a number value element prepends 
   * a corresponding element to the the underlying JSON array.
   */
  @Test
  public void testPrependNumberElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setNumberElement(0, 2.2);
    testJsonArray.setNumberElement(1, 3.3);
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals(2.2, this.getNumberElement(array, 0).doubleValue(), 0.01);
    assertEquals(3.3, this.getNumberElement(array, 1).doubleValue(), 0.01);
    
    testJsonArray.prependNumberElement(1.1);
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals(1.1, this.getNumberElement(array, 0).doubleValue(), 0.01);
    assertEquals(2.2, this.getNumberElement(array, 1).doubleValue(), 0.01);
    assertEquals(3.3, this.getNumberElement(array, 2).doubleValue(), 0.01);
  }
  
  /**
   * Test the appending of a number value element.
   * <p>
   * This test asserts that the appending of a number value element appends 
   * a corresponding element to the the underlying JSON array.
   */
  @Test
  public void testAppendNumberElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setNumberElement(0, 1.1);
    testJsonArray.setNumberElement(1, 2.2);
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals(1.1, this.getNumberElement(array, 0).doubleValue(), 0.01);
    assertEquals(2.2, this.getNumberElement(array, 1).doubleValue(), 0.01);
    
    testJsonArray.appendNumberElement(3.3);
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals(1.1, this.getNumberElement(array, 0).doubleValue(), 0.01);
    assertEquals(2.2, this.getNumberElement(array, 1).doubleValue(), 0.01);
    assertEquals(3.3, this.getNumberElement(array, 2).doubleValue(), 0.01);
  }
  
  /**
   * Test the determination of a string value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that an element
   * has a string value.
   */
  @Test
  public void testIsStringElement() {
    J array = this.createUnderlyingJsonArray();
    
    this.setBooleanElement(array, 0, true);
    this.setNumberElement(array, 1, 3.14);
    this.setStringElement(array, 2, "Test String Element Value");
    this.setObjectElement(array, 3, this.createUnderlyingJsonObject());
    this.setObjectElement(array, 4, null);
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    assertFalse("isStringElement(0) != false", testJsonArray.isStringElement(0));
    assertFalse("isStringElement(1) != false", testJsonArray.isStringElement(1));
    assertTrue("isStringElement(2) != true", testJsonArray.isStringElement(2));
    assertFalse("isStringElement(3) != false", testJsonArray.isStringElement(3));
    assertFalse("isStringElement(4) != false", testJsonArray.isStringElement(4));
  }

  /**
   * Test the retrieval of the string value of an element.
   * <p>
   * This test asserts that the retrieved string value of an element matches 
   * the value of the corresponding element of the underlying JSON array.
   */
  @Test
  public void testGetStringElement() {
    J array = this.createUnderlyingJsonArray();
    
    this.setStringElement(array, 0, "Test String Property Value");
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    assertEquals("Test String Property Value", testJsonArray.getStringElement(0));
  }
  
  /**
   * Test the setting of the string value of an element.
   * <p>
   * This test asserts that the setting of the string value of an element
   * changes the value of the corresponding element of the underlying JSON
   * array.
   */
  @Test
  public void testSetStringElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setStringElement(0, "Test String Property Value");
    
    assertEquals("Test String Property Value", this.getStringElement(array, 0));
  }
  
  /**
   * Test the insertion of a string value element.
   * <p>
   * This test asserts that the insertion of a string value element inserts 
   * a corresponding element in the the underlying JSON array.
   */
  @Test
  public void testInsertStringElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setStringElement(0, "one");
    testJsonArray.setStringElement(1, "three");
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals("one", this.getStringElement(array, 0));
    assertEquals("three", this.getStringElement(array, 1));
    
    testJsonArray.insertStringElement(1, "two");
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals("one", this.getStringElement(array, 0));
    assertEquals("two", this.getStringElement(array, 1));
    assertEquals("three", this.getStringElement(array, 2));
  }

  /**
   * Test the prepending of a string value element.
   * <p>
   * This test asserts that the prepending of a string value element prepends 
   * a corresponding element to the the underlying JSON array.
   */
  @Test
  public void testPrependStringElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setStringElement(0, "two");
    testJsonArray.setStringElement(1, "three");
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals("two", this.getStringElement(array, 0));
    assertEquals("three", this.getStringElement(array, 1));
    
    testJsonArray.prependStringElement("one");
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals("one", this.getStringElement(array, 0));
    assertEquals("two", this.getStringElement(array, 1));
    assertEquals("three", this.getStringElement(array, 2));
  }
  
  /**
   * Test the appending of a string value element.
   * <p>
   * This test asserts that the appending of a string value element appends 
   * a corresponding element to the the underlying JSON array.
   */
  @Test
  public void testAppendStringElement() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setStringElement(0, "one");
    testJsonArray.setStringElement(1, "two");
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals("one", this.getStringElement(array, 0));
    assertEquals("two", this.getStringElement(array, 1));
    
    testJsonArray.appendStringElement("three");
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals("one", this.getStringElement(array, 0));
    assertEquals("two", this.getStringElement(array, 1));
    assertEquals("three", this.getStringElement(array, 2));
  }
  
  /**
   * Test the determination of an object value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that an element
   * has an object value.
   */
  @Test
  public void testIsObjectElement() {
    J array = this.createUnderlyingJsonArray();
    
    this.setBooleanElement(array, 0, true);
    this.setNumberElement(array, 1, 3.14);
    this.setStringElement(array, 2, "Test String Element Value");
    this.setObjectElement(array, 3, this.createUnderlyingJsonObject());
    this.setObjectElement(array, 4, null);
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    assertFalse("isObjectElement(0) != false", testJsonArray.isObjectElement(0));
    assertFalse("isObjectElement(1) != false", testJsonArray.isObjectElement(1));
    assertFalse("isObjectElement(2) != false", testJsonArray.isObjectElement(2));
    assertTrue("isObjectElement(3) != true", testJsonArray.isObjectElement(3));
    assertFalse("isObjectElement(4) != false", testJsonArray.isObjectElement(4));
  }

  /**
   * Test the retrieval of the object value of an element.
   * <p>
   * This test asserts that the retrieved object value of an element matches 
   * the value of the corresponding element of the underlying JSON array.
   */
  @Test
  public void testGetObjectElement() {
    J array = this.createUnderlyingJsonArray();
    J objectElementValue = this.createUnderlyingJsonObject();
    
    this.setObjectElement(array, 0, objectElementValue);
    
    JsonArray testJsonArray = this.createJsonArray(array);
    JsonObject jsonObjectElementValue = testJsonArray.getObjectElement(0);
    
    assertEquals(objectElementValue, jsonObjectElementValue.getObject());
  }
  
  /**
   * Test the retrieval of the object value of an element with a class.
   * <p>
   * This test asserts that the retrieved object value of an element matches 
   * the value of the corresponding element of the underlying JSON array, and
   * that the class of the retrieved object value matches the class provided to
   * the retrieval method.
   */
  @Test
  public void testGetObjectElementWithClass() {
    J array = this.createUnderlyingJsonArray();
    J objectElementValue = this.createUnderlyingJsonObject();
    
    this.setObjectElement(array, 0, objectElementValue);
    
    JsonArray testJsonArray = this.createJsonArray(array);
    TestPropertyJsonObject jsonObjectElementValue = testJsonArray.getObjectElement(0, TestPropertyJsonObject.class);
    
    assertEquals(objectElementValue, jsonObjectElementValue.getObject());
  }
  
  /**
   * Test the setting of the object value of an element.
   * <p>
   * This test asserts that the setting of the object value of an element
   * changes the value of the corresponding element of the underlying JSON
   * array.
   */
  @Test
  public void testSetObjectElement() {
    J array = this.createUnderlyingJsonArray();
    J objectElementValue = this.createUnderlyingJsonObject();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setObjectElement(0, this.createJsonObject(objectElementValue));
    
    assertEquals(objectElementValue, this.getObjectElement(array, 0));
  }
  
  /**
   * Test the insertion of an object value element.
   * <p>
   * This test asserts that the insertion of an object value element inserts 
   * a corresponding element in the the underlying JSON array.
   */
  @Test
  public void testInsertObjectElement() {
    J array = this.createUnderlyingJsonArray();
    J objectElementValue1 = this.createUnderlyingJsonObject();
    J objectElementValue2 = this.createUnderlyingJsonObject();
    J objectElementValue3 = this.createUnderlyingJsonObject();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setObjectElement(0, this.createJsonObject(objectElementValue1));
    testJsonArray.setObjectElement(1, this.createJsonObject(objectElementValue3));
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals(objectElementValue1, this.getObjectElement(array, 0));
    assertEquals(objectElementValue3, this.getObjectElement(array, 1));
    
    testJsonArray.insertObjectElement(1, this.createJsonObject(objectElementValue2));
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals(objectElementValue1, this.getObjectElement(array, 0));
    assertEquals(objectElementValue2, this.getObjectElement(array, 1));
    assertEquals(objectElementValue3, this.getObjectElement(array, 2));
  }
  
  /**
   * Test the prepending of a object value element.
   * <p>
   * This test asserts that the prepending of an object value element prepends 
   * a corresponding element to the the underlying JSON array.
   */
  @Test
  public void testPrependObjectElement() {
    J array = this.createUnderlyingJsonArray();
    J objectElementValue1 = this.createUnderlyingJsonObject();
    J objectElementValue2 = this.createUnderlyingJsonObject();
    J objectElementValue3 = this.createUnderlyingJsonObject();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setObjectElement(0, this.createJsonObject(objectElementValue2));
    testJsonArray.setObjectElement(1, this.createJsonObject(objectElementValue3));
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals(objectElementValue2, this.getObjectElement(array, 0));
    assertEquals(objectElementValue3, this.getObjectElement(array, 1));
    
    testJsonArray.prependObjectElement(this.createJsonObject(objectElementValue1));
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals(objectElementValue1, this.getObjectElement(array, 0));
    assertEquals(objectElementValue2, this.getObjectElement(array, 1));
    assertEquals(objectElementValue3, this.getObjectElement(array, 2));
  }
  
  /**
   * Test the appending of a object value element.
   * <p>
   * This test asserts that the appending of an object value element appends 
   * a corresponding element to the the underlying JSON array.
   */
  @Test
  public void testAppendObjectElement() {
    J array = this.createUnderlyingJsonArray();
    J objectElementValue1 = this.createUnderlyingJsonObject();
    J objectElementValue2 = this.createUnderlyingJsonObject();
    J objectElementValue3 = this.createUnderlyingJsonObject();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setObjectElement(0, this.createJsonObject(objectElementValue1));
    testJsonArray.setObjectElement(1, this.createJsonObject(objectElementValue2));
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals(objectElementValue1, this.getObjectElement(array, 0));
    assertEquals(objectElementValue2, this.getObjectElement(array, 1));
    
    testJsonArray.appendObjectElement(this.createJsonObject(objectElementValue3));
    
    assertEquals(3, this.getArrayLength(array));
    assertEquals(objectElementValue1, this.getObjectElement(array, 0));
    assertEquals(objectElementValue2, this.getObjectElement(array, 1));
    assertEquals(objectElementValue3, this.getObjectElement(array, 2));
  }
  
  /**
   * Test the removal of elements.
   * <p>
   * This test asserts that the removal of elements removes corresponding
   * elements from the the underlying JSON array.
   */
  @Test
  public void testRemoveElements() {
    J array = this.createUnderlyingJsonArray();
    
    JsonArray testJsonArray = this.createJsonArray(array);
    
    testJsonArray.setStringElement(0, "one");
    testJsonArray.setStringElement(1, "two");
    testJsonArray.setStringElement(2, "three");
    testJsonArray.setStringElement(3, "four");
    testJsonArray.setStringElement(4, "five");
    
    assertEquals(5, this.getArrayLength(array));
    assertEquals("one", this.getStringElement(array, 0));
    assertEquals("two", this.getStringElement(array, 1));
    assertEquals("three", this.getStringElement(array, 2));
    assertEquals("four", this.getStringElement(array, 3));
    assertEquals("five", this.getStringElement(array, 4));
    
    testJsonArray.removeElements(1, 3);
    
    assertEquals(2, this.getArrayLength(array));
    assertEquals("one", this.getStringElement(array, 0));
    assertEquals("five", this.getStringElement(array, 1));
  }
  
  /**
   * Create a JSON object with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  protected abstract JsonObject createJsonObject(J object);

  /**
   * Create a JSON array with the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The JSON array.
   */
  protected abstract JsonArray createJsonArray(J array);

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
   * Retrieve the length of the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The length of the underlying JSON array.
   * @see #setArrayLength(Object, int)
   */
  protected abstract int getArrayLength(J array);
  
  /**
   * Set the length of the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param length The length of the underlying JSON array.
   * @see #getArrayLength(Object)
   */
  protected abstract void setArrayLength(J array, int length);
  
  /**
   * Retrieve the boolean value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The boolean value of the element.
   * @see #setBooleanElement(Object, int, Boolean)
   */
  protected abstract Boolean getBooleanElement(J array, int elementIndex);
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given boolean value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The boolean value of the element.
   * @see #getBooleanElement(Object, int)
   */
  protected abstract void setBooleanElement(J array, int elementIndex, Boolean elementValue);
  
  /**
   * Retrieve the numeric value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The numeric value of the element.
   * @see #setNumberElement(Object, int, Number)
   */
  protected abstract Number getNumberElement(J array, int elementIndex);
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given numeric value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The numeric value of the element.
   * @see #getNumberElement(Object, int)
   */
  protected abstract void setNumberElement(J array, int elementIndex, Number elementValue);
  
  /**
   * Retrieve the string value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The string value of the element.
   * @see #setStringElement(Object, int, String)
   */
  protected abstract String getStringElement(J array, int elementIndex);
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given string value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The string value of the element.
   * @see #getStringElement(Object, int)
   */
  protected abstract void setStringElement(J array, int elementIndex, String elementValue);
  
  /**
   * Retrieve the object value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The object value of the element.
   * @see #setObjectElement(Object, int, Object)
   */
  protected abstract J getObjectElement(J array, int elementIndex);
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given object value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The object value of the element.
   * @see #getObjectElement(Object, int)
   */
  protected abstract void setObjectElement(J array, int elementIndex, J elementValue);
}
