package annotations.io.classfile;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.TypePath;
import org.objectweb.asm.TypeReference;

import com.sun.tools.javac.code.TypeAnnotationPosition;

/** 
 * A visitor to visit a Java extended annotation.  The methods of this 
 * interface must be called in the following order:
 * (<tt>visit<tt> | 
 *  <tt>visitEnum<tt> | 
 *  <tt>visitAnnotation<tt> | 
 *  <tt>visitArray<tt>)* 
 * 
 * <tt>visitXTargetType<tt>
 * 
 * (<i>visit the fields (if any) of the <tt>reference_info</tt> structure
 *  for the given target type in the correct order using <tt>visitXOffset</tt>,
 *  <tt>visitXStartPc</tt>, <tt>visitXLength</tt>, <tt>visitXIndex</tt>,
 *  <tt>visitXParamIndex</tt>, <tt>visitXBoundIndex</tt>,
 *  <tt>visitXLocationLength</tt>, and/or <tt>visitXLocation</tt></i>)
 *  
 * <tt>visitEnd<tt>
 * 
 * @author jaimeq
 */
public class XAnnotationVisitor extends AnnotationVisitor {
  public XAnnotationVisitor(int api) {
    this(api, null);
  }

  public XAnnotationVisitor(int api, AnnotationVisitor v) {
    super(api, v);
  }

  /**
   * Visits the target type of the extended annotation, which defines the
   * type and structure of the reference info of the extended annotation.
   * 
   * @param target_type the target type of the extended annotation
   */
  public void visitXTargetType(int target_type) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXTargetType(target_type);
    }
  }

  /**
   * Visits the offset specified by the extended annotation, whose meaning
   * depends on the extended annotation's target type.
   * 
   * @param offset the offset specified by the extended annotation
   */
  public void visitXOffset(int offset) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXOffset(offset);
    }
  }

  /**
   * Visits the location_length specified by the extended annotation, whose 
   * meaning depends on the extended annotation's target type.
   * 
   * @param location_length the location_length specified by the extended 
   *  annotation
   */
  public void visitXLocationLength(int location_length) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXLocationLength(location_length);
    }
  }

  /**
   * Visits the location specified by the extended annotation, whose meaning 
   * depends on the extended annotation's target type.
   * 
   * @param location the location specified by the extended annotation
   */
  public void visitXLocation(TypeAnnotationPosition.TypePathEntry location) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXLocation(location);
    }
  }

  public void visitXNumEntries(int numEntries) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXNumEntries(numEntries);
    }
  }

  /**
   * Visits the start_pc specified by the extended annotation, whose meaning
   * depends on the extended annotation's target type.
   * 
   * @param start_pc the start_pc specified by the extended annotation
   */
  public void visitXStartPc(int start_pc) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXStartPc(start_pc);
    }
  }

  /**
   * Visits the length specified by the extended annotation, whose meaning
   * depends on the extended annotation's target type.
   * 
   * @param length the length specified by the extended annotation
   */
  public void visitXLength(int length) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXLength(length);
    }
  }

  /**
   * Visits the index specified by the extended annotation, whose meaning 
   * depends on the extended annotation's target type.
   * 
   * @param index the index specified by the extended annotation
   */
  public void visitXIndex(int index) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXIndex(index);
    }
  }

  /**
   * Visits the param_index specified by the extended annotation, whose meaning
   * depends on the extended annotation's target type.
   * 
   * @param param_index the param_index specified by the extended annotation
   */
  public void visitXParamIndex(int param_index) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXParamIndex(param_index);
    }
  }

  /**
   * Visits the bound_index specified by the extended annotation, whose meaning
   * depends on the extended annotation's target type.
   * 
   * @param bound_index the bound_index specified by the extended annotation
   */
  public void visitXBoundIndex(int bound_index) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXBoundIndex(bound_index);
    }
  }

  /**
   * Visits the type_index specified by the extended annotation, whose meaning
   * depends on the extended annotation's target type.
   * @param type_index
   */
  public void visitXTypeIndex(int type_index) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXTypeIndex(type_index);
    }
  }

  /**
   * Visits the exception_index specified by the extended annotation.
   * @param exception_index
   */
  public void visitXExceptionIndex(int exception_index) {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXExceptionIndex(exception_index);
    }
  }

  /**
   * Visits the annotation name and arguments size.
   */
  public void visitXNameAndArgsSize() {
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXNameAndArgsSize();
    }
  }

  void visitTypePath(TypePath typePath) {
    int n = typePath.getLength();
    List<Integer> l = new ArrayList<Integer>(n);
  
    for (int i = 0; i < n; i++) {
      int step = typePath.getStep(i);
      l.add(step);
      l.add(step != TypePath.TYPE_ARGUMENT ? 0
          : typePath.getStepArgument(i));
    }
  
    visitXLocationLength(n);
    for (TypeAnnotationPosition.TypePathEntry e :
        TypeAnnotationPosition.getTypePathFromBinary(l)) {
      visitXLocation(e);
    }
  }

