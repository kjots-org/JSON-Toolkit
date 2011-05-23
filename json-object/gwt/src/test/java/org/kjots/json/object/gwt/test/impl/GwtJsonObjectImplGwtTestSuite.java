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
package org.kjots.json.object.gwt.test.impl;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.kjots.json.object.gwt.client.impl.GwtJsonArrayImplGwtTest;
import org.kjots.json.object.gwt.client.impl.GwtJsonBooleanArrayImplGwtTest;
import org.kjots.json.object.gwt.client.impl.GwtJsonBooleanMapImplGwtTest;
import org.kjots.json.object.gwt.client.impl.GwtJsonNumberArrayImplGwtTest;
import org.kjots.json.object.gwt.client.impl.GwtJsonNumberMapImplGwtTest;
import org.kjots.json.object.gwt.client.impl.GwtJsonObjectArrayImplGwtTest;
import org.kjots.json.object.gwt.client.impl.GwtJsonObjectImplGwtTest;
import org.kjots.json.object.gwt.client.impl.GwtJsonObjectMapImplGwtTest;
import org.kjots.json.object.gwt.client.impl.GwtJsonStringArrayImplGwtTest;
import org.kjots.json.object.gwt.client.impl.GwtJsonStringMapImplGwtTest;

/**
 * GWT JSON Object Implementation GWT Test Suite.
 * <p>
 * Created: 9th February 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.1
 */
public class GwtJsonObjectImplGwtTestSuite extends GWTTestSuite {
  /**
   * Create the test suite.
   *
   * @return The test suite.
   */
  public static Test suite() {
    GwtJsonObjectImplGwtTestSuite suite = new GwtJsonObjectImplGwtTestSuite();
    
    addTestSuites(suite);
    
    return suite;
  }
  
  /**
   * Add the test suites.
   *
   * @param suite The test suite.
   */
  public static void addTestSuites(TestSuite suite) {
    suite.addTestSuite(GwtJsonObjectImplGwtTest.class);
    suite.addTestSuite(GwtJsonArrayImplGwtTest.class);
    
    suite.addTestSuite(GwtJsonBooleanArrayImplGwtTest.class);
    suite.addTestSuite(GwtJsonNumberArrayImplGwtTest.class);
    suite.addTestSuite(GwtJsonStringArrayImplGwtTest.class);
    suite.addTestSuite(GwtJsonObjectArrayImplGwtTest.class);
    
    suite.addTestSuite(GwtJsonBooleanMapImplGwtTest.class);
    suite.addTestSuite(GwtJsonNumberMapImplGwtTest.class);
    suite.addTestSuite(GwtJsonStringMapImplGwtTest.class);
    suite.addTestSuite(GwtJsonObjectMapImplGwtTest.class);
  }
  
  /**
   * Construct a new GWT JSON Object Implementation GWT Test Suite.
   * <p>
   * This constructor is declared <code>private</code> to prevent external
   * instantiation.
   */
  private GwtJsonObjectImplGwtTestSuite() {
    super("GWT Json Object Implementation GWT Test Suite");
  }
}
