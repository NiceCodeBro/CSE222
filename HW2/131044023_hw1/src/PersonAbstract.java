import java.util.ArrayList;

/**
 * Created by MSD on 22.02.2017.
 * PersonAbstract implements PersonInterface
 *
 */
abstract public class PersonAbstract implements PersonInterface{
    private String userName;
    private String password;
    private String loginType;
    protected ArrayList operationHistory;

    abstract public void allLogsToArrHistoryList(String allLogsFileName);
    /**
     * PersonAbstract()
     * initializing variables and allocating place
     */
    PersonAbstract()
    {
        operationHistory = new ArrayList();
        this.userName = "";
        this.password = "";
        this.loginType = "";
    }

    public void addMemberToArrayList(String line)
    {
       // operationHistory.add()
    }
    /**
     *  abstract protected void printOperationsHistory()
     *  print all operations according to authorization
     */
    abstract protected void printOperationsHistory();

    /**
     * getOperationHistory()
     * return the operation history arraylist
     * @return operationHistory
     */
    public ArrayList getOperationHistory() {   return operationHistory;  }

    /**
     * getLoginType()
     * return the loginType (member or root)
     * @return loginType
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * setLoginType(String loginType)
     * set the login type
     * @param loginType
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    /**
     * setUsername(String _userName)
     * set the user name
     * @param _userName
     */
    @Override
    public void setUsername(String _userName) {
        this.userName = _userName;
    }

    /**
     * setPassword(String _password)
     * set the password
     * @param _password
     */
    @Override
    public void setPassword(String _password) {
        this.password = _password;
    }

    /**
     * getUsername()
     * get the username
     * @return userName
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * get the password
     * @return password;
     */
    @Override
    public String getPassword() {
        return password;
    }
}
