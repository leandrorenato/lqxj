/*
 * Copyright 2017 John L. Jegutanis
 * Copyright 2018 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.script;

import com.google.common.collect.Lists;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.params.MainNetParams;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ScriptPatternTest {
    private List<ECKey> keys = Lists.newArrayList(new ECKey(), new ECKey(), new ECKey());
    private static final NetworkParameters MAINNET = MainNetParams.get();

    @Test
    public void testCommonScripts() {
        assertTrue(ScriptPattern.isP2PKH(
                ScriptBuilder.createP2PKHOutputScript(keys.get(0))
        ));
        assertTrue(ScriptPattern.isP2SH(
                ScriptBuilder.createP2SHOutputScript(2, keys)
        ));
        assertTrue(ScriptPattern.isP2PK(
                ScriptBuilder.createP2PKOutputScript(keys.get(0))
        ));
        assertTrue(ScriptPattern.isSentToMultisig(
                ScriptBuilder.createMultiSigOutputScript(2, keys)
        ));
        assertTrue(ScriptPattern.isSentToCltvPaymentChannel(
                ScriptBuilder.createCLTVPaymentChannelOutput(BigInteger.ONE, keys.get(0), keys.get(1))
        ));
        assertTrue(ScriptPattern.isOpReturn(
                ScriptBuilder.createOpReturnScript(new byte[10])
        ));
    }
}
