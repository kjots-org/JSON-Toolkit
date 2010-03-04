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
import com.google.gwt.core.client.JsArrayNumber;

import org.kjots.json.object.client.GwtJsonObjectTest;
import org.kjots.json.object.client.impl.GwtJsonNumberArrayImpl;
import org.kjots.json.object.shared.JsonNumberArray;
import org.kjots.json.object.shared.impl.JsonNumberArrayImplTestBase;

/**
 * GWT JSON Number Array Implementation Test.
 * <p>
 * Created: 7th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 0.1
 */
public class GwtJsonNumberArrayImplTest extends GwtJsonObjectTest {
  /**
   * GWT JSON Number Array Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public class GwtJsonNumberArrayImplTestDelegate extends JsonNumberArrayImplTestBase<JavaScriptObject> {
    /**
     * Create a JSON number array with the given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @return The JSON number array.
     */
    @Override
    protected JsonNumberArray createJsonNumberArray(JavaScriptObject array) {
      return new GwtJsonNumberArrayImpl((JsArrayNumber)array.cast());
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
     * Retrieve the numeric value of the element at the given index from the
     * given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @param elementIndex The index of the element.
     * @return The numeric value of the element.
     * @see #setNumberElement(JavaScriptObject, int, Number)
     */
    @Override
    protected final native Number getNumberElement(JavaScriptObject array, int elementIndex) /*-{
      var jsElementValue = array[elementIndex];
      
      return jsElementValue != null ? @java.lang.Double::valueOf(D)(jsElementValue) : null;
    }-*/;
    
    /**
     * Set the element with the given name in the given underlying JSON array to
     * the given numeric value.
     *
     * @param array The underlying JSON array.
     * @param elementIndex The index of the element.
     * @param elementValue The numeric value of the element.
     * @see #getNumberElement(JavaScriptObject, int)
     */
    @Override
    protected final native void setNumberElement(JavaScriptObject array, int elementIndex, Number elementValue) /*-{
      var jsElementValue = elementValue != null ? elementValue.@java.lang.Number::doubleValue()() : null;
      
      array[elementIndex] = jsElementValue;
    }-*/;
  }
  
  /** The GWT JSON number array implementation test delegate. */
  private final GwtJsonNumberArrayImplTestDelegate gwtJsonNumberArrayImplTestDelegate = new GwtJsonNumberArrayImplTestDelegate();

  /**
   * @see JsonNumberArrayImplTestBase#testGet()
   */
  public void testGet() {
    this.gwtJsonNumberArrayImplTestDelegate.testGet();
  }

  /**
   * @see JsonNumberArrayImplTestBase#testSet()
   */
  public void testSet() {
    this.gwtJsonNumberArrayImplTestDelegate.testSet();
  }

  /**
   * @see JsonNumberArrayImplTestBase#testInsert()
   */
  public void testInsert() {
    this.gwtJsonNumberArrayImplTestDelegate.testInsert();
  }
}
