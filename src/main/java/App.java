import picocli.CommandLine;

@CommandLine.Command
public class App {
    public static void main(String[] args) {

	PlotCommandBase.InvokeWhenDone invokeWhenDone = new PlotCommandBase.InvokeWhenDone();
	new CommandLine(new App())
	    .addSubcommand("add", new PlotCommandAdd(invokeWhenDone))
	    .addSubcommand("show", new PlotCommandShow(invokeWhenDone))
	    .execute(args);
	invokeWhenDone.invoke();
    }
}
