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
 * @since 0.1
 */
public class GwtJsonObjectArrayImplTest extends GwtJsonObjectTest {
  /**
   * GWT JSON Object Array Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public class GwtJsonObjectArrayImplTestDelegate extends JsonObjectArrayImplTestBase<JavaScriptObject> {
    /**
     * Create a JSON object with the given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @return The JSON object.
     */
    @Override
    protected JsonObject createJsonObject(JavaScriptObject object) {
      return new GwtJsonObjectImpl(object);
    }
    
    /**
     * Create a JSON object array with the given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @return The JSON object array.
     */
    @Override
    protected JsonObjectArray<JsonObject> createJsonObjectArray(JavaScriptObject array) {
      return new GwtJsonObjectArrayImpl<JsonObject>((JsArray<?>)array);
    }

    /**
     * Create an empty underlying JSON object.
     *
     * @return The empty underlying JSON object.
     */
    @Override
    protected JavaScriptObject createUnderlyingJsonObject() {
      return JavaScriptObject.createObject();
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
     * Retrieve the object value of the element at the given index from the
     * given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @param elementIndex The index of the element.
     * @return The object value of the element.
     * @see #setObjectElement(JavaScriptObject, int, JavaScriptObject)
     */
    @Override
    protected final native JavaScriptObject getObjectElement(JavaScriptObject array, int elementIndex) /*-{
      return array[elementIndex];
    }-*/;
    
    /**
     * Set the element with the given name in the given underlying JSON array to
     * the given object value.
     *
     * @param array The underlying JSON array.
     * @param elementIndex The index of the element.
     * @param elementValue The object value of the element.
     * @see #getObjectElement(JavaScriptObject, int)
     */
    @Override
    protected final native void setObjectElement(JavaScriptObject array, int elementIndex, JavaScriptObject elementValue) /*-{
      array[elementIndex] = elementValue;
    }-*/;
  }
  
  /** The GWT JSON object array implementation test delegate*/
  private final GwtJsonObjectArrayImplTestDelegate gwtJsonObjectArrayImplTestDelegate = new GwtJsonObjectArrayImplTestDelegate();

  /**
   * @see JsonObjectArrayImplTestBase#testCastElement()
   */
  public void testCastElement() {
    this.gwtJsonObjectArrayImplTestDelegate.testCastElement();
  }

  /**
   * @see JsonObjectArrayImplTestBase#testGet()
   */
  public void testGet() {
    this.gwtJsonObjectArrayImplTestDelegate.testGet();
  }

  /**
   * @see JsonObjectArrayImplTestBase#testSet()
   */
  public void testSet() {
    this.gwtJsonObjectArrayImplTestDelegate.testSet();
  }

  /**
   * @see JsonObjectArrayImplTestBase#testInsert()
   */
  public void testInsert() {
    this.gwtJsonObjectArrayImplTestDelegate.testInsert();
  }
}
