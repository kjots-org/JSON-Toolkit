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
 * JSON Property Adapter.
 * <p>
 * Created: 13th December 2009.
 *
 * @param <Tp> The type of the JSON property.
 * @param <Tv> The type of the value.
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public interface JsonPropertyAdapter<Tp, Tv> {
  /**
   * Convert to a JSON property value.
   *
   * @param value The value.
   * @return The JSON property value.
   * @see #fromJsonProperty(Object)
   */
  public Tp toJsonProperty(Tv value);
  
  /**
   * Convert from a JSON property value.
   *
   * @param propertyValue The JSON property value.
   * @return The value.
   * @see #toJsonProperty(Object)
   */
  public Tv fromJsonProperty(Tp propertyValue);
}
