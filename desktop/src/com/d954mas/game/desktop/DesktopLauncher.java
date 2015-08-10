package com.d954mas.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.OrderedMap;
import com.d954mas.game.EsqGame;
import com.d954mas.game.services.Service;
import com.d954mas.game.services.iface.NativeApiService;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		OrderedMap<Class<? extends Service>,Service> services=new OrderedMap<>();
		services.put(Service.class, new NativeApiService() {
			@Override
			public void dispose() {
			}

			@Override
			public void init() {
			}
		});

		new LwjglApplication(new EsqGame(services), config);
	}
}
