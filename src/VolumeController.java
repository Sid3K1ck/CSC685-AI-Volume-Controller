import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class VolumeController {
	// Global Variables

	// Non-fuzzy & Fuzzy sets for Time (T)
	ArrayList setsTime = new ArrayList(11);
	String[] timeHeader = {"Time (T)", "Non-fuzzy Lower Limit", "Non-fuzzy Upper Limit", "Time Long (TL)",
		"Time Medium (TM)", "Time Short (TS)"};
	// setsTime.add("T01", 0, 180, 0.0, 0.2, 1.0);
	// setsTime.add("T02", 180, 195, 0.1, 0.4, 0.9);
	// setsTime.add("T03", 195, 210, 0.2, 0.6, 0.8);
	// setsTime.add("T04", 210, 225, 0.3, 0.8, 0.7);
	// setsTime.add("T05", 225, 240, 0.4, 1.0, 0.5);
	// setsTime.add("T06", 240, 255, 0.5, 0.8, 0.4);
	// setsTime.add("T07", 255, 270, 0.7, 0.6, 0.3);
	// setsTime.add("T08", 270, 285, 0.8, 0.4, 0.2);
	// setsTime.add("T09", 285, 300, 0.9, 0.2, 0.1);
	// setsTime.add("T10", 300, 900, 1.0, 0.1, 0.0);

	// Non-fuzzy & Fuzzy sets for Decibels (D)
	ArrayList setsDecibel = new ArrayList(11);
	String[] decibelHeader = {"Decibel (D)", "Non-fuzzy Lower Limit", "Non-fuzzy Upper Limit", "Decibel High (DH)",
		"Decibel Medium (DM)", "Decibel Low (DL)"};
	// setsDecibel.add("D01", 0, 9, 0.0, 0.2, 1.0);
	// setsDecibel.add("D02", 9, 18, 0.1, 0.4, 0.9);
	// setsDecibel.add("D03", 18, 27, 0.2, 0.6, 0.8);
	// setsDecibel.add("D04", 27, 36, 0.3, 0.8, 0.7);
	// setsDecibel.add("D05", 36, 45, 0.4, 1.0, 0.5);
	// setsDecibel.add("D06", 45, 54, 0.5, 0.8, 0.4);
	// setsDecibel.add("D07", 54, 63, 0.7, 0.6, 0.3);
	// setsDecibel.add("D08", 63, 72, 0.8, 0.4, 0.2);
	// setsDecibel.add("D09", 72, 81, 0.9, 0.2, 0.1);
	// setsDecibel.add("D10", 81, 140, 1.0, 0.1, 0.0);

	// Non-fuzzy & Fuzzy sets for Volume (V)
	ArrayList setsVolume = new ArrayList(11);
	String[] volumeHeader = {"Volume (V)", "Non-fuzzy Lower Limit", "Non-fuzzy Upper Limit", "Volume High (VH)",
		"Volume Medium (VM)", "Volume Low (VL)"};
	// setsVolume.add("V01", 0, 10, 0.0, 0.2, 1.0);
	// setsVolume.add("V02", 10, 20, 0.1, 0.4, 0.9);
	// setsVolume.add("V03", 20, 30, 0.2, 0.6, 0.8);
	// setsVolume.add("V04", 30, 40, 0.3, 0.8, 0.7);
	// setsVolume.add("V05", 40, 50, 0.4, 1.0, 0.5);
	// setsVolume.add("V06", 50, 60, 0.5, 0.8, 0.4);
	// setsVolume.add("V07", 60, 70, 0.7, 0.6, 0.3);
	// setsVolume.add("V08", 70, 80, 0.8, 0.4, 0.2);
	// setsVolume.add("V09", 80, 90, 0.9, 0.2, 0.1);
	// setsVolume.add("V10", 90, 100, 1.0, 0.1, 0.0);

	// Variable Relationships
	ArrayList relationships = new ArrayList(9);
	// relationships.add("R1","Medium");
	// relationships.add("R2","Low");
	// relationships.add("R3","Medium");
	// relationships.add("R4","Low");
	// relationships.add("R5","Medium");
	// relationships.add("R6","High");
	// relationships.add("R7","Medium");
	// relationships.add("R8","Low");
	// relationships.add("R9","Medium");

	public static void main(String[] args) {

		System.out.println("What is the average time between commercials/ads (180 to 300 seconds)?");
		Scanner inputTime = new Scanner(System.in);
		int selectedTime = inputTime.nextInt();

		System.out.println("What is the current decibel level (0 to 90 dB)?");
		Scanner inputDecibel = new Scanner(System.in);
		int selectedDecibel = inputDecibel.nextInt();

		System.out.println("With commercials or ads occurring on average, every " + selectedTime + " seconds, and " +
							   "the current decible reading of " + selectedDecibel + ", the volume should be " +
							   "adjusted to " + calculateVolume(selectedTime, selectedDecibel));
	}

	private static int calculateVolume(int selectedTime, int selectedDecibel) {
		int volume = -1;
		// calculate the volume here...
		return volume;
	}
}
