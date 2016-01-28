package annotations.io.classfile;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.AnnotationVisitor;

import com.sun.tools.javac.code.TypeAnnotationPosition.TypePathEntry;

public class XArguments extends XAnnotationVisitor {
  // all of the following information
  // may or may not be present, so use a list to store
  // information as it is received from visitX* methods, and
  // correctly interpret the information in visitEnd()
  // note that all of these should contain 0 or 1 elements,
  // except for xLocationsArgs, which is actually a list
  final List<Integer> xTargetTypeArgs = new ArrayList<Integer>(1);
  final List<Integer> xIndexArgs = new ArrayList<Integer>(1);
  final List<Integer> xLengthArgs = new ArrayList<Integer>(1);
  final List<TypePathEntry> xLocationsArgs = new ArrayList<TypePathEntry>();
  final List<Integer> xLocationLengthArgs = new ArrayList<Integer>(1);
  final List<Integer> xOffsetArgs = new ArrayList<Integer>(1);
  final List<Integer> xStartPcArgs = new ArrayList<Integer>(1);
  final List<Integer> xParamIndexArgs = new ArrayList<Integer>(1);
  final List<Integer> xBoundIndexArgs = new ArrayList<Integer>(1);
  final List<Integer> xExceptionIndexArgs = new ArrayList<Integer>(1);
  final List<Integer> xTypeIndexArgs = new ArrayList<Integer>(1);

  public XArguments(int api) {
    super(api);
  }

  public XArguments(int api, AnnotationVisitor av) {
    super(api, av);
  }

  /**
   * Visits the target type of the extended annotation, which defines the
   * type and structure of the reference info of the extended annotation.
   * 
   * @param target_type the target type of the extended annotation
   */
  public void visitXTargetType(int target_type) {
    xTargetTypeArgs.add(target_type);
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
    xOffsetArgs.add(offset);
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
    xLocationLengthArgs.add(location_length);
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
  public void visitXLocation(TypePathEntry location) {
    xLocationsArgs.add(location);
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
    xStartPcArgs.add(start_pc);
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
    xLengthArgs.add(length);
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
    xIndexArgs.add(index);
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
    xParamIndexArgs.add(param_index);
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
    xBoundIndexArgs.add(bound_index);
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
    xTypeIndexArgs.add(type_index);
    if (av instanceof XAnnotationVisitor) {
      ((XAnnotationVisitor) av).visitXTypeIndex(type_index);
    }
  }

  /**
   * Visits the exception_index specified by the extended annotation.
   * @param exception_index
   */
  public void visitXExceptionIndex(int exception_index) {
    xExceptionIndexArgs.add(exception_index);
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
}
