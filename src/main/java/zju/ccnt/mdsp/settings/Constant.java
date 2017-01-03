package zju.ccnt.mdsp.settings;

/**
 * Created by Dewayne on 2016/12/25.
 */

public class Constant {
    public static final String FRONTEND_KEY = "";
    public static final String USER_INFO_SYSTEM_KEY = "";

    // server
    public static final String HIS_URL = "http://localhost:8000/mdsp-his";
    public static final String USER_INFO_SYSTEM_URL = "http://localhost:8000/mdsp-his";

    // local
//    public static final String HIS_URL = "http://localhost:7070";
//    public static final String USER_INFO_SYSTEM_URL = "http://localhost:7070";

    public static final String[] PRIVACY_FIELDS = new String[]{"id", "name", "age"
            , "idcard", "password", "phone", "email", "userId", "gender", "demander"
            , "coroner", "checker", "assayId", "userId", "patient", "recordId"
            , "address", "doctor", "recipeId", "drugItemId"};
}