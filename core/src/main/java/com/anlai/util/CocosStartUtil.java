package com.anlai.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import org.freyja.libgdx.cocostudio.ui.CocoStudioUIEditor;

/**
 * cocos处理类
 */
public class CocosStartUtil {

    private static CocoStudioUIEditor cocoStudioUIEditor;

    private static ManagerUIEditor managerUIEditor;
    private static ManagerUILoader.ManagerUIParameter managerUIParameter;

    public static Group parseScene(String name) {
//        getCocoStudioUIEditor();
//        return CCScene.parse(cocoStudioUIEditor, name);

        //设置采样方式
        ManagerUILoader.textureParameter.genMipMaps = true;
        ManagerUILoader.textureParameter.minFilter = Texture.TextureFilter.MipMapLinearLinear;
        ManagerUILoader.textureParameter.magFilter = Texture.TextureFilter.Linear;

        //设置使用cocos合图的采样方式
        ManagerUILoader.plistAtlasParameter.genMipMaps = true;
        ManagerUILoader.plistAtlasParameter.minFilter = Texture.TextureFilter.MipMapLinearLinear;
        ManagerUILoader.plistAtlasParameter.magFilter = Texture.TextureFilter.Linear;

        managerUIParameter = new ManagerUILoader.ManagerUIParameter("res/", AssetsUtil.assetManager);

        AssetsUtil.assetManager.load(name, ManagerUIEditor.class, managerUIParameter);
        AssetsUtil.assetManager.finishLoading();

        //创建需要的布局文件
        managerUIEditor = AssetsUtil.assetManager.get(name);
        //根据布局文件创建对应的group
        Group group = managerUIEditor.createGroup();

        return group;
    }
}
