package com.d954mas.game.android;

import android.os.Bundle;
import android.util.Log;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.utils.OrderedMap;
import com.d954mas.game.EsqGame;
import com.d954mas.game.services.Service;
import com.d954mas.game.services.iface.NativeApiService;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        runOnUiThread(() -> {
            Log.d("Main Thread", "Lambda works!");
        });

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        OrderedMap<Class<? extends Service>,Service> services=new OrderedMap<Class<? extends Service>,Service>();
        services.put(NativeApiService.class, new NativeApiService() {
            @Override
            public void dispose() {
            }

            @Override
            public void init() {
            }
        });
		initialize(new EsqGame(services), config);
	}
}
