/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.gwt.client;

import org.kjots.json.object.shared.JsonObjectGeneratorNumberTestBase;

/**
 * GWT JSON Object Generator Number Test.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class GwtJsonObjectGeneratorNumberTest extends GwtJsonObjectTestBase {
  /**
   * GWT JSON Object Generator Test Number Delegate.
   */
  public static class GwtJsonObjectGeneratorNumberTestDelegate extends JsonObjectGeneratorNumberTestBase {
  }
  
  /** The GWT JSON object generator number test delegate. */
  private final GwtJsonObjectGeneratorNumberTestDelegate gwtJsonObjectGeneratorNumberTestDelegate = new GwtJsonObjectGeneratorNumberTestDelegate();

  /**
   * @see JsonObjectGeneratorNumberTestBase#testGetNumberProperty()
   */
  public void testGetNumberProperty() {
    this.gwtJsonObjectGeneratorNumberTestDelegate.testGetNumberProperty();
  }

  /**
   * @see JsonObjectGeneratorNumberTestBase#testSetNumberProperty()
   */
  public void testSetNumberProperty() {
    this.gwtJsonObjectGeneratorNumberTestDelegate.testSetNumberProperty();
  }

  /**
   * @see JsonObjectGeneratorNumberTestBase#testGetAdaptedNumberProperty()
   */
  public void testGetAdaptedNumberProperty() {
    this.gwtJsonObjectGeneratorNumberTestDelegate.testGetAdaptedNumberProperty();
  }

  /**
   * @see JsonObjectGeneratorNumberTestBase#testSetAdaptedNumberProperty()
   */
  public void testSetAdaptedNumberProperty() {
    this.gwtJsonObjectGeneratorNumberTestDelegate.testSetAdaptedNumberProperty();
  }

  /**
   * @see JsonObjectGeneratorNumberTestBase#testGetNumberArrayProperty()
   */
  public void testGetNumberArrayProperty() {
    this.gwtJsonObjectGeneratorNumberTestDelegate.testGetNumberArrayProperty();
  }

  /**
   * @see JsonObjectGeneratorNumberTestBase#testSetNumberArrayProperty()
   */
  public void testSetNumberArrayProperty() {
    this.gwtJsonObjectGeneratorNumberTestDelegate.testSetNumberArrayProperty();
  }

  /**
   * @see JsonObjectGeneratorNumberTestBase#testGetNumberMapProperty()
   */
  public void testGetNumberMapProperty() {
    this.gwtJsonObjectGeneratorNumberTestDelegate.testGetNumberMapProperty();
  }

  /**
   * @see JsonObjectGeneratorNumberTestBase#testSetNumberMapProperty()
   */
  public void testSetNumberMapProperty() {
    this.gwtJsonObjectGeneratorNumberTestDelegate.testSetNumberMapProperty();
  }
}
