# How to Build
1. Follow the instructions in https://github.com/remkop/picocli/issues/454#issuecomment-579546010 to install picocli-4.2.0-SNAPSHOT in the local maven repository
2. Run `gradle shadowJar`

# How to Use

```bash
java -jar build/libs/shadow.jar my-plot \
    axis Year sample.year \
    axis Height sample.year -u centimeters \
    filter Age 'sample.year - person.birthYear' -u years -t NUMERIC_RANGE \
    filter Gender person.gender -t TEXTUAL_SINGLE \
    formula Salary sample.salary --symbol $ -u USD -S LOGARITHMIC \
    formula Happyness 'sample.pizzaEaten + sample.beerConsumed' --symbol ':-)'
```

This will print:
```
Creating new plot my-plot
	Its axes are:
		PlotAxis(Year, scale=LINEAR, unit=null, expression=sample.year)
		PlotAxis(Height, scale=LINEAR, unit=centimeters, expression=sample.year)
	Its filters are:
		PlotFilter(Age, filterType=NUMERIC_RANGE, unit=years, expression=sample.year - person.birthYear)
		PlotFilter(Gender, filterType=TEXTUAL_SINGLE, unit=null, expression=person.gender)
	Its formulas are:
		PlotFormula(Salary, symbol=$, scaleType=LOGARITHMIC, unit=USD, expression=sample.salary)
		PlotFormula(Happyness, symbol=:-), scaleType=LINEAR, unit=null, expression=sample.pizzaEaten + sample.beerConsumed)
```
