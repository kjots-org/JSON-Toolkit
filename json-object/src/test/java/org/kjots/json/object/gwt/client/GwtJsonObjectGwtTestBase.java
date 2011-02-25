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

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import com.google.gwt.inject.client.GinModules;

/**
 * GWT JSON Object GWT Test Base.
 * <p>
 * Created: 11th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public abstract class GwtJsonObjectGwtTestBase extends GWTTestCase {
  /**
   * GWT JSON Object Test Ginjector.
   * <p>
   * Created: 29th March 2010.
   */
  @GinModules(JsonObjectGinModule.class)
  public static interface Ginjector extends com.google.gwt.inject.client.Ginjector {
  }
  
  /**
   * Retrieve the name of the GWT module.
   *
   * @return The name of the GWT module.
   */
  @Override
  public final String getModuleName() {
    return "org.kjots.json.object.JsonObjectTest";
  }
  
  /**
   * Set up the GWT test case.
   */
  @Override
  protected void gwtSetUp() {
    GWT.create(Ginjector.class);
  }
}
