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
 * @since json-object-0.1
 */
public class GwtJsonNumberMapImplTest extends GwtJsonObjectTestBase {
  /** The JSON number map implementation test delegate. */
  private final JsonNumberMapImplTestBase<JavaScriptObject> jsonNumberMapImplTestDelegate = new JsonNumberMapImplTestBase<JavaScriptObject>() {
    @Override
    protected JsonNumberMap createJsonNumberMap(JavaScriptObject object) {
      return new GwtJsonNumberMapImpl(object);
    }

    @Override
    protected JavaScriptObject createUnderlyingJsonObject() {
      return JavaScriptObject.createObject();
    }
    
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
  };
  
  /**
   * @see JsonNumberMapImplTestBase#testGet()
   */
  public void testGet() {
    this.jsonNumberMapImplTestDelegate.testGet();
  }

  /**
   * @see JsonNumberMapImplTestBase#testSet()
   */
  public void testSet() {
    this.jsonNumberMapImplTestDelegate.testSet();
  }
}
