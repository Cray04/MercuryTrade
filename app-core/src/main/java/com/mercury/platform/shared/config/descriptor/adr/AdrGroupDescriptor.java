package com.mercury.platform.shared.config.descriptor.adr;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdrGroupDescriptor extends AdrComponentDescriptor {
    private List<AdrComponentDescriptor> content;
}
