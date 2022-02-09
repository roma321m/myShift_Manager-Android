package roman.game.myshiftmanager;

import android.app.Application;

import roman.game.myshiftmanager.DB.MSP;
import roman.game.myshiftmanager.Managers.BottomMenuManager;
import roman.game.myshiftmanager.Managers.DateTimeDialogManager;
import roman.game.myshiftmanager.Managers.FirebaseAuthManager;
import roman.game.myshiftmanager.Managers.ReportsMonthManager;
import roman.game.myshiftmanager.Managers.VibrationSensorManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initHelpers here
        FirebaseAuthManager.initHelper(this);
        BottomMenuManager.initHelper(this);
        MSP.initHelper(this);
        VibrationSensorManager.initHelper(this);
    }
}