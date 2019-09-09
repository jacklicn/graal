/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.graalvm.compiler.nodes.util;

import org.graalvm.compiler.core.common.type.IntegerStamp;
import org.graalvm.compiler.nodes.LogicNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;

public abstract class IntegerHelper {
    protected final int bits;

    protected IntegerHelper(int bits) {
        this.bits = bits;
    }

    public abstract long upperBound(IntegerStamp stamp);

    public abstract long lowerBound(IntegerStamp stamp);

    public int compare(long a, long b) {
        return rawCompare(cast(a), cast(b));
    }

    public long min(long a, long b) {
        return rawMin(cast(a), cast(b));
    }

    public long max(long a, long b) {
        return rawMax(cast(a), cast(b));
    }

    public abstract long cast(long a);

    public abstract long minValue();

    public abstract long maxValue();

    public abstract IntegerStamp stamp(long min, long max);

    public abstract LogicNode createCompareNode(ValueNode x, ValueNode y, NodeView view);

    protected abstract int rawCompare(long a, long b);

    protected abstract long rawMin(long a, long b);

    protected abstract long rawMax(long a, long b);
}