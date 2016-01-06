package annotations.io.classfile;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.TypePath;

/**
 * {@link MethodVisitor} with type annotation visitor overrides that
 *  call ASMx legacy methods.
 *
 * @author dbro
 */
public class XMethodVisitor extends MethodVisitor {
  public XMethodVisitor(int api) {
    super(api);
  }

  public XMethodVisitor(int api, MethodVisitor mv) {
    super(api, mv);
  }

  @Override
  public AnnotationVisitor visitTypeAnnotation(int typeRef,
      TypePath typePath, String desc, final boolean visible) {
    return new XAnnotationVisitor(api).accept(typeRef,
        typePath, desc, visible, mv);
  }

  @Override
  public AnnotationVisitor visitInsnAnnotation(int typeRef,
      TypePath typePath, String desc, boolean visible) {
    return new XAnnotationVisitor(api).accept(typeRef,
        typePath, desc, visible, mv);
  }
}
