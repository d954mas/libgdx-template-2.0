package com.d954mas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.d954mas.game.MyGame;
import com.d954mas.game.screens.events.Consumer1;
import com.d954mas.game.screens.events.EsqEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public abstract class MyScreen implements Screen {

    protected abstract Map<String, Class<EsqEvent>> initInnerEventTypes();
    protected InnerEventModel innerEventModel;

    @Override
    public void show() {
        MyGame.getStage().clear();
        Gdx.app.debug(this.getClass().getSimpleName(),"show");
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.debug(this.getClass().getSimpleName(),"resize("+width+" "+height+")");
    }

    @Override
    public void pause() {
        Gdx.app.debug(this.getClass().getSimpleName(),"pause");
    }

    @Override
    public void resume() {
        Gdx.app.debug(this.getClass().getSimpleName(),"resume");
    }

    @Override
    public void hide() {
        Gdx.app.debug(this.getClass().getSimpleName(),"hide");
    }

    @Override
    public void dispose() {
        Gdx.app.debug(this.getClass().getSimpleName(),"dispose");
    }

    public MyScreen(){
        innerEventModel = new InnerEventModel();
    }

    public InnerEventModel getInnerEventModel() {
        return innerEventModel;
    }

    public void fireEvent(String eventId) {
         innerEventModel.fireEvent(eventId);
    }

    public void fireEvent(String eventId, EsqEvent event) {
        innerEventModel.fireEvent(eventId, event);
    }


    public class InnerEventModel {
        protected Map<String, List<Consumer1<EsqEvent>>> listenersOfEventTypes = new HashMap<>();
        protected Map<String, Class<EsqEvent>> eventTypes = initInnerEventTypes();

        public InnerEventModel() {
            if (listenersOfEventTypes == null) {
                listenersOfEventTypes = new HashMap<>();
            }
            if (eventTypes == null) {
                eventTypes = new HashMap<>();
            }
        }

        public void addListener(String eventType, Consumer1<EsqEvent> listener) {
            if (!eventTypes.containsKey(eventType)) {
                throw new RuntimeException(
                        String.format("Unknown eventType:%s; expected:%s", eventType, eventTypes.keySet()));
            }

            List<Consumer1<EsqEvent>> consumer1s = listenersOfEventTypes.get(eventType);
            if (consumer1s == null) {
                listenersOfEventTypes.put(eventType, consumer1s = new ArrayList<>());
            }
            consumer1s.add(listener);
            return;
        }

        @SuppressWarnings("unchecked")
        public void fireEvent(String eventId, EsqEvent event) {
            if (event == null) {
                event = new EsqEvent();
            }
            if (eventTypes.get(eventId) != null && !eventTypes.get(eventId).isAssignableFrom(event.getClass())) {
                throw new RuntimeException(
                        format("Wrong class of event is supplied: %s, expected: %s", event.getClass().getName(),
                                eventTypes.get(eventId).getName()));
            }

            if (listenersOfEventTypes.get(eventId) == null) {
                return ;
            }

            for (Consumer1<EsqEvent> consumer1 : listenersOfEventTypes.get(eventId)) {
                consumer1.accept(event);
            }
            return ;
        }

        public void fireEvent(String eventId) {
             fireEvent(eventId, null);
        }
    }
}
