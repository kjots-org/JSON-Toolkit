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
package org.kjots.json.object.gwt.client;

import org.kjots.json.object.shared.JsonObjectGeneratorTestBase;

/**
 * GWT JSON Object Generator Test.
 * <p>
 * Created: 6th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonObjectGeneratorTest extends GwtJsonObjectTestBase {
  /**
   * GWT JSON Object Generator Test Delegate.
   * <p>
   * Created: 11th December 2009.
   */
  public static class GwtJsonObjectGeneratorTestDelegate extends JsonObjectGeneratorTestBase {
  }
  
  /** The GWT JSON object generator test delegate. */
  private final GwtJsonObjectGeneratorTestDelegate gwtJsonObjectGeneratorTestDelegate = new GwtJsonObjectGeneratorTestDelegate();

  /**
   * @see JsonObjectGeneratorTestBase#testCreateJsonObject()
   */
  public void testCreateJsonObject() {
    this.gwtJsonObjectGeneratorTestDelegate.testCreateJsonObject();
  }
  
  /**
   * @see JsonObjectGeneratorTestBase#testHasProperty()
   */
  public void testHasProperty() {
    this.gwtJsonObjectGeneratorTestDelegate.testHasProperty();
  }

  /**
   * @see JsonObjectGeneratorTestBase#testIsPropertyNull()
   */
  public void testIsPropertyNull() {
    this.gwtJsonObjectGeneratorTestDelegate.testIsPropertyNull();
  }

  /**
   * @see JsonObjectGeneratorTestBase#testDeleteProperty()
   */
  public void testDeleteProperty() {
    this.gwtJsonObjectGeneratorTestDelegate.testDeleteProperty();
  }
}
