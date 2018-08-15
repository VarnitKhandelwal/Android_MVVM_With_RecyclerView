package com.android.varnit.engineer_ai_assignment.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.varnit.engineer_ai_assignment.R;
import com.android.varnit.engineer_ai_assignment.databinding.ItemLayoutBinding;
import com.android.varnit.engineer_ai_assignment.viewmodel.ItemImageViewModel;

import java.util.Collections;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private List<String> imagesList;
    private ItemLayoutBinding itemLayoutBinding;

    public ItemListAdapter() {
        this.imagesList = Collections.emptyList();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        itemLayoutBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_layout,
                        parent, false);

        return new ViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindItem(imagesList.get(position));

        if (position == 0 && imagesList != null && imagesList.size() % 2 != 0) {
            final ViewGroup.LayoutParams layoutParams = holder.itemLayoutBinding.getRoot().getLayoutParams();
            GridLayoutManager layoutManager = new GridLayoutManager(itemLayoutBinding.getRoot().getContext(), 2);
            // Set the height by params
            layoutParams.height = holder.itemLayoutBinding.getRoot().getLayoutParams().height * 2;
            // set height of RecyclerView
            holder.itemLayoutBinding.getRoot().setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding itemLayoutBinding;

        public ViewHolder(ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.itemImage);
            this.itemLayoutBinding = itemLayoutBinding;
        }

        void bindItem(String imageUrl) {
            if (itemLayoutBinding.getImageViewModel() == null) {
                itemLayoutBinding.setImageViewModel(
                        new ItemImageViewModel(imageUrl, itemView.getContext()));
            } else {
                itemLayoutBinding.getImageViewModel().setImageUrl(imageUrl);
            }
        }
    }
}

