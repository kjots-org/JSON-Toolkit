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
package org.kjots.json.object.server;

import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.CHECKCAST;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import org.kjots.json.object.shared.JsonBooleanPropertyAdapter;
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
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

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
        jsonObjectImplClass = Class.forName(jsonObjectClass.getName() + "Impl", true, this.getClass().getClassLoader());
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
   * Retrieve the constructor arguments.
   *
   * @return The constructor arguments.
   */
  protected abstract ConstructorArgument[] getConstructorArguments();
  
  /**
   * Define the class of the implementation of the JSON object from the given class bytes.
   *
   * @param implClassBytes The class bytes of the implementation of the JSON object.
   * @return The class of the implementation of the JSON object.
   */
  @SuppressWarnings("unchecked")
  private Class<? extends T> defineImplClass(byte[] implClassBytes) {
    // Retrieve the ClassLoader.defineClass() method.
    Method defineClassMethod;
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
    if (implementedInterfaces.length != 1) {
      throw new IllegalArgumentException(jsonObjectClass.getName() + " must extend exactly one interface");
    }
    
    Class<? extends T> superJsonObjectImplClass;
    
    Class<?> implementedInterface = implementedInterfaces[0];
    if (implementedInterface.equals(JsonObject.class)) {
      superJsonObjectImplClass = this.jsonObjectImplClass;
    }
    else {
      superJsonObjectImplClass = this.getJsonObjectImplClass((Class<? extends JsonObject>)implementedInterface);
    }
    
    ClassWriter classWriter = new ClassWriter(0);
    
    String jsonObjectIClassName = Type.getInternalName(jsonObjectClass);
    String jsonObjectImplIClassName = jsonObjectIClassName + "Impl";
    String superJsonObjectImplIClassName = Type.getInternalName(superJsonObjectImplClass);
    
    classWriter.visit(V1_6, ACC_PUBLIC + ACC_SUPER, jsonObjectImplIClassName, null, superJsonObjectImplIClassName, new String[] { jsonObjectIClassName });
    
    this.generateConstructor(classWriter, jsonObjectImplIClassName, superJsonObjectImplIClassName);
    
    for (Method method : jsonObjectClass.getDeclaredMethods()) {
      this.generatePropertyMethod(classWriter, jsonObjectImplIClassName, method);
    }
    
    return classWriter.toByteArray(); 
  }
  
  /**
   * Generate the constructor.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param superJsonObjectImplIClassName The internal class name of the super JSON object implementation.
   */
  private void generateConstructor(ClassWriter classWriter, String jsonObjectImplIClassName, String superJsonObjectImplIClassName) {
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
    methodVisitor.visitMethodInsn(INVOKESPECIAL, superJsonObjectImplIClassName, "<init>", constructorSignature);
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    for (int i = 0; i < constructorArguments.length; i++) {
      methodVisitor.visitLocalVariable(constructorArguments[i].getVariableName(), constructorArguments[i].getTypeIName(), null, start, end, i + 1);
    }
    methodVisitor.visitMaxs(constructorArguments.length + 1, constructorArguments.length + 1);
    
    methodVisitor.visitEnd();
  }

  /**
   * Generate a property method. 
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param method The method.
   */
  private void generatePropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, Method method) {
    JsonProperty jsonPropertyAnnotation = method.getAnnotation(JsonProperty.class);
    if (jsonPropertyAnnotation == null) {
      throw new IllegalArgumentException(method.getName() + "() is not annotated with @" + JsonProperty.class.getName());
    }
    
    switch (jsonPropertyAnnotation.operation()) {
    case HAS:
      this.generateHasPropertyMethod(classWriter, jsonObjectImplIClassName, method, jsonPropertyAnnotation);
      
      return;
      
    case IS_NULL:
      this.generateIsNullPropertyMethod(classWriter, jsonObjectImplIClassName, method, jsonPropertyAnnotation);
      
      return;
      
    case DELETE:
      this.generateDeletePropertyMethod(classWriter, jsonObjectImplIClassName, method, jsonPropertyAnnotation);
      
      return;
      
    case GET:
      this.generateGetPropertyMethod(classWriter, jsonObjectImplIClassName, method, jsonPropertyAnnotation);
      
      return;
      
    case SET:
      this.generateSetPropertyMethod(classWriter, jsonObjectImplIClassName, method, jsonPropertyAnnotation);
      
      return;
      
    default:
      throw new IllegalArgumentException(method.getName() + "() is annotated with unsupported operation type " + jsonPropertyAnnotation.operation());
    }
  }

  /**
   * Generate a has property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateHasPropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, Method method, JsonProperty jsonPropertyAnnotation) {
    Class<?>[] parameterTypes = method.getParameterTypes();
    if (parameterTypes.length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    Class<?> returnType = method.getReturnType();
    if (!returnType.equals(boolean.class)) {
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    this.generateHasPropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name());
  }
  
  /**
   * Generate an is null property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateIsNullPropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, Method method, JsonProperty jsonPropertyAnnotation) {
    Class<?>[] parameterTypes = method.getParameterTypes();
    if (parameterTypes.length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    Class<?> returnType = method.getReturnType();
    if (!returnType.equals(boolean.class)) {
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    this.generateIsNullPropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name());
  }
  
  /**
   * Generate a delete property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateDeletePropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, Method method, JsonProperty jsonPropertyAnnotation) {
    Class<?>[] parameterTypes = method.getParameterTypes();
    if (parameterTypes.length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    Class<?> returnType = method.getReturnType();
    if (!returnType.equals(boolean.class)) {
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    this.generateDeletePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name());
  }
  
  /**
   * Generate a get property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateGetPropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, Method method, JsonProperty jsonPropertyAnnotation) {
    Class<?>[] parameterTypes = method.getParameterTypes();
    if (parameterTypes.length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    Class<?> returnType = method.getReturnType();

    Class<? extends JsonPropertyAdapter> adapterClass = jsonPropertyAnnotation.adapter();
    if (!adapterClass.equals(JsonPropertyAdapter.class)) {
      if (JsonBooleanPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateGetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), Type.getInternalName(returnType), Type.getInternalName(adapterClass), "Z", "getBooleanProperty", ISTORE, ILOAD);
      }
      else if (JsonNumberPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateGetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), Type.getInternalName(returnType), Type.getInternalName(adapterClass), "Ljava/lang/Number;", "getNumberProperty", ASTORE, ALOAD);
      }
      else if (JsonStringPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateGetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), Type.getInternalName(returnType), Type.getInternalName(adapterClass), "Ljava/lang/String;", "getStringProperty", ASTORE, ALOAD);
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
        
        this.generateGetAdaptedObjectPropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), Type.getInternalName(returnType), Type.getInternalName(adapterClass), Type.getInternalName(jsonObjectClass));
      }
      else {
        throw new IllegalArgumentException("Unsupported adapter type " + adapterClass.getName());
      }
    }
    else {
      if (returnType.equals(boolean.class)) {
        this.generateGetPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), "Z", "getBooleanProperty", IRETURN);
      }
      else if (returnType.equals(Number.class)) {
        this.generateGetPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), "Ljava/lang/Number;", "getNumberProperty", ARETURN);
      }
      else if (returnType.equals(String.class)) {
        this.generateGetPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), "Ljava/lang/String;", "getStringProperty", ARETURN);
      }
      else if (returnType.equals(JsonObjectArray.class) || returnType.equals(JsonObjectMap.class)) {
        Class<?> elementType = JsonObject.class;
        
        java.lang.reflect.Type genericReturnType = method.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
          ParameterizedType parameterizedReturnType = (ParameterizedType)genericReturnType;
          
          java.lang.reflect.Type[] typeArguments = parameterizedReturnType.getActualTypeArguments();
          if (typeArguments.length != 0) {
            elementType = (Class<?>)typeArguments[0];
          }
        }
        
        this.generateGetCompositeObjectPropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), Type.getInternalName(returnType), Type.getInternalName(elementType));
      }
      else {
        this.generateGetObjectPropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), Type.getInternalName(returnType));
      }
    }
  }
  
  /**
   * Generate a set property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param method The method.
   * @param jsonPropertyAnnotation The JSON property annotation.
   */
  private void generateSetPropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, Method method, JsonProperty jsonPropertyAnnotation) {
    Class<?> returnType = method.getReturnType();
    if (!returnType.equals(void.class)) {
      throw new IllegalArgumentException(method.getName() + "() must have a void return type");
    }
    
    Class<?>[] parameterTypes = method.getParameterTypes();
    if (parameterTypes.length != 1) {
      throw new IllegalArgumentException(method.getName() + "() must have exactly one parameter");
    }
    
    Class<?> parameterType = parameterTypes[0];
    
    Class<? extends JsonPropertyAdapter> adapterClass = jsonPropertyAnnotation.adapter();
    if (!adapterClass.equals(JsonPropertyAdapter.class)) {
      if (JsonBooleanPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateSetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), Type.getInternalName(parameterType), Type.getInternalName(adapterClass), "Z", "setBooleanProperty", ISTORE, ILOAD);
      }
      else if (JsonNumberPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateSetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), Type.getInternalName(parameterType), Type.getInternalName(adapterClass), "Ljava/lang/Number;", "setNumberProperty", ASTORE, ALOAD);
      }
      else if (JsonStringPropertyAdapter.class.isAssignableFrom(adapterClass)) {
        this.generateSetAdaptedPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), Type.getInternalName(parameterType), Type.getInternalName(adapterClass), "Ljava/lang/String;", "setStringProperty", ASTORE, ALOAD);
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
        
        this.generateSetAdaptedObjectPropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), Type.getInternalName(parameterType), Type.getInternalName(adapterClass), Type.getInternalName(jsonObjectClass));
      }
      else {
        throw new IllegalArgumentException("Unsupported adapter type " + adapterClass.getName());
      }
    }
    else {
      if (parameterType.equals(boolean.class)) {
        this.generateSetPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), "Z", "setBooleanProperty", ILOAD);
      }
      else if (parameterType.equals(Number.class)) {
        this.generateSetPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), "Ljava/lang/Number;", "setNumberProperty", ALOAD);
      }
      else if (parameterType.equals(String.class)) {
        this.generateSetPrimitivePropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), "Ljava/lang/String;", "setStringProperty", ALOAD);
      }
      else {
        this.generateSetObjectPropertyMethod(classWriter, jsonObjectImplIClassName, method.getName(), jsonPropertyAnnotation.name(), Type.getInternalName(parameterType));
      }
    }
  }
  
  /**
   * Generate a has property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   */
  private void generateHasPropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()Z", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, "hasProperty", "(Ljava/lang/String;)Z");
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate an is null property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   */
  private void generateIsNullPropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()Z", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, "isNullProperty", "(Ljava/lang/String;)Z");
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a delete property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   */
  private void generateDeletePropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()Z", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, "deleteProperty", "(Ljava/lang/String;)Z");
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param primitiveTypeSignature The type signature of the primitive.
   * @param primitiveMethodName The name of the primitive method.
   * @param returnOpcode The return opcode.
   */
  private void generateGetPrimitivePropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName, String primitiveTypeSignature, String primitiveMethodName, int returnOpcode) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()" + primitiveTypeSignature, null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, primitiveMethodName, "(Ljava/lang/String;)" + primitiveTypeSignature);
    methodVisitor.visitInsn(returnOpcode);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param primitiveTypeSignature The type signature of the primitive.
   * @param primitiveMethodName The name of the primitive method.
   * @param loadOpcode The load opcode.
   */
  private void generateSetPrimitivePropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName, String primitiveTypeSignature, String primitiveMethodName, int loadOpcode) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "(" + primitiveTypeSignature + ")V", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(loadOpcode, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, primitiveMethodName, "(Ljava/lang/String;" + primitiveTypeSignature + ")V");
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, primitiveTypeSignature, null, start, end, 1);
    methodVisitor.visitMaxs(3 , 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get object property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyIClassName The internal class name of the property.
   */
  private void generateGetObjectPropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName, String propertyIClassName) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()L" + propertyIClassName + ";", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitLdcInsn(Type.getType("L" + propertyIClassName + ";"));
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, "getObjectProperty", "(Ljava/lang/String;Ljava/lang/Class;)Lorg/kjots/json/object/shared/JsonObject;");
    methodVisitor.visitTypeInsn(CHECKCAST, propertyIClassName);
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitMaxs(3, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set object property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyIClassName The internal class name of the property.
   */
  private void generateSetObjectPropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName, String propertyIClassName) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "(L" + propertyIClassName + ";)V", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, "setObjectProperty", "(Ljava/lang/String;Lorg/kjots/json/object/shared/JsonObject;)V");
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, "L" + propertyIClassName + ";", null, start, end, 1);
    methodVisitor.visitMaxs(3, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get composite object property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyIClassName The internal class name of the property.
   * @param elementIClassName The internal class name of the element.
   */
  private void generateGetCompositeObjectPropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName, String propertyIClassName, String elementIClassName) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()L" + propertyIClassName + ";", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitLdcInsn(Type.getType("L" + propertyIClassName + ";"));
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, "getObjectProperty", "(Ljava/lang/String;Ljava/lang/Class;)Lorg/kjots/json/object/shared/JsonObject;");
    methodVisitor.visitTypeInsn(CHECKCAST, propertyIClassName);
    methodVisitor.visitLdcInsn(Type.getType("L" + elementIClassName + ";"));
    methodVisitor.visitMethodInsn(INVOKEINTERFACE, propertyIClassName, "castElement", "(Ljava/lang/Class;)L" + propertyIClassName + ";");
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitMaxs(3, 1);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get adapted primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyIClassName The internal class name of the property.
   * @param adapterIClassName The internal class name of the adapter.
   * @param primitiveTypeSignature The type signature of the primitive.
   * @param primitiveMethodName The name of the primitive method.
   * @param storeOpcode The store opcode.
   * @param loadOpcode The load opcode.
   */
  private void generateGetAdaptedPrimitivePropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName, String propertyIClassName, String adapterIClassName, String primitiveTypeSignature, String primitiveMethodName, int storeOpcode, int loadOpcode) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()L" + propertyIClassName + ";", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, primitiveMethodName, "(Ljava/lang/String;)" + primitiveTypeSignature);
    methodVisitor.visitVarInsn(storeOpcode, 1);
    methodVisitor.visitTypeInsn(NEW, adapterIClassName);
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterIClassName, "<init>", "()V");
    methodVisitor.visitVarInsn(loadOpcode, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterIClassName, "fromJsonProperty", "(" + primitiveTypeSignature + ")L" + propertyIClassName + ";");
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitLocalVariable("_" + propertyName, primitiveTypeSignature, null, start, end, 1);
    methodVisitor.visitMaxs(2, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set adapted primitive property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyIClassName The internal class name of the property.
   * @param adapterIClassName The internal class name of the adapter.
   * @param primitiveTypeSignature The type signature of the primitive.
   * @param primitiveMethodName The name of the primitive method.
   * @param storeOpcode The store opcode.
   * @param loadOpcode The load opcode.
   */
  private void generateSetAdaptedPrimitivePropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName, String propertyIClassName, String adapterIClassName, String primitiveTypeSignature, String primitiveMethodName, int storeOpcode, int loadOpcode) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "(L" + propertyIClassName + ";)V", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    methodVisitor.visitLabel(start);
    methodVisitor.visitTypeInsn(NEW, adapterIClassName);
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterIClassName, "<init>", "()V");
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterIClassName, "toJsonProperty", "(L" + propertyIClassName + ";)" + primitiveTypeSignature);
    methodVisitor.visitVarInsn(storeOpcode, 2);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(loadOpcode, 2);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, primitiveMethodName, "(Ljava/lang/String;" + primitiveTypeSignature + ")V");
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, "L" + propertyIClassName + ";", null, start, end, 1);
    methodVisitor.visitLocalVariable("_" + propertyName, primitiveTypeSignature, null, start, end, 2);
    methodVisitor.visitMaxs(3, 3);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a get adapted object property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyIClassName The internal class name of the property.
   * @param adapterIClassName The internal class name of the adapter.
   * @param jsonObjectIClassName The internal class name of the JSON object.
   */
  private void generateGetAdaptedObjectPropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName, String propertyIClassName, String adapterIClassName, String jsonObjectIClassName) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "()L" + propertyIClassName + ";", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitLdcInsn(Type.getType("L" + jsonObjectIClassName + ";"));
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, "getObjectProperty", "(Ljava/lang/String;Ljava/lang/Class;)Lorg/kjots/json/object/shared/JsonObject;");
    methodVisitor.visitTypeInsn(CHECKCAST, jsonObjectIClassName);
    methodVisitor.visitVarInsn(ASTORE, 1);
    methodVisitor.visitTypeInsn(NEW, adapterIClassName);
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterIClassName, "<init>", "()V");
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterIClassName, "fromJsonProperty", "(L" + jsonObjectIClassName + ";)L" + propertyIClassName + ";");
    methodVisitor.visitInsn(ARETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitLocalVariable("_" + propertyName, "L" + jsonObjectIClassName + ";", null, start, end, 1);
    methodVisitor.visitMaxs(3, 2);
    
    methodVisitor.visitEnd();
  }
  
  /**
   * Generate a set adapted object property method.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param methodName The name of the method.
   * @param propertyName The name of the property.
   * @param propertyIClassName The internal class name of the property.
   * @param adapterIClassName The internal class name of the adapter.
   * @param jsonObjectIClassName The internal class name of the JSON object.
   */
  private void generateSetAdaptedObjectPropertyMethod(ClassWriter classWriter, String jsonObjectImplIClassName, String methodName, String propertyName, String propertyIClassName, String adapterIClassName, String jsonObjectIClassName) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, methodName, "(L" + propertyIClassName + ";)V", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitLineNumber(138, start);
    methodVisitor.visitTypeInsn(NEW, adapterIClassName);
    methodVisitor.visitInsn(DUP);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, adapterIClassName, "<init>", "()V");
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, adapterIClassName, "toJsonProperty", "(L" + propertyIClassName + ";)L" + jsonObjectIClassName + ";");
    methodVisitor.visitVarInsn(ASTORE, 2);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(propertyName);
    methodVisitor.visitVarInsn(ALOAD, 2);
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplIClassName, "setObjectProperty", "(Ljava/lang/String;Lorg/kjots/json/object/shared/JsonObject;)V");
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitLocalVariable(propertyName, "L" + propertyIClassName + ";", null, start, end, 1);
    methodVisitor.visitLocalVariable("_" + propertyName, "L" + jsonObjectIClassName + ";", null, start, end, 2);
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
}
