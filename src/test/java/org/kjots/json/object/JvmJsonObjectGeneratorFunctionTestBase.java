/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
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
}
