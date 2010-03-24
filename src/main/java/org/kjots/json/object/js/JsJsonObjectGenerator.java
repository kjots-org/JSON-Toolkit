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
package org.kjots.json.object.js;

import org.kjots.json.object.JsonObjectGeneratorBase;
import org.kjots.json.object.js.impl.JsJsonObjectImpl;

/**
 * JavaScript JSON Object Generator.
 * <p>
 * Created: 14th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonObjectGenerator extends JsonObjectGeneratorBase<JsJsonObjectImpl> {
  /**
   * Construct a new JavaScript JSON Object Generator.
   */
  public JsJsonObjectGenerator() {
    super(JsJsonObjectImpl.class);
  }
}
