import java.util.List;

import picocli.CommandLine;

@CommandLine.Command(subcommandsRepeatable = true)
public class PlotCommandShow extends PlotCommandBase {
    public void postProcess(List<Object> args) {
	PlotEntry plotEntry = PlotEntry.buildNew("...", args.toArray());

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
