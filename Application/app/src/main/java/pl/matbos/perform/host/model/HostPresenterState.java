package pl.matbos.perform.host.model;

import java.io.Serializable;

public class HostPresenterState implements Serializable {
    public final Screen screen;

    public HostPresenterState(Screen screen) {
        this.screen = screen;
    }
}