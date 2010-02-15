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

import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;

/**
 * GWT JSON Array Implementation.
 * <p>
 * Created: 7th November 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonArrayImpl extends GwtJsonObjectImpl implements JsonArray {
  /**
   * Construct a new GWT JSON Array Implementation.
   *
   * @param jsArray The JavaScript array.
   */
  public GwtJsonArrayImpl(JavaScriptObject jsArray) {
    super(jsArray);
    
    if (!this.isArray()) {
      throw new IllegalArgumentException("jsArray is not a JavaScript array");
    }
  }
  
  /**
   * Retrieve the length of the array.
   *
   * @return The length of the array.
   */
  @Override
  public final native int getLength() /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject.length;
  }-*/;
  
  /**
   * Determine if the element at the given index is <code>null</code>.
   *
   * @param index The index.
   * @return <code>true</code> if the element is <code>null</code>.
   */
  @Override
  public final native boolean isNullElement(int index) /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index] === null;
  }-*/;
  
  /**
   * Determine if the element at the given index has a boolean value.
   *
   * @param index The index.
   * @return <code>true</code> if element has a boolean value.
   */
  @Override
  public final native boolean isBooleanElement(int index) /*-{
    return typeof this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index] == 'boolean';
  }-*/;

  /**
   * Retrieve the boolean value of the element at the given index.
   *
   * @param index The index.
   * @return The boolean value.
   * @see #setBooleanElement(int, boolean)
   */
  @Override
  public final native boolean getBooleanElement(int index) /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index];
  }-*/;
  
  /**
   * Set the element at the given index to the given boolean value.
   *
   * @param index The index.
   * @param value The boolean value.
   * @see #getBooleanElement(int)
   */
  @Override
  public final native void setBooleanElement(int index, boolean value) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index] = value;
  }-*/;
  
  /**
   * Insert the given boolean value at the given index.
   *
   * @param index The index.
   * @param value The boolean value.
   */
  @Override
  public final native void insertBooleanElement(int index, boolean value) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject.splice(index, 0, value);
  }-*/;
  
  /**
   * Determine if the element at the given index has a numeric value.
   *
   * @param index The index.
   * @return <code>true</code> if element has a numeric value.
   */
  @Override
  public final native boolean isNumberElement(int index) /*-{
    return typeof this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index] == 'number';
  }-*/;

  /**
   * Retrieve the numeric value of the element at the given index.
   *
   * @param index The index.
   * @return The numeric value.
   * @see #setNumberElement(int, double)
   */
  @Override
  public final native double getNumberElement(int index) /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index];
  }-*/;
  
  /**
   * Set the element at the given index to the given numeric value.
   *
   * @param index The index.
   * @param value The numeric value.
   * @see #getNumberElement(int)
   */
  @Override
  public final native void setNumberElement(int index, double value) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index] = value;
  }-*/;
  
  /**
   * Insert the given number value at the given index.
   *
   * @param index The index.
   * @param value The number value.
   */
  @Override
  public final native void insertNumberElement(int index, double value) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject.splice(index, 0, value);
  }-*/;

  /**
   * Determine if the element at the given index has an integer value.
   *
   * @param index The index.
   * @return <code>true</code> if element has an integer value.
   */
  @Override
  public final native boolean isIntegerElement(int index) /*-{
    var value = this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index];
    
    return typeof value == 'number' && Math.floor(value) == value;
  }-*/;

  /**
   * Retrieve the integer value of the element at the given index.
   *
   * @param index The index.
   * @return The integer value.
   * @see #setIntegerElement(int, int)
   */
  @Override
  public final native int getIntegerElement(int index) /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index];
  }-*/;
  
  /**
   * Set the element at the given index to the given integer value.
   *
   * @param index The index.
   * @param value The integer value.
   * @see #getIntegerElement(int)
   */
  @Override
  public final native void setIntegerElement(int index, int value) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index] = value;
  }-*/;
  
  /**
   * Insert the given integer value at the given index.
   *
   * @param index The index.
   * @param value The integer value.
   */
  @Override
  public final native void insertIntegerElement(int index, int value) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject.splice(index, 0, value);
  }-*/;

  /**
   * Determine if the element at the given index has a string value.
   *
   * @param index The index.
   * @return <code>true</code> if element has a string value.
   */
  @Override
  public final native boolean isStringElement(int index) /*-{
    return typeof this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index] == 'string';
  }-*/;

  /**
   * Retrieve the string value of the element at the given index.
   *
   * @param index The index.
   * @return The string value.
   * @see #setStringElement(int, String)
   */
  @Override
  public final native String getStringElement(int index) /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index];
  }-*/;
  
  /**
   * Set the element at the given index to the given string value.
   *
   * @param index The index.
   * @param value The string value.
   * @see #getStringElement(int)
   */
  @Override
  public final native void setStringElement(int index, String value) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index] = value;
  }-*/;
  
  /**
   * Insert the given string value at the given index.
   *
   * @param index The index.
   * @param value The string value.
   */
  @Override
  public final native void insertStringElement(int index, String value) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject.splice(index, 0, value);
  }-*/;

  /**
   * Determine if the element at the given index has an object value.
   *
   * @param index The index.
   * @return <code>true</code> if element has an object value.
   */
  @Override
  public final native boolean isObjectElement(int index) /*-{
    var value = this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index];
    
    return value != null && typeof value == 'object';
  }-*/;

  /**
   * Retrieve the object value of the element at the given index.
   *
   * @param index The index.
   * @return The object value.
   * @see #setObjectElement(int, JsonObject)
   */
  @Override
  public final JsonObject getObjectElement(int index) {
    return this.getObjectElement(index, JsonObject.class);
  }
  
  /**
   * Retrieve the object value of the element at the given index.
   *
   * @param <T> The type of the object value.
   * @param index The index.
   * @param jsonObjectClass The class of the object value.
   * @return The object value.
   * @see #setObjectElement(int, JsonObject)
   */
  public final <T extends JsonObject> T getObjectElement(int index, Class<T> jsonObjectClass) {
    JavaScriptObject jsObjectPropertyValue = this.getJsObjectElement(index);
    
    return jsObjectPropertyValue != null ? JsonObjectFactory.get().createJsonObject(jsonObjectClass, jsObjectPropertyValue) : null;
  }
  
  /**
   * Set the element at the given index to the given object value.
   *
   * @param index The index.
   * @param value The object value.
   * @see #getObjectElement(int)
   */
  @Override
  public final void setObjectElement(int index, JsonObject value) {
    if (value != null) {
      this.setJsObjectElement(index, (JavaScriptObject)value.getObject());
    }
    else {
      this.setJsObjectElement(index, null);
    }
  }

  /**
   * Insert the given object value at the given index.
   *
   * @param index The index.
   * @param value The object value.
   */
  @Override
  public final void insertObjectElement(int index, JsonObject value) {
    if (value != null) {
      this.insertJsObjectElement(index, (JavaScriptObject)value.getObject());
    }
    else {
      this.insertJsObjectElement(index, null);
    }
  }
  
  /**
   * Remove the given number of elements at the given index.
   *
   * @param index The index.
   * @param count The number of elements.
   */
  @Override
  public final native void removeElements(int index, int count) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject.splice(index, count);
  }-*/;
  
  /**
   * Retrieve the object value of the element at the given index.
   *
   * @param index The index.
   * @return The object value.
   * @see #setObjectElement(int, JavaScriptObject)
   */
  private native JavaScriptObject getJsObjectElement(int index) /*-{
    return this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index];
  }-*/;
  
  /**
   * Set the element at the given index to the given object value.
   *
   * @param index The index.
   * @param value The object value.
   * @see #getObjectElement(int)
   */
  private native void setJsObjectElement(int index, JavaScriptObject value) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject[index] = value;
  }-*/;
  
  /**
   * Insert the given object value at the given index.
   *
   * @param index The index.
   * @param value The string value.
   */
  private native void insertJsObjectElement(int index, JavaScriptObject value) /*-{
    this.@org.kjots.json.object.client.impl.GwtJsonObjectImpl::jsObject.splice(index, 0, value);
  }-*/;
}
