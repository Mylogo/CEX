package net.bukkitdev.cex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import net.bukkitdev.cex.expression.Expression;
import net.bukkitdev.cex.expression.ExpressionCommandName;
import net.bukkitdev.cex.expression.Result;
import net.bukkitdev.cex.expression.Results;

import org.bukkit.command.CommandSender;

public class CEXMethod {

	private final CEXer cexer;
	private final Method method;
	
	protected Expression[] expressions;
	
	protected CEXMethod(CEXer cexer, Method method, String fullCmd) throws WrongParameterException{
		this.cexer = cexer;
		this.method = method;
		
		String[] p = fullCmd.split(" ");
		System.out.println("ARRAY LENGTH:" + p.length);
		ArrayList<Expression> exprs = new ArrayList<>();
		
		//If the command is only the command itself
		String cmdName = p.length == 0 ? fullCmd : p[0];
		System.out.println("Command Name: " + cmdName);
		ExpressionCommandName exName = new ExpressionCommandName(cmdName);
		exprs.add(exName);
		
		
		if(p.length > 1)
		for(int i = 1; i < p.length; i++){
			System.out.println("Now iterating!");
			String cmdPart = p[i];
			Expression ex = Expression.getExpressor(cmdPart);
			if(ex == null){
				throw new WrongParameterException(cmdPart);
			} else {
				exprs.add(ex);
			}
			
		}
		
		expressions = new Expression[p.length];
		int index = 0;
		for(Expression ex : exprs){
			expressions[index] = ex;
			index = index + 1;
		}
	}

	public boolean reactsTo(CommandSender sender, String[] p){
		System.out.println("Parameter size:"+p.length);
		System.out.println("Expression size:"+expressions.length);
		if(p.length == expressions.length){
			System.out.println("Equal size");
			for(int i = 0; i < expressions.length; i++){
				System.out.println("Now at:" + i);
				if(!expressions[i].reactsTo(sender, p[i])){
					System.out.println("stopped at:" + i);
					return false;
				}
			}
			//It would have always reacted, so it reacts!
			System.out.println("It reacts!");
			return true;
		}
		System.out.println("Wont react");
		return false;
	}
	
	public void react(CommandSender sender, String[] p){
		Results results = result(sender, p);
		for(int i = 1; i < expressions.length; i++){
			Result r = expressions[i].react(sender, p[i]);
			results.addResult(r);
		}
		
		try {
			method.invoke(cexer.getExecutor(), sender, results);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			System.out.println(" ===FATAL ERROR: Something went wrong while passing parameters to executor objects.");
			e.printStackTrace();
		}
		
	}
	
	protected Results result(CommandSender sender, String[] p){
		Results results = new Results();
		for(int i = 1; i < expressions.length; i++){
			Result r = expressions[i].react(sender, p[i]);
			results.addResult(r);
		}
		return results;
	}
	
	public static CEXMethod newInstance(CEXer cexer, Method method, String fullCmd) throws WrongParameterException{
		if(fullCmd.endsWith("<ANY>")){
			System.out.println("It is an <ANY> method");
			return new CEXAnyMethod(cexer, method, fullCmd);
		} else {
			return new CEXMethod(cexer, method, fullCmd);
		}
	}
	
}
