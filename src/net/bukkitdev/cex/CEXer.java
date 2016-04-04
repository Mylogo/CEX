package net.bukkitdev.cex;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.bukkitdev.cex.expression.Expression;
import net.bukkitdev.cex.expression.ExpressionRest;

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
				String permission = m.getAnnotation(Command.class).permission();
				CEXMethod meth = null;
				if(permission.equals(CEXMethod.NO_PERMISSIONS))
					meth = new CEXMethod(this, m, format);
				else meth = new CEXMethod(this, m, format, permission);
				
				
				methods.add(meth);
				System.out.println("Now having:" + methods.size() + " objects in methods array");
				
			}
		} else {
			System.out.println("No methods were found while registerting " + o.toString());
		}
		this.methods = sortMethods(this.methods);
	}

	public Object getExecutor() {
		return executor;
	}

	public List<CEXMethod> getMethods() {
		return methods;
	}
	
	//putting everything with <rest> at the end
	private static List<CEXMethod> sortMethods(List<CEXMethod> meths){
		List<CEXMethod> noRest = new ArrayList<>();
		List<CEXMethod> withRest = new ArrayList<>();
		cexloop: for(CEXMethod cexm : meths){
			Expression[] exprs = cexm.expressions;
			for(Expression ex : exprs){
				if(ex instanceof ExpressionRest){
					withRest.add(cexm);
					continue cexloop;
				}
			}
			noRest.add(cexm);
		}
		List<CEXMethod> finalOrder = new ArrayList<>();
		finalOrder.addAll(noRest);
		finalOrder.addAll(withRest);
		return finalOrder;
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