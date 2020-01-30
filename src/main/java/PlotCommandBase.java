import picocli.CommandLine;

@CommandLine.Command(subcommandsRepeatable = true)
public abstract class PlotCommandBase {
    public static class InvokeWhenDone {
	private PlotCommandBase commandToCall = null;

	private void set(PlotCommandBase commandToCall) {
	    if (this.commandToCall != null && this.commandToCall != commandToCall) {
		throw new RuntimeException("InvokeWhenDone set multiple times");
	    }
	    this.commandToCall = commandToCall;
	}

	public void invoke() {
	    this.commandToCall.invoke(this.commandToCall.getOrCreateEntry());
	}
    }

    public PlotEntry entry = null;

    private InvokeWhenDone invokeWhenDone;

    public PlotCommandBase(InvokeWhenDone invokeWhenDone) {
	this.invokeWhenDone = invokeWhenDone;
    }

    protected abstract PlotEntry createEntry();
    protected abstract void invoke(PlotEntry plotEntry);

    protected PlotEntry getOrCreateEntry() {
	if (entry == null) {
	    entry = createEntry();
	    invokeWhenDone.set(this);
	}
	return entry;
    }

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
	getOrCreateEntry().getAxes().add(new PlotAxis(caption, scaleType, unitName, expression));
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
	getOrCreateEntry().getFilters().add(new PlotFilter(caption, filterType, unitName, expression));
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
	getOrCreateEntry().getFormulas().add(new PlotFormula(caption, symbol, scaleType, unitName, expression));
    }

}
