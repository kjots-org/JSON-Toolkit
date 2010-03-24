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
package org.kjots.json.object.gwt.client.impl;

import org.kjots.json.object.gwt.client.GwtJsonObjectTest;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.impl.JsonObjectImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * GWT JSON Object Implementation Test.
 * <p>
 * Created: 6th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 0.1
 */
public class GwtJsonObjectImplTest<T extends JsonObject> extends GwtJsonObjectTest {
  /**
   * GWT JSON Object Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public class GwtJsonObjectImplTestDelegate extends JsonObjectImplTestBase<JavaScriptObject> {
    /**
     * Create a JSON object with the given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @return The JSON object.
     */
    @Override
    protected JsonObject createJsonObject(JavaScriptObject object) {
      return new GwtJsonObjectImpl(object);
    }

    /**
     * Create an empty underlying JSON object.
     *
     * @return The empty underlying JSON object.
     */
    @Override
    protected JavaScriptObject createUnderlyingJsonObject() {
      return JavaScriptObject.createObject();
    }
    
    /**
     * Create an empty underlying JSON array.
     *
     * @return The empty underlying JSON array.
     */
    @Override
    protected JavaScriptObject createUnderlyingJsonArray() {
      return JavaScriptObject.createArray();
    }
    
    /**
     * Retrieve the boolean value of the property with the given name from the
     * given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @param propertyName The name of the property.
     * @return The boolean value of the property.
     * @see #setBooleanProperty(JavaScriptObject, String, Boolean)
     */
    @Override
    protected final native Boolean getBooleanProperty(JavaScriptObject object, String propertyName) /*-{
      var jsPropertyValue = object[propertyName];
      
      return jsPropertyValue != null ? @java.lang.Boolean::valueOf(Z)(jsPropertyValue) : null;
    }-*/;
    
    /**
     * Set the property with the given name in the given underlying JSON object
     * to the given boolean value.
     *
     * @param object The underlying JSON object.
     * @param propertyName The name of the property.
     * @param propertyValue The boolean value of the property.
     * @see #getBooleanProperty(JavaScriptObject, String)
     */
    @Override
    protected final native void setBooleanProperty(JavaScriptObject object, String propertyName, Boolean propertyValue) /*-{
      var jsPropertyValue = propertyValue != null ? propertyValue.@java.lang.Boolean::booleanValue()() : null;
      
      object[propertyName] = jsPropertyValue;
    }-*/;
    
    /**
     * Retrieve the number value of the property with the given name from the
     * given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @param propertyName The name of the property.
     * @return The number value of the property.
     * @see #setNumberProperty(JavaScriptObject, String, Number)
     */
    @Override
    protected final native Number getNumberProperty(JavaScriptObject object, String propertyName) /*-{
      var jsPropertyValue = object[propertyName];
      
      return jsPropertyValue != null ? @java.lang.Double::valueOf(D)(jsPropertyValue) : null;
    }-*/;
    
    /**
     * Set the property with the given name in the given underlying JSON object
     * to the given number value.
     *
     * @param object The underlying JSON object.
     * @param propertyName The name of the property.
     * @param propertyValue The number value of the property.
     * @see #getNumberProperty(JavaScriptObject, String)
     */
    @Override
    protected final native void setNumberProperty(JavaScriptObject object, String propertyName, Number propertyValue) /*-{
      var jsPropertyValue = propertyValue != null ? propertyValue.@java.lang.Number::doubleValue()() : null;
      
      object[propertyName] = jsPropertyValue;
    }-*/;
    
    /**
     * Retrieve the string value of the property with the given name from the
     * given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @param propertyName The name of the property.
     * @return The string value of the property.
     * @see #setStringProperty(JavaScriptObject, String, String)
     */
    @Override
    protected final native String getStringProperty(JavaScriptObject object, String propertyName) /*-{
      return object[propertyName];
    }-*/;
    
    /**
     * Set the property with the given name in the given underlying JSON object
     * to the given string value.
     *
     * @param object The underlying JSON object.
     * @param propertyName The name of the property.
     * @param propertyValue The string value of the property.
     * @see #getStringProperty(JavaScriptObject, String)
     */
    @Override
    protected final native void setStringProperty(JavaScriptObject object, String propertyName, String propertyValue) /*-{
      object[propertyName] = propertyValue;
    }-*/;
    
