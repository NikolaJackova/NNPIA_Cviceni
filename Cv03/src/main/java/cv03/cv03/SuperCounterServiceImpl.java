package cv03.cv03;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
//When and how is creating bean (session, singleton, prototype (creating with every call), request (new instance in http request))
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SuperCounterServiceImpl implements CounterService{

    private Integer counter = 1;

    @Override
    public void add() {
        this.counter *= 2;
    }

    @Override
    public Integer getCounter() {
        return this.counter;
    }
}
