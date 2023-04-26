package effective.software.testing.code.ch2;

import lombok.Getter;

@Getter
public class CartItem {

    private final String product;
    private final int quantity;
    private final double unitPrice;

    public CartItem(String product, int quantity, double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
