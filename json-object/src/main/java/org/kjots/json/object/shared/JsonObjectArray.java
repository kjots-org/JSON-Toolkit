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
 * JSON Object Array.
 * <p>
 * Created: 8th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public interface JsonObjectArray<T extends JsonObject> extends JsonArray, Iterable<T> {
  /**
   * JSON Object Array Iterator.
   * <p>
   * Created: 15th June 2010.
   * 
   * @since 1.0
   */
  public class Iterator<T extends JsonObject> implements java.util.Iterator<T> {
    /** The array.*/
    private final JsonObjectArray<T> array;
    
    /** The next index. */
    private int nextIndex;
    
    /**
     * Construct a new JSON Object Array Iterator.
     *
     * @param array The array.
     */
    public Iterator(JsonObjectArray<T> array) {
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
    public T next() {
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
   * Cast the JSON object array to a JSON object array with the given element
   * type.
   * 
   * @param <E> The type of the element.
   * @param elementClass The class of the element.
   */
  public <E extends JsonObject> JsonObjectArray<E> castElement(Class<E> elementClass);
  
  /**
   * Retrieve the object value of the element at the given index.
   *
   * @param index The index.
   * @return The object value.
   * @see #set(int, JsonObject)
   */
  public T get(int index);
  
  /**
   * Set the element at the given index to the given object value.
   *
   * @param index The index.
   * @param value The object value.
   * @see #get(int)
   */
  public void set(int index, T value);
  
  /**
   * Insert the given object value at the given index.
   *
   * @param index The index.
   * @param value The object value.
   */
  public void insert(int index, T value);
}
