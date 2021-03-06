package de.alphahelix.alphalibary.status;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import de.alphahelix.alphalibary.events.status.GameStatusChangeEvent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.io.Serializable;
import java.util.WeakHashMap;

public class GameStatus implements Serializable {

    @Expose
    private static transient WeakHashMap<String, GameStatus> gameStatuses = new WeakHashMap<>();

    @Expose
    private static transient GameStatus current = null;

    private String name;
    private String rawName;

    public GameStatus(String name) {
        setName(name);

        gameStatuses.put(rawName, this);
    }

    public static GameStatus getGameState(String name) {
        return gameStatuses.get(ChatColor.stripColor(name).replace(" ", "_"));
    }

    public static boolean isState(GameStatus state) {
        return (current != null && current.equals(state));
    }

    public static GameStatus getCurrentStatus() {
        return current;
    }

    public static void setCurrentStatus(GameStatus current) {
        GameStatusChangeEvent gameStatusChangeEvent = new GameStatusChangeEvent(GameStatus.current, current);
        GameStatus.current = current;
        Bukkit.getPluginManager().callEvent(gameStatusChangeEvent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.rawName = ChatColor.stripColor(name).replace(" ", "_");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameStatus that = (GameStatus) o;
        return Objects.equal(rawName, that.rawName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String toString() {
        return "GameStatus{" +
                "name='" + name + '\'' +
                ", rawName='" + rawName + '\'' +
                '}';
    }
}
