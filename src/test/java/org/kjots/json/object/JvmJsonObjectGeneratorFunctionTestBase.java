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
package org.kjots.json.object;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectGeneratorFunctionTestBase;

/**
 * JVM JSON Object Generator Function Test Base.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public abstract class JvmJsonObjectGeneratorFunctionTestBase extends JsonObjectGeneratorFunctionTestBase {
  /**
   * Setup the test.
   */
  @Before
  public void setup() {
    Functions functions = mock(Functions.class);
    
    when(functions.testBooleanReturnValueJsonFunction((JsonObject)any())).thenReturn(BOOLEAN_RETURN_VALUE);
    when(functions.testByteReturnValueJsonFunction((JsonObject)any())).thenReturn(BYTE_RETURN_VALUE);
    when(functions.testShortReturnValueJsonFunction((JsonObject)any())).thenReturn(SHORT_RETURN_VALUE);
    when(functions.testIntegerReturnValueJsonFunction((JsonObject)any())).thenReturn(INTEGER_RETURN_VALUE);
    when(functions.testLongReturnValueJsonFunction((JsonObject)any())).thenReturn(LONG_RETURN_VALUE);
    when(functions.testFloatReturnValueJsonFunction((JsonObject)any())).thenReturn(FLOAT_RETURN_VALUE);
    when(functions.testDoubleReturnValueJsonFunction((JsonObject)any())).thenReturn(DOUBLE_RETURN_VALUE);
    when(functions.testCharacterReturnValueJsonFunction((JsonObject)any())).thenReturn(CHARACTER_RETURN_VALUE);
    when(functions.testObjectReturnValueJsonFunction((JsonObject)any())).thenReturn(OBJECT_RETURN_VALUE);
    when(functions.testTypeParameterGenericReturnValueJsonFunction((JsonObject)any())).thenReturn(TYPE_PARAMETER_GENERIC_RETURN_VALUE);
    
    this.setupFunctions(functions);
  }
  
  /**
   * Cleanup the test.
   */
  @After
  public void cleanup() {
    this.cleanupFunctions();
  }
  
  /**
   * Verify the invocation of the no-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testJsonFunction(testJsonObject);
  }
  
  /**
   * Verify the invocation of the no-argument boolean-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeBooleanReturnValueJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testBooleanReturnValueJsonFunction(testJsonObject);
  }
  
  /**
   * Verify the invocation of the no-argument byte-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeByteReturnValueJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testByteReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Verify the invocation of the no-argument short-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeShortReturnValueJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testShortReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Verify the invocation of the no-argument integer-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeIntegerReturnValueJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testIntegerReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Verify the invocation of the no-argument long-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeLongReturnValueJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testLongReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Verify the invocation of the no-argument float-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeFloatReturnValueJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testFloatReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Verify the invocation of the no-argument double-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeDoubleReturnValueJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testDoubleReturnValueJsonFunction(testJsonObject);
  }

  /**
   * Verify the invocation of the no-argument character-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeCharacterReturnValueJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testCharacterReturnValueJsonFunction(testJsonObject);
  }
  
  /**
   * Verify the invocation of the no-argument object-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeObjectReturnValueJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testObjectReturnValueJsonFunction(testJsonObject);
  }
  
  /**
   * Verify the invocation of the no-argument type-parameter-generic-return-value
   * test JSON function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeTypeParameterGenericReturnValueJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testTypeParameterGenericReturnValueJsonFunction(testJsonObject);
  }
  
  /**
   * Verify the invocation of the boolean-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The boolean parameter.
   */
  @Override
  protected void verifyInvokeBooleanParameterJsonFunction(TestJsonObject testJsonObject, boolean param) {
    verify(functions()).testBooleanParameterJsonFunction(testJsonObject, param);
  }

  /**
   * Verify the invocation of the byte-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The byte parameter.
   */
  @Override
  protected void verifyInvokeByteParameterJsonFunction(TestJsonObject testJsonObject, byte param) {
    verify(functions()).testByteParameterJsonFunction(testJsonObject, param);
  }

  /**
   * Verify the invocation of the short-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The short parameter.
   */
  @Override
  protected void verifyInvokeShortParameterJsonFunction(TestJsonObject testJsonObject, short param) {
    verify(functions()).testShortParameterJsonFunction(testJsonObject, param);
  }

  /**
   * Verify the invocation of the integer-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The integer parameter.
   */
  @Override
  protected void verifyInvokeIntegerParameterJsonFunction(TestJsonObject testJsonObject, int param) {
    verify(functions()).testIntegerParameterJsonFunction(testJsonObject, param);
  }

  /**
   * Verify the invocation of the long-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The long parameter.
   */
  @Override
  protected void verifyInvokeLongParameterJsonFunction(TestJsonObject testJsonObject, long param) {
    verify(functions()).testLongParameterJsonFunction(testJsonObject, param);
  }

  /**
   * Verify the invocation of the float-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The float parameter.
   */
  @Override
  protected void verifyInvokeFloatParameterJsonFunction(TestJsonObject testJsonObject, float param) {
    verify(functions()).testFloatParameterJsonFunction(testJsonObject, param);
  }

  /**
   * Verify the invocation of the double-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The double parameter.
   */
  @Override
  protected void verifyInvokeDoubleParameterJsonFunction(TestJsonObject testJsonObject, double param) {
    verify(functions()).testDoubleParameterJsonFunction(testJsonObject, param);
  }

  /**
   * Verify the invocation of the character-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The character parameter.
   */
  @Override
  protected void verifyInvokeCharacterParameterJsonFunction(TestJsonObject testJsonObject, char param) {
    verify(functions()).testCharacterParameterJsonFunction(testJsonObject, param);
  }

  /**
   * Verify the invocation of the object-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The object parameter.
   */
  @Override
  protected void verifyInvokeObjectParameterJsonFunction(TestJsonObject testJsonObject, Object param) {
    verify(functions()).testObjectParameterJsonFunction(testJsonObject, param);
  }
  
  /**
   * Verify the invocation of the type-parameter-generic-argument
   * no-return-value test JSON function.
   *
   * @param testJsonObject The test JSON object.
   * @param param The type parameter generic test parameter.
   */
  @Override
  protected void verifyInvokeTypeParameterGenericParameterJsonFunction(TestJsonObject testJsonObject, Object param) {
    verify(functions()).testTypeParameterGenericParameterJsonFunction(testJsonObject, param);
  }
  
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
  @Override
  protected void verifyInvokeMultiParameterJsonFunction(TestJsonObject testJsonObject, boolean booleanParam, byte byteParam, short shortParam, int integerParam, long longParam, float floatParam, double doubleParam, char characterParam, Object objectParam) {
    verify(functions()).testMultiParameterJsonFunction(testJsonObject, booleanParam, byteParam, shortParam, integerParam, longParam, floatParam, doubleParam, characterParam, objectParam);
  }
  
  /**
   * Verify the invocation of the variable-arguments no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   * @param varargsParam The variable arguments parameter.
   */
  @Override
  protected void verifyInvokeVarargsParameterJsonFunction(TestJsonObject testJsonObject, Object... varargsParam) {
    verify(functions()).testVarargsParameterJsonFunction(testJsonObject, varargsParam);
  }
}
