package net.bukkitdev.cex.expression;

import org.bukkit.command.CommandSender;

public class ExpressionString extends ExpressionPrimitive {

	//private static final Pattern CUSTOM_EXPRESSION = Pattern.compile("<string\\{(\\d,\\d)\\}>");
	
	public ExpressionString() {}
	
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
		
		return commandExpression.equalsIgnoreCase("<string>");
		
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
	public boolean reactsTo(CommandSender sender, String[] p, int index, String enteredCommand) {
		return true;
	}

	@Override
	public Result react(CommandSender sender, String[] p, int index, String enteredCommand) {
		return new Result(enteredCommand);
	}
	
}
