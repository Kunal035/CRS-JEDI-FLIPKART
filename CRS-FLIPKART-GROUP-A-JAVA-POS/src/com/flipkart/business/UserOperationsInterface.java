package com.flipkart.business;

import com.flipkart.bean.User;

public interface UserOperationsInterface {

    /**
     * Retrieves the role of a user based on login details.
     *
     * @param user The User object containing login details.
     */
    void getRolebyLogin(User user);

    /**
     * Checks if the user with the given username is approved.
     *
     * @param username The username of the user.
     * @return true if the user is approved, false otherwise.
     */
    boolean isApproved(String username);

    /**
     * Updates the password for the user with the given username.
     *
     * @param username The username of the user whose password needs to be updated.
     * @param newPassword The new password to set.
     * @return true if the password update is successful, false otherwise.
     */
    Boolean updatePassword(String username, String newPassword);

    /**
     * Checks the credentials of a user.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if the credentials are correct, false otherwise.
     */
    Boolean checkCredentials(String username, String password);
}
