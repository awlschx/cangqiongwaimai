package com.chx.context;
/**
 * 上下文环境类
 */
//线程存放数据，线程之间不会互相影响
    //子线程拿不到父线程的数据
    //存在线程里面在后续的service和mapper可以直接获取线程的数据
    //线程使用之后一定要移除线程，防止内存泄露
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static Long getCurrentId(){
        return threadLocal.get();
    }
    public static void removeCurrentId(){
        threadLocal.remove();
    }
}
