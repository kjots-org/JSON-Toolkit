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

import org.kjots.json.object.shared.JsonBooleanArray;

/**
 * Native JSON Boolean Array.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class NativeJsonBooleanArray extends NativeJsonArray implements JsonBooleanArray {
  /**
   * Retrieve the boolean value of the element at the given index.
   *
   * @param index The index.
   * @return The boolean value.
   * @see #set(int, Boolean)
   */
  @Override
  public final Boolean get(int index) {
    return this.getBooleanElement(index);
  }

  /**
   * Set the element at the given index to the given boolean value.
   *
   * @param index The index.
   * @param value The boolean value.
   * @see #get(int)
   */
  @Override
  public final void set(int index, Boolean value) {
    this.setBooleanElement(index, value);
  }

  /**
   * Insert the given boolean value at the given index.
   *
   * @param index The index.
   * @param value The boolean value.
   */
  @Override
  public final void insert(int index, Boolean value) {
    this.insertBooleanElement(index, value);
  }

  /**
   * Prepend the given boolean value.
   *
   * @param value The boolean value.
   */
  @Override
  public final void prepend(Boolean value) {
    this.prependBooleanElement(value);
  }
  
  /**
   * Append the given boolean value.
   *
   * @param value The boolean value.
   */
  @Override
  public final void append(Boolean value) {
    this.appendBooleanElement(value);
  }

  /**
   * Retrieve an iterator for the array.
   *
   * @return The iterator.
   */
  @Override
  public final java.util.Iterator<Boolean> iterator() {
    return new Iterator(this);
  }
}
