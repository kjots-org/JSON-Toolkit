/* 
 * Copyright © 2010 Karl J. Ots <kjots@kjots.org>
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
package org.kjots.json.object.shared;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

/**
 * JSON Object Generator Exception Test Base.
 * <p>
 * Created: 26th May 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public abstract class JsonObjectGeneratorExceptionTestBase {
  /**
   * Test JSON Object.
   */
  public interface TestJsonObject extends JsonObject {
    /**
     * Throw the test exception.
     */
    @JsonException(klass = TestException.class)
    public void throwTestException();
  }
  
  /**
   * Test Exception.
   */
  @SuppressWarnings("serial")
  public static class TestException extends RuntimeException {
  }
  
  /**
   * Test the throwing of a exception.
   * <p>
   * This test asserts that a method throws an exception.
   */
  @Test
  public void testThrowException() {
    TestJsonObject testJsonObject = JsonObjectFactory.get().createJsonObject(TestJsonObject.class);
    
    Throwable throwable = null;
    try {
      testJsonObject.throwTestException();
    }
    catch (Throwable t) {
      throwable = t;
    }
    
    assertNotNull("throwable == null", throwable);
    assertTrue("throwable !instanceof TestException", throwable instanceof TestException);
  }
}
