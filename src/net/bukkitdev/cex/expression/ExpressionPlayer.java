package net.bukkitdev.cex.expression;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExpressionPlayer extends ExpressionObject {

	@Override
	protected String createRegex() {
		return "[a-zA-Z1-9_]{2,16}";
	}

	@Override
	protected String createShallRegex() {
		return "<player>";
	}

	@Override
	public boolean isExpressionFor(String commandExpression) {
		return commandExpression.equalsIgnoreCase("<player>");
	}

	@Override
	public boolean reactsTo(CommandSender sender, String[] p, int index, String enteredCommand) {
		if(matchesRegex(enteredCommand)){
			Player pl = Bukkit.getPlayerExact(enteredCommand);
			if(pl != null)
				return true;
		}
		return false;
	}

	@Override
	public Result react(CommandSender sender, String[] p, int index, String enteredCommand) {
		return new Result(Bukkit.getPlayerExact(enteredCommand));
	}

	
	
	
	
}
