/* 
 * Copyright © 2010 Karl J. Ots <kjots@kjots.org>
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
package org.kjots.json.object.ntive;

import org.kjots.json.object.shared.JsonStringArray;

/**
 * Native JSON String Array.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class NativeJsonStringArray extends NativeJsonArray implements JsonStringArray {
  /**
   * Retrieve the string value of the element at the given index.
   *
   * @param index The index.
   * @return The string value.
   * @see #set(int, String)
   */
  @Override
  public final String get(int index) {
    return this.getStringElement(index);
  }

  /**
   * Set the element at the given index to the given string value.
   *
   * @param index The index.
   * @param value The string value.
   * @see #get(int)
   */
  @Override
  public final void set(int index, String value) {
    this.setStringElement(index, value);
  }

  /**
   * Insert the given string value at the given index.
   *
   * @param index The index.
   * @param value The string value.
   */
  @Override
  public final void insert(int index, String value) {
    this.insertStringElement(index, value);
  }

  /**
   * Prepend the given string value.
   *
   * @param value The string value.
   */
  @Override
  public final void prepend(String value) {
    this.prependStringElement(value);
  }
  
  /**
   * Append the given string value.
   *
   * @param value The string value.
   */
  @Override
  public final void append(String value) {
    this.appendStringElement(value);
  }

  /**
   * Retrieve an iterator for the array.
   *
   * @return The iterator.
   */
  @Override
  public final java.util.Iterator<String> iterator() {
    return new Iterator(this);
  }
}
