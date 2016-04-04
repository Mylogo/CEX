package net.bukkitdev.cex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CEXHelper {

	public static List<Method> getCexMethods(Object o){
		ArrayList<Method> annotatedMethods = new ArrayList<>();
		
		for(Method meth : o.getClass().getDeclaredMethods()){
			
			if(meth.isAnnotationPresent(Command.class)){
				System.out.println("Method " + meth.getName() + " has annotation");
				annotatedMethods.add(meth);
			}
			
		}
		
		return annotatedMethods;
	}
	
//	public static List<Method> getCexMethods(Object o){
//		return getAnnotatedMethods(o, Command.class);
//	}
	
	public static List<Method> getAnnotatedMethods(Object obj, Class<? extends Annotation> annotation){
		ArrayList<Method> annotatedMethods = new ArrayList<Method>();
		
		Class<?> current = obj.getClass();
		
		while(true){
			
			if(current.equals(Object.class))
				break;
			
			
			Method[] methods = current.getDeclaredMethods();
			
			for(Method m : methods){
				if(m.isAnnotationPresent(annotation)){
					if(!annotatedMethods.contains(m))
						annotatedMethods.add(m);
				}
			}
			
			current = current.getSuperclass();
		}
		
		return annotatedMethods;
	}
	
	public static List<Method> getAnnotatedMethods(Object obj, Annotation an){
		return getAnnotatedMethods(obj, an);
	}
	
}
