package roman.game.myshiftmanager;

import android.app.Application;

import roman.game.myshiftmanager.DB.MSP;
import roman.game.myshiftmanager.Managers.VibrationSensorManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initHelpers here
        MSP.initHelper(this);
        VibrationSensorManager.initHelper(this);
    }
}