package com.andyp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestOS {

	private String[] fnames;
	private String[] lnames;
	private String[] dates;
	private String[] nextOfKin;
	private String[] doctors;
	private String[] diags;
	
	private Random rand = new Random();
	
	public static void main(String a[]) throws IOException{
		
		new TestOS().run();
		
	}

	public void run(){
		int numPatients = 100;
		fnames = genFirstNames();
		lnames = genLastNames();
		dates = genDates();
		nextOfKin = genNextOfKin(numPatients);
		doctors = genMockDoctors();
		diags = genDiagnosises();
		List<PatientInfo> patients = genPatientRecords(numPatients);
		
		writeToPatientsFile(patients, "C:\\Users\\Andy.Paladino\\Desktop\\patients.csv");
		
		List<PatientHistory> history = genPatientHistory(patients, 10);
		
		writeToHistoryFile(history, "C:\\Users\\Andy.Paladino\\Desktop\\history.csv");
	}
	
	private void writeToHistoryFile(List<PatientHistory> history, String fileName) {
		try {
			PrintWriter writer = new PrintWriter(fileName);
			writer.write("PHID,PID,DOCTORS,SYMPTOMS,DIAGNOSIS,PRESCRIPTIONS,STATUS\r");
			
			for(PatientHistory ph : history){
				writer.write(ph + "\r");
				writer.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String[] genDiagnosises() {
		String diags = "Accute something-itis,Fracture of funny bone,stubbed toe,bruised pinky,cauliflower ear";
		
		return diags.split(",");
	}

	private List<PatientHistory> genPatientHistory(List<PatientInfo> patients, int numRecordsPerPatient) {
		List<PatientHistory> hist = new ArrayList<>();
		
		for(int i=0; i < patients.size(); i++){
			PatientInfo p = patients.get(i);
			int num = rand.nextInt(numRecordsPerPatient);
			for(int j=0; j < num; j++){
				PatientHistory ph = new PatientHistory();
				ph.phid = "PHID-" + (1000 + i +j);
				ph.pid = p.pid;
				ph.diagnosis = diags[rand.nextInt(diags.length)];
				ph.doctors = doctors[rand.nextInt(doctors.length)];
				ph.prescription = (i+j) % 2 == 0 ? "Take two asperins and call me in the morning" : "Four hours of Netflix and Orange Juice";
				ph.status = "A,D,C".split(",")[rand.nextInt(3)];
				ph.symptoms = genSymptoms();
				
				hist.add(ph);
			}
		}
		return hist;
	}

	private String genSymptoms() {
		String s = "Unable to sleep,Attention deficiencies,confusion,sleep walking,loss of appitite";
		String [] symps = s.split(",");
		return symps[rand.nextInt(symps.length)];
	}

	private String[] genMockDoctors() {
		String docs = "Dr. Leonard \"Bones\" McCoy,Dr. Chris Turk, Dr. Amos \"Doc\" Cochran,Dr. Douglas \"Doogie\" Houser,Dr. Micheala Quinn,"
				+ "Dr. Sherman Cottle,Dr. Truman Carter III,Dr. Dana Scully,Dr. Benjamin Franklin \"Hawkeye\" Peirce";

		return docs.split(",");
	}

	private void writeToPatientsFile(List<PatientInfo> patients, String fileName) {
		
		try {
			PrintWriter writer = new PrintWriter(fileName);
			//PID-0,Dave,,BARNES,555-111-8000,M,Married,04/03/1974,0 someplace lane San Francisco CA,Darth Vader,800-555-1212,04/03/1974
			writer.write("PID, FIRSTNAME, MIDDLENAME, LASTNAME, PHONE, GENDER, MARITAL STATUS, D.O.B, RESIDENCE, NEXT_OF_KIN, NEXT_OF_KIN_CONTACTS, DATE_OF_REGISTRATION\n");
			writer.flush();
			for(PatientInfo p : patients){
				writer.write(p + "\r");
			}
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String[] genNextOfKin(int n) {
		String nok = "Luke Skywalker,Darth Vader,Homer Simpson,Marge Simpson,Flash Gordan,Kilo Ren," + 
					"Ben Kenobi,Chewbacca,Captain Kirk,Spock,General Aakbar,Lando Calrisian";
		String[] kin = nok.split(",");
		String [] nextKin = new String[n];
		for(int i=0; i < n; i++){
			nextKin[i] = kin[rand.nextInt(kin.length)];
		}
		return nextKin;
	}

	private String[] genDates() {
		String dates = "01/01/1971,04/03/1974,12/23/1968,11/14/2001,06/25/1994,04/04/1980,05/16/1982";
		
		return dates.split(",");
	}

	private List<PatientInfo> genPatientRecords(int n) {
		List<PatientInfo> patients = new ArrayList<>();
		
		for(int i=0; i < n; i++){
			PatientInfo p = new PatientInfo();
			
			p.pid = "PID-" + i;
			p.firstName = getRandomFirstName();
			p.lastName = getRandomLastName();
			p.gender = (i % 2 == 0) ? "M" : "F";
			p.maritalStatus = (i % 10 == 0) ? "Married" : "Single";
			p.middleName = "";
			p.nextOfKin = nextOfKin[i];
			p.nextOfKinContacts = "800-555-1212";
			p.phone = "555-111-8000";
			p.dob = getRandomDate();
			p.dateOfRegistration = getRandomDate();
			p.residence = String.format("%s someplace lane San Francisco CA", i);
			
			patients.add(p);
		}
		
		return patients;
	}


	private String getRandomDate() {
		return dates[rand.nextInt(dates.length - 1)];
	}

	private String getRandomLastName() {
		int i = rand.nextInt(lnames.length - 1);
		return lnames[i];
	}

	private String getRandomFirstName() {
		int i = rand.nextInt(fnames.length - 1);
		return fnames[i];
	}


	class PatientHistory{
		String phid,
			   pid,
			   doctors,
			   symptoms,
			   diagnosis,
			   prescription,
			   status;
		
		@Override
		public String toString(){
			return String.format("%s,%s,%s,%s,%s,%s,%s", phid, pid, doctors, symptoms, diagnosis, prescription, status);
		}
	}
	
	class PatientInfo{

		String pid,
			   firstName,
			   middleName,
			   lastName,
			   phone,
			   gender,
			   maritalStatus,
			   dob,
			   nextOfKin,
			   nextOfKinContacts,
			   dateOfRegistration,
			   residence;
		
		@Override
		public String toString(){
			/*
			 * PID, FIRSTNAME, MIDDLENAME,
			 *  LASTNAME, PHONE, GENDER, MARITAL STATUS, 
			 * D.O.B, RESIDENCE, NEXT_OF_KIN, NEXT_OF_KIN_CONTACTS, DATE_OF_REGISTRATION

			 */
			return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
					pid,
					firstName,
					middleName,
					lastName,
					phone,
					gender,
					maritalStatus,
					dob,
					residence,
					nextOfKin,
					nextOfKinContacts,
					dateOfRegistration);
		}
		public PatientInfo(){
			
		}
	}
	
	private String[] genLastNames() {
		String names = "SMITH,JOHNSON,WILLIAMS,BROWN,JONES,MILLER,DAVIS,GARCIA,RODRIGUEZ,"
				+ "WILSON,MARTINEZ,ANDERSON,TAYLOR,THOMAS,HERNANDEZ,MOORE,MARTIN,JACKSON,THOMPSON,"
				+ "WHITE,LOPEZ,LEE,GONZALEZ,HARRIS,CLARK,LEWIS,ROBINSON,WALKER,PEREZ,HALL,"
				+ "YOUNG,ALLEN,SANCHEZ,WRIGHT,KING,SCOTT,GREEN,BAKER,ADAMS,NELSON,HILL,RAMIREZ,"
				+ "CAMPBELL,MITCHELL,ROBERTS,CARTER,PHILLIPS,EVANS,TURNER,TORRES,PARKER,COLLINS,EDWARDS,STEWART,FLORES,MORRIS,NGUYEN,MURPHY,RIVERA,COOK,ROGERS,MORGAN,PETERSON,COOPER,REED,BAILEY,BELL,GOMEZ,KELLY,HOWARD,WARD,COX,DIAZ,RICHARDSON,WOOD,WATSON,BROOKS,BENNETT,GRAY,JAMES,REYES,CRUZ,HUGHES,PRICE,MYERS,LONG,FOSTER,SANDERS,ROSS,MORALES,POWELL,SULLIVAN,RUSSELL,ORTIZ,JENKINS,GUTIERREZ,PERRY,BUTLER,BARNES,FISHER,HENDERSON,COLEMAN,SIMMONS,PATTERSON,JORDAN";
		
		return names.split(",");
	}

	private String[] genFirstNames() {
		String  names = 
				"Andy,Alex,Angie,Alice,Bob,Barbara,Colleen," + 
				 "Cindy,Carl,Dave,Doris,Eunice,Frank,Gerry,Ginger,Harry,Henry,Ike,Ilsa,"
				 + "Jerry,Jill,July,Kramer,Louis,Lana,Mike,Mildred,Nancy";
		
		return names.split(",");
	}
}
