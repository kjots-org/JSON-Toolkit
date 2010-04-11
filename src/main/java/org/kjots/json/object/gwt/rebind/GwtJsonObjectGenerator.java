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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JPackage;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.JTypeParameter;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import org.kjots.json.object.gwt.client.impl.GwtJsonObjectImpl;
import org.kjots.json.object.shared.JsonBooleanPropertyAdapter;
import org.kjots.json.object.shared.JsonFunction;
import org.kjots.json.object.shared.JsonNumberPropertyAdapter;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectArray;
import org.kjots.json.object.shared.JsonObjectMap;
import org.kjots.json.object.shared.JsonObjectPropertyAdapter;
import org.kjots.json.object.shared.JsonProperty;
import org.kjots.json.object.shared.JsonPropertyAdapter;
import org.kjots.json.object.shared.JsonStringPropertyAdapter;

/**
 * GWT JSON Object Generator.
 * <p>
 * Created: 6th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonObjectGenerator extends Generator {
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
    JClassType typeClassType = context.getTypeOracle().findType(typeName);
    
    JPackage typePackage = typeClassType.getPackage();
    if (typePackage == null) {
      logger.log(TreeLogger.ERROR, typeClassType.getQualifiedSourceName() + " is not in a package", null);
      
      throw new UnableToCompleteException();
    }
    
    if (typeClassType.isInterface() == null) {
      logger.log(TreeLogger.ERROR, typeClassType.getQualifiedSourceName() + " is not an interface", null);
      
      throw new UnableToCompleteException();
    }
    
    JClassType[] implementedInterfaces = typeClassType.getImplementedInterfaces();
    if (implementedInterfaces == null || implementedInterfaces.length == 0) {
      logger.log(TreeLogger.ERROR, typeClassType.getQualifiedSourceName() + " must extend at least one interface", null);
      
      throw new UnableToCompleteException();
    }
    
    String superClassImplClassName;
    
    JClassType mainImplementedInterface = implementedInterfaces[0];
    if (mainImplementedInterface.getQualifiedBinaryName().equals(JsonObject.class.getName())) {
      superClassImplClassName = GwtJsonObjectImpl.class.getName();
    }
    else {
      superClassImplClassName = new GwtJsonObjectGenerator().generate(logger, context, mainImplementedInterface.getQualifiedSourceName());
    }
    
    String implPackage = typePackage.getName();
    String implClassName = typeClassType.getName().replace('.', '_') + "Impl";
    
    PrintWriter printWriter = context.tryCreate(logger, implPackage, implClassName);
    if (printWriter != null) {
      ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(implPackage, implClassName);
      
      composerFactory.setSuperclass(superClassImplClassName);
      composerFactory.addImplementedInterface(typeClassType.getParameterizedQualifiedSourceName());
      
      SourceWriter sourceWriter = composerFactory.createSourceWriter(context, printWriter);
    
      sourceWriter.println("public " + implClassName + "(" + JavaScriptObject.class.getName() + " jsObject) {");
      sourceWriter.indent();
      sourceWriter.println("super(jsObject);");
      sourceWriter.outdent();
      sourceWriter.println("}");
      
      for (JMethod method : typeClassType.getMethods()) {
        sourceWriter.println();
        
        if (method.getAnnotation(JsonFunction.class) != null) {
          this.writeFunctionMethod(sourceWriter, logger, context, method, method.getAnnotation(JsonFunction.class));
        }
        else if (method.getAnnotation(JsonProperty.class) != null) {
          this.writePropertyMethod(sourceWriter, logger, context, method, method.getAnnotation(JsonProperty.class));
        }
        else {
          logger.log(TreeLogger.ERROR, method.getName() + "() is not annotated with suitable annotation", null);
        }
      }
      
      sourceWriter.commit(logger);
    }
    
    return implPackage + "." + implClassName;
  }
  
  /**
   * Write a function method.
   *
   * @param sourceWriter The source writer.
   * @param logger The logger.
   * @param context The context.
   * @param method The method.
   * @param jsonFunctionAnnotation The JSON function annotation.
   * @throws UnableToCompleteException
   */
  private void writeFunctionMethod(SourceWriter sourceWriter, TreeLogger logger, GeneratorContext context, JMethod method, JsonFunction jsonFunctionAnnotation)
    throws UnableToCompleteException {
    Class<?> functionClass = jsonFunctionAnnotation.klass();
    String functionMethodName = jsonFunctionAnnotation.method();
    
    StringBuilder typeParametersBuilder = new StringBuilder();
    
    JTypeParameter[] typeParameters = method.getTypeParameters();
    if (typeParameters.length != 0) {
      typeParametersBuilder.append("<");
      
      for (int i = 0; i < typeParameters.length; i++) {
        if (i != 0) {
          typeParametersBuilder.append(", ");
        }

        typeParametersBuilder.append(typeParameters[i].getQualifiedSourceName());
      }
      
      typeParametersBuilder.append("> ");
    }
    
    String returnTypeName = this.getTypeName(method.getReturnType());
    
    JClassType functionClassType = this.getType(logger, context, functionClass.getName().replace('$', '.'));
    
    StringBuilder methodParametersBuilder = new StringBuilder();
    StringBuilder functionArgumentsBuilder = new StringBuilder("this");
    
    JParameter[] methodParameters = method.getParameters();
    for (int i = 0; i < methodParameters.length; i++) {
      if (i != 0) {
        methodParametersBuilder.append(", ");
      }
      
      methodParametersBuilder.append(this.getTypeName(methodParameters[i].getType()));
      methodParametersBuilder.append(" arg").append(i);
      
      functionArgumentsBuilder.append(", arg").append(i);
    }
    
    sourceWriter.println("@Override");
    sourceWriter.println("public final " + typeParametersBuilder.toString() + returnTypeName + " " + method.getName() + "(" + methodParametersBuilder.toString() + ") {");
    sourceWriter.indent();
    sourceWriter.println((!returnTypeName.equals("void") ? "return " : "") + functionClassType.getQualifiedSourceName() + "." + functionMethodName + "(" + functionArgumentsBuilder.toString() + ");");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a property method.
   *
   * @param sourceWriter The source writer.
   * @param logger The logger.
   * @param context The context.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   * @throws UnableToCompleteException
   */
  private void writePropertyMethod(SourceWriter sourceWriter, TreeLogger logger, GeneratorContext context, JMethod method, JsonProperty jsonPropertyAnnotation)
    throws UnableToCompleteException {
    switch (jsonPropertyAnnotation.operation()) {
    case HAS:
      this.writeHasPropertyMethod(sourceWriter, logger, method, jsonPropertyAnnotation);
      
      return;
      
    case IS_NULL:
      this.writeIsNullPropertyMethod(sourceWriter, logger, method, jsonPropertyAnnotation);
      
      return;
      
    case DELETE:
      this.writeDeletePropertyMethod(sourceWriter, logger, method, jsonPropertyAnnotation);
      
      return;
      
    case GET:
      this.writeGetPropertyMethod(sourceWriter, logger, context, method, jsonPropertyAnnotation);
      
      return;
      
    case SET:
      this.writeSetPropertyMethod(sourceWriter, logger, context, method, jsonPropertyAnnotation);
      
      return;
      
    default:
      logger.log(TreeLogger.ERROR, method.getName() + "() is annotated with unsupported operation type " + jsonPropertyAnnotation.operation(), null);
      
      throw new UnableToCompleteException();
    }
  }
  
  /**
   * Write a has property method.
   *
   * @param sourceWriter The source writer.
   * @param logger The logger.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   * @throws UnableToCompleteException
   */
  private void writeHasPropertyMethod(SourceWriter sourceWriter, TreeLogger logger, JMethod method, JsonProperty jsonPropertyAnnotation)
    throws UnableToCompleteException {
    JParameter[] parameters = method.getParameters();
    if (parameters.length != 0) {
      logger.log(TreeLogger.ERROR, method.getName() + "() must have no parameters", null);
      
      throw new UnableToCompleteException();
    }
    
    JType returnType = method.getReturnType();
    if (!returnType.getSimpleSourceName().equals("boolean")) {
      logger.log(TreeLogger.ERROR, method.getName() + "() must have a boolean return type", null);
      
      throw new UnableToCompleteException();
    }
    
    this.writeHasPropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name());
  }
  
  /**
   * Write an is null property method.
   *
   * @param sourceWriter The source writer.
   * @param logger The logger.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   * @throws UnableToCompleteException
   */
  private void writeIsNullPropertyMethod(SourceWriter sourceWriter, TreeLogger logger, JMethod method, JsonProperty jsonPropertyAnnotation)
    throws UnableToCompleteException {
    JParameter[] parameters = method.getParameters();
    if (parameters.length != 0) {
      logger.log(TreeLogger.ERROR, method.getName() + "() must have no parameters", null);
      
      throw new UnableToCompleteException();
    }
    
    JType returnType = method.getReturnType();
    if (!returnType.getSimpleSourceName().equals("boolean")) {
      logger.log(TreeLogger.ERROR, method.getName() + "() must have a boolean return type", null);
      
      throw new UnableToCompleteException();
    }
    
    this.writeIsNullPropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name());
  }
  
  /**
   * Write an delete property method.
   *
   * @param sourceWriter The source writer.
   * @param logger The logger.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   * @throws UnableToCompleteException
   */
  private void writeDeletePropertyMethod(SourceWriter sourceWriter, TreeLogger logger, JMethod method, JsonProperty jsonPropertyAnnotation)
    throws UnableToCompleteException {
    JParameter[] parameters = method.getParameters();
    if (parameters.length != 0) {
      logger.log(TreeLogger.ERROR, method.getName() + "() must have no parameters", null);
      
      throw new UnableToCompleteException();
    }
    
    JType returnType = method.getReturnType();
    if (!returnType.getSimpleSourceName().equals("boolean")) {
      logger.log(TreeLogger.ERROR, method.getName() + "() must have a boolean return type", null);
      
      throw new UnableToCompleteException();
    }
    
    this.writeDeletePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name());
  }
  
  /**
   * Write a get property method.
   *
   * @param sourceWriter The source writer.
   * @param logger The logger.
   * @param context The context.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   * @throws UnableToCompleteException
   */
  private void writeGetPropertyMethod(SourceWriter sourceWriter, TreeLogger logger, GeneratorContext context, JMethod method, JsonProperty jsonPropertyAnnotation)
    throws UnableToCompleteException {
    JParameter[] parameters = method.getParameters();
    if (parameters.length != 0) {
      logger.log(TreeLogger.ERROR, method.getName() + "() must have no parameters", null);
      
      throw new UnableToCompleteException();
    }
    
    String returnTypeName = method.getReturnType().getQualifiedSourceName();

    Class<? extends JsonPropertyAdapter> adapterClass = jsonPropertyAnnotation.adapter();
    if (!adapterClass.equals(JsonPropertyAdapter.class)) {
      this.writeGetAdaptedPropertyMethod(sourceWriter, logger, context, method.getName(), jsonPropertyAnnotation.name(), returnTypeName, adapterClass.getName().replace('$', '.'));
    }
    else {
      if (returnTypeName.equals(Boolean.class.getName())) {
        this.writeGetJsonPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), Boolean.class.getName(), "getBooleanProperty");
      }
      else if (returnTypeName.equals(Number.class.getName())) {
        this.writeGetJsonPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), Number.class.getName(), "getNumberProperty");
      }
      else if (returnTypeName.equals(String.class.getName())) {
        this.writeGetJsonPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), String.class.getName(), "getStringProperty");
      }
      else if (returnTypeName.equals(JsonObjectArray.class.getName()) || returnTypeName.equals(JsonObjectMap.class.getName())) {
        String elementTypeName;
        
        JParameterizedType parameterizedReturnType = method.getReturnType().isParameterized();
        if (parameterizedReturnType != null) {
          elementTypeName = parameterizedReturnType.getTypeArgs()[0].getQualifiedSourceName();
        }
        else {
          elementTypeName = JsonObject.class.getName();
        }
        
        this.writeGetObjectCompositePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), returnTypeName, elementTypeName);
      }
      else if (returnTypeName.equals(boolean.class.getName())) {
        this.writeGetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "boolean", "java.lang.Boolean", "getBooleanProperty", "false");
      }
      else if (returnTypeName.equals(byte.class.getName())) {
        this.writeGetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "byte", "java.lang.Number", "getNumberProperty", "0");
      }
      else if (returnTypeName.equals(short.class.getName())) {
        this.writeGetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "short", "java.lang.Number", "getNumberProperty", "0");
      }
      else if (returnTypeName.equals(int.class.getName())) {
        this.writeGetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "int", "java.lang.Number", "getNumberProperty", "0");
      }
      else if (returnTypeName.equals(long.class.getName())) {
        this.writeGetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "long", "java.lang.Number", "getNumberProperty", "0");
      }
      else if (returnTypeName.equals(float.class.getName())) {
        this.writeGetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "float", "java.lang.Number", "getNumberProperty", "0.0f");
      }
      else if (returnTypeName.equals(double.class.getName())) {
        this.writeGetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "double", "java.lang.Number", "getNumberProperty", "0.0");
      }
      else if (returnTypeName.equals(char.class.getName())) {
        this.writeGetJavaCharacterPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name());
      }
      else {
        this.writeGetObjectPropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), returnTypeName);
      }
    }
  }
  
  /**
   * Write a set property method.
   *
   * @param sourceWriter The source writer.
   * @param logger The logger.
   * @param context The context.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   * @throws UnableToCompleteException
   */
  private void writeSetPropertyMethod(SourceWriter sourceWriter, TreeLogger logger, GeneratorContext context, JMethod method, JsonProperty jsonPropertyAnnotation)
    throws UnableToCompleteException {
    JType returnType = method.getReturnType();
    if (!returnType.getSimpleSourceName().equals("void")) {
      logger.log(TreeLogger.ERROR, method.getName() + "() must have a void return type", null);
      
      throw new UnableToCompleteException();
    }
    
    JParameter[] parameters = method.getParameters();
    if (parameters.length != 1) {
      logger.log(TreeLogger.ERROR, method.getName() + "() must have exactly one parameter", null);
      
      throw new UnableToCompleteException();
    }
    
    String parameterTypeName = parameters[0].getType().getQualifiedSourceName();
    
    Class<? extends JsonPropertyAdapter> adapterClass = jsonPropertyAnnotation.adapter();
    if (!adapterClass.equals(JsonPropertyAdapter.class)) {
      this.writeSetAdaptedPropertyMethod(sourceWriter, logger, context, method.getName(), jsonPropertyAnnotation.name(), parameterTypeName, adapterClass.getName().replace('$', '.'));
    }
    else {
      if (parameterTypeName.equals(Boolean.class.getName())) {
        this.writeSetJsonPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), Boolean.class.getName(), "setBooleanProperty");
      }
      else if (parameterTypeName.equals(Number.class.getName())) {
        this.writeSetJsonPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), Number.class.getName(), "setNumberProperty"); 
      }
      else if (parameterTypeName.equals(String.class.getName())) {
        this.writeSetJsonPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), String.class.getName(), "setStringProperty");
      }
      else if (parameterTypeName.equals(boolean.class.getName())) {
        this.writeSetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "boolean", "java.lang.Boolean", "setBooleanProperty");
      }
      else if (parameterTypeName.equals(byte.class.getName())) {
        this.writeSetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "byte", "java.lang.Byte", "setNumberProperty");
      }
      else if (parameterTypeName.equals(short.class.getName())) {
        this.writeSetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "short", "java.lang.Short", "setNumberProperty");
      }
      else if (parameterTypeName.equals(int.class.getName())) {
        this.writeSetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "int", "java.lang.Integer", "setNumberProperty");
      }
      else if (parameterTypeName.equals(long.class.getName())) {
        this.writeSetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "long", "java.lang.Long", "setNumberProperty");
      }
      else if (parameterTypeName.equals(float.class.getName())) {
        this.writeSetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "float", "java.lang.Float", "setNumberProperty");
      }
      else if (parameterTypeName.equals(double.class.getName())) {
        this.writeSetJavaPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), "double", "java.lang.Double", "setNumberProperty");
      }
      else if (parameterTypeName.equals(char.class.getName())) {
        this.writeSetJavaCharacterPrimitivePropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name());
      }
      else {
        this.writeSetObjectPropertyMethod(sourceWriter, method.getName(), jsonPropertyAnnotation.name(), parameterTypeName);
      }
    }
  }
  
  /**
   * Write a has property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   */
  private void writeHasPropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final boolean " + methodName + "() {");
    sourceWriter.indent();
    sourceWriter.println("return this.hasProperty(\"" + propertyName + "\");");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write an is null property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   */
  private void writeIsNullPropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final boolean " + methodName + "() {");
    sourceWriter.indent();
    sourceWriter.println("return this.isNullProperty(\"" + propertyName + "\");");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a delete property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   */
  private void writeDeletePropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final boolean " + methodName + "() {");
    sourceWriter.indent();
    sourceWriter.println("return this.deleteProperty(\"" + propertyName + "\");");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a get JSON primitive property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param primitiveTypeName The name of the JSON primitive type.
   * @param primitiveMethodName The name of the JSON primitive method.
   */
  private void writeGetJsonPrimitivePropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName, String primitiveTypeName, String primitiveMethodName) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final " + primitiveTypeName + " " + methodName + "() {");
    sourceWriter.indent();
    sourceWriter.println("return this." + primitiveMethodName + "(\"" + propertyName + "\");");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a set JSON primitive property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param primitiveTypeName The name of the JSON primitive type.
   * @param primitiveMethodName The name of the JSON primitive method.
   */
  private void writeSetJsonPrimitivePropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName, String primitiveTypeName, String primitiveMethodName) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final void " + methodName + "(" + primitiveTypeName + " " + propertyName + ") {");
    sourceWriter.indent();
    sourceWriter.println("this." + primitiveMethodName + "(\"" + propertyName + "\", " + propertyName + ");");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a get Java primitive property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param primitiveTypeName The name of the Java primitive type.
   * @param primitiveWrapperTypeName The name of the Java primitive wrapper type.
   * @param jsonPrimitiveMethodName The name of the JSON primitive method.
   * @param defaultPrimitiveValue The default primitive value.
   */
  private void writeGetJavaPrimitivePropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName, String primitiveTypeName, String primitiveWrapperTypeName, String jsonPrimitiveMethodName, String defaultPrimitiveValue) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final " + primitiveTypeName + " " + methodName + "() {");
    sourceWriter.indent();
    sourceWriter.println(primitiveWrapperTypeName + " _" + propertyName + " = this." + jsonPrimitiveMethodName + "(\"" + propertyName + "\");");
    sourceWriter.println();
    sourceWriter.println("return _" + propertyName + " != null ? _" + propertyName + "." + primitiveTypeName + "Value() : " + defaultPrimitiveValue + ";");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a set Java primitive property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param primitiveTypeName The name of the Java primitive type.
   * @param primitiveWrapperTypeName The name of the Java primitive wrapper type.
   * @param jsonPrimitiveMethodName The name of the JSON primitive method.
   */
  private void writeSetJavaPrimitivePropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName, String primitiveTypeName, String primitiveWrapperTypeName, String jsonPrimitiveMethodName) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final void " + methodName + "(" + primitiveTypeName + " " + propertyName + ") {");
    sourceWriter.indent();
    sourceWriter.println("this." + jsonPrimitiveMethodName + "(\"" + propertyName + "\", " + primitiveWrapperTypeName + ".valueOf(" + propertyName + "));");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a get Java character primitive property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   */
  private void writeGetJavaCharacterPrimitivePropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final char " + methodName + "() {");
    sourceWriter.indent();
    sourceWriter.println("java.lang.String _" + propertyName + " = this.getStringProperty(\"" + propertyName + "\");");
    sourceWriter.println();
    sourceWriter.println("return _" + propertyName + " != null && !_" + propertyName + ".isEmpty() ? _" + propertyName + ".charAt(0) : '\\0';");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a set Java character primitive property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   */
  private void writeSetJavaCharacterPrimitivePropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final void " + methodName + "(char " + propertyName + ") {");
    sourceWriter.indent();
    sourceWriter.println("this.setStringProperty(\"" + propertyName + "\", java.lang.Character.toString(" + propertyName + "));");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a get object property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyTypeName The name of the property type.
   */
  private void writeGetObjectPropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName, String propertyTypeName) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final " + propertyTypeName + " " + methodName + "() {");
    sourceWriter.indent();
    sourceWriter.println("return this.getObjectProperty(\"" + propertyName + "\", " + propertyTypeName + ".class);");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a set object property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyTypeName The name of the property type.
   */
  private void writeSetObjectPropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName, String propertyTypeName) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final void " + methodName + "(" + propertyTypeName + " " + propertyName + ") {");
    sourceWriter.indent();
    sourceWriter.println("this.setObjectProperty(\"" + propertyName + "\", " + propertyName + ");");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a get object composite property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyTypeName The name of the property type.
   */
  private void writeGetObjectCompositePropertyMethod(SourceWriter sourceWriter, String methodName, String propertyName, String propertyTypeName, String elementTypeName) {
    sourceWriter.println("@Override");
    sourceWriter.println("public final " + propertyTypeName + " " + methodName + "() {");
    sourceWriter.indent();
    sourceWriter.println("return this.getObjectProperty(\"" + propertyName + "\", " + propertyTypeName + ".class).castElement(" + elementTypeName + ".class);");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a get adapted property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param logger The logger.
   * @param context The context.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyTypeName The name of the property type.
   * @param adapterTypeName The name of the adapter type.
   * @throws UnableToCompleteException
   */
  private void writeGetAdaptedPropertyMethod(SourceWriter sourceWriter, TreeLogger logger, GeneratorContext context, String methodName, String propertyName, String propertyTypeName, String adapterTypeName)
    throws UnableToCompleteException {
    sourceWriter.println("@Override");
    sourceWriter.println("public final " + propertyTypeName + " " + methodName + "() {");
    sourceWriter.indent();
    
    JClassType adapterType = this.getType(logger, context, adapterTypeName);
    if (adapterType.isAssignableTo(this.getType(logger, context, JsonBooleanPropertyAdapter.class.getName()))) {
      sourceWriter.println("boolean _" + propertyName + " = this.getBooleanProperty(\"" + propertyName + "\");");
    }
    else if (adapterType.isAssignableTo(this.getType(logger, context, JsonNumberPropertyAdapter.class.getName()))) {
      sourceWriter.println(Number.class.getName() + " _" + propertyName + " = this.getNumberProperty(\"" + propertyName + "\");");
    }
    else if (adapterType.isAssignableTo(this.getType(logger, context, JsonStringPropertyAdapter.class.getName()))) {
      sourceWriter.println(String.class.getName() + " _" + propertyName + " = this.getStringProperty(\"" + propertyName + "\");");
    }
    else if (adapterType.isAssignableTo(this.getType(logger, context, JsonObjectPropertyAdapter.class.getName()))) {
      JParameterizedType jsonObjectPropertyType = (JParameterizedType)this.getImplementedInterface(adapterType, JsonObjectPropertyAdapter.class.getName());
      JClassType jsonObjectType = jsonObjectPropertyType.getTypeArgs()[1];
      
      sourceWriter.println(jsonObjectType.getQualifiedSourceName() + " _" + propertyName + " = this.getObjectProperty(\"" + propertyName + "\", " + jsonObjectType.getQualifiedSourceName() + ".class);");
    }
    else {
      logger.log(TreeLogger.ERROR, "Unsupported adapter type " + adapterTypeName, null);
      
      throw new UnableToCompleteException();
    }
    
    sourceWriter.println();
    sourceWriter.println("return new " + adapterType.getQualifiedSourceName() + "().fromJsonProperty(_" + propertyName + ");");
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Write a set adapted property method for the property with the given name.
   *
   * @param sourceWriter The source writer.
   * @param logger The logger.
   * @param context The context.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyTypeName The name of the property type.
   * @param adapterTypeName The name of the adapter type.
   * @throws UnableToCompleteException
   */
  private void writeSetAdaptedPropertyMethod(SourceWriter sourceWriter, TreeLogger logger, GeneratorContext context, String methodName, String propertyName, String propertyTypeName, String adapterTypeName) 
    throws UnableToCompleteException {
    sourceWriter.println("@Override");
    sourceWriter.println("public final void " + methodName + "(" + propertyTypeName + " " + propertyName + ") {");
    sourceWriter.indent();
    
    JClassType adapterType = this.getType(logger, context, adapterTypeName);
    if (adapterType.isAssignableTo(this.getType(logger, context, JsonBooleanPropertyAdapter.class.getName()))) {
      sourceWriter.println("boolean _" + propertyName + " = new " + adapterType.getQualifiedSourceName() + "().toJsonProperty(" + propertyName + ");");
      sourceWriter.println();
      sourceWriter.println("this.setBooleanProperty(\"" + propertyName + "\", _" + propertyName + ");");
    }
    else if (adapterType.isAssignableTo(this.getType(logger, context, JsonNumberPropertyAdapter.class.getName()))) {
      sourceWriter.println(Number.class.getName() + " _" + propertyName + " = new " + adapterType.getQualifiedSourceName() + "().toJsonProperty(" + propertyName + ");");
      sourceWriter.println();
      sourceWriter.println("this.setNumberProperty(\"" + propertyName + "\", _" + propertyName + ");");
    }
    else if (adapterType.isAssignableTo(this.getType(logger, context, JsonStringPropertyAdapter.class.getName()))) {
      sourceWriter.println(String.class.getName() + " _" + propertyName + " = new " + adapterType.getQualifiedSourceName() + "().toJsonProperty(" + propertyName + ");");
      sourceWriter.println();
      sourceWriter.println("this.setStringProperty(\"" + propertyName + "\", _" + propertyName + ");");
    }
    else if (adapterType.isAssignableTo(this.getType(logger, context, JsonObjectPropertyAdapter.class.getName()))) {
      JParameterizedType jsonObjectPropertyType = (JParameterizedType)this.getImplementedInterface(adapterType, JsonObjectPropertyAdapter.class.getName());
      JClassType jsonObjectType = jsonObjectPropertyType.getTypeArgs()[1];
      
      sourceWriter.println(jsonObjectType.getQualifiedSourceName() + " _" + propertyName + " = new " + adapterType.getQualifiedSourceName() + "().toJsonProperty(" + propertyName + ");");
      sourceWriter.println();
      sourceWriter.println("this.setObjectProperty(\"" + propertyName + "\", _" + propertyName + ");");
    }
    else {
      logger.log(TreeLogger.ERROR, "Unsupported adapter type " + adapterTypeName, null);
      
      throw new UnableToCompleteException();
    }
    
    sourceWriter.outdent();
    sourceWriter.println("}");
  }
  
  /**
   * Retrieve the implemented interface with the given name from the given
   * type.
   *
   * @param type The type.
   * @param interfaceName The name of the interface.
   * @return The interface.
   */
  private JClassType getImplementedInterface(JClassType type, String interfaceName) {
    for (JClassType implementedInterface : type.getImplementedInterfaces()) {
      if (implementedInterface.getQualifiedSourceName().equals(interfaceName))  {
        return implementedInterface;
      }
      
      implementedInterface = this.getImplementedInterface(implementedInterface, interfaceName);
      if (implementedInterface != null) {
        return implementedInterface;
      }
    }
    
    JClassType superType = type.getSuperclass();
    if (superType != null) {
      JClassType implementedInterface = this.getImplementedInterface(superType, interfaceName);
      if (implementedInterface != null) {
        return implementedInterface;
      }
    }
    
    return null;
  }
  
  /**
   * Retrieve the type with the given name.
   *
   * @param logger The logger.
   * @param context The context.
   * @param typeName The name of the type.
   * @return The type.
   * @throws UnableToCompleteException
   */
  private JClassType getType(TreeLogger logger, GeneratorContext context, String typeName) 
    throws UnableToCompleteException {
    try {
      return context.getTypeOracle().getType(typeName);
    }
    catch (NotFoundException nfe) {
      logger.log(TreeLogger.ERROR, "Cannot find type " + typeName, nfe);
      
      throw new UnableToCompleteException();
    }
  }

  /**
   * Retrieve the name of the given type.
   *
   * @param type The type.
   * @return The name of the type.
   */
  private String getTypeName(JType type) {
    if (type instanceof JTypeParameter) {
      JTypeParameter typeParameter = (JTypeParameter)type;
  
      return typeParameter.getName();
    }
    else {
      return type.getQualifiedSourceName();
    }
  }
}
