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
package org.kjots.json.object.simple.impl;

import org.junit.Before;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.impl.JsonObjectImplTestBase;
import org.kjots.json.object.simple.SimpleJsonObjectModule;
import org.kjots.json.object.simple.SimpleJsonValue;

import com.google.inject.Guice;

/**
 * Simple JSON Object Implementation Test.
 * <p>
 * Created: 4th March 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class SimpleJsonObjectImplTest extends JsonObjectImplTestBase<SimpleJsonValue> {
  /**
   * Set up the JSON object implementation test.
   */
  @Before
  public void setUp() {
    Guice.createInjector(new SimpleJsonObjectModule());
  }
  
  /**
   * Create a JSON object with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  @Override
  protected JsonObject createJsonObject(SimpleJsonValue object) {
    return new SimpleJsonObjectImpl(object);
  }

  /**
   * Create an empty underlying JSON object.
   *
   * @return The empty underlying JSON object.
   */
  @Override
  protected SimpleJsonValue createUnderlyingJsonObject() {
    return SimpleJsonValue.createObject();
  }
  
  /**
   * Create an empty underlying JSON array.
   *
   * @return The empty underlying JSON array.
   */
  @Override
  protected SimpleJsonValue createUnderlyingJsonArray() {
    return SimpleJsonValue.createArray();
  }
  
  /**
   * Retrieve the boolean value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The boolean value of the property.
   * @see #setBooleanProperty(SimpleJsonValue, String, Boolean)
   */
  @Override
  protected Boolean getBooleanProperty(SimpleJsonValue object, String propertyName) {
    return (Boolean)object.getMap().get(propertyName);
  }
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given boolean value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The boolean value of the property.
   * @see #getBooleanProperty(SimpleJsonValue, String)
   */
  @Override
  protected void setBooleanProperty(SimpleJsonValue object, String propertyName, Boolean propertyValue) {
    object.getMap().put(propertyName, propertyValue);
  }
  
  /**
   * Retrieve the number value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The number value of the property.
   * @see #setNumberProperty(SimpleJsonValue, String, Number)
   */
  @Override
  protected Number getNumberProperty(SimpleJsonValue object, String propertyName) {
    return (Number)object.getMap().get(propertyName);
  }
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given number value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The number value of the property.
   * @see #getNumberProperty(SimpleJsonValue, String)
   */
  @Override
  protected void setNumberProperty(SimpleJsonValue object, String propertyName, Number propertyValue) {
    object.getMap().put(propertyName, propertyValue);
  }
  
  /**
   * Retrieve the string value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The string value of the property.
   * @see #setStringProperty(SimpleJsonValue, String, String)
   */
  @Override
  protected String getStringProperty(SimpleJsonValue object, String propertyName) {
    return (String)object.getMap().get(propertyName);
  }
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given string value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The string value of the property.
   * @see #getStringProperty(SimpleJsonValue, String)
   */
  @Override
  protected void setStringProperty(SimpleJsonValue object, String propertyName, String propertyValue) {
    object.getMap().put(propertyName, propertyValue);
  }
  
  /**
   * Retrieve the object value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The object value of the property.
   * @see #setObjectProperty(SimpleJsonValue, String, Object)
   */
  @Override
  protected SimpleJsonValue getObjectProperty(SimpleJsonValue object, String propertyName) {
    return (SimpleJsonValue)object.getMap().get(propertyName);
  }
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given object value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The object value of the property.
   * @see #getObjectProperty(SimpleJsonValue, String)
   */
  @Override
  protected void setObjectProperty(SimpleJsonValue object, String propertyName, SimpleJsonValue propertyValue) {
    object.getMap().put(propertyName, propertyValue);
  }
}
