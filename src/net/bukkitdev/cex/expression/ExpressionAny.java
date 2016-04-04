package net.bukkitdev.cex.expression;

import org.bukkit.command.CommandSender;

public class ExpressionAny extends Expression {

	@Override
	protected String createRegex() {
		return "\\w";
	}

	@Override
	protected String createShallRegex() {
		return "<ANY>";
	}

	@Override
	public boolean isExpressionFor(String commandExpression) {
		return commandExpression.equalsIgnoreCase("<ANY>");
			
	}

	@Override
	public boolean reactsTo(CommandSender sender, String enteredCommand) {
		return true;
	}

	@Override
	public Result react(CommandSender sender, String enteredCommand) {
		return null;
	}

}
