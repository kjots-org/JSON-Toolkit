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

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

/**
 * Class Visitor.
 * <p>
 * Created: 7th April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class ClassVisitor implements org.objectweb.asm.ClassVisitor {
  /** The ASM class visitor. */
  private final org.objectweb.asm.ClassVisitor asmClassVisitor;

  /**
   * Construct a new Class Visitor.
   *
   * @param asmClassVisitor The ASM class visitor.
   */
  public ClassVisitor(org.objectweb.asm.ClassVisitor asmClassVisitor) {
    this.asmClassVisitor = asmClassVisitor;
  }
  
  /**
   * Visit the class.
   * <p>
   * This is a convenience method that is equivalent to the following:
   * <pre>
   *   visit(version, access, type.getInternalName(), signature, superType.getInternalName(), interfaces)
   * </pre>
   * Where <code>interfaces</code> is an array of {@link String}, with each
   * element containing the internal name of the types in <code>interfaceTypes</code>
   * at the corresponding index.
   *
   * @param version The version number.
   * @param access The access flags.
   * @param type The type.
   * @param signature The signature.
   * @param superType The super type.
   * @param interfaceTypes The interface types.
   * @see org.objectweb.asm.ClassVisitor#visit(int, int, String, String, String, String[])
   */
  public void visit(int version, int access, Type type, String signature, Type superType, Type[] interfaceTypes) {
    String[] interfaces = new String[interfaceTypes.length];
    for (int i = 0; i < interfaceTypes.length; i++) {
      interfaces[i] = interfaceTypes[i].getInternalName();
    }
    
    this.asmClassVisitor.visit(version, access, type.getInternalName(), signature, superType.getInternalName(), interfaces);
  }
  
  /**
   * Visit a method.
   * <p>
   * This is a convenience method that is equivalent to the following:
   * <pre>
   *   visitMethod(access, method.getName(), method.getDescriptor(), signature, exceptions))
   * </pre>
   *
   * @param access The access flags.
   * @param method The method.
   * @param signature The signature.
   * @param exceptions The exception.
   * @return The method visitor.
   * @see org.objectweb.asm.ClassVisitor#visitMethod(int, String, String, String, String[])
   */
  public MethodVisitor visitMethod(int access, Method method, String signature, String[] exceptions) {
    return new MethodVisitor(this.asmClassVisitor.visitMethod(access, method.getName(), method.getDescriptor(), signature, exceptions));
  }

  /**
   * @see org.objectweb.asm.ClassVisitor#visit(int, int, String, String, String, String[])
   */
  public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
    this.asmClassVisitor.visit(version, access, name, signature, superName, interfaces);
  }
  
  /**
   * @see org.objectweb.asm.ClassVisitor#visitAnnotation(String, boolean)
   */
  public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    return this.asmClassVisitor.visitAnnotation(desc, visible);
  }

  /**
   * @see org.objectweb.asm.ClassVisitor#visitAttribute(Attribute)
   */
  public void visitAttribute(Attribute attr) {
    this.asmClassVisitor.visitAttribute(attr);
  }

  /**
   * @see org.objectweb.asm.ClassVisitor#visitEnd()
   */
  public void visitEnd() {
    this.asmClassVisitor.visitEnd();
  }

  /**
   * @see org.objectweb.asm.ClassVisitor#visitField(int, String, String, String, Object)
   */
  public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
    return this.asmClassVisitor.visitField(access, name, desc, signature, value);
  }

  /**
   * @see org.objectweb.asm.ClassVisitor#visitInnerClass(String, String, String, int)
   */
  public void visitInnerClass(String name, String outerName, String innerName, int access) {
    this.asmClassVisitor.visitInnerClass(name, outerName, innerName, access);
  }

  /**
   * @see org.objectweb.asm.ClassVisitor#visitMethod(int, String, String, String, String[])
   */
  public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
    return new MethodVisitor(this.asmClassVisitor.visitMethod(access, name, desc, signature, exceptions));
  }

  /**
   * @see org.objectweb.asm.ClassVisitor#visitOuterClass(String, String, String)
   */
  public void visitOuterClass(String owner, String name, String desc) {
    this.asmClassVisitor.visitOuterClass(owner, name, desc);
  }

  /**
   * @see org.objectweb.asm.ClassVisitor#visitSource(String, String)
   */
  public void visitSource(String source, String debug) {
    this.asmClassVisitor.visitSource(source, debug);
  }
}
