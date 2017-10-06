/**
 * 
 */
package wang.yongrui.wechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import wang.yongrui.wechat.entity.jpa.PartEntity;

/**
 * @author Wang Yongrui
 *
 */
public interface PartRepository extends JpaRepository<PartEntity, Long>, JpaSpecificationExecutor<PartEntity> {

}
