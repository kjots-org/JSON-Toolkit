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
package org.kjots.json.content.shared.text;

/**
 * JSON Text Builder.
 * <p>
 * Created: 2nd February 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class JsonTextBuilder extends JsonTextGenerator {
  /** The string builder. */
  private final StringBuilder stringBuilder;
  
  /**
   * Construct a new JSON Text Builder.
   */
  public JsonTextBuilder() {
    this(new StringBuilder());
  }
  
  /**
   * Construct a new JSON Text Builder.
   *
   * @param stringBuilder The string builder.
   */
  public JsonTextBuilder(StringBuilder stringBuilder) {
    this.stringBuilder = stringBuilder;
  }
  
  /**
   * Create a string representation of this object.
   *
   * @return The string representation.
   */
  @Override
  public String toString() {
    return this.stringBuilder.toString();
  }
  
  /**
   * Print the given character.
   *
   * @param character The character.
   */
  @Override
  protected void print(char character) {
    this.stringBuilder.append(character);
  }
  
  /**
   * Print the given string.
   *
   * @param string The string.
   */
  @Override
  protected void print(String string) {
    this.stringBuilder.append(string);
  }
}
