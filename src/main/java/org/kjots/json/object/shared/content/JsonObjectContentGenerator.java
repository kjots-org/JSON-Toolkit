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

import java.math.BigDecimal;

import org.kjots.json.content.shared.AbstractJsonContentGenerator;
import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonStringArray;

/**
 * JSON Object Content Generator.
 * <p>
 * Created: 15th February 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class JsonObjectContentGenerator extends AbstractJsonContentGenerator {
  /**
   * Generate the content for the given JSON object.
   *
   * @param jsonObject the JSON object.
   */
  public void generateContent(JsonObject jsonObject) {
    if (jsonObject != null) {
      this.contentHandler.startJson();
      
      this.handleJsonObject(jsonObject);
      
      this.contentHandler.endJson();
    }
  }
  
  /**
   * Handle the given JSON object.
   *
   * @param jsonObject The JSON object.
   */
  private void handleJsonObject(JsonObject jsonObject) {
    if (jsonObject.isArray()) {
      JsonArray jsonArray = jsonObject.cast(JsonArray.class);
      
      this.contentHandler.startArray();
      
      for (int i = 0; i < jsonArray.getLength(); i++) {
        if (jsonArray.isNullElement(i)) {
          this.contentHandler.primitive(null);
        }
        else if (jsonArray.isBooleanElement(i)) {
          this.contentHandler.primitive(Boolean.valueOf(jsonArray.getBooleanElement(i)));
        }
        else if (jsonArray.isIntegerElement(i)) {
          this.contentHandler.primitive(Integer.valueOf(jsonArray.getIntegerElement(i)));
        }
        else if (jsonArray.isNumberElement(i)) {
          this.contentHandler.primitive(BigDecimal.valueOf(jsonArray.getNumberElement(i)));
        }
        else if (jsonArray.isStringElement(i)) {
          this.contentHandler.primitive(jsonArray.getStringElement(i));
        }
        else if (jsonArray.isObjectElement(i)) {
          this.handleJsonObject(jsonArray.getObjectElement(i));
        }
        else {
          assert false : "Unreachable condition";
        }
      }
      
      this.contentHandler.endArray();
    }
    else {
      this.contentHandler.startObject();
      
      JsonStringArray propertyNames = jsonObject.getPropertyNames();
      for (int i = 0; i < propertyNames.getLength(); i++) {
        String propertyName = propertyNames.get(i);
  
        this.contentHandler.memberName(propertyName);
        
        if (jsonObject.isNullProperty(propertyName)) {
          this.contentHandler.primitive(null);
        }
        else if (jsonObject.isBooleanProperty(propertyName)) {
          this.contentHandler.primitive(Boolean.valueOf(jsonObject.getBooleanProperty(propertyName)));
        }
        else if (jsonObject.isIntegerProperty(propertyName)) {
          this.contentHandler.primitive(Integer.valueOf(jsonObject.getIntegerProperty(propertyName)));
        }
        else if (jsonObject.isNumberProperty(propertyName)) {
          this.contentHandler.primitive(BigDecimal.valueOf(jsonObject.getNumberProperty(propertyName)));
        }
        else if (jsonObject.isStringProperty(propertyName)) {
          this.contentHandler.primitive(jsonObject.getStringProperty(propertyName));
        }
        else if (jsonObject.isObjectProperty(propertyName)) {
          this.handleJsonObject(jsonObject.getObjectProperty(propertyName));
        }
        else {
          assert false : "Unreachable condition";
        }
      }
      
      this.contentHandler.endObject();
    }
  }
}
