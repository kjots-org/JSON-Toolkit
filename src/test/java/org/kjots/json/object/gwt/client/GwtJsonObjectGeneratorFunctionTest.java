/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
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
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeShortReturnValueJsonFunction()
   */
  public void testInvokeShortReturnValueJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeShortReturnValueJsonFunction();
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
