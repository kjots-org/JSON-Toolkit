/* 
 * Copyright © 2009 Karl J. Ots <kjots@kjots.org>
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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JSON Property.
 * <p>
 * Created: 6th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonProperty {
  public static enum OperationType {
    /** The "has" operation type. */
    HAS,
    
    /** The "is null" operation type. */
    IS_NULL,
    
    /** The "delete" operation type. */
    DELETE,
    
    /** The "get" operation type. */
    GET,
    
    /** The "set" operation type. */
    SET;
  }
  
  /**
   * Retrieve the name of the property.
   *
   * @return The name of the property.
   */
  public String name();
  
  /**
   * Retrieve the type of the operation on the property.
   *
   * @return The type of the operation on the property.
   */
  public OperationType operation();
  
  /**
   * Retrieve the JSON property adapter.
   *
   * @return The JSON property adapter.
   */
  public Class<? extends JsonPropertyAdapter> adapter() default JsonPropertyAdapter.class;
}
