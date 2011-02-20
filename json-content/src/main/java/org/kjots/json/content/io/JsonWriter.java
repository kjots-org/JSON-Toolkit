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
package org.kjots.json.content.io;

import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.kjots.json.content.shared.text.JsonTextGenerator;

/**
 * JSON Writer.
 * <p>
 * Created: 2nd February 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class JsonWriter extends JsonTextGenerator {
  /** The output. */
  private final PrintWriter out;

  /**
   * Construct a new JSON Writer.
   *
   * @param writer The writer.
   */
  public JsonWriter(Writer writer) {
    this.out = writer instanceof PrintWriter ? (PrintWriter)writer : new PrintWriter(writer);
  }

  /**
   * Handle the end of the JSON content.
   */
  @Override
  public void endJson() {
    super.endJson();
    
    this.out.flush();
  }

  /**
   * Set the rounding mode of numeric values.
   *
   * @param roundingMode The rounding mode of numeric values.
   * @see #getRoundingMode()
   */
  public void setRoundingMode(RoundingMode roundingMode) {
    int intRoundingMode;
    switch (roundingMode) {
    case UP:
      intRoundingMode = BigDecimal.ROUND_UP;
      
      break;
    
    case DOWN:
      intRoundingMode = BigDecimal.ROUND_DOWN;
      
      break;
    
    case CEILING:
      intRoundingMode = BigDecimal.ROUND_CEILING;
      
      break;
    
    case FLOOR:
      intRoundingMode = BigDecimal.ROUND_FLOOR;
      
      break;
    
    case HALF_UP:
      intRoundingMode = BigDecimal.ROUND_HALF_UP;
      
      break;
    
    case HALF_DOWN:
      intRoundingMode = BigDecimal.ROUND_HALF_DOWN;
      
      break;
    
    case HALF_EVEN:
      intRoundingMode = BigDecimal.ROUND_HALF_EVEN;
      
      break;
    
    case UNNECESSARY:
      intRoundingMode = BigDecimal.ROUND_UNNECESSARY;
      
      break;
      
    default:
      throw new IllegalStateException();
    }
    
    this.setRoundingMode(intRoundingMode);
  }

  /**
   * Print the given character.
   *
   * @param character The character.
   */
  @Override
  protected void print(char character) {
    this.out.print(character);
  }
  
  /**
   * Print the given string.
   *
   * @param string The string.
   */
  @Override
  protected void print(String string) {
    this.out.print(string);
  }
}
