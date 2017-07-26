package com.mercury.platform.ui.adr.components.panel;

import com.mercury.platform.shared.config.descriptor.adr.AdrDurationComponentDescriptor;
import com.mercury.platform.shared.config.descriptor.adr.AdrTrackerGroupDescriptor;
import com.mercury.platform.shared.config.descriptor.adr.AdrTrackerGroupType;
import com.mercury.platform.ui.components.ComponentsFactory;
import com.mercury.platform.ui.misc.AppThemeColor;
import com.mercury.platform.ui.misc.MercuryStoreUI;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class AdrTrackerGroupPanel extends AdrComponentPanel<AdrTrackerGroupDescriptor> {
    private List<AdrComponentPanel> cells;
    public AdrTrackerGroupPanel(AdrTrackerGroupDescriptor descriptor, ComponentsFactory componentsFactory) {
        super(descriptor, componentsFactory);
        this.setBackground(AppThemeColor.TRANSPARENT);
        this.setBorder(null);
    }

    private void init(){
        int cellCount = this.descriptor.getCells().size();
        switch (this.descriptor.getOrientation()){
            case VERTICAL:{
                if(this.descriptor.getGroupType().equals(AdrTrackerGroupType.STATIC)) {
                    this.setLayout(new GridLayout(cellCount, 1,this.descriptor.getHGap(),this.descriptor.getVGap()));
                }else {
                    this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
                }
                break;
            }
            case HORIZONTAL:{
                if(this.descriptor.getGroupType().equals(AdrTrackerGroupType.STATIC)) {
                    this.setLayout(new GridLayout(1,cellCount,this.descriptor.getHGap(),this.descriptor.getVGap()));
                }else {
                    this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
                }
                break;
            }
        }
        this.cells.forEach(item -> {
            item.setPreferredSize(this.descriptor.getSize());
            item.setLocation(this.descriptor.getLocation());
        });
        this.setLocation(descriptor.getLocation());
        MercuryStoreUI.adrRepaintSubject.onNext(true);
    }

    @Override
    public void createUI() {
        this.cells = new ArrayList<>();
        this.setLayout(new GridLayout());
        this.descriptor.getCells().forEach(component -> {
            AdrCellPanel adrCellPanel = new AdrCellPanel((AdrDurationComponentDescriptor) component, this.componentsFactory);
            adrCellPanel.disableSelect();
            this.add(adrCellPanel);
            this.cells.add(adrCellPanel);
        });
        this.init();
    }

    @Override
    public void enableSettings() {
        super.enableSettings();
        this.setVisible(true);
        this.cells.forEach(AdrComponentPanel::enableSettings);
    }

    @Override
    public void disableSettings() {
        super.disableSettings();
        this.cells.forEach(AdrComponentPanel::disableSettings);
    }

    @Override
    public void onSelect() {
    }

    @Override
    public void onUnSelect() {
    }

    @Override
    protected void onHotKeyPressed() {

    }

    @Override
    protected void onUpdate() {
        this.init();

    }
}
