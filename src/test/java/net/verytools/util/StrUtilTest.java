package net.verytools.util;

import net.verytools.util.model.SplitResult;
import org.junit.Assert;
import org.junit.Test;

public class StrUtilTest {

    @Test
    public void testSplit() {
        SplitResult ret = StrUtil.split(",a,bc,", ",");
        Assert.assertEquals(3, ret.len());
        Assert.assertNull(ret.get(3));
        Assert.assertEquals(ret.get(2), "bc");

        ret = StrUtil.split(",a,bc,", ",", true);
        Assert.assertEquals(2, ret.len());
        Assert.assertEquals(ret.get(0), "a");
        Assert.assertEquals(ret.get(1), "bc");
        Assert.assertNull(ret.get(2));

        ret = StrUtil.split(null, ",");
        Assert.assertEquals(0, ret.len());
        Assert.assertNull(ret.get(100));

        ret = StrUtil.split(null, ",", true);
        Assert.assertEquals(0, ret.len());
        Assert.assertNull(ret.get(100));

        ret = StrUtil.split("", ",");
        Assert.assertEquals(1, ret.len());
        Assert.assertEquals(ret.get(0), "");

        ret = StrUtil.split(",   ,", ",", true);
        Assert.assertEquals(0, ret.len());

        ret = StrUtil.split(",   ,1,", ",", true);
        Assert.assertEquals(1, ret.len());
        Assert.assertEquals(ret.get(0), "1");
    }

}
