package com.anlai.util;

import com.anlai.util.plist.PlistAtlas;
import com.anlai.util.plist.PlistAtlasLoader;
import com.badlogic.gdx.assets.AssetManager;

public class AssetsUtil {
    public static AssetManager assetManager;
    //    初始化资源管理器
    public static void init() {
        assetManager = new AssetManager();
        assetManager.setLoader(PlistAtlas.class, new PlistAtlasLoader(assetManager.getFileHandleResolver()));
        assetManager.setLoader(ManagerUIEditor.class,new ManagerUILoader(assetManager.getFileHandleResolver()));
    }
}
