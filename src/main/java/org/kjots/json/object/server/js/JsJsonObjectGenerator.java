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
package org.kjots.json.object.server.js;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.RETURN;

import org.kjots.json.object.server.JsonObjectGeneratorBase;
import org.kjots.json.object.server.js.impl.JsJsonObjectImpl;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

/**
 * JavaScript JSON Object Generator.
 * <p>
 * Created: 14th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonObjectGenerator extends JsonObjectGeneratorBase<JsJsonObjectImpl> {
  /**
   * Construct a new JavaScript JSON Object Generator.
   */
  public JsJsonObjectGenerator() {
    super(JsJsonObjectImpl.class);
  }
  
  /**
   * Generate the constructor.
   *
   * @param classWriter The class writer.
   * @param jsonObjectImplIClassName The internal class name of the JSON object implementation.
   * @param superJsonObjectImplIClassName The internal class name of the super JSON object implementation.
   */
  @Override
  protected void generateConstructor(ClassWriter classWriter, String jsonObjectImplIClassName, String superJsonObjectImplIClassName) {
    MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "(Ljavax/script/Invocable;Ljava/lang/Object;)V", null, null);
    
    Label start = new Label();
    Label end = new Label();
    
    methodVisitor.visitCode();
    
    methodVisitor.visitLabel(start);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor.visitVarInsn(ALOAD, 1);
    methodVisitor.visitVarInsn(ALOAD, 2);
    methodVisitor.visitMethodInsn(INVOKESPECIAL, superJsonObjectImplIClassName, "<init>", "(Ljavax/script/Invocable;Ljava/lang/Object;)V");
    methodVisitor.visitInsn(RETURN);
    methodVisitor.visitLabel(end);
    
    methodVisitor.visitLocalVariable("this", "L" + jsonObjectImplIClassName + ";", null, start, end, 0);
    methodVisitor.visitLocalVariable("jsEngine", "Ljavax/script/Invocable;", null, start, end, 1);
    methodVisitor.visitLocalVariable("jsObject", "Ljava/lang/Object;", null, start, end, 2);
    methodVisitor.visitMaxs(3, 3);
    
    methodVisitor.visitEnd();
  }
}
