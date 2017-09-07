package rpc;

import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * @author shadowyy
 * @version 2017/9/7 11:57
 */
public class DeleteGroup extends ConnectionWatcher {
    public void delete(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;

        try {
            List<String> children = zk.getChildren(path, false);
            for (String child : children) {
                zk.delete(path + "/" + child, -1);
            }
            zk.delete(path, -1);
        } catch (KeeperException.NoNodeException e) {
            System.out.printf("Group %s does not exist\n", groupName);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("127.0.0.1:2181");
        deleteGroup.delete("zoo");
        deleteGroup.close();
    }
}
