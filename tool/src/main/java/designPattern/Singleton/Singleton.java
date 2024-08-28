package designPattern.Singleton;

/**
 * 单例
 * 1. 饿汉直接new，线程安全
 * 2. 懒汉先判断，懒汉式getInstance方法添加sychronize可以简单实现线程安全
 */
public class Singleton {
    private  static Singleton instance;
    private Singleton(){};
    public synchronized static Singleton getInstance(){
        if (null == instance){
            instance = new Singleton();
        }
        return instance;
    }
}
