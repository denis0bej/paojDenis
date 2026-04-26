package com.pao.laboratory07.exercise1;

public class Order {
    private StareComanda state;
    Order(StareComanda state) {
        this.state = state;
    }
    void nextState(){
        switch(state){
            case PLACED: state = StareComanda.PROCESSED;
            case PROCESSED: state = StareComanda.SHIPPED;
            case SHIPPED: state = StareComanda.DELIVERED;
            case DELIVERED: state = StareComanda.CANCELED;
        }
    }
    void undoState(){
        switch(state){
            case PLACED: state = StareComanda.CANCELED;
            case PROCESSED: state = StareComanda.PLACED;
            case SHIPPED: state = StareComanda.PROCESSED;
            case DELIVERED: state = StareComanda.SHIPPED;
        }
    }
    void cancel(){
        state = StareComanda.CANCELED;
    }
}
