package university.util.access.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Pashnov on 15.12.2014 13:25.
 */
public class Constraint {

    private String urlPattern;
    private List<String> roles;

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public List<String> getRoles() {
        if(roles == null){
            roles = new ArrayList<>();
        }
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Constraint{");
        sb.append("urlPattern='").append(urlPattern).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
