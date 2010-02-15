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

import org.kjots.json.object.client.GwtJsonObjectTest;
import org.kjots.json.object.client.impl.GwtJsonIntegerMapImpl;
import org.kjots.json.object.shared.JsonIntegerMap;
import org.kjots.json.object.shared.impl.JsonIntegerMapImplTestBase;

/**
 * GWT JSON String Map Implementation Test.
 * <p>
 * Created: 9th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 0.1
 */
public class GwtJsonIntegerMapImplTest extends GwtJsonObjectTest {
  /**
   * GWT JSON Integer Map Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public class GwtJsonIntegerMapImplTestDelegate extends JsonIntegerMapImplTestBase<JavaScriptObject> {
    /**
     * Create a JSON integer map with the given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @return The JSON integer map.
     */
    @Override
    protected JsonIntegerMap createJsonIntegerMap(JavaScriptObject object) {
      return new GwtJsonIntegerMapImpl(object);
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
     * Retrieve the integer value of the property with the given name from the
     * given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @param propertyName The name of the property.
     * @return The integer value of the property.
     * @see #setIntegerProperty(JavaScriptObject, String, int)
     */
    @Override
    protected final native int getIntegerProperty(JavaScriptObject object, String propertyName) /*-{
      return object[propertyName];
    }-*/;
    
    /**
     * Set the property with the given name in the given underlying JSON object
     * to the given integer value.
     *
     * @param object The underlying JSON object.
     * @param propertyName The name of the property.
     * @param propertyValue The integer value of the property.
     * @see #getIntegerProperty(JavaScriptObject, String)
     */
    @Override
    protected final native void setIntegerProperty(JavaScriptObject object, String propertyName, int propertyValue) /*-{
      object[propertyName] = propertyValue;
    }-*/;
  }
  
  /** The GWT JSON integer map implementation test delegate. */
  private final GwtJsonIntegerMapImplTestDelegate gwtJsonIntegerMapImplTestDelegate = new GwtJsonIntegerMapImplTestDelegate();

  /**
   * @see JsonIntegerMapImplTestBase#testGet()
   */
  public void testGet() {
    this.gwtJsonIntegerMapImplTestDelegate.testGet();
  }

  /**
   * @see JsonIntegerMapImplTestBase#testSet()
   */
  public void testSet() {
    this.gwtJsonIntegerMapImplTestDelegate.testSet();
  }
}
