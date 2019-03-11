package cn.edu.nju.Entity;

import cn.edu.nju.Item.Equipment;

public class AttributeDecoratorImpl implements AttributeDecorator {

    private AttributeDecorator attributeDecorator;

    private Equipment equipment;

    public AttributeDecoratorImpl(AttributeDecorator attributeDecorator, Equipment equipment) {
        this.attributeDecorator = attributeDecorator;
        this.equipment = equipment;
    }

    @Override
    public Double getAttribute(String attrName) {
        return attributeDecorator.getAttribute(attrName)+equipment.getAttribute(attrName);
    }

}
