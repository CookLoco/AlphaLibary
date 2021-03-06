package de.alphahelix.alphalibary.nms.packets;

import com.google.common.base.Objects;
import de.alphahelix.alphalibary.reflection.ReflectionUtil;

public class PPOMount implements IPacket {

    private static final ReflectionUtil.SaveConstructor PACKET =
            ReflectionUtil.getDeclaredConstructor("PacketPlayOutMount",
                    ReflectionUtil.getNmsClass("Entity"));

    private Object entity;

    public PPOMount(Object entity) {
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }

    public PPOMount setEntity(Object entity) {
        this.entity = entity;
        return this;
    }

    @Override
    public Object getPacket(boolean stackTrace) {
        return PACKET.newInstance(stackTrace, entity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PPOMount ppoMount = (PPOMount) o;
        return Objects.equal(getEntity(), ppoMount.getEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getEntity());
    }

    @Override
    public String toString() {
        return "PPOMount{" +
                "entity=" + entity +
                '}';
    }
}
