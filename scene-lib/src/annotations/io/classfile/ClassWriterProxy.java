package annotations.io.classfile;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

/**
 * @author dbro
 *
 */
public class ClassWriterProxy extends ClassVisitor {
  protected final ClassReader cr;
  protected final ClassWriter cw;

  public ClassWriterProxy(final int flags) {
    this(null, flags);
  }

  public ClassWriterProxy(final ClassReader classReader, final int flags) {
    this(classReader, flags, null);
  }

  public ClassWriterProxy(final ClassReader classReader, final int flags,
      ClassVisitor v) {
    super(Opcodes.ASM5, v);
    cr = classReader;
    cw = cr == null ? new ClassWriter(flags)
        : new ClassWriter(classReader, flags);
  }

  @Override
  public void visit(final int version, final int access,
      final String name, final String signature, final String superName,
      final String[] interfaces) {
    cw.visit(version, access, name, signature, superName, interfaces);
  }

  @Override
  public void visitSource(final String file, final String debug) {
    cw.visitSource(file, debug);
  }

  @Override
  public void visitOuterClass(final String owner, final String name,
      final String desc) {
    cw.visitOuterClass(owner, name, desc);
  }

  @Override
  public AnnotationVisitor visitAnnotation(final String desc,
      final boolean visible) {
    return cw.visitAnnotation(desc, visible);
  }

  @Override
  public AnnotationVisitor visitTypeAnnotation(int typeRef,
      TypePath typePath, final String desc, final boolean visible) {
    return cw.visitTypeAnnotation(typeRef, typePath, desc, visible);
  }

  @Override
  public void visitAttribute(final Attribute attr) {
    cw.visitAttribute(attr);
  }

  @Override
  public void visitInnerClass(final String name,
      final String outerName, final String innerName, final int access) {
    cw.visitInnerClass(name, outerName, innerName, access);
  }

  @Override
  public FieldVisitor visitField(final int access, final String name,
      final String desc, final String signature, final Object value) {
    return cw.visitField(access, name, desc, signature, value);
  }

  @Override
  public MethodVisitor visitMethod(final int access,
      final String name, final String desc,
      final String signature, final String[] exceptions) {
    return new MethodWriterProxy(api, cw.visitMethod(access,
        name, desc, signature, exceptions));
  }

  @Override
  public void visitEnd() {
    cw.visitEnd();
  }

  /**
   * @see org.objectweb.asm.ClassWriter#toByteArray()
   */
  public byte[] toByteArray() {
    return cw.toByteArray();
  }

  /**
   * @see org.objectweb.asm.ClassWriter#newConst(Object)
   */
  public int newConst(final Object cst) {
    return cw.newConst(cst);
  }

  /**
   * @see org.objectweb.asm.ClassWriter#newUTF8(String)
   */
  public int newUTF8(final String value) {
    return cw.newUTF8(value);
  }

  /**
   * @see org.objectweb.asm.ClassWriter#newClass(String)
   */
  public int newClass(final String value) {
    return cw.newClass(value);
  }

  /**
   * @see org.objectweb.asm.ClassWriter#newMethodType(String)
   */
  public int newMethodType(final String methodDesc) {
    return cw.newMethodType(methodDesc);
  }

  /**
   * @see org.objectweb.asm.ClassWriter#newHandle(int, String, String, String)
   */
  public int newHandle(final int tag, final String owner, final String name,
      final String desc) {
    return cw.newHandle(tag, owner, name, desc);
  }

  /**
   * @see org.objectweb.asm.ClassWriter#newInvokeDynamic(String, String, Handle, Object...)
   */
  public int newInvokeDynamic(final String name, final String desc,
      final Handle bsm, final Object... bsmArgs) {
    return cw.newInvokeDynamic(name, desc, bsm, bsmArgs);
  }

  /**
   * @see org.objectweb.asm.ClassWriter#newField(String, String, String)
   */
  public int newField(final String owner, final String name, final String desc) {
    return cw.newField(owner, name, desc);
  }

  /**
   * @see org.objectweb.asm.ClassWriter#newMethod(String, String, String, boolean)
   */
  public int newMethod(final String owner, final String name,
      final String desc, final boolean itf) {
    return cw.newMethod(owner, name, desc, itf);
  }

  class MethodWriterProxy extends XMethodVisitor {
    public MethodWriterProxy(int api, MethodVisitor v) {
      // v must be a MethodWriter, but type not visible
      super(api, v);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
      return new AnnotationWriterProxy(api,
          super.visitAnnotation(desc, visible));
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef,
        TypePath typePath, String desc, boolean visible) {
      return new AnnotationWriterProxy(api,
          super.visitTypeAnnotation(typeRef, typePath, desc, visible));
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter,
        String desc, boolean visible) {
      return new AnnotationWriterProxy(api,
          super.visitParameterAnnotation(parameter, desc, visible));
    }

//    @Override
//    public void visitCode() {
//      mv.visitCode();
//    }
//
//    @Override
//    public void visitLabel(Label label) {
//      super.visitLabel(label);
//    }
//
//    @Override
//    public void visitLocalVariable(String name, String desc,
//        String signature, Label start, Label end, int index) {
//      mv.visitLocalVariable(name, desc, signature, start, end, index);
//    }

    @Override
    public AnnotationVisitor visitLocalVariableAnnotation(int typeRef,
        TypePath typePath, Label[] start, Label[] end, int[] index,
        String desc, boolean visible) {
      return new AnnotationWriterProxy(api,
          super.visitLocalVariableAnnotation(typeRef, typePath,
              start, end, index, desc, visible));
    }

//    @Override
//    public void visitMaxs(int maxStack, int maxLocals) {
//      mv.visitMaxs(maxStack, maxLocals);
//    }
//
//    @Override
//    public void visitEnd() {
//      mv.visitEnd();
//    }
  }

  class AnnotationWriterProxy extends AnnotationVisitor {
    // v must be an AnnotationWriter, but type not visible
    public AnnotationWriterProxy(int api, AnnotationVisitor v) {
      super(api, v);
    }

    @Override
    public void visit(final String name, final Object value) {
      super.visit(name, value);
    }

    @Override
    public void visitEnum(final String name, final String desc,
        final String value) {
      super.visitEnum(name, desc, value);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String name,
        final String desc) {
      return super.visitAnnotation(name, desc);
    }

    @Override
    public AnnotationVisitor visitArray(final String name) {
      return super.visitArray(name);
    }

    @Override
    public void visitEnd() {
      super.visitEnd();
    }
  }
}
