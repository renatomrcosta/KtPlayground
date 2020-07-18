package coThrift

import coThrift.handler.ServiceHandler
import com.xunfos.playground.PlaygroundService
import org.apache.thrift.server.TServer
import org.apache.thrift.server.TSimpleServer
import org.apache.thrift.transport.TServerSocket
import util.trace

fun main() {
    val processor = PlaygroundService.Processor(ServiceHandler())

    val server = TSimpleServer(
        TServer
            .Args(TServerSocket(9090))
            .processor(processor)
    )
    trace("Starting the server")
    server.serve()
    trace("Somehow I ran after the server is gone?")
}
