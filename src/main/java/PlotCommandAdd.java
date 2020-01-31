import java.util.List;

import picocli.CommandLine;

@CommandLine.Command(subcommandsRepeatable = true)
public class PlotCommandAdd extends PlotCommandBase {
    @CommandLine.Parameters
    private String name;

    public void postProcess(List<Object> args) {
	PlotEntry plotEntry = PlotEntry.buildNew(name, args.toArray());

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
