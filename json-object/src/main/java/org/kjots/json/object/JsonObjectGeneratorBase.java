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

import static org.objectweb.asm.Opcodes.ACC_BRIDGE;
import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ACC_SYNTHETIC;
import static org.objectweb.asm.Opcodes.ACC_VARARGS;
import static org.objectweb.asm.Opcodes.ACONST_NULL;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.ATHROW;
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
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;

import org.kjots.json.object.shared.JsonBooleanPropertyAdapter;
import org.kjots.json.object.shared.JsonException;
import org.kjots.json.object.shared.JsonFunction;
import org.kjots.json.object.shared.JsonNumberPropertyAdapter;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectArray;
import org.kjots.json.object.shared.JsonObjectMap;
import org.kjots.json.object.shared.JsonObjectPropertyAdapter;
import org.kjots.json.object.shared.JsonProperty;
import org.kjots.json.object.shared.JsonPropertyAdapter;
import org.kjots.json.object.shared.JsonStringPropertyAdapter;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

/**
 * JSON Object Generator Base.
 * <p>
 * Created: 5th March 2010
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public abstract class JsonObjectGeneratorBase<T extends JsonObject> {
  /** The class of the JSON object implementation. */
  private final Class<T> jsonObjectImplClass;
  
  /**
   * Retrieve the type of the JSON object.
   *
   * @return The type of the JSON object.
   */
  private static Type getJsonObjectType() {
    return Type.getType(JsonObject.class);
  }
  
  /**
   * Retrieve the constructor with the given argument types.
   *
   * @param argumentTypes The argument types.
   * @return The constructor.
   */
  private static Method getConstructor(Type... argumentTypes) {
    return new Method("<init>", Type.VOID_TYPE, argumentTypes);
  }
  
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
    
    this.generateClass(new ClassVisitor(classWriter), jsonObjectClass,  Type.getType(jsonObjectClass), Type.getType(superJsonObjectImplClass));
    
    return classWriter.toByteArray(); 
  }
  
  /**
   * Generate the class.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectClass The class of the JSON object.
   * @param jsonObjectType The type of the JSON object.
   * @param superJsonObjectImplType The type of the super JSON object implementation.
   */
  private void generateClass(ClassVisitor classVisitor, Class<? extends JsonObject> jsonObjectClass, Type jsonObjectType, Type superJsonObjectImplType) {
    Type jsonObjectImplType = Type.getObjectType(jsonObjectType.getInternalName() + "$" + this.jsonObjectImplClass.getSimpleName());
    
    classVisitor.visit(V1_6, ACC_PUBLIC + ACC_SUPER, jsonObjectImplType, null, superJsonObjectImplType, jsonObjectType);
    
    this.generateConstructor(classVisitor, jsonObjectImplType, superJsonObjectImplType);
    
    for (java.lang.reflect.Method method : jsonObjectClass.getDeclaredMethods()) {
      if (method.getAnnotation(JsonFunction.class) != null) {
        this.generateFunctionMethod(classVisitor, jsonObjectImplType, jsonObjectClass, Method.getMethod(method), method.getAnnotation(JsonFunction.class), method.isVarArgs());
      }
      else if (method.getAnnotation(JsonException.class) != null) {
        this.generateExceptionMethod(classVisitor, jsonObjectImplType, jsonObjectClass, Method.getMethod(method), method.getAnnotation(JsonException.class), method.isVarArgs());
      }
      else if (method.getAnnotation(JsonProperty.class) != null) {
        this.generatePropertyMethod(classVisitor, jsonObjectImplType, method, Method.getMethod(method), method.getAnnotation(JsonProperty.class));
      }
      else {
        throw new IllegalArgumentException(method.getName() + "() is not annotated with suitable annotation");
      }
    }
    
    Set<Method> implementedMethods = new HashSet<Method>(classVisitor.getImplementedMethods());
    
    for (GenericMethod declaredMethod : this.getExtraInterfaceMethods(jsonObjectClass)) {
      Method implementedMethod = null;
      
      if (declaredMethod.getGenericReturnType() || !declaredMethod.getGenericTypeIndices().isEmpty()) {
        implementedMethod = declaredMethod.getCompatibleMethod(implementedMethods);
      }
      else if (!declaredMethod.getGenericReturnType() && !implementedMethods.contains(declaredMethod)) {
        int returnTypeSort = declaredMethod.getReturnType().getSort();
        if (returnTypeSort == Type.ARRAY) {
          returnTypeSort = declaredMethod.getReturnType().getElementType().getSort();
        }
        
        if (returnTypeSort == Type.OBJECT) {
          GenericMethod newDeclaredMethod = new GenericMethod(declaredMethod.getName(), declaredMethod.getDescriptor(), true, declaredMethod.getGenericTypeIndices());
          
          implementedMethod = newDeclaredMethod.getCompatibleMethod(implementedMethods);
        }
      }
      
      if (implementedMethod != null && !implementedMethod.equals(declaredMethod)) {
        this.generateBridgeMethod(classVisitor, jsonObjectImplType, declaredMethod, implementedMethod);
      }
    }
    
    classVisitor.visitEnd();
  }
  
  /**
   * Generate the constructor.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param superJsonObjectImplType The type of the super JSON object implementation.
   */
  private void generateConstructor(ClassVisitor classVisitor, Type jsonObjectImplType, Type superJsonObjectImplType) {
    Method constructor = Method.getMethod(this.getJsonObjectImplConstructor());
    Type[] argumentTypes = constructor.getArgumentTypes();
    
    int maxLocals = 1;
    
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC, constructor, null, null);
    
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
    methodVisitor.visitMethodInsn(INVOKESPECIAL, superJsonObjectImplType, constructor);
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    for (int i = 0; i < argumentTypes.length; i++) {
      Type argumentType = argumentTypes[i];
      
      methodVisitor.visitLocalVariable("arg" + i, argumentType, null, start, end, maxLocals);
      
      maxLocals += argumentType.getSize();
    }
    methodVisitor.visitMaxs(maxLocals, maxLocals);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a function method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param jsonObjectClass The class of the JSON object.
   * @param method The method.
   * @param jsonFunctionAnnotation The JSON function annotation.
   * @param varargs The variable arguments flag.
   */
  private void generateFunctionMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Class<? extends JsonObject> jsonObjectClass, Method method, JsonFunction jsonFunctionAnnotation, boolean varargs) {
    Type returnType = method.getReturnType();
    Type[] argumentTypes = method.getArgumentTypes();
    
    int maxLocals = 1;
    
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL + (varargs ? ACC_VARARGS : 0), method, null, null);
    
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
    methodVisitor.visitMethodInsn(INVOKESTATIC, Type.getType(jsonFunctionAnnotation.klass()), this.getJsonFunctionMethod(jsonObjectClass, method, jsonFunctionAnnotation));
    methodVisitor.visitInsn(returnType.getOpcode(IRETURN));
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    for (int i = 0; i < argumentTypes.length; i++) {
      Type argumentType = argumentTypes[i];
      
      methodVisitor.visitLocalVariable("arg" + i, argumentType, null, start, end, maxLocals);
      
      maxLocals += argumentType.getSize();
    }
    methodVisitor.visitMaxs(Math.max(maxLocals, returnType.getSize()), maxLocals);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate an exception method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param jsonObjectClass The class of the JSON object.
   * @param method The method.
   * @param jsonExceptionAnnotation The JSON exception annotation.
   * @param varargs The variable arguments flag.
   */
  private void generateExceptionMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Class<? extends JsonObject> jsonObjectClass, Method method, JsonException jsonExceptionAnnotation, boolean varargs) {
    Type[] argumentTypes = method.getArgumentTypes();
    Type exceptionType = Type.getType(jsonExceptionAnnotation.klass());
    String message = jsonExceptionAnnotation.message();
    
    int maxLocals = 1;
    
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL + (varargs ? ACC_VARARGS : 0), method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitTypeInsn(NEW, exceptionType);
    methodVisitor.visitInsn(DUP);
    if (message.isEmpty()) {
      methodVisitor.visitMethodInsn(INVOKESPECIAL, exceptionType, getConstructor());
    }
    else {
      methodVisitor.visitLdcInsn(message);
      methodVisitor.visitMethodInsn(INVOKESPECIAL, exceptionType, getConstructor(Type.getType(String.class)));
    }
    methodVisitor.visitInsn(ATHROW);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    for (int i = 0; i < argumentTypes.length; i++) {
      Type argumentType = argumentTypes[i];
      
      methodVisitor.visitLocalVariable("arg" + i, argumentType, null, start, end, maxLocals);
      
      maxLocals += argumentType.getSize();
    }
    methodVisitor.visitMaxs(message.isEmpty() ? 2 : 3, maxLocals);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a property method. 
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param javaMethod The java method.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generatePropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, java.lang.reflect.Method javaMethod, Method method, JsonProperty jsonPropertyAnnotation) {
    switch (jsonPropertyAnnotation.operation()) {
    case HAS:
      this.generateHasPropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation);
      
      return;
      
    case IS_NULL:
      this.generateIsNullPropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation);
      
      return;
      
    case DELETE:
      this.generateDeletePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation);
      
      return;
      
    case GET:
      this.generateGetPropertyMethod(classVisitor, jsonObjectImplType, javaMethod, method, jsonPropertyAnnotation);
      
      return;
      
    case SET:
      this.generateSetPropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation);
      
      return;
      
    default:
      throw new IllegalArgumentException(javaMethod.getName() + "() is annotated with unsupported operation type " + jsonPropertyAnnotation.operation());
    }
  }

  /**
   * Generate a has property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateHasPropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, JsonProperty jsonPropertyAnnotation) {
    if (!method.getReturnType().equals(Type.BOOLEAN_TYPE)) { 
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    if (method.getArgumentTypes().length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(jsonPropertyAnnotation.name());
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getHasJsonPropertyMethod());
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate an is null property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateIsNullPropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, JsonProperty jsonPropertyAnnotation) {
    if (!method.getReturnType().equals(Type.BOOLEAN_TYPE)) { 
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    if (method.getArgumentTypes().length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(jsonPropertyAnnotation.name());
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getIsNullJsonPropertyMethod());
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a delete property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateDeletePropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, JsonProperty jsonPropertyAnnotation) {
    if (!method.getReturnType().equals(Type.BOOLEAN_TYPE)) { 
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    if (method.getArgumentTypes().length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(jsonPropertyAnnotation.name());
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getDeleteJsonPropertyMethod());
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param javaMethod The java method.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateGetPropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, java.lang.reflect.Method javaMethod, Method method, JsonProperty jsonPropertyAnnotation) {
    if (method.getArgumentTypes().length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    Type returnType = method.getReturnType();

    Class<? extends JsonPropertyAdapter<?, ?>> adapterClass = jsonPropertyAnnotation.adapter();
    if (adapterClass.equals(JsonProperty.NullAdapter.class) && this.isObjectType(returnType)) {
      JsonPropertyAdapter.AutoAdaptedType autoAdaptedTypeAnnotation = this.getClass(returnType).getAnnotation(JsonPropertyAdapter.AutoAdaptedType.class);
      if (autoAdaptedTypeAnnotation != null) {
        adapterClass = autoAdaptedTypeAnnotation.value();
      }
    }
    
    if (!adapterClass.equals(JsonProperty.NullAdapter.class)) {
      if (JsonBooleanPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateGetAdaptedPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Boolean.class), Type.getType(adapterClass), returnType);
      }
      else if (JsonNumberPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateGetAdaptedPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.getType(adapterClass), returnType);
      }
      else if (JsonStringPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateGetAdaptedPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(String.class), Type.getType(adapterClass), returnType);
      }
      else if (JsonObjectPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateGetAdaptedObjectPropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), this.getJsonObjectType(adapterClass), Type.getType(adapterClass), returnType);
      }
      else {
        throw new IllegalArgumentException("Unsupported adapter type " + adapterClass.getName());
      }
    }
    else {
      if (returnType.getClassName().equals(Boolean.class.getName())) {
        this.generateGetJsonPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Boolean.class));
      }
      else if (returnType.getClassName().equals(Number.class.getName())) {
        this.generateGetJsonPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class));
      }
      else if (returnType.getClassName().equals(String.class.getName())) {
        this.generateGetJsonPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(String.class));
      }
      else if (returnType.getClassName().equals(JsonObjectArray.class.getName()) || returnType.getClassName().equals(JsonObjectMap.class.getName())) {
        this.generateGetCompositeJsonObjectPropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), returnType, this.getCompositeJsonObjectElementType(javaMethod, method));
      }
      else if (returnType.equals(Type.BOOLEAN_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Boolean.class), Type.BOOLEAN_TYPE);
      }
      else if (returnType.equals(Type.BYTE_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.BYTE_TYPE);
      }
      else if (returnType.equals(Type.SHORT_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.SHORT_TYPE);
      }
      else if (returnType.equals(Type.INT_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.INT_TYPE);
      }
      else if (returnType.equals(Type.LONG_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.LONG_TYPE);
      }
      else if (returnType.equals(Type.FLOAT_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.FLOAT_TYPE);
      }
      else if (returnType.equals(Type.DOUBLE_TYPE)) {
        this.generateGetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.DOUBLE_TYPE);
      }
      else if (returnType.equals(Type.CHAR_TYPE)) {
        this.generateGetJavaCharacterPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name());
      }
      else {
        this.generateGetJsonObjectPropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), returnType);
      }
    }
  }
  
  /**
   * Generate a set property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateSetPropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, JsonProperty jsonPropertyAnnotation) {
    if (!method.getReturnType().equals(Type.VOID_TYPE)) {
      throw new IllegalArgumentException(method.getName() + "() must have a void return type");
    }
    
    Type[] argumentTypes = method.getArgumentTypes();
    if (argumentTypes.length != 1) {
      throw new IllegalArgumentException(method.getName() + "() must have exactly one parameter");
    }
    
    Type argumentType = argumentTypes[0];
    
    Class<? extends JsonPropertyAdapter<?, ?>> adapterClass = jsonPropertyAnnotation.adapter();
    if (adapterClass.equals(JsonProperty.NullAdapter.class) && this.isObjectType(argumentType)) {
      JsonPropertyAdapter.AutoAdaptedType autoAdaptedTypeAnnotation = this.getClass(argumentType).getAnnotation(JsonPropertyAdapter.AutoAdaptedType.class);
      if (autoAdaptedTypeAnnotation != null) {
        adapterClass = autoAdaptedTypeAnnotation.value();
      }
    }
    
    if (!adapterClass.equals(JsonProperty.NullAdapter.class)) {
      if (JsonBooleanPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateSetAdaptedPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Boolean.class), Type.getType(adapterClass), argumentType);
      }
      else if (JsonNumberPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateSetAdaptedPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.getType(adapterClass), argumentType);
      }
      else if (JsonStringPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateSetAdaptedPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(String.class), Type.getType(adapterClass), argumentType);
      }
      else if (JsonObjectPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateSetAdaptedObjectPropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), this.getJsonObjectType(adapterClass), Type.getType(adapterClass), argumentType);
      }
      else {
        throw new IllegalArgumentException("Unsupported adapter type " + adapterClass.getName());
      }
    }
    else {
      if (argumentType.getClassName().equals(Boolean.class.getName())) {
        this.generateSetJsonPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Boolean.class));
      }
      else if (argumentType.getClassName().equals(Number.class.getName())) {
        this.generateSetJsonPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class));
      }
      else if (argumentType.getClassName().equals(String.class.getName())) {
        this.generateSetJsonPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(String.class));
      }
      else if (argumentType.equals(Type.BOOLEAN_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Boolean.class), Type.BOOLEAN_TYPE);
      }
      else if (argumentType.equals(Type.BYTE_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.BYTE_TYPE);
      }
      else if (argumentType.equals(Type.SHORT_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.SHORT_TYPE);
      }
      else if (argumentType.equals(Type.INT_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.INT_TYPE);
      }
      else if (argumentType.equals(Type.LONG_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.LONG_TYPE);
      }
      else if (argumentType.equals(Type.FLOAT_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.FLOAT_TYPE);
      }
      else if (argumentType.equals(Type.DOUBLE_TYPE)) {
        this.generateSetJavaPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), Type.getType(Number.class), Type.DOUBLE_TYPE);
      }
      else if (argumentType.equals(Type.CHAR_TYPE)) {
        this.generateSetJavaCharacterPrimitivePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name());
      }
      else {
        this.generateSetJsonObjectPropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation.name(), argumentType);
      }
    }
  }
  
  /**
   * Generate a get JSON primitive property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   */
  private void generateGetJsonPrimitivePropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName, Type jsonPrimitiveType) {
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getGetJsonPrimitivePropertyMethod(jsonPrimitiveType));
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set JSON primitive property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   */
  private void generateSetJsonPrimitivePropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName, Type jsonPrimitiveType) {
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getSetJsonPrimitivePropertyMethod(jsonPrimitiveType));
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, jsonPrimitiveType, null, start, end, 1);
    methodVisitor.visitMaxs(3 , 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get Java primitive property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   * @param javaPrimitiveType The type of the Java primitive.
   */
  private void generateGetJavaPrimitivePropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName, Type jsonPrimitiveType, Type javaPrimitiveType) {
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label l0 = new Label();
    Label l1 = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getGetJsonPrimitivePropertyMethod(jsonPrimitiveType));
    methodVisitor.visitVarInsn(ASTORE, 1);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitJumpInsn(IFNULL, l0);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonPrimitiveType, this.getToJavaPrimitiveMethod(javaPrimitiveType));
    methodVisitor.visitJumpInsn(GOTO, l1);
    methodVisitor.visitLabel(l0);
    methodVisitor.visitFrame(Opcodes.F_APPEND, 1, new Object[] { jsonPrimitiveType }, 0, null);
    methodVisitor.visitInsn(this.getDefaultConstOpcode(javaPrimitiveType));
    methodVisitor.visitLabel(l1);
    methodVisitor.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] { this.getStackElement(javaPrimitiveType) });
    methodVisitor.visitInsn(javaPrimitiveType.getOpcode(IRETURN));
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitLocalVariable("_" + propertyName, jsonPrimitiveType, null, start, end, 1);
    methodVisitor.visitMaxs(2, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set Java primitive property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   * @param javaPrimitiveType The type of the Java primitive.
   */
  private void generateSetJavaPrimitivePropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName, Type jsonPrimitiveType, Type javaPrimitiveType) {
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(javaPrimitiveType.getOpcode(ILOAD), 1);
    methodVisitor.visitMethodInsn(INVOKESTATIC, this.getWrapperType(javaPrimitiveType), this.getFromJavaPrimitiveMethod(javaPrimitiveType));
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getSetJsonPrimitivePropertyMethod(jsonPrimitiveType));
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, javaPrimitiveType, null, start, end, 1);
    methodVisitor.visitMaxs(javaPrimitiveType.getSize() + 2, javaPrimitiveType.getSize() + 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get Java character primitive property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   */
  private void generateGetJavaCharacterPrimitivePropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName) {
    Type jsonStringPrimitiveType = Type.getType(String.class);
    
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label l0 = new Label();
    Label l1 = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getGetJsonPrimitivePropertyMethod(jsonStringPrimitiveType));
    methodVisitor.visitVarInsn(ASTORE, 1);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitJumpInsn(IFNULL, l0);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonStringPrimitiveType, new Method("isEmpty", Type.BOOLEAN_TYPE, new Type[] {}));
    methodVisitor.visitJumpInsn(IFNE, l0);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitInsn(ICONST_0);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonStringPrimitiveType, new Method("charAt", Type.CHAR_TYPE, new Type[] { Type.INT_TYPE }));
    methodVisitor.visitJumpInsn(GOTO, l1);
    methodVisitor.visitLabel(l0);
    methodVisitor.visitFrame(Opcodes.F_APPEND, 1, new Object[] { jsonStringPrimitiveType }, 0, null);
    methodVisitor.visitInsn(ICONST_0);
    methodVisitor.visitLabel(l1);
    methodVisitor.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] { Opcodes.INTEGER });
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitLocalVariable("_" + propertyName, jsonStringPrimitiveType, null, start, end, 1);
    methodVisitor.visitMaxs(2, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set Java character primitive property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   */
  private void generateSetJavaCharacterPrimitivePropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName) {
    Type jsonStringPrimitiveType = Type.getType(String.class);
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ILOAD, 1);
    methodVisitor.visitMethodInsn(INVOKESTATIC, Type.getType(Character.class), new Method("toString", jsonStringPrimitiveType, new Type[] { Type.CHAR_TYPE }));
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getSetJsonPrimitivePropertyMethod(jsonStringPrimitiveType));
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, Type.CHAR_TYPE, null, start, end, 1);
    methodVisitor.visitMaxs(3, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get JSON object property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   * @param jsonObjectType The type of the JSON object.
   */
  private void generateGetJsonObjectPropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName, Type jsonObjectType) {
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitLdcInsn(jsonObjectType);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getGetJsonObjectPropertyMethod());
    methodVisitor.visitTypeInsn(CHECKCAST, jsonObjectType);
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitMaxs(3, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set JSON object property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   * @param jsonObjectType The type of the JSON object.
   */
  private void generateSetJsonObjectPropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName, Type jsonObjectType) {
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getSetJsonObjectPropertyMethod());
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, jsonObjectType, null, start, end, 1);
    methodVisitor.visitMaxs(3, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get composite JSON object property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   * @param jsonObjectType The type of the JSON object.
   * @param elementType The type of the element.
   */
  private void generateGetCompositeJsonObjectPropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName, Type jsonObjectType, Type elementType) {
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label l0 = new Label();
    Label l1 = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitLdcInsn(jsonObjectType);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getGetJsonObjectPropertyMethod());
    methodVisitor.visitTypeInsn(CHECKCAST, jsonObjectType);
    methodVisitor.visitVarInsn(ASTORE, 1);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitJumpInsn(IFNULL, l0);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitLdcInsn(elementType);
    methodVisitor.visitMethodInsn(INVOKEINTERFACE, jsonObjectType, this.getCastCompositeJsonObjectElementMethod(jsonObjectType));
    methodVisitor.visitJumpInsn(GOTO, l1);
    methodVisitor.visitLabel(l0);
    methodVisitor.visitFrame(Opcodes.F_APPEND, 1, new Object[] { jsonObjectType }, 0, null);
    methodVisitor.visitInsn(ACONST_NULL);
    methodVisitor.visitLabel(l1);
    methodVisitor.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] { jsonObjectType });
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitLocalVariable("_" + propertyName, jsonObjectType, null, start, end, 1);
    methodVisitor.visitMaxs(3, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get adapted primitive property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   * @param adapterType The type of the adapter.
   * @param propertyType The type of the property.
   */
  private void generateGetAdaptedPrimitivePropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName, Type jsonPrimitiveType, Type adapterType, Type propertyType) {
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getGetJsonPrimitivePropertyMethod(jsonPrimitiveType));
    methodVisitor.visitVarInsn(ASTORE, 1);
    methodVisitor.visitTypeInsn(NEW, adapterType);
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterType, getConstructor());
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterType, this.getFromJsonPropertyMethod(jsonPrimitiveType, propertyType));
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitLocalVariable("_" + propertyName, jsonPrimitiveType, null, start, end, 1);
    methodVisitor.visitMaxs(2, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set adapted primitive property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   * @param jsonPrimitiveType The type of the JSON primitive.
   * @param adapterType The type of the adapter.
   * @param propertyType The type of the property.
   */
  private void generateSetAdaptedPrimitivePropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName, Type jsonPrimitiveType, Type adapterType, Type propertyType) {
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    methodVisitor.visitLabel(start);
    methodVisitor.visitTypeInsn(NEW, adapterType);
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterType, getConstructor());
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterType, this.getToJsonPropertyMethod(jsonPrimitiveType, propertyType));
    methodVisitor.visitVarInsn(ASTORE, 2);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ALOAD, 2);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getSetJsonPrimitivePropertyMethod(jsonPrimitiveType));
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, propertyType, null, start, end, 1);
    methodVisitor.visitLocalVariable("_" + propertyName, jsonPrimitiveType, null, start, end, 2);
    methodVisitor.visitMaxs(3, 3);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get adapted object property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   * @param jsonObjectType The type of the JSON object.
   * @param adapterType The type of the adapter.
   * @param propertyType The type of the property.
   */
  private void generateGetAdaptedObjectPropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName, Type jsonObjectType, Type adapterType, Type propertyType) {
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitLdcInsn(jsonObjectType);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getGetJsonObjectPropertyMethod());
    methodVisitor.visitTypeInsn(CHECKCAST, jsonObjectType);
    methodVisitor.visitVarInsn(ASTORE, 1);
    methodVisitor.visitTypeInsn(NEW, adapterType);
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterType, getConstructor());
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterType, this.getFromJsonPropertyMethod(jsonObjectType, propertyType));
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitLocalVariable("_" + propertyName, jsonObjectType, null, start, end, 1);
    methodVisitor.visitMaxs(3, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set adapted object property method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param propertyName The name of the property.
   * @param jsonObjectType The type of the JSON object.
   * @param adapterType The type of the adapter.
   * @param propertyType The type of the property.
   */
  private void generateSetAdaptedObjectPropertyMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, String propertyName, Type jsonObjectType, Type adapterType, Type propertyType) {
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitTypeInsn(NEW, adapterType);
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterType, getConstructor());
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterType, this.getToJsonPropertyMethod(jsonObjectType, propertyType));
    methodVisitor.visitVarInsn(ASTORE, 2);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ALOAD, 2);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, this.getSetJsonObjectPropertyMethod());
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, propertyType, null, start, end, 1);
    methodVisitor.visitLocalVariable("_" + propertyName, jsonObjectType, null, start, end, 2);
    methodVisitor.visitMaxs(3, 3);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a bridge method.
   *
   * @param classVisitor The class visitor.
   * @param jsonObjectImplType The type of the JSON object implementation.
   * @param method The method.
   * @param targetMethod The target method.
   */
  private void generateBridgeMethod(ClassVisitor classVisitor, Type jsonObjectImplType, Method method, Method targetMethod) {
    Type returnType = method.getReturnType();
    Type[] argumentTypes = method.getArgumentTypes();
    Type[] targetArgumentTypes = targetMethod.getArgumentTypes();
    
    int maxLocals = 1;
    
    MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, method, null, null);
    
    methodVisitor.visitCode();
    
    methodVisitor.visitVarInsn(ALOAD, 0);
    for (int i = 0; i < argumentTypes.length; i++) {
      Type argumentType = argumentTypes[i];
      Type targetArgumentType = targetArgumentTypes[i];
      
      methodVisitor.visitVarInsn(argumentType.getOpcode(ILOAD), maxLocals);
      if (!argumentType.equals(targetArgumentType)) {
        methodVisitor.visitTypeInsn(CHECKCAST, targetArgumentType);
      }
      
      maxLocals += argumentType.getSize();
    }
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, targetMethod);
    methodVisitor.visitInsn(returnType.getOpcode(IRETURN));
    methodVisitor.visitMaxs(Math.max(maxLocals, returnType.getSize()), maxLocals);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Determine if the given type is an object type.
   *
   * @param type The type.
   * @return <code>true</code> if the type is an object type.
   */
  private boolean isObjectType(Type type) {
    return type.getDescriptor().charAt(0) == 'L';
  }
  
  /**
   * Retrieve the class for the given type.
   *
   * @param type The type.
   * @return The class.
   */
  private Class<?> getClass(Type type) {
    try {
      return Class.forName(type.getClassName());
    }
    catch (ClassNotFoundException cnfe) {
      throw new IllegalArgumentException(type.toString(), cnfe);
    }
  }
  
  /**
   * Retrieve the methods of the extra interfaces of the JSON object with the
   * given class.
   *
   * @param jsonObjectClass The class of the JSON object.
   * @return The methods.
   */
  private Set<GenericMethod> getExtraInterfaceMethods(Class<? extends JsonObject> jsonObjectClass) {
    Set<GenericMethod> methods = new HashSet<GenericMethod>();
    
    Class<?>[] implementedInterfaces = jsonObjectClass.getInterfaces();
    for (int i = 1; i < implementedInterfaces.length; i++) {
      this.getInterfaceMethods(implementedInterfaces[i], methods, new HashSet<Class<?>>());
    }
    
    return methods;
  }
  
  /**
   * Retrieve the methods of the given interface.
   *
   * @param interfase The interface.
   * @param methods The methods.
   * @param encounteredInterfaces The encountered interfaces.
   */
  private void getInterfaceMethods(Class<?> interfase, Set<GenericMethod> methods, Set<Class<?>> encounteredInterfaces) {
    encounteredInterfaces.add(interfase);
    
    for (java.lang.reflect.Method javaMethod : interfase.getDeclaredMethods()) {
      methods.add(GenericMethod.getGenericMethod(javaMethod));
    }
    
    for (Class<?> extendedInterface : interfase.getInterfaces()) {
      getInterfaceMethods(extendedInterface, methods, encounteredInterfaces);
    }
  }

  /**
   * Retrieve the type of the JSON object for the adapter with the given class.
   *
   * @param adapterClass The class of the adapter.
   * @return The type of the JSON object.
   */
  private Type getJsonObjectType(Class<? extends JsonPropertyAdapter<?, ?>> adapterClass) {
    ParameterizedType adapterInterface = this.getImplementedParameterizedInterface(adapterClass, JsonObjectPropertyAdapter.class);
    if (adapterInterface != null) {
      java.lang.reflect.Type[] typeArguments = adapterInterface.getActualTypeArguments();
      if (typeArguments.length != 0) {
        java.lang.reflect.Type type = typeArguments[1];
        
        while (type instanceof ParameterizedType) {
          ParameterizedType parameterizedType = (ParameterizedType)type;
          
          type = parameterizedType.getRawType();
        }
        
        return Type.getType((Class<?>)type);
      }
    }
    
    return getJsonObjectType();
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
   * Retrieve the type of the element of the composite JSON object returned by
   * the given method.
   *
   * @param javaMethod The java method.
   * @param method The method.
   * @return The type of the element.
   */
  private Type getCompositeJsonObjectElementType(java.lang.reflect.Method javaMethod, Method method) {
    java.lang.reflect.Type genericReturnType = javaMethod.getGenericReturnType();
    if (genericReturnType instanceof ParameterizedType) {
      ParameterizedType parameterizedReturnType = (ParameterizedType)genericReturnType;
      
      java.lang.reflect.Type[] typeArguments = parameterizedReturnType.getActualTypeArguments();
      if (typeArguments.length != 0) {
        return Type.getType((Class<?>)typeArguments[0]);
      }
    }
    
    return getJsonObjectType();
  }
  
  /**
   * Retrieve the JSON function method with the given name for the given JSON
   * object method.
   *
   * @param jsonObjectClass The class of the JSON object.
   * @param jsonObjectMethod The JSON object method.
   * @param jsonFunctionAnnotation The JSON function annotation.
   * @return The JSON function method.
   */
  private Method getJsonFunctionMethod(Class<?> jsonObjectClass, Method jsonObjectMethod, JsonFunction jsonFunctionAnnotation) {
    Set<Method> methods = new HashSet<Method>();
    for (java.lang.reflect.Method javaMethod : jsonFunctionAnnotation.klass().getDeclaredMethods()) {
      if (Modifier.isStatic(javaMethod.getModifiers()) && javaMethod.getName().equals(jsonFunctionAnnotation.method())) {
        methods.add(Method.getMethod(javaMethod));
      }
    }
    
    if (!methods.isEmpty()) {
      Type[] argumentTypes = jsonObjectMethod.getArgumentTypes();
      
      while (jsonObjectClass != null) {
        Type[] jsonFunctionArgumentTypes = new Type[argumentTypes.length + 1];
        
        jsonFunctionArgumentTypes[0] = Type.getType(jsonObjectClass);
        System.arraycopy(argumentTypes, 0, jsonFunctionArgumentTypes, 1, argumentTypes.length);
        
        Method method = new Method(jsonFunctionAnnotation.method(), jsonObjectMethod.getReturnType(), jsonFunctionArgumentTypes);
        if (methods.contains(method)) {
          return method;
        }
        
        jsonObjectClass = !jsonObjectClass.equals(JsonObject.class) ? jsonObjectClass.getInterfaces()[0] : null;
      }
    }
    
    throw new IllegalArgumentException(jsonFunctionAnnotation.klass().getName() + " does not contain a suitable method named " + jsonFunctionAnnotation.method());
  }
  
  /**
   * Retrieve the has JSON property method.
   *
   * @return The has JSON property method.
   */
  private Method getHasJsonPropertyMethod() {
    return new Method("hasProperty", Type.BOOLEAN_TYPE, new Type[] { Type.getType(String.class) });
  }
  
  /**
   * Retrieve the is null JSON property method.
   *
   * @return The is null JSON property method.
   */
  private Method getIsNullJsonPropertyMethod() {
    return new Method("isNullProperty", Type.BOOLEAN_TYPE, new Type[] { Type.getType(String.class) });
  }
  
  /**
   * Retrieve the delete JSON property method.
   *
   * @return The delete JSON property method.
   */
  private Method getDeleteJsonPropertyMethod() {
    return new Method("deleteProperty", Type.BOOLEAN_TYPE, new Type[] { Type.getType(String.class) });
  }
  
  /**
   * Retrieve the get property method for the JSON primitive with the given
   * type.
   *
   * @param jsonPrimitiveType The type of the JSON primitive.
   * @return The get JSON primitive property method.
   */
  private Method getGetJsonPrimitivePropertyMethod(Type jsonPrimitiveType) {
    return new Method("get" + getJsonPrimitiveName(jsonPrimitiveType) + "Property", jsonPrimitiveType, new Type[] { Type.getType(String.class) });
  }
  
  /**
   * Retrieve the set property method for the JSON primitive with the given
   * type.
   *
   * @param jsonPrimitiveType The type of the JSON primitive.
   * @return The set JSON primitive property method.
   */
  private Method getSetJsonPrimitivePropertyMethod(Type jsonPrimitiveType) {
    return new Method("set" + getJsonPrimitiveName(jsonPrimitiveType) + "Property", Type.VOID_TYPE, new Type[] { Type.getType(String.class), jsonPrimitiveType });
  }
  
  /**
   * Retrieve the get JSON object property method.
   *
   * @return The get JSON object property method.
   */
  private Method getGetJsonObjectPropertyMethod() {
    return new Method("getObjectProperty", getJsonObjectType(), new Type[] { Type.getType(String.class), Type.getType(Class.class) });
  }
  
  /**
   * Retrieve the set JSON object property method.
   *
   * @return The set JSON object property method.
   */
  private Method getSetJsonObjectPropertyMethod() {
    return new Method("setObjectProperty", Type.VOID_TYPE, new Type[] { Type.getType(String.class), getJsonObjectType() });
  }
  
  /**
   * Retrieve the cast composite element method for the JSON object with the
   * given type.
   *
   * @param jsonObjectType The type of the JSON object.
   * @return  The cast composite JSON object element method.
   */
  private Method getCastCompositeJsonObjectElementMethod(Type jsonObjectType) {
    return new Method("castElement", jsonObjectType, new Type[] { Type.getType(Class.class) });
  }

  /**
   * Retrieve the from JSON property method for the property of the given type.
   *
   * @param jsonPropertyType The type of the JSON property.
   * @param propertyType The type of the property.
   * @return The from JSON property method.
   */
  private Method getFromJsonPropertyMethod(Type jsonPropertyType, Type propertyType) {
    return new Method("fromJsonProperty", propertyType, new Type[] { jsonPropertyType });
  }
  
  /**
   * Retrieve the to JSON property method for the property of the given type.
   *
   * @param jsonPropertyType The type of the JSON property.
   * @param propertyType The type of the property.
   * @return The to JSON property method.
   */
  private Method getToJsonPropertyMethod(Type jsonPropertyType, Type propertyType) {
    return new Method("toJsonProperty", jsonPropertyType, new Type[] { propertyType });
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
   * Retrieve the to method for the Java primitive with the given type.
   *
   * @param javaPrimitiveType The type of the Java primitive.
   * @return The to Java primitive method.
   */
  private Method getToJavaPrimitiveMethod(Type javaPrimitiveType) {
    return new Method(javaPrimitiveType.getClassName() + "Value", javaPrimitiveType, new Type[] {});
  }
  
  /**
   * Retrieve the from method for the Java primitive with the given type.
   *
   * @param javaPrimitiveType The type of the Java primitive.
   * @return The from Java primitive method.
   */
  private Method getFromJavaPrimitiveMethod(Type javaPrimitiveType) {
    return new Method("valueOf", this.getWrapperType(javaPrimitiveType), new Type[] { javaPrimitiveType });
  }
  
  /**
   * Retrieve the type of the wrapper for the Java primitive with the given
   * type.
   *
   * @param javaPrimitiveType The type of the Java primitive.
   * @return The type of the Java primitive wrapper.
   */
  private Type getWrapperType(Type javaPrimitiveType) {
    switch (javaPrimitiveType.getSort()) {
    case Type.BOOLEAN:
      return Type.getType(Boolean.class);
      
    case Type.BYTE:
      return Type.getType(Byte.class);
      
    case Type.SHORT:
      return Type.getType(Short.class);
      
    case Type.INT:
      return Type.getType(Integer.class);
    
    case Type.LONG:
      return Type.getType(Long.class);
    
    case Type.FLOAT:
      return Type.getType(Float.class);
    
    case Type.DOUBLE:
      return Type.getType(Double.class);
    
    case Type.CHAR:
      return Type.getType(Character.class);
      
    default:
      throw new IllegalArgumentException(javaPrimitiveType.toString());
    }
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
