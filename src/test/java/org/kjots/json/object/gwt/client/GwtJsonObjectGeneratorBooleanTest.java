/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.gwt.client;

import org.kjots.json.object.shared.JsonObjectGeneratorBooleanTestBase;

/**
 * GWT JSON Object Generator Boolean Test.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class GwtJsonObjectGeneratorBooleanTest extends GwtJsonObjectTestBase {
  /**
   * GWT JSON Object Generator Test Boolean Delegate.
   */
  public class GwtJsonObjectGeneratorBooleanTestDelegate extends JsonObjectGeneratorBooleanTestBase {
  }
  
  /** The GWT JSON object generator boolean test delegate. */
  private final GwtJsonObjectGeneratorBooleanTestDelegate gwtJsonObjectGeneratorBooleanTestDelegate = new GwtJsonObjectGeneratorBooleanTestDelegate();

  /**
   * @see JsonObjectGeneratorBooleanTestBase#testGetBooleanProperty()
   */
  public void testGetBooleanProperty() {
    this.gwtJsonObjectGeneratorBooleanTestDelegate.testGetBooleanProperty();
  }

  /**
   * @see JsonObjectGeneratorBooleanTestBase#testSetBooleanProperty()
   */
  public void testSetBooleanProperty() {
    this.gwtJsonObjectGeneratorBooleanTestDelegate.testSetBooleanProperty();
  }

  /**
   * @see JsonObjectGeneratorBooleanTestBase#testGetAdaptedBooleanProperty()
   */
  public void testGetAdaptedBooleanProperty() {
    this.gwtJsonObjectGeneratorBooleanTestDelegate.testGetAdaptedBooleanProperty();
  }

  /**
   * @see JsonObjectGeneratorBooleanTestBase#testSetAdaptedBooleanProperty()
   */
  public void testSetAdaptedBooleanProperty() {
    this.gwtJsonObjectGeneratorBooleanTestDelegate.testSetAdaptedBooleanProperty();
  }

  /**
   * @see JsonObjectGeneratorBooleanTestBase#testGetBooleanArrayProperty()
   */
  public void testGetBooleanArrayProperty() {
    this.gwtJsonObjectGeneratorBooleanTestDelegate.testGetBooleanArrayProperty();
  }

  /**
   * @see JsonObjectGeneratorBooleanTestBase#testSetBooleanArrayProperty()
   */
  public void testSetBooleanArrayProperty() {
    this.gwtJsonObjectGeneratorBooleanTestDelegate.testSetBooleanArrayProperty();
  }

  /**
   * @see JsonObjectGeneratorBooleanTestBase#testGetBooleanMapProperty()
   */
  public void testGetBooleanMapProperty() {
    this.gwtJsonObjectGeneratorBooleanTestDelegate.testGetBooleanMapProperty();
  }

  /**
   * @see JsonObjectGeneratorBooleanTestBase#testSetBooleanMapProperty()
   */
  public void testSetBooleanMapProperty() {
    this.gwtJsonObjectGeneratorBooleanTestDelegate.testSetBooleanMapProperty();
  }
}
