package net.bukkitdev.cex.expression;

import org.bukkit.command.CommandSender;

public class ExpressionInteger extends ExpressionPrimitive {

	final int minInteger = Integer.MIN_VALUE;
	final int maxInteger = Integer.MAX_VALUE;
	
	@Override
	protected String createRegex() {
		return "[0-9]";
	}
	@Override
	protected String createShallRegex() {
		return "<int>";
	}

	@Override
	public boolean isExpressionFor(String commandExpression) {

		return "<int>".equalsIgnoreCase(commandExpression);
		
	}

	@Override
	public boolean reactsTo(CommandSender sender, String[] p, int index, String enteredCommand) {
		try {
			System.out.println("Will i react for:" + enteredCommand);
			Integer.parseInt(enteredCommand);
			System.out.println("I react!");
			return true;
		} catch(Exception e){
			return false;
		}
	}

	@Override
	public Result react(CommandSender sender, String[] p, int index, String enteredCommand) {
		return new Result(Integer.parseInt(enteredCommand));
	}

	
	
}
