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

import org.junit.Test;

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
  public class TestNativeJsonObject extends NativeJsonObject {
    /** The test string property.*/
    @NativeJsonProperty
    private String testStringProperty;
  }
  
  /**
   * Test the retrieval of the string value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a string property.
   */
  @Test
  public void testGetStringProperty() {
    TestNativeJsonObject testJsonObject = new TestNativeJsonObject();
    
    testJsonObject.testStringProperty = "Test String Property Value";
    
    assertEquals("Test String Property Value", testJsonObject.getStringProperty("testStringProperty"));
  }
  
  /**
   * Test the setting of the string value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a string property.
   */
  @Test
  public void testSetStringProperty() {
    TestNativeJsonObject testJsonObject = new TestNativeJsonObject();
    
    testJsonObject.setStringProperty("testStringProperty", "Test String Property Value");
    
    assertEquals("Test String Property Value", testJsonObject.testStringProperty);
  }
}
