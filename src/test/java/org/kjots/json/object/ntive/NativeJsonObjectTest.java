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

import org.junit.Test;

/**
 * Native JSON Property Test.
 * <p>
 * Created: 12th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonObjectTest {
  /**
   * Test Base Native JSON Object.
   * <p>
   * Created: 10th November 2010.
   */
  public static class TestBaseNativeJsonObject extends NativeJsonObject {
  }
  
  /**
   * Test Not Base Native JSON Object.
   * <p>
   * Created: 10th November 2010.
   */
  public static class TestNotBaseNativeJsonObject extends NativeJsonObject {
  }
  
  /**
   * Test Native JSON Object.
   */
  public static class TestNativeJsonObject extends TestBaseNativeJsonObject {
    /** The test property.*/
    @NativeJsonProperty
    private String testProperty;
  }
  
  /**
   * Test the casting of a native JSON object.
   * <p>
   * This test asserts that the casting of a native JSON object to a
   * compatible JSON object class returns the native JSON object.
   */
  @Test
  public void testCastByClassToCompatibleClass() {
    TestNativeJsonObject testNativeJsonObject = new TestNativeJsonObject();
    
    TestBaseNativeJsonObject testBaseNativeJsonObject = testNativeJsonObject.cast(TestBaseNativeJsonObject.class);
    
    assertSame("testNativeJsonObject !== testBaseNativeJsonObject", testNativeJsonObject, testBaseNativeJsonObject);
  }
  
  /**
   * Test the casting of a native JSON object.
   * <p>
   * This test asserts that the casting of a native JSON object to an
   * incompatible JSON object class throws a {@link ClassCastException}.
   */
  @Test(expected = ClassCastException.class)
  public void testCastByClassToIncompatibleClass() {
    TestNativeJsonObject testNativeJsonObject = new TestNativeJsonObject();
    
    testNativeJsonObject.cast(TestNotBaseNativeJsonObject.class);
  }
  
  /**
   * Test the casting of a native JSON object.
   * <p>
   * This test asserts that the casting of a native JSON object to a
   * compatible JSON object class via the classname returns the native JSON
   * object.
   */
  @Test
  public void testCastByClassNameToCompatibleClass() {
    TestNativeJsonObject testNativeJsonObject = new TestNativeJsonObject();
    
    TestBaseNativeJsonObject testBaseNativeJsonObject = testNativeJsonObject.cast(TestBaseNativeJsonObject.class.getName());
    
    assertSame("testNativeJsonObject !== testBaseNativeJsonObject", testNativeJsonObject, testBaseNativeJsonObject);
  }
  
  /**
   * Test the casting of a native JSON object.
   * <p>
   * This test asserts that the casting of a native JSON object to an
   * incompatible JSON object class via the classname throws a {@link ClassCastException}.
   */
  @Test(expected = ClassCastException.class)
  public void testCastByClassNameToIncompatibleClass() {
    TestNativeJsonObject testNativeJsonObject = new TestNativeJsonObject();
    
    testNativeJsonObject.cast(TestNotBaseNativeJsonObject.class.getName());
  }
  
  /**
   * Test the retrieval of the names of the properties.
   * <p>
   * This test asserts that the native JSON object correctly returns the names
   * of the existing properties.
   */
  @Test
  public void testGetPropertyNames() {
    TestNativeJsonObject testNativeJsonObject = new TestNativeJsonObject();
    
    assertEquals(Collections.emptySet(), testNativeJsonObject.getPropertyNames());
    
    testNativeJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertEquals(new HashSet<String>(Arrays.asList("testProperty")), testNativeJsonObject.getPropertyNames());
  }
  
  /**
   * Test the determination of the existence of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports the
   * existence or non-existence of a property.
   */
  @Test
  public void testHasProperty() {
    TestNativeJsonObject testNativeJsonObject = new TestNativeJsonObject();
    
    assertFalse("testNativeJsonObject.hasProperty(\"testProperty\") != false", testNativeJsonObject.hasProperty("testProperty"));
    
    testNativeJsonObject.setStringProperty("testProperty", null);
    
    assertTrue("testNativeJsonObject.hasProperty(\"testProperty\") != true", testNativeJsonObject.hasProperty("testProperty"));
    
    testNativeJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertTrue("testNativeJsonObject.hasProperty(\"testProperty\") != true", testNativeJsonObject.hasProperty("testProperty"));
  }
  
  /**
   * Test the determination of the <code>null</code> value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists but has a <code>null</code> value.
   */
  @Test
  public void testIsPropertyNull() {
    TestNativeJsonObject testNativeJsonObject = new TestNativeJsonObject();
    
    assertFalse("testNativeJsonObject.isNullProperty(\"testProperty\") != false", testNativeJsonObject.isNullProperty("testProperty"));
    
    testNativeJsonObject.setStringProperty("testProperty", null);
    
    assertTrue("testNativeJsonObject.isNullProperty(\"testProperty\") != true", testNativeJsonObject.isNullProperty("testProperty"));
    
    testNativeJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertFalse("testNativeJsonObject.isNullProperty(\"testProperty\") != false", testNativeJsonObject.isNullProperty("testProperty"));
  }
  
  /**
   * Test the deletion of a property.
   * <p>
   * This test asserts that a property is correctly deleted from the native
   * JSON object.
   */
  @Test
  public void testDeleteProperty() {
    TestNativeJsonObject testNativeJsonObject = new TestNativeJsonObject();
    
    testNativeJsonObject.setStringProperty("testProperty", "Test String Property Value");
    
    assertTrue("testNativeJsonObject.deleteProperty(\"testProperty\") != true", testNativeJsonObject.deleteProperty("testProperty"));
    assertFalse("testNativeJsonObject.hasProperty(\"testProperty\") != false", testNativeJsonObject.hasProperty("testProperty"));
    assertNull("testNativeJsonObject.testProperty != null", testNativeJsonObject.testProperty);
    
    assertFalse("testNativeJsonObject.deleteProperty(\"testProperty\") != false", testNativeJsonObject.deleteProperty("testProperty"));
    assertFalse("testNativeJsonObject.hasProperty(\"testProperty\") != false", testNativeJsonObject.hasProperty("testProperty"));
    assertNull("testNativeJsonObject.testProperty != null", testNativeJsonObject.testProperty);
  }
}
