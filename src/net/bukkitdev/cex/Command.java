package net.bukkitdev.cex;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)


public @interface Command {

	/*
	 * Possible format parameters:
	 * 
	 * For command name: accesModifier+name
	 * Player only -> /
	 * Console only -> -
	 * Both -> +
	 * 
	 * For command parameters:
	 * 
	 * <String> -> Passes the text of yours back
	 * <constant=CONSTANT_NAME> -> You may add constants in your command e.g. /test <constant=add> <String>
	 * <int[+|-]>
	 * 
	 */
	String format();
	
}
