package com.build.maven.simple.impl;

import junit.framework.TestCase;
import com.build.maven.simple.Bean;

public class BeanImplTest extends TestCase {

    public void testBeanIsABean() {
	Bean aBean = new BeanImpl();
        assertTrue(aBean.isABean());
    }

}