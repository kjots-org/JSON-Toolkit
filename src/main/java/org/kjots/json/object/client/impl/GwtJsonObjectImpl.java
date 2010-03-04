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
package org.kjots.json.object.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.shared.JsonStringArray;

/**
 * GWT JSON Object Implementation.
 * <p>
 * This class is the base class for all generated {@link JsonObject}
 * implementations.
 * <p>
 * Created: 6th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonObjectImpl implements JsonObject {
  /** The JavaScript object. */
  protected final JavaScriptObject jsObject;
  
  /**
   * Construct a new GWT JSON Object Implementation.
   *
   * @param jsObject The JavaScript object.
   */
  public GwtJsonObjectImpl(JavaScriptObject jsObject) {
    this.jsObject = jsObject;
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
    if (jsonObjectClass.equals(this.getClass())) {
      return (T)this;
    }
    
    return JsonObjectFactory.get().createJsonObject(jsonObjectClass, this.jsObject);
  }
  
  /**
   * Determine if the JSON object is an array.
   *
   * @return <code>true</code> if the JSON object is an array.
   */
  @Override
  public final native boolean isArray() /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject instanceof Array || 
           this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject instanceof $wnd.Array;
   }-*/;
  
  /**
   * Retrieve the names of the properties of the JSON object.
   *
   * @return The name of the properties of the JSON object.
   */
  @Override
  public final JsonStringArray getPropertyNames() {
    return new GwtJsonStringArrayImpl(this.getJsPropertyNames());
  };
  
  /**
   * Determine if the JSON object has a property with the given name.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property.
   */
  @Override
  public final native boolean hasProperty(String propertyName) /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName] !== undefined;
  }-*/;
  
  /**
   * Determine if the JSON object has a property with the given name and the
   * value <code>null</code>.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with the value <code>null</code>.
   */
  @Override
  public final native boolean isNullProperty(String propertyName) /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName] === null;
  }-*/;
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * boolean value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a boolean value.
   */
  @Override
  public final native boolean isBooleanProperty(String propertyName) /*-{
    return typeof this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName] == 'boolean';
  }-*/;

  /**
   * Retrieve the boolean value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The boolean value of the property.
   * @see #setBooleanProperty(String, boolean)
   */
  @Override
  public final native boolean getBooleanProperty(String propertyName) /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName];
  }-*/;
  
  /**
   * Set the property with the given name to the given boolean value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The boolean value of the property.
   * @see #getBooleanProperty(String)
   */
  @Override
  public final native void setBooleanProperty(String propertyName, boolean propertyValue) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName] = propertyValue;
  }-*/;
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * numeric value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a numeric value.
   */
  @Override
  public final native boolean isNumberProperty(String propertyName) /*-{
    return typeof this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName] == 'number';
  }-*/;

  /**
   * Retrieve the numeric value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The numeric value of the property.
   * @see #setNumberProperty(String, Number)
   */
  @Override
  public final native Number getNumberProperty(String propertyName) /*-{
    var jsPropertyValue = this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName];
    
    return jsPropertyValue != null ? @java.lang.Double::valueOf(D)(jsPropertyValue) : null;
  }-*/;
  
  /**
   * Set the property with the given name to the given numeric value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The numeric value of the property.
   * @see #getNumberProperty(String)
   */
  @Override
  public final native void setNumberProperty(String propertyName, Number propertyValue) /*-{
    var jsPropertyValue = propertyValue != null ? propertyValue.@java.lang.Number::doubleValue()() : null;
    
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName] = jsPropertyValue;
  }-*/;
  
  /**
   * Determine if the JSON object has a property with the given name and a
   * string value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with a string value.
   */
  @Override
  public final native boolean isStringProperty(String propertyName) /*-{
    return typeof this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName] == 'string';
  }-*/;
  
  /**
   * Retrieve the string value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The string value of the property.
   */
  @Override
  public final native String getStringProperty(String propertyName) /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName];
  }-*/;
  
  /**
   * Set the property with the given name to the given string value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The string value of the property.
   * @see #getStringProperty(String)
   */
  @Override
  public final native void setStringProperty(String propertyName, String propertyValue) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName] = propertyValue;
  }-*/;
  
  /**
   * Determine if the JSON object has a property with the given name and an
   * object value.
   *
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object has the property with an object value.
   */
  @Override
  public final native boolean isObjectProperty(String propertyName) /*-{
    var propertyValue = this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName];
    
    return propertyValue != null && typeof propertyValue == 'object';
  }-*/;

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
  public <T extends JsonObject> T getObjectProperty(String propertyName, Class<T> jsonObjectClass) {
    JavaScriptObject jsObjectPropertyValue = this.getJsObjectProperty(propertyName);
    
    return jsObjectPropertyValue != null ? JsonObjectFactory.get().createJsonObject(jsonObjectClass, jsObjectPropertyValue) : null;
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
    if (propertyValue != null) {
      this.setJsObjectProperty(propertyName, (JavaScriptObject)propertyValue.getObject());
    }
    else {
      this.setJsObjectProperty(propertyName, null);
    }
  }
  
  /**
   * Delete the property with the given name.
   * 
   * @param propertyName The name of the property.
   * @return <code>true</code> if the JSON object contained the property.
   */
  @Override
  public final native boolean deleteProperty(String propertyName) /*-{
    var result = this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName] !== undefined;
    
    delete this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName];
    
    return result;
  }-*/;

  /**
   * Retrieve the underlying JSON object.
   *
   * @return The underlying JSON object.
   */
  public final JavaScriptObject getObject() {
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
    else if (object instanceof GwtJsonObjectImpl) {
      GwtJsonObjectImpl that = (GwtJsonObjectImpl)object;
      
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
   * Retrieve the names of the properties of the JSON object.
   *
   * @return The name of the properties of the JSON object.
   */
  private native JsArrayString getJsPropertyNames() /*-{
    var propertyNames = [];
    
    for (var propertyName in this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject) {
      propertyNames.push(propertyName);
    }
    
    return propertyNames;
  }-*/;

  /**
   * Retrieve the object value of the property with the given name.
   *
   * @param propertyName The name of the property.
   * @return The object value of the property.
   * @see #setJsObjectProperty(String, JavaScriptObject)
   */
  private native JavaScriptObject getJsObjectProperty(String propertyName) /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName];
  }-*/;
  
  /**
   * Set the property with the given name to the given object value.
   * 
   * @param propertyName The name of the property.
   * @param propertyValue The object value of the property.
   * @see #getJsObjectProperty(String)
   */
  private native void setJsObjectProperty(String propertyName, JavaScriptObject propertyValue) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[propertyName] = propertyValue;
  }-*/;
}
