package roman.game.myshiftmanager;

import android.app.Application;

import roman.game.myshiftmanager.UserData.FirebaseS;
import roman.game.myshiftmanager.UserData.MSP;
import roman.game.myshiftmanager.Managers.BottomMenuManager;
import roman.game.myshiftmanager.UserData.FirebaseAuthManager;
import roman.game.myshiftmanager.UserData.UserDataManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initHelpers here
        FirebaseAuthManager.initHelper(this);
        FirebaseS.initHelper(this);
        UserDataManager.initHelper(this);
        BottomMenuManager.initHelper(this);
        MSP.initHelper(this);
    }
}