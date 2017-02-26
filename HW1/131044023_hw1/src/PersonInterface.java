/**
 * Created by MSD on 22.02.2017.
 * PersonInterface
 * This interface have 4 abstract method
 * 1)setUsername
 * 2)setPassword
 * 3)getUsername
 * 4)getPassword
 */
public interface PersonInterface {
    /**
     * abstract set username
     * @param _userName
     */
    public abstract void setUsername(String _userName);

    /**
     * abstract set password
     * @param _password
     */
    public abstract void setPassword(String _password);

    /**
     * abstract get username
     * @return
     */
    public abstract String getUsername();

    /**
     * abstract get password
     * @return
     */
    public abstract String getPassword();
}
