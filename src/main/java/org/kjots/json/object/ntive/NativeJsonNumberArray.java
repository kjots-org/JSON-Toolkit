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

import org.kjots.json.object.shared.JsonNumberArray;

/**
 * Native JSON Number Array.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class NativeJsonNumberArray extends NativeJsonArray implements JsonNumberArray {
  /**
   * Retrieve the number value of the element at the given index.
   *
   * @param index The index.
   * @return The number value.
   * @see #set(int, Number)
   */
  @Override
  public final Number get(int index) {
    return this.getNumberElement(index);
  }

  /**
   * Set the element at the given index to the given number value.
   *
   * @param index The index.
   * @param value The number value.
   * @see #get(int)
   */
  @Override
  public final void set(int index, Number value) {
    this.setNumberElement(index, value);
  }

  /**
   * Insert the given number value at the given index.
   *
   * @param index The index.
   * @param value The number value.
   */
  @Override
  public final void insert(int index, Number value) {
    this.insertNumberElement(index, value);
  }

  /**
   * Retrieve an iterator for the array.
   *
   * @return The iterator.
   */
  @Override
  public final java.util.Iterator<Number> iterator() {
    return new Iterator(this);
  }
}
