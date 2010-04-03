/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.gwt.client;

import org.kjots.json.object.shared.JsonObjectGeneratorObjectTestBase;

/**
 * GWT JSON Object Generator Object Test.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class GwtJsonObjectGeneratorObjectTest extends GwtJsonObjectTestBase {
  /**
   * GWT JSON Object Generator Test Object Delegate.
   */
  public class GwtJsonObjectGeneratorObjectTestDelegate extends JsonObjectGeneratorObjectTestBase {
  }
  
  /** The GWT JSON object generator object test delegate. */
  private final GwtJsonObjectGeneratorObjectTestDelegate gwtJsonObjectGeneratorObjectTestDelegate = new GwtJsonObjectGeneratorObjectTestDelegate();

  /**
   * @see JsonObjectGeneratorObjectTestBase#testGetObjectProperty()
   */
  public void testGetObjectProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testGetObjectProperty();
  }

  /**
   * @see JsonObjectGeneratorObjectTestBase#testSetObjectProperty()
   */
  public void testSetObjectProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testSetObjectProperty();
  }

  /**
   * @see JsonObjectGeneratorObjectTestBase#testGetAdaptedObjectProperty()
   */
  public void testGetAdaptedObjectProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testGetAdaptedObjectProperty();
  }

  /**
   * @see JsonObjectGeneratorObjectTestBase#testSetAdaptedObjectProperty()
   */
  public void testSetAdaptedObjectProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testSetAdaptedObjectProperty();
  }

  /**
   * @see JsonObjectGeneratorObjectTestBase#testGetArrayProperty()
   */
  public void testGetArrayProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testGetArrayProperty();
  }

  /**
   * @see JsonObjectGeneratorObjectTestBase#testSetArrayProperty()
   */
  public void testSetArrayProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testSetArrayProperty();
  }

  /**
   * @see JsonObjectGeneratorObjectTestBase#testGetObjectArrayProperty()
   */
  public void testGetObjectArrayProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testGetObjectArrayProperty();
  }

  /**
   * @see JsonObjectGeneratorObjectTestBase#testSetObjectArrayProperty()
   */
  public void testSetObjectArrayProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testSetObjectArrayProperty();
  }

  /**
   * @see JsonObjectGeneratorObjectTestBase#testGetPropertyObjectArrayProperty()
   */
  public void testGetPropertyObjectArrayProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testGetPropertyObjectArrayProperty();
  }

  /**
   * @see JsonObjectGeneratorObjectTestBase#testGetObjectMapProperty()
   */
  public void testGetObjectMapProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testGetObjectMapProperty();
  }

  /**
   * @see JsonObjectGeneratorObjectTestBase#testSetObjectMapProperty()
   */
  public void testSetObjectMapProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testSetObjectMapProperty();
  }

  /**
   * @see JsonObjectGeneratorObjectTestBase#testGetPropertyObjectMapProperty()
   */
  public void testGetPropertyObjectMapProperty() {
    this.gwtJsonObjectGeneratorObjectTestDelegate.testGetPropertyObjectMapProperty();
  }
}
