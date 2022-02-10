package roman.game.myshiftmanager.DB;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import roman.game.myshiftmanager.Objects.User;

public class FirebaseDB {

    public interface Callback_checkUserExistence {
        void profileExist();
        void makeProfile();
    }

    private FirebaseDatabase database;
    private static FirebaseDB single_instance;

    private Callback_checkUserExistence callback_checkUserExistence;

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

    public void createUser(User user) {
        if (user != null && user.getUserID() != null) {
            database.getReference("Users").child(user.getUserID()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d("pttt", "data added to firebase");
                }
            });
        }
    }

    public void text(Context context){
        database.getReference("Users").child("id").setValue("user").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "text", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void hasProfile(String userID) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    callback_checkUserExistence.makeProfile();
                }else{
                    callback_checkUserExistence.profileExist();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("tfff",error.getMessage());
            }
        };
        database.getReference("Users").child(userID).addListenerForSingleValueEvent(eventListener);
    }
}
























