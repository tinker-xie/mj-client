package com.xie.game.utils;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

/**
 * @Author xie
 * @Date 17/2/16 下午12:05.
 */
public class AssetManager {
    private MoveByAction moveByAction;
    private MoveToAction moveAction;

    public AssetManager() {
        moveByAction = Actions.moveBy(-100f, -100f, 0.2f);
        moveAction = Actions.moveTo(100f, 100f, 0.5f);
    }

    public MoveByAction getMoveByAction() {
        return moveByAction;
    }

    public void setMoveByAction(MoveByAction moveByAction) {
        this.moveByAction = moveByAction;
    }

    public MoveToAction getMoveAction() {
        return moveAction;
    }

    public void setMoveAction(MoveToAction moveAction) {
        this.moveAction = moveAction;
    }
}
