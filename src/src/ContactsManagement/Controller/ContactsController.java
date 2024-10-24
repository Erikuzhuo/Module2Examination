package ContactsManagement.Controller;

import ContactsManagement.Support.checkDataIsExist;
import ContactsManagement.Model.Person;
import ContactsManagement.Pattern.PatternFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ContactsController {
    static Scanner scanner = new Scanner(System.in);

    public static void createNewContact(List<Person> listOfContact) {
        Person[] tempPersonList;
        boolean check = true;
        String confirm, tempName, tempGender, tempBirthDate, tempGroup, tempNumber, tempEmail, tempAdress;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate today = LocalDate.now(), minBirthDate = LocalDate.of(1940, 1, 1);


        do {
            System.out.println("How many new contacts? ");
            int newAddNumber = Integer.parseInt(scanner.nextLine());
            do {
                try {
                    if (newAddNumber < 0) {
                        System.out.println("Please re-input number of new contacts: ");
                        newAddNumber = Integer.parseInt(scanner.nextLine());
                        check = false;
                    } else check = true;
                } catch (NumberFormatException e) {
                    System.out.println("Number must input correctly.");
                    check = false;
                }
            } while (!check);

            // vòng lặp nhận thông tin cho từng đối tượng
            tempPersonList = new Person[newAddNumber];
            for (int i = 0; i < tempPersonList.length; i++) {
                Person contactPerson = new Person();

                // input phone number
                do {
                    System.out.println("Input 10 digits phone number of staff number " + (i + 1) + " starting by 0:");
                    tempNumber = scanner.nextLine();
                    if (!PatternFormat.checkPhoneNumber(tempNumber)) {
                        System.out.println("Number format not correct. Kindly re-input.");
                        check = false;
                    } else if (!listOfContact.isEmpty()) {
                        if (checkDataIsExist.checkNumberExist(listOfContact, tempNumber)
                                || checkDataIsExist.checkNumberExist(tempPersonList, tempNumber)) {
                            check = false;
                        } else check = true;
                    }
                } while (!check);
                contactPerson.setPhoneNumber(tempNumber);

                // input name
                do {
                    System.out.println("Input name of contact " + (i + 1) + ":");
                    tempName = scanner.nextLine();
                    if (Objects.equals(tempName, "") || !PatternFormat.namePattern(tempName)) {
                        check = false;
                    } else check = true;
                } while (!check);
                contactPerson.setName(tempName);

                // input gender
                do {
                    System.out.println("Input gender of contact " + (i + 1) + ": (M/F)");
                    tempGender = scanner.nextLine();
                    if (!tempGender.equals("M") && !tempGender.equals("F")) {
                        check = false;
                        System.out.println("Invalid input. Kindly re-input.");
                    } else check = true;
                } while (!check);
                contactPerson.setGender(tempGender);

                // input BD
                do {
                    System.out.println("Input birth date of contact " + (i + 1) + ": (YYYY/MM/DD)");
                    tempBirthDate = scanner.nextLine();
                    try {
                        LocalDate parsedDate = LocalDate.parse(tempBirthDate, dateTimeFormatter);

                        if (parsedDate.isBefore(minBirthDate)) {
                            System.out.println("Date is too early. Only after 1940.");
                            check = false;
                        } else if (parsedDate.isAfter(today)) {
                            System.out.println("Date cannot be later than today.");
                            check = false;
                        } else {
                            check = true;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Date format not correct. Kindly re-input.");
                        check = false;
                    }
                } while (!check);
                contactPerson.setBirthDate(LocalDate.parse(tempBirthDate, dateTimeFormatter));

                // input address
                do {
                    System.out.println("Input address of contact " + (i + 1) + ":");
                    tempAdress = scanner.nextLine();
                    if (Objects.equals(tempAdress, "")) {
                        check = false;
                    } else check = true;
                } while (!check);
                contactPerson.setAddress(scanner.nextLine());

                // input group
                do {
                    System.out.println("Input group of contact " + (i + 1) + ":");
                    tempGroup = scanner.nextLine();
                    if (Objects.equals(tempGroup, "")) {
                        check = false;
                    } else check = true;
                } while (!check);
                contactPerson.setGroupNumber(scanner.nextLine());

                // input email
                do {
                    System.out.println("Input email of contact " + (i + 1) + ":");
                    tempEmail = scanner.nextLine();
                    if (!PatternFormat.checkEmail(tempEmail)) {
                        System.out.println("Email format not correct. Kindly re-input.");
                        check = false;
                    } else check = true;
                } while (!check);
                contactPerson.setEmail(tempEmail);

                tempPersonList[i] = contactPerson;
            }
            // confirm
            System.out.println("Did you confirm to add on below contacts list: (Y/N) \n" +
                    Arrays.toString(tempPersonList));
            confirm = scanner.nextLine();

        } while (!confirm.equalsIgnoreCase("Y"));

        listOfContact.addAll(Arrays.asList(tempPersonList));
        System.out.println("Add on Successfully.");
    }

    public static void displayListOfContact(List<Person> listOfContact) {
        for (Person staff : listOfContact) {
            System.out.println(staff.toString());
        }
    }

    public static void deleteContact(List<Person> listOfContact) {
        String confirm, tempNumber;
        Person deleteContact = null;
        boolean check = true;
        do {
            do {
                System.out.println("Input delete number: ");
                tempNumber = scanner.nextLine();
                while (tempNumber.isEmpty() || !checkDataIsExist.checkNumberExist(listOfContact, tempNumber)) {
                    System.out.println("Invalid number. Please re-input. | Or input 0 to exit.");
                    tempNumber = scanner.nextLine();
                    if (tempNumber.equals("0")) break;
                }
                if (tempNumber.equals("0")) break;

                for (Person person : listOfContact) {
                    if (Objects.equals(person.getPhoneNumber(), tempNumber)) {
                        deleteContact = person;
                        break;
                    }
                }
                System.out.println("Are you sure want to delete this staff: (Y/N) \n"
                        + (deleteContact != null ? deleteContact.toString() : null));
                confirm = scanner.nextLine();

            } while (!confirm.equalsIgnoreCase("Y"));
            if (tempNumber.equals("0")) {
                System.out.println("Exit and cancelled delete.");
            } else {
                listOfContact.remove(deleteContact);
                System.out.println("Staff deleted successfully.");
            }
            System.out.println("Do you still want to delete other contactf: (Y/N)");
            confirm = scanner.nextLine();
        } while (!confirm.equalsIgnoreCase("N"));
    }

    public static void updateContact(List<Person> listOfContact) {

        String confirm, checkNumber, tempBirthDate, tempGender, tempGroup, tempEmail, tempNumber, tempAdress, tempName;
        int option, index = 0;
        boolean check = true, continueEditing = true;
        Person updateContact = null;
        LocalDate today = LocalDate.now(), minBirthDate = LocalDate.of(1940, 1, 1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        do {
            if (!listOfContact.isEmpty()) {
                System.out.println("Input contact phone number that need update: ");
                checkNumber = scanner.nextLine();
                while (checkNumber.isEmpty() || !checkDataIsExist.checkNumberExist(listOfContact, checkNumber)) {
                    System.out.println("Invalid number. Please re-input. | Or input 0 to exit.");
                    checkNumber = scanner.nextLine();
                    if (Objects.equals(checkNumber, "0")) break;
                }
                if (checkNumber.equals("0")) break;

                for (Person person : listOfContact) {
                    if (Objects.equals(person.getPhoneNumber(), checkNumber)) {
                        updateContact = person;
                        index = listOfContact.indexOf(person);
                        break;
                    }
                }

                do {
                    System.out.println("What's information number you want to update: \n" +
                            "1. Name.\n" +
                            "2. Birthday.\n" +
                            "3. Gender.\n" +
                            "4. address.\n" +
                            "5. Group of phone number. \n" +
                            "6. Email.\n " +
                            "7. exit.");

                    option = Integer.parseInt(scanner.nextLine());
                    switch (option) {
                        case 1:
                            do {
                                System.out.println("Input new name of " + updateContact.getPhoneNumber());
                                updateContact.setName(scanner.nextLine());
                                tempName = scanner.nextLine();
                                if (Objects.equals(tempName, "") || !PatternFormat.namePattern(tempName)) {
                                    check = false;
                                } else check = true;
                            } while (!check);
                            updateContact.setName(tempName);
                            break;
                        case 2:
                            do {
                                System.out.println("Input new BD of " + updateContact.getPhoneNumber());
                                tempBirthDate = scanner.nextLine();
                                try {
                                    LocalDate parsedDate = LocalDate.parse(tempBirthDate, dateTimeFormatter);

                                    if (parsedDate.isBefore(minBirthDate)) {
                                        System.out.println("Date is too early. Only after 1940.");
                                        check = false;
                                    } else if (parsedDate.isAfter(today)) {
                                        System.out.println("Date cannot be later than today.");
                                        check = false;
                                    } else {
                                        check = true;
                                    }
                                } catch (DateTimeParseException e) {
                                    System.out.println("Date format not correct. Kindly re-input.");
                                    check = false;
                                }
                            } while (!check);
                            updateContact.setBirthDate(LocalDate.parse(tempBirthDate, dateTimeFormatter));
                            break;
                        case 3:
                            do {
                                System.out.println("Input gender of " + updateContact.getPhoneNumber() + ": (M/F)");
                                tempGender = scanner.nextLine();
                                if (!tempGender.equals("M") && !tempGender.equals("F")) {
                                    check = false;
                                    System.out.println("Invalid input. Kindly re-input.");
                                } else check = true;
                            } while (!check);
                            updateContact.setGender(tempGender);
                            break;
                        case 4:
                            do {
                                System.out.println("Input new address of " + updateContact.getPhoneNumber());
                                tempAdress = scanner.nextLine();
                                if (Objects.equals(tempAdress, "")) {
                                    check = false;
                                } else check = true;
                            } while (!check);
                            updateContact.setAddress(scanner.nextLine());
                            break;
                        case 5:
                            do {
                                System.out.println("Input new position of " + updateContact.getPhoneNumber());
                                tempGroup = scanner.nextLine();
                                if (Objects.equals(tempGroup, "")) {
                                    check = false;
                                } else check = true;
                            } while (!check);
                            updateContact.setGroupNumber(scanner.nextLine());
                            break;
                        case 6:
                            do {
                                System.out.println("Input new email of " + updateContact.getPhoneNumber());
                                tempEmail = scanner.nextLine();
                                if (!PatternFormat.checkEmail(tempEmail)) {
                                    System.out.println("Email format not correct. Kindly re-input.");
                                    check = false;
                                } else check = true;
                            } while (!check);
                            updateContact.setEmail(tempEmail);
                            break;
                        case 7:
                            continueEditing = false;
                            break;

                    }
                    if (!continueEditing) break;
                    else {
                        System.out.println("New update as below. Do you still want to update other information? (Y/N)\n"
                                + updateContact.toString());
                        confirm = scanner.nextLine();
                    }
                } while (!confirm.equalsIgnoreCase("Y"));

                if (!continueEditing) break;
                else {
                    System.out.println("Update completed. Do you want update other contact? (Y/N)");
                    confirm = scanner.nextLine();
                }
            } else {
                System.out.println("List is empty.");
                continueEditing = false;
                break;
            }
        } while (!confirm.equalsIgnoreCase("N"));
        if (!continueEditing) {
            System.out.println("Cancelled Editing.");
        } else {
            listOfContact.set(index, updateContact);
            System.out.println("Update completed.");
        }
    }

    public static void findElement(List<Person> listOfContact) {
        String checkNumber;
        boolean check = true;
        String confirm = null;

        do {
            do {
                System.out.println("Input contact phone number that need to find: ");
                checkNumber = scanner.nextLine();
                while (checkNumber.isEmpty() || !checkDataIsExist.checkNumberExist(listOfContact, checkNumber)) {
                    System.out.println("Invalid number. Please re-input. | Or input 0 to exit.");
                    checkNumber = scanner.nextLine();
                    if (Objects.equals(checkNumber, "0")) break;
                }
                if (Objects.equals(checkNumber, "0")) {
                    System.out.println("Cancelled Find");
                    break;
                }

                for (Person person : listOfContact) {
                    if (Objects.equals(person.getPhoneNumber(), checkNumber)) {
                        System.out.println("The contact you find is: \n" + person);
                        break;
                    }
                }
            } while (!check);
            System.out.println("Find completed. Do you want find other contact? (Y/N)");
            confirm = scanner.nextLine();
        } while (!confirm.equalsIgnoreCase("N"));
    }
}
