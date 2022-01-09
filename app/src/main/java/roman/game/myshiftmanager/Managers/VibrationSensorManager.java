package roman.game.myshiftmanager.Managers;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class VibrationSensorManager {
    private static VibrationSensorManager single_instance = null;
    private Context context;

    private VibrationSensorManager(Context context){
        this.context =context;
    }

    public static VibrationSensorManager initHelper(Context context) {
        if (single_instance == null) {
            single_instance = new VibrationSensorManager(context);
        }
        return single_instance;
    }

    public static VibrationSensorManager getInstance() {
        return single_instance;
    }

    public void makeVibration(int milliseconds) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(milliseconds);
        }
    }
}
