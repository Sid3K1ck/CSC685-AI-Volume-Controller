public class RuleSet {

	private String code;
	private String type;
	private double value;
	private String time;
	private String decibel;

	public RuleSet(String code, String type, double value, String time, String decibel) {
		this.code = code;
		this.type = type;
		this.time = time;
		this.decibel = decibel;
	}

	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }

	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	public double getValue() { return value; }
	public void setValue(double value) { this.value = value; }

	public String getTime() { return time; }
	public void setTime(String time) { this.time = time; }

	public String getDecibel() { return decibel; }
	public void setDecibel(String decibel) { this.decibel = decibel; }

}
