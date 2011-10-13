package org.kjots.json.`object`.scala

import org.kjots.json.`object`.shared.JsonObject

import org.junit.Assert.assertTrue
import org.junit.Test

class JsonObjectTest {
  trait TestJsonObject extends JsonObject {
  }
  
  @Test
  def testCreate() {
    val testJsonObject: TestJsonObject = ScalaJsonObject.create[TestJsonObject]
    
    assertTrue(testJsonObject.isInstanceOf[TestJsonObject])
  }
}