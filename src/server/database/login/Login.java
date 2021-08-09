package server.database.login;
/*
 * Class created on 30.07.2021
 * Class is used store information about login
 * */

import java.io.Serializable;

public class Login implements Serializable {
        private static final long serialVersionUID = 1L;
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

