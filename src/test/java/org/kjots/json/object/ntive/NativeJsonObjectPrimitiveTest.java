/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.ntive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Native JSON Object Primitive Test.
 * <p>
 * Created: 12th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonObjectPrimitiveTest {
  /**
   * Test Native JSON Object.
   */
  public static class TestNativeJsonObject extends NativeJsonObject {
    /** The test boolean primitive property.*/
    @NativeJsonProperty
    private boolean testBooleanPrimitiveProperty;
    
    /** The test byte primitive property.*/
    @NativeJsonProperty
    private byte testBytePrimitiveProperty;
    
    /** The test short primitive property.*/
    @NativeJsonProperty
    private short testShortPrimitiveProperty;
    
    /** The test integer primitive property.*/
    @NativeJsonProperty
    private int testIntegerPrimitiveProperty;
    
    /** The test long primitive property.*/
    @NativeJsonProperty
    private long testLongPrimitiveProperty;
    
    /** The test float primitive property.*/
    @NativeJsonProperty
    private float testFloatPrimitiveProperty;
    
    /** The test double primitive property.*/
    @NativeJsonProperty
    private double testDoublePrimitiveProperty;
    
    /** The test character primitive property.*/
    @NativeJsonProperty
    private char testCharacterPrimitiveProperty;
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
   * property exists and has a boolean primitive value.
   */
  @Test
  public void testIsBooleanPrimitiveProperty() {
    assertFalse("testNativeJsonObject.isBooleanProperty(\"testBooleanPrimitiveProperty\") != false", testNativeJsonObject.isBooleanProperty("testBooleanPrimitiveProperty"));
    
    testNativeJsonObject.setHasProperty("testBooleanPrimitiveProperty");
    
    assertTrue("testNativeJsonObject.isBooleanProperty(\"testBooleanPrimitiveProperty\") != true", testNativeJsonObject.isBooleanProperty("testBooleanPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the primitive boolean value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a boolean primitive property.
   */
  @Test
  public void testGetBooleanPrimitiveProperty() {
    testNativeJsonObject.testBooleanPrimitiveProperty = true;
    testNativeJsonObject.setHasProperty("testBooleanPrimitiveProperty");
    
    assertEquals(true, testNativeJsonObject.getBooleanProperty("testBooleanPrimitiveProperty").booleanValue());
  }
  
  /**
   * Test the setting of the primitive boolean value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a boolean primitive property.
   */
  @Test
  public void testSetBooleanPrimitiveProperty() {
    testNativeJsonObject.setBooleanProperty("testBooleanPrimitiveProperty", Boolean.TRUE);
    
    assertEquals(true, testNativeJsonObject.testBooleanPrimitiveProperty);
    assertTrue("testNativeJsonObject.hasProperty(\"testBooleanPrimitiveProperty\") != true", testNativeJsonObject.hasProperty("testBooleanPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the default primitive boolean value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * default value of a boolean primitive property.
   */
  @Test
  public void testGetDefaultBooleanPrimitiveProperty() {
    assertEquals(false, testNativeJsonObject.getBooleanProperty("testBooleanPrimitiveProperty").booleanValue());
  }
  
  /**
   * Test the determination of a byte value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has a byte primitive value.
   */
  @Test
  public void testIsBytePrimitiveProperty() {
    assertFalse("testNativeJsonObject.isByteProperty(\"testBytePrimitiveProperty\") != false", testNativeJsonObject.isNumberProperty("testBytePrimitiveProperty"));
    
    testNativeJsonObject.setHasProperty("testBytePrimitiveProperty");
    
    assertTrue("testNativeJsonObject.isByteProperty(\"testBytePrimitiveProperty\") != true", testNativeJsonObject.isNumberProperty("testBytePrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the primitive byte value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a byte primitive property.
   */
  @Test
  public void testGetBytePrimitiveProperty() {
    testNativeJsonObject.testBytePrimitiveProperty = (byte)42;
    testNativeJsonObject.setHasProperty("testBytePrimitiveProperty");
    
    assertEquals((byte)42, testNativeJsonObject.getNumberProperty("testBytePrimitiveProperty").byteValue());
  }
  
  /**
   * Test the setting of the primitive byte value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a byte primitive property.
   */
  @Test
  public void testSetBytePrimitiveProperty() {
    testNativeJsonObject.setNumberProperty("testBytePrimitiveProperty", Byte.valueOf((byte)42));
    
    assertEquals((byte)42, testNativeJsonObject.testBytePrimitiveProperty);
    assertTrue("testNativeJsonObject.hasProperty(\"testBytePrimitiveProperty\") != true", testNativeJsonObject.hasProperty("testBytePrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the default primitive byte value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * default value of a byte primitive property.
   */
  @Test
  public void testGetDefaultBytePrimitiveProperty() {
    assertEquals((byte)0, testNativeJsonObject.getNumberProperty("testBytePrimitiveProperty").byteValue());
  }
  
  /**
   * Test the determination of a short value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has a short primitive value.
   */
  @Test
  public void testIsShortPrimitiveProperty() {
    assertFalse("testNativeJsonObject.isShortProperty(\"testShortPrimitiveProperty\") != false", testNativeJsonObject.isNumberProperty("testShortPrimitiveProperty"));
    
    testNativeJsonObject.setHasProperty("testShortPrimitiveProperty");
    
    assertTrue("testNativeJsonObject.isShortProperty(\"testShortPrimitiveProperty\") != true", testNativeJsonObject.isNumberProperty("testShortPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the primitive short value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a short primitive property.
   */
  @Test
  public void testGetShortPrimitiveProperty() {
    testNativeJsonObject.testShortPrimitiveProperty = (short)42;
    testNativeJsonObject.setHasProperty("testShortPrimitiveProperty");
    
    assertEquals((short)42, testNativeJsonObject.getNumberProperty("testShortPrimitiveProperty").shortValue());
  }
  
  /**
   * Test the setting of the primitive short value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a short primitive property.
   */
  @Test
  public void testSetShortPrimitiveProperty() {
    testNativeJsonObject.setNumberProperty("testShortPrimitiveProperty", Short.valueOf((short)42));
    
    assertEquals((short)42, testNativeJsonObject.testShortPrimitiveProperty);
    assertTrue("testNativeJsonObject.hasProperty(\"testShortPrimitiveProperty\") != true", testNativeJsonObject.hasProperty("testShortPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the default primitive short value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * default value of a short primitive property.
   */
  @Test
  public void testGetDefaultShortPrimitiveProperty() {
    assertEquals((short)0, testNativeJsonObject.getNumberProperty("testShortPrimitiveProperty").shortValue());
  }
  
  /**
   * Test the determination of a integer value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has a integer primitive value.
   */
  @Test
  public void testIsIntegerPrimitiveProperty() {
    assertFalse("testNativeJsonObject.isIntegerProperty(\"testIntegerPrimitiveProperty\") != false", testNativeJsonObject.isNumberProperty("testIntegerPrimitiveProperty"));
    
    testNativeJsonObject.setHasProperty("testIntegerPrimitiveProperty");
    
    assertTrue("testNativeJsonObject.isIntegerProperty(\"testIntegerPrimitiveProperty\") != true", testNativeJsonObject.isNumberProperty("testIntegerPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the primitive integer value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a integer primitive property.
   */
  @Test
  public void testGetIntegerPrimitiveProperty() {
    testNativeJsonObject.testIntegerPrimitiveProperty = 42;
    testNativeJsonObject.setHasProperty("testIntegerPrimitiveProperty");
    
    assertEquals(42, testNativeJsonObject.getNumberProperty("testIntegerPrimitiveProperty").intValue());
  }
  
  /**
   * Test the setting of the primitive integer value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a integer primitive property.
   */
  @Test
  public void testSetIntegerPrimitiveProperty() {
    testNativeJsonObject.setNumberProperty("testIntegerPrimitiveProperty", Integer.valueOf(42));
    
    assertEquals(42, testNativeJsonObject.testIntegerPrimitiveProperty);
    assertTrue("testNativeJsonObject.hasProperty(\"testIntegerPrimitiveProperty\") != true", testNativeJsonObject.hasProperty("testIntegerPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the default primitive integer value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * default value of a integer primitive property.
   */
  @Test
  public void testGetDefaultIntegerPrimitiveProperty() {
    assertEquals(0, testNativeJsonObject.getNumberProperty("testIntegerPrimitiveProperty").intValue());
  }
  
  /**
   * Test the determination of a long value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has a long primitive value.
   */
  @Test
  public void testIsLongPrimitiveProperty() {
    assertFalse("testNativeJsonObject.isLongProperty(\"testLongPrimitiveProperty\") != false", testNativeJsonObject.isNumberProperty("testLongPrimitiveProperty"));
    
    testNativeJsonObject.setHasProperty("testLongPrimitiveProperty");
    
    assertTrue("testNativeJsonObject.isLongProperty(\"testLongPrimitiveProperty\") != true", testNativeJsonObject.isNumberProperty("testLongPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the primitive long value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a long primitive property.
   */
  @Test
  public void testGetLongPrimitiveProperty() {
    testNativeJsonObject.testLongPrimitiveProperty = 42L;
    testNativeJsonObject.setHasProperty("testLongPrimitiveProperty");
    
    assertEquals(42L, testNativeJsonObject.getNumberProperty("testLongPrimitiveProperty").longValue());
  }
  
  /**
   * Test the setting of the primitive long value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a long primitive property.
   */
  @Test
  public void testSetLongPrimitiveProperty() {
    testNativeJsonObject.setNumberProperty("testLongPrimitiveProperty", Long.valueOf(42L));
    
    assertEquals(42L, testNativeJsonObject.testLongPrimitiveProperty);
    assertTrue("testNativeJsonObject.hasProperty(\"testLongPrimitiveProperty\") != true", testNativeJsonObject.hasProperty("testLongPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the default primitive long value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * default value of a long primitive property.
   */
  @Test
  public void testGetDefaultLongPrimitiveProperty() {
    assertEquals(0L, testNativeJsonObject.getNumberProperty("testLongPrimitiveProperty").longValue());
  }
  
  /**
   * Test the determination of a float value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has a float primitive value.
   */
  @Test
  public void testIsFloatPrimitiveProperty() {
    assertFalse("testNativeJsonObject.isFloatProperty(\"testFloatPrimitiveProperty\") != false", testNativeJsonObject.isNumberProperty("testFloatPrimitiveProperty"));
    
    testNativeJsonObject.setHasProperty("testFloatPrimitiveProperty");
    
    assertTrue("testNativeJsonObject.isFloatProperty(\"testFloatPrimitiveProperty\") != true", testNativeJsonObject.isNumberProperty("testFloatPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the primitive float value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a float primitive property.
   */
  @Test
  public void testGetFloatPrimitiveProperty() {
    testNativeJsonObject.testFloatPrimitiveProperty = 3.14f;
    testNativeJsonObject.setHasProperty("testFloatPrimitiveProperty");
    
    assertEquals(3.14f, testNativeJsonObject.getNumberProperty("testFloatPrimitiveProperty").floatValue(), 0.001);
  }
  
  /**
   * Test the setting of the primitive float value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a float primitive property.
   */
  @Test
  public void testSetFloatPrimitiveProperty() {
    testNativeJsonObject.setNumberProperty("testFloatPrimitiveProperty", Float.valueOf(3.14f));
    
    assertEquals(3.14f, testNativeJsonObject.testFloatPrimitiveProperty, 0.001);
    assertTrue("testNativeJsonObject.hasProperty(\"testFloatPrimitiveProperty\") != true", testNativeJsonObject.hasProperty("testFloatPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the default primitive float value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * default value of a float primitive property.
   */
  @Test
  public void testGetDefaultFloatPrimitiveProperty() {
    assertEquals(0f, testNativeJsonObject.getNumberProperty("testFloatPrimitiveProperty").floatValue(), 0.0);
  }
  
  /**
   * Test the determination of a double value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has a double primitive value.
   */
  @Test
  public void testIsDoublePrimitiveProperty() {
    assertFalse("testNativeJsonObject.isDoubleProperty(\"testDoublePrimitiveProperty\") != false", testNativeJsonObject.isNumberProperty("testDoublePrimitiveProperty"));
    
    testNativeJsonObject.setHasProperty("testDoublePrimitiveProperty");
    
    assertTrue("testNativeJsonObject.isDoubleProperty(\"testDoublePrimitiveProperty\") != true", testNativeJsonObject.isNumberProperty("testDoublePrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the primitive double value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a double primitive property.
   */
  @Test
  public void testGetDoublePrimitiveProperty() {
    testNativeJsonObject.testDoublePrimitiveProperty = 3.14;
    testNativeJsonObject.setHasProperty("testDoublePrimitiveProperty");
    
    assertEquals(3.14, testNativeJsonObject.getNumberProperty("testDoublePrimitiveProperty").doubleValue(), 0.001);
  }
  
  /**
   * Test the setting of the primitive double value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a double primitive property.
   */
  @Test
  public void testSetDoublePrimitiveProperty() {
    testNativeJsonObject.setNumberProperty("testDoublePrimitiveProperty", Double.valueOf(3.14));
    
    assertEquals(3.14, testNativeJsonObject.testDoublePrimitiveProperty, 0.001);
    assertTrue("testNativeJsonObject.hasProperty(\"testDoublePrimitiveProperty\") != true", testNativeJsonObject.hasProperty("testDoublePrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the default primitive double value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * default value of a double primitive property.
   */
  @Test
  public void testGetDefaultDoublePrimitiveProperty() {
    assertEquals(0, testNativeJsonObject.getNumberProperty("testDoublePrimitiveProperty").doubleValue(), 0.0);
  }
  
  /**
   * Test the determination of a character value of a property.
   * <p>
   * This test asserts that the native JSON object correctly reports that a
   * property exists and has a character primitive value.
   */
  @Test
  public void testIsCharacterPrimitiveProperty() {
    assertFalse("testNativeJsonObject.isStringProperty(\"testCharacterPrimitiveProperty\") != false", testNativeJsonObject.isStringProperty("testCharacterPrimitiveProperty"));
    
    testNativeJsonObject.setHasProperty("testCharacterPrimitiveProperty");
    
    assertTrue("testNativeJsonObject.isStringProperty(\"testCharacterPrimitiveProperty\") != true", testNativeJsonObject.isStringProperty("testCharacterPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the primitive character value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * value of a character primitive property.
   */
  @Test
  public void testGetCharacterPrimitiveProperty() {
    testNativeJsonObject.testCharacterPrimitiveProperty = 'X';
    testNativeJsonObject.setHasProperty("testCharacterPrimitiveProperty");
    
    assertEquals('X', testNativeJsonObject.getStringProperty("testCharacterPrimitiveProperty").charAt(0));
  }
  
  /**
   * Test the setting of the primitive character value of a property.
   * <p>
   * This test asserts that the native JSON object correctly sets the value of
   * a character primitive property.
   */
  @Test
  public void testSetCharacterPrimitiveProperty() {
    testNativeJsonObject.setStringProperty("testCharacterPrimitiveProperty", "X");
    
    assertEquals('X', testNativeJsonObject.testCharacterPrimitiveProperty);
    assertTrue("testNativeJsonObject.hasProperty(\"testCharacterPrimitiveProperty\") != true", testNativeJsonObject.hasProperty("testCharacterPrimitiveProperty"));
  }
  
  /**
   * Test the retrieval of the default primitive character value of a property.
   * <p>
   * This test asserts that the native JSON object correctly retrieves the
   * default value of a character primitive property.
   */
  @Test
  public void testGetDefaultCharacterPrimitiveProperty() {
    assertEquals('\0', testNativeJsonObject.getStringProperty("testCharacterPrimitiveProperty").charAt(0));
  }
}
