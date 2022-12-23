package farm.nolimit;

import farm.Cow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoLimitCow implements Cow {
    private final UUID id;

    private final String nickName;

    private final List<NoLimitCow> children;

    public NoLimitCow(UUID id, String nickName, List<NoLimitCow> children) {
        this.id = id;
        this.nickName = nickName;
        this.children = children;
    }

    public NoLimitCow(String nickName) {
        this(UUID.randomUUID(), nickName, new ArrayList<>());
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    public List<NoLimitCow> getChildren() {
        return children;
    }
}
