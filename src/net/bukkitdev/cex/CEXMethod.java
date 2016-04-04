package net.bukkitdev.cex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import net.bukkitdev.cex.expression.Expression;
import net.bukkitdev.cex.expression.ExpressionCommandName;
import net.bukkitdev.cex.expression.ExpressionRest;
import net.bukkitdev.cex.expression.Result;
import net.bukkitdev.cex.expression.Results;

public class CEXMethod {

	private final CEXer cexer;
	private final Method method;
	
	public static final String NO_PERMISSIONS = "NO_PERMS";
	public static final String NO_PERM_MESSAGE = ChatColor.GRAY + " You do not have any permissions to execute this command.";
	private String permission = NO_PERMISSIONS;
	
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
	
	protected CEXMethod(CEXer cexer, Method method, String fullCmd, String permission) throws WrongParameterException{
		this(cexer, method, fullCmd);
		this.permission = permission;
	}

	public boolean reactsTo(CommandSender sender, String[] p){
		System.out.println("Parameter size:"+p.length + " Expression size:"+expressions.length);
		
		if(p.length == expressions.length){
			System.out.println("Equal size");
			for(int i = 0; i < expressions.length; i++){
				System.out.println("Now at:" + i);
				if(!expressions[i].reactsTo(sender, p, i, p[i])){
					System.out.println("stopped at:" + i);
					return false;
				}
			}
			//It would have always reacted, so it reacts!
			System.out.println(" == It reacts!");
			return true;
		}
		
		if(p.length > expressions.length){
			if(expressions[expressions.length-1] instanceof ExpressionRest){
				System.out.println("I am am instance!");
				for(int i = 0; i < expressions.length-1; i++){
					
					if(!expressions[i].reactsTo(sender, p, i, p[i])){
						System.out.println("stopped at:" + i);
						return false;
					}
					
				}
				System.out.println(" == It reacts cause of <rest>!");
				return true;
			}
		}
		
		System.out.println(" == Wont react");
		return false;
	}
	
	public void react(CommandSender sender, String[] p){
		Results results = result(sender, p);
		for(int i = 1; i < expressions.length; i++){
			Result r = expressions[i].react(sender, p, i, p[i]);
			results.addResult(r);
		}
		
		if(!permission.equals(NO_PERMISSIONS))
			if(!sender.hasPermission(permission)){
				sender.sendMessage(NO_PERM_MESSAGE);
				return;
			}
		
		try {
			method.invoke(cexer.getExecutor(), sender, results);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			System.out.println(" == FATAL ERROR: Something went wrong while passing parameters to executor objects.");
			e.printStackTrace();
		}
		
	}
	
	protected Results result(CommandSender sender, String[] p){
		Results results = new Results();
		for(int i = 1; i < expressions.length; i++){
			Result r = expressions[i].react(sender, p, i, p[i]);
			results.addResult(r);
		}
		return results;
	}
	
}
