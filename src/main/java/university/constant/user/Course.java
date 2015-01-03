package university.constant.user;

import org.apache.log4j.Logger;

/**
 * Created by Andrii_Pashnov on 26.12.2014 11:19.
 */
public enum Course {

    I,II,III,IV,V,VI;

    private static final Logger LOG = Logger.getLogger(Course.class);

    public static Course getCourse(String name){
        try{
            return valueOf(name);
        } catch (NullPointerException e){
            LOG.debug("getName return null");
            return null;
        }
    }


}
