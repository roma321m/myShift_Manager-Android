package roman.game.myshiftmanager.Fragments.Panel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import roman.game.myshiftmanager.R;

public class Fragment_Settings extends Fragment {

    private AppCompatActivity activity;

    public Fragment_Settings(){};

    public Fragment_Settings setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        findViews(view);

        return view;
    }

    private void findViews(View view) {

    }
}
