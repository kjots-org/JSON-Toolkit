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
package org.kjots.json.object.shared;

/**
 * JSON Array.
 * <p>
 * Created: 7th November 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public interface JsonArray extends JsonObject {
  /**
   * Retrieve the length of the array.
   *
   * @return The length of the array.
   * @see #setLength(int)
   */
  public int getLength();
  
  /**
   * Set the length of the array.
   * 
   * @param length The length of the array.
   * @see #getLength()
   */
  public void setLength(int length);
  
  /**
   * Determine if the element at the given index is <code>null</code>.
   *
   * @param index The index.
   * @return <code>true</code> if the element is <code>null</code>.
   */
  public boolean isNullElement(int index);
  
  /**
   * Determine if the element at the given index has a boolean value.
   * <p>
   * This method will return <code>false</code> if the element at the given
   * index is <code>null</code>.
   *
   * @param index The index.
   * @return <code>true</code> if element has a boolean value.
   */
  public boolean isBooleanElement(int index);

  /**
   * Retrieve the boolean value of the element at the given index.
   * <p>
   * This method will return <code>null</code> if there is no element at the
   * given index.
   *
   * @param index The index.
   * @return The boolean value.
   * @see #setBooleanElement(int, Boolean)
   */
  public Boolean getBooleanElement(int index);
  
  /**
   * Set the element at the given index to the given boolean value.
   *
   * @param index The index.
   * @param value The boolean value.
   * @see #getBooleanElement(int)
   */
  public void setBooleanElement(int index, Boolean value);
  
  /**
   * Insert the given boolean value at the given index.
   *
   * @param index The index.
   * @param value The boolean value.
   */
  public void insertBooleanElement(int index, Boolean value);
  
  /**
   * Determine if the element at the given index has a numeric value.
   * <p>
   * This method will return <code>false</code> if the element at the given
   * index is <code>null</code>.
   *
   * @param index The index.
   * @return <code>true</code> if element has a numeric value.
   */
  public boolean isNumberElement(int index);

  /**
   * Retrieve the numeric value of the element at the given index.
   * <p>
   * This method will return <code>null</code> if there is no element at the
   * given index.
   *
   * @param index The index.
   * @return The numeric value.
   * @see #setNumberElement(int, Number)
   */
  public Number getNumberElement(int index);
  
  /**
   * Set the element at the given index to the given numeric value.
   *
   * @param index The index.
   * @param value The numeric value.
   * @see #getNumberElement(int)
   */
  public void setNumberElement(int index, Number value);
  
  /**
   * Insert the given numeric value at the given index.
   *
   * @param index The index.
   * @param value The numeric value.
   */
  public void insertNumberElement(int index, Number value);

  /**
   * Determine if the element at the given index has a string value.
   * <p>
   * This method will return <code>false</code> if the element at the given
   * index is <code>null</code>.
   *
   * @param index The index.
   * @return <code>true</code> if element has a string value.
   */
  public boolean isStringElement(int index);

  /**
   * Retrieve the string value of the element at the given index.
   * <p>
   * This method will return <code>null</code> if there is no element at the
   * given index.
   *
   * @param index The index.
   * @return The string value.
   * @see #setStringElement(int, String)
   */
  public String getStringElement(int index);
  
  /**
   * Set the element at the given index to the given string value.
   *
   * @param index The index.
   * @param value The string value.
   * @see #getStringElement(int)
   */
  public void setStringElement(int index, String value);
  
  /**
   * Insert the given string value at the given index.
   *
   * @param index The index.
   * @param value The string value.
   */
  public void insertStringElement(int index, String value);

  /**
   * Determine if the element at the given index has an object value.
   * <p>
   * This method will return <code>false</code> if the element at the given
   * index is <code>null</code>.
   *
   * @param index The index.
   * @return <code>true</code> if element has an object value.
   */
  public boolean isObjectElement(int index);

  /**
   * Retrieve the object value of the element at the given index.
   * <p>
   * This method will return <code>null</code> if there is no element at the
   * given index.
   *
   * @param index The index.
   * @return The object value.
   * @see #setObjectElement(int, JsonObject)
   */
  public JsonObject getObjectElement(int index);
  
  /**
   * Retrieve the object value of the element at the given index.
   * <p>
   * This method will return the object value as a instance of the given class.
   * <p>
   * This method will return <code>null</code> if there is no element at the
   * given index.
   *
   * @param <T> The type of the object value.
   * @param index The index.
   * @param jsonObjectClass The class of the object value.
   * @return The object value.
   * @see #setObjectElement(int, JsonObject)
   */
  public <T extends JsonObject> T getObjectElement(int index, Class<T> jsonObjectClass);
  
  /**
   * Set the element at the given index to the given object value.
   *
   * @param index The index.
   * @param value The object value.
   * @see #getObjectElement(int)
   */
  public void setObjectElement(int index, JsonObject value);

  /**
   * Insert the given object value at the given index.
   *
   * @param index The index.
   * @param value The object value.
   */
  public void insertObjectElement(int index, JsonObject value);
  
  /**
   * Remove the given number of elements at the given index.
   *
   * @param index The index.
   * @param count The number of elements.
   */
  public void removeElements(int index, int count);
}
