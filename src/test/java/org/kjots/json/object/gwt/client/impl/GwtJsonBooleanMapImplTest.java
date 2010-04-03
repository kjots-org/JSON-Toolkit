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

import org.kjots.json.object.gwt.client.GwtJsonObjectTestBase;
import org.kjots.json.object.shared.JsonBooleanMap;
import org.kjots.json.object.shared.impl.JsonBooleanMapImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * GWT JSON Boolean Map Implementation Test.
 * <p>
 * Created: 9th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonBooleanMapImplTest extends GwtJsonObjectTestBase {
  /**
   * GWT JSON Boolean Map Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public static class GwtJsonBooleanMapImplTestDelegate extends JsonBooleanMapImplTestBase<JavaScriptObject> {
    /**
     * Create a JSON boolean map with the given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @return The JSON boolean map.
     */
    @Override
    protected JsonBooleanMap createJsonBooleanMap(JavaScriptObject object) {
      return new GwtJsonBooleanMapImpl(object);
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
  }
  
  /** The GWT JSON boolean map implementation test delegate. */
  private final GwtJsonBooleanMapImplTestDelegate gwtJsonBooleanMapImplTestDelegate = new GwtJsonBooleanMapImplTestDelegate();

  /**
   * @see JsonBooleanMapImplTestBase#testGet()
   */
  public void testGet() {
    this.gwtJsonBooleanMapImplTestDelegate.testGet();
  }

  /**
   * @see JsonBooleanMapImplTestBase#testSet()
   */
  public void testSet() {
    this.gwtJsonBooleanMapImplTestDelegate.testSet();
  }
}
