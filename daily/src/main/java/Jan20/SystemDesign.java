package Jan20;

public class SystemDesign {

    /**
     * System Design - Interview
     *
     * 1. The amount of data we are getting, the complexity of data and the speed at which it is changing.
     * 2. Send a message to another process, to be handled asyc. (stream processing)
     * 3. Periodically crunch a large amount of accumulated data (batch processing)
     * 4. Remember the result of an expensive operation, or same data getting read multiple times (cache)
     * 5. Store data such that current or other applications can find it later (database)
     * 6. Lets try to achieve reliable, scalable, maintainable system. By taking different decision on the design for the current problem.
     * 7. How do we ensure that the data remain correct and complete, event when things go wrong interally?
     * 8. How we will provide consistently good performance for clients even when parts of tthe system are degraded?
     * 9. How do we scale to handle an increase on load? black friday.
     * 10. Lets draw the diagram of CAP vs RSM (reliable scalable maintainable)
     *
     * 11. Reliable - Continue to work correctly even when things go wrong.
     * (1. The application perform the expected operation the user expects;
     * 2. The application can tolerate the user making mistakes or using the applicaiton in unexpected ways;
     * 3. The application performance is good enough for the required use case,
     *  under the expected load and data volume;
     * 4. The system prevents any unauthorized access and abuse;
     * 5. Tolerating faults (meaning resilient)).
     */
}
