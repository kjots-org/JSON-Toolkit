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

import org.junit.Test;
import org.kjots.json.object.gwt.client.GwtJsonObjectTestBase;
import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.impl.JsonArrayImplTestBase;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * GWT JSON Array Implementation Test.
 * <p>
 * Created: 7th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 0.1
 */
public class GwtJsonArrayImplTest extends GwtJsonObjectTestBase {
  /**
   * GWT JSON Array Implementation Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public class GwtJsonArrayImplTestDelegate extends JsonArrayImplTestBase<JavaScriptObject> {
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
     * Create a JSON array with the given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @return The JSON array.
     */
    @Override
    protected JsonArray createJsonArray(JavaScriptObject array) {
      return new GwtJsonArrayImpl(array);
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
     * @see #setArrayLength(Object, int)
     */
    @Override
    protected final native int getArrayLength(JavaScriptObject array) /*-{
      return array.length;
    }-*/;
    
    /**
     * Set the length of the given underlying JSON array.
     *
     * @param array The underlying JSON array.
     * @param length The length of the underlying JSON array.
     * @see #getArrayLength(Object)
     */
    @Override
    protected final native void setArrayLength(JavaScriptObject array, int length) /*-{
      array.length = length;
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
  
  /** The GWT JSON array implementation test delegate. */
  private final GwtJsonArrayImplTestDelegate gwtJsonArrayImplTestDelegate = new GwtJsonArrayImplTestDelegate();
  
  /**
   * Test the construction of a JSON array with a non-array-object argument.
   * <p>
   * This test asserts that the constructor of a JSON array will throw a
   * {@link IllegalArgumentException} if the argument is not an array object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructionWithNonArrayObject() {
    try {
      new GwtJsonArrayImpl(JavaScriptObject.createObject());
      
      fail("Expected " + IllegalArgumentException.class.getName());
    }
    catch (IllegalArgumentException iae) {
    }
  }

  /**
   * @see JsonArrayImplTestBase#testGetLength()
   */
  public void testGetLength() {
    this.gwtJsonArrayImplTestDelegate.testGetLength();
  }

  /**
   * @see JsonArrayImplTestBase#testIsNullElement()
   */
  public void testIsNullElement() {
    this.gwtJsonArrayImplTestDelegate.testIsNullElement();
  }

  /**
   * @see JsonArrayImplTestBase#testIsBooleanElement()
   */
  public void testIsBooleanElement() {
    this.gwtJsonArrayImplTestDelegate.testIsBooleanElement();
  }

  /**
   * @see JsonArrayImplTestBase#testGetBooleanElement()
   */
  public void testGetBooleanElement() {
    this.gwtJsonArrayImplTestDelegate.testGetBooleanElement();
  }

  /**
   * @see JsonArrayImplTestBase#testSetBooleanElement()
   */
  public void testSetBooleanElement() {
    this.gwtJsonArrayImplTestDelegate.testSetBooleanElement();
  }

  /**
   * @see JsonArrayImplTestBase#testInsertBooleanElement()
   */
  public void testInsertBooleanElement() {
    this.gwtJsonArrayImplTestDelegate.testInsertBooleanElement();
  }

  /**
   * @see JsonArrayImplTestBase#testIsNumberElement()
   */
  public void testIsNumberElement() {
    this.gwtJsonArrayImplTestDelegate.testIsNumberElement();
  }

  /**
   * @see JsonArrayImplTestBase#testGetNumberElement()
   */
  public void testGetNumberElement() {
    this.gwtJsonArrayImplTestDelegate.testGetNumberElement();
  }

  /**
   * @see JsonArrayImplTestBase#testSetNumberElement()
   */
  public void testSetNumberElement() {
    this.gwtJsonArrayImplTestDelegate.testSetNumberElement();
  }

  /**
   * @see JsonArrayImplTestBase#testInsertNumberElement()
   */
  public void testInsertNumberElement() {
    this.gwtJsonArrayImplTestDelegate.testInsertNumberElement();
  }

  /**
   * @see JsonArrayImplTestBase#testIsStringElement()
   */
  public void testIsStringElement() {
    this.gwtJsonArrayImplTestDelegate.testIsStringElement();
  }

  /**
   * @see JsonArrayImplTestBase#testGetStringElement()
   */
  public void testGetStringElement() {
    this.gwtJsonArrayImplTestDelegate.testGetStringElement();
  }

  /**
   * @see JsonArrayImplTestBase#testSetStringElement()
   */
  public void testSetStringElement() {
    this.gwtJsonArrayImplTestDelegate.testSetStringElement();
  }

  /**
   * @see JsonArrayImplTestBase#testInsertStringElement()
   */
  public void testInsertStringElement() {
    this.gwtJsonArrayImplTestDelegate.testInsertStringElement();
  }

  /**
   * @see JsonArrayImplTestBase#testIsObjectElement()
   */
  public void testIsObjectElement() {
    this.gwtJsonArrayImplTestDelegate.testIsObjectElement();
  }

  /**
   * @see JsonArrayImplTestBase#testGetObjectElement()
   */
  public void testGetObjectElement() {
    this.gwtJsonArrayImplTestDelegate.testGetObjectElement();
  }

  /**
   * @see JsonArrayImplTestBase#testGetObjectElementWithClass()
   */
  public void testGetObjectElementWithClass() {
    this.gwtJsonArrayImplTestDelegate.testGetObjectElementWithClass();
  }

  /**
   * @see JsonArrayImplTestBase#testSetObjectElement()
   */
  public void testSetObjectElement() {
    this.gwtJsonArrayImplTestDelegate.testSetObjectElement();
  }

  /**
   * @see JsonArrayImplTestBase#testInsertObjectElement()
   */
  public void testInsertObjectElement() {
    this.gwtJsonArrayImplTestDelegate.testInsertObjectElement();
  }

  /**
   * @see JsonArrayImplTestBase#testRemoveElements()
   */
  public void testRemoveElements() {
    this.gwtJsonArrayImplTestDelegate.testRemoveElements();
  }
}
