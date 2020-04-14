/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.internal.server.tcp;

import com.hazelcast.cluster.Address;
import com.hazelcast.internal.server.NetworkStats;
import com.hazelcast.internal.nio.ConnectionListener;
import com.hazelcast.internal.nio.Packet;
import com.hazelcast.internal.server.Server;
import com.hazelcast.internal.server.ServerConnection;
import com.hazelcast.internal.server.ServerConnectionManager;

import java.util.Collection;
import java.util.Set;

public class TextViewUnifiedServerConnectionManager
        implements ServerConnectionManager {

    private final UnifiedServerConnectionManager unifiedEndpointManager;
    private final boolean rest;

    TextViewUnifiedServerConnectionManager(UnifiedServerConnectionManager unifiedEndpointManager, boolean rest) {
        this.unifiedEndpointManager = unifiedEndpointManager;
        this.rest = rest;
    }

    @Override
    public Server getServer() {
        return unifiedEndpointManager.getServer();
    }

    @Override
    public Set<ServerConnection> getActiveConnections() {
        return rest ? unifiedEndpointManager.getRestConnections() : unifiedEndpointManager.getMemachedConnections();
    }

    @Override
    public Collection<ServerConnection> getConnections() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addConnectionListener(ConnectionListener listener) {
        unifiedEndpointManager.addConnectionListener(listener);
    }

    @Override
    public void accept(Packet packet) {
        unifiedEndpointManager.accept(packet);
    }

    @Override
    public boolean register(Address remoteAddress, ServerConnection connection) {
        return unifiedEndpointManager.register(remoteAddress, connection);
    }

    @Override
    public ServerConnection get(Address address) {
        return unifiedEndpointManager.get(address);
    }

    @Override
    public ServerConnection getOrConnect(Address address) {
        return unifiedEndpointManager.getOrConnect(address);
    }

    @Override
    public ServerConnection getOrConnect(Address address, boolean silent) {
        return unifiedEndpointManager.getOrConnect(address, silent);
    }

    @Override
    public boolean transmit(Packet packet, ServerConnection connection) {
        return unifiedEndpointManager.transmit(packet, connection);
    }

    @Override
    public boolean transmit(Packet packet, Address target) {
        return unifiedEndpointManager.transmit(packet, target);
    }

    @Override
    public NetworkStats getNetworkStats() {
        return unifiedEndpointManager.getNetworkStats();
    }

    @Override
    public String toString() {
        return unifiedEndpointManager.toString();
    }
}
