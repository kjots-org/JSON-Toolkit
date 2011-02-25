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
package org.kjots.json.object.js.impl;

import javax.script.Invocable;
import javax.script.ScriptException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;

import org.junit.After;
import org.junit.Before;

import org.kjots.json.object.js.JsJsonObjectModule;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.impl.JsonObjectImplTestBase;

/**
 * JSON Object Implementation Test.
 * <p>
 * Created: 11th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonObjectImplTest extends JsonObjectImplTestBase<Object> {
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
   * Create a JSON object with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  @Override
  protected JsonObject createJsonObject(Object object) {
    return new JsJsonObjectImpl(JsonObject.class, this.getJsEngine(), object);
  }

  /**
   * Create an empty underlying JSON object.
   *
   * @return The empty underlying JSON object.
   */
  @Override
  protected Object createUnderlyingJsonObject() {
    return this.injector.getInstance(Key.get(Object.class, JsJsonObjectModule.JsObject.class));
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
   * Retrieve the boolean value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The boolean value of the property.
   * @see #setBooleanProperty(Object, String, Boolean)
   */
  @Override
  protected Boolean getBooleanProperty(Object object, String propertyName) {
    return this.invokeFunction("getProperty", object, propertyName);
  }
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given boolean value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The boolean value of the property.
   * @see #getBooleanProperty(Object, String)
   */
  @Override
  protected void setBooleanProperty(Object object, String propertyName, Boolean propertyValue) {
    this.invokeFunction("setProperty", object, propertyName, propertyValue);
  }
  
  /**
   * Retrieve the number value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The number value of the property.
   * @see #setNumberProperty(Object, String, Number)
   */
  @Override
  protected Number getNumberProperty(Object object, String propertyName) {
    return this.invokeFunction("getProperty", object, propertyName);
  }
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given number value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The number value of the property.
   * @see #getNumberProperty(Object, String)
   */
  @Override
  protected void setNumberProperty(Object object, String propertyName, Number propertyValue) {
    this.invokeFunction("setProperty", object, propertyName, propertyValue);
  }
  
  /**
   * Retrieve the string value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The string value of the property.
   * @see #setStringProperty(Object, String, String)
   */
  @Override
  protected String getStringProperty(Object object, String propertyName) {
    return this.invokeFunction("getProperty", object, propertyName);
  }
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given string value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The string value of the property.
   * @see #getStringProperty(Object, String)
   */
  @Override
  protected void setStringProperty(Object object, String propertyName, String propertyValue) {
    this.invokeFunction("setProperty", object, propertyName, propertyValue);
  }
  
  /**
   * Retrieve the object value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The object value of the property.
   * @see #setObjectProperty(Object, String, Object)
   */
  @Override
  protected Object getObjectProperty(Object object, String propertyName) {
    return this.invokeFunction("getProperty", object, propertyName);
  }
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given object value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The object value of the property.
   * @see #getObjectProperty(Object, String)
   */
  @Override
  protected void setObjectProperty(Object object, String propertyName, Object propertyValue) {
    this.invokeFunction("setProperty", object, propertyName, propertyValue);
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
