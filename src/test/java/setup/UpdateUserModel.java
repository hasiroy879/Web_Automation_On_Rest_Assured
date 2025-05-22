package setup;

public class UpdateUserModel {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String gender;
    // ←–– GETTERS ––––>
    public String getEmail()      { return email; }
    public String getFirstName()  { return firstName; }
    public String getLastName()   { return lastName; }
    public String getPhoneNumber(){ return phoneNumber; }
    public String getAddress()    { return address; }
    public String getGender()     { return gender; }



        // ←–– SETTERS ––––>
        public void setEmail(String email)           { this.email = email; }
        public void setFirstName(String firstName)   { this.firstName = firstName; }
        public void setLastName(String lastName)     { this.lastName = lastName; }
        public void setPhoneNumber(String phoneNumber){ this.phoneNumber = phoneNumber; }
        public void setAddress(String address)       { this.address = address; }
        public void setGender(String gender)         { this.gender = gender; }

        public UpdateUserModel() {

        }
}
