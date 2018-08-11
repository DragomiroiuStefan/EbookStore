package model;

public class Author {
    private String ssn;
    private String firstName;
    private String familyName;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Override
    public String toString() {
        return "Author{" + "ssn=" + ssn + ", firstName=" + firstName + ", familyName=" + familyName + '}';
    }
      
}
