package net.bukkitdev.cex.expression;

import java.util.regex.Pattern;

import org.bukkit.command.CommandSender;

public class ExpressionString extends ExpressionPrimitive implements CustomExpression{

	private static final Pattern CUSTOM_EXPRESSION = Pattern.compile("<string\\{(\\d,\\d)\\}>");
	
	private int min = -1;
	private int max = -1;
	
	public ExpressionString() {}
	
	public ExpressionString(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	protected String createRegex() {
		return "[\\w+]";
	}

	@Override
	protected String createShallRegex() {
		return "<string>";
	}

	@Override
	public boolean isExpressionFor(String commandExpression) {
		
		
		
		int length = commandExpression.length();
		int myLength = "<string>".length();
		if(length == myLength){
			return commandExpression.equalsIgnoreCase("<string>");
		} else {
			if(CUSTOM_EXPRESSION.matcher(commandExpression).find()){
				return true;
			}
			
		}
		
		return false;
		
		
		
		
//		if(commandExpression.length() == shallRegex.pattern().length()){
//			System.out.println("Simple, it is same length");
//			System.out.println("IMPORTANT:"+commandExpression.equalsIgnoreCase("<string>"));
//			return commandExpression.equalsIgnoreCase("<string>");
//		} else {
//			System.out.println("trying if it has requirements");
//			System.out.println("IMPORTANT2:"+CUSTOM_EXPRESSION.matcher(commandExpression).find() + " second: "+ (commandExpression.length()=="<string>".length()));
//			return CUSTOM_EXPRESSION.matcher(commandExpression).find() && commandExpression.length()>"<string>".length();
//		}	
	}

	@Override
	public boolean reactsTo(CommandSender sender, String enteredCommand) {
		if(min == -1){
			System.out.println("min is " +min + " so simple true" );
			return true;
		}
		else
		{
			int length = enteredCommand.length();
			System.out.println("my min is " + min + " and max " + max);
			if(length >= min && length <= max)
				return true;
			else return false;
		}
	}

	@Override
	public Result react(CommandSender sender, String enteredCommand) {
		return new Result(enteredCommand);
	}

	@Override
	public Expression getCustomExpression(String commandExpressionPart) {
		System.out.println("creating custom expression");
		String[] p = CUSTOM_EXPRESSION.matcher(commandExpressionPart).group(1).split(",");
		int thisMin = Integer.parseInt(p[0]);
		int thisMax = Integer.parseInt(p[1]);
		return new ExpressionString(thisMin, thisMax);
	}

	
	
}
