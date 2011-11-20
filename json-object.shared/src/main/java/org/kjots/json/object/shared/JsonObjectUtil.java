/* 
 * Copyright © 2011 Karl J. Ots <kjots@kjots.org>
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

import java.util.List;
import java.util.Map;

import org.kjots.json.content.shared.JsonContentUtil;

import org.kjots.json.object.shared.content.JsonObjectBuilder;
import org.kjots.json.object.shared.content.JsonObjectContentGenerator;

/**
 * JSON Object Utility.
 * <p>
 * Created: 25th February 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.1
 */
public class JsonObjectUtil {
  /**
   * Create a JSON object from the given map.
   * <p>
   * This method will return <code>null</code> if the given map is <code>null</code>.
   *
   * @param map The map.
   * @return The JSON object.
   */
  public static JsonObject forMap(Map<String, ?> map) {
    return forMap(JsonObject.class, map);
  }
  
  /**
   * Create a JSON object from the given map.
   * <p>
   * This method will return <code>null</code> if the given map is <code>null</code>.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @param map The map.
   * @return The JSON object.
   */
  public static <T extends JsonObject> T forMap(Class<T> jsonObjectClass, Map<String, ?> map) {
    if (map == null) {
      return null;
    }
    
    return JsonContentUtil.handle(new JsonObjectBuilder(), map).getJsonObject().cast(jsonObjectClass);
  }
  
  /**
   * Create a JSON array from the given list.
   * <p>
   * This method will return <code>null</code> if the given list is <code>null</code>.
   *
   * @param list The list.
   * @return The JSON array.
   */
  public static JsonArray forList(List<?> list) {
    return forList(JsonArray.class, list);
  }
  
  /**
   * Create a JSON array from the given list.
   * <p>
   * This method will return <code>null</code> if the given list is <code>null</code>.
   *
   * @param <T> The type of the JSON array.
   * @param jsonArrayClass The class of the JSON array.
   * @param list The list.
   * @return The JSON array.
   */
  public static <T extends JsonArray> T forList(Class<T> jsonArrayClass, List<?> list) {
    if (list == null) {
      return null;
    }
    
    return JsonContentUtil.handle(new JsonObjectBuilder(), list).getJsonObject().cast(jsonArrayClass);
  }
  
  /**
   * Clone the given JSON object.
   * <p>
   * This method will return <code>null</code> if the given JSON object is <code>null</code>.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObject The JSON object.
   * @return The cloned JSON object.
   */
  @SuppressWarnings("unchecked")
  public static <T extends JsonObject> T clone(T jsonObject) {
    if (jsonObject == null) {
      return null;
    }
    
    JsonObjectContentGenerator jsonObjectContentGenerator = new JsonObjectContentGenerator();
    JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
    
    jsonObjectContentGenerator.setJsonContentHandler(jsonObjectBuilder);
    
    jsonObjectContentGenerator.generateContent(jsonObject);
    
    return jsonObjectBuilder.getJsonObject().cast((Class<T>)jsonObject.getJsonObjectClass());
  }
  
  /**
   * Merge the given JSON objects.
   *
   * @param jsonObjects The JSON objects.
   * @return The merged JSON object.
   */
  public static JsonObject merge(JsonObject... jsonObjects) {
    return mergeInto(JsonObjectFactory.get().createJsonObject(), jsonObjects);
  }
  
  /**
   * Merge the given JSON objects.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @param jsonObjects The JSON objects.
   * @return The merged JSON object.
   */
  public static <T extends JsonObject> T merge(Class<T> jsonObjectClass, T... jsonObjects) {
    return mergeInto(JsonObjectFactory.get().createJsonObject(jsonObjectClass), jsonObjects);
  }
  
  /**
   * Merge given JSON objects into the given JSON object.
   * <p>
   * For convenience, this method will return the given JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObject The JSON object.
   * @param jsonObjects The JSON objects.
   * @return The JSON object.
   */
  public static <T extends JsonObject> T mergeInto(T jsonObject, T... jsonObjects) {
    JsonObjectContentGenerator jsonObjectContentGenerator = new JsonObjectContentGenerator();
    JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder(jsonObject);
    
    jsonObjectContentGenerator.setJsonContentHandler(jsonObjectBuilder);
    
    for (JsonObject jsonObject2 : jsonObjects) {
      jsonObjectContentGenerator.generateContent(jsonObject2);
    }
    
    return jsonObject;
  }
}
