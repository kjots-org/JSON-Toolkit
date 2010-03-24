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
package org.kjots.json.object.simple;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Simple JSON Value.
 * <p>
 * Created: 4th March 2010
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public final class SimpleJsonValue {
  /** The map. */
  private final Map<String, Object> map;
  
  /** The list. */
  private final List<Object> list;
  
  /**
   * Create a new simple JSON object.
   *
   * @return The new simple JSON object.
   */
  public static SimpleJsonValue createObject() {
    return new SimpleJsonValue(new HashMap<String, Object>(), null);
  }
  
  /**
   * Create a new simple JSON array.
   *
   * @return The new simple JSON array.
   */
  public static SimpleJsonValue createArray() {
    return new SimpleJsonValue(new HashMap<String, Object>(), new LinkedList<Object>());
  }
  
  /**
   * Retrieve the map.
   *
   * @return The map.
   */
  public Map<String, Object> getMap() {
    return this.map;
  }

  /**
   * Retrieve the list.
   *
   * @return The list.
   */
  public List<Object> getList() {
    return this.list;
  }

  /**
   * Determine if this object is equal to the given object.
   *
   * @param object The object.
   * @return TRUE if this object is equal to the given object.
   */
  @Override
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }
    else if (object instanceof SimpleJsonValue) {
      SimpleJsonValue that = (SimpleJsonValue)object;
      
      return this.list != null ? this.list.equals(that.list) : this.map.equals(that.map);
    }
    else {
      return false;
    }
  }
  
  /**
   * Calculate the hash code for this object.
   *
   * @return The hash code for this object.
   */
  @Override
  public int hashCode() {
    return this.list != null ? this.list.hashCode() : this.map.hashCode();
  }
  
  /**
   * Create a string representation of this object.
   *
   * @return The string representation of this object.
   */
  @Override
  public String toString() {
    return this.list != null ? this.list.toString() : this.map.toString();
  }
  
  /**
   * Construct a new Simple JSON Value.
   * <p>
   * This constructor is declared <code>private</code> to prevent external
   * instantiation.
   * 
   * @param map The map.
   * @param list The list.
   */
  private SimpleJsonValue(Map<String, Object> map, List<Object> list) {
    this.map = map;
    this.list = list;
  }
}
