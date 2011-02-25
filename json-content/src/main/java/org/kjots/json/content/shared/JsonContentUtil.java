/* 
 * Copyright © 2011 Karl J. Ots <kjots@kjots.org>
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

import java.util.List;
import java.util.Map;

/**
 * JSON Content Utility.
 * <p>
 * Created: 25th February 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.1
 */
public class JsonContentUtil {
  /**
   * Handle the given value with the given JSON content handler as a complete
   * JSON stream.
   * <p>
   * This method is equivalent to:
   * <pre>
   *   {@link #handle(JsonContentHandler, Object, boolean) handle}(<code>jsonContentHandler</code>, <code>value</code>, <code>false</code>)
   * </pre>
   *
   * @param jsonContentHandler The JSON content handler.
   * @param value The value.
   */
  public static void handle(JsonContentHandler jsonContentHandler, Object value) {
    handle(jsonContentHandler, value, false);
  }
  
  /**
   * Handle the given value with the given JSON content handler.
   * <p>
   * This method can handle objects of the given types:
   * <ul>
   * <li>{@link Map}&lt;{@link String}, ?&gt; - Handled as a JSON object via
   * {@link #handle(JsonContentHandler, Map, boolean)}.</li>
   * <li>{@link List}&lt;?&gt; - Handled as a JSON array via {@link #handle(JsonContentHandler, List, boolean)}.</li>
   * <li><code>null</code>, {@link Boolean}, {@link Number}, {@link String} - 
   * Handled as a JSON primitive (if <code>fragment</code> is <code>true</code>).</li>
   * </ul>
   * In all other cases, this method will throw an {@link IllegalArgumentException}.
   * <p>
   * If the given <code>fragment</code> parameter is <code>true</code>, this
   * method will <em>not</em> generate a complete JSON content stream (i.e. the
   * methods {@link JsonContentHandler#startJson()} and {@link JsonContentHandler#endJson()}
   * will <em>not</em> be invoked).
   * 
   * @param jsonContentHandler The JSON content handler.
   * @param value The value.
   * @param fragment The fragment flag.
   */
  @SuppressWarnings("unchecked")
  public static void handle(JsonContentHandler jsonContentHandler, Object value, boolean fragment) {
    if (value instanceof Map<?, ?>) {
      handle(jsonContentHandler, (Map<String, ?>)value, fragment);
    }
    else if (value instanceof List<?>) {
      handle(jsonContentHandler, (List<?>)value, fragment);
    }
    else if (fragment && (value == null || value instanceof Boolean || value instanceof Number || value instanceof String)) {
      jsonContentHandler.primitive(value);
    }
    else {
      throw new IllegalArgumentException("Unsupported type: " + (value != null ? value.getClass().getName() : null));
    }
  }

  /**
   * Handle the given map with the given JSON content handler as a complete
   * JSON stream.
   * <p>
   * This method is equivalent to:
   * <pre>
   *   {@link #handle(JsonContentHandler, Map, boolean) handle}(<code>jsonContentHandler</code>, <code>map</code>, <code>false</code>)
   * </pre>
   *
   * @param jsonContentHandler The JSON content handler.
   * @param map The map.
   */
  public static void handle(JsonContentHandler jsonContentHandler, Map<String, ?> map) {
    handle(jsonContentHandler, map, false);
  }

  /**
   * Handle the given map with the given JSON content handler.
   * <p>
   * This method will handle the given map as a JSON object, with the key of
   * each entry handled as the member name and the value handled via
   * {@link #handle(JsonContentHandler, Object, boolean)}.
   * <p>
   * If the given <code>fragment</code> parameter is <code>true</code>, this
   * method will <em>not</em> generate a complete JSON content stream (i.e. the
   * methods {@link JsonContentHandler#startJson()} and {@link JsonContentHandler#endJson()}
   * will <em>not</em> be invoked).
   *
   * @param jsonContentHandler The JSON content handler.
   * @param map The map.
   * @param fragment The fragment flag.
   */
  public static void handle(JsonContentHandler jsonContentHandler, Map<String, ?> map, boolean fragment) {
    if (!fragment) {
      jsonContentHandler.startJson();
    }
    
    jsonContentHandler.startObject();
    
    for (Map.Entry<String, ?> entry : map.entrySet()) {
      // Invoking toString() on key to trigger potential NullPointerException.
      jsonContentHandler.memberName(entry.getKey().toString());
      
      handle(jsonContentHandler, entry.getValue(), true);
    }
    
    jsonContentHandler.endObject();
    
    if (!fragment) {
      jsonContentHandler.endJson();
    }
  }
  
  /**
   * Handle the given list with the given JSON content handler as a complete
   * JSON stream.
   * <p>
   * This method is equivalent to:
   * <pre>
   *   {@link #handle(JsonContentHandler, List, boolean) handle}(<code>jsonContentHandler</code>, <code>list</code>, <code>false</code>)
   * </pre>
   *
   * @param jsonContentHandler The JSON content handler.
   * @param list The list.
   */
  public static void handle(JsonContentHandler jsonContentHandler, List<?> list) {
    handle(jsonContentHandler, list, false);
  }
  
  /**
   * Handle the given list with the given JSON content handler.
   * <p>
   * This method will handle the given list as a JSON array, with each value 
   * handled via {@link #handle(JsonContentHandler, Object, boolean)}.
   * <p>
   * If the given <code>fragment</code> parameter is <code>true</code>, this
   * method will <em>not</em> generate a complete JSON content stream (i.e. the
   * methods {@link JsonContentHandler#startJson()} and {@link JsonContentHandler#endJson()}
   * will <em>not</em> be invoked).
   *
   * @param jsonContentHandler The JSON content handler.
   * @param list The list.
   * @param fragment The fragment flag.
   */
  public static void handle(JsonContentHandler jsonContentHandler, List<?> list, boolean fragment) {
    if (!fragment) {
      jsonContentHandler.startJson();
    }
    
    jsonContentHandler.startArray();
    
    for (Object value : list) {
      handle(jsonContentHandler, value, true);
    }
    
    jsonContentHandler.endArray();
    
    if (!fragment) {
      jsonContentHandler.endJson();
    }
  }
}
