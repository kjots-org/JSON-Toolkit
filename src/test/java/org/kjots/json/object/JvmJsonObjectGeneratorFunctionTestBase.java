/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;

import org.kjots.json.object.shared.JsonObjectGeneratorFunctionTestBase;

/**
 * JVM JSON Object Generator Function Test Base.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public abstract class JvmJsonObjectGeneratorFunctionTestBase extends JsonObjectGeneratorFunctionTestBase {
  /**
   * Setup the test.
   */
  @Before
  public void setup() {
    this.setupFunctions(mock(Functions.class));
  }
  
  /**
   * Cleanup the test.
   */
  @After
  public void cleanup() {
    this.cleanupFunctions();
  }
  
  /**
   * Verify the invocation of the no-argument no-return-value test JSON
   * function.
   *
   * @param testJsonObject The test JSON object.
   */
  @Override
  protected void verifyInvokeJsonFunction(TestJsonObject testJsonObject) {
    verify(functions()).testJsonFunction(testJsonObject);
  }
}
