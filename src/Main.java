

import dataBase.IdCounter;
import moduls.Department;
import moduls.Doctor;
import moduls.Hospital;
import moduls.Patient;
import service.impls.*;
import enums.Gender;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            HospitalIMPL hospitalService = new HospitalIMPL();
            DepartmentIMPL departmentService = new DepartmentIMPL();
            DoctorIMPL doctorService = new DoctorIMPL();
            PatientIMPL patientService = new PatientIMPL();

            while (true) {
                try {
                    System.out.println("\n--- Hospital Management System ---");
                    System.out.println("1. Add Hospital");
                    System.out.println("2. Find Hospital by ID");
                    System.out.println("3. Get All Hospitals");
                    System.out.println("4. Delete Hospital by ID");
                    System.out.println("5. Get Hospitals by Address");
                    System.out.println("6. Add Department to Hospital");
                    System.out.println("7. Find Department by Name");
                    System.out.println("8. Get All Departments by Hospital ID");
                    System.out.println("9. Add Doctor to Hospital");
                    System.out.println("10. Find Doctor by ID");
                    System.out.println("11. Assign Doctors to Department");
                    System.out.println("12. Get All Doctors by Hospital ID");
                    System.out.println("13. Get All Doctors by Department ID");
                    System.out.println("14. Add Patient to Hospital");
                    System.out.println("15. Find Patient by ID");
                    System.out.println("16. Get Patients by Age");
                    System.out.println("17. Sort Patients by Age");
                    System.out.println("18. Remove Department by ID");
                    System.out.println("19. Remove Doctor by ID");
                    System.out.println("10. Remove Patient by ID");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            try {
                                Long hospitalId = IdCounter.hospitalId();
                                scanner.nextLine();
                                System.out.print("Enter hospital name: ");
                                String hospitalName = scanner.nextLine();
                                System.out.print("Enter hospital address: ");
                                String address = scanner.nextLine();
                                Hospital hospital = new Hospital(hospitalId, hospitalName, address);
                                System.out.println(hospitalService.addHospital(hospital));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                System.out.print("Enter hospital ID: ");
                                Long searchHospitalId = scanner.nextLong();
                                Hospital foundHospital = hospitalService.findHospitalById(searchHospitalId);
                                if (foundHospital != null) {
                                    System.out.println(foundHospital);

                                } else {
                                    System.out.println("Hospital not found");
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 3:
                            try {
                                System.out.println(hospitalService.getAllHospital());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 4:
                            try {
                                System.out.print("Enter hospital ID to delete: ");
                                Long deleteHospitalId = scanner.nextLong();
                                System.out.println(hospitalService.deleteHospitalById(deleteHospitalId));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 5:
                            try {
                                scanner.nextLine();
                                System.out.print("Enter address: ");
                                String searchAddress = scanner.nextLine();
                                System.out.println(hospitalService.getAllHospitalByAddress(searchAddress));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 6:
                            try {
                                Long departmentId = IdCounter.departmentId();
                                scanner.nextLine();
                                System.out.print("Enter department name: ");
                                String departmentName = scanner.nextLine();
                                Department department = new Department(departmentId, departmentName);
                                System.out.print("Enter hospital ID to add this department: ");
                                Long targetHospitalId = scanner.nextLong();
                                System.out.println(departmentService.add(targetHospitalId, department));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 7:
                            try{
                                scanner.nextLine();
                                System.out.print("Enter department name: ");
                                String searchDepartmentName = scanner.nextLine();
                                Department foundDepartment = departmentService.findDepartmentByName(searchDepartmentName);
                                if (foundDepartment != null) {
                                    System.out.println(foundDepartment);

                                } else {
                                    System.out.println("Department not found");
                                }} catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 8:
                            try{
                                System.out.print("Enter hospital ID: ");
                                Long hospitalIdForDepartments = scanner.nextLong();
                                System.out.println(departmentService.getAllDepartmentByHospital(hospitalIdForDepartments));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 9:
                            try{
                                Long doctorId = IdCounter.doctorId();
                                scanner.nextLine();
                                System.out.print("Enter doctor's first name: ");
                                String doctorFirstName = scanner.nextLine();
                                System.out.print("Enter doctor's last name: ");
                                String doctorLastName = scanner.nextLine();
                                System.out.print("Enter doctor's gender (MALE/FEMALE): ");
                                String genderInput = scanner.nextLine().toUpperCase();
                                Gender doctorGender = Gender.valueOf(genderInput);
                                System.out.print("Enter doctor's experience years: ");
                                int experienceYears = scanner.nextInt();
                                Doctor doctor = new Doctor(doctorId, doctorFirstName, doctorLastName, doctorGender, experienceYears);
                                System.out.print("Enter hospital ID to add this doctor: ");
                                Long hospitalIdForDoctor = scanner.nextLong();
                                System.out.println(doctorService.add(hospitalIdForDoctor, doctor));

                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введено не число. Попробуйте снова.");
                                scanner.nextLine();
                            }catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 10:
                            System.out.print("Enter doctor ID: ");
                            Long searchDoctorId = scanner.nextLong();
                            Doctor foundDoctor = doctorService.findDoctorById(searchDoctorId);
                            if (foundDoctor != null) {
                                System.out.println(foundDoctor);

                            } else {
                                System.out.println("Hospital not found");
                            }
                            break;

                        case 11:
                            System.out.print("Enter department ID: ");
                            Long departmentIdForAssignment = scanner.nextLong();
                            System.out.print("Enter doctor IDs (comma-separated): ");
                            scanner.nextLine();
                            String[] doctorIdsInput = scanner.nextLine().split(",");
                            List<Long> doctorIds = new ArrayList<>();
                            for (String id : doctorIdsInput) {
                                doctorIds.add(Long.parseLong(id.trim()));
                            }
                            System.out.println(doctorService.assignDoctorToDepartment(departmentIdForAssignment, doctorIds));
                            break;

                        case 12:
                            try{
                                System.out.print("Enter hospital ID: ");
                                Long hospitalIdForDoctors = scanner.nextLong();
                                List<Doctor> doctorsByHospital = doctorService.getAllDoctorsByHospitalId(hospitalIdForDoctors);
                                doctorsByHospital.forEach(System.out::println);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 13:
                            try{
                                System.out.print("Enter department ID: ");
                                Long departmentIdForDoctors = scanner.nextLong();
                                System.out.println(doctorService.getAllDoctorsByDepartmentId(departmentIdForDoctors));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 14:
                            try{
                                Long patientId = IdCounter.patientId();
                                scanner.nextLine();
                                System.out.print("Enter patient's first name: ");
                                String patientFirstName = scanner.nextLine();
                                System.out.print("Enter patient's last name: ");
                                String patientLastName = scanner.nextLine();
                                System.out.print("Enter patient's age: ");
                                int patientAge = scanner.nextInt();
                                System.out.print("Enter patient's gender (MALE/FEMALE): ");
                                scanner.nextLine();
                                String patientGenderInput = scanner.nextLine().toUpperCase();
                                Gender patientGender = Gender.valueOf(patientGenderInput);
                                Patient patient = new Patient(patientId, patientFirstName, patientLastName, patientAge, patientGender);
                                System.out.print("Enter hospital ID to add this patient: ");
                                Long hospitalIdForPatient = scanner.nextLong();
                                System.out.println(patientService.add(hospitalIdForPatient, patient));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 15:
                            try {
                                System.out.print("Enter patient ID: ");
                                Long searchPatientId = scanner.nextLong();
                                System.out.println(patientService.getPatientById(searchPatientId));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 16:
                            try{
                                System.out.print("Enter patient age: ");
                                int searchAge = scanner.nextInt();
                                System.out.println(patientService.getPatientByAge(searchAge));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 17:
                            try{
                                scanner.nextLine();
                                System.out.print("Sort by (asc/desc): ");
                                String sortOrder = scanner.nextLine();
                                System.out.println(patientService.sortPatientsByAge(sortOrder));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 18:
                            try{
                                System.out.print("Enter department ID to remove: ");
                                Long removeDepartmentId = scanner.nextLong();
                                departmentService.removeById(removeDepartmentId);
                                System.out.println("Department removed.");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 19:
                            try{
                                System.out.print("Enter doctor ID to remove: ");
                                Long removeDoctorId = scanner.nextLong();
                                doctorService.removeById(removeDoctorId);
                                System.out.println("Doctor removed.");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 20:
                            try{
                                System.out.print("Enter patient ID to remove: ");
                                Long removePatientId = scanner.nextLong();
                                patientService.removeById(removePatientId);
                                System.out.println("Patient removed.");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        default:
                            System.out.println("Invalid option. Try again.");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
