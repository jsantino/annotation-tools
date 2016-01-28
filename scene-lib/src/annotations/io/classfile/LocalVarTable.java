package annotations.io.classfile;

import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.Label;

import annotations.el.LocalLocation;

public class LocalVarTable {
  private final Set<Entry> entries;

  LocalVarTable() {
    entries = new HashSet<Entry>();
  }

  public Entry get(LocalLocation loc) {
    return get(loc.scopeStart, loc.scopeStart + loc.scopeLength, loc.index);
  }

  public Entry get(int start, int end, int index) {
    for (Entry e : entries) {
      if (e.start.getOffset() == start
          && e.end.getOffset() == end
          && e.index == index) {
        return e;
      }
    }
    return null;
  }

  public Entry get(String name, String desc, String signature) {
    for (Entry e : entries) {
      if (name.equals(e.name) && desc.equals(e.desc)
          && signature.equals(e.signature)) {
        return e;
      }
    }
    return null;
  }

  public void put(String name, String desc, String signature,
      Label start, Label end, int index) {
    entries.add(new Entry(name, desc, signature, start, end, index));
  }

  static class Entry {
    final Label start;
    final Label end;
    final int index;
    final String name;
    final String desc;
    final String signature;

    Entry(String name, String desc, String signature,
        Label start, Label end, int index) {
      this.start = start;
      this.end = end;
      this.index = index;
      this.name = name;
      this.desc = desc;
      this.signature = signature;
    }

    public boolean equals(Entry e) {
      return e != null && index == e.index
          && start.equals(e.start) && end.equals(e.end)
          && name.equals(e.name) && desc.equals(e.desc)
          && signature.equals(e.signature);
    }

    @Override
    public boolean equals(Object obj) {
      return obj instanceof Entry && equals((Entry) obj);
    }

    @Override
    public int hashCode() {
      return index & start.hashCode() & end.hashCode()
          & name.hashCode() & desc.hashCode() & signature.hashCode();
    }
  }

  public Iterable<Entry> getEntries() {
    return entries;
  }
}
