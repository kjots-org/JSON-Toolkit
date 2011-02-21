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
package org.kjots.json.content.shared;

/**
 * Partial JSON Content Handler.
 * <p>
 * This class implements {@link JsonContentHandler} and uses an {@link UnsupportedOperationException}
 * to cause every method to fail.
 * <p>
 * It is intended that this class should be used as the base for classes that
 * only concern themselves with a part of the {@link JsonContentHandler}
 * contract.  Such classes would coordinate with other partial implementations
 * to provide a complete {@link JsonContentHandler} implementation.  By
 * extending this class, these partial implementations need only override the
 * methods that relate to their concern - under correct behaviour, the other
 * methods will never be invoked.
 * <p>
 * For example, a class that only concerns itself with the root JSON context
 * (i.e. the context directly after the invocation of {@link JsonContentHandler#startJson()})
 * knows that under normal circumstances the {@link JsonContentHandler#primitive(Object)}
 * method will never be invoked - thus such a class need not provide an
 * implementation of that method.
 * <p>
 * Created: 15th February 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public abstract class PartialJsonContentHandler implements JsonContentHandler {
  /**
   * Handle the start of the JSON content.
   */
  @Override
  public void startJson() {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Handle the end of the JSON content.
   */
  @Override
  public void endJson() {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Handle the start of a JSON object.
   */
  @Override
  public void startObject() {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Handle the end of a JSON object.
   */
  @Override
  public void endObject() {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Handle the start of a JSON array.
   */
  @Override
  public void startArray() {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Handle the end of a JSON array.
   */
  @Override
  public void endArray() {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Handle the name of a member of a JSON object.
   *
   * @param name The name of the member.
   */
  @Override
  public void memberName(String name) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Handle a JSON primitive.
   *
   * @param value The value of the JSON primitive.
   */
  @Override
  public void primitive(Object value) {
    throw new UnsupportedOperationException();
  }
}
