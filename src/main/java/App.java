import java.util.List;

import picocli.CommandLine;

@CommandLine.Command(subcommands = {
// CliAddAxis.class,
},
subcommandsRepeatable = true
)
public class App {
    private PlotEntry entry = null;

    private PlotEntry getEntry() {
	if (entry == null) {
	    entry = PlotEntry.buildNew(name);
	}
	return entry;
    }

    @CommandLine.Parameters
    private String name;

    @CommandLine.Command(name = "axis")
    private void axis(
	    @CommandLine.Parameters
	    String caption,
	    @CommandLine.Parameters
	    String expression,
	    @CommandLine.Option(names = {"-S", "--scale"}, defaultValue="LINEAR")
	    ScaleType scaleType,
	    @CommandLine.Option(names = {"-u", "--unit"})
	    String unitName
	    )
    {
	getEntry().getAxes().add(new PlotAxis(caption, scaleType, unitName, expression));
    }

    @CommandLine.Command(name = "filter")
    private void filter(
	    @CommandLine.Parameters
	    String caption,
	    @CommandLine.Parameters
	    String expression,
	    @CommandLine.Option(names = {"-t", "--type"}, required = true)
	    FilterType filterType,
	    @CommandLine.Option(names = {"-u", "--unit"})
	    String unitName
    )
    {
	getEntry().getFilters().add(new PlotFilter(caption, filterType, unitName, expression));
    }

    @CommandLine.Command(name = "formula")
    private void formula(
	    @CommandLine.Parameters
	    String caption,
	    @CommandLine.Parameters
	    String expression,
	    @CommandLine.Option(names = {"-s", "--symbol"})
	    String symbol,
	    @CommandLine.Option(names = {"-S", "--scale"}, defaultValue="LINEAR")
	    ScaleType scaleType,
	    @CommandLine.Option(names = {"-u", "--unit"})
	    String unitName
    )
    {
	getEntry().getFormulas().add(new PlotFormula(caption, symbol, scaleType, unitName, expression));
    }

    public static void main(String[] args) {

	App app = new App();
	CommandLine cli = new CommandLine(app);
	cli.execute(args);
	System.out.printf("Creating new plot %s\n", app.entry.getName());

	System.out.println("\tIts axes are:");
	for (PlotAxis axis : app.entry.getAxes()) {
	    System.out.printf("\t\t%s\n", axis);
	}

	System.out.println("\tIts filters are:");
	for (PlotFilter filter : app.entry.getFilters()) {
	    System.out.printf("\t\t%s\n", filter);
	}

	System.out.println("\tIts formulas are:");
	for (PlotFormula formula : app.entry.getFormulas()) {
	    System.out.printf("\t\t%s\n", formula);
	}
    }
}