//  public AnnotationVisitor accept(int typeRef, TypePath typePath,
//      String desc, boolean visible, ClassVisitor v) {
//    accept(typeRef, typePath, desc, visible);
//    return v == null ? null
//        : v.visitTypeAnnotation(typeRef, typePath, desc, visible);
//  }
//
//  public AnnotationVisitor accept(int typeRef, TypePath typePath,
//      String desc, boolean visible, MethodVisitor v) {
//    //AnnotationVisitor av =
//    accept(typeRef, typePath, desc, visible);
//    return v == null ? null
//        : v.visitTypeAnnotation(typeRef, typePath, desc, visible);
//  }
//
//  public AnnotationVisitor accept(int typeRef, TypePath typePath,
//      String desc, boolean visible, FieldVisitor v) {
//    accept(typeRef, typePath, desc, visible);
//    return v.visitTypeAnnotation(typeRef, typePath, desc, visible);
//  }
//
//  void accept(int typeRef, TypePath typePath,
//      String desc, boolean visible) {
//    accept(typeRef, typePath, desc, visible, -1);
//  }
//
//  void accept(int typeRef, TypePath typePath,
//      String desc, boolean visible, int offset) {
//    TypeReference typeReference = new TypeReference(typeRef);
//    int targetType = typeReference.getSort();
//    visitXTargetType(targetType);
//
//    if (typePath != null) {
//      visitTypePath(typePath);
//    }
//
//    if (offset >= 0) {
//      visitXOffset(offset);
//    }
//
//    switch (targetType) {
//    case TypeReference.INSTANCEOF:
//    case TypeReference.NEW:
//    case TypeReference.CONSTRUCTOR_REFERENCE:
//    case TypeReference.METHOD_REFERENCE:
//      break;
//
//    case TypeReference.LOCAL_VARIABLE:
//    case TypeReference.RESOURCE_VARIABLE:
//      break;
//
//    case TypeReference.METHOD_RETURN:
//      break;
//
//    case TypeReference.METHOD_FORMAL_PARAMETER:
//      visitXParamIndex(typeReference.getFormalParameterIndex());
//      break;
//
//    case TypeReference.FIELD:
//      break;
//
//    case TypeReference.CLASS_TYPE_PARAMETER_BOUND:
//    case TypeReference.METHOD_TYPE_PARAMETER_BOUND:
//      visitXParamIndex(typeReference.getTypeParameterIndex());
//      visitXBoundIndex(typeReference.getTypeParameterBoundIndex());
//      break;
//
//    case TypeReference.CLASS_EXTENDS:
//      visitXTypeIndex(typeReference.getSuperTypeIndex());
//      break;
//
//    case TypeReference.THROWS:
//      visitXTypeIndex(typeReference.getExceptionIndex());
//      break;
//
//    case TypeReference.EXCEPTION_PARAMETER:
//      visitXExceptionIndex(typeReference.getTryCatchBlockIndex());
//      break;
//
//    case TypeReference.CAST:
//    case TypeReference.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
//    case TypeReference.METHOD_INVOCATION_TYPE_ARGUMENT:
//    case TypeReference.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
//    case TypeReference.METHOD_REFERENCE_TYPE_ARGUMENT:
//      visitXTypeIndex(typeReference.getTypeArgumentIndex());
//      break;
//
//    case TypeReference.CLASS_TYPE_PARAMETER:
//    case TypeReference.METHOD_TYPE_PARAMETER:
//      visitXParamIndex(typeReference.getTypeParameterIndex());
//      break;
//
//    default: throw new IllegalArgumentException(
//        "Unrecognized target type: " + targetType);
//    }
//  }
//
//  public AnnotationVisitor accept(int typeRef, TypePath typePath,
//      Label[] start, Label[] end, int[] index,
//      String desc, boolean visible, MethodVisitor mv) {
//    assert index.length == 1;
//    this.start = start[0];
//    this.end = end[0];
//    this.index = index[0];
//    return accept(typeRef, typePath, desc, visible, mv);
//  }
}
