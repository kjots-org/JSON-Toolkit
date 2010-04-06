/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Serializable;

import org.junit.After;
import org.junit.Before;
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
   * Test Object Generic Interface.
   */
  public interface TestObjectGenericInterface<T> {
    /**
     * The test generic JSON function.
     *
     * @param genericParam The generic parameter.
     */
    public void testGenericJsonFunction(T genericParam);
  }
  
  /**
   * Test Serializable Generic Interface.
   */
  public interface TestSerializableGenericInterface<S extends Serializable> {
    /**
     * The test generic JSON function.
     *
     * @param genericParam The generic parameter.
     */
    public void testGenericJsonFunction(S genericParam);
  }
  
  /**
   * Test Comparable Generic Interface.
   */
  public interface TestComparableGenericInterface<C extends Comparable<?>> {
    /**
     * The test generic JSON function.
     *
     * @param genericParam The generic parameter.
     */
    public void testGenericJsonFunction(C genericParam);
  }
  
  /**
   * Test Serializable/Comparable Generic Interface.
   */
  public interface TestSerializableComparableGenericInterface<S extends Serializable, C extends Comparable<?>> extends TestSerializableGenericInterface<S>, TestComparableGenericInterface<C> {
  }
  
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject, TestObjectGenericInterface<String>, TestSerializableComparableGenericInterface<String, String> {
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
   * This test asserts that the test generic JSON function is invoked with an
   * {@link Object} argument correctly.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testInvokeGenericJsonFunctionWithObjectArgument() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    ((TestObjectGenericInterface)testJsonObject).testGenericJsonFunction("Test Generic Parameter");
    
    verify(functions).testGenericJsonFunction(testJsonObject, "Test Generic Parameter");
  }
  
  /**
   * Test the invocation of a generic JSON function.
   * <p>
   * This test asserts that the test generic JSON function is invoked with a
   * {@link Serializable} argument correctly.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testInvokeGenericJsonFunctionWithSerializableArgument() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    ((TestSerializableGenericInterface)testJsonObject).testGenericJsonFunction("Test Generic Parameter");
    
    verify(functions).testGenericJsonFunction(testJsonObject, "Test Generic Parameter");
  }
  
  /**
   * Test the invocation of a generic JSON function.
   * <p>
   * This test asserts that the test generic JSON function is invoked with a
   * {@link Comparable} argument correctly.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testInvokeGenericJsonFunctionWithComparableArgument() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    ((TestComparableGenericInterface)testJsonObject).testGenericJsonFunction("Test Generic Parameter");
    
    verify(functions).testGenericJsonFunction(testJsonObject, "Test Generic Parameter");
  }
}
