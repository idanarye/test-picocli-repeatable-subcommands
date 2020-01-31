import picocli.CommandLine;

@CommandLine.Command(subcommandsRepeatable = true)
public abstract class PlotCommandBase implements SubcommandPostprocessing {
    @CommandLine.Command(name = "axis")
    private PlotAxis axis(
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
	return new PlotAxis(caption, scaleType, unitName, expression);
    }

    @CommandLine.Command(name = "filter")
    private PlotFilter filter(
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
	return new PlotFilter(caption, filterType, unitName, expression);
    }

    @CommandLine.Command(name = "formula")
    private PlotFormula formula(
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
	return new PlotFormula(caption, symbol, scaleType, unitName, expression);
    }

}
