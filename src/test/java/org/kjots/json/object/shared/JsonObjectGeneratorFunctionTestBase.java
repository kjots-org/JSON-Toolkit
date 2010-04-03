/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.shared;

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
     * Test JSON Function.
     * 
     * @param jsonObject The JSON object.
     */
    public void testJsonFunction(JsonObject jsonObject);
  }
  
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject {
    /**
     * Test JSON Function.
     */
    @JsonFunction(klass = JsonObjectGeneratorFunctionTestBase.class, method = "testJsonFunction")
    public void testJsonFunction();
  }
  
  /** The test JSON object functions. */
  private static Functions functions;
  
  /**
   * Test JSON Function.
   * 
   * @param jsonObject The JSON object.
   */
  public static void testJsonFunction(JsonObject jsonObject) {
    functions.testJsonFunction(jsonObject);
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
}
