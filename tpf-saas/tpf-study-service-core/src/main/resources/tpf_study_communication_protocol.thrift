namespace java com.tpf.framework.communication

enum ProtocolVersion{
V1
}
service CommunicationProtocol
{
string process(1:required ProtocolVersion version,2:required string path,3:string data)
}
