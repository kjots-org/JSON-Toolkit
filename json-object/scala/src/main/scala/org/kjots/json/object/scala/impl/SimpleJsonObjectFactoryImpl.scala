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
package org.kjots.json.`object`.scala.impl

import org.kjots.json.`object`.impl.JvmJsonObjectFactoryImplBase
import org.kjots.json.`object`.scala.SimpleJsonObjectGenerator
import org.kjots.json.`object`.shared._
import org.kjots.json.`object`.simple.impl._
import org.kjots.json.`object`.simple.SimpleJsonValue

/**
 * Simple JSON Object Factory Implementation.
 * <p>
 * Created: 28th May 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.2
 */
class SimpleJsonObjectFactoryImpl extends JvmJsonObjectFactoryImplBase {
  private lazy val JSON_OBJECT_CLASS = classOf[JsonObject]
  private lazy val JSON_ARRAY_CLASS = classOf[JsonArray]
  private lazy val JSON_BOOLEAN_ARRAY_CLASS = classOf[JsonBooleanArray]
  private lazy val JSON_NUMBER_ARRAY_CLASS = classOf[JsonNumberArray]
  private lazy val JSON_STRING_ARRAY_CLASS = classOf[JsonStringArray]
  private lazy val JSON_OBJECT_ARRAY_CLASS = classOf[JsonObjectArray[_]]
  private lazy val JSON_BOOLEAN_MAP_CLASS = classOf[JsonBooleanMap]
  private lazy val JSON_NUMBER_MAP_CLASS = classOf[JsonNumberMap]
  private lazy val JSON_STRING_MAP_CLASS = classOf[JsonStringMap]
  private lazy val JSON_OBJECT_MAP_CLASS = classOf[JsonObjectMap[_]]
  
  override def createJsonObject[T <: JsonObject](jsonObjectClass: Class[T], obj: AnyRef): T = {
    def simpleJsonValue = obj.asInstanceOf[SimpleJsonValue]

    def simpleJsonObjectImpl = jsonObjectClass match {
      case `JSON_OBJECT_CLASS` => new SimpleJsonObjectImpl(simpleJsonValue)
      case `JSON_ARRAY_CLASS` => new SimpleJsonArrayImpl(simpleJsonValue)
      case `JSON_BOOLEAN_ARRAY_CLASS` => new SimpleJsonBooleanArrayImpl(simpleJsonValue)
      case `JSON_NUMBER_ARRAY_CLASS` => new SimpleJsonNumberArrayImpl(simpleJsonValue)
      case `JSON_STRING_ARRAY_CLASS` => new SimpleJsonStringArrayImpl(simpleJsonValue)
      case `JSON_OBJECT_ARRAY_CLASS` => new SimpleJsonObjectArrayImpl(simpleJsonValue)
      case `JSON_BOOLEAN_MAP_CLASS` => new SimpleJsonBooleanMapImpl(simpleJsonValue)
      case `JSON_NUMBER_MAP_CLASS` => new SimpleJsonNumberMapImpl(simpleJsonValue)
      case `JSON_STRING_MAP_CLASS` => new SimpleJsonStringMapImpl(simpleJsonValue)
      case `JSON_OBJECT_MAP_CLASS` => new SimpleJsonObjectMapImpl(simpleJsonValue)
      case _ => SimpleJsonObjectGenerator.newJsonObjectImpl(jsonObjectClass, simpleJsonValue)
    }

    simpleJsonObjectImpl.asInstanceOf[T]
  }

  override def createJsonObject[T <: JsonObject](jsonObjectClassName: String, obj: AnyRef): T =
    createJsonObject(Class.forName(jsonObjectClassName).asInstanceOf[Class[T]], obj)

  override def createJsonObject[T <: JsonObject](jsonObjectClass: Class[T]): T =
    createJsonObject(jsonObjectClass, SimpleJsonValue.createObject())

  override def createJsonObject[T <: JsonObject](jsonObjectClassName: String): T =
    createJsonObject(jsonObjectClassName, SimpleJsonValue.createObject())

  override def createJsonArray[T <: JsonArray](jsonArrayClass: Class[T]): T =
    createJsonObject(jsonArrayClass, SimpleJsonValue.createArray())

  override def createJsonArray[T <: JsonArray](jsonArrayClassName: String): T =
    createJsonObject(jsonArrayClassName, SimpleJsonValue.createArray())
}
