#include <fstream>
#include <iostream>
#include <string>

using namespace std;


string searchDirectory(string studentID) {
	ifstream inputFile;

	inputFile.open("students.txt");

	if (!inputFile) {
		cout << "Unable to open file" << endl;
		system("pause");
		exit(1);
	}

	string line;
	int pos;
	while (inputFile.good()) {
		getline(inputFile, line);
		pos = line.find(studentID);
		if (pos != string::npos) {
			return line;
			break;
		}
	}
	return "Student not found";
}

void addStudent() {
	//ofstream outputFile;

	string student;
	string inpt;
	string newFile;
	cout << "Enter Student ID" << endl;
	getline(cin, inpt);
	newFile = inpt;
	student += inpt;
	student += "$";
	cout << "Enter first name: ";
	getline(cin, inpt);
	student += inpt;
	student += "$";
	cout << "Enter last name: ";
	getline(cin, inpt);
	student += inpt;
	student += "$";
	cout << student;

	newFile += ".txt";
	//outputFile.open(newFile);
	//outputFile <<
}