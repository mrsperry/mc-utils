package io.github.mrsperry.mcutils.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MenuManager {
    private static MenuManager self;
    Map<UUID, List<Menu>> history;

    private MenuManager() {
        history = new HashMap<>();
    }

    public static MenuManager getInstance() {
        if(self == null) {
            self = new MenuManager();
        }
        return self;
    }

    public Menu getCurrentMenu(UUID player) {
        if(this.history.containsKey(player)) {
            return this.history.get(player).get(0);
        }

        return null;
    }
}
