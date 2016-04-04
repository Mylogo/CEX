package net.bukkitdev.cex.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.command.CommandSender;

abstract public class Expression {

	protected Pattern regex;
	protected Pattern shallRegex;
	
	public static final List<Expression> EXPRESSIONS = new ArrayList<>();
	public static final List<Expression> CUSTOM_EXPRESSIONS = new ArrayList<>();
	
	public static final Expression getExpressor(String commandExpression){
		for(Expression expr : EXPRESSIONS){
			if(expr.isExpressionFor(commandExpression)){
				return expr;
			}
		}
		for(Expression expr : CUSTOM_EXPRESSIONS){
			if(expr.isExpressionFor(commandExpression)){
				if(expr instanceof CustomExpression){
					System.out.println("Returning a custom expression");
					return ((CustomExpression)expr).getCustomExpression(commandExpression);
				}
			}
		}
		return null;
	}
	
	static {
		//ADD IT!
		
		ExpressionString expressionString = new ExpressionString();
		EXPRESSIONS.add(expressionString);
		ExpressionPlayer expressionPlayer = new ExpressionPlayer();
		EXPRESSIONS.add(expressionPlayer);
		ExpressionAny any = new ExpressionAny();
		EXPRESSIONS.add(any);
		
		ExpressionConstant constantChecker = new ExpressionConstant("TEMPORARY_DUE_TO_CHECK");
		CUSTOM_EXPRESSIONS.add(constantChecker);
		CUSTOM_EXPRESSIONS.add(expressionString);
		
	}
		
	public Expression(){
		this.regex = Pattern.compile(createRegex());
		this.shallRegex = Pattern.compile(createShallRegex());
	}
	
	abstract protected String createRegex();
	abstract protected String createShallRegex();
	
	abstract public boolean isExpressionFor(String commandExpression);
	abstract public boolean reactsTo(CommandSender sender, String enteredCommand);
	
	public boolean matchesShallRegex(String val){
		return shallRegex.matcher(val).find();
	}
	
	public boolean matchesRegex(String val){
		return regex.matcher(val).find();
	}

	abstract public Result react(CommandSender sender, String enteredCommand);
	
}
