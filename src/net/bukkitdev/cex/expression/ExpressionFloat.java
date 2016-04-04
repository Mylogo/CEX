package net.bukkitdev.cex.expression;

import org.bukkit.command.CommandSender;

public class ExpressionFloat extends ExpressionPrimitive {

	@Override
	protected String createRegex() {
		return "[0-9]";
	}

	@Override
	protected String createShallRegex() {
		return "<float>";
	}

	@Override
	public boolean isExpressionFor(String commandExpression) {
		return matchesShallRegex(commandExpression);
	}

	@Override
	public boolean reactsTo(CommandSender sender, String[] p, int index, String enteredCommand) {
		try {
			enteredCommand = enteredCommand.replace(',', '.');
			Float.parseFloat(enteredCommand);
			return true;
		} catch(Exception e){
			return false;
		}
	}

	@Override
	public Result react(CommandSender sender, String[] p, int index, String enteredCommand) {
		return new Result(Float.parseFloat(enteredCommand.replace(',', '.')));
	}

}
