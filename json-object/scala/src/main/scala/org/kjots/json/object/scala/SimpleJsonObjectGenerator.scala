/* 
 * Copyright © 2011 Karl J. Ots <kjots@kjots.org>
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
package org.kjots.json.`object`.scala

import java.io.PrintWriter

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.Type
import org.objectweb.asm.commons.Method
import org.objectweb.asm.util.TraceClassVisitor

import org.kjots.json.`object`.ClassVisitor
import org.kjots.json.`object`.shared._
import org.kjots.json.`object`.simple.impl.SimpleJsonObjectImpl
import org.kjots.json.`object`.simple.SimpleJsonValue

/**
 * Simple JSON Object Generator.
 * <p>
 * Created: 29th May 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.2
 */
object SimpleJsonObjectGenerator {
  import org.objectweb.asm.Opcodes._

  def getJsonObjectImplClass(jsonObjectClass: Class[_ <: JsonObject]) = {
    var jsonObjectImplClass: Class[_] = null

    if (classOf[SimpleJsonObjectImpl].isAssignableFrom(jsonObjectClass)) {
      jsonObjectImplClass = jsonObjectClass
    } else {
      jsonObjectImplClass = try {
        Class.forName(jsonObjectClass.getName() + "$scala$" + classOf[SimpleJsonObjectImpl].getSimpleName(), true, getClass().getClassLoader())
      } catch {
        case cnfe: ClassNotFoundException => defineImplClass(generateImplClassBytes(jsonObjectClass));
      }
    }

    jsonObjectImplClass.asInstanceOf[Class[_ <: SimpleJsonObjectImpl]]
  }

  def newJsonObjectImpl(jsonObjectClass: Class[_ <: JsonObject], obj: SimpleJsonValue) =
    getJsonObjectImplClass(jsonObjectClass)
      .getConstructor(classOf[Class[_ <: JsonObject]], classOf[SimpleJsonValue])
      .newInstance(jsonObjectClass, obj)

  private def defineImplClass(implClassBytes: Array[Byte]) = {
    val defineClassMethod = classOf[ClassLoader]
      .getDeclaredMethod("defineClass", classOf[String], classOf[Array[Byte]], classOf[Int], classOf[Int]);

    defineClassMethod.setAccessible(true)
    try {
      defineClassMethod
        .invoke(getClass().getClassLoader(), null, implClassBytes, 0.asInstanceOf[AnyRef], implClassBytes.length.asInstanceOf[AnyRef])
        .asInstanceOf[Class[_ <: SimpleJsonObjectImpl]]
    } finally {
      defineClassMethod.setAccessible(false)
    }
  }

  private def generateImplClassBytes(jsonObjectClass: Class[_ <: JsonObject]): Array[Byte] = {
    val classPackage = jsonObjectClass.getPackage();
    if (classPackage == null || classPackage.getName().isEmpty()) {
      throw new IllegalArgumentException(jsonObjectClass.getName() + " is not in a package");
    }

    if (!jsonObjectClass.isInterface()) {
      throw new IllegalArgumentException(jsonObjectClass.getName() + " is not an interface");
    }

    val implementedInterfaces = jsonObjectClass.getInterfaces();
    if (implementedInterfaces.length == 0) {
      throw new IllegalArgumentException(jsonObjectClass.getName() + " must extend at least one interface");
    }

    val superJsonObjectImplClass: Class[_ <: SimpleJsonObjectImpl] =
      if (implementedInterfaces(0) == classOf[JsonObject])
        classOf[SimpleJsonObjectImpl]
      else
        getJsonObjectImplClass(implementedInterfaces(0).asInstanceOf[Class[_ <: JsonObject]])

    val classWriter = new ClassWriter(0)
    val traceClassVisitor = new TraceClassVisitor(classWriter, new PrintWriter(System.err))

    generateClass(new ClassVisitor(traceClassVisitor), jsonObjectClass, Type.getType(jsonObjectClass), Type.getType(superJsonObjectImplClass))

    classWriter.toByteArray()
  }
  
  private def generateClass(classVisitor: ClassVisitor, jsonObjectClass: Class[_ <: JsonObject], jsonObjectType: Type, superJsonObjectImplType: Type) {
    val jsonObjectImplType = Type.getObjectType(jsonObjectType.getInternalName() + "$scala$" + classOf[SimpleJsonObjectImpl].getSimpleName())
    
    classVisitor.visit(V1_6, ACC_PUBLIC + ACC_SUPER, jsonObjectImplType, null, superJsonObjectImplType, jsonObjectType);
    
    generateConstructor(classVisitor, jsonObjectImplType, superJsonObjectImplType)
    
    for (method <- jsonObjectClass.getDeclaredMethods()) { 
      if (method.getAnnotation(classOf[JsonFunction]) != null) {
        generateFunctionMethod(classVisitor, jsonObjectImplType, jsonObjectClass, Method.getMethod(method), method.getAnnotation(classOf[JsonFunction]), method.isVarArgs());
      }
      else if (method.getAnnotation(classOf[JsonException]) != null) {
        generateExceptionMethod(classVisitor, jsonObjectImplType, jsonObjectClass, Method.getMethod(method), method.getAnnotation(classOf[JsonException]), method.isVarArgs());
      }
      else if (method.getAnnotation(classOf[JsonProperty]) != null) {
        generatePropertyMethod(classVisitor, jsonObjectImplType, method, Method.getMethod(method), method.getAnnotation(classOf[JsonProperty]))
      }
      else {
        throw new IllegalArgumentException(method.getName() + "() is not annotated with suitable annotation")
      }
    }
    
    classVisitor.visitEnd();
  }
  
