package com.company.UI;

import javax.swing.*;

public abstract class BasicView {
    public ViewManager viewManager;

    protected BasicView(ViewManager viewManager){
        this.viewManager = viewManager;
    }

    public abstract void init();

    public abstract JComponent getView();
}
