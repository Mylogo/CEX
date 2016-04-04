package net.bukkitdev.cex.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.command.CommandSender;

public class ExpressionConstant extends Expression implements CustomExpression {

	private final String CONSTANT_NAME;
	private Pattern p = Pattern.compile("<constant=(.*?)>");
	
	public ExpressionConstant(String constantCommandPart) {
		CONSTANT_NAME = constantCommandPart;
		this.shallRegex = null;
		this.regex = null;
	}
	
	@Override
	protected String createRegex() {
		return "!";
	}

	@Override
	protected String createShallRegex() {
		return "!";
	}

	@Override
	public boolean isExpressionFor(String commandExpression) {
		System.out.println("checking if constant is right");
		return p.matcher(commandExpression).find();
	}

	@Override
	public boolean reactsTo(CommandSender sender, String[] p, int index, String enteredCommand) {
		
		return enteredCommand.equalsIgnoreCase(CONSTANT_NAME);
		
	}

	@Override
	public Result react(CommandSender sender, String[] p, int index, String enteredCommand) {
		return new Result(enteredCommand);
	}

	@Override
	public Expression getCustomExpression(String commandExpressionPart) {
		Matcher m = p.matcher(commandExpressionPart);
		String s = m.find() ? m.group(1) : "";
		System.out.println("Constant:" + s);
		return new ExpressionConstant(s);
	}

}
