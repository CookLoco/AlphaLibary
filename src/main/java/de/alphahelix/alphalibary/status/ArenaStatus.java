package de.alphahelix.alphalibary.status;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import de.alphahelix.alphalibary.arena.Arena;
import de.alphahelix.alphalibary.events.status.ArenaStatusChangeEvent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.WeakHashMap;

public class ArenaStatus {

    @Expose
    private static transient WeakHashMap<String, ArenaStatus> arenaStatuses = new WeakHashMap<>();

    @Expose
    private static transient WeakHashMap<Arena, ArenaStatus> current = new WeakHashMap<>();

    private String name;
    private String rawName;

    public ArenaStatus(String name) {
        setName(name);

        arenaStatuses.put(rawName, this);
    }

    public static ArenaStatus getArenaState(String name) {
        return arenaStatuses.get(ChatColor.stripColor(name).replace(" ", "_"));
    }

    public static boolean isState(Arena toCheck, ArenaStatus match) {
        return current.containsKey(toCheck) && current.get(toCheck).equals(match);
    }

    public static ArenaStatus getCurrentStatus(Arena arena) {
        return current.get(arena);
    }

    public static void setCurrentStatus(Arena toUpdate, ArenaStatus current) {
        ArenaStatusChangeEvent gameStatusChangeEvent = new ArenaStatusChangeEvent(getCurrentStatus(toUpdate), current);
        ArenaStatus.current.put(toUpdate, current);
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
        ArenaStatus that = (ArenaStatus) o;
        return Objects.equal(rawName, that.rawName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rawName);
    }

    @Override
    public String toString() {
        return "ArenaStatus{" +
                "name='" + name + '\'' +
                ", rawName='" + rawName + '\'' +
                '}';
    }
}
