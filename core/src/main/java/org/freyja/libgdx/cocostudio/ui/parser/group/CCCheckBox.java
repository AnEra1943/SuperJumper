package org.freyja.libgdx.cocostudio.ui.parser.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import org.freyja.libgdx.cocostudio.ui.CocoStudioUIEditor;
import org.freyja.libgdx.cocostudio.ui.model.ObjectData;
import org.freyja.libgdx.cocostudio.ui.parser.GroupParser;

/**
 * @author i see
 * @tip libgdx的CheckBox只有选中和未选中两个状态的图片显示
 */
public class CCCheckBox extends GroupParser {

    @Override
    public String getClassName() {
        return "CheckBoxObjectData";
    }

    @Override
    public Actor parse(CocoStudioUIEditor editor, ObjectData widget) {
        CheckBoxStyle style = new CheckBoxStyle(null, null, new BitmapFont(), Color.BLACK);

        if (widget.getNodeNormalFileData() != null) {// 选中图片

            style.checkboxOff = editor.findDrawable(widget, widget.getNodeNormalFileData());
        }
        if (widget.getNormalBackFileData() != null) {// 没选中图片
            style.checkboxOn = editor.findDrawable(widget, widget.getNormalBackFileData());
        }
        CheckBox checkBox = new CheckBox("", style);
        checkBox.setChecked(widget.isDisplayState());
        checkBox.setDisabled(widget.isDisplayState());
        return checkBox;
    }
}
