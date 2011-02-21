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
package org.kjots.json.object.shared;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import org.kjots.json.object.shared.JsonProperty.OperationType;

/**
 * JSON Object Generator Primitive Test Base.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public abstract class JsonObjectGeneratorPrimitiveTestBase {
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject {
    /**
     * Retrieve the test boolean primitive property.
     *
     * @return The test boolean primitive property.
     * @see #setTestBooleanPrimitiveProperty(boolean)
     */
    @JsonProperty(name = "testBooleanPrimitiveProperty", operation = OperationType.GET)
    public boolean getTestBooleanPrimitiveProperty();
    
    /**
     * Set the test boolean primitive property.
     *
     * @param testBooleanPrimitiveProperty The test boolean primitive property.
     * @see #getTestBooleanPrimitiveProperty()
     */
    @JsonProperty(name = "testBooleanPrimitiveProperty", operation = OperationType.SET)
    public void setTestBooleanPrimitiveProperty(boolean testBooleanPrimitiveProperty);
    
    /**
     * Retrieve the test byte primitive property.
     *
     * @return The test byte primitive property.
     * @see #setTestBytePrimitiveProperty(byte)
     */
    @JsonProperty(name = "testBytePrimitiveProperty", operation = OperationType.GET)
    public byte getTestBytePrimitiveProperty();
    
    /**
     * Set the test byte primitive property.
     *
     * @param testBytePrimitiveProperty The test byte primitive property.
     * @see #getTestBytePrimitiveProperty()
     */
    @JsonProperty(name = "testBytePrimitiveProperty", operation = OperationType.SET)
    public void setTestBytePrimitiveProperty(byte testBytePrimitiveProperty);
    
    /**
     * Retrieve the test short primitive property.
     *
     * @return The test short primitive property.
     * @see #setTestShortPrimitiveProperty(short)
     */
    @JsonProperty(name = "testShortPrimitiveProperty", operation = OperationType.GET)
    public short getTestShortPrimitiveProperty();
    
    /**
     * Set the test short primitive property.
     *
     * @param testShortPrimitiveProperty The test short primitive property.
     * @see #getTestShortPrimitiveProperty()
     */
    @JsonProperty(name = "testShortPrimitiveProperty", operation = OperationType.SET)
    public void setTestShortPrimitiveProperty(short testShortPrimitiveProperty);
    
    /**
     * Retrieve the test integer primitive property.
     *
     * @return The test integer primitive property.
     * @see #setTestIntegerPrimitiveProperty(int)
     */
    @JsonProperty(name = "testIntegerPrimitiveProperty", operation = OperationType.GET)
    public int getTestIntegerPrimitiveProperty();
    
    /**
     * Set the test integer primitive property.
     *
     * @param testIntegerPrimitiveProperty The test integer primitive property.
     * @see #getTestIntegerPrimitiveProperty()
     */
    @JsonProperty(name = "testIntegerPrimitiveProperty", operation = OperationType.SET)
    public void setTestIntegerPrimitiveProperty(int testIntegerPrimitiveProperty);
    
    /**
     * Retrieve the test long primitive property.
     *
     * @return The test long primitive property.
     * @see #setTestLongPrimitiveProperty(int)
     */
    @JsonProperty(name = "testLongPrimitiveProperty", operation = OperationType.GET)
    public long getTestLongPrimitiveProperty();
    
    /**
     * Set the test long primitive property.
     *
     * @param testLongPrimitiveProperty The test long primitive property.
     * @see #getTestLongPrimitiveProperty()
     */
    @JsonProperty(name = "testLongPrimitiveProperty", operation = OperationType.SET)
    public void setTestLongPrimitiveProperty(long testLongPrimitiveProperty);
    
    /**
     * Retrieve the test float primitive property.
     *
     * @return The test float primitive property.
     * @see #setTestFloatPrimitiveProperty(float)
     */
    @JsonProperty(name = "testFloatPrimitiveProperty", operation = OperationType.GET)
    public float getTestFloatPrimitiveProperty();
    
    /**
     * Set the test float primitive property.
     *
     * @param testFloatPrimitiveProperty The test float primitive property.
     * @see #getTestFloatPrimitiveProperty()
     */
    @JsonProperty(name = "testFloatPrimitiveProperty", operation = OperationType.SET)
    public void setTestFloatPrimitiveProperty(float testFloatPrimitiveProperty);
    
    /**
     * Retrieve the test double primitive property.
     *
     * @return The test double primitive property.
     * @see #setTestDoublePrimitiveProperty(double)
     */
    @JsonProperty(name = "testDoublePrimitiveProperty", operation = OperationType.GET)
    public double getTestDoublePrimitiveProperty();
    
    /**
     * Set the test double primitive property.
     *
     * @param testDoublePrimitiveProperty The test double primitive property.
     * @see #getTestDoublePrimitiveProperty()
     */
    @JsonProperty(name = "testDoublePrimitiveProperty", operation = OperationType.SET)
    public void setTestDoublePrimitiveProperty(double testDoublePrimitiveProperty);
    
    /**
     * Retrieve the test character primitive property.
     *
     * @return The test character primitive property.
     * @see #setTestCharacterPrimitiveProperty(character)
     */
    @JsonProperty(name = "testCharacterPrimitiveProperty", operation = OperationType.GET)
    public char getTestCharacterPrimitiveProperty();
    
    /**
     * Set the test character primitive property.
     *
     * @param testCharacterPrimitiveProperty The test character primitive property.
     * @see #getTestCharacterPrimitiveProperty()
     */
    @JsonProperty(name = "testCharacterPrimitiveProperty", operation = OperationType.SET)
    public void setTestCharacterPrimitiveProperty(char testCharacterPrimitiveProperty);
  }
  
  /**
   * Test the retrieval of the primitive boolean value of a property.
   * <p>
   * This test asserts that the retrieved primitive boolean value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetBooleanPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setBooleanProperty("testBooleanPrimitiveProperty", true);
    
    assertEquals(true, testJsonObject.getTestBooleanPrimitiveProperty());
  }
  
  /**
   * Test the setting of the primitive boolean value of a property.
   * <p>
   * This test asserts that the setting of the primitive boolean value of a
   * property changes the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testSetBooleanPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestBooleanPrimitiveProperty(true);
    
    assertEquals(true, testJsonObject.getBooleanProperty("testBooleanPrimitiveProperty").booleanValue());
  }
  
  /**
   * Test the retrieval of the default primitive boolean value of a property.
   * <p>
   * This test asserts that the retrieved primitive boolean value of a property
   * that does not exist in the underlying JSON object is the default boolean
   * value.
   */
  @Test
  public void testGetDefaultBooleanPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(false, testJsonObject.getTestBooleanPrimitiveProperty());
  }
  
  /**
   * Test the retrieval of the primitive byte value of a property.
   * <p>
   * This test asserts that the retrieved primitive byte value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetBytePrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setNumberProperty("testBytePrimitiveProperty", 42);
    
    assertEquals((byte)42, testJsonObject.getTestBytePrimitiveProperty());
  }
  
  /**
   * Test the setting of the primitive byte value of a property.
   * <p>
   * This test asserts that the setting of the primitive byte value of a
   * property changes the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testSetBytePrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestBytePrimitiveProperty((byte)42);
    
    assertEquals((byte)42, testJsonObject.getNumberProperty("testBytePrimitiveProperty").byteValue());
  }
  
  /**
   * Test the retrieval of the default primitive byte value of a property.
   * <p>
   * This test asserts that the retrieved primitive byte value of a property
   * that does not exist in the underlying JSON object is the default byte
   * value.
   */
  @Test
  public void testGetDefaultBytePrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals((byte)0, testJsonObject.getTestBytePrimitiveProperty());
  }
  
  /**
   * Test the retrieval of the primitive short value of a property.
   * <p>
   * This test asserts that the retrieved primitive short value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetShortPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setNumberProperty("testShortPrimitiveProperty", 42);
    
    assertEquals((short)42, testJsonObject.getTestShortPrimitiveProperty());
  }
  
  /**
   * Test the setting of the primitive short value of a property.
   * <p>
   * This test asserts that the setting of the primitive short value of a
   * property changes the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testSetShortPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestShortPrimitiveProperty((short)42);
    
    assertEquals((short)42, testJsonObject.getNumberProperty("testShortPrimitiveProperty").shortValue());
  }
  
  /**
   * Test the retrieval of the default primitive short value of a property.
   * <p>
   * This test asserts that the retrieved primitive short value of a property
   * that does not exist in the underlying JSON object is the default short
   * value.
   */
  @Test
  public void testGetDefaultShortPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals((short)0, testJsonObject.getTestShortPrimitiveProperty());
  }
  
  /**
   * Test the retrieval of the primitive integer value of a property.
   * <p>
   * This test asserts that the retrieved primitive integer value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetIntegerPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setNumberProperty("testIntegerPrimitiveProperty", 42);
    
    assertEquals(42, testJsonObject.getTestIntegerPrimitiveProperty());
  }
  
  /**
   * Test the setting of the primitive integer value of a property.
   * <p>
   * This test asserts that the setting of the primitive integer value of a
   * property changes the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testSetIntegerPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestIntegerPrimitiveProperty(42);
    
    assertEquals(42, testJsonObject.getNumberProperty("testIntegerPrimitiveProperty").intValue());
  }
  
  /**
   * Test the retrieval of the default primitive integer value of a property.
   * <p>
   * This test asserts that the retrieved primitive integer value of a property
   * that does not exist in the underlying JSON object is the default integer
   * value.
   */
  @Test
  public void testGetDefaultIntegerPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(0, testJsonObject.getTestIntegerPrimitiveProperty());
  }
  
  /**
   * Test the retrieval of the primitive long value of a property.
   * <p>
   * This test asserts that the retrieved primitive long value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetLongPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setNumberProperty("testLongPrimitiveProperty", 42L);
    
    assertEquals(42, testJsonObject.getTestLongPrimitiveProperty());
  }
  
  /**
   * Test the setting of the primitive long value of a property.
   * <p>
   * This test asserts that the setting of the primitive long value of a
   * property changes the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testSetLongPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestLongPrimitiveProperty(42);
    
    assertEquals(42, testJsonObject.getNumberProperty("testLongPrimitiveProperty").longValue());
  }
  
  /**
   * Test the retrieval of the default primitive long value of a property.
   * <p>
   * This test asserts that the retrieved primitive long value of a property
   * that does not exist in the underlying JSON object is the default long
   * value.
   */
  @Test
  public void testGetDefaultLongPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(0, testJsonObject.getTestLongPrimitiveProperty());
  }
  
  /**
   * Test the retrieval of the primitive float value of a property.
   * <p>
   * This test asserts that the retrieved primitive float value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetFloatPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setNumberProperty("testFloatPrimitiveProperty", 3.14f);
    
    assertEquals(3.14f, testJsonObject.getTestFloatPrimitiveProperty(), 0.001f);
  }
  
  /**
   * Test the setting of the primitive float value of a property.
   * <p>
   * This test asserts that the setting of the primitive float value of a
   * property changes the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testSetFloatPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestFloatPrimitiveProperty(3.14f);
    
    assertEquals(3.14f, testJsonObject.getNumberProperty("testFloatPrimitiveProperty").floatValue(), 0.001f);
  }
  
  /**
   * Test the retrieval of the default primitive float value of a property.
   * <p>
   * This test asserts that the retrieved primitive float value of a property
   * that does not exist in the underlying JSON object is the default float
   * value.
   */
  @Test
  public void testGetDefaultFloatPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(0.0f, testJsonObject.getTestFloatPrimitiveProperty(), 0.0f);
  }
  
  /**
   * Test the retrieval of the primitive double value of a property.
   * <p>
   * This test asserts that the retrieved primitive double value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetDoublePrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setNumberProperty("testDoublePrimitiveProperty", 3.14);
    
    assertEquals(3.14, testJsonObject.getTestDoublePrimitiveProperty(), 0.001);
  }
  
  /**
   * Test the setting of the primitive double value of a property.
   * <p>
   * This test asserts that the setting of the primitive double value of a
   * property changes the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testSetDoublePrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestDoublePrimitiveProperty(3.14);
    
    assertEquals(3.14, testJsonObject.getNumberProperty("testDoublePrimitiveProperty").doubleValue(), 0.001);
  }
  
  /**
   * Test the retrieval of the default primitive double value of a property.
   * <p>
   * This test asserts that the retrieved primitive double value of a property
   * that does not exist in the underlying JSON object is the default double
   * value.
   */
  @Test
  public void testGetDefaultDoublePrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(0.0, testJsonObject.getTestDoublePrimitiveProperty(), 0.0);
  }
  
  /**
   * Test the retrieval of the primitive character value of a property.
   * <p>
   * This test asserts that the retrieved primitive character value of a property
   * matches the value of the corresponding property of the underlying JSON
   * object.
   */
  @Test
  public void testGetCharacterPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setStringProperty("testCharacterPrimitiveProperty", "X");
    
    assertEquals('X', testJsonObject.getTestCharacterPrimitiveProperty());
  }
  
  /**
   * Test the setting of the primitive character value of a property.
   * <p>
   * This test asserts that the setting of the primitive character value of a
   * property changes the value of the corresponding property of the underlying
   * JSON object.
   */
  @Test
  public void testSetCharacterPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.setTestCharacterPrimitiveProperty('X');
    
    assertEquals("X", testJsonObject.getStringProperty("testCharacterPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the default primitive character value of a property.
   * <p>
   * This test asserts that the retrieved primitive character value of a property
   * that does not exist in the underlying JSON object is the default character
   * value.
   */
  @Test
  public void testGetDefaultCharacterPrimitiveProperty() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals('\0', testJsonObject.getTestCharacterPrimitiveProperty());
  }
}
