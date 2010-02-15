/* 
 * Copyright © 2009 Karl J. Ots <kjots@kjots.org>
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
package org.kjots.json.object.server.js.impl;

import javax.script.Invocable;
import javax.script.ScriptException;

import org.junit.After;
import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;

import org.kjots.json.object.server.js.JsJsonObjectModule;
import org.kjots.json.object.server.js.impl.JsJsonIntegerArrayImpl;
import org.kjots.json.object.shared.JsonIntegerArray;
import org.kjots.json.object.shared.impl.JsonIntegerArrayImplTestBase;

/**
 * JavaScript JSON Integer Array Implementation Test.
 * <p>
 * Created: 12th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonIntegerArrayImplTest extends JsonIntegerArrayImplTestBase<Object> {
  /** The injector. */
  private Injector injector;
  
  /**
   * Set up the JSON object implementation test.
   */
  @Before
  public void setUp() {
    this.injector = Guice.createInjector(new JsJsonObjectModule());
  }
  
  /**
   * Tear down the JSON object implementation test.
   */
  @After
  public void tearDown() {
    this.injector = null;
  }
  
  /**
   * Create a JSON integer array with the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The JSON integer array.
   */
  @Override
  protected JsonIntegerArray createJsonIntegerArray(Object array) {
    return new JsJsonIntegerArrayImpl(this.getJsEngine(), array);
  }

  /**
   * Create an empty underlying JSON array.
   *
   * @return The empty underlying JSON array.
   */
  @Override
  protected Object createUnderlyingJsonArray() {
    return this.injector.getInstance(Key.get(Object.class, JsJsonObjectModule.JsArray.class));
  }
  
  /**
   * Retrieve the length of the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The length of the underlying JSON array.
   * @see #setArrayLength(Object, int)
   */
  @Override
  protected int getArrayLength(Object array) {
    Double arrayLength = this.invokeFunction("getArrayLength", array);
    
    return arrayLength.intValue();
  }
  
  /**
   * Retrieve the integer value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The integer value of the element.
   * @see #setIntegerElement(Object, int, int)
   */
  @Override
  protected int getIntegerElement(Object array, int elementIndex) {
    return this.invokeFunction("getProperty", array, elementIndex);
  }
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given integer value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The integer value of the element.
   * @see #getIntegerElement(Object, int)
   */
  @Override
  protected void setIntegerElement(Object array, int elementIndex, int elementValue) {
    this.invokeFunction("setProperty", array, elementIndex, elementValue);
  }
  
  /**
   * Retrieve the JavaScript engine.
   *
   * @return The JavaScript engine.
   */
  private Invocable getJsEngine() {
    return this.injector.getInstance(Key.get(Invocable.class, JsJsonObjectModule.JsEngine.class));
  }

  /**
   * Invoke the function with the given name and arguments and return the
   * result.
   *
   * @param <T> The type of the result.
   * @param name The name of the function.
   * @param args The arguments.
   * @return The result.
   */
  @SuppressWarnings("unchecked")
  private <T> T invokeFunction(String name, Object... args) {
    try {
      return (T)this.getJsEngine().invokeFunction(name, args);
    }
    catch (ScriptException se) {
      throw new IllegalStateException(se);
    }
    catch (NoSuchMethodException nsme) {
      throw new IllegalStateException(nsme);
    }
  }
}
