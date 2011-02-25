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
package org.kjots.json.object.shared.content;

import org.kjots.json.content.shared.JsonContentHandler;
import org.kjots.json.content.shared.PartialJsonContentHandler;

import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;

/**
 * JSON Object Builder.
 * <p>
 * Created: 15th February 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class JsonObjectBuilder implements JsonContentHandler {
  /**
   * Duplicate Member Policy.
   * <p>
   * Created: 2nd June 2010.
   */
  public enum DuplicateMemberPolicy {
    /** The error duplicate member policy. */
    ERROR,
    
    /** The ignore duplicate member policy. */
    IGNORE,
    
    /** The replace duplicate member policy. */
    REPLACE,
    
    /** The merge duplicate member policy. */
    MERGE;
  }
  
  /**
   * JSON Context.
   */
  private abstract class JsonContext extends PartialJsonContentHandler {
    /** The parent JSON context. */
    protected final JsonContext parentJsonContext;
    
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
      JsonObjectBuilder.this.jsonContext = this.parentJsonContext;
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
      this.close();
    }

    /**
     * Handle the start of a JSON object.
     */
    @Override
    public void startObject() {
      if (JsonObjectBuilder.this.jsonObject != null) {
        switch (JsonObjectBuilder.this.duplicateMemberPolicy) {
        case REPLACE:
          JsonObjectBuilder.this.jsonObject = null;
        }
      }
      
      if (JsonObjectBuilder.this.jsonObject == null) {
        JsonObjectBuilder.this.jsonObject = JsonObjectFactory.get().createJsonObject();
      }
      
      JsonObjectBuilder.this.jsonContext = new ObjectJsonContext(this, JsonObjectBuilder.this.jsonObject);
    }

    /**
     * Handle the start of a JSON array.
     */
    @Override
    public void startArray() {
      if (JsonObjectBuilder.this.jsonObject != null) {
        switch (JsonObjectBuilder.this.duplicateMemberPolicy) {
        case MERGE:
          JsonObjectBuilder.this.jsonContext = new NonOpArrayJsonContext(this);
          
          return;
        }
      }
      
      JsonObjectBuilder.this.jsonObject = JsonObjectFactory.get().createJsonArray();
      
      JsonObjectBuilder.this.jsonContext = new ArrayJsonContext(this, (JsonArray)JsonObjectBuilder.this.jsonObject);
    }
  }
  
  /**
   * Object JSON Context.
   */
  private class ObjectJsonContext extends JsonContext {
    /** The JSON object. */
    private final JsonObject jsonObject;
    
    /** The name of the next member. */
    private String nextMemberName;
    
    /**
     * Construct a new Object JSON Context.
     *
     * @param parentJsonContext The parent JSON context.
     * @param jsonObject The JSON object.
     */
    public ObjectJsonContext(JsonContext parentJsonContext, JsonObject jsonObject) {
      super(parentJsonContext);
      
      this.jsonObject = jsonObject;
    }
    
    /**
     * Handle the start of a JSON object.
     */
    @Override
    public void startObject() {
      JsonObject jsonObject = null;
      
      if (this.jsonObject.hasProperty(this.nextMemberName)) {
        switch (JsonObjectBuilder.this.duplicateMemberPolicy) {
        case MERGE:
          if (this.jsonObject.isObjectProperty(this.nextMemberName)) {
            JsonObject existingJsonObject = this.jsonObject.getObjectProperty(this.nextMemberName);
            if (!existingJsonObject.isArray()) {
              jsonObject = existingJsonObject;
              
              break;
            }
          }
          
          // Fall though
          
        case IGNORE:
          JsonObjectBuilder.this.jsonContext = new NonOpObjectJsonContext(this);
          
          return;
        }
      }
      
      if (jsonObject == null) {
        jsonObject = JsonObjectFactory.get().createJsonObject();
        
        this.jsonObject.setObjectProperty(this.nextMemberName, jsonObject);
      }
      
      JsonObjectBuilder.this.jsonContext = new ObjectJsonContext(this, jsonObject);
    }
    
    /**
     * Handle the end of a JSON object.
     */
    @Override
    public void endObject() {
      this.close();
    }

    /**
     * Handle the start of a JSON array.
     */
    @Override
    public void startArray() {
      if (this.jsonObject.hasProperty(this.nextMemberName)) {
        switch (JsonObjectBuilder.this.duplicateMemberPolicy) {
        case MERGE:
        case IGNORE:
          JsonObjectBuilder.this.jsonContext = new NonOpArrayJsonContext(this);
          
          return;
        }
      }
      
      JsonArray jsonArray = JsonObjectFactory.get().createJsonArray();
      
      this.jsonObject.setObjectProperty(this.nextMemberName, jsonArray);
      
      JsonObjectBuilder.this.jsonContext = new ArrayJsonContext(this, jsonArray);
    }
    
    /**
     * Handle the name of a member of a JSON object.
     *
     * @param name The name of the member.
     */
    @Override
    public void memberName(String name) {
      if (this.jsonObject.hasProperty(name)) {
        switch (JsonObjectBuilder.this.duplicateMemberPolicy) {
        case ERROR:
          throw new UnsupportedOperationException("Duplicate member: " + name);
        }
      }
      
      this.nextMemberName = name;
    }

    /**
     * Handle a JSON primitive.
     *
     * @param value The value of the JSON primitive.
     */
    @Override
    public void primitive(Object value) {
      if (this.jsonObject.hasProperty(this.nextMemberName)) {
        switch (JsonObjectBuilder.this.duplicateMemberPolicy) {
        case MERGE:
        case IGNORE:
          return;
        }
      }
      
      if (value == null) {
        this.jsonObject.setObjectProperty(this.nextMemberName, null);
      }
      else if (value instanceof Boolean) {
        Boolean booleanValue = (Boolean)value;
        
        this.jsonObject.setBooleanProperty(this.nextMemberName, booleanValue.booleanValue());
      }
      else if (value instanceof Number) {
        Number numberValue = (Number)value;
        
        this.jsonObject.setNumberProperty(this.nextMemberName, numberValue);
      }
      else if (value instanceof String) {
        String stringValue = (String)value;
        
        this.jsonObject.setStringProperty(this.nextMemberName, stringValue);
      }
      else {
        throw new IllegalStateException("Unsupported primitive type: " + value.getClass().getName());
      }
    }
  }
  
  /**
   * Array JSON Context.
   */
  private class ArrayJsonContext extends JsonContext {
    /** The JSON array. */
    private final JsonArray jsonArray;
    
    /**
     * Construct a new Array JSON Context.
     *
     * @param parentJsonContext The parent JSON context.
     * @param jsonArray The JSON array.
     */
    public ArrayJsonContext(JsonContext parentJsonContext, JsonArray jsonArray) {
      super(parentJsonContext);
      
      this.jsonArray =  jsonArray;
    }
    
    /**
     * Handle the start of a JSON object.
     */
    @Override
    public void startObject() {
      JsonObject jsonObject = JsonObjectFactory.get().createJsonObject();
      
      this.jsonArray.setObjectElement(this.jsonArray.getLength(), jsonObject);
      
      JsonObjectBuilder.this.jsonContext = new ObjectJsonContext(this, jsonObject);
    }
    
    /**
     * Handle the start of a JSON array.
     */
    @Override
    public void startArray() {
      JsonArray jsonArray = JsonObjectFactory.get().createJsonArray();
      
      this.jsonArray.setObjectElement(this.jsonArray.getLength(), jsonArray);
      
      JsonObjectBuilder.this.jsonContext = new ArrayJsonContext(this, jsonArray);
    }
    
    /**
     * Handle the end of a JSON array.
     */
    @Override
    public void endArray() {
      this.close();
    }

    /**
     * Handle a JSON primitive.
     *
     * @param value The value of the JSON primitive.
     */
    @Override
    public void primitive(Object value) {
      if (value == null) {
        this.jsonArray.setObjectElement(this.jsonArray.getLength(), null);
      }
      else if (value instanceof Boolean) {
        Boolean booleanValue = (Boolean)value;
        
        this.jsonArray.setBooleanElement(this.jsonArray.getLength(), booleanValue.booleanValue());
      }
      else if (value instanceof Number) {
        Number numberValue = (Number)value;
        
        this.jsonArray.setNumberElement(this.jsonArray.getLength(), numberValue);
      }
      else if (value instanceof String) {
        String stringValue = (String)value;
        
        this.jsonArray.setStringElement(this.jsonArray.getLength(), stringValue);
      }
      else {
        throw new IllegalStateException("Unsupported primitive type: " + value.getClass().getName());
      }
    }
  }
  
  /**
   * Non-operational JSON Context.
   * <p>
   * Created: 2nd June 2010.
   */
  private abstract class NonOpJsonContext extends JsonContext {
    /**
     * Handle the start of a JSON object.
     */
    @Override
    public void startObject() {
      JsonObjectBuilder.this.jsonContext = new NonOpObjectJsonContext(this);
    }
    
    /**
     * Handle the start of a JSON array.
     */
    @Override
    public void startArray() {
      JsonObjectBuilder.this.jsonContext = new NonOpArrayJsonContext(this);
    }
    
    /**
     * Handle a JSON primitive.
     *
     * @param value The value of the JSON primitive.
     */
    @Override
    public void primitive(Object value) {
    }
    
    /**
     * Construct a new Non-operational JSON Context.
     *
     * @param parentJsonContext
     */
    protected NonOpJsonContext(JsonContext parentJsonContext) {
      super(parentJsonContext);
    }
  }
  
  /**
   * Non-operational Root JSON Context.
   * <p>
   * Created: 2nd June 2010.
   */
  private class NonOpRootJsonContext extends NonOpJsonContext {
    /**
     * Construct a new Non-operational Root JSON Context.
     *
     * @param parentJsonContext The parent JSON context.
     */
    public NonOpRootJsonContext(JsonContext parentJsonContext) {
      super(parentJsonContext);
    }
    
    /**
     * Handle the end of the JSON content.
     */
    @Override
    public void endJson() {
      this.close();
    }
  }
  
  /**
   * Non-operational Object JSON Context.
   * <p>
   * Created: 2nd June 2010.
   */
  private class NonOpObjectJsonContext extends NonOpJsonContext {
    /**
     * Construct a new Non-operational Object JSON Context.
     *
     * @param parentJsonContext The parent JSON context.
     */
    public NonOpObjectJsonContext(JsonContext parentJsonContext) {
      super(parentJsonContext);
    }
    
    /**
     * Handle the end of a JSON object.
     */
    @Override
    public void endObject() {
      this.close();
    }

    /**
     * Handle the name of a member of a JSON object.
     *
     * @param name The name of the member.
     */
    @Override
    public void memberName(String name) {
    }
  }
  
  /**
   * Non-operational Array JSON Context.
   * <p>
   * Created: 2nd June 2010.
   */
  private class NonOpArrayJsonContext extends NonOpJsonContext {
    /**
     * Construct a new Non-operational Array JSON Context.
     *
     * @param parentJsonContext The parent JSON context.
     */
    public NonOpArrayJsonContext(JsonContext parentJsonContext) {
      super(parentJsonContext);
    }
    
    /**
     * Handle the end of a JSON array.
     */
    @Override
    public void endArray() {
      this.close();
    }
  }
  
  /** The JSON context. */
  private JsonContext jsonContext = new JsonContext(null) {
    @Override
    public void startJson() {
      if (JsonObjectBuilder.this.jsonObject != null) {
        switch (JsonObjectBuilder.this.duplicateMemberPolicy) {
        case ERROR:
          throw new UnsupportedOperationException("JSON content already handled");
          
        case IGNORE:
          JsonObjectBuilder.this.jsonContext = new NonOpRootJsonContext(JsonObjectBuilder.this.jsonContext);
          
          return;
        }
      }
      
      JsonObjectBuilder.this.jsonContext = new RootJsonContext(JsonObjectBuilder.this.jsonContext);
    }
  };
  
  /** The duplicate member policy. */
  private DuplicateMemberPolicy duplicateMemberPolicy;
  
  /** The JSON object. */
  private JsonObject jsonObject;
  
  /**
   * Construct a new JSON Object Builder.
   */
  public JsonObjectBuilder() {
    this.duplicateMemberPolicy = DuplicateMemberPolicy.REPLACE;
  }
  
  /**
   * Construct a new JSON Object Builder.
   *
   * @param duplicateMemberPolicy The duplicate member policy.
   */
  public JsonObjectBuilder(DuplicateMemberPolicy duplicateMemberPolicy) {
    this.duplicateMemberPolicy = duplicateMemberPolicy;
  }
  
  /**
   * Construct a new JSON Object Builder.
   *
   * @param jsonObject The JSON object.
   */
  public JsonObjectBuilder(JsonObject jsonObject) {
    this.jsonObject = jsonObject;
    this.duplicateMemberPolicy = DuplicateMemberPolicy.MERGE;
  }
  
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
   * Retrieve the duplicate member policy.
   *
   * @return The duplicate member policy.
   * @see #setDuplicateMemberPolicy(DuplicateMemberPolicy)
   */
  public DuplicateMemberPolicy getDuplicateMemberPolicy() {
    return this.duplicateMemberPolicy;
  }

  /**
   * Set the duplicate member policy.
   *
   * @param duplicateMemberPolicy The duplicate member policy.
   * @see #getDuplicateMemberPolicy()
   */
  public void setDuplicateMemberPolicy(DuplicateMemberPolicy duplicateMemberPolicy) {
    this.duplicateMemberPolicy = duplicateMemberPolicy;
  }

  /**
   * Retrieve the JSON object.
   *
   * @return The JSON object.
   */
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
}
