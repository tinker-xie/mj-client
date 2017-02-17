package com.xie.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/**
 * @Author xie
 * @Date 17/2/12 下午3:43.
 */
public class PTexture {

    private static PTexture pTexture;
    private Array<Texture> textureList;

    private PTexture() {
        this.init();
    }

    public static PTexture getInstance() {
        if (null == pTexture) {
            pTexture = new PTexture();
            return pTexture;
        } else {
            return pTexture;
        }
    }

    private void init() {
        textureList = new Array<Texture>();
        for (int i = PAI.B1.getCode(); i < PAI.BA.getCode(); i++) {
            Texture texture = new Texture(Gdx.files.internal("item_" + i + ".png"));
            textureList.add(texture);
        }
    }

    public Texture getTextureByCode(int code) {
        if (code >= PAI.B1.getCode() && code <= PAI.BA.getCode()) {
            return textureList.get(code);
        }
        return null;
    }

    public Texture getTextureByCode(PAI code) {
        if (code.getCode() >= PAI.W1.getCode() && code.getCode() <= PAI.BA.getCode()) {
            return textureList.get(code.getCode());
        }
        return null;
    }

    public void dispose() {
        if (null != textureList) {
            for (int i = 0; i < textureList.size; i++) {
                textureList.get(i).dispose();
            }
        }

    }
}
