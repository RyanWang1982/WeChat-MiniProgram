/**
 * 
 */
package wang.yongrui.wechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import wang.yongrui.wechat.entity.jpa.ActionEntity;

/**
 * @author Wang Yongrui
 *
 */
@Repository
public interface ActionRepository extends JpaRepository<ActionEntity, Long>, JpaSpecificationExecutor<ActionEntity> {

}
