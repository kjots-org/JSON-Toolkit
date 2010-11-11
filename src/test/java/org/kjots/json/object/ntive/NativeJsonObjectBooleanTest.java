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

/**
 * Native JSON Object Boolean Test.
 * <p>
 * Created: 12th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonObjectBooleanTest {
  /**
   * Test Native JSON Object.
   */
  public static class TestNativeJsonObject extends NativeJsonObject {
    /** The test boolean property.*/
    @NativeJsonProperty
    private Boolean testBooleanProperty;
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
}
