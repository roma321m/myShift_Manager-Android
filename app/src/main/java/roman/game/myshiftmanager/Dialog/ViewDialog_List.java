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
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import roman.game.myshiftmanager.Adapters.Adapter_Item;
import roman.game.myshiftmanager.R;

public class ViewDialog_List {

    public interface Callback_ViewDialog{
        void itemClicked(int pos);
    }

    private RecyclerView dialog_list_LST_items;
    private MaterialTextView dialog_list_LBL_title;
    private MaterialButton dialog_list_BTN_back;
    private LinearLayoutManager linearLayoutManager;

    public void showDialog(Activity activity, String title, ArrayList<String> list, Callback_ViewDialog callback_viewDialog) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogbox_item);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        findViews(dialog);

        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        dialog_list_LST_items.setLayoutManager(linearLayoutManager);
        dialog_list_LST_items.setHasFixedSize(true);
        dialog_list_LST_items.setItemAnimator(new DefaultItemAnimator());

        dialog_list_LBL_title.setText(title);

        Adapter_Item adapter_item = new Adapter_Item(activity, list);
        dialog_list_LST_items.setAdapter(adapter_item);

        adapter_item.setItemClickListener(new Adapter_Item.ItemClickListener() {
            @Override
            public void itemClicked(int pos) {
                callback_viewDialog.itemClicked(pos);
                dialog.cancel();
            }
        });

        dialog_list_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void findViews(Dialog dialog){
        dialog_list_LST_items = dialog.findViewById(R.id.dialog_list_LST_items);
        dialog_list_BTN_back = dialog.findViewById(R.id.dialog_list_BTN_back);
        dialog_list_LBL_title = dialog.findViewById(R.id.dialog_list_LBL_title);
    }
}
