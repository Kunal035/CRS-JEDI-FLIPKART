package com.flipkart.dao;

import com.flipkart.bean.User;

public interface UserDaoInterface {

    /**
     * Fetches the role and user ID for the given user by their username.
     * @param user The User object containing the username.
     */
    void getRoleByLogin(User user);

    /**
     * Checks if the provided username and password match a user in the database.
     * @param username The username to check.
     * @param password The password to check.
     * @return true if the credentials are correct, false otherwise.
     */
    Boolean checkCredentials(String username, String password);

    /**
     * Updates the password for the given username.
     * @param username The username of the user whose password is to be updated.
     * @param newPassword The new password to set.
     * @return true if the password was updated successfully, false otherwise.
     */
    Boolean updatePassword(String username, String newPassword);

    /**
     * Checks if the user with the given username is approved.
     * @param username The username of the user to check.
     * @return true if the user is approved, false otherwise.
     */
    boolean isApproved(String username);
}
