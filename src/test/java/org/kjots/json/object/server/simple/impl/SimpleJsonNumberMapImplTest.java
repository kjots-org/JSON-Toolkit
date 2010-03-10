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
import org.kjots.json.object.shared.JsonNumberMap;
import org.kjots.json.object.shared.impl.JsonNumberMapImplTestBase;

import com.google.inject.Guice;

/**
 * Simple JSON Number Map Implementation Test.
 * <p>
 * Created: 9th March 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class SimpleJsonNumberMapImplTest extends JsonNumberMapImplTestBase<SimpleJsonValue> {
  /**
   * Set up the JSON object implementation test.
   */
  @Before
  public void setUp() {
    Guice.createInjector(new SimpleJsonObjectModule());
  }
  
  /**
   * Create a JSON number map with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON number map.
   */
  @Override
  protected JsonNumberMap createJsonNumberMap(SimpleJsonValue object) {
    return new SimpleJsonNumberMapImpl(object);
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
}
