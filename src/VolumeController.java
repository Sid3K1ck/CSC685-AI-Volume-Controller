import java.util.Scanner;

public class VolumeController {

	public static double calculateVolume(double[] selectedVector, RecordSet[] volumeSet) {

		/*
		This includes Step 5 (calculate the scalar Volume action using the center of gravity in which the selected
		deterministic output has a vector that divides the area under a fuzzy set into equal halves.
		 */

		double[] weights = new double[10];
		double[] weightedMemberships = new double[10];
		double netActionVector = 0.0;
		double totalWeightedMembership = 0.0;
		double volume = (totalWeightedMembership) / (netActionVector);

		for (int i = 0; i < volumeSet.length; i++) {

			double lower = volumeSet[i].getLowerBound();
			double upper = volumeSet[i].getUpperBound();
			double value = selectedVector[i];
			double weight = lower + (upper - lower) / 2;
			double weightedMembership = value * weight;

			weights[i] = weight;
			weightedMemberships[i] = weightedMembership;
			totalWeightedMembership = totalWeightedMembership + weightedMembership;
			netActionVector = netActionVector + value;
			volume = (totalWeightedMembership) / (netActionVector);
		}

		return volume;
	}

	public static RuleSet[] updateRelationships(RuleSet[] relationships, RecordSet[] timeSet, RecordSet[] decibelSet,
	                                          int selectedTime, int selectedDecibel) {

		/*
		This includes Step 1 (find the degree membership for each input to the related fuzzy sets by using the timeSet
		and decibelSet arrays) and Step 2 (find the degree of freedom or DOF from the fulfillment of the IF part of
		all rules by ANDing the membership of both time which is variable 1 and decibel level which is variable 2).
		 */

		int sTime = selectedTime;
		int sDecibel = selectedDecibel;

		// set relationship value to the value of the timeSet fuzzy set value if the selected time is within u/l bounds
		for (int i = 0; i < timeSet.length; i++) {

			if (timeSet[i].getLowerBound() < sTime && timeSet[i].getUpperBound() >= sTime) {

				// get fuzzy set values of current time set
				double TL = timeSet[i].getValueFuzzySet1();
				double TM = timeSet[i].getValueFuzzySet2();
				double TS = timeSet[i].getValueFuzzySet3();

				// place values in the Relationships array
				relationships[0].setValue(TL);
				relationships[1].setValue(TL);
				relationships[2].setValue(TL);
				relationships[3].setValue(TM);
				relationships[4].setValue(TM);
				relationships[5].setValue(TM);
				relationships[6].setValue(TS);
				relationships[7].setValue(TS);
				relationships[8].setValue(TS);
			}
		}

		// update relationship value if it is lower than current value
		for (int j = 0; j < decibelSet.length; j++) {

			if (decibelSet[j].getLowerBound() < sDecibel && decibelSet[j].getUpperBound() >= sDecibel) {

				// get fuzzy set values of current decibel set
				double DH = decibelSet[j].getValueFuzzySet1();
				double DM = decibelSet[j].getValueFuzzySet2();
				double DL = decibelSet[j].getValueFuzzySet3();

				// replace value if lower than current value in Relationships array
				if (DH < relationships[0].getValue()) { relationships[0].setValue(DH); }
				if (DM < relationships[1].getValue()) { relationships[1].setValue(DM); }
				if (DL < relationships[2].getValue()) { relationships[2].setValue(DL); }
				if (DH < relationships[3].getValue()) { relationships[3].setValue(DH); }
				if (DM < relationships[4].getValue()) { relationships[4].setValue(DM); }
				if (DL < relationships[5].getValue()) { relationships[5].setValue(DL); }
				if (DH < relationships[6].getValue()) { relationships[6].setValue(DH); }
				if (DM < relationships[7].getValue()) { relationships[7].setValue(DM); }
				if (DL < relationships[8].getValue()) { relationships[8].setValue(DL); }
			}
		}

		return relationships;
	}

