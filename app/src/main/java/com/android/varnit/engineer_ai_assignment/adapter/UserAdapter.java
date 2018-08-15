/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.varnit.engineer_ai_assignment.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.varnit.engineer_ai_assignment.R;
import com.android.varnit.engineer_ai_assignment.databinding.ItemUserBinding;
import com.android.varnit.engineer_ai_assignment.model.User;
import com.android.varnit.engineer_ai_assignment.viewmodel.ItemUserViewModel;

import java.util.Collections;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {

    private List<User> userList;
    private ItemUserBinding itemUserBinding;

    public UserAdapter() {
        this.userList = Collections.emptyList();
    }

    @Override
    public UserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemUserBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user,
                        parent, false);

        return new UserAdapterViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(UserAdapterViewHolder holder, int position) {
        holder.bindUser(userList.get(position));
        setupImageList(holder.itemUserBinding.itemRecyclerView, userList.get(position).items);
    }

    private void setupImageList(RecyclerView recyclerView, final List<String> imagesList) {
        ItemListAdapter adapter = new ItemListAdapter();
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(), 2);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (imagesList != null && (imagesList.size() % 2) != 0 && position == 0) ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setImagesList(imagesList);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public static class UserAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemUserBinding itemUserBinding;

        public UserAdapterViewHolder(ItemUserBinding itemPeopleBinding) {
            super(itemPeopleBinding.itemUser);
            this.itemUserBinding = itemPeopleBinding;
        }

        void bindUser(User user) {
            if (itemUserBinding.getUserViewModel() == null) {
                itemUserBinding.setUserViewModel(
                        new ItemUserViewModel(user, itemView.getContext()));
            } else {
                itemUserBinding.getUserViewModel().setUser(user);
            }
        }
    }
}
