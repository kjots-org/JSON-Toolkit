/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.gwt.client;

import org.kjots.json.object.shared.JsonObjectGeneratorStringTestBase;

/**
 * GWT JSON Object Generator String Test.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class GwtJsonObjectGeneratorStringTest extends GwtJsonObjectTestBase {
  /**
   * GWT JSON Object Generator Test String Delegate.
   */
  public static class GwtJsonObjectGeneratorStringTestDelegate extends JsonObjectGeneratorStringTestBase {
  }
  
  /** The GWT JSON object generator string test delegate. */
  private final GwtJsonObjectGeneratorStringTestDelegate gwtJsonObjectGeneratorStringTestDelegate = new GwtJsonObjectGeneratorStringTestDelegate();

  /**
   * @see JsonObjectGeneratorStringTestBase#testGetStringProperty()
   */
  public void testGetStringProperty() {
    this.gwtJsonObjectGeneratorStringTestDelegate.testGetStringProperty();
  }

  /**
   * @see JsonObjectGeneratorStringTestBase#testSetStringProperty()
   */
  public void testSetStringProperty() {
    this.gwtJsonObjectGeneratorStringTestDelegate.testSetStringProperty();
  }

  /**
   * @see JsonObjectGeneratorStringTestBase#testGetAdaptedStringProperty()
   */
  public void testGetAdaptedStringProperty() {
    this.gwtJsonObjectGeneratorStringTestDelegate.testGetAdaptedStringProperty();
  }

  /**
   * @see JsonObjectGeneratorStringTestBase#testSetAdaptedStringProperty()
   */
  public void testSetAdaptedStringProperty() {
    this.gwtJsonObjectGeneratorStringTestDelegate.testSetAdaptedStringProperty();
  }

  /**
   * @see JsonObjectGeneratorStringTestBase#testGetStringArrayProperty()
   */
  public void testGetStringArrayProperty() {
    this.gwtJsonObjectGeneratorStringTestDelegate.testGetStringArrayProperty();
  }

  /**
   * @see JsonObjectGeneratorStringTestBase#testSetStringArrayProperty()
   */
  public void testSetStringArrayProperty() {
    this.gwtJsonObjectGeneratorStringTestDelegate.testSetStringArrayProperty();
  }

  /**
   * @see JsonObjectGeneratorStringTestBase#testGetStringMapProperty()
   */
  public void testGetStringMapProperty() {
    this.gwtJsonObjectGeneratorStringTestDelegate.testGetStringMapProperty();
  }

  /**
   * @see JsonObjectGeneratorStringTestBase#testSetStringMapProperty()
   */
  public void testSetStringMapProperty() {
    this.gwtJsonObjectGeneratorStringTestDelegate.testSetStringMapProperty();
  }
}
