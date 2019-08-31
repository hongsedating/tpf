该项目针对的是多saas场景。
目前该项目从场景、需求、架构、及实现系老K个人完成，出于开源精神，分享出来，望小伙伴们一起完善，最终的目标是成为一个成熟的多sass框架。
由于个人能力有限，一些问题和技术考虑不周，敬请指教。

tpf-saas项目的架构初步设计如下：
	前端————>Nginx—————>rest层————>微服务
	
目前项目组成如下：
	（1）tpf-study-service-core：微服务核心模块
	（2）tpf-study-service-demo：微服务demo模块，可从该模块开始，阅读tpf-saas微服务的代码
	各模块根目录下的readme.txt为模块的介绍。

微服务层使用技术：
    spring
    mybatis
    thrift
    zookeeper（微服务注册、配置）（暂未使用）
    redis（暂未使用）

