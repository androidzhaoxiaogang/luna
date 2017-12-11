package luna.common;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractLifeCycle implements LifeCycle{
    protected final Logger logger  = LogManager.getLogger("luna");
    protected final Logger errorLog = LogManager.getLogger("error");
    protected final Logger timeLog = LogManager.getLogger("time");
    private AtomicBoolean  running = new AtomicBoolean(false);

    public boolean isStart(){ return running.get();}

    public boolean isStop(){return !running.get();}

    public void start(){
        running.compareAndSet(false,true);
    }

    public void stop(){
        running.compareAndSet(true,false);
    }

    public void abort(String why, Throwable e){
        logger.error("abort caused by "+ why,e);
        stop();
    }

}
