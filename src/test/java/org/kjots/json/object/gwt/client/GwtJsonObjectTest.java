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
package org.kjots.json.object.gwt.client;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectArray;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.shared.JsonObjectMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * GWT JSON Object Test.
 * <p>
 * Created: 11th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 0.1
 */
public abstract class GwtJsonObjectTest extends GWTTestCase {
  /**
   * GWT JSON Object Test Ginjector.
   * <p>
   * Created: 29th March 2010.
   */
  @GinModules(JsonObjectGinModule.class)
  public static interface Ginjector extends com.google.gwt.inject.client.Ginjector {
  }
  
  /**
   * Retrieve the name of the GWT module.
   *
   * @return The name of the GWT module.
   */
  @Override
  public final String getModuleName() {
    return "org.kjots.json.object.JsonObjectTest";
  }
  
  /**
   * Set up the GWT test case.
   */
  @Override
  protected void gwtSetUp() {
    GWT.create(Ginjector.class);
  }
  
  /**
   * Create a JSON object with the given class using the given JavaScript
   * object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @param jsObject The JavaScript object.
   * @return The JSON object.
   */
  protected <T extends JsonObject> T createJsonObject(Class<T> jsonObjectClass, JavaScriptObject jsObject) {
    return JsonObjectFactory.get().createJsonObject(jsonObjectClass, jsObject);
  }
  
  /**
   * Create a JSON object array with the given element class using the given
   * JavaScript object.
   *
   * @param <E> The type of the element of the JSOB object array.
   * @param jsonObjectClass The type of the element of the JSOB object array.
   * @param jsObject The JavaScript object.
   * @return The JSON object array.
   */
  protected <E extends JsonObject> JsonObjectArray<E> createJsonObjectArray(Class<E> jsonObjectClass, JavaScriptObject jsObject) {
    JsonObjectArray<?> jsonObjectArray = JsonObjectFactory.get().createJsonObject(JsonObjectArray.class, jsObject);
    
    return jsonObjectArray.castElement(jsonObjectClass);
  }
  
  /**
   * Create a JSON object map with the given element class using the given
   * JavaScript object.
   *
   * @param <E> The type of the element of the JSOB object map
   * @param jsonObjectClass The type of the element of the JSOB object map.
   * @param jsObject The JavaScript object.
   * @return The JSON object Map.
   */
  protected <E extends JsonObject> JsonObjectMap<E> createJsonObjectMap(Class<E> jsonObjectClass, JavaScriptObject jsObject) {
    JsonObjectMap<?> jsonObjectMap = JsonObjectFactory.get().createJsonObject(JsonObjectMap.class, jsObject);
    
    return jsonObjectMap.castElement(jsonObjectClass);
  }
}
