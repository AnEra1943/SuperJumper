package org.freyja.libgdx.cocostudio.ui.parser.group;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import org.freyja.libgdx.cocostudio.ui.CocoStudioUIEditor;
import org.freyja.libgdx.cocostudio.ui.model.ObjectData;
import org.freyja.libgdx.cocostudio.ui.parser.GroupParser;

/**
 * @author i see
 * @tip 还未支持单色背景属性, 背景图片在Cocostudio里面并不是铺满, 而是居中
 */
public class CCLayer extends GroupParser {

    @Override
    public String getClassName() {
        return "LayerObjectData";
    }

    @Override
    public Actor parse(CocoStudioUIEditor editor, ObjectData widget) {
        return new Group();
    }

}
