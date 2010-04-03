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
  /**
   * GWT JSON String Array Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public static class GwtJsonStringArrayImplTestDelegate extends JsonStringArrayImplTestBase<JavaScriptObject> {
    /**
     * Create a JSON string array with the given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @return The JSON string array.
     */
    @Override
    protected JsonStringArray createJsonStringArray(JavaScriptObject array) {
      return new GwtJsonStringArrayImpl((JsArrayString)array.cast());
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
     * Retrieve the string value of the element at the given index from the
     * given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @param elementIndex The index of the element.
     * @return The string value of the element.
     * @see #setStringElement(JavaScriptObject, int, String)
     */
    @Override
    protected final native String getStringElement(JavaScriptObject array, int elementIndex) /*-{
      return array[elementIndex];
    }-*/;
    
    /**
     * Set the element with the given name in the given underlying JSON array to
     * the given string value.
     *
     * @param array The underlying JSON array.
     * @param elementIndex The index of the element.
     * @param elementValue The string value of the element.
     * @see #getStringElement(JavaScriptObject, int)
     */
    @Override
    protected final native void setStringElement(JavaScriptObject array, int elementIndex, String elementValue) /*-{
      array[elementIndex] = elementValue;
    }-*/;
  }
  
  /** The GWT JSON string array implementation test delegate. */
  private final GwtJsonStringArrayImplTestDelegate gwtJsonStringArrayImplTestDelegate = new GwtJsonStringArrayImplTestDelegate();

  /**
   * @see JsonStringArrayImplTestBase#testGet()
   */
  public void testGet() {
    this.gwtJsonStringArrayImplTestDelegate.testGet();
  }

  /**
   * @see JsonStringArrayImplTestBase#testSet()
   */
  public void testSet() {
    this.gwtJsonStringArrayImplTestDelegate.testSet();
  }

  /**
   * @see JsonStringArrayImplTestBase#testInsert()
   */
  public void testInsert() {
    this.gwtJsonStringArrayImplTestDelegate.testInsert();
  }
}
