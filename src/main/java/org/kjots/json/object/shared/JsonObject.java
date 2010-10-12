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

import java.util.Set;

/**
 * JSON Object.
 * <p>
 * Created: 6th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public interface JsonObject {
  /**
   * Retrieve the JSON object class.
   *
   * @return The JSON object class.
   */
  public Class<? extends JsonObject> getJsonObjectClass();
  
  /**
   * Cast this JSON object a JSON object with the given class.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @return The cast JSON object.
   */
  public <T extends JsonObject> T cast(Class<T> jsonObjectClass);
  
  /**
   * Cast this JSON object a JSON object with the given class name.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClassName The name of the class of the JSON object.
   * @return The cast JSON object.
   */
  public <T extends JsonObject> T cast(String jsonObjectClassName);
  
  /**
   * Determine if the JSON object is an array.
   *
   * @return <code>true</code> if the JSON object is an array.
   */
  public boolean isArray();
  
  /**
   * Retrieve the names of the properties of the JSON object.
   *
   * @return The name of the properties of the JSON object.
   */
  public Set<String> getPropertyNames();
  
  /**
   * Determine if the JSON object has a property with the given name.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property.
   */
  public boolean hasProperty(String propertyName);
  
  /**
   * Determine if the JSON object has a property with the given name and the
   * value <code>null</code>.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with the value <code>null</code>.
   */
  public boolean isNullProperty(String propertyName);
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * boolean value.
   * <p>
   * This method will return <code>false</code> if there is no property with the
   * given name or the value of the property is <code>null</code>.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a boolean value.
   */
  public boolean isBooleanProperty(String propertyName);

  /**
   * Retrieve the boolean value of the property with the given name.
   * <p>
   * This method will return <code>null</code> if there is no property with the
   * given name.
   *
   * @param propertyName The name of the property.
   * @return The boolean value of the property.
   * @see #setBooleanProperty(String, Boolean)
   */
  public Boolean getBooleanProperty(String propertyName);
  
  /**
   * Set the property with the given name to the given boolean value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The boolean value of the property.
   * @see #getBooleanProperty(String)
   */
  public void setBooleanProperty(String propertyName, Boolean propertyValue);
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * numeric value.
   * <p>
   * This method will return <code>false</code> if there is no property with the
   * given name or the value of the property is <code>null</code>.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a numeric value.
   */
  public boolean isNumberProperty(String propertyName);

  /**
   * Retrieve the numeric value of the property with the given name.
   * <p>
   * This method will return <code>null</code> if there is no property with the
   * given name.
   *
   * @param propertyName The name of the property.
   * @return The numeric value of the property.
   * @see #setNumberProperty(String, Number)
   */
  public Number getNumberProperty(String propertyName);
  
  /**
   * Set the property with the given name to the given numeric value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The numeric value of the property.
   * @see #getNumberProperty(String)
   */
  public void setNumberProperty(String propertyName, Number propertyValue);
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * string value.
   * <p>
   * This method will return <code>false</code> if there is no property with the
   * given name or the value of the property is <code>null</code>.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a string value.
   */
  public boolean isStringProperty(String propertyName);
  
  /**
   * Retrieve the string value of the property with the given name.
   * <p>
   * This method will return <code>null</code> if there is no property with the
   * given name.
   *
   * @param propertyName The name of the property.
   * @return The string value of the property.
   * @see #setStringProperty(String, String)
   */
  public String getStringProperty(String propertyName);
  
  /**
   * Set the property with the given name to the given string value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The string value of the property.
   * @see #getStringProperty(String)
   */
  public void setStringProperty(String propertyName, String propertyValue);
  
  /**
   * Determine if the JSON object has a property with the given name and an
   * object value.
   * <p>
   * This method will return <code>false</code> if there is no property with the
   * given name or the value of the property is <code>null</code>.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with an object value.
   */
  public boolean isObjectProperty(String propertyName);

  /**
   * Retrieve the object value of the property with the given name.
   * <p>
   * This method will return <code>null</code> if there is no property with the
   * given name.
   *
   * @param propertyName The name of the property.
   * @return The object value of the property.
   * @see #setObjectProperty(String, JsonObject)
   */
  public JsonObject getObjectProperty(String propertyName);
  
  /**
   * Retrieve the object value of the property with the given name.
   * <p>
   * This method will return the object value as a instance of the given class.
   * <p>
   * This method will return <code>null</code> if there is no property with the
   * given name.
   *
   * @param <T> The type of the object value.
   * @param propertyName The name of the property.
   * @param jsonObjectClass The class of the object value.
   * @return The object value of the property.
   * @see #setObjectProperty(String, JsonObject)
   */
  public <T extends JsonObject> T getObjectProperty(String propertyName, Class<T> jsonObjectClass);
  
  /**
   * Set the property with the given name to the given object value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The object value of the property.
   * @see #getObjectProperty(String)
   * @see #getObjectProperty(String, Class)
   */
  public void setObjectProperty(String propertyName, JsonObject propertyValue);

  /**
   * Delete the property with the given name.
   * <p>
   * Following the completion of this method, the JSON object will not have a
   * property with the given name (i.e. <code>hasProperty(propertyName)</code>
   * will return <code>false</code>).
   * <p>
   * This method will return <code>true</code> if the JSON object contained a
   * property with the given name prior to invocation.
   * 
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object contained the property.
   */
  public boolean deleteProperty(String propertyName);
  
  /**
   * Retrieve the underlying JSON object.
   *
   * @return The underlying JSON object.
   */
  public Object getObject();
}
