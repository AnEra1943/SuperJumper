//package com.anlai.superjumper;
//
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.InputAdapter;
//
//public class MouseController extends InputAdapter {
//    private boolean LeftPressed = false;
//    private boolean RightPressed = false;
//    private boolean MiddleJustPressed = false;
//    // 鼠标按下时会自动调用
//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//
//        if (button == Input.Buttons.LEFT) {
//            LeftPressed = true;
//        }
//
//        if (button == Input.Buttons.RIGHT) {
//            RightPressed = true;
//        }
//
//        if (button == Input.Buttons.MIDDLE) {
//            MiddleJustPressed = true;
//        }
//        return true;
//    }
//
//        // 鼠标松开时会自动调用
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//
//        if (button == Input.Buttons.LEFT) {
//            LeftPressed = false;
//        }
//
//        if (button == Input.Buttons.RIGHT) {
//            RightPressed = false;
//        }
//
//        return true;
//    }
//    // 提供给 GameScreen 查询左键状态
//    public boolean isLeftPressed() {
//        return LeftPressed;
//    }
//
//
//    // 提供给 GameScreen 查询右键状态
//    public boolean isRightPressed() {
//        return RightPressed;
//    }
//
//
//    // 提供给 GameScreen 查询中键状态
//    public boolean isMiddleJustPressed() {
//        return MiddleJustPressed;
//    }
//
//
//    // 每一帧使用完中键状态后，清零
//    public void resetMiddleJustPressed() {
//        MiddleJustPressed = false;
//    }
//}
