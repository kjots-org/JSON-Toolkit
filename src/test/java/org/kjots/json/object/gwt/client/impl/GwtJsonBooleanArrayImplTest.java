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
import org.kjots.json.object.shared.JsonBooleanArray;
import org.kjots.json.object.shared.impl.JsonBooleanArrayImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayBoolean;

/**
 * GWT JSON Boolean Array Implementation Test.
 * <p>
 * Created: 7th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 0.1
 */
public class GwtJsonBooleanArrayImplTest extends GwtJsonObjectTest {
  /**
   * GWT JSON Boolean Array Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public class GwtJsonBooleanArrayImplTestDelegate extends JsonBooleanArrayImplTestBase<JavaScriptObject> {
    /**
     * Create a JSON boolean array with the given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @return The JSON boolean array.
     */
    @Override
    protected JsonBooleanArray createJsonBooleanArray(JavaScriptObject array) {
      return new GwtJsonBooleanArrayImpl((JsArrayBoolean)array.cast());
    }

    /**
     * Create an empty underlying JSON array.
     *
     * @return The empty underlying JSON array.
     */
    @Override
    protected JavaScriptObject createUnderlyingJsonArray() {
      return JavaScriptObject.createArray();
    }
    
    /**
     * Retrieve the length of the given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @return The length of the underlying JSON array.
     */
    @Override
    protected final native int getArrayLength(JavaScriptObject array) /*-{
      return array.length;
    }-*/;
    
    /**
     * Retrieve the boolean value of the element at the given index from the
     * given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @param elementIndex The index of the element.
     * @return The boolean value of the element.
     * @see #setBooleanElement(JavaScriptObject, int, Boolean)
     */
    @Override
    protected final native Boolean getBooleanElement(JavaScriptObject array, int elementIndex) /*-{
      var jsElementValue = array[elementIndex];
      
      return jsElementValue != null ? @java.lang.Boolean::valueOf(Z)(jsElementValue) : null;
    }-*/;
    
    /**
     * Set the element with the given name in the given underlying JSON array to
     * the given boolean value.
     *
     * @param array The underlying JSON array.
     * @param elementIndex The index of the element.
     * @param elementValue The boolean value of the element.
     * @see #getBooleanElement(JavaScriptObject, int)
     */
    @Override
    protected final native void setBooleanElement(JavaScriptObject array, int elementIndex, Boolean elementValue) /*-{
      var jsElementValue = elementValue != null ? elementValue.@java.lang.Boolean::booleanValue()() : null;
      
      array[elementIndex] = jsElementValue;
    }-*/;
  }
  
  /** The GWT JSON boolean array implementation test delegate. */
  private final GwtJsonBooleanArrayImplTestDelegate gwtJsonBooleanArrayImplTestDelegate = new GwtJsonBooleanArrayImplTestDelegate();

  /**
   * @see JsonBooleanArrayImplTestBase#testGet()
   */
  public void testGet() {
    this.gwtJsonBooleanArrayImplTestDelegate.testGet();
  }

  /**
   * @see JsonBooleanArrayImplTestBase#testSet()
   */
  public void testSet() {
    this.gwtJsonBooleanArrayImplTestDelegate.testSet();
  }

  /**
   * @see JsonBooleanArrayImplTestBase#testInsert()
   */
  public void testInsert() {
    this.gwtJsonBooleanArrayImplTestDelegate.testInsert();
  }
}
