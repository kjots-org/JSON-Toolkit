/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.shared;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

/**
 * JSON Object Generator Function Test Base.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public abstract class JsonObjectGeneratorFunctionTestBase {
  /**
   * Test JSON Object Functions.
   */
  public interface Functions {
    /**
     * The test JSON Function.
     * 
     * @param jsonObject The JSON object.
     */
    public void testJsonFunction(JsonObject jsonObject);
    
    /**
     * The boolean return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The boolean return value.
     */
    public boolean testBooleanReturnValueJsonFunction(JsonObject jsonObject);
    
    /**
     * The byte return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The byte return value.
     */
    public byte testByteReturnValueJsonFunction(JsonObject jsonObject);
    
    /**
     * The short return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The short return value.
     */
    public short testShortReturnValueJsonFunction(JsonObject jsonObject);
    
    /**
     * The integer return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The integer return value.
     */
    public int testIntegerReturnValueJsonFunction(JsonObject jsonObject);
    
    /**
     * The long return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The long return value.
     */
    public long testLongReturnValueJsonFunction(JsonObject jsonObject);
    
    /**
     * The float return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The float return value.
     */
    public float testFloatReturnValueJsonFunction(JsonObject jsonObject);
    
    /**
     * The double return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The double return value.
     */
    public double testDoubleReturnValueJsonFunction(JsonObject jsonObject);
    
    /**
     * The character return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The character return value.
     */
    public char testCharacterReturnValueJsonFunction(JsonObject jsonObject);
    
    /**
     * The object return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The object return value.
     */
    public Object testObjectReturnValueJsonFunction(JsonObject jsonObject);
  }
  
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject {
    /**
     * The test JSON Function.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testJsonFunction")
    public void testJsonFunction();
    
    /**
     * The boolean return value JSON Function.
     * 
     * @return The boolean return value.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testBooleanReturnValueJsonFunction")
    public boolean testBooleanReturnValueJsonFunction();
    
    /**
     * The byte return value JSON function.
     *
     * @return The byte return value.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testByteReturnValueJsonFunction")
    public byte testByteReturnValueJsonFunction();
    
    /**
     * The short return value JSON function.
     *
     * @return The short return value.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testShortReturnValueJsonFunction")
    public short testShortReturnValueJsonFunction();
    
    /**
     * The integer return value JSON function.
     *
     * @return The integer return value.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testIntegerReturnValueJsonFunction")
    public int testIntegerReturnValueJsonFunction();
    
    /**
     * The long return value JSON function.
     *
     * @return The long return value.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testLongReturnValueJsonFunction")
    public long testLongReturnValueJsonFunction();
    
    /**
     * The float return value JSON function.
     *
     * @return The float return value.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testFloatReturnValueJsonFunction")
    public float testFloatReturnValueJsonFunction();
    
    /**
     * The double return value JSON function.
     *
     * @return The double return value.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testDoubleReturnValueJsonFunction")
    public double testDoubleReturnValueJsonFunction();
    
    /**
     * The character return value JSON function.
     *
     * @return The character return value.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testCharacterReturnValueJsonFunction")
    public char testCharacterReturnValueJsonFunction();
    
    /**
     * The object return value JSON function.
     *
     * @return The object return value.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testObjectReturnValueJsonFunction")
    public Object testObjectReturnValueJsonFunction();
  }

  /** The boolean return value. */
  public static final boolean BOOLEAN_RETURN_VALUE = true;

  /** The byte return value. */
  public static final byte BYTE_RETURN_VALUE = 42;

  /** The short return value. */
  public static final short SHORT_RETURN_VALUE = 42;

  /** The integer return value. */
  public static final int INTEGER_RETURN_VALUE = 42;

  /** The long return value. */
  public static final long LONG_RETURN_VALUE = 42;

  /** The float return value. */
  public static final float FLOAT_RETURN_VALUE = 3.14f;

  /** The double return value. */
  public static final double DOUBLE_RETURN_VALUE = 3.14;

  /** The character return value. */
  public static final char CHARACTER_RETURN_VALUE = 'X';

  /** The object return value. */
  public static final Object OBJECT_RETURN_VALUE = "Object Return Value";
  
  /** The test JSON object functions. */
  private static Functions functions;
  
  /**
   * The test JSON Function.
   * 
   * @param jsonObject The JSON object.
   */
  public static void testJsonFunction(JsonObject jsonObject) {
    functions.testJsonFunction(jsonObject);
  }
  
  /**
   * The boolean return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The boolean return value.
   */
  public static boolean testBooleanReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testBooleanReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * The byte return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The byte return value.
   */
  public static byte testByteReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testByteReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * The short return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The short return value.
   */
  public static short testShortReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testShortReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * The integer return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The integer return value.
   */
  public static int testIntegerReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testIntegerReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * The long return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The long return value.
   */
  public static long testLongReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testLongReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * The float return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The float return value.
   */
  public static float testFloatReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testFloatReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * The double return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The double return value.
   */
  public static double testDoubleReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testDoubleReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * The character return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The character return value.
   */
  public static char testCharacterReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testCharacterReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * The object return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The object return value.
   */
  public static Object testObjectReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testObjectReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * Setup the test JSON object functions.
   *
   * @param functions The test JSON object functions.
   */
  public void setupFunctions(Functions functions) {
    JsonObjectGeneratorFunctionTestBase.functions = functions;
  }

  /**
   * Cleanup the test JSON object functions.
   */
  public void cleanupFunctions() {
    JsonObjectGeneratorFunctionTestBase.functions = null;
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with no arguments and no
   * return value is invoked correctly.
   */
  @Test
  public void testInvokeJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testJsonFunction();
    
    this.verifyInvokeJsonFunction(testJsonObject);
  }
  
  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with no arguments and a
   * boolean return value return value is invoked correctly.
   */
  @Test
  public void testInvokeBooleanReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(BOOLEAN_RETURN_VALUE, testJsonObject.testBooleanReturnValueJsonFunction());
    
    this.verifyInvokeBooleanReturnValueJsonFunction(testJsonObject);
  }
  
  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with no arguments and a
   * byte return value return value is invoked correctly.
   */
  @Test
  public void testInvokeByteReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(BYTE_RETURN_VALUE, testJsonObject.testByteReturnValueJsonFunction());
    
    this.verifyInvokeByteReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with no arguments and a
   * short return value return value is invoked correctly.
   */
  @Test
  public void testInvokeShortReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(SHORT_RETURN_VALUE, testJsonObject.testShortReturnValueJsonFunction());
    
    this.verifyInvokeShortReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with no arguments and a
   * integer return value return value is invoked correctly.
   */
  @Test
  public void testInvokeIntegerReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(INTEGER_RETURN_VALUE, testJsonObject.testIntegerReturnValueJsonFunction());
    
    this.verifyInvokeIntegerReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with no arguments and a
   * long return value return value is invoked correctly.
   */
  @Test
  public void testInvokeLongReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(LONG_RETURN_VALUE, testJsonObject.testLongReturnValueJsonFunction());
    
    this.verifyInvokeLongReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with no arguments and a
   * float return value return value is invoked correctly.
   */
  @Test
  public void testInvokeFloatReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(FLOAT_RETURN_VALUE, testJsonObject.testFloatReturnValueJsonFunction(), 0.001f);
    
    this.verifyInvokeFloatReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with no arguments and a
   * double return value return value is invoked correctly.
   */
  @Test
  public void testInvokeDoubleReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(DOUBLE_RETURN_VALUE, testJsonObject.testDoubleReturnValueJsonFunction(), 0.001);
    
    this.verifyInvokeDoubleReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with no arguments and a
   * character return value return value is invoked correctly.
   */
  @Test
  public void testInvokeCharacterReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(CHARACTER_RETURN_VALUE, testJsonObject.testCharacterReturnValueJsonFunction());
    
    this.verifyInvokeCharacterReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with no arguments and a
   * object return value return value is invoked correctly.
   */
  @Test
  public void testInvokeObjectReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(OBJECT_RETURN_VALUE, testJsonObject.testObjectReturnValueJsonFunction());
    
    this.verifyInvokeObjectReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Retrieve the test JSON object functions.
   *
   * @return The test JSON object functions.
   */
  protected Functions functions() {
    return functions;
  }
  
  /**
   * Verify the invocation of the no-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  protected abstract void verifyInvokeJsonFunction(TestJsonObject testJsonObject);
  
  /**
   * Verify the invocation of the no-argument boolean-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  protected abstract void verifyInvokeBooleanReturnValueJsonFunction(TestJsonObject testJsonObject);
  
  /**
   * Verify the invocation of the no-argument byte-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  protected abstract void verifyInvokeByteReturnValueJsonFunction(TestJsonObject testJsonObject);

  /**
   * Verify the invocation of the no-argument short-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  protected abstract void verifyInvokeShortReturnValueJsonFunction(TestJsonObject testJsonObject);

  /**
   * Verify the invocation of the no-argument integer-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  protected abstract void verifyInvokeIntegerReturnValueJsonFunction(TestJsonObject testJsonObject);

  /**
   * Verify the invocation of the no-argument long-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  protected abstract void verifyInvokeLongReturnValueJsonFunction(TestJsonObject testJsonObject);

  /**
   * Verify the invocation of the no-argument float-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  protected abstract void verifyInvokeFloatReturnValueJsonFunction(TestJsonObject testJsonObject);

  /**
   * Verify the invocation of the no-argument double-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  protected abstract void verifyInvokeDoubleReturnValueJsonFunction(TestJsonObject testJsonObject);

  /**
   * Verify the invocation of the no-argument character-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  protected abstract void verifyInvokeCharacterReturnValueJsonFunction(TestJsonObject testJsonObject);
  
  /**
   * Verify the invocation of the no-argument object-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  protected abstract void verifyInvokeObjectReturnValueJsonFunction(TestJsonObject testJsonObject);
}
