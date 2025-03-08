package michael.co.fastgrocery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import michael.co.model.Item;
import michael.co.model.Items;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>{
    private Context context;
    private Items items;
    private int single_layout;
    private OnItemClickListener listener;
    private OnItemLongClickListener longListener;

    public interface OnItemClickListener {
        public void onItemClicked(Item item);
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClicked(Item item);
    }

    public ItemsAdapter(Context context, Items items, int single_layout,
                        OnItemClickListener listener,
                        OnItemLongClickListener longListener){
        this.listener = listener;
        this.longListener = longListener;
        this.context = context;
        this.items = items;
        this.single_layout = single_layout;
    }
    @NonNull
    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(single_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemViewHolder holder, int position) {
        Item item = items.get(position);

        if (item != null){
            holder.bind(item, listener, longListener);
        }
    }

    @Override
    public int getItemCount() {
        return (items == null) ? 0 : items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private MaterialTextView tvName;
        private MaterialTextView tvPrice;
        private ImageView ivPicture;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivPicture = itemView.findViewById(R.id.ivPicture);
        }
        @SuppressLint("SetTextI18n")
        public void bind(Item item, OnItemClickListener listener,
                         OnItemLongClickListener longListener){
            tvName.setText(item.getName());
            tvPrice.setText("$" + item.getPrice());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(item);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longListener.onItemLongClicked(item);
                    return true;
                }
            });
        }
    }
}