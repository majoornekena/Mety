package extract.zframework.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import extract.zframework.annotation.HForceForm;
import extract.zframework.annotation.HForm;
import extract.zframework.annotation.HListe;
import extract.zframework.annotation.NotForm;
import extract.zframework.annotation.relation.Attribut;
import extract.zframework.annotation.relation.Id;
import extract.zframework.annotation.relation.MappedBy;
import extract.zframework.controlExcept.NoFieldMapped;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.jmenu.JHMenuItem;
import extract.zframework.hdata.CId;

public class OwnTool {
    public static HType[] htypes = { new HVarchar(), new HDate(), new HInt(),
            new HNumeric(), new HTimestamp() };

    public static Calendar toCalendar(Date date) {
        Calendar cStart = Calendar.getInstance();
        date.setTime(date.getTime());
        return cStart;
    }

    public static LocalDate toLocalDate(Date date) {
        LocalDate ans = date.toLocalDate();
        return ans;
    }

    public static JHMenuItem[] loadAllItem(String packageName) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Set<Class<?>> liste = OwnTool.findAllClassesUsingClassLoader(packageName);
        JHMenuItem[] ans = new JHMenuItem[liste.size()];
        int i = 0;
        for (Class<?> l : liste) {
            ans[i] = (JHMenuItem) l.getConstructor().newInstance();
            i++;
        }
        return ans;
    }

    public static CId getOnlyId(Field field, Object object) throws IllegalArgumentException, IllegalAccessException {
        field.setAccessible(true);
        Attribut attribut = field.getAnnotation(Attribut.class);
        String ans = OwnTool.getNameId(field, attribut);
        CId cid = new CId(ans, String.valueOf(field.get(object)), attribut.varchar());
        return cid;
    }

    public static Field getFieldMap(Field[] field, Class<?> map) throws NoFieldMapped {
        for (int i = 0; i < field.length; i++) {
            MappedBy mapper = field[i].getAnnotation(MappedBy.class);
            if (mapper.cls() == map) {
                return field[i];
            }
        }
        throw new NoFieldMapped("No field mapped to MappedBy annotation");
    }

    public static <T extends Annotation> Field[] getAnnotations(Class<?> cls, Class<T> annotation) {
        ArrayList<Field> fields = OwnTool.getAllFields(cls);
        ArrayList<Field> liste = new ArrayList<Field>();
        for (int i = 0; i < fields.size(); i++) {
            fields.get(i).setAccessible(true);
            T annot = fields.get(i).getAnnotation(annotation);
            if (annot != null) {
                liste.add(fields.get(i));
            }
        }
        return OwnTool.toFields(liste);
    }

    public static HForceForm isHForceForm(Method method) throws NotForm {
        HForceForm ans = method.getAnnotation(HForceForm.class);
        if (ans == null) {
            throw new NotForm("Method not list");
        }
        return ans;
    }

    public static HListe isHListe(Method method) throws NotForm {
        HListe hliste = method.getAnnotation(HListe.class);
        if (hliste == null) {
            throw new NotForm("Method not list");
        }
        return hliste;
    }

    public static HForm isHForm(Method method) throws NotForm {
        HForm hform = method.getAnnotation(HForm.class);
        if (hform == null) {
            String message = "Method %s not form";
            throw new NotForm(String.format(message, method.getName()));
        }
        return hform;
    }

    public static void test(Attribut attr) throws Exception {
        if (attr == null) {
            throw new Exception("Not colomn");
        }
    }

    public static Attribut isAttribut(Method field) throws Exception {
        Attribut attribut = field.getAnnotation(Attribut.class);
        OwnTool.test(attribut);
        return attribut;
    }

    public static void isAttribut(Field field) throws Exception {
        Attribut attribut = field.getAnnotation(Attribut.class);
        OwnTool.test(attribut);
    }

    public static String getSeq(Field field) {
        Id id = field.getAnnotation(Id.class);
        String seq = id.sequence();
        return seq;
    }

    public static ArrayList<Method> getAllMethods(Class<?> current) {
        ArrayList<Method> liste = new ArrayList<Method>();
        while (current.getSuperclass() != null) {
            liste.addAll(Arrays.asList(current.getDeclaredMethods()));
            current = current.getSuperclass();
        }
        return liste;
    }

    public static ArrayList<Field> getAllFields(Class<?> current) {
        ArrayList<Field> liste = new ArrayList<Field>();
        while (current.getSuperclass() != null) {
            liste.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        return liste;
    }

    public static String extractValue(ObjectBdd<?> g, Field field)
            throws IllegalArgumentException, IllegalAccessException {
        field.setAccessible(true);
        return g == null ? "" : String.valueOf(field.get(g));
    }

    public static Attribut loadAttribut(Field field, Attribut attribut) {
        return attribut == null ? field.getAnnotation(Attribut.class) : attribut;
    }

    public static String getNameId(Field field, Attribut attribut) {
        field.setAccessible(true);
        attribut = OwnTool.loadAttribut(field, attribut);
        String ans = attribut.value();
        if (ans.equals("")) {
            ans = field.getName();
        }
        return ans;
    }

    public static Field getFieldId(Field[] ans) throws Exception {
        for (int i = 0; i < ans.length; i++) {
            Id id = ans[i].getAnnotation(Id.class);
            if (id != null) {
                return ans[i];
            }
        }
        return null;
    }

    public static Method[] toMeths(ArrayList<Method> liste) {
        Method[] ans = new Method[liste.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (Method) liste.get(i);
        }
        return ans;
    }

    public static Field[] toFields(ArrayList<Field> liste) {
        Field[] ans = new Field[liste.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (Field) liste.get(i);
        }
        return ans;
    }

    public static void list(Set<Class<?>> ans, File root) {
        try {
            ans.addAll(OwnTool.findAllClassesUsingClassLoader(root.getName()));
        } catch (Exception e) {

        }
        if (root.isDirectory()) {
            for (File file : root.listFiles()) {
                list(ans, file);
            }
        }
    }

    public static InputStream loadInput(String packageName) {
        return ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
    }

    public static Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
        // InputStream stream = OwnTool.loadInput(packageName);
        // BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        // return (Set<Class<?>>) reader.lines().filter(line -> line.endsWith(".class"))
        //         .map(line -> toClass(line, packageName))
        //         .collect(Collectors.toSet());
        return null;
    }

    private static Class<?> toClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    public static boolean match(String string, String regex) {
        return Pattern.matches(regex, string);
    }

    public static boolean isMatch(String string, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static Object cast(String value, String type) throws Exception {
        for (int i = 0; i < htypes.length; i++) {
            if (htypes[i].isMe(type)) {
                return OwnTool.htypes[i].cast(value);
            }
        }
        throw new Exception();
    }

    public static String[] getType(ResultSet res, int len) throws SQLException {
        String[] ans = new String[len];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.getMetaData().getColumnTypeName(i + 1);
        }
        return ans;
    }

    public static boolean in(String str, String[] liste) {
        for (int i = 0; i < liste.length; i++) {
            if (str.compareTo(liste[i]) == 0) {
                return true;
            }
        }
        return false;
    }

    public static String[] concat(ArrayList<String[]> liste, String splt) {
        if (liste.size() == 0) {
            return new String[0];
        }
        String[] answer = new String[liste.get(0).length];
        for (int i = 0; i < liste.size(); i++) {
            for (int j = 0; j < liste.get(i).length; j++) {
                if (i == 0) {
                    answer[j] = "";
                }
                answer[j] += ((i != 0) ? splt : "") + liste.get(i)[j];
            }
        }
        return answer;
    }
}
