/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kjots.json.object.shared.JsonFunction;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;

/**
 * JVM JSON Object Generator Bridge Test Base.
 * <p>
 * Created: 6th April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public abstract class JvmJsonObjectGeneratorBridgeTestBase {
  /**
   * Test JSON Object Functions.
   */
  public interface Functions {
    /**
     * The test generic JSON function.
     *
     * @param jsonObject The JSON object.
     * @param genericParam The generic parameter.
     */
    public void testGenericJsonFunction(JsonObject jsonObject, String genericParam);
  }
  
  /**
   * Test Generic Interface.
   */
  public interface TestGenericInterface<T> {
    /**
     * The test generic JSON function.
     *
     * @param genericParam The generic parameter.
     */
    public void testGenericJsonFunction(T genericParam);
  }
  
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject, TestGenericInterface<String> {
    /**
     * The test generic JSON function.
     *
     * @param genericParam The generic parameter.
     */
    @Override
    @JsonFunction(klass = JvmJsonObjectGeneratorBridgeTestBase.class, method = "testGenericJsonFunction")
    public void testGenericJsonFunction(String genericParam);
  }
  
  /** The test JSON object functions. */
  private static Functions functions;
  
  /**
   * The test generic JSON function.
   *
   * @param jsonObject The JSON object.
   * @param genericParam The generic parameter.
   */
  public static void testGenericJsonFunction(JsonObject jsonObject, String genericParam) {
    functions.testGenericJsonFunction(jsonObject, genericParam);
  }
  
  /**
   * Setup the test.
   */
  @Before
  public void setup() {
    functions = mock(Functions.class);
  }
  
  /**
   * Cleanup the test.
   */
  @After
  public void cleanup() {
    functions = null;
  }
  
  /**
   * Test the invocation of a generic JSON function.
   * <p>
   * This test asserts that the test generic JSON function is invoked
   * correctly.
   */
  @Test
  @Ignore // FIXME: Enable this test.
  @SuppressWarnings("unchecked")
  public void testInvokeGenericJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    ((TestGenericInterface)testJsonObject).testGenericJsonFunction("Test Generic Parameter");
    
    verify(functions).testGenericJsonFunction(testJsonObject, "Test Generic Parameter");
  }
}
