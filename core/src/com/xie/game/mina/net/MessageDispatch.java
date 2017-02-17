package com.xie.game.mina.net;

import com.xie.game.mina.msg.MinaMessage;
import com.xie.game.screen.BaseScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xie
 * @Date 17/2/14 下午4:30.
 */
public class MessageDispatch {

    private List<BaseScreen> screenList = new ArrayList<BaseScreen>();

    public List<BaseScreen> getScreenList() {
        return screenList;
    }

    public void setScreenList(List<BaseScreen> screenList) {
        this.screenList = screenList;
    }

    public void dispatch(MinaMessage.Message message) {
        boolean isHandled = false;
        if (screenList != null && screenList.size() > 0) {
            for (int i = 0; i < screenList.size(); i++) {
                if (screenList.get(i).canHandle(message.getType())) {
                    isHandled = false;
                    switch (message.getType()) {
                        case COMMAND:
                            isHandled = screenList.get(i).onCOMMAND(message.getId(), message.getData());
                            break;
                        case RESPONSE:
                            isHandled = screenList.get(i).onMSG(message.getId(), message.getData());
                            break;
                        case INDICATION:
                            isHandled = screenList.get(i).onINDICATION(message.getId(), message.getData());
                            break;
                        case OTHER:
                            isHandled = screenList.get(i).onOTHER(message.getId(), message.getData());
                            break;
                    }
                    if (isHandled) {
                        break;
                    }
                }
            }
        } else {
            System.out.println("message not dispatch!");
        }
    }
}
