package com.example.FinanceApp.state;

import com.example.FinanceApp.entity.base.User;

public class UserContext {
    private final User user;
    private UserState state;

    public UserContext(User user) {
        this.user = user;
        this.state = resolveState(user.getState());
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }


    public void suspend() {
        state.suspend(this);
    }

    public void activate() {
        state.activate(this);
    }

    public void close() {
        state.close(this);
    }


    private UserState resolveState(UserStateType stateType) {
        return switch (stateType) {
            case ACTIVE -> new ActiveState();
            case SUSPENDED -> new SuspendedState();
            case CLOSED -> new ClosedState();
        };
    }
}

