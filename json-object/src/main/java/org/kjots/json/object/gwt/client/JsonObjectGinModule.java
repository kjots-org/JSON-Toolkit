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
package org.kjots.json.object.gwt.client;

import org.kjots.json.object.gwt.client.impl.GwtJsonObjectImpl;
import org.kjots.json.object.shared.JsonObjectFactory;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * JSON Object Gin Module.
 * <p>
 * Created: 11th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class JsonObjectGinModule extends AbstractGinModule {
  /**
   * Configure the module.
   */
  @Override
  protected void configure() {
    this.bind(JsonObjectFactory.class).in(Singleton.class);
    
    this.requestStaticInjection(JsonObjectFactory.class);
    this.requestStaticInjection(GwtJsonObjectImpl.class);
  }
}
