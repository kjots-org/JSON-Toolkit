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
import org.kjots.json.object.shared.JsonBooleanMap;
import org.kjots.json.object.shared.impl.JsonBooleanMapImplTestBase;
import org.kjots.json.object.simple.SimpleJsonObjectModule;
import org.kjots.json.object.simple.SimpleJsonValue;

import com.google.inject.Guice;

/**
 * Simple JSON Boolean Map Implementation Test.
 * <p>
 * Created: 9th March 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class SimpleJsonBooleanMapImplTest extends JsonBooleanMapImplTestBase<SimpleJsonValue> {
  /**
   * Set up the JSON object implementation test.
   */
  @Before
  public void setUp() {
    Guice.createInjector(new SimpleJsonObjectModule());
  }
  
  /**
   * Create a JSON boolean map with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON boolean map.
   */
  @Override
  protected JsonBooleanMap createJsonBooleanMap(SimpleJsonValue object) {
    return new SimpleJsonBooleanMapImpl(object);
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
}
