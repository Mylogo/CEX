package net.bukkitdev.cex.expression;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ExpressionCommandName extends Expression {

	private Class<?> senderType;
	private String commandName;
	
	public ExpressionCommandName(String cmdName) {
		super();

		System.out.println("Now registering cmd thingy");
		
		if(cmdName.startsWith("/")){
			senderType = Player.class;
		} else if(cmdName.startsWith("+")){
			senderType = CommandSender.class;
		} else {
			senderType = ConsoleCommandSender.class;
		}

		if(!cmdName.startsWith("/")){
			cmdName = "/" + cmdName.substring(1);
		}
		
		this.commandName = cmdName;
	}
	
	@Override
	protected String createRegex() {
		return "!";
	}

	
//	@Command(format="/commandPlayer")
//	@Command(format="+commandAll")
//	@Command(format="-consoleOnly")
	@Override
	protected String createShallRegex() {
		return "(\\/|\\+|-)\\w+";
	}
	
	@Override
	public boolean reactsTo(CommandSender sender, String[] p, int index, String enteredCommand) {
		System.out.println("checking if " + enteredCommand + " will wrok");
		System.out.println("My Sender:"+senderType.getName() + " CommandSender:"+sender.getClass().getName());
		if(senderType.isInstance(sender)){ //Checking if it inhertis
			System.out.println("Sender type is correct");
			if(enteredCommand.equalsIgnoreCase(commandName)){
				System.out.println("Equals is right");
				return true;
			}  else {
				System.out.println("The command " + enteredCommand + " did not match with " + commandName);
			}
			
		}
		return false;
	}
	
	public Result react(CommandSender sender, String[] p, int index, String enteredCommand){
		return null;
	}

	@Override
	public boolean isExpressionFor(String commandExpression) {
		return matchesShallRegex(commandExpression);
	}

	
	
}
