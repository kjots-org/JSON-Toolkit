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
    public void testJsonFunction(JsonObject jsonObject) {
      this.methodName = "testJsonFunction";
      this.jsonObject = jsonObject;
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
  };
  
  /**
   * @see JsonObjectGeneratorFunctionTestBase#testInvokeJsonFunction()
   */
  public void testInvokeJsonFunction() {
    this.jsonObjectGeneratorFunctionTestDelegate.testInvokeJsonFunction();
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
