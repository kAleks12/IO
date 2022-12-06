package interfaces.database;

import enums.UserRole;

public interface SecurityHandler {
    UserRole validateCredentials(String login, String password);
}
