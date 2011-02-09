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
import org.kjots.json.object.shared.JsonStringMap;
import org.kjots.json.object.shared.impl.JsonStringMapImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * GWT JSON String Map Implementation GWT Test.
 * <p>
 * Created: 9th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonStringMapImplGwtTest extends GwtJsonObjectGwtTestBase {
  /** The JSON string map implementation test delegate. */
  private final JsonStringMapImplTestBase<JavaScriptObject> jsonStringMapImplTestDelegate = new JsonStringMapImplTestBase<JavaScriptObject>() {
    @Override
    protected JsonStringMap createJsonStringMap(JavaScriptObject object) {
      return new GwtJsonStringMapImpl(object);
    }

    @Override
    protected JavaScriptObject createUnderlyingJsonObject() {
      return JavaScriptObject.createObject();
    }
    
    @Override
    protected final native String getStringProperty(JavaScriptObject object, String propertyName) /*-{
      return object[propertyName];
    }-*/;
    
    @Override
    protected final native void setStringProperty(JavaScriptObject object, String propertyName, String propertyValue) /*-{
      object[propertyName] = propertyValue;
    }-*/;
  };

  /**
   * @see JsonStringMapImplTestBase#testGet()
   */
  public void testGet() {
    this.jsonStringMapImplTestDelegate.testGet();
  }

  /**
   * @see JsonStringMapImplTestBase#testSet()
   */
  public void testSet() {
    this.jsonStringMapImplTestDelegate.testSet();
  }
}
