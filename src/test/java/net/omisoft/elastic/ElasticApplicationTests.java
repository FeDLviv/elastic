package net.omisoft.elastic;

import net.omisoft.elastic.entity.Info;
import org.junit.Assert;
import org.junit.Test;

public class ElasticApplicationTests {

    @Test
    public void entityBuilder() {
        String name = "some_text";

        Info unit = Info.builder()
                .name(name)
                .build();

        Assert.assertNotNull(unit.getId());
        Assert.assertNotNull(unit.getDate());
        Assert.assertEquals(unit.getName(), name);
        Assert.assertNull(unit.getDuration());
    }

    @Test
    public void entityDefaultConstructor() {
        Info unit = new Info();

        Assert.assertNull(unit.getId());
        Assert.assertNull(unit.getDate());
        Assert.assertNull(unit.getName());
        Assert.assertNull(unit.getDuration());
    }

}
