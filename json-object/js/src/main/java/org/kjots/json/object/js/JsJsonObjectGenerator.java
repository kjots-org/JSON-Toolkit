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
package org.kjots.json.object.js;

import java.lang.reflect.InvocationTargetException;

import javax.script.Invocable;

import org.kjots.json.object.JsonObjectGeneratorBase;
import org.kjots.json.object.js.impl.JsJsonObjectImpl;
import org.kjots.json.object.shared.JsonObject;

/**
 * JavaScript JSON Object Generator.
 * <p>
 * Created: 14th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonObjectGenerator extends JsonObjectGeneratorBase<JsJsonObjectImpl> {
  /**
   * Construct a new JavaScript JSON Object Generator.
   */
  public JsJsonObjectGenerator() {
    super(JsJsonObjectImpl.class);
  }
  
  /**
   * Create a new instance of the the implementation of the JSON object with
   * the given class.
   *
   * @param jsonObjectClass The class of the JSON object.
   * @param jsEngine The JavaScript engine.
   * @param object The underlying JSON object.
   * @return The new JSON object implementation instance
   */
  public JsJsonObjectImpl newJsonObjectImpl(Class<? extends JsonObject> jsonObjectClass, Invocable jsEngine, Object object) {
    Class<? extends JsJsonObjectImpl> jsonObjectImplClass = this.getJsonObjectImplClass(jsonObjectClass);

    try {
      return jsonObjectImplClass.getConstructor(Class.class, Invocable.class, Object.class).newInstance(jsonObjectClass, jsEngine, object);
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
