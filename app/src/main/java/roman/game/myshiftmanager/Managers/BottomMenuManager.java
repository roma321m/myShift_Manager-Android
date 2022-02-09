package roman.game.myshiftmanager.Managers;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import roman.game.myshiftmanager.R;

public class BottomMenuManager {

    public static final int PROFILE = 0, CALENDAR = 1, REPORTS = 2, SETTINGS = 3, SIZE = 4;

    private static BottomMenuManager single_instance = null;
    private Context context;

    private BottomMenuManager(Context context) {
        this.context = context;
    }

    public static BottomMenuManager initHelper(Context context) {
        if (single_instance == null) {
            single_instance = new BottomMenuManager(context);
        }
        return single_instance;
    }

    public static BottomMenuManager getInstance() {
        return single_instance;
    }

    public void onStartView(MaterialButton[] bottomMenu, MaterialToolbar toolbar){
        onClickView(PROFILE, bottomMenu, toolbar);
    }

    public void onClickView(int click, MaterialButton[] bottomMenu, MaterialToolbar toolbar){
        switch (click){
            case CALENDAR:
                toolbar.setTitle(R.string.calendar);
                bottomMenu[PROFILE].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
                bottomMenu[CALENDAR].setIconTint(ColorStateList.valueOf(context.getColor(R.color.red_400)));
                bottomMenu[REPORTS].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
                bottomMenu[SETTINGS].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
                Log.d("pttt", "icon");
                break;
            case REPORTS:
                toolbar.setTitle(R.string.reports);
                bottomMenu[PROFILE].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
                bottomMenu[CALENDAR].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
                bottomMenu[REPORTS].setIconTint(ColorStateList.valueOf(context.getColor(R.color.red_400)));
                bottomMenu[SETTINGS].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
                break;
            case SETTINGS:
                toolbar.setTitle(R.string.settings);
                bottomMenu[PROFILE].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
                bottomMenu[CALENDAR].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
                bottomMenu[REPORTS].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
                bottomMenu[SETTINGS].setIconTint(ColorStateList.valueOf(context.getColor(R.color.red_400)));
                break;
            default:
                toolbar.setTitle(R.string.profile);
                bottomMenu[PROFILE].setIconTint(ColorStateList.valueOf(context.getColor(R.color.red_400)));
                bottomMenu[CALENDAR].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
                bottomMenu[REPORTS].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
                bottomMenu[SETTINGS].setIconTint(ColorStateList.valueOf(context.getColor(R.color.grey_700)));
        }
    }
}
