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
import com.google.gwt.core.client.JsArrayInteger;

import org.kjots.json.object.client.GwtJsonObjectTest;
import org.kjots.json.object.client.impl.GwtJsonIntegerArrayImpl;
import org.kjots.json.object.shared.JsonIntegerArray;
import org.kjots.json.object.shared.impl.JsonIntegerArrayImplTestBase;

/**
 * GWT JSON Integer Array Implementation Test.
 * <p>
 * Created: 7th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 0.1
 */
public class GwtJsonIntegerArrayImplTest extends GwtJsonObjectTest {
  /**
   * GWT JSON Integer Array Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public class GwtJsonIntegerArrayImplTestDelegate extends JsonIntegerArrayImplTestBase<JavaScriptObject> {
    /**
     * Create a JSON integer array with the given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @return The JSON integer array.
     */
    @Override
    protected JsonIntegerArray createJsonIntegerArray(JavaScriptObject array) {
      return new GwtJsonIntegerArrayImpl((JsArrayInteger)array.cast());
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
     * Retrieve the integer value of the element at the given index from the
     * given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @param elementIndex The index of the element.
     * @return The integer value of the element.
     * @see #setIntegerElement(JavaScriptObject, int, int)
     */
    @Override
    protected final native int getIntegerElement(JavaScriptObject array, int elementIndex) /*-{
      return array[elementIndex];
    }-*/;
    
    /**
     * Set the element with the given name in the given underlying JSON array to
     * the given integer value.
     *
     * @param array The underlying JSON array.
     * @param elementIndex The index of the element.
     * @param elementValue The integer value of the element.
     * @see #getIntegerElement(JavaScriptObject, int)
     */
    @Override
    protected final native void setIntegerElement(JavaScriptObject array, int elementIndex, int elementValue) /*-{
      array[elementIndex] = elementValue;
    }-*/;
  }
  
  /** The GWT JSON integer array implementation test delegate. */
  private final GwtJsonIntegerArrayImplTestDelegate gwtJsonIntegerArrayImplTestDelegate = new GwtJsonIntegerArrayImplTestDelegate();

  /**
   * @see JsonIntegerArrayImplTestBase#testGet()
   */
  public void testGet() {
    this.gwtJsonIntegerArrayImplTestDelegate.testGet();
  }

  /**
   * @see JsonIntegerArrayImplTestBase#testSet()
   */
  public void testSet() {
    this.gwtJsonIntegerArrayImplTestDelegate.testSet();
  }

  /**
   * @see JsonIntegerArrayImplTestBase#testInsert()
   */
  public void testInsert() {
    this.gwtJsonIntegerArrayImplTestDelegate.testInsert();
  }
}
