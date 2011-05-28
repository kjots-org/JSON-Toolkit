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

import java.math.BigDecimal;

import org.kjots.json.content.shared.JsonContentHandler;
import org.kjots.json.content.shared.PartialJsonContentHandler;

/**
 * JSON Text Generator.
 * <p>
 * Created: 2nd February 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public abstract class JsonTextGenerator implements JsonContentHandler {
  /**
   * JSON Context.
   */
  private abstract class JsonContext extends PartialJsonContentHandler {
    /** The parent JSON context. */
    protected final JsonContext parentJsonContext;
    
    /** The first entry flag. */
    private boolean firstEntry = true;
    
    /**
     * Construct a new JSON Context.
     *
     * @param parentJsonContext The parent JSON context.
     */
    protected JsonContext(JsonContext parentJsonContext) {
      this.parentJsonContext = parentJsonContext;
    }
    
    /**
     * Close the JSON context.
     */
    protected void close() {
      JsonTextGenerator.this.jsonContext = this.parentJsonContext;
    }
    
    /**
     * Determine if the context is set to the first entry.
     *
     * @return <code>true</code> if the context is set to the first entry.
     */
    protected boolean isFirstEntry() {
      return this.firstEntry;
    }
    
    /**
     * Advance to the next entry.
     */
    protected void nextEntry() {
      this.firstEntry = false;
    }
    
    /**
     * Print the given numeric value.
     *
     * @param value The numeric value
     */
    protected void printNumber(Number value) {
      String stringValue = value.toString();
      
      if (JsonTextGenerator.this.maxDecimalPlaces >= 0) {
        BigDecimal bigDecimal = new BigDecimal(stringValue);
        
        stringValue = bigDecimal.setScale(JsonTextGenerator.this.maxDecimalPlaces, JsonTextGenerator.this.roundingMode).toString();
      }
      
      this.print(stringValue);
    }
    
    /**
     * Print the given string value.
     *
     * @param value The string value.
     */
    protected void printString(String value) {
      this.print('"');
      
      for (int i = 0; i < value.length(); i++) {
        char c = value.charAt(i);
        
        if (c <= 0x001F || c == '"' || c == '\\' || (c >= 0xD800 && c <= 0xDFFF)) {
          this.print('\\');
          
          switch (c) {
          case '\b':
            this.print('b');
            break;
            
          case '\f':
            this.print('f');
            break;
            
          case '\n':
            this.print('n');
            break;
            
          case '\r':
            this.print('r');
            break;
            
          case '\t':
            this.print('t');
            break;
            
          case '"':
          case '\\':
            this.print(c);
            break;
            
          default:
            this.print('u');
            if (c < 0x1000) {
              this.print('0');
              if (c < 0x0100) {
                this.print('0');
                if (c < 0x0010) {
                  this.print('0');
                }
              }
            }
            this.print(Integer.toHexString(c).toUpperCase());
          }
        }
        else {
          this.print(c);
        }
      }
      
      this.print('"');
    }
    
    /**
     * Print a space.
     * <p>
     * This method will have no effect if the format flag is <code>false</code>.
     */
    protected void printSpace() {
      if (JsonTextGenerator.this.format) {
        this.print(" ");
      }
    }
    
    /**
     * Print an indent.
     * <p>
     * This method will have no effect if the format flag is <code>false</code>.
     */
    protected void printIndent() {
      if (JsonTextGenerator.this.format) {
        int indent = this.getIndent();
        
        for (int i = 0; i < indent; i++) {
          this.print("  ");
        }
      }
    }
    
    /**
     * Print a newline.
     * <p>
     * This method will have no effect if the format flag is <code>false</code>.
     */
    protected void printNewline() {
      if (JsonTextGenerator.this.format) {
        this.print("\n");
      }
    }
    
    /**
     * Print the given character.
     *
     * @param character The character.
     */
    protected void print(char character) {
      JsonTextGenerator.this.print(character);
    }
    
    /**
     * Print the given string.
     *
     * @param string The string,
     */
    protected void print(String string) {
      JsonTextGenerator.this.print(string);
    }

    /**
     * Retrieve the indent.
     *
     * @return the indent.
     */
    private int getIndent() {
      return this.parentJsonContext != null ? this.parentJsonContext.getIndent() + 1 : -1;
    }
  }
  
  /**
   * Root JSON Context.
   */
  private class RootJsonContext extends JsonContext {
    /**
     * Construct a new Root JSON Context.
     *
     * @param parentJsonContext The parent JSON context.
     */
    public RootJsonContext(JsonContext parentJsonContext) {
      super(parentJsonContext);
    }
    
    /**
     * Handle the end of the JSON content.
     */
    @Override
    public void endJson() {
      this.printNewline();
      
      this.close();
    }
    
    /**
     * Handle the start of a JSON object.
     */
    @Override
    public void startObject() {
      this.print("{");
      
      JsonTextGenerator.this.jsonContext = new ObjectJsonContext(this);
    }
    
    /**
     * Handle the start of a JSON array.
     */
    @Override
    public void startArray() {
      this.print("[");
      
      JsonTextGenerator.this.jsonContext = new ArrayJsonContext(this);
    }
  }
  
  /**
   * Composite JSON Context.
   */
  private abstract class CompositeJsonContext extends JsonContext {
    /**
     * Handle the start of a JSON object.
     */
    @Override
    public void startObject() {
      this.beginEntry();
      
      this.print("{");
      
      JsonTextGenerator.this.jsonContext = new ObjectJsonContext(this);
    }
    
    /**
     * Handle the start of a JSON array.
     */
    @Override
    public void startArray() {
      this.beginEntry();
      
      this.print("[");
      
      JsonTextGenerator.this.jsonContext = new ArrayJsonContext(this);
    }
    
    /**
     * Handle a JSON primitive.
     *
     * @param value The value of the JSON primitive.
     */
    @Override
    public void primitive(Object value) {
      this.beginEntry();
      
      if (value == null) {
        this.print("null");
      }
      else if (value instanceof Boolean) {
        Boolean booleanValue = (Boolean)value;
        
        this.print(booleanValue.booleanValue() ? "true" : "false");
      }
      else if (value instanceof Number) {
        Number numericValue = (Number)value;
        
        this.printNumber(numericValue);
      }
      else if (value instanceof String) {
        String stringValue = (String)value;
        
        this.printString(stringValue);
      }
      else {
        throw new IllegalStateException("Unsupported primitive type: " + value.getClass().getName());
      }
      
      this.nextEntry();
    }
    
    /**
     * Construct a new Composite JSON Context.
     *
     * @param parentJsonContext The parent JSON context.
     */
    protected CompositeJsonContext(JsonContext parentJsonContext) {
      super(parentJsonContext);
    }
    
    /**
     * Begin an entry.
     */
    protected abstract void beginEntry();
  }
  
  /**
   * Object JSON Context.
   */
  private class ObjectJsonContext extends CompositeJsonContext {
    /** The name of the next member. */
    private String nextMemberName;
    
    /**
     * Construct a new Object JSON Context.
     *
     * @param parentJsonContext The parent JSON context.
     */
    public ObjectJsonContext(JsonContext parentJsonContext) {
      super(parentJsonContext);
    }
    
    /**
     * Handle the end of a JSON object.
     */
    @Override
    public void endObject() {
      if (!this.isFirstEntry()) {
        this.parentJsonContext.printNewline();
        this.parentJsonContext.printIndent();
      }
      
      this.parentJsonContext.print("}");
      
      this.parentJsonContext.nextEntry();
      
      this.close();
    }
    
    /**
     * Handle the name of a member of a JSON object.
     *
     * @param name The name of the member.
     */
    @Override
    public void memberName(String name) {
      this.nextMemberName = name;
    }
    
    /**
     * Begin an entry.
     */
    @Override
    protected void beginEntry() {
      if (!this.isFirstEntry()) {
        this.print(",");
      }
      
      this.printNewline();
      this.printIndent();
      this.printString(this.nextMemberName);
      this.printSpace();
      this.print(":");
      this.printSpace();
    }
  }
  
  /**
   * Array JSON Context.
   */
  private class ArrayJsonContext extends CompositeJsonContext {
    /**
     * Construct a new Array JSON Context.
     *
     * @param parentJsonContext The parent JSON context.
     */
    public ArrayJsonContext(JsonContext parentJsonContext) {
      super(parentJsonContext);
    }
    
    /**
     * Handle the end of a JSON array.
     */
    @Override
    public void endArray() {
      if (!this.isFirstEntry()) {
        this.parentJsonContext.printNewline();
        this.parentJsonContext.printIndent();
      }
      
      this.parentJsonContext.print("]");
      
      this.parentJsonContext.nextEntry();
      
      this.close();
    }
    
    /**
     * Begin an entry.
     */
    @Override
    protected void beginEntry() {
      if (!this.isFirstEntry()) {
        this.print(",");
      }
      
      this.printNewline();
      this.printIndent();
    }
  }
  
  /** The JSON context. */
  private JsonContext jsonContext = new JsonContext(null) {
    @Override
    public void startJson() {
      JsonTextGenerator.this.jsonContext = new RootJsonContext(JsonTextGenerator.this.jsonContext);
    }
  };
  
  /** The format flag. */
  private boolean format;
  
  /** The maximum number of decimal places of numeric values. */
  private int maxDecimalPlaces = -1;
  
  /** The rounding mode of numeric values. */
  private int roundingMode = BigDecimal.ROUND_HALF_UP;
  
  /**
   * Handle the start of the JSON content.
   */
  @Override
  public void startJson() {
    this.jsonContext.startJson();
  }
  
  /**
   * Handle the end of the JSON content.
   */
  @Override
  public void endJson() {
    this.jsonContext.endJson();
  }
  
  /**
   * Handle the start of a JSON object.
   */
  @Override
  public void startObject() {
    this.jsonContext.startObject();
  }
  
  /**
   * Handle the end of a JSON object.
   */
  @Override
  public void endObject() {
    this.jsonContext.endObject();
  }
  
  /**
   * Handle the start of a JSON array.
   */
  @Override
  public void startArray() {
    this.jsonContext.startArray();
  }
  
  /**
   * Handle the end of a JSON array.
   */
  @Override
  public void endArray() {
    this.jsonContext.endArray();
  }
  
  /**
   * Handle the name of a member of a JSON object.
   *
   * @param name The name of the member.
   */
  @Override
  public void memberName(String name) {
    this.jsonContext.memberName(name);
  }
  
  /**
   * Handle a JSON primitive.
   *
   * @param value The value of the JSON primitive.
   */
  @Override
  public void primitive(Object value) {
    this.jsonContext.primitive(value);
  }
  
  /**
   * Retrieve the format flag.
   *
   * @return The format flag.
   * @see #setFormat(boolean)
   */
  public boolean getFormat() {
    return this.format;
  }

  /**
   * Set the format flag.
   *
   * @param format The format flag.
   * @see #getFormat()
   */
  public void setFormat(boolean format) {
    this.format = format;
  }
  
  /**
   * Retrieve the maximum number of decimal places of numeric values.
   *
   * @return The maximum number of decimal places of numeric values.
   * @see #setMaxDecimalPlaces(int)
   */
  public int getMaxDecimalPlaces() {
    return this.maxDecimalPlaces;
  }
  
  /**
   * Set the maximum number of decimal places of numeric values.
   *
   * @param maxDecimalPlaces The maximum number of decimal places of numeric values.
   * @see #getMaxDecimalPlaces()
   */
  public void setMaxDecimalPlaces(int maxDecimalPlaces) {
      this.maxDecimalPlaces = maxDecimalPlaces;
  }
  
  /**
   * Retrieve the rounding mode of numeric values.
   *
   * @return The rounding mode of numeric values.
   * @see #setRoundingMode(int)
   */
  public int getRoundingMode() {
    return this.roundingMode;
  }

  /**
   * Set the rounding mode of numeric values.
   *
   * @param roundingMode The rounding mode of numeric values.
   * @see #getRoundingMode()
   */
  public void setRoundingMode(int roundingMode) {
    this.roundingMode = roundingMode;
  }

  /**
   * Print the given character.
   *
   * @param character The character.
   */
  protected abstract void print(char character);

  /**
   * Print the given string.
   *
   * @param string The string.
   */
  protected abstract void print(String string);
}
