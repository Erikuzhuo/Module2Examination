package ContactsManagement.Support;

import ContactsManagement.Model.Person;

import java.util.List;
import java.util.Objects;

public class checkDataIsExist {
    public static boolean checkNumberExist(List<Person> list, String ele) {
        for (Person check : list) {
            if (ele.isEmpty() || Objects.equals(check.getPhoneNumber(), ele)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkNumberExist(Person[] list, String ele) {
        for (Person check : list) {
            if (ele.isEmpty() || Objects.equals(check.getPhoneNumber(), ele)) {
                return true;
            }
        }
        return false;
    }

}
