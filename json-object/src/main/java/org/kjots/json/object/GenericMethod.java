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

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

/**
 * Generic Method.
 * <p>
 * Created: 11th April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class GenericMethod extends Method {
  /** The indices of the generic types. */
  private final Set<Integer> genericTypeIndices;
  
  /** The generic return type flag. */
  private final boolean genericReturnType;
  
  /**
   * Determine if the given Java method is a generic method.
   *
   * @param javaMethod The Java method.
   * @return <code>true</code> if the method is a generic method.
   */
  public static boolean isGenericMethod(java.lang.reflect.Method javaMethod) {
    if (isGenericType(javaMethod.getGenericReturnType())) {
      return true;
    }
    
    for (java.lang.reflect.Type parameterType : javaMethod.getGenericParameterTypes()) {
      if (isGenericType(parameterType)) {
        return true;
      }
    }
    
    return false;
  }
  
  /**
   * Retrieve the generic method for the given Java method.
   *
   * @param javaMethod The Java method.
   * @return The generic method.
   */
  public static GenericMethod getGenericMethod(java.lang.reflect.Method javaMethod) {
    Method method = Method.getMethod(javaMethod);
    Set<Integer> genericTypeIndices = new TreeSet<Integer>();
    
    java.lang.reflect.Type[] parameterTypes = javaMethod.getGenericParameterTypes();
    for (int i = 0; i < parameterTypes.length; i++) {
      if (isGenericType(parameterTypes[i])) {
        genericTypeIndices.add(i);
      }
    }
    
    return new GenericMethod(method, isGenericType(javaMethod.getGenericReturnType()), genericTypeIndices);
  }
  
  /**
   * Determine if the given Java type is a generic type.
   *
   * @param javaType The Java type.
   * @return <code>true</code> if the Java type is a generic type.
   */
  private static boolean isGenericType(java.lang.reflect.Type javaType) {
    return javaType instanceof TypeVariable<?> || 
           javaType instanceof GenericArrayType;
  }

  /**
   * Construct a new Generic Method.
   *
   * @param name The name.
   * @param descriptor The descriptor.
   * @param genericReturnType The generic return type flag.
   * @param genericTypeIndices The indices of the generic types.
   */
  public GenericMethod(String name, String descriptor, boolean genericReturnType, Set<Integer> genericTypeIndices) {
    super(name, descriptor);
    
    this.genericReturnType = genericReturnType;
    this.genericTypeIndices = genericTypeIndices;
  }

  /**
   * Construct a new Generic Method.
   *
   * @param name The name.
   * @param returnType The return type.
   * @param argumentTypes The argument types.
   * @param genericReturnType The generic return type flag.
   * @param genericTypeIndices The indices of the generic types.
   */
  public GenericMethod(String name, Type returnType, Type[] argumentTypes, boolean genericReturnType, Set<Integer> genericTypeIndices) {
    super(name, returnType, argumentTypes);
    
    this.genericReturnType = genericReturnType;
    this.genericTypeIndices = genericTypeIndices;
  }
  
  /**
   * Construct a new Generic Method.
   *
   * @param method The method.
   * @param genericReturnType The generic return type flag.
   * @param genericTypeIndices The indices of the generic types.
   */
  public GenericMethod(Method method, boolean genericReturnType, Set<Integer> genericTypeIndices) {
    super(method.getName(), method.getDescriptor());
    
    this.genericReturnType = genericReturnType;
    this.genericTypeIndices = genericTypeIndices;
  }
  
  /**
   * Retrieve the generic return type flag.
   *
   * @return The generic return type flag.
   */
  public boolean getGenericReturnType() {
    return this.genericReturnType;
  }

  /**
   * Retrieve the indices of the generic types.
   *
   * @return The indices of the generic types.
   */
  public Set<Integer> getGenericTypeIndices() {
    return this.genericTypeIndices;
  }
  
  /**
   * Retrieve a compatible method for the generic method from the given
   * methods.
   * <p>
   * This method will return <code>null</code> if there is no compatible method
   * in the given methods.
   * 
   * @param methods The methods.
   * @return The compatible method.
   */
  public Method getCompatibleMethod(Collection<Method> methods) {
    for (Method method : methods) {
      if (this.isCompatible(method)) {
        return method;
      }
    }
    
    return null;
  }
  
  /**
   * Determine if the given method is compatible with the generic method.
   *
   * @param method The method.
   * @return <code>true</code> if the method is compatible.
   */
  public boolean isCompatible(Method method) {
    if (!this.getName().equals(method.getName())) {
      return false;
    }
    
    if (this.genericReturnType) {
      if (!this.isCompatibleType(this.getReturnType(), method.getReturnType())) {
        return false;
      }
    }
    else if (!this.getReturnType().equals(method.getReturnType())) {
      return false;
    }
    
    Type[] argumentTypes = this.getArgumentTypes();
    Type[] methodArgumentTypes = method.getArgumentTypes();
    
    if (argumentTypes.length != methodArgumentTypes.length) {
      return false;
    }
    
    for (int i = 0; i < argumentTypes.length; i++) {
      Type argumentType = argumentTypes[i];
      Type methodArgumentType = methodArgumentTypes[i];
      
      if (this.genericTypeIndices.contains(i)) {
        if (!this.isCompatibleType(argumentType, methodArgumentType)) {
          return false;
        }
      }
      else if (!argumentType.equals(methodArgumentType)) {
        return false;
      }
    }
    
    return true;
  }
  
  /**
   * Determine if the given generic type is compatible with the given type.
   *
   * @param genericType The generic type.
   * @param type The type.
   * @return <code>true<code> if the generic type is compatible with the type.
   */
  private boolean isCompatibleType(Type genericType, Type type) {
    if (genericType.getSort() == Type.OBJECT) {
      if (type.getSort() != Type.OBJECT) {
        return false;
      }
      
      try {
        return Class.forName(genericType.getClassName()).isAssignableFrom(Class.forName(type.getClassName()));
      }
      catch (ClassNotFoundException cnfe) {
        throw new IllegalStateException(cnfe);
      }
    }
    else if (genericType.getSort() == Type.ARRAY) {
      if (type.getSort() != Type.ARRAY) {
        return false;
      }
      
      if (genericType.getDimensions() != type.getDimensions()) {
        return false;
      }
      
      return isCompatibleType(genericType.getElementType(), type.getElementType());
    }
    else {
      assert false : "Unreachable condition"; return false;
    }
  }
}
