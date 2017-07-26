package com.mercury.platform.ui.adr.components.panel;

import com.mercury.platform.shared.config.descriptor.adr.AdrComponentDescriptor;
import com.mercury.platform.shared.config.descriptor.adr.AdrDurationComponentDescriptor;
import com.mercury.platform.shared.config.descriptor.adr.AdrGroupDescriptor;
import com.mercury.platform.shared.config.descriptor.adr.AdrTrackerGroupDescriptor;
import com.mercury.platform.ui.adr.components.panel.tree.AdrMouseOverListener;
import com.mercury.platform.ui.components.ComponentsFactory;
import com.mercury.platform.ui.misc.AppThemeColor;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class AdrGroupPanel extends AdrComponentPanel<AdrGroupDescriptor> {
    private JPanel contentPanel;
    private JLabel headerLabel;
    @Getter
    private JPanel headerPanel;
    private List<AdrComponentPanel> innerComponents;
    public AdrGroupPanel(AdrGroupDescriptor descriptor, ComponentsFactory componentsFactory) {
        super(descriptor, componentsFactory);
        this.setBackground(AppThemeColor.TRANSPARENT);
        this.headerLabel = this.componentsFactory.getTextLabel(this.descriptor.getTitle());
        this.headerPanel = this.componentsFactory.getJPanel(new FlowLayout(FlowLayout.LEFT));
        this.headerPanel.setBackground(AppThemeColor.ADR_BG);
        this.headerPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,AppThemeColor.ADR_DEFAULT_BORDER));
        this.headerPanel.add(headerLabel);
        this.add(headerPanel,BorderLayout.PAGE_START);
    }

    @Override
    public void enableSettings() {
        super.enableSettings();
        this.innerComponents.forEach(AdrComponentPanel::enableSettings);
        this.headerPanel.setVisible(true);
    }

    @Override
    public void disableSettings() {
        super.disableSettings();
        this.innerComponents.forEach(AdrComponentPanel::disableSettings);
        this.headerPanel.setVisible(false);
    }

    @Override
    public void createUI() {
        this.setLayout(new BorderLayout());
        this.innerComponents = new ArrayList<>();
        this.contentPanel = this.componentsFactory.getJPanel(null);
        this.contentPanel.setBackground(AppThemeColor.TRANSPARENT);
        this.add(this.contentPanel,BorderLayout.CENTER);
        this.loadRecursively(this.contentPanel,this.descriptor.getContent());
    }
    public void loadRecursively(JComponent parent, List<AdrComponentDescriptor> child){
        Insets insets = parent.getInsets();
        child.forEach(it -> {
            switch (it.getType()) {
                case GROUP: {

                    break;
                }
                case TRACKER_GROUP: {
                    AdrTrackerGroupPanel cell = new AdrTrackerGroupPanel((AdrTrackerGroupDescriptor) it, this.componentsFactory);
                    cell.addMouseListener(new AdrMouseOverListener<>(cell,it,false));
                    cell.setBounds(it.getLocation().x + insets.left,it.getLocation().y + insets.top,
                            it.getSize().width,it.getSize().height);
                    this.innerComponents.add(cell);
                    parent.add(cell);
                    break;
                }
                default:{
                    AdrCellPanel cell = new AdrCellPanel((AdrDurationComponentDescriptor) it, this.componentsFactory);
                    cell.addMouseListener(new AdrMouseOverListener<>(cell,it,false));
                    cell.setBounds(it.getLocation().x + insets.left,it.getLocation().y + insets.top,
                            it.getSize().width,it.getSize().height);
                    this.innerComponents.add(cell);
                    parent.add(cell);
                }
            }
        });
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
        this.headerLabel.setText(this.descriptor.getTitle());
    }
}