  private def generateConstructor(classVisitor: ClassVisitor, jsonObjectImplType: Type, superJsonObjectImplType: Type) {
    val constructor = Method.getMethod(classOf[SimpleJsonObjectImpl].getConstructors()(0))
    val argumentTypes = constructor.getArgumentTypes()
    
    val methodVisitor = classVisitor.visitMethod(ACC_PUBLIC, constructor, null, null);
    
    val start = new Label();
    val end = new Label();
    
    var index = 1
    var maxLocals = 1
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    for (argumentType <- argumentTypes) {
      methodVisitor.visitVarInsn(argumentType.getOpcode(ILOAD), index);
      
      index += argumentType.getSize();
    }
    methodVisitor.visitMethodInsn(INVOKESPECIAL, superJsonObjectImplType, constructor);
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    for (i <- 0 to argumentTypes.length - 1) {
      val argumentType = argumentTypes(i)
      
      methodVisitor.visitLocalVariable("arg" + i, argumentType, null, start, end, maxLocals);
      
      maxLocals += argumentType.getSize();
    }
    methodVisitor.visitMaxs(maxLocals, maxLocals);
    
    methodVisitor.visitEnd();
  }
  
  private def generateFunctionMethod(classVisitor: ClassVisitor, jsonObjectImplType: Type, jsonObjectClass: Class[_ <: JsonObject], method: Method, jsonFunctionAnnotation: JsonFunction, varargs: Boolean) {
    // TODO: Implement this method.
  }
  
  private def generateExceptionMethod(classVisitor: ClassVisitor, jsonObjectImplType: Type, jsonObjectClass: Class[_ <: JsonObject], method: Method, jsonExceptionAnnotation: JsonException, varargs: Boolean) {
    // TODO: Implement this method.
  }
  
  private def generatePropertyMethod(classVisitor: ClassVisitor, jsonObjectImplType: Type, javaMethod: java.lang.reflect.Method, method: Method, jsonPropertyAnnotation: JsonProperty) {
    import JsonProperty.OperationType._
    
    jsonPropertyAnnotation.operation() match {
      case e if e == HAS => generateHasPropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation)
      case e if e == IS_NULL => generateIsNullPropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation)
      case e if e == DELETE => generateDeletePropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation)
      case e if e == GET => generateGetPropertyMethod(classVisitor, jsonObjectImplType, javaMethod, method, jsonPropertyAnnotation)
      case e if e == SET => generateSetPropertyMethod(classVisitor, jsonObjectImplType, method, jsonPropertyAnnotation)
    }
  }
  
  private def generateHasPropertyMethod(classVisitor: ClassVisitor, jsonObjectImplType: Type, method: Method, jsonPropertyAnnotation: JsonProperty) {
    if (!method.getReturnType().equals(Type.BOOLEAN_TYPE)) { 
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    if (method.getArgumentTypes().length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    val methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    val start = new Label();
    val end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(jsonPropertyAnnotation.name());
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, hasJsonPropertyMethod);
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  private def generateIsNullPropertyMethod(classVisitor: ClassVisitor, jsonObjectImplType: Type, method: Method, jsonPropertyAnnotation: JsonProperty) {
    if (!method.getReturnType().equals(Type.BOOLEAN_TYPE)) { 
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    if (method.getArgumentTypes().length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    val methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    val start = new Label();
    val end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(jsonPropertyAnnotation.name());
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, isNullJsonPropertyMethod);
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  private def generateDeletePropertyMethod(classVisitor: ClassVisitor, jsonObjectImplType: Type, method: Method, jsonPropertyAnnotation: JsonProperty) {
    if (!method.getReturnType().equals(Type.BOOLEAN_TYPE)) { 
      throw new IllegalArgumentException(method.getName() + "() must have a boolean return type");
    }
    
    if (method.getArgumentTypes().length != 0) {
      throw new IllegalArgumentException(method.getName() + "() must have no parameters");
    }
    
    val methodVisitor = classVisitor.visitMethod(ACC_PUBLIC + ACC_FINAL, method, null, null);
    
    val start = new Label();
    val end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitLdcInsn(jsonPropertyAnnotation.name());
    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, jsonObjectImplType, deleteJsonPropertyMethod);
    methodVisitor.visitInsn(IRETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", jsonObjectImplType, null, start, end, 0);
    methodVisitor.visitMaxs(2, 1);
    
    methodVisitor.visitEnd();
  }
  
  private def generateGetPropertyMethod(classVisitor: ClassVisitor, jsonObjectImplType: Type, javaMethod: java.lang.reflect.Method, method: Method, jsonPropertyAnnotation: JsonProperty) {
    // TODO: Implement this method.
  }
  
  private def generateSetPropertyMethod(classVisitor: ClassVisitor, jsonObjectImplType: Type, method: Method, jsonPropertyAnnotation: JsonProperty) {
    // TODO: Implement this method.
  }
  
  private def hasJsonPropertyMethod = 
    new Method("hasProperty", Type.BOOLEAN_TYPE, Array(Type.getType(classOf[String])))
  
  private def isNullJsonPropertyMethod =
    new Method("isNullProperty", Type.BOOLEAN_TYPE, Array(Type.getType(classOf[String])))
  
  private def deleteJsonPropertyMethod = 
    new Method("deleteProperty", Type.BOOLEAN_TYPE, Array(Type.getType(classOf[String])))
}
