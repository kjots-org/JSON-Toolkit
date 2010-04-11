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
package org.kjots.json.object.gwt.client;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectGeneratorFunctionTestBase;

/**
 * GWT JSON Object Generator Function Test.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class GwtJsonObjectGeneratorFunctionTest extends GwtJsonObjectTestBase {
  /**
   * GWT JSON Object Generator Test Function Test Functions.
   */
  private static class Functions implements JsonObjectGeneratorFunctionTestBase.Functions {
    /** The name of the method. */
    private String methodName;
    
    /** The JSON object. */
    private JsonObject jsonObject;
    
    /** The arguments. */
    private Object[] arguments;
    
    /**
     * Test JSON Function.
     * 
     * @param jsonObject The JSON object.
     */
    @Override
    public void testJsonFunction(JsonObject jsonObject) {
      this.methodName = "testJsonFunction";
      this.jsonObject = jsonObject;
    }
    
    /**
     * The boolean return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The boolean return value.
     */
    @Override
    public boolean testBooleanReturnValueJsonFunction(JsonObject jsonObject) {
      this.methodName = "testBooleanReturnValueJsonFunction";
      this.jsonObject = jsonObject;
      
      return JsonObjectGeneratorFunctionTestBase.BOOLEAN_RETURN_VALUE;
    }
    
    /**
     * The byte return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The byte return value.
     */
    @Override
    public byte testByteReturnValueJsonFunction(JsonObject jsonObject) {
      this.methodName = "testByteReturnValueJsonFunction";
      this.jsonObject = jsonObject;
      
      return JsonObjectGeneratorFunctionTestBase.BYTE_RETURN_VALUE;
    }
    
    /**
     * The short return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The short return value.
     */
    @Override
    public short testShortReturnValueJsonFunction(JsonObject jsonObject) {
      this.methodName = "testShortReturnValueJsonFunction";
      this.jsonObject = jsonObject;
      
      return JsonObjectGeneratorFunctionTestBase.SHORT_RETURN_VALUE;
    }
    
    /**
     * The integer return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The integer return value.
     */
    @Override
    public int testIntegerReturnValueJsonFunction(JsonObject jsonObject) {
      this.methodName = "testIntegerReturnValueJsonFunction";
      this.jsonObject = jsonObject;
      
      return JsonObjectGeneratorFunctionTestBase.INTEGER_RETURN_VALUE;
    }
    
    /**
     * The long return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The long return value.
     */
    @Override
    public long testLongReturnValueJsonFunction(JsonObject jsonObject) {
      this.methodName = "testLongReturnValueJsonFunction";
      this.jsonObject = jsonObject;
      
      return JsonObjectGeneratorFunctionTestBase.LONG_RETURN_VALUE;
    }
    
    /**
     * The float return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The float return value.
     */
    @Override
    public float testFloatReturnValueJsonFunction(JsonObject jsonObject) {
      this.methodName = "testFloatReturnValueJsonFunction";
      this.jsonObject = jsonObject;
      
      return JsonObjectGeneratorFunctionTestBase.FLOAT_RETURN_VALUE;
    }
    
    /**
     * The double return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The double return value.
     */
    @Override
    public double testDoubleReturnValueJsonFunction(JsonObject jsonObject) {
      this.methodName = "testDoubleReturnValueJsonFunction";
      this.jsonObject = jsonObject;
      
      return JsonObjectGeneratorFunctionTestBase.DOUBLE_RETURN_VALUE;
    }
    
    /**
     * The character return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The character return value.
     */
    @Override
    public char testCharacterReturnValueJsonFunction(JsonObject jsonObject) {
      this.methodName = "testCharacterReturnValueJsonFunction";
      this.jsonObject = jsonObject;
      
      return JsonObjectGeneratorFunctionTestBase.CHARACTER_RETURN_VALUE;
    }
    
    /**
     * The object return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The object return value.
     */
    @Override
    public Object testObjectReturnValueJsonFunction(JsonObject jsonObject) {
      this.methodName = "testObjectReturnValueJsonFunction";
      this.jsonObject = jsonObject;
      
      return JsonObjectGeneratorFunctionTestBase.OBJECT_RETURN_VALUE;
    }
    
    /**
     * The type parameter generic return value JSON function.
     *
     * @param <T> The type parameter.
     * @return The type parameter generic return value.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T testTypeParameterGenericReturnValueJsonFunction(JsonObject jsonObject) {
      this.methodName = "testTypeParameterGenericReturnValueJsonFunction";
      this.jsonObject = jsonObject;
      
      return (T)JsonObjectGeneratorFunctionTestBase.TYPE_PARAMETER_GENERIC_RETURN_VALUE;
    }
    
    /**
     * The type parameter generic array return value JSON function.
     *
     * @param <T> The type parameter.
     * @return The type parameter generic array return value.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] testTypeParameterGenericArrayReturnValueJsonFunction(JsonObject jsonObject) {
      this.methodName = "testTypeParameterGenericArrayReturnValueJsonFunction";
      this.jsonObject = jsonObject;
      
      return (T[])JsonObjectGeneratorFunctionTestBase.TYPE_PARAMETER_GENERIC_ARRAY_RETURN_VALUE;
    }
    
    /**
     * The boolean parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The boolean parameter.
     */
    @Override
    public void testBooleanParameterJsonFunction(JsonObject jsonObject, boolean param) {
      this.methodName = "testBooleanParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] { Boolean.valueOf(param) };
    }
    
    /**
     * The byte parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The byte parameter.
     */
    @Override
    public void testByteParameterJsonFunction(JsonObject jsonObject, byte param) {
      this.methodName = "testByteParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] { Byte.valueOf(param) };
    }
    
    /**
     * The short parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The short parameter.
     */
    @Override
    public void testShortParameterJsonFunction(JsonObject jsonObject, short param) {
      this.methodName = "testShortParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] { Short.valueOf(param) };
    }
    
    /**
     * The integer parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The integer parameter.
     */
    @Override
    public void testIntegerParameterJsonFunction(JsonObject jsonObject, int param) {
      this.methodName = "testIntegerParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] { Integer.valueOf(param) };
    }
    
    /**
     * The long parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The long parameter.
     */
    @Override
    public void testLongParameterJsonFunction(JsonObject jsonObject, long param) {
      this.methodName = "testLongParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] { Long.valueOf(param) };
    }
    
    /**
     * The float parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The float parameter.
     */
    @Override
    public void testFloatParameterJsonFunction(JsonObject jsonObject, float param) {
      this.methodName = "testFloatParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] { Float.valueOf(param) };
    }
    
    /**
     * The double parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The double parameter.
     */
    @Override
    public void testDoubleParameterJsonFunction(JsonObject jsonObject, double param) {
      this.methodName = "testDoubleParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] { Double.valueOf(param) };
    }
    
    /**
     * The character parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The character parameter.
     */
    @Override
    public void testCharacterParameterJsonFunction(JsonObject jsonObject, char param) {
      this.methodName = "testCharacterParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] { Character.valueOf(param) };
    }
    
    /**
     * The object parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param param The object parameter.
     */
    @Override
    public void testObjectParameterJsonFunction(JsonObject jsonObject, Object param) {
      this.methodName = "testObjectParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] { param };
    }
    
    /**
     * The type parameter generic parameter JSON function.
     *
     * @param <T> The type parameter.
     * @param param The type parameter generic parameter.
     */
    @Override
    public <T> void testTypeParameterGenericParameterJsonFunction(JsonObject jsonObject, T param) {
      this.methodName = "testTypeParameterGenericParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] { param };
    }

    /**
     * The type parameter generic array parameter JSON function.
     *
     * @param <T> The type parameter.
     * @param param The type parameter generic array parameter.
     */
    @Override
    public <T> void testTypeParameterGenericArrayParameterJsonFunction(JsonObject jsonObject, T[] param) {
      this.methodName = "testTypeParameterGenericArrayParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] { param };
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
    @Override
    public void testMultiParameterJsonFunction(JsonObject jsonObject, boolean booleanParam, byte byteParam, short shortParam, int integerParam, long longParam, float floatParam, double doubleParam, char characterParam, Object objectParam) {
      this.methodName = "testMultiParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = new Object[] {
        Boolean.valueOf(booleanParam),
        Byte.valueOf(byteParam),
        Short.valueOf(shortParam),
        Integer.valueOf(integerParam),
        Long.valueOf(longParam),
        Float.valueOf(floatParam),
        Double.valueOf(doubleParam),
        Character.valueOf(characterParam),
        objectParam
      };
    }
    
    /**
     * The variable arguments JSON function.
     *
     * @param jsonObject The JSON object.
     * @param varargsParam The variable arguments parameter.
     */
    @Override
    public void testVarargsParameterJsonFunction(JsonObject jsonObject, Object... varargsParam) {
      this.methodName = "testVarargsParameterJsonFunction";
      this.jsonObject = jsonObject;
      this.arguments = varargsParam;
    }
  }
  
  /** The JSON object generator function test delegate. */
  private final JsonObjectGeneratorFunctionTestBase jsonObjectGeneratorFunctionTestDelegate = new JsonObjectGeneratorFunctionTestBase() {
    @Override
    protected GwtJsonObjectGeneratorFunctionTest.Functions functions() {
      return (GwtJsonObjectGeneratorFunctionTest.Functions)super.functions();
    }

    @Override
    protected void verifyInvokeJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeBooleanReturnValueJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testBooleanReturnValueJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeByteReturnValueJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testByteReturnValueJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeShortReturnValueJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testShortReturnValueJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeIntegerReturnValueJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testIntegerReturnValueJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeLongReturnValueJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testLongReturnValueJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeFloatReturnValueJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testFloatReturnValueJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeDoubleReturnValueJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testDoubleReturnValueJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeCharacterReturnValueJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testCharacterReturnValueJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeObjectReturnValueJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testObjectReturnValueJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeTypeParameterGenericReturnValueJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testTypeParameterGenericReturnValueJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeTypeParameterGenericArrayReturnValueJsonFunction(TestJsonObject testJsonObject) {
      assertEquals("testTypeParameterGenericArrayReturnValueJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
    }

    @Override
    protected void verifyInvokeBooleanParameterJsonFunction(TestJsonObject testJsonObject, boolean param) {
      assertEquals("testBooleanParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(Boolean.valueOf(param), functions().arguments[0]);
    }

    @Override
    protected void verifyInvokeByteParameterJsonFunction(TestJsonObject testJsonObject, byte param) {
      assertEquals("testByteParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(Byte.valueOf(param), functions().arguments[0]);
    }

    @Override
    protected void verifyInvokeShortParameterJsonFunction(TestJsonObject testJsonObject, short param) {
      assertEquals("testShortParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(Short.valueOf(param), functions().arguments[0]);
    }

    @Override
    protected void verifyInvokeIntegerParameterJsonFunction(TestJsonObject testJsonObject, int param) {
      assertEquals("testIntegerParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(Integer.valueOf(param), functions().arguments[0]);
    }

    @Override
    protected void verifyInvokeLongParameterJsonFunction(TestJsonObject testJsonObject, long param) {
      assertEquals("testLongParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(Long.valueOf(param), functions().arguments[0]);
    }

    @Override
    protected void verifyInvokeFloatParameterJsonFunction(TestJsonObject testJsonObject, float param) {
      assertEquals("testFloatParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(Float.valueOf(param), functions().arguments[0]);
    }

    @Override
    protected void verifyInvokeDoubleParameterJsonFunction(TestJsonObject testJsonObject, double param) {
      assertEquals("testDoubleParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(Double.valueOf(param), functions().arguments[0]);
    }

    @Override
    protected void verifyInvokeCharacterParameterJsonFunction(TestJsonObject testJsonObject, char param) {
      assertEquals("testCharacterParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(Character.valueOf(param), functions().arguments[0]);
    }

    @Override
    protected void verifyInvokeObjectParameterJsonFunction(TestJsonObject testJsonObject, Object param) {
      assertEquals("testObjectParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(param, functions().arguments[0]);
    }
    
    @Override
    protected void verifyInvokeTypeParameterGenericParameterJsonFunction(TestJsonObject testJsonObject, Object param) {
      assertEquals("testTypeParameterGenericParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(param, functions().arguments[0]);
    }
    
    @Override
    protected void verifyInvokeTypeParameterGenericArrayParameterJsonFunction(TestJsonObject testJsonObject, Object[] param) {
      assertEquals("testTypeParameterGenericArrayParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(param, functions().arguments[0]);
    }
    
    @Override
    protected void verifyInvokeMultiParameterJsonFunction(TestJsonObject testJsonObject, boolean booleanParam, byte byteParam, short shortParam, int integerParam, long longParam, float floatParam, double doubleParam, char characterParam, Object objectParam) {
      assertEquals("testMultiParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(Boolean.valueOf(booleanParam), functions().arguments[0]);
      assertEquals(Byte.valueOf(byteParam), functions().arguments[1]);
      assertEquals(Short.valueOf(shortParam), functions().arguments[2]);
      assertEquals(Integer.valueOf(integerParam), functions().arguments[3]);
      assertEquals(Long.valueOf(longParam), functions().arguments[4]);
      assertEquals(Float.valueOf(floatParam), functions().arguments[5]);
      assertEquals(Double.valueOf(doubleParam), functions().arguments[6]);
      assertEquals(Character.valueOf(characterParam), functions().arguments[7]);
      assertEquals(objectParam, functions().arguments[8]);
    }
    
    @Override
    protected void verifyInvokeVarargsParameterJsonFunction(TestJsonObject testJsonObject, Object... varargsParam) {
      assertEquals("testVarargsParameterJsonFunction", functions().methodName);
      assertEquals(testJsonObject, functions().jsonObject);
      assertEquals(varargsParam.length, functions().arguments.length);
      for (int i = 0; i < varargsParam.length; i++) {
        assertEquals(varargsParam[i], functions().arguments[i]);
      }
    }
  };
  
  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeJsonFunction()
   */
  public void testInvokeJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeBooleanReturnValueJsonFunction()
   */
  public void testInvokeBooleanReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeBooleanReturnValueJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeByteReturnValueJsonFunction()
   */
  public void testInvokeByteReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeByteReturnValueJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeCharacterReturnValueJsonFunction()
   */
  public void testInvokeCharacterReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeCharacterReturnValueJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeDoubleReturnValueJsonFunction()
   */
  public void testInvokeDoubleReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeDoubleReturnValueJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeFloatReturnValueJsonFunction()
   */
  public void testInvokeFloatReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeFloatReturnValueJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeIntegerReturnValueJsonFunction()
   */
  public void testInvokeIntegerReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeIntegerReturnValueJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeLongReturnValueJsonFunction()
   */
  public void testInvokeLongReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeLongReturnValueJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeObjectReturnValueJsonFunction()
   */
  public void testInvokeObjectReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeObjectReturnValueJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeTypeParameterGenericReturnValueJsonFunction()
   */
  public void testInvokeTypeParameterGenericReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeTypeParameterGenericReturnValueJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeTypeParameterGenericArrayReturnValueJsonFunction()
   */
  public void testInvokeTypeParameterGenericArrayReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeTypeParameterGenericArrayReturnValueJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeShortReturnValueJsonFunction()
   */
  public void testInvokeShortReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeShortReturnValueJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeBooleanParameterJsonFunction()
   */
  public void testInvokeBooleanParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeBooleanParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeByteParameterJsonFunction()
   */
  public void testInvokeByteParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeByteParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeShortParameterJsonFunction()
   */
  public void testInvokeShortParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeShortParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeIntegerParameterJsonFunction()
   */
  public void testInvokeIntegerParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeIntegerParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeLongParameterJsonFunction()
   */
  public void testInvokeLongParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeLongParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeFloatParameterJsonFunction()
   */
  public void testInvokeFloatParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeFloatParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeDoubleParameterJsonFunction()
   */
  public void testInvokeDoubleParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeDoubleParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeCharacterParameterJsonFunction()
   */
  public void testInvokeCharacterParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeCharacterParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeObjectParameterJsonFunction()
   */
  public void testInvokeObjectParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeObjectParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeTypeParameterGenericParameterJsonFunction()
   */
  public void testInvokeTypeParameterGenericParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeTypeParameterGenericParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeTypeParameterGenericArrayParameterJsonFunction()
   */
  public void testInvokeTypeParameterGenericArrayParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeTypeParameterGenericArrayParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeMultiParameterJsonFunction()
   */
  public void testInvokeMultiParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeMultiParameterJsonFunction();
  }

  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeVarargsParameterJsonFunction()
   */
  public void testInvokeVarargsParameterJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeVarargsParameterJsonFunction();
  }

  /**
   * Set up the GWT test case.
   */
  @Override
  protected void gwtSetUp() {
    super.gwtSetUp();
    
    this.jsonObjectGeneratorFunctionTestDelegate.setupFunctions(new Functions());
  }

  /**
   * Tear down the GWT test case.
   */
  @Override
  protected void gwtTearDown() {
    this.jsonObjectGeneratorFunctionTestDelegate.cleanupFunctions();
  }
}
