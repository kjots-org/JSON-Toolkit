package org.kjots.json.`object`.test.feature

import com.google.inject.Guice

import org.kjots.json.`object`.shared.JsonObject
import org.kjots.json.`object`.shared.JsonObjectFactory
import org.kjots.json.`object`.simple.SimpleJsonObjectModule

import org.junit.runner.RunWith

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.MustMatchers

/**
 * Create JSON Object Specification.
 * <p>
 * Created: 14th September 2011.
 * 
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.2
 */
@RunWith(classOf[JUnitRunner])
class CreateJsonObjectFeatureSpec extends FeatureSpec with GivenWhenThen with MustMatchers {
  
  feature("The user can create a new JSON Object") {
    
    info("As a user of the JSON Object API")
    info("I want to be able to create a new JSON Object")
    info("So that I can construct JSON data structures")
    
    scenario("A newly created JSON Object has no properties") {
      given("the JSON Object Factory has been configured")
      Guice.createInjector(new SimpleJsonObjectModule())
      
      when("the createJsonObject() method is invoked on the JSON Object Factory instance")
      val jsonObject: JsonObject = JsonObjectFactory.get().createJsonObject()
      
      then("a new JSON Object instance is returned")
      jsonObject must not be (null)
      jsonObject.isInstanceOf[JsonObject] must be (true)
      
      and("the JSON Object instance has no properties")
      jsonObject.getPropertyNames().size() must be (0)
    }
  }
}
