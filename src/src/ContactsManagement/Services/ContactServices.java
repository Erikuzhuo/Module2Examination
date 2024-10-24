package ContactsManagement.Services;

import ContactsManagement.Controller.ContactsController;
import ContactsManagement.Data.ContactFile;
import ContactsManagement.Model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ContactServices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> listOfContact = new ArrayList<>();
        boolean check = true;
        int option = 0;
        while(true) {
            do {
                try {
                    System.out.println("---- Chương Trình Quản Lý Danh Bạ ----\n" +
                            "Chọn chức năng theo số (để tiếp tục):\n" +
                            "1. Xem Danh Sách;\n" +
                            "2. Thêm Mới;\n" +
                            "3. Cập Nhật;\n" +
                            "4. Xóa;\n" +
                            "5. Tìm Kiếm;\n" +
                            "6. Đọc Từ File;\n" +
                            "7. Ghi vào File;\n" +
                            "8. Thoát.\n" +
                            "Chọn Chức Năng: ");
                    option = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Number must input correctly.");
                    check = false;
                }
            } while (!check);
            switch (option) {
                case 1:
                    if (!listOfContact.isEmpty()) {
                        ContactsController.displayListOfContact(listOfContact);
                    } else System.out.println("Danh sách trống.");
                    break;
                case 2:
                    ContactsController.createNewContact(listOfContact);
                    break;
                case 3:
                    ContactsController.updateContact(listOfContact);
                    break;
                case 4:
                    ContactsController.deleteContact(listOfContact);
                    break;
                case 5:
                    ContactsController.findElement(listOfContact);
                    break;
                case 6:
                    System.out.println("Bạn có sẽ lưu danh sách vào File.");
                    String checkOption2 = scanner.nextLine();
                    if(Objects.equals(checkOption2,"Y")) {
                    listOfContact = ContactFile.readListFromFile();
                    }else System.out.println("Hủy Lưu.");
                    break;
                case 7:
                    System.out.println("Bạn có chắc đọc File, vì toàn bộ danh sách hiện có trong bộ nhơ " +
                            "nếu chưa lưu sẽ bị xóa.");
                    String checkOption = scanner.nextLine();;
                    if(Objects.equals(checkOption,"Y")) {
                        ContactFile.writeListToFile(listOfContact);
                    }else System.out.println("Hủy Đọc.");
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Kindly re-input the number");
                    break;
            }
        }
    }
}
