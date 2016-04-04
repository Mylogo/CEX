package net.bukkitdev.cex;

import java.lang.reflect.Method;

import net.bukkitdev.cex.expression.Result;
import net.bukkitdev.cex.expression.Results;

import org.bukkit.command.CommandSender;

public class CEXAnyMethod extends CEXMethod {

	protected CEXAnyMethod(CEXer cexer, Method method, String fullCmd) throws WrongParameterException {
		super(cexer, method, fullCmd);
//		String[] p = fullCmd.split(" ");
//		ExpressionCommandName exName = new ExpressionCommandName(p[0]);
	}
	
	@Override
	public boolean reactsTo(CommandSender sender, String[] p) {
		return (expressions[0].reactsTo(sender, p[0]));
	}
	
	@Override
	protected Results result(CommandSender sender, String[] p) {
		Results r = new Results();
		for(String s : p){
			
			r.addResult(new Result(s));
			
		}
		return r;
	}

	
	
}