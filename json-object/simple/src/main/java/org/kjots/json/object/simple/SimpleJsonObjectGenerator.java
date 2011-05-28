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
package org.kjots.json.object.simple;

import java.lang.reflect.InvocationTargetException;

import org.kjots.json.object.JsonObjectGeneratorBase;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.simple.impl.SimpleJsonObjectImpl;

/**
 * Simple JSON Object Generator.
 * <p>
 * Created: 5th March 2010
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class SimpleJsonObjectGenerator extends JsonObjectGeneratorBase<SimpleJsonObjectImpl> {
  /**
   * Construct a new Simple JSON Object Generator.
   */
  public SimpleJsonObjectGenerator() {
    super(SimpleJsonObjectImpl.class);
  }
  
  /**
   * Create a new instance of the the implementation of the JSON object with
   * the given class.
   *
   * @param jsonObjectClass The class of the JSON object.
   * @param object The underlying JSON object.
   * @return The new JSON object implementation instance
   */
  public SimpleJsonObjectImpl newJsonObjectImpl(Class<? extends JsonObject> jsonObjectClass, SimpleJsonValue object) {
    Class<? extends SimpleJsonObjectImpl> jsonObjectImplClass = this.getJsonObjectImplClass(jsonObjectClass);
    
    try {
      return jsonObjectImplClass.getConstructor(Class.class, SimpleJsonValue.class).newInstance(jsonObjectClass, object);
    }
    catch (NoSuchMethodException nsme) {
      throw new IllegalStateException(nsme);
    }
    catch (IllegalAccessException iae) {
      throw new IllegalStateException(iae);
    }
    catch (InstantiationException ie) {
      throw new IllegalStateException(ie);
    }
    catch (InvocationTargetException ite) {
      Throwable t = ite.getCause();
      
      if (t instanceof RuntimeException) {
        throw (RuntimeException)t;
      }
      else if (t instanceof Error) {
        throw (Error)t;
      }
      else {
        throw new IllegalStateException(t);
      }
    }
  }
}
