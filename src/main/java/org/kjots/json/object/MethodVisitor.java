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
   * @see #visitMethodInsn(int, String, String, String)
   */
  public void visitMethodInsn(int opcode, Type type, Method method) {
    this.asmMethodVisitor.visitMethodInsn(opcode, type.getInternalName(), method.getName(), method.getDescriptor());
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitAnnotation(String, boolean)
   */
  public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    return this.asmMethodVisitor.visitAnnotation(desc, visible);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitAnnotationDefault()
   */
  public AnnotationVisitor visitAnnotationDefault() {
    return this.asmMethodVisitor.visitAnnotationDefault();
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitAttribute(Attribute)
   */
  public void visitAttribute(Attribute attr) {
    this.asmMethodVisitor.visitAttribute(attr);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitCode()
   */
  public void visitCode() {
    this.asmMethodVisitor.visitCode();
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitEnd()
   */
  public void visitEnd() {
    this.asmMethodVisitor.visitEnd();
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitFieldInsn(int, String, String, String)
   */
  public void visitFieldInsn(int opcode, String owner, String name, String desc) {
    this.asmMethodVisitor.visitFieldInsn(opcode, owner, name, desc);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitFrame(int, int, Object[], int, Object[])
   */
  public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
    this.asmMethodVisitor.visitFrame(type, nLocal, local, nStack, stack);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitIincInsn(int, int)
   */
  public void visitIincInsn(int var, int increment) {
    this.asmMethodVisitor.visitIincInsn(var, increment);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitInsn(int)
   */
  public void visitInsn(int opcode) {
    this.asmMethodVisitor.visitInsn(opcode);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitIntInsn(int, int)
   */
  public void visitIntInsn(int opcode, int operand) {
    this.asmMethodVisitor.visitIntInsn(opcode, operand);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitJumpInsn(int, Label)
   */
  public void visitJumpInsn(int opcode, Label label) {
    this.asmMethodVisitor.visitJumpInsn(opcode, label);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitLabel(Label)
   */
  public void visitLabel(Label label) {
    this.asmMethodVisitor.visitLabel(label);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitLdcInsn(Object)
   */
  public void visitLdcInsn(Object cst) {
    this.asmMethodVisitor.visitLdcInsn(cst);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitLineNumber(int, Label)
   */
  public void visitLineNumber(int line, Label start) {
    this.asmMethodVisitor.visitLineNumber(line, start);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitLocalVariable(String, String, String, Label, Label, int)
   */
  public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
    this.asmMethodVisitor.visitLocalVariable(name, desc, signature, start, end, index);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitLookupSwitchInsn(Label, int[], Label[])
   */
  public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
    this.asmMethodVisitor.visitLookupSwitchInsn(dflt, keys, labels);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitMaxs(int, int)
   */
  public void visitMaxs(int maxStack, int maxLocals) {
    this.asmMethodVisitor.visitMaxs(maxStack, maxLocals);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitMethodInsn(int, String, String, String)
   */
  public void visitMethodInsn(int opcode, String owner, String name, String desc) {
    this.asmMethodVisitor.visitMethodInsn(opcode, owner, name, desc);
  }
  
  /**
   * @see org.objectweb.asm.MethodVisitor#visitMultiANewArrayInsn(String, int)
   */
  public void visitMultiANewArrayInsn(String desc, int dims) {
    this.asmMethodVisitor.visitMultiANewArrayInsn(desc, dims);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitParameterAnnotation(int, String, boolean)
   */
  public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
    return this.asmMethodVisitor.visitParameterAnnotation(parameter, desc, visible);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitTableSwitchInsn(int, int, Label, Label[])
   */
  public void visitTableSwitchInsn(int min, int max, Label dflt, Label[] labels) {
    this.asmMethodVisitor.visitTableSwitchInsn(min, max, dflt, labels);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitTryCatchBlock(Label, Label, Label, String)
   */
  public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
    this.asmMethodVisitor.visitTryCatchBlock(start, end, handler, type);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitTypeInsn(int, String)
   */
  public void visitTypeInsn(int opcode, String type) {
    this.asmMethodVisitor.visitTypeInsn(opcode, type);
  }

  /**
   * @see org.objectweb.asm.MethodVisitor#visitVarInsn(int, int)
   */
  public void visitVarInsn(int opcode, int var) {
    this.asmMethodVisitor.visitVarInsn(opcode, var);
  }
}
