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

import java.util.NoSuchElementException;

/**
 * JSON Number Array.
 * <p>
 * Created: 7th November 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public interface JsonNumberArray extends JsonArray, Iterable<Number> {
  /**
   * JSON Number Array Iterator.
   * <p>
   * Created: 15th June 2010.
   * 
   * @since 1.0
   */
  public class Iterator implements java.util.Iterator<Number> {
    /** The array.*/
    private final JsonNumberArray array;
    
    /** The next index. */
    private int nextIndex;
    
    /**
     * Construct a new JSON Number Array Iterator.
     *
     * @param array The array.
     */
    public Iterator(JsonNumberArray array) {
      this.array = array;
    }
    
    /**
     * Determine if the iterator has more elements.
     *
     * @return <code>true</code> if the iterator has more elements.
     */
    @Override
    public boolean hasNext() {
      return this.nextIndex < this.array.getLength();
    }

    /**
     * Retrieve the next element.
     *
     * @return The next element.
     */
    @Override
    public Number next() {
      if (this.nextIndex < this.array.getLength()) {
        return this.array.get(this.nextIndex++);
      }
      
      throw new NoSuchElementException();
    }
    
    /**
     * Remove the current element.
     */
    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
  /**
   * Retrieve the numeric value of the element at the given index.
   *
   * @param index The index.
   * @return The numeric value.
   * @see #set(int, Number)
   */
  public Number get(int index);
  
  /**
   * Set the element at the given index to the given numeric value.
   *
   * @param index The index.
   * @param value The numeric value.
   * @see #get(int)
   */
  public void set(int index, Number value);
  
  /**
   * Insert the given numeric value at the given index.
   *
   * @param index The index.
   * @param value The numeric value.
   */
  public void insert(int index, Number value);
}
