package io.github.masyumero.mekanismavaritia.common.block.attribute;

import io.github.masyumero.mekanismavaritia.common.content.blocktype.MAFactoryType;
import mekanism.common.block.attribute.Attribute;
import org.jetbrains.annotations.NotNull;

public class MAAttributeFactoryType implements Attribute {

    private final MAFactoryType type;

    public MAAttributeFactoryType(MAFactoryType type) {
        this.type = type;
    }

    @NotNull
    public MAFactoryType getFactoryType() {
        return type;
    }
}
