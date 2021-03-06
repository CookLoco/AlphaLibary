package de.alphahelix.alphalibary.events;

import com.google.common.base.Objects;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class ArmorChangeEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    private double oV, nV;

    public ArmorChangeEvent(Player who, double oldValue, double newValue) {
        super(who);
        this.oV = oldValue;
        this.nV = newValue;
    }

    public double getOldValue() {
        return oV;
    }

    public double getNewValue() {
        return nV;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public final static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArmorChangeEvent that = (ArmorChangeEvent) o;
        return Double.compare(that.oV, oV) == 0 &&
                Double.compare(that.nV, nV) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(oV, nV);
    }

    @Override
    public String toString() {
        return "ArmorChangeEvent{" +
                "oldValue=" + oV +
                ", newValue=" + nV +
                '}';
    }
}
