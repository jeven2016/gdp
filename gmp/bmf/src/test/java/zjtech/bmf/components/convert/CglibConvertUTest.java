package zjtech.bmf.components.convert;


import net.sf.cglib.beans.BeanCopier;
import org.junit.Test;
import zjtech.bmf.api.dto.servers.MemcachedClusterDTO;
import zjtech.bmf.api.dto.servers.MemcachedServerDTO;
import zjtech.common.result.IBaseDTO;
import zjtech.dmf.domain.servers.MemcachedClusterEntity;
import zjtech.dmf.domain.servers.MemcachedServerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CglibConvertUTest {
    IBeanConvertor convert = new CglibConverter();

    @Test
    public void testSimpleConvert() {
        MemcachedClusterEntity clusterEntity = generateClusterEntity();

        MemcachedClusterDTO clusterVO = new MemcachedClusterDTO();

        convert.convert(clusterEntity, clusterVO);

        //validate
        simpleValidate(clusterEntity, clusterVO);
    }

    private void simpleValidate(MemcachedClusterEntity clusterEntity, MemcachedClusterDTO clusterVO) {
        assertTrue("the servers shouldn't be empty.", !clusterVO.getServers().isEmpty());
        assertTrue("The server hasn't been converted to VO instance.", clusterVO.getServers().get(0) instanceof IBaseDTO);
        assertEquals("Name should be as same.", clusterEntity.getName(), clusterVO.getName());
        assertEquals("Description should be as same.", clusterEntity.getDescription(), clusterVO.getDescription());
        assertEquals("Disabled should be as same.", clusterEntity.getDisabled(), clusterVO.getDisabled());
    }

    @Test
    public void testServersAreOverriden() {
        MemcachedClusterDTO clusterVO = new MemcachedClusterDTO();
        MemcachedServerDTO serverVO = new MemcachedServerDTO();
        serverVO.setDescription("server dto");
        serverVO.setName("server dto name");
        serverVO.setId(5566l);
        serverVO.setPort(7622);
        serverVO.setAddress("serverAddressVo");

        clusterVO.getServers().add(serverVO);

        serverVO = new MemcachedServerDTO();
        serverVO.setDescription("server vo2");
        serverVO.setName("server dto name2");
        serverVO.setId(5626l);
        serverVO.setPort(22);
        serverVO.setAddress("serverAddressVo2");

        clusterVO.getServers().add(serverVO);

        MemcachedClusterEntity clusterEntity = generateClusterEntity();
        convert.convert(clusterEntity, clusterVO);

        //validate
        simpleValidate(clusterEntity, clusterVO);
        assertTrue("The length of servers should be 1.", clusterVO.getServers().size() == 1);
    }

    @Test
    public void testClusterCopySpeed() {
        MemcachedClusterEntity clusterEntity = generateClusterEntity();
        MemcachedClusterDTO clusterVO = new MemcachedClusterDTO();

        BeanCopier copier = BeanCopier.create(MemcachedClusterEntity.class, MemcachedClusterDTO.class, false);
        convert.convert(clusterEntity, clusterVO);

        //waiting for 5 seconds
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int count = 1000000;
        long timer1 = System.currentTimeMillis();
        IntStream.rangeClosed(0, count).forEach(i -> {
            MemcachedClusterDTO clusterVO2 = new MemcachedClusterDTO();
            copier.copy(clusterEntity, clusterVO2, null);
        });
        long timer2 = System.currentTimeMillis();
        long timerWithNonConverter = timer2 - timer1;
        System.out.println("copier.copy takes:" + timerWithNonConverter + " ms");

        //waiting for 5 seconds
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long timer3 = System.currentTimeMillis();
        IntStream.rangeClosed(0, count).forEach(i -> {
            MemcachedClusterDTO clusterVO3 = new MemcachedClusterDTO();
            convert.convert(clusterEntity, clusterVO3);
        });
        long timer4 = System.currentTimeMillis();
        long timer = timer4 - timer3;
        System.out.println("IConverter.convert takes:" + timer + " ms");
    }

    @Test
    public void testEntityToDTO() {
        MemcachedServerDTO dto = new MemcachedServerDTO();
        dto.setAddress("localhost");
        dto.setDescription("test");
        dto.setEnable(true);
        dto.setName("server");
        dto.setPort(8877);
        dto.setId(44l);

        MemcachedServerEntity entity = new MemcachedServerEntity();
        entity.setAddress("localhost");
        entity.setDescription("test");
        entity.setEnable(true);
        entity.setName("server");
        entity.setPort(8877);
        entity.setId(44l);

        BeanCopier copier = BeanCopier.create(MemcachedServerDTO.class, MemcachedServerEntity.class, false);

        //预热
//        BeanCopier copier = BeanCopier.create(MemcachedServerDTO.class, MemcachedServerEntity.class, false);
//        convert.convert(dto, entity);
    }

    @Test
    public void testServerCopySpeed() {
        MemcachedServerEntity entity = new MemcachedServerEntity();
        entity.setAddress("localhost");
        entity.setDescription("test");
        entity.setEnable(true);
        entity.setName("server");
        entity.setPort(8877);

        MemcachedServerDTO serverVO = new MemcachedServerDTO();

        //预热
        BeanCopier copier = BeanCopier.create(MemcachedServerEntity.class, MemcachedServerDTO.class, false);
        convert.convert(entity, serverVO);

        //waiting for 5 seconds
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int count = 1000000;
        long timer1 = System.currentTimeMillis();
        IntStream.rangeClosed(0, count).forEach(i -> {
            MemcachedServerDTO serverVO2 = new MemcachedServerDTO();
            copier.copy(entity, serverVO2, null);
        });
        long timer2 = System.currentTimeMillis();
        long timerWithNonConverter = timer2 - timer1;
        System.out.println("copier.copy takes:" + timerWithNonConverter + " ms");

        //waiting for 5 seconds
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long timer3 = System.currentTimeMillis();
        IntStream.rangeClosed(0, count).forEach(i -> {
            MemcachedServerDTO serverVO3 = new MemcachedServerDTO();
            convert.convert(entity, serverVO3);
        });
        long timer4 = System.currentTimeMillis();
        long timer = timer4 - timer3;
        System.out.println("IConverter.convert takes:" + timer + " ms");
    }

    private MemcachedClusterEntity generateClusterEntity() {
        MemcachedServerEntity entity = new MemcachedServerEntity();
        entity.setAddress("localhost");
        entity.setDescription("test");
        entity.setEnable(true);
        entity.setName("server");
        entity.setPort(8877);

        List<MemcachedServerEntity> list = new ArrayList<>();
        list.add(entity);

        MemcachedClusterEntity clusterEntity = new MemcachedClusterEntity();
        clusterEntity.setName("cluster");
        clusterEntity.setDescription("cluster");
        clusterEntity.setDisabled(false);
        clusterEntity.setId(223l);

//    clusterEntity.setServers(list);       //todo需要放开
        return clusterEntity;
    }
}
