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

package com.android.varnit.engineer_ai_assignment.application;

import android.app.Application;
import android.content.Context;

import com.android.varnit.engineer_ai_assignment.data.UserFactory;
import com.android.varnit.engineer_ai_assignment.data.UserService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class UserApplication extends Application {

  private UserService userService;
  private Scheduler scheduler;

  private static UserApplication get(Context context) {
    return (UserApplication) context.getApplicationContext();
  }

  public static UserApplication create(Context context) {
    return UserApplication.get(context);
  }

  public UserService getUserService() {
    if (userService == null) {
      userService = UserFactory.create();
    }

    return userService;
  }

  public Scheduler subscribeScheduler() {
    if (scheduler == null) {
      scheduler = Schedulers.io();
    }

    return scheduler;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public void setScheduler(Scheduler scheduler) {
    this.scheduler = scheduler;
  }
}
