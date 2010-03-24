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
import org.kjots.json.object.shared.JsonStringMap;
import org.kjots.json.object.shared.impl.JsonStringMapImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * GWT JSON String Map Implementation Test.
 * <p>
 * Created: 9th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 0.1
 */
public class GwtJsonStringMapImplTest extends GwtJsonObjectTest {
  /**
   * GWT JSON String Map Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public class GwtJsonStringMapImplTestDelegate extends JsonStringMapImplTestBase<JavaScriptObject> {
    /**
     * Create a JSON string map with the given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @return The JSON string map.
     */
    @Override
    protected JsonStringMap createJsonStringMap(JavaScriptObject object) {
      return new GwtJsonStringMapImpl(object);
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
  }
  
  /** The GWT JSON string map implementation test delegate. */
  private final GwtJsonStringMapImplTestDelegate gwtJsonStringMapImplTestDelegate = new GwtJsonStringMapImplTestDelegate();

  /**
   * @see JsonStringMapImplTestBase#testGet()
   */
  public void testGet() {
    this.gwtJsonStringMapImplTestDelegate.testGet();
  }

  /**
   * @see JsonStringMapImplTestBase#testSet()
   */
  public void testSet() {
    this.gwtJsonStringMapImplTestDelegate.testSet();
  }
}
