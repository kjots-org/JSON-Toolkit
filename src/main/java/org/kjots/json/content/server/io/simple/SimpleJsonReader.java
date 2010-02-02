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
package org.kjots.json.content.server.io.simple;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;

import org.kjots.json.content.rebase.org.json.simple.parser.ContentHandler;
import org.kjots.json.content.rebase.org.json.simple.parser.JSONParser;
import org.kjots.json.content.rebase.org.json.simple.parser.ParseException;
import org.kjots.json.content.server.io.JsonReader;
import org.kjots.json.content.server.io.JsonReaderException;
import org.kjots.json.content.shared.JsonContentHandler;

/**
 * Simple JSON Reader.
 * <p>
 * This class implements a JSON reader backed the parser from JSON.simple
 * [<a href="http://code.google.com/p/json-simple/">http://code.google.com/p/json-simple/</a>],
 * slightly modified to remove functionality not relevant to this library.
 * <p>
 * Created: 1st February 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class SimpleJsonReader implements JsonReader {
  /** The content handler. */
  private JsonContentHandler contentHandler;

  /**
   * Retrieve the content handler.
   *
   * @return The content handler.
   * @see #setSontentHandler(JsonContentHandler)
   */
  @Override
  public JsonContentHandler getContentHandler() {
    return this.contentHandler;
  }

  /**
   * Set the content handler.
   *
   * @param contentHandler The content handler.
   * @see #getContentHandler()
   */
  @Override
  public void setContentHandler(JsonContentHandler contentHandler) {
    this.contentHandler = contentHandler;
  }
  
  /**
   * Parse the given JSON text.
   * 
   * @param jsonText The JSON text.
   * @throws JsonReaderException
   */
  @Override
  public void parse(String jsonText)
    throws JsonReaderException {
    try {
      new JSONParser().parse(jsonText, this.createContentHandler());
    }
    catch (ParseException pe) {
      throw new JsonReaderException(pe);
    }
  }
  
  /**
   * Parse the JSON text provided by the given reader.
   * 
   * @param reader The reader.
   * @throws IOException
   * @throws JsonReaderException
   */
  @Override
  public void parse(Reader reader)
    throws IOException, JsonReaderException {
    try {
      new JSONParser().parse(reader, this.createContentHandler());
    }
    catch (ParseException pe) {
      throw new JsonReaderException(pe);
    }
  }
  
  /**
   * Create the content handler.
   *
   * @return The content handler.
   */
  private ContentHandler createContentHandler() {
    return new ContentHandler() {
      @Override
      public void startJSON() {
        if (SimpleJsonReader.this.contentHandler != null) {
          SimpleJsonReader.this.contentHandler.startJson();
        }
      }

      @Override
      public void endJSON() {
        if (SimpleJsonReader.this.contentHandler != null) {
          SimpleJsonReader.this.contentHandler.endJson();
        }
      }
      
      @Override
      public boolean startObject() {
        if (SimpleJsonReader.this.contentHandler != null) {
          SimpleJsonReader.this.contentHandler.startObject();
        }
        
        return true;
      }

      @Override
      public boolean endObject() {
        if (SimpleJsonReader.this.contentHandler != null) {
          SimpleJsonReader.this.contentHandler.endObject();
        }
        
        return true;
      }

      @Override
      public boolean startObjectEntry(String key) {
        if (SimpleJsonReader.this.contentHandler != null) {
          SimpleJsonReader.this.contentHandler.memberName(key);
        }
        
        return true;
      }

      @Override
      public boolean endObjectEntry() {
        return true;
      }

      @Override
      public boolean startArray()  {
        if (SimpleJsonReader.this.contentHandler != null) {
          SimpleJsonReader.this.contentHandler.startArray();
        }
        
        return true;
      }

      @Override
      public boolean endArray() {
        if (SimpleJsonReader.this.contentHandler != null) {
          SimpleJsonReader.this.contentHandler.endArray();
        }
        
        return true;
      }

      @Override
      public boolean primitive(Object value) {
        if (value instanceof BigDecimal) {
          BigDecimal numericValue = (BigDecimal)value;
          
          // Attempt to coerce the value into an integer
          try {
            value = numericValue.intValueExact();
          }
          catch (ArithmeticException ae1) {
            // Attempt to coerce the value into a long
            try {
              value = numericValue.longValueExact();
            }
            catch (ArithmeticException ae2) {
              // Attempt to coerce the value into a BigInteger
              try {
                value = numericValue.toBigIntegerExact();
              }
              catch (ArithmeticException ae3) {
                // Ignore this exception - value will remain a BigDecimal
              }
            }
          }
        }
        
        SimpleJsonReader.this.contentHandler.primitive(value);
        
        return true;
      }
    };
  }
}
