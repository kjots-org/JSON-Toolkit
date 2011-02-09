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

import org.kjots.json.object.gwt.client.GwtJsonObjectGwtTestBase;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.impl.JsonObjectImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * GWT JSON Object Implementation GWT Test.
 * <p>
 * Created: 6th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonObjectImplGwtTest extends GwtJsonObjectGwtTestBase {
  /** The JSON object implementation test delegate. */
  private final JsonObjectImplTestBase<JavaScriptObject> jsonObjectImplTestDelegate = new JsonObjectImplTestBase<JavaScriptObject>() {
    @Override
    protected JsonObject createJsonObject(JavaScriptObject object) {
      return new GwtJsonObjectImpl(JsonObject.class, object);
    }

    @Override
    protected JavaScriptObject createUnderlyingJsonObject() {
      return JavaScriptObject.createObject();
    }
    
    @Override
    protected JavaScriptObject createUnderlyingJsonArray() {
      return JavaScriptObject.createArray();
    }
    
    @Override
    protected final native Boolean getBooleanProperty(JavaScriptObject object, String propertyName) /*-{
      var jsPropertyValue = object[propertyName];
      
      return jsPropertyValue != null ? @java.lang.Boolean::valueOf(Z)(jsPropertyValue) : null;
    }-*/;
    
    @Override
    protected final native void setBooleanProperty(JavaScriptObject object, String propertyName, Boolean propertyValue) /*-{
      var jsPropertyValue = propertyValue != null ? propertyValue.@java.lang.Boolean::booleanValue()() : null;
      
      object[propertyName] = jsPropertyValue;
    }-*/;
    
    @Override
    protected final native Number getNumberProperty(JavaScriptObject object, String propertyName) /*-{
      var jsPropertyValue = object[propertyName];
      
      return jsPropertyValue != null ? @java.lang.Double::valueOf(D)(jsPropertyValue) : null;
    }-*/;
    
    @Override
    protected final native void setNumberProperty(JavaScriptObject object, String propertyName, Number propertyValue) /*-{
      var jsPropertyValue = propertyValue != null ? propertyValue.@java.lang.Number::doubleValue()() : null;
      
      object[propertyName] = jsPropertyValue;
    }-*/;
    
    @Override
    protected final native String getStringProperty(JavaScriptObject object, String propertyName) /*-{
      return object[propertyName];
    }-*/;
    
    @Override
    protected final native void setStringProperty(JavaScriptObject object, String propertyName, String propertyValue) /*-{
      object[propertyName] = propertyValue;
    }-*/;
    
    @Override
    protected final native JavaScriptObject getObjectProperty(JavaScriptObject object, String propertyName) /*-{
      return object[propertyName];
    }-*/;
    
    @Override
    protected final native void setObjectProperty(JavaScriptObject object, String propertyName, JavaScriptObject propertyValue) /*-{
      object[propertyName] = propertyValue;
    }-*/;
  };
  
  /**
   * @see JsonObjectImplTestBase#testCastByClass()
   */
  public void testCastByClass() {
    this.jsonObjectImplTestDelegate.testCastByClass();
  }

  /**
   * @see JsonObjectImplTestBase#testCastByClassName()
   */
  public void testCastByClassName() {
    this.jsonObjectImplTestDelegate.testCastByClassName();
  }

  /**
   * @see JsonObjectImplTestBase#testIsArray()
   */
  public void testIsArray() {
    this.jsonObjectImplTestDelegate.testIsArray();
  }

  /**
   * @see JsonObjectImplTestBase#testGetPropertyNames()
   */
  public void testGetPropertyNames() {
    this.jsonObjectImplTestDelegate.testGetPropertyNames();
  }

  /**
   * @see JsonObjectImplTestBase#testHasProperty()
   */
  public void testHasProperty() {
    this.jsonObjectImplTestDelegate.testHasProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testIsNullProperty()
   */
  public void testIsNullProperty() {
    this.jsonObjectImplTestDelegate.testIsNullProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testIsBooleanProperty()
   */
  public void testIsBooleanProperty() {
    this.jsonObjectImplTestDelegate.testIsBooleanProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testGetBooleanProperty()
   */
  public void testGetBooleanProperty() {
    this.jsonObjectImplTestDelegate.testGetBooleanProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testSetBooleanProperty()
   */
  public void testSetBooleanProperty() {
    this.jsonObjectImplTestDelegate.testSetBooleanProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testIsNumberProperty()
   */
  public void testIsNumberProperty() {
    this.jsonObjectImplTestDelegate.testIsNumberProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testGetNumberProperty()
   */
  public void testGetNumberProperty() {
    this.jsonObjectImplTestDelegate.testGetNumberProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testSetNumberProperty()
   */
  public void testSetNumberProperty() {
    this.jsonObjectImplTestDelegate.testSetNumberProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testIsStringProperty()
   */
  public void testIsStringProperty() {
    this.jsonObjectImplTestDelegate.testIsStringProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testGetStringProperty()
   */
  public void testGetStringProperty() {
    this.jsonObjectImplTestDelegate.testGetStringProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testSetStringProperty()
   */
  public void testSetStringProperty() {
    this.jsonObjectImplTestDelegate.testSetStringProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testIsObjectProperty()
   */
  public void testIsObjectProperty() {
    this.jsonObjectImplTestDelegate.testIsObjectProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testGetObjectProperty()
   */
  public void testGetObjectProperty() {
    this.jsonObjectImplTestDelegate.testGetObjectProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testGetObjectPropertyWithClass()
   */
  public void testGetObjectPropertyWithClass() {
    this.jsonObjectImplTestDelegate.testGetObjectPropertyWithClass();
  }

  /**
   * @see JsonObjectImplTestBase#testSetObjectProperty()
   */
  public void testSetObjectProperty() {
    this.jsonObjectImplTestDelegate.testSetObjectProperty();
  }

  /**
   * @see JsonObjectImplTestBase#testDeleteProperty()
   */
  public void testDeleteProperty() {
    this.jsonObjectImplTestDelegate.testDeleteProperty();
  }
  
  /**
   * @see JsonObjectImplTestBase#testEquals()
   */
  public void testEquals() {
    this.jsonObjectImplTestDelegate.testEquals();
  }

  /**
   * @see JsonObjectImplTestBase#testHashCode()
   */
  public void testHashCode() {
    this.jsonObjectImplTestDelegate.testHashCode();
  }
}
