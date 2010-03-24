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
package org.kjots.json.object.server.simple.impl;

import org.junit.Before;
import org.kjots.json.object.server.simple.SimpleJsonObjectModule;
import org.kjots.json.object.server.simple.SimpleJsonValue;
import org.kjots.json.object.shared.JsonStringMap;
import org.kjots.json.object.shared.impl.JsonStringMapImplTestBase;

import com.google.inject.Guice;

/**
 * Simple JSON String Map Implementation Test.
 * <p>
 * Created: 9th March 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class SimpleJsonStringMapImplTest extends JsonStringMapImplTestBase<SimpleJsonValue> {
  /**
   * Set up the JSON object implementation test.
   */
  @Before
  public void setUp() {
    Guice.createInjector(new SimpleJsonObjectModule());
  }
  
  /**
   * Create a JSON string map with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON string map.
   */
  @Override
  protected JsonStringMap createJsonStringMap(SimpleJsonValue object) {
    return new SimpleJsonStringMapImpl(object);
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
}
