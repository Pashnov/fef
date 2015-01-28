package university.util.access.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Pashnov on 15.12.2014 13:24.
 */
public class Security {

    List<Constraint> constraints;

    public List<Constraint> getConstraints() {
        if (constraints == null) {
            constraints = new ArrayList<>();
        }
        return constraints;
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Security{");
        sb.append("constraints=").append(constraints);
        sb.append('}');
        return sb.toString();
    }
}
