package roman.game.myshiftmanager.UserData;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.Objects.User;
import roman.game.myshiftmanager.Objects.Workplace;

public class FirebaseDB {

    public interface Callback_checkUserExistence {
        void profileExist();
        void makeProfile();
    }

    public interface Callback_loadUserData{
        void callback_loadUserData(User user);
        void callback_loadWorkplaces(ArrayList<Workplace> workplaces);
        void callback_loadShifts(ArrayList<Shift> shifts);
    }

    private FirebaseDatabase database;
    private static FirebaseDB single_instance;

    private Callback_checkUserExistence callback_checkUserExistence;
    private Callback_loadUserData callback_loadUserData;

    private FirebaseDB() {
        database = FirebaseDatabase.getInstance();
    }

    public static FirebaseDB getInstance() {
        if (single_instance == null) {
            single_instance = new FirebaseDB();
        }
        return single_instance;
    }

    public FirebaseDB setCallback_checkUserExistence(Callback_checkUserExistence callback_checkUserExistence) {
        this.callback_checkUserExistence = callback_checkUserExistence;
        return this;
    }

    public FirebaseDB setCallback_loadUserData(Callback_loadUserData callback_loadUserData){
        this.callback_loadUserData = callback_loadUserData;
        return this;
    }

    public void createUser(String uid, User user) {
        if (user != null && uid != null) {
            database.getReference("Users").child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    // TODO: 10/02/2022 - ?
                }
            });
        }
    }

    public void addShift(String uid, Shift shift){
        // TODO: 10/02/2022 - add new shift to db
    }

    public void addWorkplace(String uid, Workplace workplace){
        // TODO: 10/02/2022 - add new workplace to db
    }

    public void hasProfile(String userID) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(!snapshot.exists()) {
                    if (callback_checkUserExistence != null)
                    callback_checkUserExistence.makeProfile();
                }else{
                    try {
                        User user = snapshot.getValue(User.class);
                        if (callback_loadUserData != null){
                            loadWorks(userID);
                            loadShifts(userID);
                            callback_loadUserData.callback_loadUserData(user);
                        }
                        if (callback_checkUserExistence != null)
                            callback_checkUserExistence.profileExist();
                    } catch (Exception ex) {
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("tfff",error.getMessage());
            }
        };
        database.getReference("Users").child(userID).addListenerForSingleValueEvent(eventListener);
    }

    private void loadShifts(String userID) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    ArrayList<Shift> shifts = new ArrayList<>();
                    for (DataSnapshot child : snapshot.getChildren()) {
                        try {
                            Shift s1 = child.getValue(Shift.class);
                            shifts.add(s1);
                        } catch (Exception ex) {
                        }
                    }
                    if (callback_loadUserData != null) {
                        callback_loadUserData.callback_loadShifts(shifts);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        database.getReference("Shifts").child(userID).addListenerForSingleValueEvent(eventListener);
    }

    private void loadWorks(String userID) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    ArrayList<Workplace> workplaces = new ArrayList<>();
                    for (DataSnapshot child : snapshot.getChildren()) {
                        try {
                            Workplace workplace = child.getValue(Workplace.class);
                            workplaces.add(workplace);
                        } catch (Exception ex) {
                        }
                    }
                    if (callback_loadUserData != null) {
                        callback_loadUserData.callback_loadWorkplaces(workplaces);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        database.getReference("Workplaces").child(userID).addListenerForSingleValueEvent(eventListener);
    }
}
























