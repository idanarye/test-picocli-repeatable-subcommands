import java.util.LinkedList;

import picocli.CommandLine;

@CommandLine.Command
public class App {
    public static void main(String[] args) {

	CommandLine cli = new CommandLine(new App());
	cli
	    .addSubcommand("add", new PlotCommandAdd())
	    .addSubcommand("show", new PlotCommandShow())
	    .execute(args);
	SubcommandPostprocessing postprocessingTarget = null;
	LinkedList<Object> postprocessingArgs = null;
	for (CommandLine cmd : cli.getParseResult().asCommandLineList()) {
	    if (cmd.getCommand() instanceof SubcommandPostprocessing) {
		if (postprocessingTarget != null) {
		    postprocessingTarget.postProcess(postprocessingArgs);
		}
		postprocessingTarget = cmd.getCommand();
		postprocessingArgs = new LinkedList<>();
	    } else if (postprocessingArgs != null) {
		Object executionResult = cmd.getExecutionResult();
		if (executionResult != null) {
		    postprocessingArgs.add(executionResult);
		}
	    }
	}
	if (postprocessingTarget != null) {
	    postprocessingTarget.postProcess(postprocessingArgs);
	}
    }
}
