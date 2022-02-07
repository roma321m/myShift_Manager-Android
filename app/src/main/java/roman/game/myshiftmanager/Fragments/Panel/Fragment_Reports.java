package roman.game.myshiftmanager.Fragments.Panel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import roman.game.myshiftmanager.R;

public class Fragment_Reports extends Fragment {

    private AppCompatActivity activity;

    public Fragment_Reports(){};

    public Fragment_Reports setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports, container, false);
        findViews(view);

        return view;
    }

    private void findViews(View view) {

    }
}
