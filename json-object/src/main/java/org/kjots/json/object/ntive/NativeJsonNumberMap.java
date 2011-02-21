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

import org.kjots.json.object.shared.JsonNumberMap;

/**
 * Native JSON Number Map.
 * <p>
 * Created: 11th November 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class NativeJsonNumberMap extends NativeJsonMap implements JsonNumberMap {
  /**
   * Retrieve the numeric value of the element with the given key.
   *
   * @param key The key.
   * @return The numeric value.
   * @see #set(String, Number)
   */
  @Override
  public Number get(String key) {
    return this.getNumberProperty(key);
  }

  /**
   * Set the element with the given key to the given numeric value.
   *
   * @param key The key.
   * @param value The numeric value.
   * @see #get(String)
   */
  @Override
  public void set(String key, Number value) {
    this.setNumberProperty(key, value);
  }
}
