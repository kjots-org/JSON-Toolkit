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
import org.junit.BeforeClass;
import org.junit.Test;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.simple.SimpleJsonObjectModule;

import com.google.inject.Guice;

/**
 * Native JSON Object Object Test.
 * <p>
 * Created: 12th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonObjectObjectTest {
  /**
   * Test Property JSON Object.
   */
  public static interface TestPropertyJsonObject extends JsonObject {
  }

  /**
   * Test Native JSON Object.
   */
  public static class TestNativeJsonObject extends NativeJsonObject {
    /** The test object property.*/
    @NativeJsonProperty
    private JsonObject testObjectProperty;
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
}
