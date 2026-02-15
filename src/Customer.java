import java.io.Serializable;

public  class Customer extends Person implements Serializable {
    public Customer(String name, String surname, String nationalCode, String birthDate, String username, String password, String enrolmentDate) {
        super(name, surname, nationalCode, birthDate, username, password, enrolmentDate);
    }

    public Customer(){}
    @Override
    public String toString() {
        return "\tCustomer Information:\n" +
                "Name: " + getName() + "\n" +
                "Surname: " + getSurname() + "\n" +
                "National Code: " + getNationalCode() + "\n" +
                "Birth Date: " + getBirthDate() + "\n" +
                "Username: " + getUsername() + "\n" +
                "Password: *****\n" +
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