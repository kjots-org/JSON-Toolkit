/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.server.js.impl;

import java.lang.reflect.InvocationTargetException;

import javax.script.Invocable;

import org.kjots.json.object.server.js.JsJsonObjectGenerator;
import org.kjots.json.object.server.js.JsJsonObjectModule;
import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonBooleanArray;
import org.kjots.json.object.shared.JsonBooleanMap;
import org.kjots.json.object.shared.JsonNumberArray;
import org.kjots.json.object.shared.JsonNumberMap;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectArray;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.shared.JsonObjectMap;
import org.kjots.json.object.shared.JsonStringArray;
import org.kjots.json.object.shared.JsonStringMap;

import com.google.inject.Inject;

/**
 * JavaScript JSON Object Factory Implementation.
 * <p>
 * Created: 12th January 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.5
 */
public class JsJsonObjectFactoryImpl extends JsonObjectFactory {
  /** The JSON object generator. */
  @Inject
  private JsJsonObjectGenerator jsonObjectGenerator;
  
  /** The JavaScript engine. */
  @Inject
  @JsJsonObjectModule.JsEngine
  private Invocable jsEngine;
  
  /** The JavaScript object provider. */
  @Inject
  private JsJsonObjectModule.JsObjectProvider jsObjectProvider;
  
  /** The JavaScript array provider. */
  @Inject
  private JsJsonObjectModule.JsArrayProvider jsArrayProvider;
  
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
  public <T extends JsonObject> T createJsonObject(Class<T> jsonObjectClass, Object object) {
    JsJsonObjectImpl jsonObjectImpl = this.createStaticJsonObject(jsonObjectClass, object);
    if (jsonObjectImpl == null) {
      Class<? extends JsJsonObjectImpl> jsonObjectImplClass = this.jsonObjectGenerator.getJsonObjectImplClass(jsonObjectClass);

      try {
        jsonObjectImpl = jsonObjectImplClass.getConstructor(Invocable.class, Object.class).newInstance(this.jsEngine, object);
      }
      catch (NoSuchMethodException nsme) {
        throw new IllegalStateException(nsme);
      }
      catch (IllegalAccessException iae) {
        throw new IllegalStateException(iae);
      }
      catch (InstantiationException ie) {
        throw new IllegalStateException(ie);
      }
      catch (InvocationTargetException ite) {
        Throwable t = ite.getCause();
        
        if (t instanceof RuntimeException) {
          throw (RuntimeException)t;
        }
        else if (t instanceof Error) {
          throw (Error)t;
        }
        else {
          throw new IllegalStateException(t);
        }
      }
    }
    
    return (T)jsonObjectImpl;
  }

  /**
   * Create a new JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @return The JSON object.
   */
  @Override
  public <T extends JsonObject> T createJsonObject(Class<T> jsonObjectClass) {
    return this.createJsonObject(jsonObjectClass, this.jsObjectProvider.get());
  }
  
  /**
   * Create a new JSON array.
   *
   * @param <T> The type of the JSON array.
   * @param jsonArrayClass The class of the JSON array.
   * @return The JSON array.
   */
  @Override
  public <T extends JsonArray> T createJsonArray(Class<T> jsonArrayClass) {
    return this.createJsonObject(jsonArrayClass, this.jsArrayProvider.get());
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
      return new JsJsonObjectImpl(this.jsEngine, object);
    }
    else if (jsonObjectClass.equals(JsonArray.class)) {
      return new JsJsonArrayImpl(this.jsEngine, object);
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