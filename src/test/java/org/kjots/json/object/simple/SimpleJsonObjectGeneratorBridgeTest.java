/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.simple;

import org.junit.Before;
import org.kjots.json.object.JvmJsonObjectGeneratorBridgeTestBase;

import com.google.inject.Guice;

/**
 * Simple JSON Object Generator Bridge Test.
 * <p>
 * Created: 6th April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class SimpleJsonObjectGeneratorBridgeTest extends JvmJsonObjectGeneratorBridgeTestBase {
  /**
   * Set up the JSON object implementation test.
   */
  @Before
  public void setUp() {
    Guice.createInjector(new SimpleJsonObjectModule());
  }
}
