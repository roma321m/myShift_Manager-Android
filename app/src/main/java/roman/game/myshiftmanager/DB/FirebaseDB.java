package roman.game.myshiftmanager.DB;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import roman.game.myshiftmanager.Objects.User;

public class FirebaseDB {

    public interface Callback_splashCheck {
        void profileExist(String userID);

        void makeProfile();
    }

    private FirebaseDatabase database;
    private DatabaseReference usersRef;
    private DatabaseReference phonesRef;
    private static FirebaseDB single_instance;

    private Callback_splashCheck callback_splashCheck;

    private FirebaseDB() {
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");
        phonesRef = database.getReference("PhoneLogins");
    }

    public static FirebaseDB getInstance() {
        if (single_instance == null) {
            single_instance = new FirebaseDB();
        }
        return single_instance;
    }

    public FirebaseDB setCallback_splashCheck(Callback_splashCheck callback_splashCheck) {
        this.callback_splashCheck = callback_splashCheck;
        return this;
    }

    public void createUser(User user) {
        if (user != null && user.getUserID() != null) {
            usersRef.child(user.getUserID()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d("pttt", "data added to firebase");
                }
            });
        }
    }

    public void hasProfile(String userID) {
        // FIXME: 07/02/2022 - not working 
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("tfff", "1");
                if (!dataSnapshot.exists()) {
                    Log.d("tfff", "2");
                    callback_splashCheck.makeProfile();
                } else {
                    Log.d("tfff", "2");
                    callback_splashCheck.profileExist(userID);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("tfff", databaseError.getMessage());
            }
        };
        usersRef.child(userID).addListenerForSingleValueEvent(eventListener);

    }
}
























