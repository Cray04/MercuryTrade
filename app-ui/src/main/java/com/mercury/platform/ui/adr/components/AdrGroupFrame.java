package com.mercury.platform.ui.adr.components;


import com.mercury.platform.shared.config.descriptor.adr.AdrGroupDescriptor;
import com.mercury.platform.ui.adr.components.panel.AdrGroupPanel;
import com.mercury.platform.ui.misc.AppThemeColor;

import javax.swing.*;
import java.awt.*;

public class AdrGroupFrame extends AbstractAdrComponentFrame<AdrGroupDescriptor>{
    private AdrGroupPanel groupPanel;
    public AdrGroupFrame(AdrGroupDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.groupPanel = new AdrGroupPanel(this.descriptor,this.componentsFactory);
        this.groupPanel.getHeaderPanel().addMouseListener(this.mouseListener);
        this.groupPanel.getHeaderPanel().addMouseListener(this.mouseOverListener);
        this.groupPanel.getHeaderPanel().addMouseMotionListener(this.motionListener);
        this.groupPanel.addMouseListener(this.mouseListener);
        this.groupPanel.addMouseMotionListener(this.motionListener);
        this.setPreferredSize(this.descriptor.getSize());
        this.add(this.groupPanel,BorderLayout.CENTER);
        this.pack();
    }

    @Override
    public void subscribe() {
        super.subscribe();
    }

    @Override
    public void enableSettings() {
        super.enableSettings();
        this.getRootPane().setBorder(BorderFactory.createLineBorder(AppThemeColor.ADR_DEFAULT_BORDER));
        this.setBackground(AppThemeColor.ADR_GROUP_BG);
        this.groupPanel.enableSettings();
        this.groupPanel.addMouseListener(this.mouseOverListener);
        this.pack();
        this.repaint();
    }

    @Override
    public void disableSettings() {
        super.disableSettings();
        this.setBackground(AppThemeColor.TRANSPARENT);
        this.groupPanel.disableSettings();
        this.groupPanel.removeMouseListener(this.mouseOverListener);
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        this.pack();
        this.repaint();
    }

    @Override
    protected LayoutManager getFrameLayout() {
        return new BorderLayout();
    }
}
