package me.dong.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

@ConfigurationProperties("git")
public class GitProperties {
    private final Github github = new Github();

    private final Security security = new Security();

    public static class Github{

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class Security {

        private List<String> admins = Collections.singletonList("dong");

        public List<String> getAdmins() {
            return admins;
        }

        public void setAdmins(List<String> admins) {
            this.admins = admins;
        }
    }
}
