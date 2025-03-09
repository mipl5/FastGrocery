package michael.co.fastgrocery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import michael.co.model.GroceryShop;
import michael.co.model.GroceryShops;

public class GroceryShopsAdapter extends RecyclerView.Adapter<GroceryShopsAdapter.GroceryShopViewHolder>{

    private Context context;
    private GroceryShops groceryShops;
    private int single_layout;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        public void onItemClicked(GroceryShop groceryShop);
    }

    public GroceryShopsAdapter(Context context, GroceryShops groceryShops, int single_layout,
                        OnItemClickListener listener){
        this.listener = listener;
        this.context = context;
        this.groceryShops = groceryShops;
        this.single_layout = single_layout;
    }
    @NonNull
    @Override
    public GroceryShopsAdapter.GroceryShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(single_layout, parent, false);
        return new GroceryShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryShopViewHolder holder, int position) {
        GroceryShop groceryShop = groceryShops.get(position);

        if (groceryShop != null){
            holder.bind(groceryShop, listener);
        }
    }

    @Override
    public int getItemCount() {
        return (groceryShops == null) ? 0 : groceryShops.size();
    }

    public static class GroceryShopViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView tvName;
        private MaterialTextView tvWebsiteURL;
        private ImageView ivPicture;
        public GroceryShopViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvWebsiteURL = itemView.findViewById(R.id.tvWebsiteURL);
            ivPicture = itemView.findViewById(R.id.ivPicture);
        }
        @SuppressLint("SetTextI18n")
        public void bind(GroceryShop groceryShop, OnItemClickListener listener){
            tvName.setText(String.valueOf(groceryShop.getName()));
            tvWebsiteURL.setText(String.valueOf(groceryShop.getWebsiteURL()));
            ivPicture.setImageResource(groceryShop.getImageResource());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(groceryShop);
                }
            });
        }
    }
}
