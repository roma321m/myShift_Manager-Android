package roman.game.myshiftmanager.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import roman.game.myshiftmanager.Adapters.Adapter_Workplace;
import roman.game.myshiftmanager.Fragments.Panel.Fragment_Settings;
import roman.game.myshiftmanager.Objects.Workplace;
import roman.game.myshiftmanager.R;

public class ViewDialog_Workplaces {

    private RecyclerView dialog_workplaces_LST_workplaces;
    private LinearLayoutManager linearLayoutManager;

    public void showDialog(Activity activity, ArrayList<Workplace> workplaces, Fragment_Settings.Callback_ViewDialogWorkplaces callback_viewDialogWorkplaces) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogbox_workplaces);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog_workplaces_LST_workplaces = dialog.findViewById(R.id.dialog_workplaces_LST_workplaces);
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        dialog_workplaces_LST_workplaces.setLayoutManager(linearLayoutManager);
        dialog_workplaces_LST_workplaces.setHasFixedSize(true);
        dialog_workplaces_LST_workplaces.setItemAnimator(new DefaultItemAnimator());
        MaterialButton dialog_workplaces_BTN_back = dialog.findViewById(R.id.dialog_workplaces_BTN_back);


        ArrayList<String> list = new ArrayList<>();
        for (Workplace w : workplaces) {
            list.add(w.getName());
        }
        list.add("Add new workplace");

        Adapter_Workplace adapter_workplace = new Adapter_Workplace(activity, list);
        dialog_workplaces_LST_workplaces.setAdapter(adapter_workplace);

        adapter_workplace.setWorkplaceItemClickListener(new Adapter_Workplace.WorkplaceItemClickListener() {
            @Override
            public void itemClicked(int pos) {
                if (pos == workplaces.size()) {
                    callback_viewDialogWorkplaces.newWorkplaceSelected();
                } else {
                    callback_viewDialogWorkplaces.workplaceSelected(pos);
                }
                dialog.cancel();
            }
        });

        dialog_workplaces_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