	public static double[][] calculateVolumeActionVectors(RuleSet[] relationships, RecordSet[] volumeSet) {

		/*
		This includes Step 3 (calculate volume action vector for each rule by ANDing DOF with the Volume action subset
		elements) and Step 4 (compute the net select action vector by ORing the vectors from Step 3).
		 */

		// create a vector of ROWS = 9 rules with COLS = 10 non-fuzzy sets for volume
		double[][] vectorArray = new double[9][10];

		// i represents ROW or Rule
		for (int i = 0; i < relationships.length; i++) {

			String volumeType = relationships[i].getType();
			double rValue = relationships[i].getValue();

			// j represents COL or non-fuzzy set for Volume
			for (int j = 0; j < volumeSet.length; j++) {

				if (volumeType == "High") {
					double VH = volumeSet[j].getValueFuzzySet1();
					vectorArray[i][j] = (rValue < VH) ? rValue : VH;
				} else if (volumeType == "Medium") {
					double VM = volumeSet[j].getValueFuzzySet2();
					vectorArray[i][j] = (rValue < VM) ? rValue : VM;
				} else if (volumeType == "Low") {
					double VL = volumeSet[j].getValueFuzzySet3();
					vectorArray[i][j] = (rValue < VL) ? rValue : VL;
				} else {
					Boolean error = true;
				}
			}
		}
		return vectorArray;
	}

