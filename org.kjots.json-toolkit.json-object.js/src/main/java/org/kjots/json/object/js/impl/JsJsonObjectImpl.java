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

import java.util.HashSet;
import java.util.Set;

import javax.script.Invocable;
import javax.script.ScriptException;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;

/**
 * JavaScript JSON Object Implementation.
 * <p>
 * Created: 11th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonObjectImpl implements JsonObject {
  /** The JSON object class. */
  protected final Class<? extends JsonObject> jsonObjectClass;
  
  /** The JavaScript engine. */
  protected final Invocable jsEngine;
  
  /** The JavaScript object. */
  protected final Object jsObject;
  
  /**
   * Construct a new JavaScript JSON Object Implementation.
   *
   * @param jsonObjectClass The JSON object class.
   * @param jsEngine The JavaScript engine.
   * @param jsObject The JavaScript object.
   */
  public JsJsonObjectImpl(Class<? extends JsonObject> jsonObjectClass, Invocable jsEngine, Object jsObject) {
    this.jsonObjectClass = jsonObjectClass;
    this.jsEngine = jsEngine;
    this.jsObject = jsObject;
  }
  
  /**
   * Construct a new JavaScript JSON Object Implementation.
   *
   * @param jsEngine The JavaScript engine.
   * @param jsObject The JavaScript object.
   */
  public JsJsonObjectImpl(Invocable jsEngine, Object jsObject) {
    this(JsonObject.class, jsEngine, jsObject);
  }
  
  /**
   * Retrieve the JSON object class.
   *
   * @return The JSON object class.
   */
  @Override
  public Class<? extends JsonObject> getJsonObjectClass() {
    return this.jsonObjectClass;
  }
  
  /**
   * Cast this JSON object a JSON object with the given class.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @return The cast JSON object.
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <T extends JsonObject> T cast(Class<T> jsonObjectClass) {
    if (jsonObjectClass.equals(this.jsonObjectClass)) {
      return (T)this;
    }
    
    return JsonObjectFactory.get().createJsonObject(jsonObjectClass, this.jsObject);
  }
  
  /**
   * Cast this JSON object a JSON object with the given class name.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClassName The name of the class of the JSON object.
   * @return The cast JSON object.
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T extends JsonObject> T cast(String jsonObjectClassName) {
    if (jsonObjectClassName.equals(this.jsonObjectClass.getName())) {
      return (T)this;
    }
    
    return JsonObjectFactory.get().<T>createJsonObject(jsonObjectClassName, this.jsObject);
  }
  
  /**
   * Determine if the JSON object is an array.
   *
   * @return <code>true</code> if the JSON object is an array.
   */
  @Override
  public final boolean isArray() {
    return (Boolean)this.invokeFunction("isArray", this.jsObject);
  }
  
  /**
   * Retrieve the names of the properties of the JSON object.
   *
   * @return The name of the properties of the JSON object.
   */
  @Override
  public final Set<String> getPropertyNames() {
    Set<String> propertyNames = new HashSet<String>();
    
    Object jsPropertyNames = this.invokeFunction("getPropertyNames", this.jsObject);
    Double numPropertyNames = this.invokeFunction("getArrayLength", jsPropertyNames);
    for (int i = 0; i < numPropertyNames.intValue(); i++) {
      String propertyName = this.invokeFunction("getProperty", jsPropertyNames, i);
      
      propertyNames.add(propertyName);
    }
    
    return propertyNames;
  }
  
  /**
   * Determine if the JSON object has a property with the given name.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property.
   */
  @Override
  public final boolean hasProperty(String propertyName) {
    String propertyType = this.invokeFunction("getPropertyType", this.jsObject, propertyName);
    
    return !propertyType.equals("undefined");
  }
  
  /**
   * Determine if the JSON object has a property with the given name and the
   * value <code>null</code>.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with the value <code>null</code>.
   */
  @Override
  public final boolean isNullProperty(String propertyName) {
    String propertyType = this.invokeFunction("getPropertyType", this.jsObject, propertyName);
    
    return !propertyType.equals("undefined") && this.invokeFunction("getProperty", this.jsObject, propertyName) == null;
  }
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * boolean value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a boolean value.
   */
  @Override
  public final boolean isBooleanProperty(String propertyName) {
    String propertyType = this.invokeFunction("getPropertyType", this.jsObject, propertyName);
    
    return propertyType.equals("boolean");
  }

  /**
   * Retrieve the boolean value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The boolean value of the property.
   * @see #setBooleanProperty(String, Boolean)
   */
  @Override
  public final Boolean getBooleanProperty(String propertyName) {
    return (Boolean)this.invokeFunction("getProperty", this.jsObject, propertyName);
  }
  
  /**
   * Set the property with the given name to the given boolean value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The boolean value of the property.
   * @see #getBooleanProperty(String)
   */
  @Override
  public final void setBooleanProperty(String propertyName, Boolean propertyValue) {
    this.invokeFunction("setProperty", this.jsObject, propertyName, propertyValue);
  }
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * numeric value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a numeric value.
   */
  @Override
  public final boolean isNumberProperty(String propertyName) {
    String propertyType = this.invokeFunction("getPropertyType", this.jsObject, propertyName);
    
    return propertyType.equals("number");
  }

  /**
   * Retrieve the numeric value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The numeric value of the property.
   * @see #setNumberProperty(String, Number)
   */
  @Override
  public final Number getNumberProperty(String propertyName) {
    return (Number)this.invokeFunction("getProperty", this.jsObject, propertyName);
  }
  
  /**
   * Set the property with the given name to the given numeric value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The numeric value of the property.
   * @see #getNumberProperty(String)
   */
  @Override
  public final void setNumberProperty(String propertyName, Number propertyValue) {
    this.invokeFunction("setProperty", this.jsObject, propertyName, propertyValue);
  }
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * string value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a string value.
   */
  @Override
  public final boolean isStringProperty(String propertyName) {
    String propertyType = this.invokeFunction("getPropertyType", this.jsObject, propertyName);
    
    return propertyType.equals("string");
  }
  
  /**
   * Retrieve the string value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The string value of the property.
   * @see #setStringProperty(String, String)
   */
  @Override
  public final String getStringProperty(String propertyName) {
    return this.invokeFunction("getProperty", this.jsObject, propertyName);
  }
  
  /**
   * Set the property with the given name to the given string value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The string value of the property.
   * @see #getStringProperty(String)
   */
  @Override
  public final void setStringProperty(String propertyName, String propertyValue) {
    this.invokeFunction("setProperty", this.jsObject, propertyName, propertyValue);
  }
  
  /**
   * Determine if the JSON object has a property with the given name and an
   * object value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with an object value.
   */
  @Override
  public final boolean isObjectProperty(String propertyName) {
    String propertyType = this.invokeFunction("getPropertyType", this.jsObject, propertyName);
    
    return propertyType.equals("object") && this.invokeFunction("getProperty", this.jsObject, propertyName) != null;
  }

  /**
   * Retrieve the object value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The object value of the property.
   * @see #setObjectProperty(String, JsonObject)
   */
  @Override
  public final JsonObject getObjectProperty(String propertyName) {
    return this.getObjectProperty(propertyName, JsonObject.class);
  }
  
  /**
   * Retrieve the object value of the property with the given name.
   *
   * @param <T> The type of the object value.
   * @param propertyName The name of the property.
   * @param jsonObjectClass The class of the object value.
   * @return The object value of the property.
   * @see #setObjectProperty(String, JsonObject)
   */
  @Override
  public final <T extends JsonObject> T getObjectProperty(String propertyName, Class<T> jsonObjectClass) {
    Object jsPropertyValue = this.invokeFunction("getProperty", this.jsObject, propertyName);
    
    return jsPropertyValue != null ? JsonObjectFactory.get().createJsonObject(jsonObjectClass, jsPropertyValue) : null;
  }
  
  /**
   * Set the property with the given name to the given object value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The object value of the property.
   * @see #getObjectProperty(String)
   * @see #getObjectProperty(String, Class)
   */
  @Override
  public final void setObjectProperty(String propertyName, JsonObject propertyValue) {
    Object jsPropertyValue = propertyValue != null ? propertyValue.getObject() : null;
    
    this.invokeFunction("setProperty", this.jsObject, propertyName, jsPropertyValue);
  }

  /**
   * Delete the property with the given name.
   * 
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object contained the property.
   */
  @Override
  public final boolean deleteProperty(String propertyName) {
    boolean result = this.hasProperty(propertyName);
    
    this.invokeFunction("deleteProperty", this.jsObject, propertyName);
    
    return result;
  }
  
  /**
   * Retrieve the underlying JSON object.
   *
   * @return The underlying JSON object.
   */
  @Override
  public final Object getObject() {
    return this.jsObject;
  }
  /**
   * Determine if this object is equal to the given object.
   *
   * @param object The object.
   * @return TRUE if this object is equal to the given object.
   */
  @Override
  public final boolean equals(Object object) {
    if (object == this) {
      return true;
    }
    else if (object instanceof JsJsonObjectImpl) {
      JsJsonObjectImpl that = (JsJsonObjectImpl)object;
      
      return this.jsObject.equals(that.jsObject);
    }
    else {
      return false;
    }
  }
  
  /**
   * Calculate the hash code for this object.
   *
   * @return The hash code for this object.
   */
  @Override
  public final int hashCode() {
    return this.jsObject.hashCode();
  }
  
  /**
   * Create a string representation of this object.
   *
   * @return The string representation of this object.
   */
  @Override
  public final String toString() {
    return this.jsObject.toString();
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
  protected final <T> T invokeFunction(String name, Object... args) {
    try {
      return (T)this.jsEngine.invokeFunction(name, args);
    }
    catch (ScriptException se) {
      throw new IllegalStateException(se);
    }
    catch (NoSuchMethodException nsme) {
      throw new IllegalStateException(nsme);
    }
  }
}
