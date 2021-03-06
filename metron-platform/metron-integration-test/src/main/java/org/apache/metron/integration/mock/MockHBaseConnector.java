/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.metron.integration.mock;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.metron.hbase.Connector;
import org.apache.metron.hbase.TupleTableConfig;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockHBaseConnector extends Connector {
    static List<Put> puts = Collections.synchronizedList(new ArrayList<Put>());
    public MockHBaseConnector(TupleTableConfig conf, String _quorum, String _port) throws IOException {
        super(conf, _quorum, _port);
    }

    @Override
    public void put(Put put) throws InterruptedIOException, RetriesExhaustedWithDetailsException {
        puts.add(put);
    }

    @Override
    public void close() {

    }
    public static void clear() {
        puts.clear();
    }
    public static List<Put> getPuts() {
        return puts;
    }
}
