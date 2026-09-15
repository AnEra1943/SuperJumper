package org.freyja.libgdx.cocostudio.ui.model;

import org.freyja.libgdx.cocostudio.ui.model.animation.CCAnimation;

import java.util.List;

public class GameProjectData {
    TimelineActionData Animation;
    List<CCAnimation> AnimationList;
    ObjectData ObjectData;

    public GameProjectData() {
    }

    public TimelineActionData getAnimation() {
        return Animation;
    }

    public void setAnimation(TimelineActionData animation) {
        Animation = animation;
    }

    public List<CCAnimation> getAnimationList() {
        return AnimationList;
    }

    public void setAnimationList(List<CCAnimation> animationList) {
        AnimationList = animationList;
    }

    public ObjectData getObjectData() {
        return ObjectData;
    }

    public void setObjectData(ObjectData objectData) {
        ObjectData = objectData;
    }

}
