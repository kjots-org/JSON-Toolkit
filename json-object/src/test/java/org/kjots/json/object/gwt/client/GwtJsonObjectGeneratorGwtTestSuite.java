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

import com.google.gwt.junit.tools.GWTTestSuite;

/**
 * GWT JSON Object Generator GWT Test Suite.
 * <p>
 * Created: 9th February 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.1
 */
public class GwtJsonObjectGeneratorGwtTestSuite extends GWTTestSuite {
  /**
   * Create the test suite.
   *
   * @return The test suite.
   */
  public static Test suite() {
    GwtJsonObjectGeneratorGwtTestSuite suite = new GwtJsonObjectGeneratorGwtTestSuite();
    
    suite.addTestSuite(GwtJsonObjectGeneratorGwtTest.class);
    
    suite.addTestSuite(GwtJsonObjectGeneratorPrimitiveGwtTest.class);
    suite.addTestSuite(GwtJsonObjectGeneratorBooleanGwtTest.class);
    suite.addTestSuite(GwtJsonObjectGeneratorNumberGwtTest.class);
    suite.addTestSuite(GwtJsonObjectGeneratorStringGwtTest.class);
    suite.addTestSuite(GwtJsonObjectGeneratorObjectGwtTest.class);
    
    suite.addTestSuite(GwtJsonObjectGeneratorFunctionGwtTest.class);
    suite.addTestSuite(GwtJsonObjectGeneratorExceptionGwtTest.class);
    
    return suite;
  }
  
  /**
   * Construct a new GWT JSON Object Generator GWT Test Suite.
   * <p>
   * This constructor is declared <code>private</code> to prevent external
   * instantiation.
   */
  private GwtJsonObjectGeneratorGwtTestSuite() {
    super("GWT Json Object Generator GWT Test Suite");
  }
}
