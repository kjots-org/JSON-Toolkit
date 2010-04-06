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
package org.kjots.json.object;

import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.CHECKCAST;
import static org.objectweb.asm.Opcodes.DCONST_0;
import static org.objectweb.asm.Opcodes.DOUBLE;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.FCONST_0;
import static org.objectweb.asm.Opcodes.FLOAT;
import static org.objectweb.asm.Opcodes.GOTO;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.IFNE;
import static org.objectweb.asm.Opcodes.IFNULL;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INTEGER;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.LCONST_0;
import static org.objectweb.asm.Opcodes.LONG;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_6;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

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
 * JSON Object Generator Base.
 * <p>
 * Created: 5th March 2010
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public abstract class JsonObjectGeneratorBase<T extends JsonObject> {
  /**
   * Constructor Argument.
   * <p>
   * Created: 9th March 2010.
   */
  protected static class ConstructorArgument {
    /** The name of the variable. */
    private final String variableName;
    
    /** The internal name of the type. */
    private final String typeIName;
    
    /**
     * Create the constructor signature for the given arguments.
     *
     * @param constructorArguments The constructor arguments.
     * @return The constructor signature.
     */
    public static String createConstructorSignature(ConstructorArgument[] constructorArguments) {
      StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append("(");
      for (ConstructorArgument constructorArgument : constructorArguments) {
        stringBuffer.append(constructorArgument.getTypeIName());
      }
      stringBuffer.append(")V");
      
      return stringBuffer.toString();
    }

    /**
     * Construct a new Constructor Argument.
     *
     * @param variableName The name of the variable.
     * @param typeIName The internal name of the type.
     */
    public ConstructorArgument(String variableName, String typeIName) {
      this.variableName = variableName;
      this.typeIName = typeIName;
    }

    /**
     * Retrieve the name of the variable.
     *
     * @return The name of the variable.
     */
    public String getVariableName() {
      return this.variableName;
    }

    /**
     * Retrieve the internal name of the type.
     *
     * @return The internal name of the type.
     */
    public String getTypeIName() {
      return this.typeIName;
    }
  }
  
  /** The JSON object type. */
  private static final Type JSON_OBJECT_TYPE = Type.getType(JsonObject.class);
  
  /** The class of the JSON object implementation. */
  private final Class<T> jsonObjectImplClass;
  
  /**
   * Retrieve the class of the implementation of the JSON object with the given
   * class.
   * 
   * @param jsonObjectClass The class of the JSON object.
   * @return The class of the implementation of the JSON object.
   */
  @SuppressWarnings("unchecked")
  public Class<? extends T> getJsonObjectImplClass(Class<? extends JsonObject> jsonObjectClass) {
    Class<?> jsonObjectImplClass;
    
    if (this.jsonObjectImplClass.isAssignableFrom(jsonObjectClass)) {
      jsonObjectImplClass = jsonObjectClass;
    }
    else {
      try {
        jsonObjectImplClass = Class.forName(jsonObjectClass.getName() + "$" + this.jsonObjectImplClass.getSimpleName(), true, this.getClass().getClassLoader());
      }
      catch (ClassNotFoundException cnfe) {
        jsonObjectImplClass = this.defineImplClass(this.generateImplClassBytes(jsonObjectClass));
      }
    }
    
    return (Class<? extends T>)jsonObjectImplClass;
  }
  
  /**
   * Construct a new SON Object Generator Base.
   *
   * @param jsonObjectImplClass The class of the JSON object implementation.
   */
  protected JsonObjectGeneratorBase(Class<T> jsonObjectImplClass) {
    this.jsonObjectImplClass = jsonObjectImplClass;
  }
  
  /**
   * Retrieve the constructor of the JSON object implementation.
   * <p>
   * The default implementation of this method returns the first defined
   * public constructor.
   *
   * @return The constructor of the JSON object implementation.
   */
  @SuppressWarnings("unchecked")
  protected Constructor<T> getJsonObjectImplConstructor() {
    Constructor<?>[] constructors = this.jsonObjectImplClass.getConstructors();
    
    return (Constructor<T>)constructors[0];
  }
  
  /**
   * Retrieve the constructor arguments.
   * <p>
   * The default implementation of this method creates an array of class-based
   * constructor arguments derived from the return value of {@link #getJsonObjectImplConstructor()}.
   *
   * @return The constructor arguments.
   */
  protected ConstructorArgument[] getConstructorArguments() {
    Class<?>[] constructorParameterTypes = this.getJsonObjectImplConstructor().getParameterTypes();
    ConstructorArgument[] constructorArguments = new ConstructorArgument[constructorParameterTypes.length];
    
    for (int i = 0; i < constructorParameterTypes.length; i++) {
      constructorArguments[i] = new ConstructorArgument("arg" + i, "L" + Type.getInternalName(constructorParameterTypes[i]) + ";");
    }
    
    return constructorArguments;
  }
  
  /**
   * Define the class of the implementation of the JSON object from the given class bytes.
   *
   * @param implClassBytes The class bytes of the implementation of the JSON object.
   * @return The class of the implementation of the JSON object.
   */
  @SuppressWarnings("unchecked")
  private Class<? extends T> defineImplClass(byte[] implClassBytes) {
    // Retrieve the ClassLoader.defineClass() method.
    java.lang.reflect.Method defineClassMethod;
    try {
      defineClassMethod = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
    }
    catch (NoSuchMethodException nsme) {
      throw new IllegalStateException(nsme);
    }
    
    // Invoke the ClassLoader.defineClass() method.
    defineClassMethod.setAccessible(true);
    try {
      return (Class<? extends T>)defineClassMethod.invoke(this.getClass().getClassLoader(), null, implClassBytes, 0, implClassBytes.length);
    } 
    catch (IllegalAccessException iae) {
      throw new IllegalStateException(iae);
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
    finally {
      defineClassMethod.setAccessible(false);
    }
  }

  /**
   * Generate the class bytes of the implementation of the JSON object with the
   * given class.
   *
   * @param jsonObjectClass The class of the JSON object.
   * @return The class bytes of the implementation of the JSON object.
   */
  @SuppressWarnings("unchecked")
  private byte[] generateImplClassBytes(Class<? extends JsonObject> jsonObjectClass) {
    Package classPackage = jsonObjectClass.getPackage();
    if (classPackage == null || classPackage.getName().isEmpty()) {
      throw new IllegalArgumentException(jsonObjectClass.getName() + " is not in a package");
    }
    
    if (!jsonObjectClass.isInterface()) {
      throw new IllegalArgumentException(jsonObjectClass.getName() + " is not an interface");
    }
    
    Class<?>[] implementedInterfaces = jsonObjectClass.getInterfaces();
    if (implementedInterfaces.length == 0) {
      throw new IllegalArgumentException(jsonObjectClass.getName() + " must extend at least one interface");
    }
    
    Class<? extends T> superJsonObjectImplClass;
    
    Class<?> mainImplementedInterface = implementedInterfaces[0];
    if (mainImplementedInterface.equals(JsonObject.class)) {
      superJsonObjectImplClass = this.jsonObjectImplClass;
    }
    else {
      superJsonObjectImplClass = this.getJsonObjectImplClass((Class<? extends JsonObject>)mainImplementedInterface);
    }
    
    ClassWriter classWriter = new ClassWriter(0);
    
    String jsonObjectIClassName = Type.getInternalName(jsonObjectClass);
    Type jsonObjectImplType = Type.getObjectType(jsonObjectIClassName + "$" + this.jsonObjectImplClass.getSimpleName());
    Type superJsonObjectImplType = Type.getType(superJsonObjectImplClass);
    
    classWriter.visit(V1_6, ACC_PUBLIC + ACC_SUPER, jsonObjectImplType.getInternalName(), null, superJsonObjectImplType.getInternalName(), new String[] { jsonObjectIClassName });
    
    this.generateConstructor(classWriter, jsonObjectImplType, superJsonObjectImplType);
    
    for (java.lang.reflect.Method method : jsonObjectClass.getDeclaredMethods()) {
      if (method.getAnnotation(JsonFunction.class) != null) {
        this.generateFunctionMethod(classWriter, jsonObjectImplType, Method.getMethod(method), method.getAnnotation(JsonFunction.class));
      }
      else if (method.getAnnotation(JsonProperty.class) != null) {
        this.generatePropertyMethod(classWriter, jsonObjectImplType, method, Method.getMethod(method), method.getAnnotation(JsonProperty.class));
      }
      else {
        throw new IllegalArgumentException(method.getName() + "() is not annotated with suitable annotation");
      }
    }
    
    return classWriter.toByteArray(); 
  }
  
  /**
   * Generate the constructor.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param superJsonObjectImplType The type of the super JSON object implementation.
   */
  private void generateConstructor(ClassWriter classWriter, Type jsonObjectImplType, Type superJsonObjectImplType) {
    ConstructorArgument[] constructorArguments = this.getConstructorArguments();
    String constructorSignature = this.createConstructorSignature(constructorArguments);
    
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", constructorSignature, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    for (int i = 0; i < constructorArguments.length; i++) {
      methodVisitor.visitVarInsn(ALOAD, i + 1);
    }
    methodVisitor.visitMethodInsn(INVOKESPECIAL, superJsonObjectImplType.getInternalName(), "<init>", constructorSignature);
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    for (int i = 0; i < constructorArguments.length; i++) {
      methodVisitor.visitLocalVariable(constructorArguments[i].getVariableName(), constructorArguments[i].getTypeIName(), null, start, end, i + 1);
    }
    methodVisitor.visitMaxs(constructorArguments.length + 1, constructorArguments.length + 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a function method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   */
  private void generateFunctionMethod(ClassWriter classWriter, Type jsonObjectImplType, Method method, JsonFunction jsonFunctionAnnotation) {
    Type returnType = method.getReturnType();
    Type[] argumentTypes = method.getArgumentTypes();
    
    int maxLocals = 1;
    
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, method.getName(), method.getDescriptor(), null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    for (int i = 0, index = 1; i < argumentTypes.length; i++) {
      Type argumentType = argumentTypes[i];
      
      methodVisitor.visitVarInsn(argumentType.getOpcode(ILOAD), index);
      
      index += argumentType.getSize();
    }
    methodVisitor.visitMethodInsn(INVOKESTATIC, Type.getType(jsonFunctionAnnotation.klass()).getInternalName(), jsonFunctionAnnotation.method(), "(" + JSON_OBJECT_TYPE.getDescriptor() + method.getDescriptor().substring(1));
    methodVisitor.visitInsn(returnType.getOpcode(IRETURN));
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    for (int i = 0; i < argumentTypes.length; i++) {
      Type argumentType = argumentTypes[i];
      
      methodVisitor.visitLocalVariable("arg" + i, argumentType.getDescriptor(), null, start, end, maxLocals);
      
      maxLocals += argumentType.getSize();
    }
    methodVisitor.visitMaxs(Math.max(maxLocals, returnType.getSize()), maxLocals);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a property method. 
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param javaMethod The java method.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generatePropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, java.lang.reflect.Method javaMethod, Method method, JsonProperty jsonPropertyAnnotation) {
    switch (jsonPropertyAnnotation.operation()) {
    case HAS:
      this.generateHasPropertyMethod(classWriter, jsonObjectImplType, method, jsonPropertyAnnotation);
      
      return;
      
    case IS_NULL:
      this.generateIsNullPropertyMethod(classWriter, jsonObjectImplType, method, jsonPropertyAnnotation);
      
      return;
      
    case DELETE:
      this.generateDeletePropertyMethod(classWriter, jsonObjectImplType, method, jsonPropertyAnnotation);
      
      return;
      
    case GET:
      this.generateGetPropertyMethod(classWriter, jsonObjectImplType, javaMethod, method, jsonPropertyAnnotation);
      
      return;
      
    case SET:
      this.generateSetPropertyMethod(classWriter, jsonObjectImplType, method, jsonPropertyAnnotation);
      
      return;
      
    default:
      throw new IllegalArgumentException(javaMethod.getName() + "() is annotated with unsupported operation type " + jsonPropertyAnnotation.operation());
    }
  }

  /**
   * Generate a has property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateHasPropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, Method method, JsonProperty jsonPropertyAnnotation) {
    if (!method.getReturnType().equals(Type.BOOLEAN_TYPE)) { 
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    if (method.getArgumentTypes().length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, method.getName(), "()Z", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(jsonPropertyAnnotation.name());
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "hasProperty", "(Ljava/lang/String;)Z");
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate an is null property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateIsNullPropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, Method method, JsonProperty jsonPropertyAnnotation) {
    if (!method.getReturnType().equals(Type.BOOLEAN_TYPE)) { 
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    if (method.getArgumentTypes().length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, method.getName(), "()Z", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(jsonPropertyAnnotation.name());
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "isNullProperty", "(Ljava/lang/String;)Z");
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a delete property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateDeletePropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, Method method, JsonProperty jsonPropertyAnnotation) {
    if (!method.getReturnType().equals(Type.BOOLEAN_TYPE)) { 
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    if (method.getArgumentTypes().length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, method.getName(), "()Z", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(jsonPropertyAnnotation.name());
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "deleteProperty", "(Ljava/lang/String;)Z");
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param javaMethod The java method.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateGetPropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, java.lang.reflect.Method javaMethod, Method method, JsonProperty jsonPropertyAnnotation) {
    if (method.getArgumentTypes().length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    Type returnType = method.getReturnType();

    Class<? extends JsonPropertyAdapter> adapterClass = jsonPropertyAnnotation.adapter();
    if (!adapterClass.equals(JsonPropertyAdapter.class)) {
      if (JsonBooleanPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateGetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Boolean.class), Type.getType(adapterClass), returnType);
      }
      else if (JsonNumberPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateGetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.getType(adapterClass), returnType);
      }
      else if (JsonStringPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateGetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(String.class), Type.getType(adapterClass), returnType);
      }
      else if (JsonObjectPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        Class<?> jsonObjectClass = JsonObject.class;
        
        ParameterizedType adapterInterface = this.getImplementedParameterizedInterface(adapterClass, JsonObjectPropertyAdapter.class);
        if (adapterInterface != null) {
          java.lang.reflect.Type[] typeArguments = adapterInterface.getActualTypeArguments();
          if (typeArguments.length != 0) {
            jsonObjectClass = (Class<?>)typeArguments[1];
          }
        }
        
        this.generateGetAdaptedObjectPropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(jsonObjectClass), Type.getType(adapterClass), returnType);
      }
      else {
        throw new IllegalArgumentException("Unsupported adapter type " + adapterClass.getName());
      }
    }
    else {
      if (returnType.getClassName().equals(Boolean.class.getName())) {
        this.generateGetJsonPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Boolean.class));
      }
      else if (returnType.getClassName().equals(Number.class.getName())) {
        this.generateGetJsonPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class));
      }
      else if (returnType.getClassName().equals(String.class.getName())) {
        this.generateGetJsonPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(String.class));
      }
      else if (returnType.getClassName().equals(JsonObjectArray.class.getName()) || returnType.getClassName().equals(JsonObjectMap.class.getName())) {
        Class<?> elementType = JsonObject.class;
        
        java.lang.reflect.Type genericReturnType = javaMethod.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
          ParameterizedType parameterizedReturnType = (ParameterizedType)genericReturnType;
          
          java.lang.reflect.Type[] typeArguments = parameterizedReturnType.getActualTypeArguments();
          if (typeArguments.length != 0) {
            elementType = (Class<?>)typeArguments[0];
          }
        }
        
        this.generateGetCompositeJsonObjectPropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), returnType, Type.getType(elementType));
      }
      else if (returnType.equals(Type.BOOLEAN_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Boolean.class), Type.BOOLEAN_TYPE);
      }
      else if (returnType.equals(Type.BYTE_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.BYTE_TYPE);
      }
      else if (returnType.equals(Type.SHORT_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.SHORT_TYPE);
      }
      else if (returnType.equals(Type.INT_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.INT_TYPE);
      }
      else if (returnType.equals(Type.LONG_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.LONG_TYPE);
      }
      else if (returnType.equals(Type.FLOAT_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.FLOAT_TYPE);
      }
      else if (returnType.equals(Type.DOUBLE_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.DOUBLE_TYPE);
      }
      else if (returnType.equals(Type.CHAR_TYPE)) {
        this.generateGetJavaCharacterPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name());
      }
      else {
        this.generateGetJsonObjectPropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), returnType);
      }
    }
  }
  
  /**
   * Generate a set property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateSetPropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, Method method, JsonProperty jsonPropertyAnnotation) {
    if (!method.getReturnType().equals(Type.VOID_TYPE)) {
      throw new IllegalArgumentException(method.getName() + "() must have a void return type");
    }
    
    Type[] argumentTypes = method.getArgumentTypes();
    if (argumentTypes.length != 1) {
      throw new IllegalArgumentException(method.getName() + "() must have exactly one parameter");
    }
    
    Type argumentType = argumentTypes[0];
    
    Class<? extends JsonPropertyAdapter> adapterClass = jsonPropertyAnnotation.adapter();
    if (!adapterClass.equals(JsonPropertyAdapter.class)) {
      if (JsonBooleanPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateSetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Boolean.class), Type.getType(adapterClass), argumentType);
      }
      else if (JsonNumberPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateSetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.getType(adapterClass), argumentType);
      }
      else if (JsonStringPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateSetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(String.class), Type.getType(adapterClass), argumentType);
      }
      else if (JsonObjectPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        Class<?> jsonObjectClass = JsonObject.class;
        
        ParameterizedType adapterInterface = this.getImplementedParameterizedInterface(adapterClass, JsonObjectPropertyAdapter.class);
        if (adapterInterface != null) {
          java.lang.reflect.Type[] typeArguments = adapterInterface.getActualTypeArguments();
          if (typeArguments.length != 0) {
            jsonObjectClass = (Class<?>)typeArguments[1];
          }
        }
        
        this.generateSetAdaptedObjectPropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(jsonObjectClass), Type.getType(adapterClass), argumentType);
      }
      else {
        throw new IllegalArgumentException("Unsupported adapter type " + adapterClass.getName());
      }
    }
    else {
      if (argumentType.getClassName().equals(Boolean.class.getName())) {
        this.generateSetJsonPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Boolean.class));
      }
      else if (argumentType.getClassName().equals(Number.class.getName())) {
        this.generateSetJsonPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class));
      }
      else if (argumentType.getClassName().equals(String.class.getName())) {
        this.generateSetJsonPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(String.class));
      }
      else if (argumentType.equals(Type.BOOLEAN_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Boolean.class), Type.BOOLEAN_TYPE, Type.getType(Boolean.class));
      }
      else if (argumentType.equals(Type.BYTE_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.BYTE_TYPE, Type.getType(Byte.class));
      }
      else if (argumentType.equals(Type.SHORT_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.SHORT_TYPE, Type.getType(Short.class));
      }
      else if (argumentType.equals(Type.INT_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.INT_TYPE, Type.getType(Integer.class));
      }
      else if (argumentType.equals(Type.LONG_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.LONG_TYPE, Type.getType(Long.class));
      }
      else if (argumentType.equals(Type.FLOAT_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.FLOAT_TYPE, Type.getType(Float.class));
      }
      else if (argumentType.equals(Type.DOUBLE_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.DOUBLE_TYPE, Type.getType(Double.class));
      }
      else if (argumentType.equals(Type.CHAR_TYPE)) {
        this.generateSetJavaCharacterPrimitivePropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name());
      }
      else {
        this.generateSetJsonObjectPropertyMethod(classWriter, jsonObjectImplType, method.getName(), jsonPropertyAnnotation.name(), argumentType);
      }
    }
  }
  
  /**
   * Generate a get JSON primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   */
  private void generateGetJsonPrimitivePropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName, Type jsonPrimitiveType) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()" + jsonPrimitiveType.getDescriptor(), null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "get" + this.getJsonPrimitiveName(jsonPrimitiveType) + "Property", "(Ljava/lang/String;)" + jsonPrimitiveType.getDescriptor());
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set JSON primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   */
  private void generateSetJsonPrimitivePropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName, Type jsonPrimitiveType) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "(" + jsonPrimitiveType.getDescriptor() + ")V", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "set" + this.getJsonPrimitiveName(jsonPrimitiveType) + "Property", "(Ljava/lang/String;" + jsonPrimitiveType.getDescriptor() + ")V");
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, jsonPrimitiveType.getDescriptor(), null, start, end, 1);
    methodVisitor.visitMaxs(3 , 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get Java primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   * @param javaPrimitiveType The type of the Java primitive.
   */
  private void generateGetJavaPrimitivePropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName, Type jsonPrimitiveType, Type javaPrimitiveType) {
    MethodVisitor mv = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()" + javaPrimitiveType.getDescriptor(), null, null);
    
    Label start = new Label();
    Label l0 = new Label();
    Label l1 = new Label();
    Label end = new Label();
    
    mv.visitCode();
    
    mv.visitLabel(start);
    mv.visitVarInsn(ALOAD, 0);
    mv.visitLdcInsn(propertyName);
    mv.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "get" + this.getJsonPrimitiveName(jsonPrimitiveType) + "Property", "(Ljava/lang/String;)" + jsonPrimitiveType.getDescriptor());
    mv.visitVarInsn(ASTORE, 1);
    mv.visitVarInsn(ALOAD, 1);
    mv.visitJumpInsn(IFNULL, l0);
    mv.visitVarInsn(ALOAD, 1);
    mv.visitMethodInsn(INVOKEVIRTUAL, jsonPrimitiveType.getInternalName(), javaPrimitiveType.getClassName() + "Value", "()" + javaPrimitiveType.getDescriptor());
    mv.visitJumpInsn(GOTO, l1);
    mv.visitLabel(l0);
    mv.visitFrame(Opcodes.F_APPEND, 1, new Object[] { jsonPrimitiveType.getInternalName() }, 0, null);
    mv.visitInsn(this.getDefaultConstOpcode(javaPrimitiveType));
    mv.visitLabel(l1);
    mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] { this.getStackElement(javaPrimitiveType) });
    mv.visitInsn(javaPrimitiveType.getOpcode(IRETURN));
    mv.visitLabel(end);
    
    mv.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    mv.visitLocalVariable("_" + propertyName, jsonPrimitiveType.getDescriptor(), null, start, end, 1);
    mv.visitMaxs(2, 2);
    
    mv.visitEnd();
  }
  
  /**
   * Generate a set Java primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   * @param javaPrimitiveType The type of the Java primitive.
   * @param javaPrimitiveWrapperType The type of the Java primitive wrapper.
   */
  private void generateSetJavaPrimitivePropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName, Type jsonPrimitiveType, Type javaPrimitiveType, Type javaPrimitiveWrapperType) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "(" + javaPrimitiveType.getDescriptor() + ")V", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(javaPrimitiveType.getOpcode(ILOAD), 1);
    methodVisitor.visitMethodInsn(INVOKESTATIC, javaPrimitiveWrapperType.getInternalName(), "valueOf", "(" + javaPrimitiveType.getDescriptor() + ")" + javaPrimitiveWrapperType.getDescriptor());
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "set" + this.getJsonPrimitiveName(jsonPrimitiveType) + "Property", "(Ljava/lang/String;" + jsonPrimitiveType.getDescriptor() + ")V");
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, javaPrimitiveType.getDescriptor(), null, start, end, 1);
    methodVisitor.visitMaxs(javaPrimitiveType.getSize() + 2, javaPrimitiveType.getSize() + 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get Java character primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   */
  private void generateGetJavaCharacterPrimitivePropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()C", null, null);
    
    Label start = new Label();
    Label l0 = new Label();
    Label l1 = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "getStringProperty", "(Ljava/lang/String;)Ljava/lang/String;");
    methodVisitor.visitVarInsn(ASTORE, 1);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitJumpInsn(IFNULL, l0);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "isEmpty", "()Z");
    methodVisitor.visitJumpInsn(IFNE, l0);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitInsn(ICONST_0);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "charAt", "(I)C");
    methodVisitor.visitJumpInsn(GOTO, l1);
    methodVisitor.visitLabel(l0);
    methodVisitor.visitFrame(Opcodes.F_APPEND, 1, new Object[] { "java/lang/String" }, 0, null);
    methodVisitor.visitInsn(ICONST_0);
    methodVisitor.visitLabel(l1);
    methodVisitor.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] { Opcodes.INTEGER });
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitLocalVariable("_" + propertyName, "Ljava/lang/String;", null, start, end, 1);
    methodVisitor.visitMaxs(2, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set Java character primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   */
  private void generateSetJavaCharacterPrimitivePropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "(C)V", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ILOAD, 1);
    methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Character", "toString", "(C)Ljava/lang/String;");
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "setStringProperty", "(Ljava/lang/String;Ljava/lang/String;)V");
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, "C", null, start, end, 1);
    methodVisitor.visitMaxs(3, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get JSON object property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param jsonObjectType The type of the JSON object.
   */
  private void generateGetJsonObjectPropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName, Type jsonObjectType) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()" + jsonObjectType.getDescriptor(), null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitLdcInsn(jsonObjectType);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "getObjectProperty", "(Ljava/lang/String;Ljava/lang/Class;)" + JSON_OBJECT_TYPE.getDescriptor());
    methodVisitor.visitTypeInsn(CHECKCAST, jsonObjectType.getInternalName());
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitMaxs(3, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set JSON object property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param jsonObjectType The type of the JSON object.
   */
  private void generateSetJsonObjectPropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName, Type jsonObjectType) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "(" + jsonObjectType.getDescriptor() + ")V", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "setObjectProperty", "(Ljava/lang/String;" + JSON_OBJECT_TYPE.getDescriptor() + ")V");
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, jsonObjectType.getDescriptor(), null, start, end, 1);
    methodVisitor.visitMaxs(3, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get composite JSON object property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param jsonObjectType The type of the JSON object.
   * @param elementType The type of the element.
   */
  private void generateGetCompositeJsonObjectPropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName, Type jsonObjectType, Type elementType) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()" + jsonObjectType.getDescriptor(), null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitLdcInsn(jsonObjectType);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "getObjectProperty", "(Ljava/lang/String;Ljava/lang/Class;)" + JSON_OBJECT_TYPE.getDescriptor());
    methodVisitor.visitTypeInsn(CHECKCAST, jsonObjectType.getInternalName());
    methodVisitor.visitLdcInsn(elementType);
    methodVisitor.visitMethodInsn(INVOKEINTERFACE, jsonObjectType.getInternalName(), "castElement", "(Ljava/lang/Class;)" + jsonObjectType.getDescriptor());
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitMaxs(3, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get adapted primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   * @param adapterType The type of the adapter.
   * @param propertyType The type of the property.
   */
  private void generateGetAdaptedPrimitivePropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName, Type jsonPrimitiveType, Type adapterType, Type propertyType) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()" + propertyType.getDescriptor(), null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "get" + this.getJsonPrimitiveName(jsonPrimitiveType) + "Property", "(Ljava/lang/String;)" + jsonPrimitiveType.getDescriptor());
    methodVisitor.visitVarInsn(ASTORE, 1);
    methodVisitor.visitTypeInsn(NEW, adapterType.getInternalName());
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterType.getInternalName(), "<init>", "()V");
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterType.getInternalName(), "fromJsonProperty", "(" + jsonPrimitiveType.getDescriptor() + ")" + propertyType.getDescriptor());
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitLocalVariable("_" + propertyName, jsonPrimitiveType.getDescriptor(), null, start, end, 1);
    methodVisitor.visitMaxs(2, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set adapted primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   * @param adapterType The type of the adapter.
   * @param propertyType The type of the property.
   */
  private void generateSetAdaptedPrimitivePropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName, Type jsonPrimitiveType, Type adapterType, Type propertyType) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "(" + propertyType.getDescriptor() + ")V", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    methodVisitor.visitLabel(start);
    methodVisitor.visitTypeInsn(NEW, adapterType.getInternalName());
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterType.getInternalName(), "<init>", "()V");
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterType.getInternalName(), "toJsonProperty", "(" + propertyType.getDescriptor() + ")" + jsonPrimitiveType.getDescriptor());
    methodVisitor.visitVarInsn(ASTORE, 2);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ALOAD, 2);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "set" + this.getJsonPrimitiveName(jsonPrimitiveType) + "Property", "(Ljava/lang/String;" + jsonPrimitiveType.getDescriptor() + ")V");
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, propertyType.getDescriptor(), null, start, end, 1);
    methodVisitor.visitLocalVariable("_" + propertyName, jsonPrimitiveType.getDescriptor(), null, start, end, 2);
    methodVisitor.visitMaxs(3, 3);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get adapted object property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param jsonObjectType The type of the JSON object.
   * @param adapterType The type of the adapter.
   * @param propertyType The type of the property.
   */
  private void generateGetAdaptedObjectPropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName, Type jsonObjectType, Type adapterType, Type propertyType) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()" + propertyType.getDescriptor(), null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitLdcInsn(jsonObjectType);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "getObjectProperty", "(Ljava/lang/String;Ljava/lang/Class;)" + JSON_OBJECT_TYPE.getDescriptor());
    methodVisitor.visitTypeInsn(CHECKCAST, jsonObjectType.getInternalName());
    methodVisitor.visitVarInsn(ASTORE, 1);
    methodVisitor.visitTypeInsn(NEW, adapterType.getInternalName());
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterType.getInternalName(), "<init>", "()V");
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterType.getInternalName(), "fromJsonProperty", "(" + jsonObjectType.getDescriptor() + ")" + propertyType.getDescriptor());
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitLocalVariable("_" + propertyName, jsonObjectType.getDescriptor(), null, start, end, 1);
    methodVisitor.visitMaxs(3, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set adapted object property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param jsonObjectType The type of the JSON object.
   * @param adapterType The type of the adapter.
   * @param propertyType The type of the property.
   */
  private void generateSetAdaptedObjectPropertyMethod(ClassWriter classWriter, Type jsonObjectImplType, String methodName, String propertyName, Type jsonObjectType, Type adapterType, Type propertyType) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "(" + propertyType.getDescriptor() + ")V", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitLineNumber(138, start);
    methodVisitor.visitTypeInsn(NEW, adapterType.getInternalName());
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterType.getInternalName(), "<init>", "()V");
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterType.getInternalName(), "toJsonProperty", "(" + propertyType.getDescriptor() + ")" + jsonObjectType.getDescriptor());
    methodVisitor.visitVarInsn(ASTORE, 2);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ALOAD, 2);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType.getInternalName(), "setObjectProperty", "(Ljava/lang/String;" + JSON_OBJECT_TYPE.getDescriptor() + ")V");
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType.getDescriptor(), null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, propertyType.getDescriptor(), null, start, end, 1);
    methodVisitor.visitLocalVariable("_" + propertyName, jsonObjectType.getDescriptor(), null, start, end, 2);
    methodVisitor.visitMaxs(3, 3);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Create the constructor signature for the given arguments.
   *
   * @param constructorArguments The constructor arguments.
   * @return The constructor signature.
   */
  private String createConstructorSignature(ConstructorArgument[] constructorArguments) {
    StringBuffer stringBuffer = new StringBuffer();
    
    stringBuffer.append("(");
    for (ConstructorArgument constructorArgument : constructorArguments) {
      stringBuffer.append(constructorArgument.getTypeIName());
    }
    stringBuffer.append(")V");
    
    return stringBuffer.toString();
  }

  /**
   * Retrieve the implemented parameterized interface with the given raw
   * interface from the given class.
   *
   * @param klass The class.
   * @param rawInterface The raw interface.
   * @return The parameterized interface.
   */
  private ParameterizedType getImplementedParameterizedInterface(Class<?> klass, Class<?> rawInterface) {
    java.lang.reflect.Type[] genericInterfaces = klass.getGenericInterfaces();
    if (genericInterfaces != null) {
      for (java.lang.reflect.Type genericInterface : genericInterfaces) {
        if (genericInterface instanceof ParameterizedType) {
          ParameterizedType parameterizedType = (ParameterizedType)genericInterface;
          
          Class<?> rawType = (Class<?>)parameterizedType.getRawType();
          if (rawType.equals(rawInterface)) {
            return parameterizedType;
          }
          
          parameterizedType = this.getImplementedParameterizedInterface(rawType, rawInterface);
          if (parameterizedType != null) {
            return parameterizedType;
          }
        }
        else if (genericInterface instanceof Class<?>) {
          ParameterizedType parameterizedType = this.getImplementedParameterizedInterface((Class<?>)genericInterface, rawInterface);
          if (parameterizedType != null) {
            return parameterizedType;
          }
        }
      }
    }
    
    Class<?> superClass = klass.getSuperclass();
    if (superClass != null) {
      ParameterizedType parameterizedType = this.getImplementedParameterizedInterface(superClass, rawInterface);
      if (parameterizedType != null) {
        return parameterizedType;
      }
    }
    
    return null;
  }

  /**
   * Retrieve the name of the JSON primitive with the given type.
   *
   * @param jsonPrimitiveType The type of the JSON primitive.
   * @return The name of the JSON primitive.
   */
  private String getJsonPrimitiveName(Type jsonPrimitiveType) {
    String jsonPrimitiveClassName = jsonPrimitiveType.getClassName();
    
    return jsonPrimitiveClassName.substring(jsonPrimitiveClassName.lastIndexOf('.') + 1);
  }
  
  /**
   * Retrieve the stack element for the Java primitive with the given type.
   *
   * @param javaPrimitiveType The type of the Java primitive.
   * @return The stack element.
   */
  private Integer getStackElement(Type javaPrimitiveType) {
    switch (javaPrimitiveType.getSort()) {
    case Type.BOOLEAN:
    case Type.BYTE:
    case Type.SHORT:
    case Type.INT:
    case Type.CHAR:
      return INTEGER;
    
    case Type.LONG:
      return LONG;
    
    case Type.FLOAT:
      return FLOAT;
    
    case Type.DOUBLE:
      return DOUBLE;
    
    default:
      throw new IllegalArgumentException(javaPrimitiveType.toString());
    }
  }
  
  /**
   * Retrieve the default constant opcode for the Java primitive with the given
   * type.
   *
   * @param javaPrimitiveType The type of the Java primitive.
   * @return The default constant opcode.
   */
  private int getDefaultConstOpcode(Type javaPrimitiveType) {
    switch (javaPrimitiveType.getSort()) {
    case Type.BOOLEAN:
    case Type.BYTE:
    case Type.SHORT:
    case Type.INT:
    case Type.CHAR:
      return ICONST_0;
    
    case Type.LONG:
      return LCONST_0;
    
    case Type.FLOAT:
      return FCONST_0;
    
    case Type.DOUBLE:
      return DCONST_0;
    
    default:
      throw new IllegalArgumentException(javaPrimitiveType.toString());
    }
  }
}
