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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.kjots.json.object.shared.JsonBooleanPropertyAdapter;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonProperty;
import org.kjots.json.object.shared.JsonPropertyAdapter;
import org.kjots.json.object.shared.JsonStringPropertyAdapter;

/**
 * Native JSON Object.
 * <p>
 * Created: 12th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public abstract class NativeJsonObject implements JsonObject {
  /**
   * Native JSON Property Info.
   */
  private class NativeJsonPropertyInfo {
    /** The field. */
    private final Field field;
    
    /** The adapter. */
    private final JsonPropertyAdapter<Object, Object> adapter;
    
    /** The has value flag. */
    private boolean hasValue;
    
    /**
     * Construct a new Native JSON Property Info.
     *
     * @param field The field.
     */
    public NativeJsonPropertyInfo(Field field, NativeJsonProperty nativeJsonPropertyAnnotation) {
      this.field = field;
      this.adapter = this.newJsonPropertyAdapter(nativeJsonPropertyAnnotation.adapter());
    }
    
    /**
     * Retrieve the field.
     *
     * @return The field.
     */
    public Field getField() {
      return this.field;
    }

    /**
     * Retrieve the adapter.
     *
     * @return The adapter.
     */
    public JsonPropertyAdapter<Object, Object> getAdapter() {
      return this.adapter;
    }

    /**
     * Retrieve the has value flag.
     *
     * @return The has value flag.
     * @see #setHasValue(boolean)
     */
    public boolean getHasValue() {
      return this.hasValue;
    }

    /**
     * Set the has value flag.
     *
     * @param hasValue The has value flag.
     * @see #getHasValue()
     */
    public void setHasValue(boolean hasValue) {
      this.hasValue = hasValue;
    }

    /**
     * Retrieve the value of the property.
     *
     * @return The value of the property.
     * @see #setValue(Object)
     */
    public Object getValue() {
      Object value = this.getRawValue();
      
      return this.adapter != null ? this.adapter.toJsonProperty(value) : value;
    }
    
    /**
     * Set the value of the property.
     *
     * @param propertyValue The value of the property.
     * @see #getValue()
     */
    public void setValue(Object propertyValue) {
      Object value = this.adapter != null ? this.adapter.fromJsonProperty(propertyValue) : propertyValue;
      
      this.field.setAccessible(true);
      try {
        this.field.set(NativeJsonObject.this, value);
      }
      catch (IllegalAccessException iae) {
        throw new IllegalStateException(iae);
      }
      finally {
        this.field.setAccessible(false);
      }
      
      this.hasValue = true;
    }
    
    /**
     * Remove the value of the property.
     */
    public void removeValue() {
      this.field.setAccessible(true);
      try {
        this.field.set(NativeJsonObject.this, null);
      }
      catch (IllegalAccessException iae) {
        throw new IllegalStateException(iae);
      }
      finally {
        this.field.setAccessible(false);
      }
      
      this.hasValue = false;
    }
    
    /**
     * Retrieve the raw value of the property.
     *
     * @return The raw value of the property.
     */
    public Object getRawValue() {
      this.field.setAccessible(true);
      try {
        return this.field.get(NativeJsonObject.this);
      }
      catch (IllegalAccessException iae) {
        throw new IllegalStateException(iae);
      }
      finally {
        this.field.setAccessible(false);
      }
    }
    
    /**
     * Create a new JSON property adapter with the given class.
     *
     * @param adapterClass The class of the JSON property adapter.
     * @return The JSON property adapter.
     */
    @SuppressWarnings("unchecked")
    private JsonPropertyAdapter<Object, Object> newJsonPropertyAdapter(Class<? extends JsonPropertyAdapter<?, ?>> adapterClass) {
      if (adapterClass.equals(JsonProperty.NullAdapter.class)) {
        return null;
      }
      
      try {
        return (JsonPropertyAdapter<Object, Object>)adapterClass.newInstance();
      }
      catch (IllegalAccessException iae) {
        throw new IllegalStateException(iae);
      }
      catch (InstantiationException ie) {
        throw new IllegalStateException(ie);
      }
    }
  }
  
  /** The native JSON properties information. */
  private final Map<String, NativeJsonPropertyInfo> nativeJsonPropertiesInfo;
  
  /**
   * Retrieve the JSON object class.
   *
   * @return The JSON object class.
   */
  @Override
  public Class<? extends NativeJsonObject> getJsonObjectClass() {
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
    Set<String> propertyNames = new HashSet<String>();
    
    for (Map.Entry<String, NativeJsonPropertyInfo> entry : this.nativeJsonPropertiesInfo.entrySet()) { 
      if (entry.getValue().getHasValue()) { 
        propertyNames.add(entry.getKey());
      }
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
  public boolean hasProperty(String propertyName) {
    return this.getNativeJsonPropertyInfo(propertyName).getHasValue();
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
    NativeJsonPropertyInfo nativeJsonPropertyInfo = this.getNativeJsonPropertyInfo(propertyName);
    
    return nativeJsonPropertyInfo.getHasValue() && nativeJsonPropertyInfo.getValue() == null;
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
    NativeJsonPropertyInfo nativeJsonPropertyInfo = this.getNativeJsonPropertyInfo(propertyName);
    
    JsonPropertyAdapter<?, ?> adapter = nativeJsonPropertyInfo.getAdapter();
    if (adapter != null) {
      return adapter instanceof JsonBooleanPropertyAdapter<?> && nativeJsonPropertyInfo.getRawValue() != null;
    }
    
    return nativeJsonPropertyInfo.getHasValue() && nativeJsonPropertyInfo.getValue() instanceof Boolean;
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
    return (Boolean)this.getNativeJsonPropertyInfo(propertyName).getValue();
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
    this.getNativeJsonPropertyInfo(propertyName).setValue(propertyValue);
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
    NativeJsonPropertyInfo nativeJsonPropertyInfo = this.getNativeJsonPropertyInfo(propertyName);
    
    return nativeJsonPropertyInfo.getHasValue() && nativeJsonPropertyInfo.getValue() instanceof Number;
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
    return (Number)this.getNativeJsonPropertyInfo(propertyName).getValue();
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
    this.getNativeJsonPropertyInfo(propertyName).setValue(propertyValue);
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
    NativeJsonPropertyInfo nativeJsonPropertyInfo = this.getNativeJsonPropertyInfo(propertyName);
    
    JsonPropertyAdapter<?, ?> adapter = nativeJsonPropertyInfo.getAdapter();
    if (adapter != null) {
      return adapter instanceof JsonStringPropertyAdapter<?> && nativeJsonPropertyInfo.getRawValue() != null;
    }
    
    Class<?> fieldType = nativeJsonPropertyInfo.getField().getType();
    if (fieldType.equals(char.class) || fieldType.equals(Character.class)) {
      return nativeJsonPropertyInfo.getHasValue() && nativeJsonPropertyInfo.getValue() instanceof Character;
    }
    else {
      return nativeJsonPropertyInfo.getValue() instanceof String;
    }
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
    NativeJsonPropertyInfo nativeJsonPropertyInfo = this.getNativeJsonPropertyInfo(propertyName);
    
    Class<?> fieldType = nativeJsonPropertyInfo.getField().getType();
    if (fieldType.equals(char.class) || fieldType.equals(Character.class)) {
      Character propertyValue = (Character)nativeJsonPropertyInfo.getValue();
      
      return propertyValue != null ? propertyValue.toString() : null;
    }
    
    return (String)nativeJsonPropertyInfo.getValue();
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
    NativeJsonPropertyInfo nativeJsonPropertyInfo = this.getNativeJsonPropertyInfo(propertyName);
    
    Class<?> fieldType = nativeJsonPropertyInfo.getField().getType();
    if (fieldType.equals(char.class) || fieldType.equals(Character.class)) {
      nativeJsonPropertyInfo.setValue(Character.valueOf(propertyValue.charAt(0)));
      
      return;
    }
    
    nativeJsonPropertyInfo.setValue(propertyValue);
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
    return this.getNativeJsonPropertyInfo(propertyName).getValue() instanceof JsonObject;
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
    return (JsonObject)this.getNativeJsonPropertyInfo(propertyName).getValue();
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
    JsonObject propertyValue = (JsonObject)this.getNativeJsonPropertyInfo(propertyName).getValue();
    
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
    this.getNativeJsonPropertyInfo(propertyName).setValue(propertyValue);
  }

  /**
   * Delete the property with the given name.
   * 
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object contained the property.
   */
  @Override
  public boolean deleteProperty(String propertyName) {
    NativeJsonPropertyInfo nativeJsonPropertyInfo = this.getNativeJsonPropertyInfo(propertyName);
    boolean hasPropertyValue = nativeJsonPropertyInfo.getHasValue();
    
    nativeJsonPropertyInfo.removeValue();
    
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
   * Construct a new Native JSON Object.
   */
  protected NativeJsonObject() {
    this.nativeJsonPropertiesInfo = this.getNativeJsonPropertiesInfo();
  }
  
  /**
   * Set the has value flag for the native JSON property with the given name.
   *
   * @param propertyName The name of the property.
   */
  protected void setHasProperty(String propertyName) {
    this.getNativeJsonPropertyInfo(propertyName).setHasValue(true);
  }
  
  /**
   * Retrieve the native JSON properties information.
   */
  private Map<String, NativeJsonPropertyInfo> getNativeJsonPropertiesInfo() {
    Map<String, NativeJsonPropertyInfo> nativeJsonPropertiesInfo = new HashMap<String, NativeJsonPropertyInfo>();
    
    Class<?> klass = this.getClass();
    while (!klass.equals(NativeJsonObject.class)) {
      for (Field field : klass.getDeclaredFields()) {
        NativeJsonProperty nativeJsonPropertyAnnotation = field.getAnnotation(NativeJsonProperty.class);
        if (nativeJsonPropertyAnnotation != null) {
          String propertyName = field.getName();
          if (nativeJsonPropertiesInfo.containsKey(propertyName)) {
            throw new IllegalStateException("Duplicate native JSON property: " + propertyName);
          }
          
          nativeJsonPropertiesInfo.put(propertyName, new NativeJsonPropertyInfo(field, nativeJsonPropertyAnnotation));
        }
      }
      
      klass = klass.getSuperclass();
    }
    
    return nativeJsonPropertiesInfo;
  }
  
  /**
   * Retrieve the information for the native JSON property with the given name.
   * <p>
   * This method will throw an {@link IllegalArgumentException} if there is no
   * native JSON property with the given name.
   * 
   * @param propertyName The name of the property.
   * @return The native JSON property information.
   */
  private NativeJsonPropertyInfo getNativeJsonPropertyInfo(String propertyName) {
    NativeJsonPropertyInfo nativeJsonPropertyInfo = this.nativeJsonPropertiesInfo.get(propertyName);
    if (nativeJsonPropertyInfo == null) {
      throw new IllegalArgumentException("No such property: " + propertyName);
    }
    
    return nativeJsonPropertyInfo;
  }
}
