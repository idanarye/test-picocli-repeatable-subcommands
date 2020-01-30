import picocli.CommandLine;

@CommandLine.Command(subcommandsRepeatable = true)
public class PlotCommandAdd extends PlotCommandBase {
    public PlotCommandAdd(InvokeWhenDone invokeWhenDone) {
	super(invokeWhenDone);
    }

    @CommandLine.Parameters
    private String name;

    @Override
    protected PlotEntry createEntry() {
	return PlotEntry.buildNew(name);
    }

    @Override
    protected void invoke(PlotEntry plotEntry) {
	System.out.printf("Creating new plot %s\n", plotEntry.getName());

	System.out.println("\tIts axes are:");
	for (PlotAxis axis : plotEntry.getAxes()) {
	    System.out.printf("\t\t%s\n", axis);
	}

	System.out.println("\tIts filters are:");
	for (PlotFilter filter : plotEntry.getFilters()) {
	    System.out.printf("\t\t%s\n", filter);
	}

	System.out.println("\tIts formulas are:");
	for (PlotFormula formula : plotEntry.getFormulas()) {
	    System.out.printf("\t\t%s\n", formula);
	}
    }
}
