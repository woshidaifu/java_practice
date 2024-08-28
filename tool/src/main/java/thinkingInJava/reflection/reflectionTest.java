package thinkingInJava.reflection;
import java.lang.reflect.Field;

/**
 * 通过反射来修改分行信息的属性
 */
public class reflectionTest {
    public static void main(String[] args) {
        SubBank subBank = new SubBank("shanghai","dongyin","hahah");
        Class<?> cls=SubBank.class;
        Field field= null;
        try {
            field = cls.getDeclaredField("bankName");
            field.setAccessible(true);//为 true 则表示反射的对象在使用时取消 Java 语言访问检查
//            System.out.println("修改之前的属性"+subBank.getBankName());
            field.set(subBank,"beijing");
//            System.out.println("修改之前的属性"+subBank.getBankName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
