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
 * JSON Object Property Adapter.
 * <p>
 * Created: 13th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public interface JsonObjectPropertyAdapter<T, J extends JsonObject> extends JsonPropertyAdapter {
  /**
   * Convert to a JSON object property value.
   *
   * @param t The value.
   * @return The JSON object property value.
   * @see #fromJsonProperty(JsonObject)
   */
  public J toJsonProperty(T t);
  
  /**
   * Convert from a JSON object property value.
   *
   * @param propertyValue The JSON object property value.
   * @return The value.
   * @see #toJsonProperty(Object)
   */
  public T fromJsonProperty(J propertyValue);
}
