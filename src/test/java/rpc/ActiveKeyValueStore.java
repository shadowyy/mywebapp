package rpc;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author shadowyy
 * @version 2017/9/7 12:59
 */
public class ActiveKeyValueStore extends ConnectionWatcher {
    public void write(String path, String value) throws InterruptedException, KeeperException {
        Stat stat = zk.exists(path, false);
        if (stat == null) {
            zk.create(path, value.getBytes(UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            zk.setData(path, value.getBytes(UTF_8), -1);
        }
    }

    public String read(String path, Watcher watcher) throws InterruptedException, KeeperException {
        byte[] data = zk.getData(path, watcher, null/*stat*/);//调用者不仅可以获得数据，还能够获得znode的metadata
        return new String(data, UTF_8);
    }
}