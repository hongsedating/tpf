1、微服务接口定义如下：
      /**
       * @param version 必填，协议版本
       * @param path 必填，请求路径，微服务根据请求路径跳转到指定处理器
       * @param data 请求参数，V1版本，参数可为空，但若不为空时，必须是Json格式，之所以类型为String，是考虑到后续版本的扩展的简约、灵活
       * */
    public String process(ProtocolVersion version, String path, String data) throws TException;
