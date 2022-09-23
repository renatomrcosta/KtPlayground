package kt_1_7.context

private class Service1() { fun act1() {} }
private class Service2() { fun act2() {} }

fun main() {
    val service1 = Service1()
    val service2 = Service2()

    // lift service2 instance to main context
    with(service2) {
        service1.fromService2Call()
    }

    // lift both. Unfortunately, no 'multi-with's are available yet
    with(service1) {
        with(service2) {
            doSomethingAllContextual()
        }
    }
}

private fun Service1.doSomething(service2: Service2) {
    act1()
    service2.act2()
}

context(Service2)
private fun Service1.fromService2Call() {
    act1()
    act2()
}

context(Service1, Service2)
private fun doSomethingAllContextual() {
    act1()
    act2()
}
