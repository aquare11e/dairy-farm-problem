package farm.limited;

import farm.Cow;
import farm.limited.list.NodeList;

import java.util.UUID;

public class LimitedCow implements Cow {
    private final UUID id;

    private final String nickName;

    private final NodeList<LimitedCow> children;

    public LimitedCow(UUID id, String nickName, NodeList<LimitedCow> children) {
        this.id = id;
        this.nickName = nickName;
        this.children = children;
    }

    public LimitedCow(String nickName) {
        this(UUID.randomUUID(), nickName, new NodeList<>());
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    public NodeList<LimitedCow> getChildren() {
        return children;
    }
}
