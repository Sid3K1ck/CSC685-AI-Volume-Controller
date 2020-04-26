public class RecordSet {

	private String label;
	private int lowerBound;
	private int upperBound;
	private double valueFuzzySet1;
	private double valueFuzzySet2;
	private double valueFuzzySet3;

	public RecordSet(String label, int lowerBound, int upperBound, double valueFuzzySet1, double valueFuzzySet2,
	                 double valueFuzzySet3) {
		this.label = label;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.valueFuzzySet1 = valueFuzzySet1;
		this.valueFuzzySet2 = valueFuzzySet2;
		this.valueFuzzySet3 = valueFuzzySet3;
	}

	public String getLabel() { return label; }
	public void setLabel(String label) { this.label = label; }

	public int getLowerBound() { return lowerBound; }
	public void setLowerBound(int lowerBound) { this.lowerBound = lowerBound; }

	public int getUpperBound() { return upperBound; }
	public void setUpperBound(int upperBound) { this.upperBound = upperBound; }

	public double getValueFuzzySet1() { return valueFuzzySet1; }
	public void setValueFuzzySet1(double valueFuzzySet1) { this.valueFuzzySet1 = valueFuzzySet1; }

	public double getValueFuzzySet2() { return valueFuzzySet2; }
	public void setValueFuzzySet2(double valueFuzzySet2) { this.valueFuzzySet2 = valueFuzzySet2; }

	public double getValueFuzzySet3() { return valueFuzzySet3; }
	public void setValueFuzzySet3(double valueFuzzySet3) { this.valueFuzzySet3 = valueFuzzySet3; }

}
