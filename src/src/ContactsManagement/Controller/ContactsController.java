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
            System.out.println("Thêm mới bao nhiêu số điện thoại? ");
            int newAddNumber = Integer.parseInt(scanner.nextLine());
            do {
                try {
                    if (newAddNumber < 0) {
                        System.out.println("Mời nhập lại số lượng: ");
                        newAddNumber = Integer.parseInt(scanner.nextLine());
                        check = false;
                    } else check = true;
                } catch (NumberFormatException e) {
                    System.out.println("Số lượng cần nhập đúng.");
                    check = false;
                }
            } while (!check);

            // vòng lặp nhận thông tin cho từng đối tượng
            tempPersonList = new Person[newAddNumber];
            for (int i = 0; i < tempPersonList.length; i++) {
                Person contactPerson = new Person();

                // input phone number
                do {
                    System.out.println("Xin mời nhập số đt của người thứ " + (i + 1) + " bắt đầu bằng số 0 với 10 số:");
                    tempNumber = scanner.nextLine();
                    if (!PatternFormat.checkPhoneNumber(tempNumber)) {
                        System.out.println("Xin mời nhập đúng yêu cầu.");
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
                    System.out.println("mời nhập tên của người " + (i + 1) + ":");
                    tempName = scanner.nextLine();
                    if (Objects.equals(tempName, "") || !PatternFormat.namePattern(tempName)) {
                        System.out.println("Xin mời nhập đúng tên.");
                        check = false;
                    } else check = true;
                } while (!check);
                contactPerson.setName(tempName);

                // input gender
                do {
                    System.out.println("Mời nhập giới tính của người " + (i + 1) + ": (M/F)");
                    tempGender = scanner.nextLine();
                    if (!tempGender.equals("M") && !tempGender.equals("F")) {
                        check = false;
                        System.out.println("Mời nhập đúng theo yêu cầu.");
                    } else check = true;
                } while (!check);
                contactPerson.setGender(tempGender);

                // input BD
                do {
                    System.out.println("Mời nhập ngày sinh của người " + (i + 1) + ": (YYYY/MM/DD)");
                    tempBirthDate = scanner.nextLine();
                    try {
                        LocalDate parsedDate = LocalDate.parse(tempBirthDate, dateTimeFormatter);

                        if (parsedDate.isBefore(minBirthDate)) {
                            System.out.println("Năm sinh sớm nhất là 1940 ");
                            check = false;
                        } else if (parsedDate.isAfter(today)) {
                            System.out.println("Không thể trể hơn hôm nay.");
                            check = false;
                        } else {
                            check = true;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Mời nhập đúng mặc định.");
                        check = false;
                    }
                } while (!check);
                contactPerson.setBirthDate(LocalDate.parse(tempBirthDate, dateTimeFormatter));

                // input address
                do {
                    System.out.println("mời  nhập địa chỉ người thứ " + (i + 1) + ":");
                    tempAdress = scanner.nextLine();
                    if (Objects.equals(tempAdress, "")) {
                        System.out.println("Xin mời nhập đúng địa chỉ.");
                        check = false;
                    } else check = true;
                } while (!check);
                contactPerson.setAddress(scanner.nextLine());

                // input group
                do {
                    System.out.println("Nhập tên nhóm số đt " + (i + 1) + ":");
                    tempGroup = scanner.nextLine();
                    if (Objects.equals(tempGroup, "")) {
                        System.out.println("Xin mời nhập đúng tên nhóm.");
                        check = false;
                    } else check = true;
                } while (!check);
                contactPerson.setGroupNumber(scanner.nextLine());

                // input email
                do {
                    System.out.println("Mời nhập địa chỉ email " + (i + 1) + ":");
                    tempEmail = scanner.nextLine();
                    if (!PatternFormat.checkEmail(tempEmail)) {
                        System.out.println("Email không đúng, mời nhập lại..");
                        check = false;
                    } else check = true;
                } while (!check);
                contactPerson.setEmail(tempEmail);

                tempPersonList[i] = contactPerson;
            }
            // confirm
            System.out.println("Bạn có chắc lưu danh sách dưới đây vào bộ nhớ: (Y/N) \n" +
                    Arrays.toString(tempPersonList));
            confirm = scanner.nextLine();

        } while (!confirm.equalsIgnoreCase("Y"));

        listOfContact.addAll(Arrays.asList(tempPersonList));
        System.out.println("Lưu thành công.");
    }

    public static void displayListOfContact(List<Person> listOfContact) {
        System.out.println("Danh sách danh bạ trong bộ nhớ: \n");
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
                System.out.println("Nhập số đt cần xóa: ");
                tempNumber = scanner.nextLine();
                while (tempNumber.isEmpty() || !checkDataIsExist.checkNumberExist(listOfContact, tempNumber)) {
                    System.out.println("Không đúng mời nhập lại | hoặc nhập 0 để thoát.");
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
                System.out.println("Bạn có chắc xóa danh bạ này? (Y/N) \n"
                        + (deleteContact != null ? deleteContact.toString() : null));
                confirm = scanner.nextLine();

            } while (!confirm.equalsIgnoreCase("Y"));
            if (tempNumber.equals("0")) {
                System.out.println("Thoát.");
            } else {
                listOfContact.remove(deleteContact);
                System.out.println("Đã xóa. Danh bạ hiện tại. \n");
                for (Person staff : listOfContact) {
                    System.out.println(staff.toString());
                }
            }
            System.out.println("Bạn có muốn xóa thêm? (Y/N)");
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
                System.out.println("Nhập số đt cần cập nhật: ");
                checkNumber = scanner.nextLine();
                while (checkNumber.isEmpty() || !checkDataIsExist.checkNumberExist(listOfContact, checkNumber)) {
                    System.out.println("Không đúng mời nhập lại | hoặc nhập 0 để thoát.");
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
                    System.out.println("Bạn muốn cập nhật gì: \n" +
                            "1. Tên.\n" +
                            "2. Ngày Sinh.\n" +
                            "3. Giới Tính.\n" +
                            "4. Đại Chỉ.\n" +
                            "5. Nhóm Đt. \n" +
                            "6. Email.\n " +
                            "7. Thoát.");

                    option = Integer.parseInt(scanner.nextLine());
                    switch (option) {
                        case 1:
                            do {
                                System.out.println("Nhập tên mới của " + updateContact.getPhoneNumber());
                                updateContact.setName(scanner.nextLine());
                                tempName = scanner.nextLine();
                                if (Objects.equals(tempName, "") || !PatternFormat.namePattern(tempName)) {
                                    System.out.println("Mời nhập đúng tên.");
                                    check = false;
                                } else check = true;
                            } while (!check);
                            updateContact.setName(tempName);
                            break;
                        case 2:
                            do {
                                System.out.println("Ngày sinh mới của " + updateContact.getPhoneNumber());
                                tempBirthDate = scanner.nextLine();
                                try {
                                    LocalDate parsedDate = LocalDate.parse(tempBirthDate, dateTimeFormatter);

                                    if (parsedDate.isBefore(minBirthDate)) {
                                        System.out.println("Không sớm hơn 1940.");
                                        check = false;
                                    } else if (parsedDate.isAfter(today)) {
                                        System.out.println("Không trễ hơn hôm nay.");
                                        check = false;
                                    } else {
                                        check = true;
                                    }
                                } catch (DateTimeParseException e) {
                                    System.out.println("Mời nhập theo mặc định.");
                                    check = false;
                                }
                            } while (!check);
                            updateContact.setBirthDate(LocalDate.parse(tempBirthDate, dateTimeFormatter));
                            break;
                        case 3:
                            do {
                                System.out.println("Giới tính mới của " + updateContact.getPhoneNumber() + ": (M/F)");
                                tempGender = scanner.nextLine();
                                if (!tempGender.equals("M") && !tempGender.equals("F")) {
                                    check = false;
                                    System.out.println("Không đúng mời nhập lại.");
                                } else check = true;
                            } while (!check);
                            updateContact.setGender(tempGender);
                            break;
                        case 4:
                            do {
                                System.out.println("Địa chỉ mới của " + updateContact.getPhoneNumber());
                                tempAdress = scanner.nextLine();
                                if (Objects.equals(tempAdress, "")) {
                                    System.out.println("Mời nhập đúng địa chỉ.");
                                    check = false;
                                } else check = true;
                            } while (!check);
                            updateContact.setAddress(scanner.nextLine());
                            break;
                        case 5:
                            do {
                                System.out.println("Mời nhập nhóm ĐT mới của " + updateContact.getPhoneNumber());
                                tempGroup = scanner.nextLine();
                                if (Objects.equals(tempGroup, "")) {
                                    System.out.println("Mời nhập đúng tên nhóm.");
                                    check = false;
                                } else check = true;
                            } while (!check);
                            updateContact.setGroupNumber(scanner.nextLine());
                            break;
                        case 6:
                            do {
                                System.out.println("Email mới của " + updateContact.getPhoneNumber());
                                tempEmail = scanner.nextLine();
                                if (!PatternFormat.checkEmail(tempEmail)) {
                                    System.out.println("Email không đúng mời nhập lại.");
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
                        System.out.println("Dữ liệu cập nhật mới như sau, bạn cần điều chỉnh khác? (Y/N)\n"
                                + updateContact.toString());
                        confirm = scanner.nextLine();
                    }
                } while (!confirm.equalsIgnoreCase("Y"));

                if (!continueEditing) break;
                else {
                    System.out.println("Cập nhật thành công. Bạn cần điều chỉnh danh bạ khác? (Y/N)");
                    confirm = scanner.nextLine();
                }
            } else {
                System.out.println("Bộ nhớ trống.");
                continueEditing = false;
                break;
            }
        } while (!confirm.equalsIgnoreCase("N"));
        if (!continueEditing) {
            System.out.println("Hủy hiệu chỉnh.");
        } else {
            listOfContact.set(index, updateContact);
            System.out.println("Cập nhật thành công.");
        }
    }

    public static void findElement(List<Person> listOfContact) {
        String checkNumber;
        boolean check = true;
        String confirm = null;

        do {
            do {
                System.out.println("Số Đt cần tìm: ");
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
