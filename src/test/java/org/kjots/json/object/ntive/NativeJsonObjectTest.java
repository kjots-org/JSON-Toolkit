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

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.kjots.json.object.shared.JsonObject;

/**
 * Native JSON Property Test.
 * <p>
 * Created: 12th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonObjectTest {
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject {
  }
  
  /**
   * Test Native JSON Object.
   */
  public class TestNativeJsonObject extends NativeJsonObject implements TestJsonObject {
    /** The test property.*/
    @NativeJsonProperty
    @SuppressWarnings("unused")
    private String testProperty;
  }
  
  /**
   * Test the determination of the existence of a property.
   * <p>
   * This test asserts that the JSON object correctly reports the existence or
   * non-existence of a property.
   */
  @Test
  public void testHasProperty() {
    TestJsonObject testJsonObject = new TestNativeJsonObject();
    
    assertFalse("testJsonObject.hasProperty(\"testProperty\") != false", testJsonObject.hasProperty("testProperty"));
    
    testJsonObject.setStringProperty("testProperty", null);
    
    assertTrue("testJsonObject.hasProperty(\"testProperty\") != true", testJsonObject.hasProperty("testProperty"));
    
    testJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertTrue("testJsonObject.hasProperty(\"testProperty\") != true", testJsonObject.hasProperty("testProperty"));
  }
  
  /**
   * Test the determination of the <code>null</code> value of a property.
   * <p>
   * This test asserts that the JSON object correctly reports that a property
   * exists but has a <code>null</code> value.
   */
  @Test
  public void testIsPropertyNull() {
    TestJsonObject testJsonObject = new TestNativeJsonObject();
    
    assertFalse("testJsonObject.isNullProperty(\"testProperty\") != false", testJsonObject.isNullProperty("testProperty"));
    
    testJsonObject.setStringProperty("testProperty", null);
    
    assertTrue("testJsonObject.isNullProperty(\"testProperty\") != true", testJsonObject.isNullProperty("testProperty"));
    
    testJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertFalse("testJsonObject.isNullProperty(\"testProperty\") != false", testJsonObject.isNullProperty("testProperty"));
  }
  
  /**
   * Test the deletion of a property.
   * <p>
   * This test asserts that a property is correctly deleted from the JSON
   * object.
   */
  @Test
  public void testDeleteProperty() {
    TestJsonObject testJsonObject = new TestNativeJsonObject();
    
    testJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertTrue("testJsonObject.deleteProperty(\"testProperty\") != true", testJsonObject.deleteProperty("testProperty"));
    assertFalse("testJsonObject.hasProperty(\"testProperty\") != false", testJsonObject.hasProperty("testProperty"));
    assertNull("testJsonObject.getStringProperty(\"testProperty\") != null", testJsonObject.getStringProperty("testProperty"));
    
    assertFalse("testJsonObject.deleteProperty(\"testProperty\") != false", testJsonObject.deleteProperty("testProperty"));
    assertFalse("testJsonObject.hasProperty(\"testProperty\") != false", testJsonObject.hasProperty("testProperty"));
    assertNull("testJsonObject.getStringProperty(\"testProperty\") != null", testJsonObject.getStringProperty("testProperty"));
  }
}
