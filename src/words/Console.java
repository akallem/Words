package words;

/**
 * Methods to display the REPL console.
 */
public class Console {
	public static void showPrompt() {
		if (Options.PRINT_TO_CONSOLE)
			System.out.print("> ");
	}
	
	public static void showPromptMore() {
		if (Options.PRINT_TO_CONSOLE)
			System.err.printf("... ");
	}
}
