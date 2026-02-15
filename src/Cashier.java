import java.io.Serializable;

public class Cashier extends Person implements Serializable {

    private String employeeId;

    public Cashier(String employeeId,String name, String surname, String nationalCode, String birthDate, String username, String password, String enrolmentDate) {
        super(name, surname, nationalCode, birthDate, username, password, enrolmentDate);
        this.employeeId = employeeId;
    }
    public Cashier() {}
    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "\tEmployee Information:\n" +
                "Employee ID: " + employeeId + "\n" +
                "Name: " + getName() + "\n" +
                "Surname: " + getSurname() + "\n" +
                "National Code: " + getNationalCode() + "\n" +
                "Birth Date: " + getBirthDate() + "\n" +
                "Username: " + getUsername() + "\n" +
                "Password: " + "*******" + "\n" +
                "Enrolment Date: " + getEnrolmentDate()+"\n";
    }
    @Override
    public boolean login(String username, String password) {
        if (super.getUsername().equals(username) && super.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
