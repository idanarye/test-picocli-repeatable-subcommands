import picocli.CommandLine;

@CommandLine.Command(subcommandsRepeatable = true)
public class PlotCommandShow extends PlotCommandBase {
    public PlotCommandShow(InvokeWhenDone invokeWhenDone) {
	super(invokeWhenDone);
    }

    @Override
    protected PlotEntry createEntry() {
	return PlotEntry.buildNew("...");
    }

    @Override
    protected void invoke(PlotEntry plotEntry) {
	System.out.println("Showing plot:");

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
