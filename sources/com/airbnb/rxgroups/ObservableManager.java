package com.airbnb.rxgroups;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ObservableManager {
    private final AtomicLong nextId = new AtomicLong(1);
    private final Map<Long, ObservableGroup> observableGroupMap = new ConcurrentHashMap();
    private final UUID uuid = UUID.randomUUID();

    public ObservableGroup getGroup(long groupId) {
        ObservableGroup observableGroup = (ObservableGroup) this.observableGroupMap.get(Long.valueOf(groupId));
        if (observableGroup == null) {
            throw new IllegalArgumentException("Group not found with groupId=" + groupId);
        } else if (!observableGroup.isDestroyed()) {
            return observableGroup;
        } else {
            throw new IllegalArgumentException("Group is already destroyed with groupId=" + groupId);
        }
    }

    public ObservableGroup newGroup() {
        long id = this.nextId.getAndIncrement();
        ObservableGroup observableGroup = new ObservableGroup(id);
        this.observableGroupMap.put(Long.valueOf(id), observableGroup);
        return observableGroup;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: id */
    public UUID mo26670id() {
        return this.uuid;
    }

    public void destroy(ObservableGroup group) {
        group.destroy();
        this.observableGroupMap.remove(Long.valueOf(group.mo26652id()));
    }
}
