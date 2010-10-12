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
import org.kjots.json.object.shared.JsonObjectArray;
import org.kjots.json.object.shared.impl.JsonObjectArrayImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * GWT JSON Object Array Implementation Test.
 * <p>
 * Created: 8th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonObjectArrayImplTest extends GwtJsonObjectTestBase {
  /** The JSON object array implementation test delegate*/
  private final JsonObjectArrayImplTestBase<JavaScriptObject> jsonObjectArrayImplTestDelegate = new JsonObjectArrayImplTestBase<JavaScriptObject>() {
    @Override
    protected JsonObject createJsonObject(JavaScriptObject object) {
      return new GwtJsonObjectImpl(JsonObject.class, object);
    }
    
    @Override
    protected JsonObjectArray<JsonObject> createJsonObjectArray(JavaScriptObject array) {
      return new GwtJsonObjectArrayImpl<JsonObject>((JsArray<?>)array);
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
    protected final native int getArrayLength(JavaScriptObject array) /*-{
      return array.length;
    }-*/;
    
    @Override
    protected final native JavaScriptObject getObjectElement(JavaScriptObject array, int elementIndex) /*-{
      return array[elementIndex];
    }-*/;
    
    @Override
    protected final native void setObjectElement(JavaScriptObject array, int elementIndex, JavaScriptObject elementValue) /*-{
      array[elementIndex] = elementValue;
    }-*/;
  };
  
  /**
   * @see JsonObjectArrayImplTestBase#testCastElement()
   */
  public void testCastElement() {
    this.jsonObjectArrayImplTestDelegate.testCastElement();
  }

  /**
   * @see JsonObjectArrayImplTestBase#testGet()
   */
  public void testGet() {
    this.jsonObjectArrayImplTestDelegate.testGet();
  }

  /**
   * @see JsonObjectArrayImplTestBase#testSet()
   */
  public void testSet() {
    this.jsonObjectArrayImplTestDelegate.testSet();
  }

  /**
   * @see JsonObjectArrayImplTestBase#testInsert()
   */
  public void testInsert() {
    this.jsonObjectArrayImplTestDelegate.testInsert();
  }
}
