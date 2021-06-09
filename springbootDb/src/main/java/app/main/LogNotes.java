package app.main;

/**
 
 
 Logger intro:
---------------

steps:
----------

1.create the logger obj in every component classes


ex:


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
private static final Logger logger = LoggerFactory.getLogger(TestApp.class);


why logger?
ans)
1.we will not write syso() statements , as they are costly
2.log statements are helpfull for tracing the issues or identifying the root cause for defects.

log levels:
-----------
logger.info("hello info");
logger.warn("hello warn");
logger.debug("hello debug");
logger.error("hello error");


where do we write the log messages?
ans)
inside the method/constr/static block.

what is the default log level?
ans)
ERROR

in production we will always set log level as ERROR

in case if there areany issues then we need to change the loglevel to "debug"


all log statements are printed in the server logs.

 */
public class LogNotes {

}
