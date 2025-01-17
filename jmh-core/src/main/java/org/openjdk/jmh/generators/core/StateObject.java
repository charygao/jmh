
package org.openjdk.jmh.generators.core;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.util.HashMultimap;
import org.openjdk.jmh.util.Multimap;
import org.openjdk.jmh.util.TreeMultimap;

import java.util.*;

class StateObject {

    public static final Comparator<StateObject> ID_COMPARATOR = Comparator.comparing(o -> o.fieldIdentifier);

    public final String packageName;
    public final String userType;
    public final String type;
    public final Scope scope;
    public final String localIdentifier;
    public final String fieldIdentifier;
    public final Multimap<String, FieldInfo> params;
    public final SortedSet<HelperMethodInvocation> helpers;
    public final Multimap<String, String> helperArgs;
    public final List<StateObject> depends;

    public StateObject(Identifiers identifiers, ClassInfo info, Scope scope) {
        this.packageName = info.getPackageName() + "." + BenchmarkGenerator.JMH_GENERATED_SUBPACKAGE;
        this.userType = info.getQualifiedName();
        this.type = identifiers.getJMHtype(info);
        this.scope = scope;

        String id = identifiers.collapseTypeName(userType) + identifiers.identifier(scope);
        this.localIdentifier = "l_" + id;
        this.fieldIdentifier = "f_" + id;

        this.params = new TreeMultimap<>();
        this.helpers = new TreeSet<>();
        this.helperArgs = new HashMultimap<>();
        this.depends = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateObject that = (StateObject) o;

        if (!fieldIdentifier.equals(that.fieldIdentifier))
            return false;
        if (scope != that.scope) return false;
        if (!Objects.equals(type, that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + (fieldIdentifier.hashCode());
        return result;
    }

    public String toTypeDef() {
        return type + " " + localIdentifier;
    }

    public String toLocal() {
        return localIdentifier;
    }

    public Collection<String> getParamsLabels() {
        return params.keys();
    }

    public void addParam(FieldInfo fieldInfo) {
        params.put(fieldInfo.getName(), fieldInfo);
    }

    public Collection<FieldInfo> getParam(String name) {
        return params.get(name);
    }

    public String getParamAccessor(FieldInfo paramField) {
        String name = paramField.getName();
        String type = paramField.getType().getQualifiedName();

        if (type.equalsIgnoreCase("java.lang.String")) {
            return "control.getParam(\"" + name + "\")";
        }
        if (type.equalsIgnoreCase("boolean") || type.equalsIgnoreCase("java.lang.Boolean")) {
            return "Boolean.valueOf(control.getParam(\"" + name + "\"))";
        }
        if (type.equalsIgnoreCase("byte") || type.equalsIgnoreCase("java.lang.Byte")) {
            return "Byte.valueOf(control.getParam(\"" + name + "\"))";
        }
        if (type.equalsIgnoreCase("char") || type.equalsIgnoreCase("java.lang.Character")) {
            return "(control.getParam(\"" + name + "\")).charAt(0)";
        }
        if (type.equalsIgnoreCase("short") || type.equalsIgnoreCase("java.lang.Short")) {
            return "Short.valueOf(control.getParam(\"" + name + "\"))";
        }
        if (type.equalsIgnoreCase("int") || type.equalsIgnoreCase("java.lang.Integer")) {
            return "Integer.valueOf(control.getParam(\"" + name + "\"))";
        }
        if (type.equalsIgnoreCase("float") || type.equalsIgnoreCase("java.lang.Float")) {
            return "Float.valueOf(control.getParam(\"" + name + "\"))";
        }
        if (type.equalsIgnoreCase("long") || type.equalsIgnoreCase("java.lang.Long")) {
            return "Long.valueOf(control.getParam(\"" + name + "\"))";
        }
        if (type.equalsIgnoreCase("double") || type.equalsIgnoreCase("java.lang.Double")) {
            return "Double.valueOf(control.getParam(\"" + name + "\"))";
        }

        // assume enum
        return type + ".valueOf(control.getParam(\"" + name + "\"))";
    }

    public void addHelper(HelperMethodInvocation hmi) {
        helpers.add(hmi);
    }

    public Collection<HelperMethodInvocation> getHelpers() {
        return helpers;
    }
}