    /**
     * Retrieve the object value of the property with the given name from the
     * given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @param propertyName The name of the property.
     * @return The object value of the property.
     * @see #setObjectProperty(JavaScriptObject, String, JavaScriptObject)
     */
    @Override
    protected final native JavaScriptObject getObjectProperty(JavaScriptObject object, String propertyName) /*-{
      return object[propertyName];
    }-*/;
    
    /**
     * Set the property with the given name in the given underlying JSON object
     * to the given object value.
     *
     * @param object The underlying JSON object.
     * @param propertyName The name of the property.
     * @param propertyValue The object value of the property.
     * @see #getObjectProperty(JavaScriptObject, String)
     */
    @Override
    protected final native void setObjectProperty(JavaScriptObject object, String propertyName, JavaScriptObject propertyValue) /*-{
      object[propertyName] = propertyValue;
    }-*/;
  }
  
  /** The GWT JSON object implementation test delegate. */
  private final GwtJsonObjectImplTestDelegate gwtJsonObjectImplTestDelegate = new GwtJsonObjectImplTestDelegate();

  /**
   * @see JsonObjectImplTestBase#testCast()
   */
  public void testCast() {
    this.gwtJsonObjectImplTestDelegate.testCast();
  }

  /**
   * @see JsonObjectImplTestBase#testIsArray()
   */
  public void testIsArray() {
    this.gwtJsonObjectImplTestDelegate.testIsArray();
  }

  /**
   * @see JsonObjectImplTestBase#testGetPropertyNames()
   */
  public void testGetPropertyNames() {
    this.gwtJsonObjectImplTestDelegate.testGetPropertyNames();
  }

  /**
   * @see JsonObjectImplTestBase#testHasProperty()
   */
  public void testHasProperty() {
    this.gwtJsonObjectImplTestDelegate.testHasProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testIsNullProperty()
   */
  public void testIsNullProperty() {
    this.gwtJsonObjectImplTestDelegate.testIsNullProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testIsBooleanProperty()
   */
  public void testIsBooleanProperty() {
    this.gwtJsonObjectImplTestDelegate.testIsBooleanProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testGetBooleanProperty()
   */
  public void testGetBooleanProperty() {
    this.gwtJsonObjectImplTestDelegate.testGetBooleanProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testSetBooleanProperty()
   */
  public void testSetBooleanProperty() {
    this.gwtJsonObjectImplTestDelegate.testSetBooleanProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testIsNumberProperty()
   */
  public void testIsNumberProperty() {
    this.gwtJsonObjectImplTestDelegate.testIsNumberProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testGetNumberProperty()
   */
  public void testGetNumberProperty() {
    this.gwtJsonObjectImplTestDelegate.testGetNumberProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testSetNumberProperty()
   */
  public void testSetNumberProperty() {
    this.gwtJsonObjectImplTestDelegate.testSetNumberProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testIsStringProperty()
   */
  public void testIsStringProperty() {
    this.gwtJsonObjectImplTestDelegate.testIsStringProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testGetStringProperty()
   */
  public void testGetStringProperty() {
    this.gwtJsonObjectImplTestDelegate.testGetStringProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testSetStringProperty()
   */
  public void testSetStringProperty() {
    this.gwtJsonObjectImplTestDelegate.testSetStringProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testIsObjectProperty()
   */
  public void testIsObjectProperty() {
    this.gwtJsonObjectImplTestDelegate.testIsObjectProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testGetObjectProperty()
   */
  public void testGetObjectProperty() {
    this.gwtJsonObjectImplTestDelegate.testGetObjectProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testGetObjectPropertyWithClass()
   */
  public void testGetObjectPropertyWithClass() {
    this.gwtJsonObjectImplTestDelegate.testGetObjectPropertyWithClass();
  }

  /**
   * @see JsonObjectImplTestBase#testSetObjectProperty()
   */
  public void testSetObjectProperty() {
    this.gwtJsonObjectImplTestDelegate.testSetObjectProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testDeleteProperty()
   */
  public void testDeleteProperty() {
    this.gwtJsonObjectImplTestDelegate.testDeleteProperty();
  }
  
  /**
   * @see JsonObjectImplTestBase#testEquals()
   */
  public void testEquals() {
    this.gwtJsonObjectImplTestDelegate.testEquals();
  }

  /**
   * @see JsonObjectImplTestBase#testHashCode()
   */
  public void testHashCode() {
    this.gwtJsonObjectImplTestDelegate.testHashCode();
  }
}
