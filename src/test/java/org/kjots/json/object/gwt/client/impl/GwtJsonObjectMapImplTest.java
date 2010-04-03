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
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectMap;
import org.kjots.json.object.shared.impl.JsonObjectMapImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * GWT JSON Object Map Implementation Test.
 * <p>
 * Created: 9th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 0.1
 */
public class GwtJsonObjectMapImplTest extends GwtJsonObjectTestBase {
  /**
   * GWT JSON Object Map Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public class GwtJsonObjectMapImplTestDelegate extends JsonObjectMapImplTestBase<JavaScriptObject> {
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
     * Create a JSON object map with the given underlying JSON map.
     *
     * @param map The underlying JSON map.
     * @return The JSON object map.
     */
    @Override
    protected JsonObjectMap<JsonObject> createJsonObjectMap(JavaScriptObject map) {
      return new GwtJsonObjectMapImpl<JsonObject>(map);
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
  
  /** The GWT JSON object map implementation test delegate*/
  private final GwtJsonObjectMapImplTestDelegate gwtJsonObjectMapImplTestDelegate = new GwtJsonObjectMapImplTestDelegate();

  /**
   * @see JsonObjectMapImplTestBase#testCastElement()
   */
  public void testCastElement() {
    this.gwtJsonObjectMapImplTestDelegate.testCastElement();
  }

  /**
   * @see JsonObjectMapImplTestBase#testGet()
   */
  public void testGet() {
    this.gwtJsonObjectMapImplTestDelegate.testGet();
  }

  /**
   * @see JsonObjectMapImplTestBase#testSet()
   */
  public void testSet() {
    this.gwtJsonObjectMapImplTestDelegate.testSet();
  }
}
