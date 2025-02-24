package com.ChatApplication.ChatApplication.DTO;

/**
 * Data Transfer Object (DTO) for handling user login requests.
 * This class encapsulates the email information of a user during authentication.
 */
public class LoginUser {

    private String email; // Stores the email of the user

    /**
     * Constructor to initialize LoginUser with an email.
     *
     * @param email The email of the user.
     */
    public LoginUser(String email) {
        this.email = email;
    }

    /**
     * Default constructor required for frameworks like Spring to instantiate objects dynamically.
     */
    public LoginUser() {
    }

    /**
     * Retrieves the email of the user.
     *
     * @return The email address as a String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email address to be assigned.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the LoginUser object.
     *
     * @return A formatted string containing the email.
     */
    @Override
    public String toString() {
        return "LoginUser{" +
                "email='" + email + '\'' +
                '}';
    }
}
