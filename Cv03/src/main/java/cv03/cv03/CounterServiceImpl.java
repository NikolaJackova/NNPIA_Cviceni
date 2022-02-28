package cv03.cv03;

import org.springframework.stereotype.Service;

//Spring is holding Singleton of this class
@Service
public class CounterServiceImpl implements CounterService {

    private Integer counter = 0;

    @Override
    public void add() {
        this.counter++;
    }

    @Override
    public Integer getCounter(){
        return this.counter;
    }
}
