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

import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.simple.SimpleJsonObjectModule;

import com.google.inject.Guice;

/**
 * Native JSON Array Test.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class NativeJsonArrayTest {
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject {
  }
  
  /**
   * Test Native JSON Object.
   */
  public static class TestNativeJsonObject extends NativeJsonObject {
  }
  
  /** The test native JSON array. */
  private NativeJsonArray testNativeJsonArray;
  
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
    this.testNativeJsonArray = new NativeJsonArray();
  }
  
  /**
   * Test the retrieval of the length of the array.
   * <p>
   * This test asserts that the native JSON array correctly retrieves the length
   * of the array.
   */
  @Test
  public void testGetLength() {
    assertEquals(0, testNativeJsonArray.getLength());
    
    testNativeJsonArray.list.addAll(Arrays.asList(null, null, null, null, null));
    
    assertEquals(5, testNativeJsonArray.getLength());
    
    testNativeJsonArray.list.clear();
    
    assertEquals(0, testNativeJsonArray.getLength());
  }
  
  /**
   * Test the settings of the length of the array.
   * <p>
   * This test asserts that the native JSON array correctly sets the length of
   * the array.
   */
  @Test
  public void testSetLength() {
    assertEquals(0, testNativeJsonArray.list.size());
    
    testNativeJsonArray.setLength(5);
    
    assertEquals(5, testNativeJsonArray.list.size());
    
    testNativeJsonArray.setLength(0);
    
    assertEquals(0, testNativeJsonArray.list.size());
  }
  
  /**
   * Test the determination of the <code>null</code> value of an element.
   * <p>
   * This test asserts that the native JSON array correctly reports that an
   * element has a <code>null</code> value.
   */
  @Test
  public void testIsNullElement() {
    testNativeJsonArray.list.add(null);
    testNativeJsonArray.list.add(JsonObjectFactory.get().createJsonObject());
    
    assertTrue("isNull(0) != true", testNativeJsonArray.isNullElement(0));
    assertFalse("isNull(1) != false", testNativeJsonArray.isNullElement(1));
  }
  
  /**
   * Test the determination of a boolean value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that an
   * element has a boolean value.
   */
  @Test
  public void testIsBooleanElement() {
    testNativeJsonArray.list.add(Boolean.TRUE);
    testNativeJsonArray.list.add(Double.valueOf(3.14));
    testNativeJsonArray.list.add("Test String Element Value");
    testNativeJsonArray.list.add(JsonObjectFactory.get().createJsonObject());
    testNativeJsonArray.list.add(null);
    
    assertTrue("isBooleanElement(0) != true", testNativeJsonArray.isBooleanElement(0));
    assertFalse("isBooleanElement(1) != false", testNativeJsonArray.isBooleanElement(1));
    assertFalse("isBooleanElement(2) != false", testNativeJsonArray.isBooleanElement(2));
    assertFalse("isBooleanElement(3) != false", testNativeJsonArray.isBooleanElement(3));
    assertFalse("isBooleanElement(4) != false", testNativeJsonArray.isBooleanElement(4));
  }

  /**
   * Test the retrieval of the boolean value of an element.
   * <p>
   * This test asserts that the native JSON array correctly retrieves a boolean
   * element from the array.
   */
  @Test
  public void testGetBooleanElement() {
    testNativeJsonArray.list.add(Boolean.TRUE);
    
    assertEquals(true, testNativeJsonArray.getBooleanElement(0).booleanValue());
  }
  
  /**
   * Test the setting of the boolean value of an element.
   * <p>
   * This test asserts that the native JSON array correctly sets a boolean
   * element of the array.
   */
  @Test
  public void testSetBooleanElement() {
    testNativeJsonArray.setBooleanElement(0, true);
    
    assertEquals(true, ((Boolean)testNativeJsonArray.list.get(0)).booleanValue());
  }
  
  /**
   * Test the insertion of a boolean value element.
   * <p>
   * This test asserts that the native JSON array correctly inserts a boolean
   * element into the array.
   */
  @Test
  public void testInsertBooleanElement() {
    testNativeJsonArray.setBooleanElement(0, true);
    testNativeJsonArray.setBooleanElement(1, true);
    
    assertEquals(2, testNativeJsonArray.list.size());
    assertEquals(true, ((Boolean)testNativeJsonArray.list.get(0)).booleanValue());
    assertEquals(true, ((Boolean)testNativeJsonArray.list.get(1)).booleanValue());
    
    testNativeJsonArray.insertBooleanElement(1, false);
    
    assertEquals(3, testNativeJsonArray.list.size());
    assertEquals(true, ((Boolean)testNativeJsonArray.list.get(0)).booleanValue());
    assertEquals(false, ((Boolean)testNativeJsonArray.list.get(1)).booleanValue());
    assertEquals(true, ((Boolean)testNativeJsonArray.list.get(2)).booleanValue());
  }
  
  /**
   * Test the determination of a numeric value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that an
   * element has a numeric value.
   */
  @Test
  public void testIsNumberElement() {
    testNativeJsonArray.list.add(Boolean.TRUE);
    testNativeJsonArray.list.add(Double.valueOf(3.14));
    testNativeJsonArray.list.add("Test String Element Value");
    testNativeJsonArray.list.add(JsonObjectFactory.get().createJsonObject());
    testNativeJsonArray.list.add(null);
    
    assertFalse("isNumberElement(0) != false", testNativeJsonArray.isNumberElement(0));
    assertTrue("isNumberElement(1) != true", testNativeJsonArray.isNumberElement(1));
    assertFalse("isNumberElement(2) != false", testNativeJsonArray.isNumberElement(2));
    assertFalse("isNumberElement(3) != false", testNativeJsonArray.isNumberElement(3));
    assertFalse("isNumberElement(4) != false", testNativeJsonArray.isNumberElement(4));
  }

  /**
   * Test the retrieval of the numeric value of an element.
   * <p>
   * This test asserts that the native JSON array correctly retrieves a number
   * element from the array.
   */
  @Test
  public void testGetNumberElement() {
    testNativeJsonArray.list.add(Double.valueOf(3.14));
    
    assertEquals(3.14, testNativeJsonArray.getNumberElement(0).doubleValue(), 0.001);
  }
  
  /**
   * Test the setting of the numeric value of an element.
   * <p>
   * This test asserts that the native JSON array correctly sets a number
   * element of the array.
   */
  @Test
  public void testSetNumberElement() {
    testNativeJsonArray.setNumberElement(0, 3.14);
    
    assertEquals(3.14, ((Number)testNativeJsonArray.list.get(0)).doubleValue(), 0.001);
  }
  
  /**
   * Test the insertion of a number value element.
   * <p>
   * This test asserts that the native JSON array correctly inserts a number
   * element into the array.
   */
  @Test
  public void testInsertNumberElement() {
    testNativeJsonArray.setNumberElement(0, 1.1);
    testNativeJsonArray.setNumberElement(1, 3.3);
    
    assertEquals(2, testNativeJsonArray.list.size());
    assertEquals(1.1, ((Number)testNativeJsonArray.list.get(0)).doubleValue(), 0.01);
    assertEquals(3.3, ((Number)testNativeJsonArray.list.get(1)).doubleValue(), 0.01);
    
    testNativeJsonArray.insertNumberElement(1, 2.2);
    
    assertEquals(3, testNativeJsonArray.list.size());
    assertEquals(1.1, ((Number)testNativeJsonArray.list.get(0)).doubleValue(), 0.01);
    assertEquals(2.2, ((Number)testNativeJsonArray.list.get(1)).doubleValue(), 0.01);
    assertEquals(3.3, ((Number)testNativeJsonArray.list.get(2)).doubleValue(), 0.01);
  }

  /**
   * Test the determination of a string value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that an
   * element has a string value.
   */
  @Test
  public void testIsStringElement() {
    testNativeJsonArray.list.add(Boolean.TRUE);
    testNativeJsonArray.list.add(Double.valueOf(3.14));
    testNativeJsonArray.list.add("Test String Element Value");
    testNativeJsonArray.list.add(JsonObjectFactory.get().createJsonObject());
    testNativeJsonArray.list.add(null);
    
    assertFalse("isStringElement(0) != false", testNativeJsonArray.isStringElement(0));
    assertFalse("isStringElement(1) != false", testNativeJsonArray.isStringElement(1));
    assertTrue("isStringElement(2) != true", testNativeJsonArray.isStringElement(2));
    assertFalse("isStringElement(3) != false", testNativeJsonArray.isStringElement(3));
    assertFalse("isStringElement(4) != false", testNativeJsonArray.isStringElement(4));
  }

  /**
   * Test the retrieval of the string value of an element.
   * <p>
   * This test asserts that the native JSON array correctly retrieves a string
   * element from the array.
   */
  @Test
  public void testGetStringElement() {
    testNativeJsonArray.list.add("Test String Element Value");
    
    assertEquals("Test String Element Value", testNativeJsonArray.getStringElement(0));
  }
  
  /**
   * Test the setting of the string value of an element.
   * <p>
   * This test asserts that the native JSON array correctly sets a string
   * element of the array.
   */
  @Test
  public void testSetStringElement() {
    testNativeJsonArray.setStringElement(0, "Test String Property Value");
    
    assertEquals("Test String Property Value", (String)testNativeJsonArray.list.get(0));
  }
  
  /**
   * Test the insertion of a string value element.
   * <p>
   * This test asserts that the native JSON array correctly inserts a string
   * element into the array.
   */
  @Test
  public void testInsertStringElement() {
    testNativeJsonArray.setStringElement(0, "one");
    testNativeJsonArray.setStringElement(1, "three");
    
    assertEquals(2, testNativeJsonArray.list.size());
    assertEquals("one", (String)testNativeJsonArray.list.get(0));
    assertEquals("three", (String)testNativeJsonArray.list.get(1));
    
    testNativeJsonArray.insertStringElement(1, "two");
    
    assertEquals(3, testNativeJsonArray.list.size());
    assertEquals("one", (String)testNativeJsonArray.list.get(0));
    assertEquals("two", (String)testNativeJsonArray.list.get(1));
    assertEquals("three", (String)testNativeJsonArray.list.get(2));
  }

  /**
   * Test the determination of an object value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that an
   * element has an object value.
   */
  @Test
  public void testIsObjectElement() {
    testNativeJsonArray.list.add(Boolean.TRUE);
    testNativeJsonArray.list.add(Double.valueOf(3.14));
    testNativeJsonArray.list.add("Test String Element Value");
    testNativeJsonArray.list.add(JsonObjectFactory.get().createJsonObject());
    testNativeJsonArray.list.add(null);
    
    assertFalse("isObjectElement(0) != false", testNativeJsonArray.isObjectElement(0));
    assertFalse("isObjectElement(1) != false", testNativeJsonArray.isObjectElement(1));
    assertFalse("isObjectElement(2) != false", testNativeJsonArray.isObjectElement(2));
    assertTrue("isObjectElement(3) != true", testNativeJsonArray.isObjectElement(3));
    assertFalse("isObjectElement(4) != false", testNativeJsonArray.isObjectElement(4));
  }

  /**
   * Test the retrieval of the object value of an element.
   * <p>
   * This test asserts that the native JSON array correctly retrieves an object
   * element from the array.
   */
  @Test
  public void testGetObjectElement() {
    JsonObject jsonObject = JsonObjectFactory.get().createJsonObject();
    
    testNativeJsonArray.list.add(jsonObject);
    
    assertEquals(jsonObject, testNativeJsonArray.getObjectElement(0));
  }
  
  /**
   * Test the retrieval of the object value of an element with a class.
   * <p>
   * This test asserts that the native JSON array correctly retrieves an object
   * element from the array, and that the class of the retrieved object value
   * matches the native JSON object class provided to the retrieval method.
   * <p>
   */
  @Test
  public void testGetObjectElementWithNativeClass() {
    JsonObject jsonObject = new TestNativeJsonObject();
    
    testNativeJsonArray.list.add(jsonObject);
    
    TestNativeJsonObject testNativeJsonObject = testNativeJsonArray.getObjectElement(0, TestNativeJsonObject.class);
    
    assertEquals(jsonObject, testNativeJsonObject);
  }
  
  /**
   * Test the retrieval of the object value of an element with a class.
   * <p>
   * This test asserts that the native JSON array correctly retrieves an object
   * element from the array, and that the class of the retrieved object value
   * matches the non-native JSON object class provided to the retrieval method.
   * <p>
   */
  @Test
  public void testGetObjectElementWithNonNativeClass() {
    JsonObject jsonObject = JsonObjectFactory.get().createJsonObject();
    
    testNativeJsonArray.list.add(jsonObject);
    
    TestJsonObject testJsonObject = testNativeJsonArray.getObjectElement(0, TestJsonObject.class);
    
    assertEquals(jsonObject, testJsonObject);
  }
  
  /**
   * Test the setting of the object value of an element.
   * <p>
   * This test asserts that the native JSON array correctly sets an object
   * element of the array.
   */
  @Test
  public void testSetObjectElement() {
    JsonObject jsonObject = JsonObjectFactory.get().createJsonObject();
    
    testNativeJsonArray.setObjectElement(0, jsonObject);
    
    assertEquals(jsonObject, (JsonObject)testNativeJsonArray.list.get(0));
  }
  
  /**
   * Test the insertion of an object value element.
   * <p>
   * This test asserts that the native JSON array correctly inserts an object
   * element into the array.
   */
  @Test
  public void testInsertObjectElement() {
    JsonObject jsonObject1 = JsonObjectFactory.get().createJsonObject();
    JsonObject jsonObject2 = JsonObjectFactory.get().createJsonObject();
    JsonObject jsonObject3 = JsonObjectFactory.get().createJsonObject();
    
    testNativeJsonArray.setObjectElement(0, jsonObject1);
    testNativeJsonArray.setObjectElement(1, jsonObject3);
    
    assertEquals(2, testNativeJsonArray.list.size());
    assertEquals(jsonObject1, (JsonObject)testNativeJsonArray.list.get(0));
    assertEquals(jsonObject3, (JsonObject)testNativeJsonArray.list.get(1));
    
    testNativeJsonArray.insertObjectElement(1, jsonObject2);
    
    assertEquals(3, testNativeJsonArray.list.size());
    assertEquals(jsonObject1, (JsonObject)testNativeJsonArray.list.get(0));
    assertEquals(jsonObject2, (JsonObject)testNativeJsonArray.list.get(1));
    assertEquals(jsonObject3, (JsonObject)testNativeJsonArray.list.get(2));
  }
  
  /**
   * Test the removal of elements.
   * <p>
   * This test asserts that the native JSON array correctly removes elements
   * from the array.
   */
  @Test
  public void testRemoveElements() {
    testNativeJsonArray.setStringElement(0, "one");
    testNativeJsonArray.setStringElement(1, "two");
    testNativeJsonArray.setStringElement(2, "three");
    testNativeJsonArray.setStringElement(3, "four");
    testNativeJsonArray.setStringElement(4, "five");
    
    assertEquals(5, testNativeJsonArray.list.size());
    assertEquals("one", (String)testNativeJsonArray.list.get(0));
    assertEquals("two", (String)testNativeJsonArray.list.get(1));
    assertEquals("three", (String)testNativeJsonArray.list.get(2));
    assertEquals("four", (String)testNativeJsonArray.list.get(3));
    assertEquals("five", (String)testNativeJsonArray.list.get(4));
    
    testNativeJsonArray.removeElements(1, 3);
    
    assertEquals(2, testNativeJsonArray.list.size());
    assertEquals("one", (String)testNativeJsonArray.list.get(0));
    assertEquals("five", (String)testNativeJsonArray.list.get(1));
  }
}
