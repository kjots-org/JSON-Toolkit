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
package org.kjots.json.object.shared;

import com.google.inject.Inject;

/**
 * JSON Object Factory.
 * <p>
 * Created: 12th January 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.5
 */
public abstract class JsonObjectFactory {
  /** The JSON object factory. */
  @Inject
  private static JsonObjectFactory jsonObjectFactory;
  
  /**
   * Retrieve the JSON object factory.
   *
   * @return The JSON object factory.
   */
  public static JsonObjectFactory get() {
    return jsonObjectFactory;
  }
  
  /**
   * Create a new JSON object with the given underlying JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  public abstract <T extends JsonObject> T createJsonObject(Class<T> jsonObjectClass, Object object);
  
  /**
   * Create a new JSON object with the given underlying JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClassName The name of the class of the JSON object.
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  public abstract <T extends JsonObject> T createJsonObject(String jsonObjectClassName, Object object);
  
  /**
   * Create a new JSON object.
   *
   * @return The JSON object.
   */
  public JsonObject createJsonObject() {
    return this.createJsonObject(JsonObject.class);
  }

  /**
   * Create a new JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @return The JSON object.
   */
  public abstract <T extends JsonObject> T createJsonObject(Class<T> jsonObjectClass);
  
  /**
   * Create a new JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClassName The name of the class of the JSON object.
   * @return The JSON object.
   */
  public abstract <T extends JsonObject> T createJsonObject(String jsonObjectClassName);
  
  /**
   * Create a new JSON object map.
   *
   * @param <E> The type of the element of the JSON object map.
   * @param elementClass The class of the element of the JSON object array.
   * @return The JSON object map.
   */
  public <E extends JsonObject> JsonObjectMap<E> createJsonObjectMap(Class<E> elementClass) {
    JsonObjectMap<?> jsonObjectMap = this.createJsonObject(JsonObjectMap.class);
    
    return jsonObjectMap.castElement(elementClass);
  }
  
  /**
   * Create a new JSON array.
   *
   * @return The JSON array.
   */
  public JsonArray createJsonArray() {
    return this.createJsonArray(JsonArray.class);
  }
  
  /**
   * Create a new JSON array.
   *
   * @param <T> The type of the JSON array.
   * @param jsonArrayClass The class of the JSON array.
   * @return The JSON array.
   */
  public abstract <T extends JsonArray> T createJsonArray(Class<T> jsonArrayClass);
  
  /**
   * Create a new JSON array.
   *
   * @param <T> The type of the JSON array.
   * @param jsonArrayClassName The name of the class of the JSON array.
   * @return The JSON array.
   */
  public abstract <T extends JsonArray> T createJsonArray(String jsonArrayClassName);
  
  /**
   * Create a new JSON object array.
   *
   * @param <E> The type of the element of the JSON object array.
   * @param elementClass The class of the element of the JSON object array.
   * @return The JSON object array.
   */
  public <E extends JsonObject> JsonObjectArray<E> createJsonObjectArray(Class<E> elementClass) {
    JsonObjectArray<?> jsonObjectArray = this.createJsonArray(JsonObjectArray.class);
    
    return jsonObjectArray.castElement(elementClass);
  }
}
