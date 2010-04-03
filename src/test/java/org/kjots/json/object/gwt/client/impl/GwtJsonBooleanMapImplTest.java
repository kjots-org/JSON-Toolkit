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
  /** The JSON boolean map implementation test delegate. */
  private final JsonBooleanMapImplTestBase<JavaScriptObject> jsonBooleanMapImplTestDelegate = new JsonBooleanMapImplTestBase<JavaScriptObject>() {
    @Override
    protected JsonBooleanMap createJsonBooleanMap(JavaScriptObject object) {
      return new GwtJsonBooleanMapImpl(object);
    }

    @Override
    protected JavaScriptObject createUnderlyingJsonObject() {
      return JavaScriptObject.createObject();
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
  };
  
  /**
   * @see JsonBooleanMapImplTestBase#testGet()
   */
  public void testGet() {
    this.jsonBooleanMapImplTestDelegate.testGet();
  }

  /**
   * @see JsonBooleanMapImplTestBase#testSet()
   */
  public void testSet() {
    this.jsonBooleanMapImplTestDelegate.testSet();
  }
}
