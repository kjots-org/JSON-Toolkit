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
import org.kjots.json.object.shared.JsonBooleanArray;
import org.kjots.json.object.shared.impl.JsonBooleanArrayImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayBoolean;

/**
 * GWT JSON Boolean Array Implementation GWT Test.
 * <p>
 * Created: 7th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonBooleanArrayImplGwtTest extends GwtJsonObjectGwtTestBase {
  /** The JSON boolean array implementation test delegate. */
  private final JsonBooleanArrayImplTestBase<JavaScriptObject> jsonBooleanArrayImplTestDelegate = new JsonBooleanArrayImplTestBase<JavaScriptObject>() {
    @Override
    protected JsonBooleanArray createJsonBooleanArray(JavaScriptObject array) {
      return new GwtJsonBooleanArrayImpl((JsArrayBoolean)array.cast());
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
    protected final native Boolean getBooleanElement(JavaScriptObject array, int elementIndex) /*-{
      var jsElementValue = array[elementIndex];
      
      return jsElementValue != null ? @java.lang.Boolean::valueOf(Z)(jsElementValue) : null;
    }-*/;
    
    @Override
    protected final native void setBooleanElement(JavaScriptObject array, int elementIndex, Boolean elementValue) /*-{
      var jsElementValue = elementValue != null ? elementValue.@java.lang.Boolean::booleanValue()() : null;
      
      array[elementIndex] = jsElementValue;
    }-*/;
  };
  
  /**
   * @see JsonBooleanArrayImplTestBase#testGet()
   */
  public void testGet() {
    this.jsonBooleanArrayImplTestDelegate.testGet();
  }

  /**
   * @see JsonBooleanArrayImplTestBase#testSet()
   */
  public void testSet() {
    this.jsonBooleanArrayImplTestDelegate.testSet();
  }

  /**
   * @see JsonBooleanArrayImplTestBase#testInsert()
   */
  public void testInsert() {
    this.jsonBooleanArrayImplTestDelegate.testInsert();
  }
}
