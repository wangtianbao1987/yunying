package com.wtb.fuwu.wechart;

import java.io.Closeable;
import java.io.OutputStream;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SourceUtils {
	private static Log logging = LogFactory.getLog(SourceUtils.class);
	public static void runMethod(String methodName,Object... objs){
		if(objs == null){
			return;
		}
		for(Object obj:objs){
			if(obj == null){
				return;
			}
			try {
				Method m = obj.getClass().getDeclaredMethod(methodName);
				m.invoke(obj);
			} catch (Exception e) {
				logging.error("执行["+methodName+"]方法异常！对象："+obj+";异常："+e.getMessage());
			}
		}
	}
	public static void close(Object... objs){
		if(objs == null){
			return;
		}
		for(Object obj:objs){
			if(obj == null){
				return;
			}
			try {
				if(obj instanceof Closeable){
					((Closeable) obj).close();
				}else if(obj instanceof AutoCloseable){
					((AutoCloseable) obj).close();
				}else{
					runMethod("close", obj);
				}
			} catch (Exception e) {
				logging.error("关闭资源异常！对象："+obj+";异常："+e.getMessage(), e);
			}
		}
	}
	
	public static void flush(Object... objs){
		if(objs == null){
			return;
		}
		for(Object obj:objs){
			if(obj == null){
				return;
			}
			try {
				if(obj instanceof OutputStream){
					((OutputStream) obj).flush();
				}else{
					runMethod("flush", obj);
				}
			} catch (Exception e) {
				logging.error("关闭资源异常！对象："+obj+";异常："+e.getMessage());
			}
		}
	}
	
}
