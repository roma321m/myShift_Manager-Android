package roman.game.myshiftmanager.UserData;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.Objects.User;
import roman.game.myshiftmanager.Objects.Workplace;

public class FirebaseDB {

    public static final String USERS = "Users", SHIFTS = "Shifts", WORKPLACES = "workplaces";

    public interface Callback_checkUserExistence {
        void profileExist();

        void makeProfile();
    }

    public interface Callback_loadUserData {
        void callback_loadUserData(User user);

        void callback_loadWorkplaces(ArrayList<Workplace> workplaces);

        void callback_loadShifts(ArrayList<Shift> shifts);
    }

    private FirebaseDatabase database;
    private DatabaseReference users, shifts, workplaces;
    private static FirebaseDB single_instance;

    private Callback_checkUserExistence callback_checkUserExistence;
    private Callback_loadUserData callback_loadUserData;

    private FirebaseDB() {
        database = FirebaseDatabase.getInstance();
        users = database.getReference(USERS);
        shifts = database.getReference(SHIFTS);
        workplaces = database.getReference(WORKPLACES);
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

    public FirebaseDB setCallback_loadUserData(Callback_loadUserData callback_loadUserData) {
        this.callback_loadUserData = callback_loadUserData;
        return this;
    }

    public void createUser(String uid, User user) {
        if (user != null && uid != null) {
            users.child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    // TODO: 10/02/2022 - ?
                }
            });
        }
    }

    public void addShift(String uid, Shift shift) {
        if (shift != null && uid != null) {
            shifts.child(uid).child(shift.getId()).setValue(shift)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // TODO: 10/02/2022 - ?
                        }
                    });
        }
    }

    public void removeShift(String uid, String shiftId) {
        if ( uid != null && shiftId != null)
            shifts.child(uid).child(shiftId).removeValue();
    }

    public void addWorkplace(String uid, Workplace workplace) {
        if (workplace != null && uid != null) {
            workplaces.child(uid).child(workplace.getId()).setValue(workplace)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // TODO: 10/02/2022 - ?
                        }
                    });
        }
    }

    public void removeWorkplace(String uid, String workplaceId) {
        if ( uid != null && workplaceId != null)
            workplaces.child(uid).child(workplaceId).removeValue();
    }

    public void hasProfile(String userID) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    if (callback_checkUserExistence != null)
                        callback_checkUserExistence.makeProfile();
                } else {
                    try {
                        User user = snapshot.getValue(User.class);
                        if (callback_loadUserData != null) {
                            callback_loadUserData.callback_loadUserData(user);
                            loadWorks(userID);
                            loadShifts(userID);
                        }
                        if (callback_checkUserExistence != null)
                            callback_checkUserExistence.profileExist();
                    } catch (Exception ex) {
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("tfff", error.getMessage());
            }
        };
        users.child(userID).addListenerForSingleValueEvent(eventListener);
    }

    private void loadShifts(String userID) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Shift> shifts = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                        try {
                            Shift s1 = child.getValue(Shift.class);
                            shifts.add(s1);
                        } catch (Exception ex) {
                        }
                    }
                }
                if (callback_loadUserData != null) {
                    callback_loadUserData.callback_loadShifts(shifts);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        shifts.child(userID).addListenerForSingleValueEvent(eventListener);
    }

    private void loadWorks(String userID) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Workplace> workplaces = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                        try {
                            Workplace workplace = child.getValue(Workplace.class);
                            workplaces.add(workplace);
                        } catch (Exception ex) {
                        }
                    }
                }
                if (callback_loadUserData != null) {
                    callback_loadUserData.callback_loadWorkplaces(workplaces);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        workplaces.child(userID).addListenerForSingleValueEvent(eventListener);
    }

    public void updateCurrency(String uid, int value) {
        if (uid != null) {
            users.child(uid).child("currency").setValue(value)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // TODO: 10/02/2022 - ?
                        }
                    });
        }
    }

    public void updateTimeFormat(String uid, int value) {
        if (uid != null) {
            users.child(uid).child("timeFormat").setValue(value)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // TODO: 10/02/2022 - ?
                        }
                    });
        }
    }

    public void updateDateFormat(String uid, int value) {
        if (uid != null) {
            users.child(uid).child("dateFormat").setValue(value)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // TODO: 10/02/2022 - ?
                        }
                    });
        }
    }
}
























