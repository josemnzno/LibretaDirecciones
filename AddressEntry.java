public class AddressEntry {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String email;
    private String phone;

    public AddressEntry(String firstName, String lastName, String street, String city, String state, String postalCode, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.email = email;
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Nombre: " + firstName + " " + lastName +
                "\nDirección: " + street +
                "\nCiudad: " + city +
                "\nEstado: " + state +
                "\nCódigo postal: " + postalCode +
                "\nCorreo electrónico: " + email +
                "\nTeléfono: " + phone+"\n";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}