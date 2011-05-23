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
package org.kjots.json.object.gwt.client;

import org.kjots.json.object.shared.JsonObjectGeneratorExceptionTestBase;

/**
 * GWT JSON Object Generator Exception GWT Test.
 * <p>
 * Created: 26th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class GwtJsonObjectGeneratorExceptionGwtTest extends GwtJsonObjectGwtTestBase {
  /** The JSON object generator exception test delegate. */
  private final JsonObjectGeneratorExceptionTestBase jsonObjectGeneratorExceptionTestDelegate = new JsonObjectGeneratorExceptionTestBase() {
  };

  /**
   * @see JsonObjectGeneratorExceptionTestBase#testThrowException()
   */
  public void testThrowException() {
    this.jsonObjectGeneratorExceptionTestDelegate.testThrowException();
  }

  /**
   * @see JsonObjectGeneratorExceptionTestBase#testThrowExceptionWithMessage()
   */
  public void testThrowExceptionWithMessage() {
    this.jsonObjectGeneratorExceptionTestDelegate.testThrowExceptionWithMessage();
  }

  /**
   * @see JsonObjectGeneratorExceptionTestBase#testThrowExceptionFromMethodWithArguments()
   */
  public void testThrowExceptionFromMethodWithArguments() {
    this.jsonObjectGeneratorExceptionTestDelegate.testThrowExceptionFromMethodWithArguments();
  }

  /**
   * @see JsonObjectGeneratorExceptionTestBase#testThrowExceptionWithMessageFromMethodWithArguments()
   */
  public void testThrowExceptionWithMessageFromMethodWithArguments() {
    this.jsonObjectGeneratorExceptionTestDelegate.testThrowExceptionWithMessageFromMethodWithArguments();
  }
}
