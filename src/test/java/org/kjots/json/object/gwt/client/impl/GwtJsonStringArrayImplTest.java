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
import org.kjots.json.object.shared.JsonStringArray;
import org.kjots.json.object.shared.impl.JsonStringArrayImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * GWT JSON String Array Implementation Test.
 * <p>
 * Created: 7th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonStringArrayImplTest extends GwtJsonObjectTestBase {
  /** The JSON string array implementation test delegate. */
  private final JsonStringArrayImplTestBase<JavaScriptObject> jsonStringArrayImplTestDelegate = new JsonStringArrayImplTestBase<JavaScriptObject>() {
    @Override
    protected JsonStringArray createJsonStringArray(JavaScriptObject array) {
      return new GwtJsonStringArrayImpl((JsArrayString)array.cast());
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
    protected final native String getStringElement(JavaScriptObject array, int elementIndex) /*-{
      return array[elementIndex];
    }-*/;
    
    @Override
    protected final native void setStringElement(JavaScriptObject array, int elementIndex, String elementValue) /*-{
      array[elementIndex] = elementValue;
    }-*/;
  };
  
  /**
   * @see JsonStringArrayImplTestBase#testGet()
   */
  public void testGet() {
    this.jsonStringArrayImplTestDelegate.testGet();
  }

  /**
   * @see JsonStringArrayImplTestBase#testSet()
   */
  public void testSet() {
    this.jsonStringArrayImplTestDelegate.testSet();
  }

  /**
   * @see JsonStringArrayImplTestBase#testInsert()
   */
  public void testInsert() {
    this.jsonStringArrayImplTestDelegate.testInsert();
  }
}
