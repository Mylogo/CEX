package net.bukkitdev.cex.expression;

import org.bukkit.command.CommandSender;

public class ExpressionRest extends ExpressionObject {

	@Override
	protected String createRegex() {
		return "\\w+";
	}

	@Override
	protected String createShallRegex() {
		return "<rest>";
	}

	@Override
	public boolean isExpressionFor(String commandExpression) {
		return matchesShallRegex(commandExpression);
	}

	@Override
	public boolean reactsTo(CommandSender sender, String[] p, int index, String enteredCommand) {
		return true;
	}

	@Override
	public Result react(CommandSender sender, String[] p, int index, String enteredCommand) {
		StringBuilder sb = new StringBuilder();
		System.out.println("Now start indexing");
		for(int i = index; i < p.length; i++){
			System.out.println("being at index " + i);
			sb.append(p[i] + (i == p.length-1 ? "" : " "));
		}
		
		return new Result(sb.toString());
		
	}

}
