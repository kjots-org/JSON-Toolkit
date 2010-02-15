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
 * JSON Boolean Map.
 * <p>
 * Created: 9th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public interface JsonBooleanMap extends JsonObject {
  /**
   * Retrieve the boolean value of the element with the given key.
   *
   * @param key The key.
   * @return The boolean value.
   * @see #set(String, boolean)
   */
  public boolean get(String key);
  
  /**
   * Set the element with the given key to the given boolean value.
   *
   * @param key The key.
   * @param value The boolean value.
   * @see #get(String)
   */
  public void set(String key, boolean value);
}