	public static double[] calculateSelectedVectors(double[][] vectorArray) {

		double[] selectedVectors = new double[10];
		double currentValue = 0.0;

		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 9; row++) {
				double vectorValue = vectorArray[row][col];
				currentValue = (vectorValue > currentValue) ? vectorValue : currentValue;
			}
			selectedVectors[col] = currentValue;
			currentValue = 0.0;
		}

		return selectedVectors;
	}

	public static void main(String[] args) {

		// Create arrays for Non-fuzzy & Fuzzy sets for Time (T)
		String[] timeHeader = {"Time (T)", "Non-fuzzy Lower Limit", "Non-fuzzy Upper Limit", "Time Long (TL)",
			"Time Medium (TM)", "Time Short (TS)"};
		RecordSet[] timeSet = new RecordSet[10];
		timeSet[0] = new RecordSet("T01", 0, 180, 0.0, 0.2, 1.0);
		timeSet[1] = new RecordSet("T02", 180, 195, 0.1, 0.4, 0.9);
		timeSet[2] = new RecordSet("T03", 195, 210, 0.2, 0.6, 0.8);
		timeSet[3] = new RecordSet("T04", 210, 225, 0.3, 0.8, 0.7);
		timeSet[4] = new RecordSet("T05", 225, 240, 0.4, 1.0, 0.5);
		timeSet[5] = new RecordSet("T06", 240, 255, 0.5, 0.8, 0.4);
		timeSet[6] = new RecordSet("T07", 255, 270, 0.7, 0.6, 0.3);
		timeSet[7] = new RecordSet("T08", 270, 285, 0.8, 0.4, 0.2);
		timeSet[8] = new RecordSet("T09", 285, 300, 0.9, 0.2, 0.1);
		timeSet[9] = new RecordSet("T10", 300, 900, 1.0, 0.1, 0.0);

		// Create arrays for Non-fuzzy & Fuzzy sets for Decibels (D)
		String[] decibelHeader = {"Decibel (D)", "Non-fuzzy Lower Limit", "Non-fuzzy Upper Limit", "Decibel High (DH)",
			"Decibel Medium (DM)", "Decibel Low (DL)"};
		RecordSet[] decibelSet = new RecordSet[10];
		decibelSet[0] = new RecordSet("D01", 0, 9, 0.0, 0.2, 1.0);
		decibelSet[1] = new RecordSet("D02", 9, 18, 0.1, 0.4, 0.9);
		decibelSet[2] = new RecordSet("D03", 18, 27, 0.2, 0.6, 0.8);
		decibelSet[3] = new RecordSet("D04", 27, 36, 0.3, 0.8, 0.7);
		decibelSet[4] = new RecordSet("D05", 36, 45, 0.4, 1.0, 0.5);
		decibelSet[5] = new RecordSet("D06", 45, 54, 0.5, 0.8, 0.4);
		decibelSet[6] = new RecordSet("D07", 54, 63, 0.7, 0.6, 0.3);
		decibelSet[7] = new RecordSet("D08", 63, 72, 0.8, 0.4, 0.2);
		decibelSet[8] = new RecordSet("D09", 72, 81, 0.9, 0.2, 0.1);
		decibelSet[9] = new RecordSet("D10", 81, 140, 1.0, 0.1, 0.0);

		// Create arrays for Non-fuzzy & Fuzzy sets for Volume (V)
		String[] volumeHeader = {"Volume (V)", "Non-fuzzy Lower Limit", "Non-fuzzy Upper Limit", "Volume High (VH)",
			"Volume Medium (VM)", "Volume Low (VL)"};
		RecordSet[] volumeSet = new RecordSet[10];
		volumeSet[0] = new RecordSet("V01", 0, 10, 0.0, 0.2, 1.0);
		volumeSet[1] = new RecordSet("V02", 10, 20, 0.1, 0.4, 0.9);
		volumeSet[2] = new RecordSet("V03", 20, 30, 0.2, 0.6, 0.8);
		volumeSet[3] = new RecordSet("V04", 30, 40, 0.3, 0.8, 0.7);
		volumeSet[4] = new RecordSet("V05", 40, 50, 0.4, 1.0, 0.5);
		volumeSet[5] = new RecordSet("V06", 50, 60, 0.5, 0.8, 0.4);
		volumeSet[6] = new RecordSet("V07", 60, 70, 0.7, 0.6, 0.3);
		volumeSet[7] = new RecordSet("V08", 70, 80, 0.8, 0.4, 0.2);
		volumeSet[8] = new RecordSet("V09", 80, 90, 0.9, 0.2, 0.1);
		volumeSet[9] = new RecordSet("V10", 90, 100, 1.0, 0.1, 0.0);

		// Create array for Variable Relationships
		RuleSet[] relationshipSet = new RuleSet[9];
		relationshipSet[0] = new RuleSet("R1", "Low", 0.0, "TL", "DH");
		relationshipSet[1] = new RuleSet("R2","Medium", 0.0, "TL", "DM");
		relationshipSet[2] = new RuleSet("R3","Medium", 0.0, "TL", "DL");
		relationshipSet[3] = new RuleSet("R4","Low", 0.0, "TM", "DH");
		relationshipSet[4] = new RuleSet("R5","Medium", 0.0, "TM", "DM");
		relationshipSet[5] = new RuleSet("R6","Medium", 0.0, "TM", "DL");
		relationshipSet[6] = new RuleSet("R7","Medium", 0.0, "TS", "DH");
		relationshipSet[7] = new RuleSet("R8","Medium", 0.0, "TS", "DM");
		relationshipSet[8] = new RuleSet("R9","High", 0.0, "TS", "DL");

		System.out.println("What is the average time between commercials/ads (180 to 300 seconds)?");
		Scanner inputTime = new Scanner(System.in);
		int selectedTime = inputTime.nextInt();

		// if selected time value is invalid, ask again with more detail
		if (selectedTime < 180 || selectedTime > 300) {
			System.out.println("Incorrect time value, please enter a number between 180 and 300, for seconds");
			selectedTime = inputTime.nextInt();
		}

		System.out.println("What is the current decibel level (0 to 90 dB)?");
		Scanner inputDecibel = new Scanner(System.in);
		int selectedDecibel = inputDecibel.nextInt();

		// if selected decibel value is invalid, ask again with more detail
		if (selectedDecibel < 0 || selectedDecibel > 90) {
			System.out.println("Incorrect decibel value, please select a number between 0 and 90, for dB");
			selectedDecibel = inputDecibel.nextInt();
		}

		// Steps 1 and 2: update the relationships table based on the selected time and decibel level
		RuleSet[] updatedRelationships = updateRelationships(relationshipSet, timeSet, decibelSet, selectedTime,
			selectedDecibel);

		// Step 3:
		double[][] calculatedActionVectors = calculateVolumeActionVectors(updatedRelationships, volumeSet);

		// Step 4:
		double[] calculatedSelectedVectors = calculateSelectedVectors(calculatedActionVectors);

		// Step 5:
		double calculatedVolume = calculateVolume(calculatedSelectedVectors, volumeSet);
		int integerVolume = (int) calculatedVolume;

		System.out.println("With commercials or ads occurring on average, every " + selectedTime + " seconds, and " +
			                   "the current decibel reading of " + selectedDecibel + ", the volume should be " +
			                   "adjusted to " + integerVolume + ".");

	}
}
