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
package org.kjots.json.object.js.impl;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.script.Invocable;

import org.kjots.json.object.impl.JvmJsonObjectFactoryImplBase;
import org.kjots.json.object.js.JsArray;
import org.kjots.json.object.js.JsEngine;
import org.kjots.json.object.js.JsJsonObjectGenerator;
import org.kjots.json.object.js.JsObject;
import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonBooleanArray;
import org.kjots.json.object.shared.JsonBooleanMap;
import org.kjots.json.object.shared.JsonNumberArray;
import org.kjots.json.object.shared.JsonNumberMap;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectArray;
import org.kjots.json.object.shared.JsonObjectMap;
import org.kjots.json.object.shared.JsonStringArray;
import org.kjots.json.object.shared.JsonStringMap;

/**
 * JavaScript JSON Object Factory Implementation.
 * <p>
 * Created: 12th January 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.5
 */
public class JsJsonObjectFactoryImpl extends JvmJsonObjectFactoryImplBase {
  /** The JSON object generator. */
  private final JsJsonObjectGenerator jsonObjectGenerator;
  
  /** The JavaScript engine. */
  private final Invocable jsEngine;
  
  /** The JavaScript object provider. */
  private final Provider<Object> jsObjectProvider;
  
  /** The JavaScript array provider. */
  private final Provider<Object> jsArrayProvider;
  
  /**
   * Construct a new JavaScript JSON Object Factory Implementation.
   *
   * @param jsonObjectGenerator The JSON object generator.
   * @param jsEngine The JavaScript engine.
   * @param jsObjectProvider The JavaScript object provider.
   * @param jsArrayProvider The JavaScript array provider.
   */
  @Inject
  public JsJsonObjectFactoryImpl(JsJsonObjectGenerator jsonObjectGenerator, @JsEngine Invocable jsEngine, @JsObject Provider<Object> jsObjectProvider, @JsArray Provider<Object> jsArrayProvider) {
    this.jsonObjectGenerator = jsonObjectGenerator;
    this.jsEngine = jsEngine;
    this.jsObjectProvider = jsObjectProvider;
    this.jsArrayProvider = jsArrayProvider;
  }
  
  /**
   * Create a new JSON object with the given underlying JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <T extends JsonObject> T createJsonObject(Class<T> jsonObjectClass, Object object) {
    JsJsonObjectImpl jsonObjectImpl = this.createStaticJsonObject(jsonObjectClass, object);
    if (jsonObjectImpl == null) {
      jsonObjectImpl = this.jsonObjectGenerator.newJsonObjectImpl(jsonObjectClass, this.jsEngine, object);
    }
    
    return (T)jsonObjectImpl;
  }

  /**
   * Create a new JSON object with the given underlying JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClassName The name of the class of the JSON object.
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <T extends JsonObject> T createJsonObject(String jsonObjectClassName, Object object) {
    try {
      return this.createJsonObject((Class<T>)Class.forName(jsonObjectClassName), object);
    }
    catch (ClassNotFoundException cnfe) {
      throw new IllegalArgumentException(jsonObjectClassName, cnfe);
    }
  }
  
  /**
   * Create a new JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @return The JSON object.
   */
  @Override
  public final <T extends JsonObject> T createJsonObject(Class<T> jsonObjectClass) {
    return this.createJsonObject(jsonObjectClass, this.jsObjectProvider.get());
  }
  
  /**
   * Create a new JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClassName The name of the class of the JSON object.
   * @return The JSON object.
   */
  @Override
  public final <T extends JsonObject> T createJsonObject(String jsonObjectClassName) {
    return this.<T>createJsonObject(jsonObjectClassName, this.jsObjectProvider.get());
  }
  
  /**
   * Create a new JSON array.
   *
   * @param <T> The type of the JSON array.
   * @param jsonArrayClass The class of the JSON array.
   * @return The JSON array.
   */
  @Override
  public final <T extends JsonArray> T createJsonArray(Class<T> jsonArrayClass) {
    return this.createJsonObject(jsonArrayClass, this.jsArrayProvider.get());
  }
  
  /**
   * Create a new JSON array.
   *
   * @param <T> The type of the JSON array.
   * @param jsonArrayClassName The name of the class of the JSON array.
   * @return The JSON array.
   */
  @Override
  public final <T extends JsonArray> T createJsonArray(String jsonArrayClassName) {
    return this.<T>createJsonObject(jsonArrayClassName, this.jsArrayProvider.get());
  }
  
  /**
   * Create a new JSON object instance with given class using the given
   * underlying JSON object.
   * <p>
   * This method will only create JSON object instances with statically defined
   * implementations.
   *
   * @param jsonObjectClass The class of the JSON object.
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  private JsJsonObjectImpl createStaticJsonObject(Class<? extends JsonObject> jsonObjectClass, Object object) {
    if (jsonObjectClass.equals(JsonObject.class)) {
      return new JsJsonObjectImpl(JsonObject.class, this.jsEngine, object);
    }
    else if (jsonObjectClass.equals(JsonArray.class)) {
      return new JsJsonArrayImpl(JsonArray.class, this.jsEngine, object);
    }
    else if (jsonObjectClass.equals(JsonBooleanArray.class)) {
      return new JsJsonBooleanArrayImpl(this.jsEngine, object);
    }
    else if (jsonObjectClass.equals(JsonNumberArray.class)) {
      return new JsJsonNumberArrayImpl(this.jsEngine, object);
    }
    else if (jsonObjectClass.equals(JsonStringArray.class)) {
      return new JsJsonStringArrayImpl(this.jsEngine, object);
    }
    else if (jsonObjectClass.equals(JsonObjectArray.class)) {
      return new JsJsonObjectArrayImpl<JsonObject>(this.jsEngine, object);
    }
    else if (jsonObjectClass.equals(JsonBooleanMap.class)) {
      return new JsJsonBooleanMapImpl(this.jsEngine, object);
    }
    else if (jsonObjectClass.equals(JsonNumberMap.class)) {
      return new JsJsonNumberMapImpl(this.jsEngine, object);
    }
    else if (jsonObjectClass.equals(JsonStringMap.class)) {
      return new JsJsonStringMapImpl(this.jsEngine, object);
    }
    else if (jsonObjectClass.equals(JsonObjectMap.class)) {
      return new JsJsonObjectMapImpl<JsonObject>(this.jsEngine, object);
    }
    else {
      return null;
    }
  }
}
