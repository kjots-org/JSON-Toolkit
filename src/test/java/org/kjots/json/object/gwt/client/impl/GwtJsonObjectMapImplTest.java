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

import com.google.gwt.core.client.JavaScriptObject;

import org.kjots.json.object.gwt.client.GwtJsonObjectTestBase;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectMap;
import org.kjots.json.object.shared.impl.JsonObjectMapImplTestBase;

/**
 * GWT JSON Object Map Implementation Test.
 * <p>
 * Created: 9th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonObjectMapImplTest extends GwtJsonObjectTestBase {
  /** The JSON object map implementation test delegate*/
  private final JsonObjectMapImplTestBase<JavaScriptObject> jsonObjectMapImplTestDelegate = new JsonObjectMapImplTestBase<JavaScriptObject>() {
    @Override
    protected JsonObject createJsonObject(JavaScriptObject object) {
      return new GwtJsonObjectImpl(object);
    }
    
    @Override
    protected JsonObjectMap<JsonObject> createJsonObjectMap(JavaScriptObject map) {
      return new GwtJsonObjectMapImpl<JsonObject>(map);
    }

    @Override
    protected JavaScriptObject createUnderlyingJsonObject() {
      return JavaScriptObject.createObject();
    }
    
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
   * @see JsonObjectMapImplTestBase#testCastElement()
   */
  public void testCastElement() {
    this.jsonObjectMapImplTestDelegate.testCastElement();
  }

  /**
   * @see JsonObjectMapImplTestBase#testGet()
   */
  public void testGet() {
    this.jsonObjectMapImplTestDelegate.testGet();
  }

  /**
   * @see JsonObjectMapImplTestBase#testSet()
   */
  public void testSet() {
    this.jsonObjectMapImplTestDelegate.testSet();
  }
}
