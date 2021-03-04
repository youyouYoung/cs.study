package tutor;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * 功能描述: 启动类
 *
 * @author youyou
 * @date 5/20/20 11:33 AM
 */
public class Driver {

    public static void main(String[] args) {

    }

    private static CoordinatorRegistryCenter getRegistryCenter() {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration("127.0.0.1:2181", "elastic-job-tutor"));
    }
}
