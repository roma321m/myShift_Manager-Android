package roman.game.myshiftmanager.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.R;

public class Adapter_Shift extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface ShiftItemClickListener {
        void editClicked(Shift shift);
        void deleteClicked(Shift shift);
    }

    private Activity activity;
    private ArrayList<Shift> shiftArrayList;
    private ShiftItemClickListener shiftItemClickListener;

    public Adapter_Shift(Activity activity, ArrayList<Shift> shifts) {
        this.activity = activity;
        this.shiftArrayList = shifts;
    }

    public Adapter_Shift setShiftItemClickListener(ShiftItemClickListener shiftItemClickListener) {
        this.shiftItemClickListener = shiftItemClickListener;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_shift, viewGroup, false);
        return new ShiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ShiftViewHolder shiftViewHolder = (ShiftViewHolder) holder;
        Shift s = getItem(position);

        // here - put data from s to the view
        // FIXME: 08/02/2022 - crush - workplace null?
        //shiftViewHolder.shift_LBL_workplace.setText(s.getWorkplace().getName());
        shiftViewHolder.shift_LBL_start.setText(s.getStart().toString());
        shiftViewHolder.shift_LBL_end.setText(s.getEnd().toString());
        shiftViewHolder.shift_LBL_revenue.setText("" + s.getRevenue());

        // TODO: 08/02/2022 - get the currency from the data manager
    }

    @Override
    public int getItemCount() {
        if (shiftArrayList == null) return 0;
        return shiftArrayList.size();
    }

    private Shift getItem(int position) {
        return shiftArrayList.get(position);
    }

    public class ShiftViewHolder extends RecyclerView.ViewHolder {
        // components here
        public MaterialTextView shift_LBL_workplace, shift_LBL_start, shift_LBL_end,
                shift_LBL_revenue, shift_LBL_currency;
        public MaterialButton reports_BTN_edit, reports_BTN_delete;

        public ShiftViewHolder(final View itemView) {
            super(itemView);
            // find view by id here
            this.shift_LBL_workplace = itemView.findViewById(R.id.shift_LBL_workplace);
            this.shift_LBL_start = itemView.findViewById(R.id.shift_LBL_start);
            this.shift_LBL_end = itemView.findViewById(R.id.shift_LBL_end);
            this.shift_LBL_revenue = itemView.findViewById(R.id.shift_LBL_revenue);
            this.shift_LBL_currency = itemView.findViewById(R.id.shift_LBL_currency);
            this.reports_BTN_edit = itemView.findViewById(R.id.reports_BTN_edit);
            this.reports_BTN_delete = itemView.findViewById(R.id.reports_BTN_delete);

            // callbacks here
            reports_BTN_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shiftItemClickListener != null) {
                        shiftItemClickListener.editClicked(getItem(getAdapterPosition()));
                    }
                }
            });

            reports_BTN_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shiftItemClickListener != null) {
                        shiftItemClickListener.deleteClicked(getItem(getAdapterPosition()));
                    }
                }
            });
        }
    }
}

