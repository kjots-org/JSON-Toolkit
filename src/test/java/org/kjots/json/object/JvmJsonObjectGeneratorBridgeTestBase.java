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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
     * The test return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The return value.
     */
    public String testReturnValueJsonFunction(JsonObject jsonObject);
    
    /**
     * The test generic return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The generic return value.
     */
    public String testGenericReturnValueJsonFunction(JsonObject jsonObject);
    
    /**
     * The test generic parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param genericParam The generic parameter.
     */
    public void testGenericParameterJsonFunction(JsonObject jsonObject, String genericParam);
    
    /**
     * The test generic array return value JSON function.
     *
     * @param jsonObject The JSON object.
     * @return The generic array return value.
     */
    public String[] testGenericArrayReturnValueJsonFunction(JsonObject jsonObject);
    
    /**
     * The test generic array parameter JSON function.
     *
     * @param jsonObject The JSON object.
     * @param genericArrayParam The generic array parameter.
     */
    public void testGenericArrayParameterJsonFunction(JsonObject jsonObject, String[] genericArrayParam);
  }
  
  /**
   * Test Object Generic Interface.
   */
  public interface TestObjectGenericInterface<T> {
    /**
     * The test return value JSON function.
     *
     * @return The return value.
     */
    public Object testReturnValueJsonFunction();
    
    /**
     * The test generic return value JSON function.
     *
     * @return The generic return value.
     */
    public T testGenericReturnValueJsonFunction();
    
    /**
     * The test generic parameter JSON function.
     *
     * @param genericParam The generic parameter.
     */
    public void testGenericParameterJsonFunction(T genericParam);
    
    /**
     * The test generic array return value JSON function.
     *
     * @return The generic array return value.
     */
    public T[] testGenericArrayReturnValueJsonFunction();
    
    /**
     * The test generic array parameter JSON function.
     *
     * @param genericArrayParam The generic array parameter.
     */
    public void testGenericArrayParameterJsonFunction(T[] genericArrayParam);
  }
  
  /**
   * Test Serializable Generic Interface.
   */
  public interface TestSerializableGenericInterface<S extends Serializable> {
    /**
     * The test generic parameter JSON function.
     *
     * @param genericParam The generic parameter.
     */
    public void testGenericParameterJsonFunction(S genericParam);
  }
  
  /**
   * Test Comparable Generic Interface.
   */
  public interface TestComparableGenericInterface<C extends Comparable<?>> {
    /**
     * The test generic parameter JSON function.
     *
     * @param genericParam The generic parameter.
     */
    public void testGenericParameterJsonFunction(C genericParam);
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
     * The test return value JSON function.
     *
     * @return The return value.
     */
    @Override
    @JsonFunction(klass = JvmJsonObjectGeneratorBridgeTestBase.class, method = "testReturnValueJsonFunction")
    public String testReturnValueJsonFunction();
    
    /**
     * The test generic return value JSON function.
     *
     * @return The generic return value.
     */
    @Override
    @JsonFunction(klass = JvmJsonObjectGeneratorBridgeTestBase.class, method = "testGenericReturnValueJsonFunction")
    public String testGenericReturnValueJsonFunction();
    
    /**
     * The test generic parameter JSON function.
     *
     * @param genericParam The generic parameter.
     */
    @Override
    @JsonFunction(klass = JvmJsonObjectGeneratorBridgeTestBase.class, method = "testGenericParameterJsonFunction")
    public void testGenericParameterJsonFunction(String genericParam);
    
    
    /**
     * The test generic array return value JSON function.
     *
     * @return The generic array return value.
     */
    @Override
    @JsonFunction(klass = JvmJsonObjectGeneratorBridgeTestBase.class, method = "testGenericArrayReturnValueJsonFunction")
    public String[] testGenericArrayReturnValueJsonFunction();
    
    /**
     * The test generic array parameter JSON function.
     *
     * @param genericArrayParam The generic array parameter.
     */
    @Override
    @JsonFunction(klass = JvmJsonObjectGeneratorBridgeTestBase.class, method = "testGenericArrayParameterJsonFunction")
    public void testGenericArrayParameterJsonFunction(String[] genericArrayParam);
  }
  
  /** The return value. */
  private static final String RETURN_VALUE = "Test Return Value";
  
  /** The generic return value. */
  private static final String GENERIC_RETURN_VALUE = "Test Generic Return Value";
  
  /** The generic array return value. */
  private static final String[] GENERIC_ARRAY_RETURN_VALUE = new String[] {};
  
  /** The test JSON object functions. */
  private static Functions functions;
  
  /**
   * The test return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The return value.
   */
  public static String testReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * The test generic return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The generic return value.
   */
  public static String testGenericReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testGenericReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * The test generic parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param genericParam The generic parameter.
   */
  public static void testGenericParameterJsonFunction(JsonObject jsonObject, String genericParam) {
    functions.testGenericParameterJsonFunction(jsonObject, genericParam);
  }
  
  /**
   * The test generic array return value JSON function.
   *
   * @param jsonObject The JSON object.
   * @return The generic array return value.
   */
  public static String[] testGenericArrayReturnValueJsonFunction(JsonObject jsonObject) {
    return functions.testGenericArrayReturnValueJsonFunction(jsonObject);
  }
  
  /**
   * The test generic array parameter JSON function.
   *
   * @param jsonObject The JSON object.
   * @param genericArrayParam The generic array parameter.
   */
  public static void testGenericArrayParameterJsonFunction(JsonObject jsonObject, String[] genericArrayParam) {
    functions.testGenericArrayParameterJsonFunction(jsonObject, genericArrayParam);
  }
  
  /**
   * Setup the test.
   */
  @Before
  public void setup() {
    functions = mock(Functions.class);
    
    when(functions.testReturnValueJsonFunction((JsonObject)any())).thenReturn(RETURN_VALUE);
    when(functions.testGenericReturnValueJsonFunction((JsonObject)any())).thenReturn(GENERIC_RETURN_VALUE);
    when(functions.testGenericArrayReturnValueJsonFunction((JsonObject)any())).thenReturn(GENERIC_ARRAY_RETURN_VALUE);
  }
  
  /**
   * Cleanup the test.
   */
  @After
  public void cleanup() {
    functions = null;
  }
  
  /**
   * Test the invocation of a return value JSON function.
   * <p>
   * This test asserts that the return value JSON function is invoked
   * correctly.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testInvokeReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(RETURN_VALUE, ((TestObjectGenericInterface)testJsonObject).testReturnValueJsonFunction());
  }
  
  /**
   * Test the invocation of a generic return value JSON function.
   * <p>
   * This test asserts that the generic return value JSON function is invoked
   * correctly.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testInvokeGenericReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertEquals(GENERIC_RETURN_VALUE, ((TestObjectGenericInterface)testJsonObject).testGenericReturnValueJsonFunction());
  }
  
  /**
   * Test the invocation of a generic parameter JSON function.
   * <p>
   * This test asserts that the test generic parameter JSON function is invoked
   * with an {@link Object} argument correctly.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testInvokeGenericParameterJsonFunctionWithObjectArgument() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    ((TestObjectGenericInterface)testJsonObject).testGenericParameterJsonFunction("Test Generic Parameter");
    
    verify(functions).testGenericParameterJsonFunction(testJsonObject, "Test Generic Parameter");
  }
  
  /**
   * Test the invocation of a generic parameter JSON function.
   * <p>
   * This test asserts that the test generic parameter JSON function is invoked
   * with an {@link Serializable} argument correctly.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testInvokeGenericParameterJsonFunctionWithSerializableArgument() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    ((TestSerializableGenericInterface)testJsonObject).testGenericParameterJsonFunction("Test Generic Parameter");
    
    verify(functions).testGenericParameterJsonFunction(testJsonObject, "Test Generic Parameter");
  }
  
  /**
   * Test the invocation of a generic parameter JSON function.
   * <p>
   * This test asserts that the test generic parameter JSON function is invoked
   * with an {@link Comparable} argument correctly.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testInvokeGenericParameterJsonFunctionWithComparableArgument() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    ((TestComparableGenericInterface)testJsonObject).testGenericParameterJsonFunction("Test Generic Parameter");
    
    verify(functions).testGenericParameterJsonFunction(testJsonObject, "Test Generic Parameter");
  }
  
  /**
   * Test the invocation of a generic array return value JSON function.
   * <p>
   * This test asserts that the generic array return value JSON function is
   * invoked correctly.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testInvokeGenericArrayReturnValueJsonFunction() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    assertSame(GENERIC_ARRAY_RETURN_VALUE, ((TestObjectGenericInterface)testJsonObject).testGenericArrayReturnValueJsonFunction());
  }
  
  /**
   * Test the invocation of a generic array parameter JSON function.
   * <p>
   * This test asserts that the test generic array parameter JSON function is
   * invoked with an {@link Object}[] argument correctly.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testInvokeGenericArrayParameterJsonFunctionWithObjectArgument() {
    String[] genericArrayParam = new String[] {};
    
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    ((TestObjectGenericInterface)testJsonObject).testGenericArrayParameterJsonFunction(genericArrayParam);
    
    verify(functions).testGenericArrayParameterJsonFunction(testJsonObject, genericArrayParam);
  }
}
