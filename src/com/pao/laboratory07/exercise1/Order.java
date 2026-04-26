package com.pao.laboratory07.exercise1;

import com.pao.laboratory07.exercise1.exceptions.CannotCancelFinalOrderException;
import com.pao.laboratory07.exercise1.exceptions.CannotRevertInitialOrderStateException;
import com.pao.laboratory07.exercise1.exceptions.OrderIsAlreadyFinalException;

public class Order {
    OrderState state;
    public Order(OrderState state) {
        this.state = state;
    }
    public void nextState(){
        switch (state) {
            case PLACED -> {
                state = OrderState.PROCESSED;
                System.out.println("Order state updated to: PROCESSED");
            }
            case PROCESSED -> {
                state = OrderState.SHIPPED;
                System.out.println("Order state updated to: SHIPPED");
            }
            case SHIPPED -> {
                state = OrderState.DELIVERED;
                System.out.println("Order state updated to: DELIVERED");
            }
            case DELIVERED -> throw new OrderIsAlreadyFinalException("Order is already delivered.");
            case CANCELED -> throw new OrderIsAlreadyFinalException("Order is already canceled.");
        }
    }
    public void cancel(){
        if (state != OrderState.CANCELED && state != OrderState.DELIVERED) {
            state = OrderState.CANCELED;
            System.out.println("Order has been canceled.");
        } else {
            throw new CannotCancelFinalOrderException("Cannot cancel a final state order.");
        }
    }
    public void undoState(){
        switch (state) {
            case PLACED -> throw new CannotRevertInitialOrderStateException("Cannot undo the initial order state.");
            case PROCESSED -> {
                state = OrderState.PLACED;
                System.out.println("Order state reverted to: PLACED");
            }
            case SHIPPED -> {
                state = OrderState.PROCESSED;
                System.out.println("Order state reverted to: PROCESSED");
            }
            case DELIVERED -> {
                state = OrderState.SHIPPED;
                System.out.println("Order state reverted to: SHIPPED");
            }
            case CANCELED -> {
                state = OrderState.PROCESSED;
                System.out.println("Order state reverted to: PROCESSED");
            }
        }
    }
}
