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
 * JSON Content Handler.
 * <p>
 * This interface defines a handler for JSON content, similar in nature to the
 * SAX {@link org.xml.sax.ContentHandler}.  The methods of this interface
 * represent <em>events</em> that correspond to the content of a JSON data
 * structure.
 * <p>
 * It is the responsibility of the consumer of this interface (i.e. the
 * generator of JSON content events) to ensure that the methods of this
 * interface are invoked consistently; that is, there is no requirement for an
 * implementation of this interface to verify that a particular method is
 * invoked in the correct context.
 * <p>
 * Created: 28th January 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public interface JsonContentHandler {
  /**
   * Handle the start of the JSON content.
   * <p>
   * Following the invocation of this method, the content handler will be in
   * the root context.
   */
  public void startJson();
  
  /**
   * Handle the end of the JSON content.
   * <p>
   * This method will only be invoked in the root context and will mark the
   * closing of that context, and thus the end of the JSON content.
   */
  public void endJson();
  
  /**
   * Handle the start of a JSON object.
   * <p>
   * Following the invocation of this method, the content handler will be in an
   * object context.
   */
  public void startObject();
  
  /**
   * Handle the end of a JSON object.
   * <p>
   * This method will only be invoked in an object context and will mark the
   * closing of that object context.
   */
  public void endObject();
  
  /**
   * Handle the start of a JSON array.
   * <p>
   * Following the invocation of this method, the content handler will be in an
   * array context.
   */
  public void startArray();
  
  /**
   * Handle the end of a JSON array.
   * <p>
   * This method will only be invoked in an array context and will mark the
   * closing of that context.
   */
  public void endArray();
  
  /**
   * Handle the name of a member of a JSON object.
   * <p>
   * This method will only be invoked in an object context.
   *
   * @param name The name of the member.
   */
  public void memberName(String name);
  
  /**
   * Handle a JSON primitive.
   * <p>
   * The type of the given <code>value</code> parameter will be, depending on
   * the type of the JSON primitive: 
   * <ul>
   * <li>
   * <dl>
   * <dt><li><code>null</code></li></dt>
   * <dd>
   * <code>null</code>
   * </dd>
   * </dl>
   * </li>
   * <li>
   * <dl>
   * <dt><li><code>boolean</code></li></dt>
   * <dd>
   * {@link java.lang.Boolean}
   * </dd>
   * </dl>
   * </li>
   * <li>
   * <dl>
   * <dt><li><code>number</code></li></dt>
   * <dd>
   * {@link java.lang.Integer} for integer values between <code>-2<sup>31</sup></code> and <code>2<sup>31</sup>-1</code> inclusive, or<br>
   * {@link java.lang.Long} for all other integer values between <code>-2<sup>63</sup></code> and <code>2<sup>63</sup>-1</code> inclusive, or<br>
   * {@link java.math.BigInteger} for all other integer values, or<br>
   * {@link java.math.BigDecimal} for all non-integer values<br>
   * </dd>
   * </dl>
   * </li>
   * <li>
   * <dl>
   * <dt><li><code>string</code></li></dt>
   * <dd>
   * {@link java.lang.String}
   * </dd>
   * </dl>
   * </li>
   * </ul>
   * <p>
   * This method will only be invoked in an object or an array context.  In an
   * object context, this method will be immediately preceded by an invocation
   * of {@link #memberName(String)}.
   *
   * @param value The value of the JSON primitive.
   */
  public void primitive(Object value);
}
