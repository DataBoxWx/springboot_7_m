package com.bjpowernode.p2p.admin.schedule;

import com.bjpowernode.p2p.admin.service.ContractService;
import com.bjpowernode.p2p.admin.service.CreditorRightsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务
 * 
 * @author yanglijun
 *
 */
@Configuration
@EnableScheduling //该注解启用定时任务
public class SchedulingTask {
	
	/**日志记录器*/
	private static final Logger logger = LoggerFactory.getLogger(SchedulingTask.class);
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private CreditorRightsService creditorRightsService;

	//线上每1小时执行一次
	@Scheduled(cron = "0/5 * * * * *")
    public void scheduler() {
        logger.debug(">>>>>>>>>>>>> scheduled start ... ");
        
        //contractService.genegateContract(null);
        
        logger.debug(">>>>>>>>>>>>> scheduled end ... ");
        
    }
	
	//线上每1小时执行一次
	//@Scheduled(cron = "0/5 * * * * *")
    public void creditorScheduler() {
        logger.debug(">>>>>>>>>>>>> scheduled start ... ");
        
        //creditorRightsService.receiveCreditor();
        
        logger.debug(">>>>>>>>>>>>> scheduled end ... ");
    }
}