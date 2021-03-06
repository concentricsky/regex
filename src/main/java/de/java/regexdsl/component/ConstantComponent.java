package de.java.regexdsl.component;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import de.java.regexdsl.model.SimpleExpression;

/**
 * Matches the constant expression, escapes special characters contained in the constant 
 * expression.
 * 
 * @author Christian Bannes
 */
public class ConstantComponent extends SimpleExpression {

	private final static String[] metaChars = {"?", "$", ".", "+", "^", "(", ")", "[", "]"};
	private final @Nonnull String constant;

	/**
	 * @param constant the constant expression, not null.
	 */
	public ConstantComponent(final @Nonnull String constant) {
		checkNotNull(constant, "constant must not be null");
		
		String result = constant.replaceAll("\\\\", "\\\\\\\\");
		
		for(final String metaChar : metaChars)
		{
			result = result.replaceAll("\\" + metaChar , "\\\\" + metaChar);
		}
		this.constant = result;
	}
	
	@Override
	public @Nonnull String asRegex() {
		return constant;
	}

}
