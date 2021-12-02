/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.idsmanager.commons.utils;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/*
  * @author Shengzhao Li
  */
public class UUIDGeneratorTest {

    @Test
    public void testGenerate() throws Exception {

        final String uuid = UUIDGenerator.generate();
        assertNotNull(uuid);
    }
}