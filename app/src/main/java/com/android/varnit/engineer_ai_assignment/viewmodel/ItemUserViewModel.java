package com.android.varnit.engineer_ai_assignment.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.android.varnit.engineer_ai_assignment.model.User;
import com.bumptech.glide.Glide;

public class ItemUserViewModel extends BaseObservable {

    private User user;
    private Context context;

    public ItemUserViewModel(User user, Context context) {
        this.user = user;
        this.context = context;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url != null)
            url = url.replace("http", "https");
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public String getUserName() {
        return user.name;
    }

    public String getUserImage() {
        return user.image;
    }

    public void setUser(User user) {
        this.user = user;
        notifyChange();
    }
}
