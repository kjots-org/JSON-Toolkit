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
package org.kjots.json.`object`.scala

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

import org.kjots.json.`object`.JvmJsonObjectGeneratorBridgeTestBase
import org.kjots.json.`object`.JvmJsonObjectGeneratorFunctionTestBase
import org.kjots.json.`object`.shared.JsonObjectGeneratorAutoAdaptedPropertyTestBase
import org.kjots.json.`object`.shared.JsonObjectGeneratorBooleanTestBase
import org.kjots.json.`object`.shared.JsonObjectGeneratorExceptionTestBase
import org.kjots.json.`object`.shared.JsonObjectGeneratorNumberTestBase
import org.kjots.json.`object`.shared.JsonObjectGeneratorObjectTestBase
import org.kjots.json.`object`.shared.JsonObjectGeneratorPrimitiveTestBase
import org.kjots.json.`object`.shared.JsonObjectGeneratorStringTestBase
import org.kjots.json.`object`.shared.JsonObjectGeneratorTestBase

/**
 * Simple JSON Object Generator Test Suite.
 * <p>
 * Created: 28th May 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.2
 */
@RunWith(classOf[Suite])
@SuiteClasses(Array(
  classOf[SimpleJsonObjectGeneratorTest]
  
//  classOf[SimpleJsonObjectGeneratorPrimitiveTest],
//  classOf[SimpleJsonObjectGeneratorBooleanTest],
//  classOf[SimpleJsonObjectGeneratorNumberTest],
//  classOf[SimpleJsonObjectGeneratorStringTest],
//  classOf[SimpleJsonObjectGeneratorObjectTest],
  
//  classOf[SimpleJsonObjectGeneratorAutoAdaptedPropertyTest],
  
//  classOf[SimpleJsonObjectGeneratorFunctionTest],
//  classOf[SimpleJsonObjectGeneratorExceptionTest],
  
//  classOf[SimpleJsonObjectGeneratorBridgeTest]
))
class SimpleJsonObjectGeneratorTestSuite {}

class SimpleJsonObjectGeneratorTest extends JsonObjectGeneratorTestBase
  with SimpleJsonObjectTest {}

class SimpleJsonObjectGeneratorPrimitiveTest extends JsonObjectGeneratorPrimitiveTestBase
  with SimpleJsonObjectTest {}

class SimpleJsonObjectGeneratorBooleanTest extends JsonObjectGeneratorBooleanTestBase
  with SimpleJsonObjectTest {}

class SimpleJsonObjectGeneratorStringTest extends JsonObjectGeneratorStringTestBase
  with SimpleJsonObjectTest {}

class SimpleJsonObjectGeneratorNumberTest extends JsonObjectGeneratorNumberTestBase
  with SimpleJsonObjectTest {}

class SimpleJsonObjectGeneratorObjectTest extends JsonObjectGeneratorObjectTestBase
  with SimpleJsonObjectTest {}

class SimpleJsonObjectGeneratorAutoAdaptedPropertyTest extends JsonObjectGeneratorAutoAdaptedPropertyTestBase
  with SimpleJsonObjectTest {}

class SimpleJsonObjectGeneratorFunctionTest extends JvmJsonObjectGeneratorFunctionTestBase
  with SimpleJsonObjectTest {}

class SimpleJsonObjectGeneratorExceptionTest extends JsonObjectGeneratorExceptionTestBase
  with SimpleJsonObjectTest {}

class SimpleJsonObjectGeneratorBridgeTest extends JvmJsonObjectGeneratorBridgeTestBase
  with SimpleJsonObjectTest {}
