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
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

/**
 * Method Visitor.
 * <p>
 * Created: 7th April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class MethodVisitor implements org.objectweb.asm.MethodVisitor {
  /** The ASM method visitor. */
  private final org.objectweb.asm.MethodVisitor asmMethodVisitor;
  
  /**
   * Construct a new Method Visitor.
   *
   * @param asmMethodVisitor The ASM method visitor.
   */
  public MethodVisitor(org.objectweb.asm.MethodVisitor asmMethodVisitor) {
    this.asmMethodVisitor = asmMethodVisitor;
  }
  
  /**
   * Visit a frame.
   * <p>
   * This method will covert the given <code>local</code> and <code>stack</code>
   * objects in the following manner:
   * <ul>
   * <li>{@link Type} - to internal class name via {@link Type#getInternalName()}</li>.
   * <li>All other object types will be used unmodified.</li>
   * </ul>
   *
   * @param type The type.
   * @param nLocal The number of local objects.
   * @param local The local objects.
   * @param nStack The number of stack objects.
   * @param stack The stack objects.
   * @see org.objectweb.asm.MethodVisitor#visitFrame(int, int, Object[], int, Object[])
   */
  @Override
  public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
    this.asmMethodVisitor.visitFrame(type, nLocal, this.convertFameObjects(local), nStack, this.convertFameObjects(stack));
  }
  
  /**
   * Visit a local variable.
   * <p>
   * This is a convenience method that is equivalent to the following:
   * <pre>
   *   visitLocalVariable(name, type.getDescriptor(), signature, start, end, index)
   * </pre>
   *
   * @param name The name.
   * @param type The type.
   * @param signature The signature.
   * @param start The start label.
   * @param end The end label.
   * @param index The index.
   * @see org.objectweb.asm.MethodVisitor#visitLocalVariable(String, String, String, Label, Label, int)
   */
  public void visitLocalVariable(String name, Type type, String signature, Label start, Label end, int index) {
    this.asmMethodVisitor.visitLocalVariable(name, type.getDescriptor(), signature, start, end, index);
  }
  
  /**
   * Visit a method instruction.
   * <p>
   * This is a convenience method that is equivalent to the following:
   * <pre>
   *   visitMethodInsn(opcode, type.getInternalName(), method.getName(), method.getDescriptor())
   * </pre>
   *
   * @param opcode The opcode.
   * @param type The type.
   * @param method The method.
   * @see org.objectweb.asm.MethodVisitor#visitMethodInsn(int, String, String, String)
   */
  public void visitMethodInsn(int opcode, Type type, Method method) {
    this.asmMethodVisitor.visitMethodInsn(opcode, type.getInternalName(), method.getName(), method.getDescriptor());
  }

  /**
   * Visit a type instruction.
   * <p>
   * This is a convenience method that is equivalent to the following:
   * <pre>
   *   visitTypeInsn(opcode, type.getInternalName())
   * </pre>
   *
   * @param opcode The opcode.
   * @param type The type.
   * @see org.objectweb.asm.MethodVisitor#visitTypeInsn(int, String)
   */
  public void visitTypeInsn(int opcode, Type type) {
    this.asmMethodVisitor.visitTypeInsn(opcode, type.getInternalName());
  }
  
  /**
   * @see org.objectweb.asm.MethodVisitor#visitAnnotation(String, boolean)
   */
  @Override
  public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    return this.asmMethodVisitor.visitAnnotation(desc, visible);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitAnnotationDefault()
   */
  @Override
  public AnnotationVisitor visitAnnotationDefault() {
    return this.asmMethodVisitor.visitAnnotationDefault();
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitAttribute(Attribute)
   */
  @Override
  public void visitAttribute(Attribute attr) {
    this.asmMethodVisitor.visitAttribute(attr);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitCode()
   */
  @Override
  public void visitCode() {
    this.asmMethodVisitor.visitCode();
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitEnd()
   */
  @Override
  public void visitEnd() {
    this.asmMethodVisitor.visitEnd();
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitFieldInsn(int, String, String, String)
   */
  @Override
  public void visitFieldInsn(int opcode, String owner, String name, String desc) {
    this.asmMethodVisitor.visitFieldInsn(opcode, owner, name, desc);
  }
  
  /**
   * @see org.objectweb.asm.MethodVisitor#visitIincInsn(int, int)
   */
  @Override
  public void visitIincInsn(int var, int increment) {
    this.asmMethodVisitor.visitIincInsn(var, increment);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitInsn(int)
   */
  @Override
  public void visitInsn(int opcode) {
    this.asmMethodVisitor.visitInsn(opcode);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitIntInsn(int, int)
   */
  @Override
  public void visitIntInsn(int opcode, int operand) {
    this.asmMethodVisitor.visitIntInsn(opcode, operand);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitJumpInsn(int, Label)
   */
  @Override
  public void visitJumpInsn(int opcode, Label label) {
    this.asmMethodVisitor.visitJumpInsn(opcode, label);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitLabel(Label)
   */
  @Override
  public void visitLabel(Label label) {
    this.asmMethodVisitor.visitLabel(label);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitLdcInsn(Object)
   */
  @Override
  public void visitLdcInsn(Object cst) {
    this.asmMethodVisitor.visitLdcInsn(cst);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitLineNumber(int, Label)
   */
  @Override
  public void visitLineNumber(int line, Label start) {
    this.asmMethodVisitor.visitLineNumber(line, start);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitLocalVariable(String, String, String, Label, Label, int)
   */
  @Override
  public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
    this.asmMethodVisitor.visitLocalVariable(name, desc, signature, start, end, index);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitLookupSwitchInsn(Label, int[], Label[])
   */
  @Override
  public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
    this.asmMethodVisitor.visitLookupSwitchInsn(dflt, keys, labels);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitMaxs(int, int)
   */
  @Override
  public void visitMaxs(int maxStack, int maxLocals) {
    this.asmMethodVisitor.visitMaxs(maxStack, maxLocals);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitMethodInsn(int, String, String, String)
   */
  @Override
  public void visitMethodInsn(int opcode, String owner, String name, String desc) {
    this.asmMethodVisitor.visitMethodInsn(opcode, owner, name, desc);
  }
  
  /**
   * @see org.objectweb.asm.MethodVisitor#visitMultiANewArrayInsn(String, int)
   */
  @Override
  public void visitMultiANewArrayInsn(String desc, int dims) {
    this.asmMethodVisitor.visitMultiANewArrayInsn(desc, dims);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitParameterAnnotation(int, String, boolean)
   */
  @Override
  public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
    return this.asmMethodVisitor.visitParameterAnnotation(parameter, desc, visible);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitTableSwitchInsn(int, int, Label, Label[])
   */
  @Override
  public void visitTableSwitchInsn(int min, int max, Label dflt, Label[] labels) {
    this.asmMethodVisitor.visitTableSwitchInsn(min, max, dflt, labels);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitTryCatchBlock(Label, Label, Label, String)
   */
  @Override
  public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
    this.asmMethodVisitor.visitTryCatchBlock(start, end, handler, type);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitTypeInsn(int, String)
   */
  @Override
  public void visitTypeInsn(int opcode, String type) {
    this.asmMethodVisitor.visitTypeInsn(opcode, type);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitVarInsn(int, int)
   */
  @Override
  public void visitVarInsn(int opcode, int var) {
    this.asmMethodVisitor.visitVarInsn(opcode, var);
  }
  
  /**
   * Convert the values of the given array of objects into values suitable for 
   * {@link org.objectweb.asm.MethodVisitor#visitFrame(int, int, Object[], int, Object[])}
   *
   * @param objects The object.
   * @return The converted objects.
   */
  private Object[] convertFameObjects(Object[] objects) {
    if (objects != null) {
      Object[] newObjects = new Object[objects.length];
      
      for (int i = 0; i < objects.length; i++) {
        Object object = objects[i];
        
        if (object instanceof Type) {
          Type type = (Type)object;
          
          newObjects[i] = type.getInternalName();
        }
        else {
          newObjects[i] = object;
        }
      }
      
      return newObjects;
    }
    else {
      return null;
    }
  }
}
