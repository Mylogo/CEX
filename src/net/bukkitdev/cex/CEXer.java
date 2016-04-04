package net.bukkitdev.cex;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CEXer {

	private Object executor;
	private List<CEXMethod> methods = new ArrayList<>();
	
	public CEXer(Object o) throws WrongParameterException {
		this.executor = o;
		List<Method> meths = CEXHelper.getCexMethods(o);
		System.out.println("mets.length="+meths.size());
		if(meths.size() > 0){
			
			for(Method m : meths){
				
				String format = m.getAnnotation(Command.class).format();
				System.out.println("Now registering method:" + m.getName());
				CEXMethod meth = CEXMethod.newInstance(this, m, format);
				
				methods.add(meth);
				System.out.println("Now having:" + methods.size() + " objects in methods array");
			}
		} else {
			System.out.println("No methods were found while registerting " + o.toString());
		}
	}

	public Object getExecutor() {
		return executor;
	}

	public List<CEXMethod> getMethods() {
		return methods;
	}
	
}



class WrongParameterException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final String parameter;
	
	public WrongParameterException(String parameter){
		this.parameter = parameter;
	}
	
	@Override
	public void printStackTrace() {
		super.printStackTrace();
		System.out.println("The Object/Parameter " + parameter + " should have been something else.");
	}

}