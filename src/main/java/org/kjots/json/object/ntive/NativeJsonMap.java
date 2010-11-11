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
package org.kjots.json.object.ntive;

import java.util.HashMap;
import java.util.Set;

import org.kjots.json.object.shared.JsonObject;

/**
 * Native JSON Map.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonMap implements JsonObject {
  /** The map. */
  protected final HashMap<String, Object> map;
  
  /**
   * Construct a new Native JSON Map.
   */
  public NativeJsonMap() {
    this(new HashMap<String, Object>());
  }
  
  /**
   * Retrieve the JSON object class.
   *
   * @return The JSON object class.
   */
  @Override
  public Class<? extends NativeJsonMap> getJsonObjectClass() {
    return this.getClass();
  }
  
  /**
   * Cast this JSON object a JSON object with the given class.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @return The cast JSON object.
   */
  @Override
  public <T extends JsonObject> T cast(Class<T> jsonObjectClass) {
    return jsonObjectClass.cast(this);
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
    try {
      return this.cast((Class<T>)Class.forName(jsonObjectClassName));
    }
    catch (ClassNotFoundException cnfe) {
      throw new IllegalArgumentException(jsonObjectClassName, cnfe);
    }
  }
  
  /**
   * Determine if the JSON object is an array.
   *
   * @return <code>true</code> if the JSON object is an array.
   */
  @Override
  public boolean isArray() {
    return false;
  }
  
  /**
   * Retrieve the names of the properties of the JSON object.
   *
   * @return The name of the properties of the JSON object.
   */
  @Override
  public Set<String> getPropertyNames() {
    return this.map.keySet();
  }
  
  /**
   * Determine if the JSON object has a property with the given name.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property.
   */
  @Override
  public boolean hasProperty(String propertyName) {
    return this.map.containsKey(propertyName);
  }
  
  /**
   * Determine if the JSON object has a property with the given name and the
   * value <code>null</code>.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with the value <code>null</code>.
   */
  @Override
  public boolean isNullProperty(String propertyName) {
    return this.map.containsKey(propertyName) && this.map.get(propertyName) == null;
  }
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * boolean value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a boolean value.
   */
  @Override
  public boolean isBooleanProperty(String propertyName) {
    return this.map.get(propertyName) instanceof Boolean;
  }

  /**
   * Retrieve the boolean value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The boolean value of the property.
   * @see #setBooleanProperty(String, Boolean)
   */
  @Override
  public Boolean getBooleanProperty(String propertyName) {
    return (Boolean)this.map.get(propertyName);
  }
  
  /**
   * Set the property with the given name to the given boolean value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The boolean value of the property.
   * @see #getBooleanProperty(String)
   */
  @Override
  public void setBooleanProperty(String propertyName, Boolean propertyValue) {
    this.map.put(propertyName, propertyValue);
  }
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * numeric value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a numeric value.
   */
  @Override
  public boolean isNumberProperty(String propertyName) {
    return this.map.get(propertyName) instanceof Number;
  }

  /**
   * Retrieve the numeric value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The numeric value of the property.
   * @see #setNumberProperty(String, Number)
   */
  @Override
  public Number getNumberProperty(String propertyName) {
    return (Number)this.map.get(propertyName);
  }
  
  /**
   * Set the property with the given name to the given numeric value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The numeric value of the property.
   * @see #getNumberProperty(String)
   */
  @Override
  public void setNumberProperty(String propertyName, Number propertyValue) {
    this.map.put(propertyName, propertyValue);
  }
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * string value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a string value.
   */
  @Override
  public boolean isStringProperty(String propertyName) {
    return this.map.get(propertyName) instanceof String;
  }
  
  /**
   * Retrieve the string value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The string value of the property.
   * @see #setStringProperty(String, String)
   */
  @Override
  public String getStringProperty(String propertyName) {
    return (String)this.map.get(propertyName);
  }
  
  /**
   * Set the property with the given name to the given string value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The string value of the property.
   * @see #getStringProperty(String)
   */
  @Override
  public void setStringProperty(String propertyName, String propertyValue) {
    this.map.put(propertyName, propertyValue);
  }
  
  /**
   * Determine if the JSON object has a property with the given name and an
   * object value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with an object value.
   */
  @Override
  public boolean isObjectProperty(String propertyName) {
    return this.map.get(propertyName) instanceof JsonObject;
  }

  /**
   * Retrieve the object value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The object value of the property.
   * @see #setObjectProperty(String, JsonObject)
   */
  @Override
  public JsonObject getObjectProperty(String propertyName) {
    return (JsonObject)this.map.get(propertyName);
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
  public <T extends JsonObject> T getObjectProperty(String propertyName, Class<T> jsonObjectClass) {
    JsonObject propertyValue = (JsonObject)this.map.get(propertyName);
    
    return propertyValue != null ? propertyValue.cast(jsonObjectClass) : null;
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
  public void setObjectProperty(String propertyName, JsonObject propertyValue) {
    this.map.put(propertyName, propertyValue);
  }

  /**
   * Delete the property with the given name.
   * 
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object contained the property.
   */
  @Override
  public boolean deleteProperty(String propertyName) {
    boolean hasPropertyValue = this.map.containsKey(propertyName);
    
    this.map.remove(propertyName);
    
    return hasPropertyValue;
  }
  
  /**
   * Retrieve the underlying JSON object.
   *
   * @return The underlying JSON object.
   */
  @Override
  public Object getObject() {
    return this;
  }
  
  /**
   * Construct a new Native JSON Map.
   *
   * @param map The map.
   */
  protected NativeJsonMap(HashMap<String, Object> map) {
    this.map = map;
  }
}
