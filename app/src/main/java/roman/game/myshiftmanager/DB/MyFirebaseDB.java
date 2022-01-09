package roman.game.myshiftmanager.DB;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyFirebaseDB {

    private FirebaseDatabase database;
    private DatabaseReference usersRef;
    private DatabaseReference phonesRef;
    private static MyFirebaseDB single_instance;

    private MyFirebaseDB() {
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");
        phonesRef = database.getReference("PhoneLogins");
    }

    public static MyFirebaseDB getInstance() {
        if (single_instance == null) {
            single_instance = new MyFirebaseDB();
        }
        return single_instance;
    }
}
























