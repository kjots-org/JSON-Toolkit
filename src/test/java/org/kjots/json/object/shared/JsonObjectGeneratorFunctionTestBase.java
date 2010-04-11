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
    
    /**
     * The boolean parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The boolean parameter.
     */
    public void testBooleanParameterJsonFunction(JsonObject jsonObject, boolean param);
    
    /**
     * The byte parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The byte parameter.
     */
    public void testByteParameterJsonFunction(JsonObject jsonObject, byte param);
    
    /**
     * The short parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The short parameter.
     */
    public void testShortParameterJsonFunction(JsonObject jsonObject, short param);
    
    /**
     * The integer parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The integer parameter.
     */
    public void testIntegerParameterJsonFunction(JsonObject jsonObject, int param);
    
    /**
     * The long parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The long parameter.
     */
    public void testLongParameterJsonFunction(JsonObject jsonObject, long param);
    
    /**
     * The float parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The float parameter.
     */
    public void testFloatParameterJsonFunction(JsonObject jsonObject, float param);
    
    /**
     * The double parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The double parameter.
     */
    public void testDoubleParameterJsonFunction(JsonObject jsonObject, double param);
    
    /**
     * The character parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The character parameter.
     */
    public void testCharacterParameterJsonFunction(JsonObject jsonObject, char param);
    
    /**
     * The object parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The object parameter.
     */
    public void testObjectParameterJsonFunction(JsonObject jsonObject, Object param);
    
    /**
     * The multiple parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param booleanParam The boolean parameter.
     * @param byteParam The byte parameter.
     * @param shortParam The short parameter.
     * @param integerParam The integer parameter.
     * @param longParam The long parameter.
     * @param floatParam The float parameter.
     * @param doubleParam The double parameter.
     * @param characterParam The character parameter.
     * @param objectParam The object parameter.
     */
    public void testMultiParameterJsonFunction(JsonObject jsonObject, boolean booleanParam, byte byteParam, short shortParam, int integerParam, long longParam, float floatParam, double doubleParam, char characterParam, Object objectParam);
    
    /**
     * The variable arguments JSON function.
     *
     * @param jsonObject The JSON object.
     * @param varargsParam The variable arguments parameter.
     */
    public void testVarargsParameterJsonFunction(JsonObject jsonObject, Object... varargsParam);
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

    /**
     * The boolean parameter JSON function.
     *
     * @param param The boolean parameter.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testBooleanParameterJsonFunction")
    public void testBooleanParameterJsonFunction(boolean param);

    /**
     * The byte parameter JSON function.
     *
     * @param param The byte parameter.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testByteParameterJsonFunction")
    public void testByteParameterJsonFunction(byte param);

    /**
     * The short parameter JSON function.
     *
     * @param param The short parameter.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testShortParameterJsonFunction")
    public void testShortParameterJsonFunction(short param);

    /**
     * The integer parameter JSON function.
     *
     * @param param The integer parameter.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testIntegerParameterJsonFunction")
    public void testIntegerParameterJsonFunction(int param);

    /**
     * The long parameter JSON function.
     *
     * @param param The long parameter.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testLongParameterJsonFunction")
    public void testLongParameterJsonFunction(long param);

    /**
     * The float parameter JSON function.
     *
     * @param param The float parameter.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testFloatParameterJsonFunction")
    public void testFloatParameterJsonFunction(float param);

    /**
     * The double parameter JSON function.
     *
     * @param param The double parameter.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testDoubleParameterJsonFunction")
    public void testDoubleParameterJsonFunction(double param);

    /**
     * The character parameter JSON function.
     *
     * @param param The character parameter.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testCharacterParameterJsonFunction")
    public void testCharacterParameterJsonFunction(char param);

    /**
     * The object parameter JSON function.
     *
     * @param param The object parameter.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testObjectParameterJsonFunction")
    public void testObjectParameterJsonFunction(Object param);
    
    
    /**
     * The multiple parameter JSON function.
     *
     * @param booleanParam The boolean parameter.
     * @param byteParam The byte parameter.
     * @param shortParam The short parameter.
     * @param integerParam The integer parameter.
     * @param longParam The long parameter.
     * @param floatParam The float parameter.
     * @param doubleParam The double parameter.
     * @param characterParam The character parameter.
     * @param objectParam The object parameter.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testMultiParameterJsonFunction")
    public void testMultiParameterJsonFunction(boolean booleanParam, byte byteParam, short shortParam, int integerParam, long longParam, float floatParam, double doubleParam, char characterParam, Object objectParam);
    
    /**
     * The variable arguments JSON function.
     *
     * @param varargsParam The variable arguments parameter.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testVarargsParameterJsonFunction")
    public void testVarargsParameterJsonFunction(Object... varargsParam);
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
  public static final Object OBJECT_RETURN_VALUE = "Test Object Return Value";
  
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
   * The boolean parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param param The boolean parameter.
   */
  public static void testBooleanParameterJsonFunction(JsonObject jsonObject, boolean param) {
    functions.testBooleanParameterJsonFunction(jsonObject, param);
  }

  /**
   * The byte parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param param The byte parameter.
   */
  public static void testByteParameterJsonFunction(JsonObject jsonObject, byte param) {
    functions.testByteParameterJsonFunction(jsonObject, param);
  }

  /**
   * The short parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param param The short parameter.
   */
  public static void testShortParameterJsonFunction(JsonObject jsonObject, short param) {
    functions.testShortParameterJsonFunction(jsonObject, param);
  }

  /**
   * The integer parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param param The integer parameter.
   */
  public static void testIntegerParameterJsonFunction(JsonObject jsonObject, int param) {
    functions.testIntegerParameterJsonFunction(jsonObject, param);
  }

  /**
   * The long parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param param The long parameter.
   */
  public static void testLongParameterJsonFunction(JsonObject jsonObject, long param) {
    functions.testLongParameterJsonFunction(jsonObject, param);
  }

  /**
   * The float parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param param The float parameter.
   */
  public static void testFloatParameterJsonFunction(JsonObject jsonObject, float param) {
    functions.testFloatParameterJsonFunction(jsonObject, param);
  }

  /**
   * The double parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param param The double parameter.
   */
  public static void testDoubleParameterJsonFunction(JsonObject jsonObject, double param) {
    functions.testDoubleParameterJsonFunction(jsonObject, param);
  }

  /**
   * The character parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param param The character parameter.
   */
  public static void testCharacterParameterJsonFunction(JsonObject jsonObject, char param) {
    functions.testCharacterParameterJsonFunction(jsonObject, param);
  }

  /**
   * The object parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param param The object parameter.
   */
  public static void testObjectParameterJsonFunction(JsonObject jsonObject, Object param) {
    functions.testObjectParameterJsonFunction(jsonObject, param);
  }
  
  /**
   * The variable arguments JSON function.
   *
   * @param jsonObject The JSON object.
   * @param varargsParam The variable arguments parameter.
   */
  public static void testVarargsParameterJsonFunction(JsonObject jsonObject, Object... varargsParam) {
    functions.testVarargsParameterJsonFunction(jsonObject, varargsParam);
  }
  
  /**
   * The multiple parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param booleanParam The boolean parameter.
   * @param byteParam The byte parameter.
   * @param shortParam The short parameter.
   * @param integerParam The integer parameter.
   * @param longParam The long parameter.
   * @param floatParam The float parameter.
   * @param doubleParam The double parameter.
   * @param characterParam The character parameter.
   * @param objectParam The object parameter.
   */
  public static void testMultiParameterJsonFunction(JsonObject jsonObject, boolean booleanParam, byte byteParam, short shortParam, int integerParam, long longParam, float floatParam, double doubleParam, char characterParam, Object objectParam) {
    functions.testMultiParameterJsonFunction(jsonObject, booleanParam, byteParam, shortParam, integerParam, longParam, floatParam, doubleParam, characterParam, objectParam);
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
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with a boolean argument and
   * no return value is invoked correctly.
   */
  @Test
  public void testInvokeBooleanParameterJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testBooleanParameterJsonFunction(true);
    
    this.verifyInvokeBooleanParameterJsonFunction(testJsonObject, true);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with a byte argument and
   * no return value is invoked correctly.
   */
  @Test
  public void testInvokeByteParameterJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testByteParameterJsonFunction((byte)42);
    
    this.verifyInvokeByteParameterJsonFunction(testJsonObject, (byte)42);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with a short argument and
   * no return value is invoked correctly.
   */
  @Test
  public void testInvokeShortParameterJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testShortParameterJsonFunction((short)42);
    
    this.verifyInvokeShortParameterJsonFunction(testJsonObject, (short)42);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with a integer argument and
   * no return value is invoked correctly.
   */
  @Test
  public void testInvokeIntegerParameterJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testIntegerParameterJsonFunction(42);
    
    this.verifyInvokeIntegerParameterJsonFunction(testJsonObject, 42);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with a long argument and
   * no return value is invoked correctly.
   */
  @Test
  public void testInvokeLongParameterJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testLongParameterJsonFunction(42L);
    
    this.verifyInvokeLongParameterJsonFunction(testJsonObject, 42L);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with a float argument and
   * no return value is invoked correctly.
   */
  @Test
  public void testInvokeFloatParameterJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testFloatParameterJsonFunction(3.14f);
    
    this.verifyInvokeFloatParameterJsonFunction(testJsonObject, 3.14f);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with a double argument and
   * no return value is invoked correctly.
   */
  @Test
  public void testInvokeDoubleParameterJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testDoubleParameterJsonFunction(3.14);
    
    this.verifyInvokeDoubleParameterJsonFunction(testJsonObject, 3.14);
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with a character argument and
   * no return value is invoked correctly.
   */
  @Test
  public void testInvokeCharacterParameterJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testCharacterParameterJsonFunction('X');
    
    this.verifyInvokeCharacterParameterJsonFunction(testJsonObject, 'X');
  }

  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with a object argument and
   * no return value is invoked correctly.
   */
  @Test
  public void testInvokeObjectParameterJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testObjectParameterJsonFunction("Test Object Parameter");
    
    this.verifyInvokeObjectParameterJsonFunction(testJsonObject, "Test Object Parameter");
  }
  
  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with multiple argument and
   * no return value is invoked correctly.
   */
  @Test
  public void testInvokeMultiParameterJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testMultiParameterJsonFunction(true, (byte)42, (short)42, 42, 42L, 3.14f, 3.14, 'X', "Test Object Parameter");
    
    this.verifyInvokeMultiParameterJsonFunction(testJsonObject, true, (byte)42, (short)42, 42, 42L, 3.14f, 3.14, 'X', "Test Object Parameter");
  }
  
  /**
   * Test the invocation of a JSON function.
   * <p>
   * This test asserts that the test JSON function with variable arguments and
   * no return value is invoked correctly.
   */
  public void testInvokeVarargsParameterJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    testJsonObject.testVarargsParameterJsonFunction("one", "two", "three");
   
    this.verifyInvokeVarargsParameterJsonFunction(testJsonObject, "one", "two", "three");
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

  /**
   * Verify the invocation of the boolean-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The boolean parameter.
   */
  protected abstract void verifyInvokeBooleanParameterJsonFunction(TestJsonObject testJsonObject, boolean param);

  /**
   * Verify the invocation of the byte-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The byte parameter.
   */
  protected abstract void verifyInvokeByteParameterJsonFunction(TestJsonObject testJsonObject, byte param);

  /**
   * Verify the invocation of the short-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The short parameter.
   */
  protected abstract void verifyInvokeShortParameterJsonFunction(TestJsonObject testJsonObject, short param);

  /**
   * Verify the invocation of the integer-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The integer parameter.
   */
  protected abstract void verifyInvokeIntegerParameterJsonFunction(TestJsonObject testJsonObject, int param);

  /**
   * Verify the invocation of the long-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The long parameter.
   */
  protected abstract void verifyInvokeLongParameterJsonFunction(TestJsonObject testJsonObject, long param);

  /**
   * Verify the invocation of the float-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The float parameter.
   */
  protected abstract void verifyInvokeFloatParameterJsonFunction(TestJsonObject testJsonObject, float param);

  /**
   * Verify the invocation of the double-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The double parameter.
   */
  protected abstract void verifyInvokeDoubleParameterJsonFunction(TestJsonObject testJsonObject, double param);

  /**
   * Verify the invocation of the character-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The character parameter.
   */
  protected abstract void verifyInvokeCharacterParameterJsonFunction(TestJsonObject testJsonObject, char param);

  /**
   * Verify the invocation of the object-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The object parameter.
   */
  protected abstract void verifyInvokeObjectParameterJsonFunction(TestJsonObject testJsonObject, Object param);
  
  /**
   * Verify the invocation of the multiple-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param booleanParam The boolean parameter.
   * @param byteParam The byte parameter.
   * @param shortParam The short parameter.
   * @param integerParam The integer parameter.
   * @param longParam The long parameter.
   * @param floatParam The float parameter.
   * @param doubleParam The double parameter.
   * @param characterParam The character parameter.
   * @param objectParam The object parameter.
   */
  protected abstract void verifyInvokeMultiParameterJsonFunction(TestJsonObject testJsonObject, boolean booleanParam, byte byteParam, short shortParam, int integerParam, long longParam, float floatParam, double doubleParam, char characterParam, Object objectParam);
  
  /**
   * Verify the invocation of the variable-arguments no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param varargsParam The variable arguments parameter.
   */
  protected abstract void verifyInvokeVarargsParameterJsonFunction(TestJsonObject testJsonObject, Object... varargsParam);
}
