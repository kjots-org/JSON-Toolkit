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
package org.kjots.json.content.shared;

/**
 * JSON Content Generator.
 * <p>
 * Created: 15th February 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public interface JsonContentGenerator {
  /**
   * Retrieve the JSON content handler.
   *
   * @return The JSON content handler.
   * @see #setJsonContentHandler(JsonContentHandler)
   */
  public JsonContentHandler getJsonContentHandler();

  /**
   * Set the JSON content handler.
   *
   * @param jsonContentHandler The JSON content handler.
   * @see #getJsonContentHandler()
   */
  public void setJsonContentHandler(JsonContentHandler jsonContentHandler);
}