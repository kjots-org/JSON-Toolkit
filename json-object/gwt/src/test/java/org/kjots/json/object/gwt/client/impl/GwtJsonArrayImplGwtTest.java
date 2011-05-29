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

import org.kjots.json.object.gwt.client.GwtJsonObjectGwtTestBase;
import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.impl.JsonArrayImplTestBase;

/**
 * GWT JSON Array Implementation GWT Test.
 * <p>
 * Created: 7th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonArrayImplGwtTest extends GwtJsonObjectGwtTestBase {
  /** The JSON array implementation test delegate. */
  private final JsonArrayImplTestBase<JavaScriptObject> jsonArrayImplTestDelegate = new JsonArrayImplTestBase<JavaScriptObject>() {
    @Override
    protected JsonObject createJsonObject(JavaScriptObject object) {
      return new GwtJsonObjectImpl(object);
    }

    @Override
    protected JsonArray createJsonArray(JavaScriptObject array) {
      return new GwtJsonArrayImpl(array);
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
    protected final native void setArrayLength(JavaScriptObject array, int length) /*-{
      array.length = length;
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
    
    @Override
    protected final native Number getNumberElement(JavaScriptObject array, int elementIndex) /*-{
      var jsElementValue = array[elementIndex];
      
      return jsElementValue != null ? @java.lang.Double::valueOf(D)(jsElementValue) : null;
    }-*/;
    
    @Override
    protected final native void setNumberElement(JavaScriptObject array, int elementIndex, Number elementValue) /*-{
      var jsElementValue = elementValue != null ? elementValue.@java.lang.Number::doubleValue()() : null;
      
      array[elementIndex] = jsElementValue;
    }-*/;
    
    @Override
    protected final native String getStringElement(JavaScriptObject array, int elementIndex) /*-{
      return array[elementIndex];
    }-*/;
    
    @Override
    protected final native void setStringElement(JavaScriptObject array, int elementIndex, String elementValue) /*-{
      array[elementIndex] = elementValue;
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
   * Test the construction of a JSON array with a non-array-object argument.
   * <p>
   * This test asserts that the constructor of a JSON array will throw a
   * {@link IllegalArgumentException} if the argument is not an array object.
   */
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
    this.jsonArrayImplTestDelegate.testGetLength();
  }

  /**
   * @see JsonArrayImplTestBase#testSetLength()
   */
  public void testSetLength() {
    this.jsonArrayImplTestDelegate.testSetLength();
  }

  /**
   * @see JsonArrayImplTestBase#testIsNullElement()
   */
  public void testIsNullElement() {
    this.jsonArrayImplTestDelegate.testIsNullElement();
  }

  /**
   * @see JsonArrayImplTestBase#testIsBooleanElement()
   */
  public void testIsBooleanElement() {
    this.jsonArrayImplTestDelegate.testIsBooleanElement();
  }

  /**
   * @see JsonArrayImplTestBase#testGetBooleanElement()
   */
  public void testGetBooleanElement() {
    this.jsonArrayImplTestDelegate.testGetBooleanElement();
  }

  /**
   * @see JsonArrayImplTestBase#testSetBooleanElement()
   */
  public void testSetBooleanElement() {
    this.jsonArrayImplTestDelegate.testSetBooleanElement();
  }

  /**
   * @see JsonArrayImplTestBase#testInsertBooleanElement()
   */
  public void testInsertBooleanElement() {
    this.jsonArrayImplTestDelegate.testInsertBooleanElement();
  }

  /**
   * @see JsonArrayImplTestBase#testPrependBooleanElement()
   */
  public void testPrependBooleanElement() {
    this.jsonArrayImplTestDelegate.testPrependBooleanElement();
  }

  /**
   * @see JsonArrayImplTestBase#testAppendBooleanElement()
   */
  public void testAppendBooleanElement() {
    this.jsonArrayImplTestDelegate.testAppendBooleanElement();
  }

  /**
   * @see JsonArrayImplTestBase#testIsNumberElement()
   */
  public void testIsNumberElement() {
    this.jsonArrayImplTestDelegate.testIsNumberElement();
  }

  /**
   * @see JsonArrayImplTestBase#testGetNumberElement()
   */
  public void testGetNumberElement() {
    this.jsonArrayImplTestDelegate.testGetNumberElement();
  }

  /**
   * @see JsonArrayImplTestBase#testSetNumberElement()
   */
  public void testSetNumberElement() {
    this.jsonArrayImplTestDelegate.testSetNumberElement();
  }

  /**
   * @see JsonArrayImplTestBase#testInsertNumberElement()
   */
  public void testInsertNumberElement() {
    this.jsonArrayImplTestDelegate.testInsertNumberElement();
  }

  /**
   * @see JsonArrayImplTestBase#testPrependNumberElement()
   */
  public void testPrependNumberElement() {
    this.jsonArrayImplTestDelegate.testPrependNumberElement();
  }

  /**
   * @see JsonArrayImplTestBase#testAppendNumberElement()
   */
  public void testAppendNumberElement() {
    this.jsonArrayImplTestDelegate.testAppendNumberElement();
  }

  /**
   * @see JsonArrayImplTestBase#testIsStringElement()
   */
  public void testIsStringElement() {
    this.jsonArrayImplTestDelegate.testIsStringElement();
  }

  /**
   * @see JsonArrayImplTestBase#testGetStringElement()
   */
  public void testGetStringElement() {
    this.jsonArrayImplTestDelegate.testGetStringElement();
  }

  /**
   * @see JsonArrayImplTestBase#testSetStringElement()
   */
  public void testSetStringElement() {
    this.jsonArrayImplTestDelegate.testSetStringElement();
  }

  /**
   * @see JsonArrayImplTestBase#testInsertStringElement()
   */
  public void testInsertStringElement() {
    this.jsonArrayImplTestDelegate.testInsertStringElement();
  }

  /**
   * @see JsonArrayImplTestBase#testPrependStringElement()
   */
  public void testPrependStringElement() {
    this.jsonArrayImplTestDelegate.testPrependStringElement();
  }

  /**
   * @see JsonArrayImplTestBase#testAppendStringElement()
   */
  public void testAppendStringElement() {
    this.jsonArrayImplTestDelegate.testAppendStringElement();
  }

  /**
   * @see JsonArrayImplTestBase#testIsObjectElement()
   */
  public void testIsObjectElement() {
    this.jsonArrayImplTestDelegate.testIsObjectElement();
  }

  /**
   * @see JsonArrayImplTestBase#testGetObjectElement()
   */
  public void testGetObjectElement() {
    this.jsonArrayImplTestDelegate.testGetObjectElement();
  }

  /**
   * @see JsonArrayImplTestBase#testGetObjectElementWithClass()
   */
  public void testGetObjectElementWithClass() {
    this.jsonArrayImplTestDelegate.testGetObjectElementWithClass();
  }

  /**
   * @see JsonArrayImplTestBase#testSetObjectElement()
   */
  public void testSetObjectElement() {
    this.jsonArrayImplTestDelegate.testSetObjectElement();
  }

  /**
   * @see JsonArrayImplTestBase#testInsertObjectElement()
   */
  public void testInsertObjectElement() {
    this.jsonArrayImplTestDelegate.testInsertObjectElement();
  }

  /**
   * @see JsonArrayImplTestBase#testPrependObjectElement()
   */
  public void testPrependObjectElement() {
    this.jsonArrayImplTestDelegate.testPrependObjectElement();
  }

  /**
   * @see JsonArrayImplTestBase#testAppendObjectElement()
   */
  public void testAppendObjectElement() {
    this.jsonArrayImplTestDelegate.testAppendObjectElement();
  }

  /**
   * @see JsonArrayImplTestBase#testRemoveElements()
   */
  public void testRemoveElements() {
    this.jsonArrayImplTestDelegate.testRemoveElements();
  }
}
