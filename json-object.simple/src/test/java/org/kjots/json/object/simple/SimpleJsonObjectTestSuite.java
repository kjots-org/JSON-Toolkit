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
package org.kjots.json.object.simple;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.kjots.json.object.simple.impl.SimpleJsonArrayImplTest;
import org.kjots.json.object.simple.impl.SimpleJsonBooleanArrayImplTest;
import org.kjots.json.object.simple.impl.SimpleJsonBooleanMapImplTest;
import org.kjots.json.object.simple.impl.SimpleJsonNumberArrayImplTest;
import org.kjots.json.object.simple.impl.SimpleJsonNumberMapImplTest;
import org.kjots.json.object.simple.impl.SimpleJsonObjectArrayImplTest;
import org.kjots.json.object.simple.impl.SimpleJsonObjectImplTest;
import org.kjots.json.object.simple.impl.SimpleJsonObjectMapImplTest;
import org.kjots.json.object.simple.impl.SimpleJsonStringArrayImplTest;
import org.kjots.json.object.simple.impl.SimpleJsonStringMapImplTest;

/**
 * Simple JSON Object Test Suite.
 * <p>
 * Created: 9th February 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.1
 */
@RunWith(Suite.class)
@SuiteClasses({
  SimpleJsonObjectTestSuite.SimpleJsonObjectGeneratorTestSuite.class,
  SimpleJsonObjectTestSuite.SimpleJsonObjectImplTestSuite.class
})
public class SimpleJsonObjectTestSuite {
  /**
   * Simple JSON Object Generator Test Suite.
   */
  @RunWith(Suite.class)
  @SuiteClasses({
    SimpleJsonObjectGeneratorTest.class,
    
    SimpleJsonObjectGeneratorPrimitiveTest.class,
    SimpleJsonObjectGeneratorBooleanTest.class,
    SimpleJsonObjectGeneratorNumberTest.class,
    SimpleJsonObjectGeneratorStringTest.class,
    SimpleJsonObjectGeneratorObjectTest.class,
    
    SimpleJsonObjectGeneratorAutoAdaptedPropertyTest.class,
    
    SimpleJsonObjectGeneratorFunctionTest.class,
    SimpleJsonObjectGeneratorExceptionTest.class,
    
    SimpleJsonObjectGeneratorBridgeTest.class
  })
  public static class SimpleJsonObjectGeneratorTestSuite {
  }
  
  /**
   * Simple JSON Object Implementation Test Suite.
   */
  @RunWith(Suite.class)
  @SuiteClasses({
    SimpleJsonObjectImplTest.class,
    SimpleJsonArrayImplTest.class,
    
    SimpleJsonBooleanArrayImplTest.class,
    SimpleJsonNumberArrayImplTest.class,
    SimpleJsonStringArrayImplTest.class,
    SimpleJsonObjectArrayImplTest.class,
    
    SimpleJsonBooleanMapImplTest.class,
    SimpleJsonNumberMapImplTest.class,
    SimpleJsonStringMapImplTest.class,
    SimpleJsonObjectMapImplTest.class
  })
  public static class SimpleJsonObjectImplTestSuite {
  }
}
