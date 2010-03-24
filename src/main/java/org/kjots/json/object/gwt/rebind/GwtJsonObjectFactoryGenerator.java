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
package org.kjots.json.object.gwt.rebind;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.kjots.json.object.gwt.client.impl.GwtJsonObjectFactoryImplBase;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectFactory;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * GWT JSON Object Factory Generator.
 * <p>
 * Created: 12th January 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.5
 */
public class GwtJsonObjectFactoryGenerator extends Generator {
  /**
   * Generate the code for the type with the given name.
   *
   * @param logger The logger.
   * @param context The context.
   * @param typeName The type name.
   * @return The name of the generated  class.
   * @throws UnableToCompleteException
   */
  @Override
  public String generate(TreeLogger logger, GeneratorContext context, String typeName)
    throws UnableToCompleteException {
    TypeOracle typeOracle = context.getTypeOracle();
    
    JClassType typeClassType = typeOracle.findType(typeName);
    if (!typeClassType.getQualifiedBinaryName().equals(JsonObjectFactory.class.getName())) {
      logger.log(TreeLogger.ERROR, "This generator only supports " + JsonObjectFactory.class.getName(), null);
      
      throw new UnableToCompleteException();
    }
    
    JClassType implBaseClassType = typeOracle.findType(GwtJsonObjectFactoryImplBase.class.getName());
    
    String implPackage =  implBaseClassType.getPackage().getName();
    String implClassName = "GwtJsonObjectFactoryImpl";
    
    PrintWriter printWriter = context.tryCreate(logger, implPackage, implClassName);
    if (printWriter != null) {
      ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(implPackage, implClassName);
      
      composerFactory.setSuperclass(implBaseClassType.getQualifiedSourceName());
      
      SourceWriter sourceWriter = composerFactory.createSourceWriter(context, printWriter);
      
      sourceWriter.println("@SuppressWarnings(\"unchecked\")");
      sourceWriter.println("public " + implClassName + "() {");
      sourceWriter.indent();
      
      for (Map.Entry<String, String> entry : this.getJsonObjectImplClasses(logger, context).entrySet()) {
        this.writeInstantiator(sourceWriter, entry.getKey(), entry.getValue(), null);
      }
      
      sourceWriter.outdent();
      sourceWriter.println("}");
      
      sourceWriter.commit(logger);
    }

    return implPackage + "." + implClassName;
  }
  
  /**
   * Write an instantiator for the given JSON object and implementation types.
   *
   * @param sourceWriter The source writer.
   * @param jsonObjectTypeName The name of the JSON object type.
   * @param jsonObjectImplTypeName The name of the JSON object implementation type.
   * @param jsObjectTypeName The name of the JavaScript object type.
   */
  private void writeInstantiator(SourceWriter sourceWriter, String jsonObjectTypeName, String jsonObjectImplTypeName, String jsObjectTypeName) {
    sourceWriter.println("this.registerJsonObjectInstantiator(" + jsonObjectTypeName + ".class, new JsonObjectInstantiator<" + jsonObjectTypeName + ">() {");
    sourceWriter.indent();
    sourceWriter.println("public final " + jsonObjectTypeName + " newInstance(" + JavaScriptObject.class.getName() + " jsObject) {");
    sourceWriter.indent();
    if (jsObjectTypeName != null) {
      sourceWriter.println("return new " + jsonObjectImplTypeName + "((" + jsObjectTypeName + ")jsObject.cast());");
    }
    else {
      sourceWriter.println("return new " + jsonObjectImplTypeName + "(jsObject);");
    }
    sourceWriter.outdent();
    sourceWriter.println("}");
    sourceWriter.outdent();
    sourceWriter.println("});");
  }
  
  /**
   * Retrieve the JSON object implementation classes.
   *
   * @param logger The logger.
   * @param context The context.
   * @return The JSON object implementation classes.
   * @throws NotFoundException 
   */
  private Map<String, String> getJsonObjectImplClasses(TreeLogger logger, GeneratorContext context)
    throws UnableToCompleteException {
    Map<String, String> jsonObjectImplClasses = new HashMap<String, String>();
    
    TypeOracle typeOracle = context.getTypeOracle();
    JClassType jsonObjectType = typeOracle.findType(JsonObject.class.getName());
    
    for (JClassType type : typeOracle.getTypes()) {
      if (type.isInterface() != null && type.isAssignableTo(jsonObjectType) && !(type.getPackage().equals(jsonObjectType.getPackage()) && type.getEnclosingType() == null)) {
        String typeName = type.getQualifiedSourceName();
        
        String implTypeName = typeName + "Impl";
        if (typeOracle.findType(implTypeName) == null) {
          implTypeName = new GwtJsonObjectGenerator().generate(logger, context, typeName);
        }
        
        jsonObjectImplClasses.put(typeName, implTypeName);
      }
    }
    
    return jsonObjectImplClasses;
  }
}
