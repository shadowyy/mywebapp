package com.shadow.domain;

import java.util.List;

/**
 * Created by alice on 2016/12/1 18:04
 */
public class AreaPO {
    private String id;
    private String name;
    private List<ServerPO> servers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServerPO> getServers() {
        return servers;
    }

    public void setServers(List<ServerPO> servers) {
        this.servers = servers;
    }
}
