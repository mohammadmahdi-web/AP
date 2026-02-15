import java.io.Serializable;

public abstract class Person implements Serializable {
    private String name;
    private String surname;
    private String nationalCode;
    private String birthDate;
    private String username;
    private String password;
    private String enrolmentDate;

    public Person(){}
    public Person(String name, String surname, String nationalCode, String birthDate, String username, String password, String enrolmentDate) {
        this.name = name;
        this.surname = surname;
        this.nationalCode = nationalCode;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
        this.enrolmentDate = enrolmentDate;
    }
    public abstract boolean login(String username, String password);

    //get
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getNationalCode() {
        return nationalCode;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEnrolmentDate() {
        return enrolmentDate;
    }
}
