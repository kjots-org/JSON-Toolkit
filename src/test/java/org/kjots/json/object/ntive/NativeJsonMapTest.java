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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.simple.SimpleJsonObjectModule;

import com.google.inject.Guice;

/**
 * Native JSON Map Test.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonMapTest {
  /**
   * Test Property JSON Object.
   */
  public static interface TestPropertyJsonObject extends JsonObject {
  }

  /**
   * Test Base Native JSON Map.
   */
  public static class TestBaseNativeJsonMap extends NativeJsonMap {
  }
  
  /**
   * Test Not Base Native JSON Map.
   */
  public static class TestNotBaseNativeJsonMap extends NativeJsonMap {
  }
  
  /**
   * Test Native JSON Map.
   */
  public static class TestNativeJsonMap extends TestBaseNativeJsonMap {
  }
  
  /** The test native JSON map. */
  private TestNativeJsonMap testNativeJsonMap;
  
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
    this.testNativeJsonMap = new TestNativeJsonMap();
  }
  
  /**
   * Test {@link NativeJsonObject#getJsonObjectClass()}.
   * <p>
   * This test asserts that the {@link {@link NativeJsonObject#getJsonObjectClass()}
   * returns the class of the native JSON map.
   */
  @Test
  public void testGetJsonObjectClass() {
    assertEquals(TestNativeJsonMap.class, testNativeJsonMap.getJsonObjectClass());
  }
  
  /**
   * Test the casting of a native JSON map.
   * <p>
   * This test asserts that the casting of a native JSON map to a
   * compatible JSON object class returns the native JSON map.
   */
  @Test
  public void testCastByClassToCompatibleClass() {
    TestBaseNativeJsonMap testBaseNativeJsonObject = testNativeJsonMap.cast(TestBaseNativeJsonMap.class);
    
    assertSame("testNativeJsonMap !== testBaseNativeJsonObject", testNativeJsonMap, testBaseNativeJsonObject);
  }
  
  /**
   * Test the casting of a native JSON map.
   * <p>
   * This test asserts that the casting of a native JSON map to an
   * incompatible JSON object class throws a {@link ClassCastException}.
   */
  @Test(expected = ClassCastException.class)
  public void testCastByClassToIncompatibleClass() {
    testNativeJsonMap.cast(TestNotBaseNativeJsonMap.class);
  }
  
  /**
   * Test the casting of a native JSON map.
   * <p>
   * This test asserts that the casting of a native JSON map to a
   * compatible JSON object class via the classname returns the native JSON
   * object.
   */
  @Test
  public void testCastByClassNameToCompatibleClass() {
    TestBaseNativeJsonMap testBaseNativeJsonObject = testNativeJsonMap.cast(TestBaseNativeJsonMap.class.getName());
    
    assertSame("testNativeJsonMap !== testBaseNativeJsonObject", testNativeJsonMap, testBaseNativeJsonObject);
  }
  
  /**
   * Test the casting of a native JSON map.
   * <p>
   * This test asserts that the casting of a native JSON map to an
   * incompatible JSON object class via the classname throws a {@link ClassCastException}.
   */
  @Test(expected = ClassCastException.class)
  public void testCastByClassNameToIncompatibleClass() {
    testNativeJsonMap.cast(TestNotBaseNativeJsonMap.class.getName());
  }
  
  /**
   * Test the retrieval of the names of the properties.
   * <p>
   * This test asserts that the native JSON map correctly returns the names
   * of the existing properties.
   */
  @Test
  public void testGetPropertyNames() {
    assertEquals(Collections.emptySet(), testNativeJsonMap.getPropertyNames());
    
    testNativeJsonMap.setStringProperty("testProperty", "Test String Property Value");
    
    assertEquals(new HashSet<String>(Arrays.asList("testProperty")), testNativeJsonMap.getPropertyNames());
  }
  
  /**
   * Test the determination of the existence of a property.
   * <p>
   * This test asserts that the native JSON map correctly reports the
   * existence or non-existence of a property.
   */
  @Test
  public void testHasProperty() {
    assertFalse("testNativeJsonMap.hasProperty(\"testProperty\") != false", testNativeJsonMap.hasProperty("testProperty"));
    
    testNativeJsonMap.setStringProperty("testProperty", null);
    
    assertTrue("testNativeJsonMap.hasProperty(\"testProperty\") != true", testNativeJsonMap.hasProperty("testProperty"));
    
    testNativeJsonMap.setStringProperty("testProperty", "Test String Property Value");
    
    assertTrue("testNativeJsonMap.hasProperty(\"testProperty\") != true", testNativeJsonMap.hasProperty("testProperty"));
  }
  
  /**
   * Test the determination of the <code>null</code> value of a property.
   * <p>
   * This test asserts that the native JSON map correctly reports that a
   * property exists but has a <code>null</code> value.
   */
  @Test
  public void testIsPropertyNull() {
    assertFalse("testNativeJsonMap.isNullProperty(\"testProperty\") != false", testNativeJsonMap.isNullProperty("testProperty"));
    
    testNativeJsonMap.setStringProperty("testProperty", null);
    
    assertTrue("testNativeJsonMap.isNullProperty(\"testProperty\") != true", testNativeJsonMap.isNullProperty("testProperty"));
    
    testNativeJsonMap.setStringProperty("testProperty", "Test String Property Value");
    
    assertFalse("testNativeJsonMap.isNullProperty(\"testProperty\") != false", testNativeJsonMap.isNullProperty("testProperty"));
  }
  
  /**
   * Test the determination of a boolean value of a property.
   * <p>
   * This test asserts that the native JSON map correctly reports that a
   * property exists and has a boolean value.
   */
  @Test
  public void testIsBooleanProperty() {
    assertFalse("testNativeJsonMap.isBooleanProperty(\"testBooleanProperty\") != false", testNativeJsonMap.isBooleanProperty("testBooleanProperty"));
    
    testNativeJsonMap.map.put("testBooleanProperty", null);
    
    assertFalse("testNativeJsonMap.isBooleanProperty(\"testBooleanProperty\") != false", testNativeJsonMap.isBooleanProperty("testBooleanProperty"));
    
    testNativeJsonMap.map.put("testBooleanProperty", Boolean.TRUE);
    
    assertTrue("testNativeJsonMap.isBooleanProperty(\"testBooleanProperty\") != true", testNativeJsonMap.isBooleanProperty("testBooleanProperty"));
  }
  
  /**
   * Test the retrieval of the boolean value of a property.
   * <p>
   * This test asserts that the native JSON map correctly retrieves the
   * value of a boolean property.
   */
  @Test
  public void testGetBooleanProperty() {
    testNativeJsonMap.map.put("testBooleanProperty", Boolean.TRUE);
    
    assertEquals(true, testNativeJsonMap.getBooleanProperty("testBooleanProperty").booleanValue());
  }
  
  /**
   * Test the setting of the boolean value of a property.
   * <p>
   * This test asserts that the native JSON map correctly sets the value of
   * a boolean property.
   */
  @Test
  public void testSetBooleanProperty() {
    testNativeJsonMap.setBooleanProperty("testBooleanProperty", Boolean.TRUE);
    
    assertEquals(true, ((Boolean)testNativeJsonMap.map.get("testBooleanProperty")).booleanValue());
    assertTrue("testNativeJsonMap.hasProperty(\"testBooleanProperty\") != true", testNativeJsonMap.hasProperty("testBooleanProperty"));
  }
  
  /**
   * Test the determination of a numerical value of a property.
   * <p>
   * This test asserts that the native JSON map correctly reports that a
   * property exists and has a numerical value.
   */
  @Test
  public void testIsNumberProperty() {
    assertFalse("testNativeJsonMap.isNumberProperty(\"testNumberProperty\") != false", testNativeJsonMap.isNumberProperty("testNumberProperty"));
    
    testNativeJsonMap.map.put("testNumberProperty", null);
    
    assertFalse("testNativeJsonMap.isNumberProperty(\"testNumberProperty\") != false", testNativeJsonMap.isNumberProperty("testNumberProperty"));
    
    testNativeJsonMap.map.put("testNumberProperty", Double.valueOf(3.14));
    
    assertTrue("testNativeJsonMap.isNumberProperty(\"testNumberProperty\") != true", testNativeJsonMap.isNumberProperty("testNumberProperty"));
  }
  
  /**
   * Test the retrieval of the numerical value of a property.
   * <p>
   * This test asserts that the native JSON map correctly retrieves the
   * value of a numerical property.
   */
  @Test
  public void testGetNumberProperty() {
    testNativeJsonMap.map.put("testNumberProperty", Double.valueOf(3.14));
    
    assertEquals(3.14, testNativeJsonMap.getNumberProperty("testNumberProperty").doubleValue(), 0.001);
  }
  
  /**
   * Test the setting of the numerical value of a property.
   * <p>
   * This test asserts that the native JSON map correctly sets the value of
   * a numerical property.
   */
  @Test
  public void testSetNumberProperty() {
    testNativeJsonMap.setNumberProperty("testNumberProperty", Double.valueOf(3.14));
    
    assertEquals(3.14, ((Number)testNativeJsonMap.map.get("testNumberProperty")).doubleValue(), 0.001);
    assertTrue("testNativeJsonMap.hasProperty(\"testNumberProperty\") != true", testNativeJsonMap.hasProperty("testNumberProperty"));
  }
  
  /**
   * Test the determination of a string value of a property.
   * <p>
   * This test asserts that the native JSON map correctly reports that a
   * property exists and has a string value.
   */
  @Test
  public void testIsStringProperty() {
    assertFalse("testNativeJsonMap.isStringProperty(\"testStringProperty\") != false", testNativeJsonMap.isStringProperty("testStringProperty"));
    
    testNativeJsonMap.map.put("testStringProperty", null);
    
    assertFalse("testNativeJsonMap.isStringProperty(\"testStringProperty\") != false", testNativeJsonMap.isStringProperty("testStringProperty"));
    
    testNativeJsonMap.map.put("testStringProperty", "Test String Property Value");
    
    assertTrue("testNativeJsonMap.isStringProperty(\"testStringProperty\") != true", testNativeJsonMap.isStringProperty("testStringProperty"));
  }
  
  /**
   * Test the retrieval of the string value of a property.
   * <p>
   * This test asserts that the native JSON map correctly retrieves the
   * value of a string property.
   */
  @Test
  public void testGetStringProperty() {
    testNativeJsonMap.map.put("testStringProperty", "Test String Property Value");
    
    assertEquals("Test String Property Value", testNativeJsonMap.getStringProperty("testStringProperty"));
  }
  
  /**
   * Test the setting of the string value of a property.
   * <p>
   * This test asserts that the native JSON map correctly sets the value of
   * a string property.
   */
  @Test
  public void testSetStringProperty() {
    testNativeJsonMap.setStringProperty("testStringProperty", "Test String Property Value");
    
    assertEquals("Test String Property Value", (String)testNativeJsonMap.map.get("testStringProperty"));
    assertTrue("testNativeJsonMap.hasProperty(\"testStringProperty\") != true", testNativeJsonMap.hasProperty("testStringProperty"));
  }
  
  /**
   * Test the determination of a object value of a property.
   * <p>
   * This test asserts that the native JSON map correctly reports that a
   * property exists and has a object value.
   */
  @Test
  public void testIsObjectProperty() {
    assertFalse("testNativeJsonMap.isObjectProperty(\"testObjectProperty\") != false", testNativeJsonMap.isObjectProperty("testObjectProperty"));
    
    testNativeJsonMap.map.put("testObjectProperty", null);
    
    assertFalse("testNativeJsonMap.isObjectProperty(\"testObjectProperty\") != false", testNativeJsonMap.isObjectProperty("testObjectProperty"));
    
    testNativeJsonMap.map.put("testObjectProperty", JsonObjectFactory.get().createJsonObject());
    
    assertTrue("testNativeJsonMap.isObjectProperty(\"testObjectProperty\") != true", testNativeJsonMap.isObjectProperty("testObjectProperty"));
  }
  
  /**
   * Test the retrieval of the object value of a property.
   * <p>
   * This test asserts that the native JSON map correctly retrieves the
   * value of a object property.
   */
  @Test
  public void testGetObjectProperty() {
    JsonObject jsonObject = JsonObjectFactory.get().createJsonObject();
    
    testNativeJsonMap.map.put("testObjectProperty", jsonObject);
    
    assertEquals(jsonObject, testNativeJsonMap.getObjectProperty("testObjectProperty"));
  }
  
  /**
   * Test the retrieval of the object value of a property with a class.
   * <p>
   * This test asserts that the native JSON map correctly retrieves the
   * value of a object property, and that the class of the retrieved object
   * value matches the native JSON map class provided to the retrieval
   * method.
   */
  @Test
  public void testGetObjectPropertyWithNativeClass() {
    JsonObject jsonObject = new TestNativeJsonMap();
    
    testNativeJsonMap.map.put("testObjectProperty", jsonObject);
    
    TestNativeJsonMap jsonObjectPropertyValue = testNativeJsonMap.getObjectProperty("testObjectProperty", TestNativeJsonMap.class);
    
    assertEquals(jsonObject, jsonObjectPropertyValue);
  }
  
  /**
   * Test the retrieval of the object value of a property with a class.
   * <p>
   * This test asserts that the native JSON map correctly retrieves the
   * value of a object property, and that the class of the retrieved object
   * value matches the non-native JSON map class provided to the retrieval
   * method.
   */
  @Test
  public void testGetObjectPropertyWithNonNativeClass() {
    JsonObject jsonObject = JsonObjectFactory.get().createJsonObject();
    
    testNativeJsonMap.map.put("testObjectProperty", jsonObject);
    
    TestPropertyJsonObject jsonObjectPropertyValue = testNativeJsonMap.getObjectProperty("testObjectProperty", TestPropertyJsonObject.class);
    
    assertEquals(jsonObject, jsonObjectPropertyValue);
  }
  
  /**
   * Test the setting of the object value of a property.
   * <p>
   * This test asserts that the native JSON map correctly sets the value of
   * a object property.
   */
  @Test
  public void testSetObjectProperty() {
    JsonObject jsonObject = JsonObjectFactory.get().createJsonObject();
    
    testNativeJsonMap.setObjectProperty("testObjectProperty", jsonObject);
    
    assertEquals(jsonObject, (JsonObject)testNativeJsonMap.map.get("testObjectProperty"));
    assertTrue("testNativeJsonMap.hasProperty(\"testObjectProperty\") != true", testNativeJsonMap.hasProperty("testObjectProperty"));
  }

  /**
   * Test the deletion of a property.
   * <p>
   * This test asserts that a property is correctly deleted from the native
   * JSON object.
   */
  @Test
  public void testDeleteProperty() {
    testNativeJsonMap.setStringProperty("testProperty", "Test String Property Value");
    
    assertTrue("testNativeJsonMap.deleteProperty(\"testProperty\") != true", testNativeJsonMap.deleteProperty("testProperty"));
    assertFalse("testNativeJsonMap.hasProperty(\"testProperty\") != false", testNativeJsonMap.hasProperty("testProperty"));
    assertNull("testNativeJsonMap.testProperty != null", testNativeJsonMap.map.get("testProperty"));
    
    assertFalse("testNativeJsonMap.deleteProperty(\"testProperty\") != false", testNativeJsonMap.deleteProperty("testProperty"));
    assertFalse("testNativeJsonMap.hasProperty(\"testProperty\") != false", testNativeJsonMap.hasProperty("testProperty"));
    assertNull("testNativeJsonMap.testProperty != null", testNativeJsonMap.map.get("testProperty"));
  }
}
