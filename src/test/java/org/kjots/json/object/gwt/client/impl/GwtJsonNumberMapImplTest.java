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
import org.kjots.json.object.shared.JsonNumberMap;
import org.kjots.json.object.shared.impl.JsonNumberMapImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * GWT JSON Number Map Implementation Test.
 * <p>
 * Created: 9th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 0.1
 */
public class GwtJsonNumberMapImplTest extends GwtJsonObjectTestBase {
  /**
   * GWT JSON Number Map Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public class GwtJsonNumberMapImplTestDelegate extends JsonNumberMapImplTestBase<JavaScriptObject> {
    /**
     * Create a JSON number map with the given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @return The JSON number map.
     */
    @Override
    protected JsonNumberMap createJsonNumberMap(JavaScriptObject object) {
      return new GwtJsonNumberMapImpl(object);
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
  }
  
  /** The GWT JSON number map implementation test delegate. */
  private final GwtJsonNumberMapImplTestDelegate gwtJsonNumberMapImplTestDelegate = new GwtJsonNumberMapImplTestDelegate();

  /**
   * @see JsonNumberMapImplTestBase#testGet()
   */
  public void testGet() {
    this.gwtJsonNumberMapImplTestDelegate.testGet();
  }

  /**
   * @see JsonNumberMapImplTestBase#testSet()
   */
  public void testSet() {
    this.gwtJsonNumberMapImplTestDelegate.testSet();
  }
}
