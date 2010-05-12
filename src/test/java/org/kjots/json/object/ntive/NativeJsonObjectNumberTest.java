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

import org.junit.Test;

/**
 * Native JSON Object Number Test.
 * <p>
 * Created: 12th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonObjectNumberTest {
  /**
   * Test Native JSON Object.
   */
  public static class TestNativeJsonObject extends NativeJsonObject {
    /** The test numerical property.*/
    @NativeJsonProperty
    private Number testNumberProperty;
  }
  
  /**
   * Test the determination of a numerical value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has a numerical value.
   */
  @Test
  public void testIsNumberProperty() {
    TestNativeJsonObject testNativeJsonObject = new TestNativeJsonObject();
    
    assertFalse("testNativeJsonObject.isNumberProperty(\"testNumberProperty\") != false", testNativeJsonObject.isNumberProperty("testNumberProperty"));
    
    testNativeJsonObject.testNumberProperty = null;
    testNativeJsonObject.setHasProperty("testNumberProperty");
    
    assertFalse("testNativeJsonObject.isNumberProperty(\"testNumberProperty\") != false", testNativeJsonObject.isNumberProperty("testNumberProperty"));
    
    testNativeJsonObject.testNumberProperty = Double.valueOf(3.14);
    
    assertTrue("testNativeJsonObject.isNumberProperty(\"testNumberProperty\") != true", testNativeJsonObject.isNumberProperty("testNumberProperty"));
  }
  
  /**
   * Test the retrieval of the numerical value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a numerical property.
   */
  @Test
  public void testGetNumberProperty() {
    TestNativeJsonObject testNativeJsonObject = new TestNativeJsonObject();
    
    testNativeJsonObject.testNumberProperty = Double.valueOf(3.14);
    testNativeJsonObject.setHasProperty("testNumberProperty");
    
    assertEquals(3.14, testNativeJsonObject.getNumberProperty("testNumberProperty").doubleValue(), 0.001);
  }
  
  /**
   * Test the setting of the numerical value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a numerical property.
   */
  @Test
  public void testSetNumberProperty() {
    TestNativeJsonObject testNativeJsonObject = new TestNativeJsonObject();
    
    testNativeJsonObject.setNumberProperty("testNumberProperty", Double.valueOf(3.14));
    
    assertEquals(3.14, testNativeJsonObject.testNumberProperty.doubleValue(), 0.001);
    assertTrue("testNativeJsonObject.hasProperty(\"testNumberProperty\") != true", testNativeJsonObject.hasProperty("testNumberProperty"));
  }
}
