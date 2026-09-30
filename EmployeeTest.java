package demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Employee employee = new Employee();
        Attendance attendance = new Attendance();
        Salary salary = new Salary();

        int choice;
        String fileName = "employee_payroll.txt";

        do {
            System.out.println("\n===== EMPLOYEE PAYROLL MANAGEMENT =====");
            System.out.println("1. Enter Employee Details");
            System.out.println("2. Calculate Attendance");
            System.out.println("3. Calculate Salary");
            System.out.println("4. Display Employee Details");
            System.out.println("5. Save Employee Details to File");
            System.out.println("6. Read Employee Details from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Employee ID: ");
                    employee.employeeId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Employee Name: ");
                    employee.employeeName = sc.nextLine();

                    System.out.println("\nSelect Department:");
                    System.out.println("1. IT");
                    System.out.println("2. HR");
                    System.out.println("3. Finance");
                    System.out.println("4. Marketing");
                    System.out.print("Enter department choice: ");

                    employee.departmentChoice = sc.nextInt();

                    switch (employee.departmentChoice) {
                        case 1:
                            employee.department = "IT";
                            break;

                        case 2:
                            employee.department = "HR";
                            break;

                        case 3:
                            employee.department = "Finance";
                            break;

                        case 4:
                            employee.department = "Marketing";
                            break;

                        default:
                            employee.department = "Unknown";
                            System.out.println("Invalid department choice.");
                    }

                    System.out.print("Enter Basic Salary: ");
                    employee.basicSalary = sc.nextDouble();

                    if (employee.basicSalary <= 0) {
                        System.out.println("Salary must be greater than 0.");
                    } else {
                        System.out.println("Employee details entered successfully.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Total Working Days: ");
                    attendance.totalWorkingDays = sc.nextInt();

                    if (attendance.totalWorkingDays <= 0) {
                        System.out.println("Working days must be greater than 0.");
                        break;
                    }

                    attendance.presentDays = 0;
                    attendance.absentDays = 0;

                    System.out.println(
                            "Enter attendance for each day (1 = Present, 0 = Absent):");

                    for (int i = 1; i <= attendance.totalWorkingDays; i++) {

                        System.out.print("Day " + i + ": ");
                        attendance.attendance = sc.nextInt();

                        if (attendance.attendance == 1) {
                            attendance.presentDays++;
                        } else if (attendance.attendance == 0) {
                            attendance.absentDays++;
                        } else {
                            System.out.println(
                                    "Invalid input. Enter 1 for Present or 0 for Absent.");
                            i--;
                        }
                    }

                    attendance.attendancePercentage =
                            ((double) attendance.presentDays
                            / attendance.totalWorkingDays) * 100;

                    System.out.println(
                            "Present Days: " + attendance.presentDays);
                    System.out.println(
                            "Absent Days: " + attendance.absentDays);
                    System.out.println(
                            "Attendance Percentage: "
                            + attendance.attendancePercentage + "%");

                    if (attendance.attendancePercentage >= 75) {
                        System.out.println("Attendance Status: Eligible");
                    } else {
                        System.out.println("Attendance Status: Not Eligible");
                    }
                    break;

                case 3:
                    if (employee.basicSalary <= 0) {
                        System.out.println(
                                "Please enter valid employee details first.");
                        break;
                    }

                    if (attendance.totalWorkingDays <= 0) {
                        System.out.println(
                                "Please calculate attendance first.");
                        break;
                    }

                    if (attendance.attendancePercentage >= 90) {

                        salary.incentive = employee.basicSalary * 0.10;
                        salary.deduction = 0;

                        System.out.println(
                                "Attendance is 90% or above.");
                        System.out.println(
                                "10% incentive added.");

                    } else if (attendance.attendancePercentage >= 75) {

                        salary.incentive = 0;
                        salary.deduction = 0;

                        System.out.println(
                                "Attendance is between 75% and 89%.");
                        System.out.println(
                                "No incentive or deduction.");

                    } else {

                        salary.incentive = 0;
                        salary.deduction = employee.basicSalary * 0.10;

                        System.out.println(
                                "Attendance is below 75%.");
                        System.out.println(
                                "10% deduction applied.");
                    }

                    salary.finalSalary =
                            employee.basicSalary
                            + salary.incentive
                            - salary.deduction;

                    System.out.println(
                            "Final Salary: " + salary.finalSalary);
                    break;

                case 4:
                    if (employee.employeeId == 0) {
                        System.out.println(
                                "Please enter employee details first.");
                    } else {
                        System.out.println("\n===== EMPLOYEE DETAILS =====");
                        System.out.println(
                                "Employee ID: " + employee.employeeId);
                        System.out.println(
                                "Employee Name: " + employee.employeeName);
                        System.out.println(
                                "Department: " + employee.department);
                        System.out.println(
                                "Basic Salary: " + employee.basicSalary);
                        System.out.println(
                                "Total Working Days: "
                                + attendance.totalWorkingDays);
                        System.out.println(
                                "Present Days: "
                                + attendance.presentDays);
                        System.out.println(
                                "Absent Days: "
                                + attendance.absentDays);
                        System.out.println(
                                "Attendance Percentage: "
                                + attendance.attendancePercentage);
                        System.out.println(
                                "Final Salary: " + salary.finalSalary);
                    }
                    break;

                case 5:
                    try {
                        FileWriter fw = new FileWriter(fileName);
                        PrintWriter pw = new PrintWriter(fw);

                        pw.println("EMPLOYEE PAYROLL DETAILS");
                        pw.println("========================");
                        pw.println("Employee ID: " + employee.employeeId);
                        pw.println("Employee Name: "
                                + employee.employeeName);
                        pw.println("Department: " + employee.department);
                        pw.println("Basic Salary: "
                                + employee.basicSalary);
                        pw.println("Total Working Days: "
                                + attendance.totalWorkingDays);
                        pw.println("Present Days: "
                                + attendance.presentDays);
                        pw.println("Absent Days: "
                                + attendance.absentDays);
                        pw.println("Attendance Percentage: "
                                + attendance.attendancePercentage);
                        pw.println("Final Salary: "
                                + salary.finalSalary);

                        pw.close();

                        System.out.println(
                                "Employee details saved successfully.");
                        System.out.println(
                                "File: " + fileName);

                    } catch (IOException e) {
                        System.out.println(
                                "Error writing to file: " + e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        FileReader fr = new FileReader(fileName);
                        BufferedReader br = new BufferedReader(fr);

                        String line;

                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }

                        br.close();

                    } catch (IOException e) {
                        System.out.println(
                                "Error reading file. Please save employee details first.");
                    }
                    break;

                case 7:
                    System.out.println(
                            "Thank you for using Employee Payroll Management.");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please enter a choice from 1 to 7.");
            }

        } while (choice != 7);

        sc.close();
    }
}