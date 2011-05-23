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

import java.util.Set;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.simple.SimpleJsonValue;

/**
 * Simple JSON Object Implementation.
 * <p>
 * Created: 4th March 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class SimpleJsonObjectImpl implements JsonObject {
  /** The JSON object class. */
  protected final Class<? extends JsonObject> jsonObjectClass;
  
  /** The simple JSON value. */
  protected final SimpleJsonValue simpleJsonValue;
  
  /**
   * Construct a new Simple JSON Object Implementation.
   *
   * @param jsonObjectClass The JSON object class.
   * @param simpleJsonValue The simple JSON value.
   */
  public SimpleJsonObjectImpl(Class<? extends JsonObject> jsonObjectClass, SimpleJsonValue simpleJsonValue) {
    this.jsonObjectClass = jsonObjectClass;
    this.simpleJsonValue = simpleJsonValue;
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
    
    return JsonObjectFactory.get().createJsonObject(jsonObjectClass, this.simpleJsonValue);
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
    
    return JsonObjectFactory.get().<T>createJsonObject(jsonObjectClassName, this.simpleJsonValue);
  }
  
  /**
   * Determine if the JSON object is an array.
   *
   * @return <code>true</code> if the JSON object is an array.
   */
  @Override
  public final boolean isArray() {
    return this.simpleJsonValue.getList() != null;
  }
  
  /**
   * Retrieve the names of the properties of the JSON object.
   *
   * @return The name of the properties of the JSON object.
   */
  @Override
  public final Set<String> getPropertyNames() {
    return this.simpleJsonValue.getMap().keySet();
  }
  
  /**
   * Determine if the JSON object has a property with the given name.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property.
   */
  @Override
  public final boolean hasProperty(String propertyName) {
    return this.simpleJsonValue.getMap().containsKey(propertyName);
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
    return this.simpleJsonValue.getMap().containsKey(propertyName) &&
           this.simpleJsonValue.getMap().get(propertyName) == null;
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
    return this.simpleJsonValue.getMap().get(propertyName) instanceof Boolean;
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
    return (Boolean)this.simpleJsonValue.getMap().get(propertyName);
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
    this.simpleJsonValue.getMap().put(propertyName, propertyValue);
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
    return this.simpleJsonValue.getMap().get(propertyName) instanceof Number;
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
    return (Number)this.simpleJsonValue.getMap().get(propertyName);
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
    this.simpleJsonValue.getMap().put(propertyName, propertyValue);
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
    return this.simpleJsonValue.getMap().get(propertyName) instanceof String;
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
    return (String)this.simpleJsonValue.getMap().get(propertyName);
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
    this.simpleJsonValue.getMap().put(propertyName, propertyValue);
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
    return this.simpleJsonValue.getMap().get(propertyName) instanceof SimpleJsonValue;
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
    SimpleJsonValue propertyValue = (SimpleJsonValue)this.simpleJsonValue.getMap().get(propertyName);
    
    return propertyValue != null ? JsonObjectFactory.get().createJsonObject(jsonObjectClass, propertyValue) : null;
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
    SimpleJsonValue simplePropertyValue = propertyValue != null ? (SimpleJsonValue)propertyValue.getObject() : null;
    
    this.simpleJsonValue.getMap().put(propertyName, simplePropertyValue);
  }

  /**
   * Delete the property with the given name.
   * 
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object contained the property.
   */
  @Override
  public final boolean deleteProperty(String propertyName) {
    boolean result = this.simpleJsonValue.getMap().containsKey(propertyName);
    
    this.simpleJsonValue.getMap().remove(propertyName);
    
    return result;
  }
  
  /**
   * Retrieve the underlying JSON object.
   *
   * @return The underlying JSON object.
   */
  @Override
  public final Object getObject() {
    return this.simpleJsonValue;
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
    else if (object instanceof SimpleJsonObjectImpl) {
      SimpleJsonObjectImpl that = (SimpleJsonObjectImpl)object;
      
      return this.simpleJsonValue.equals(that.simpleJsonValue);
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
    return this.simpleJsonValue.hashCode();
  }
  
  /**
   * Create a string representation of this object.
   *
   * @return The string representation of this object.
   */
  @Override
  public final String toString() {
    return this.simpleJsonValue.toString();
  }
}
