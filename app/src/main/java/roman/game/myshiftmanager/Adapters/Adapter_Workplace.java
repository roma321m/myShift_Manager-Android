package roman.game.myshiftmanager.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import roman.game.myshiftmanager.R;

public class Adapter_Workplace extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface WorkplaceItemClickListener {
        void itemClicked(int pos);
    }

    private Activity activity;
    private ArrayList<String> list;
    private WorkplaceItemClickListener workplaceItemClickListener;

    public Adapter_Workplace(Activity activity, ArrayList<String> list) {
        this.activity = activity;
        this.list = list;
    }

    public Adapter_Workplace setWorkplaceItemClickListener(WorkplaceItemClickListener workplaceItemClickListener) {
        this.workplaceItemClickListener = workplaceItemClickListener;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_workplace, viewGroup, false);
        return new WorkplaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        WorkplaceViewHolder workplaceViewHolder = (WorkplaceViewHolder) holder;
        String name = getItem(position);
        workplaceViewHolder.workplace_LBL_name.setText(name);
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    private String getItem(int position) {
        return list.get(position);
    }

    public class WorkplaceViewHolder extends RecyclerView.ViewHolder {
        public MaterialTextView workplace_LBL_name;

        public WorkplaceViewHolder(final View itemView) {
            super(itemView);
            this.workplace_LBL_name = itemView.findViewById(R.id.workplace_LBL_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (workplaceItemClickListener != null) {
                        workplaceItemClickListener.itemClicked(getAdapterPosition());
                    }
                }
            });
        }
    }
}

