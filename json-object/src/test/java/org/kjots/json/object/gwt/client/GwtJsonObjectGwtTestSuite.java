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
package org.kjots.json.object.gwt.client;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.kjots.json.object.gwt.client.impl.GwtJsonObjectImplGwtTestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

/**
 * GWT JSON Object Test GWT Suite.
 * <p>
 * Created: 20th February 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.1
 */
public class GwtJsonObjectGwtTestSuite extends GWTTestSuite {
  /**
   * Create the test suite.
   *
   * @return The test suite.
   */
  public static Test suite() {
    GwtJsonObjectGwtTestSuite suite = new GwtJsonObjectGwtTestSuite();
    
    addTestSuites(suite);
    
    return suite;
  }
  
  /**
   * Add the test suites.
   *
   * @param suite The test suite.
   */
  public static void addTestSuites(TestSuite suite) {
    GwtJsonObjectGeneratorGwtTestSuite.addTestSuites(suite);
    GwtJsonObjectImplGwtTestSuite.addTestSuites(suite);
  }
  
  /**
   * Construct a new GWT JSON Object GWT Test Suite.
   * <p>
   * This constructor is declared <code>private</code> to prevent external
   * instantiation.
   */
  private GwtJsonObjectGwtTestSuite() {
    super("GWT Json Object GWT Test Suite");
  }
}
