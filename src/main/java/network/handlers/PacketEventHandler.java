//package network.handlers;
//
//
//import reactor.core.Environment;
//import reactor.core.Reactor;
//import reactor.core.spec.Reactors;
//import reactor.event.Event;
//
//import static reactor.event.selector.Selectors.$;
//
//public class PacketEventHandler {
//
//    public void reactor() {
//
//
//        // This factory call creates a Reactor.
//        Reactor reactor = Reactors.reactor()
//                .env(env) // our current Environment
//                .dispatcher(Environment.EVENT_LOOP) // use one of the BlockingQueueDispatchers
//                .get(); // get the object when finished configuring
//
//
//        @Inject
//        Service service;
//
//        // Use a method reference to create a Consumer<Event<T>>
//        reactor.on($("parse"), service::handleEvent);
//
//        // Notify consumers of the 'parse' topic that data is ready
//        // by passing a Supplier<Event<T>> in the form of a lambda
//
//    }
//}
